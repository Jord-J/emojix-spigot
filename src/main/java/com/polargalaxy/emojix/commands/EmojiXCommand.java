package com.polargalaxy.emojix.commands;

import java.util.HashMap;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import com.polargalaxy.emojix.EmojiX;
import com.polargalaxy.emojix.configs.AttributeConfig;
import com.polargalaxy.emojix.configs.EmojiConfig;

import mkremins.fanciful.FancyMessage;

public class EmojiXCommand implements CommandExecutor {

	public EmojiX plugin;
	private HashMap<String, String> emojiHash = new HashMap<String, String>();

	public EmojiXCommand(EmojiX plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
		// If command does not equal to 'EmojiX' then return
		if (!cmd.getName().equalsIgnoreCase("EmojiX"))
			return false;

		// Checks to see if Player has permission to use help command
		if (!sender.hasPermission("emojix.chat.help")) {
			sender.sendMessage(
					ChatColor.translateAlternateColorCodes('&', AttributeConfig.config.getString("nopermission.color"))
							+ AttributeConfig.config.getString("nopermission.string"));
			return true;
		}

		FancyMessage emojis = new FancyMessage("");

		/*
		 * I used a HashMap for tooltip purposes, FancyMessage wasn't detecting
		 * empty string so I had to try a different approach to get the 'You
		 * have no Emojis' message
		 */
		for (Object obj : EmojiConfig.emoji.getConfigurationSection("emoji").getKeys(false)) {
			if (sender.hasPermission("emojix.chat." + obj.toString()))
				emojiHash.put(obj.toString(), EmojiConfig.emoji.getString("emoji." + obj.toString()));
			else if (sender.hasPermission("emojix.chat.*"))
				emojiHash.put(obj.toString(), EmojiConfig.emoji.getString("emoji." + obj.toString()));
			else
				continue;
		}

		// If hash is empty - Change message, else display Emojis
		if (emojiHash.isEmpty())
			emojis.then(ChatColor.translateAlternateColorCodes('&', AttributeConfig.config.getString("noemoji.color"))
					+ AttributeConfig.config.getString("noemoji.string"))
					.tooltip(ChatColor.translateAlternateColorCodes('&',
							AttributeConfig.config.getString("tt-noemoji.color"))
							+ AttributeConfig.config.getString("tt-noemoji.string"));
		else
			for (HashMap.Entry<String, String> entry : emojiHash.entrySet())
				emojis.then(entry.getValue()).color(ChatColor.WHITE).insert(entry.getValue()).tooltip(entry.getKey())
						.color(ChatColor.WHITE);

		FancyMessage header = new FancyMessage(ChatColor.translateAlternateColorCodes('&',
				AttributeConfig.config.getString("suffix.color") + "EmojiX"))
						.tooltip(ChatColor.translateAlternateColorCodes('&',
								AttributeConfig.config.getString("tt-suffix.color")
										+ AttributeConfig.config.getString("tt-suffix.string")))
						.then(" "
								+ ChatColor.translateAlternateColorCodes('&',
										AttributeConfig.config.getString("hover.color"))
								+ AttributeConfig.config.getString("hover.string"))
						.tooltip(ChatColor.translateAlternateColorCodes('&',
								AttributeConfig.config.getString("tt-hover.color"))
								+ AttributeConfig.config.getString("tt-hover.string"));
		header.send(sender);
		emojis.send(sender);
		return true;
	}
}