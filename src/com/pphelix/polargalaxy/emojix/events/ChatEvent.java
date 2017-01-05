package com.pphelix.polargalaxy.emojix.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.pphelix.polargalaxy.emojix.configs.EmojiConfig;

public class ChatEvent implements Listener {

	// Make the event highest priority due to the consistency of the chat
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onChat(AsyncPlayerChatEvent event) {
		String message = event.getMessage();

		//TODO: REMOVE UPON RELEASE
		message = ChatColor.RED + message;

		// This allows us to retain the chat color after we use Emotes
		String oldColor = ChatColor.getLastColors(message);

		String[] words = message.split(" ");

		for (Object obj : EmojiConfig.emoji.getConfigurationSection("emoji").getKeys(false)) {

			/* This loop allows detection for Emotes mixed with words and also
			   eliminates Emotes appearing in URL's - Emotes must have a space
			   on both sides if applicable */
			for (String word : words) {

				// This is to allow the first word to be an Emoji should users
				// use an Emoji at the start
				if (word == words[0]) {
					word = ChatColor.stripColor(word);
					if (word.equals(obj.toString()))
						message = message.replace(obj.toString(),
								ChatColor.WHITE + EmojiConfig.emoji.getString("emoji." + obj.toString()) + oldColor);
					continue;
				}
				
				if (word.equals(obj.toString())) {
					message = message.replace(obj.toString(),
							ChatColor.WHITE + EmojiConfig.emoji.getString("emoji." + obj.toString()) + oldColor);
				}
			}
		}
		event.setMessage(message);
	}
}
