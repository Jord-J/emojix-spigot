package com.polargalaxy.emojix.configs;

import java.io.File;
import org.bukkit.configuration.file.YamlConfiguration;

import com.polargalaxy.emojix.EmojiX;

public class EmojiConfig {

	private EmojiX plugin;
	public static YamlConfiguration emoji;

	public EmojiConfig(EmojiX plugin) {
		this.plugin = plugin;

		// Call loadConfig method - This is so we can access config for Plugin
		// purposes
		loadConfig();
	}

	private void loadConfig() {
		// If Plugin folder doesn't exist generate a new folder
		if (!plugin.getDataFolder().exists())
			plugin.getDataFolder().mkdirs();

		// Obtain File
		File file = new File(plugin.getDataFolder(), "emoji.yml");

		// If the config file doesn't exist generate a default config via
		// the one from resources
		if (!file.exists())
			plugin.saveResource("emoji.yml", true);

		// Load the config for plugin use
		emoji = YamlConfiguration.loadConfiguration(file);
	}
}