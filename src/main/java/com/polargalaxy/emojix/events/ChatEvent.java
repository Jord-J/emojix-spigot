package com.polargalaxy.emojix.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.polargalaxy.emojix.configs.EmojiConfig;

public final class ChatEvent implements Listener {

	// Make the event highest priority due to the consistency of the chat
	@EventHandler(priority = EventPriority.HIGHEST)
	public final void onChat(AsyncPlayerChatEvent event) {

		String message = event.getMessage();
		Player player = (Player) event.getPlayer();
		String[] words = message.split(" ");

		// If player has colored prefix we obtain that color
		String color = ChatColor.getLastColors(words[0].toString());

		/*
		 * This loop allows detection for Emotes mixed with words and also
		 * eliminates Emotes appearing in URL's - Emotes must have a space on
		 * both sides if applicable
		 */
		for (Object obj : EmojiConfig.emoji.getConfigurationSection("emoji").getKeys(false)) {
			for (String word : words) {

				// This is so the user can use an emoji singularly or at the
				// start of line
				if (word == words[0])
					word = ChatColor.stripColor(word);

				if (word.equals(obj.toString()))
					if (player.hasPermission("emojix.chat." + word))
						message = message.replace(word,
								ChatColor.RESET + EmojiConfig.emoji.getString("emoji." + obj.toString()) + color);
			}
		}

		event.setMessage(message);
		// TODO: Plugin works as intended - 1. Fix any bugs should they occur 2. Add extra functionality
	}
}
