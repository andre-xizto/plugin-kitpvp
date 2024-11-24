package dev.buskopan.kitPvP.utils.events;

import dev.buskopan.kitPvP.Main;
import io.papermc.paper.event.player.PlayerPickItemEvent;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.server.ServerEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.event.world.TimeSkipEvent;

public class DefaultEvents implements Listener {

    private final String[] blockedCommands = new String[]{
            "/pl",
            "/plugin",
            "/say",
            "/me"
    };
    private final Main main;

    public DefaultEvents(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onHunger(FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onExplosive(EntityExplodeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onHitByNonPlayer(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onTabCommand(PlayerCommandPreprocessEvent e) {
        String command = e.getMessage().split(" ")[0];
        Player p = e.getPlayer();

        for (String blockedCommand : blockedCommands) {
            if (command.startsWith(blockedCommand)) {
                e.setCancelled(true);
                p.sendMessage("§cVocê não tem permissão para isto!");
                break;
            }
        }
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onPickupItem(PlayerPickItemEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onItemSpawn(ItemSpawnEvent e) {
        e.getEntity().remove();
        e.getLocation().getWorld().playEffect(e.getEntity().getLocation(), Effect.SMOKE, 10);
    }

}
