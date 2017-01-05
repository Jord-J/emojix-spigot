package com.pphelix.polargalaxy.emojix;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.pphelix.polargalaxy.emojix.configs.EmojiConfig;
import com.pphelix.polargalaxy.emojix.events.ChatEvent;

public class EmojiX extends JavaPlugin {
	
	private final PluginDescriptionFile pdf = getDescription();
	private final Logger logger = Logger.getLogger( "Minecraft" );
	
	@Override
	public void onEnable()
	{
		logger.info( pdf.getName() + " Has been enabled!" );
		getServer().getPluginManager().registerEvents(new ChatEvent(), this);
		
		new EmojiConfig( this );
	}
	
	@Override
	public void onDisable()
	{
		logger.info( pdf.getName() + " Has been disabled!" );
	}
}
