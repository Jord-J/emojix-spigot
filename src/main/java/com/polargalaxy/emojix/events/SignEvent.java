package com.polargalaxy.emojix.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import com.polargalaxy.emojix.configs.EmojiConfig;

public final class SignEvent implements Listener {

	// Make the event monitor priority
	@EventHandler(priority = EventPriority.MONITOR)
	public final void onSignChange(SignChangeEvent event) {

		String[] lines = event.getLines();
		Player player = (Player) event.getPlayer();

		if (!player.hasPermission("emojix.sign.use"))
			return;

		/*
		 * This loop allows detection for emotes mixed with words and also
		 * eliminates emotes appearing in URL's - emotes must have a space on
		 * both sides if applicable
		 */
		for (Object obj : EmojiConfig.emoji.getConfigurationSection("emoji").getKeys(false)) {
			for (int i = 0; i < lines.length; i++) {

				String[] words = lines[i].split(" ");

				// If player has colored prefix we obtain that color
				String color = ChatColor.getLastColors(words[0].toString());

				for (String word : words) {

					// This is so the user can use an emoji singularly or at the
					// start of line
					if (word == words[0])
						word = ChatColor.stripColor(word);

					if (word.equals(obj.toString()))
						if (player.hasPermission("emojix.chat." + word))
							lines[i] = lines[i].replace(word,
									ChatColor.RESET + EmojiConfig.emoji.getString("emoji." + obj.toString()) + color);
				}

				event.setLine(i, lines[i]);
				// TODO: Plugin works as intended - 1. Fix any bugs should they occur 2. Add extra functionality
			}
		}
	}
}
