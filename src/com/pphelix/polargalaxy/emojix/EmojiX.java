package com.pphelix.polargalaxy.emojix;

import org.bukkit.plugin.java.JavaPlugin;

import com.pphelix.polargalaxy.emojix.configs.EmojiConfig;
import com.pphelix.polargalaxy.emojix.events.ChatEvent;

public class EmojiX extends JavaPlugin {

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new ChatEvent(), this);

		new EmojiConfig(this);
	}
}
