package com.polargalaxy.emojix;

import org.bukkit.plugin.java.JavaPlugin;

import com.polargalaxy.emojix.commands.EmojiXCommand;
import com.polargalaxy.emojix.configs.AttributeConfig;
import com.polargalaxy.emojix.configs.EmojiConfig;
import com.polargalaxy.emojix.events.ChatEvent;
import com.polargalaxy.emojix.events.SignEvent;

public class EmojiX extends JavaPlugin {

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new ChatEvent(), this);
		getServer().getPluginManager().registerEvents(new SignEvent(), this);

		getCommand("EmojiX").setExecutor(new EmojiXCommand(this));

		new EmojiConfig(this);
		new AttributeConfig(this);
	}
}
