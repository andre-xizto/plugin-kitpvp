package dev.buskopan.kitPvP.data.player;

import dev.buskopan.kitPvP.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class PlayerDataManager {

    private File file;
    private FileConfiguration config;

    public PlayerDataManager() {
        file = new File("plugins/KitPvP", "players.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public void saveConfig() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadConfig() {
        config = YamlConfiguration.loadConfiguration(file);
    }


}
