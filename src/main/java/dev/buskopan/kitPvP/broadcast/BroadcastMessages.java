package dev.buskopan.kitPvP.broadcast;

import dev.buskopan.kitPvP.Main;
import dev.buskopan.kitPvP.config.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.List;

public class BroadcastMessages {

    private final Main main;

    public BroadcastMessages(Main main) {
        this.main = main;
        initBroadcastMessages();
    }

    private void initBroadcastMessages() {
        int timeInSeconds = ConfigManager.getTimeBetweenBroadcastMessage();
        long timeInTicks = 20L * timeInSeconds;
        List<String> messages = ConfigManager.getBroadcastMessages();
        String serverName = ConfigManager.getServerName();
        Bukkit.getServer().getScheduler().runTaskTimer(main, () -> {
            String msg = messages.get( (int) Math.floor(Math.random() * messages.size()));
            this.main.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', serverName) + ": "+ ChatColor.translateAlternateColorCodes('&', msg));
        }, 0, timeInTicks);
    }
}
