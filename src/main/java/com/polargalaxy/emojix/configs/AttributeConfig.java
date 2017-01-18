package com.polargalaxy.emojix.configs;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;

import com.polargalaxy.emojix.EmojiX;

public class AttributeConfig {

	private EmojiX plugin;
	public static YamlConfiguration config;

	public AttributeConfig(EmojiX plugin) {
		this.plugin = plugin;

		// Call loadConfig method - This is so we can access config for Plugin
		// purposes
		loadConfig();
	}

	private void loadConfig() {
		try {
			// If Plugin folder doesn't exist generate a new folder
			if (!plugin.getDataFolder().exists())
				plugin.getDataFolder().mkdirs();

			// Obtain File, if not generate new file under the emoji config name
			File file = new File(plugin.getDataFolder(), "config.yml");

			// If the config file doesn't exist generate a default config via
			// resource
			if (!file.exists())
				plugin.saveResource("config.yml", true);

			// Load the config for plugin use
			config = YamlConfiguration.loadConfiguration(file);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}