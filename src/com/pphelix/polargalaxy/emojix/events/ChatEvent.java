package com.pphelix.polargalaxy.emojix.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.pphelix.polargalaxy.emojix.configs.EmojiConfig;

public class ChatEvent implements Listener {

	// Make the event highest priority due to the consistency of the chat
	@EventHandler( priority = EventPriority.HIGHEST )
	public void onChat( AsyncPlayerChatEvent event )
	{
		// Get the message and store it as a variable
		String message = event.getMessage();
		
		// This is for testing purposes
		message = ChatColor.RED + message;
		
		// This allows us to retain the chat color after we use Emotes
		String oldColor = ChatColor.getLastColors( message.toString() );
		
		// Split the message into an array
		String[] words = message.split( " " );
		
		for ( Object obj : EmojiConfig.emoji.getConfigurationSection( "emoji" ).getKeys( false ) ) {
				
			// This loop allows detection for Emotes mixed with words and also eliminates Emotes appearing in URL's - Emotes must have a space on both sides if applicable
			for( String word : words ) {
				
				if( word.toString().equals( obj.toString() ) ) {
					message = message.replace( obj.toString(), ChatColor.WHITE + EmojiConfig.emoji.getString( "emoji." + obj.toString() ) + oldColor );
				}
			}
		}
		event.setMessage( message );
	}
}
