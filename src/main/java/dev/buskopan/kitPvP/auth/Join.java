package dev.buskopan.kitPvP.auth;

import dev.buskopan.kitPvP.Main;
import dev.buskopan.kitPvP.config.ConfigManager;
import dev.buskopan.kitPvP.data.player.PlayerStats;
import dev.buskopan.kitPvP.tablist.TabList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.UUID;

public class Join implements Listener {

    private final Main main;

    private final HashMap<UUID, Integer> tasks = new HashMap<>();

    private final PlayerStats playerStats;

    public Join(Main main, PlayerStats playerStats) {
        this.main = main;
        this.playerStats = playerStats;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        playerStats.registerPlayer(player);
        main.getTagManager().setup(player);
        createTab(player);
        event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', "&7[&a+&7] " + player.getName()));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigManager.getJoinMessage(player.getName())));
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
        player.sendTitle(ChatColor.translateAlternateColorCodes('&', ConfigManager.getServerName()), "§aSeja bem-vindo(a)!");
    }

    @EventHandler
    public void onTryAccess(PlayerLoginEvent event) {
        Player player = event.getPlayer();

    }

    private void createTab(Player player) {
        int taskId = Bukkit.getServer().getScheduler().runTaskTimer(main, () -> {
            if (player.isOnline()) {
                TabList.updateTabList(player);
            }
        }, 20L, 100L).getTaskId();
        tasks.put(player.getUniqueId(), taskId);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        int taskId = tasks.get(player.getUniqueId());
        Bukkit.getScheduler().cancelTask(taskId);
        tasks.remove(player.getUniqueId());
    }
}
