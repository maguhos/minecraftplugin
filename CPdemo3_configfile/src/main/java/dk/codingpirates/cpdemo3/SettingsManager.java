/**
 *
 * @author maguhos
 */
package dk.codingpirates.cpdemo3;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

public class SettingsManager {

    private static SettingsManager instance = null;

    public static synchronized SettingsManager getInstance() {
        if (instance == null) {
            instance = new SettingsManager();
        }
        return instance;
    }

    private static Plugin plugin;
    FileConfiguration config;
    File cfile;
    private static File datafolder;

    public void setup(Plugin plugin) {
        this.plugin = plugin;
        //config.options().copyDefaults(true);
        //saveConfig();
        this.plugin.saveDefaultConfig();
        config = this.plugin.getConfig();
        datafolder = this.plugin.getDataFolder();
        cfile = new File(datafolder, "config.yml");
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public void saveConfig() {
        try {
            config.save(cfile);
            //p.saveConfig();
        } catch (IOException e) {
            Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save config.yml!");
        }
    }

    public void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(cfile);
    }

    public PluginDescriptionFile getDesc() {
        return plugin.getDescription();
    }

    public Plugin getPlugin() {
        return plugin;
    }

    /**
     * @return the datafolder
     */
    public File getDatafolder() {
        return datafolder;
    }

    public String getDataFolder() {
        return datafolder.toString();
    }

    public YamlConfiguration loadConfigFile(String file) {
        File yamlFile = new File(datafolder, file);
        YamlConfiguration config = null;
        if (yamlFile.exists()) {
            try {
                config = new YamlConfiguration();
                config.load(yamlFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            config = new YamlConfiguration();
            try {
                if (plugin.getResource(file) != null) {
                    plugin.saveResource(file, false);
                    config = new YamlConfiguration();
                    config.load(yamlFile);
                } else {
                    config.save(yamlFile);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return config;
    }

    public void saveConfigFile(YamlConfiguration config, String file) {
        File yamlFile = new File(datafolder, file);
        try {
            config.save(yamlFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
