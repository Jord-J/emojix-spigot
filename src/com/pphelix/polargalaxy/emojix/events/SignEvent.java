package com.pphelix.polargalaxy.emojix.events;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import com.pphelix.polargalaxy.emojix.configs.EmojiConfig;

public class SignEvent implements Listener {

	private ArrayList<String> colors = new ArrayList<String>();

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onSignChange(SignChangeEvent event) {
		String[] lines = event.getLines();
		Player player = (Player) event.getPlayer();

		for (int i = 0; i < lines.length; i++)
			colors.add(ChatColor.getLastColors(lines[i]));

		for (Object obj : EmojiConfig.emoji.getConfigurationSection("emoji").getKeys(false)) {

			/*
			 * This loop allows detection for Emotes mixed with words and also
			 * eliminates Emotes appearing in URL's - Emotes must have a space
			 * on both sides if applicable
			 */
			for (int i = 0; i < lines.length; i++) {
				String[] words = lines[i].split(" ");

				for (String word : words) {

					// This is to allow the first word to be an Emoji should
					// use an Emoji at the start
					if (word == words[0])
						word = ChatColor.stripColor(word);

					if (word.equals(obj.toString())) {
						if (player.hasPermission("emojix.sign." + word))
							lines[i] = lines[i].replace(obj.toString(), ChatColor.RESET
									+ EmojiConfig.emoji.getString("emoji." + obj.toString()) + colors.get(i));
						else if (player.hasPermission("emojix.sign.*"))
							lines[i] = lines[i].replace(obj.toString(), ChatColor.RESET
									+ EmojiConfig.emoji.getString("emoji." + obj.toString()) + colors.get(i));
						else
							continue;
					}
				}
				event.setLine(i, lines[i]);
				// TODO: Plugin works as intended - 1. Fix any bugs should they occur 2. Add extra functionality
			}
		}
	}
}
