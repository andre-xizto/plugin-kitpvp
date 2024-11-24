package dev.buskopan.kitPvP;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import dev.buskopan.kitPvP.auth.Join;
import dev.buskopan.kitPvP.auth.Leave;
import dev.buskopan.kitPvP.broadcast.BroadcastMessages;
import dev.buskopan.kitPvP.config.ConfigManager;
import dev.buskopan.kitPvP.data.player.PlayerDataManager;
import dev.buskopan.kitPvP.data.player.PlayerStats;
import dev.buskopan.kitPvP.data.punishes.PunishDataManager;
import dev.buskopan.kitPvP.lib.PlayersListInfo;
import dev.buskopan.kitPvP.tag.SetarGrupoCommand;
import dev.buskopan.kitPvP.tag.TagManager;
import dev.buskopan.kitPvP.utils.commands.PingCommand;
import dev.buskopan.kitPvP.utils.events.DefaultEvents;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Main extends JavaPlugin {

    private PlayerDataManager playerDataManager;
    private PunishDataManager punishDataManager;
    private PlayerStats playerStats;
    private ProtocolManager protocolManager;
    private TagManager tagManager;

    @Override
    public void onEnable() {

        protocolManager = ProtocolLibrary.getProtocolManager();
        tagManager = new TagManager(this);
        new PlayersListInfo(this).listPlayers();


        playerDataManager = new PlayerDataManager();
        playerStats = new PlayerStats(playerDataManager,this);
        ConfigManager.loadConfig(this);
        new BroadcastMessages(this);
        try {
            registerEvents();
        } catch (Exception e) {
            this.getServer().shutdown();
        }

        try {
            registerCommands();
        } catch (Exception e) {
            this.getServer().shutdown();
        }

        this.getServer().setMotd(ChatColor.translateAlternateColorCodes('&', ConfigManager.getMotd()));

        ConsoleCommandSender console = getServer().getConsoleSender();
        console.sendMessage("§a§m------------------------------");
        console.sendMessage("§a Plugin Iniciado Com Sucesso ");
        console.sendMessage("§a Plugin  feito  Por    §3§lBuskopan !  ");
        console.sendMessage("§a§m------------------------------");

    }

    private void registerCommands() {
        Objects.requireNonNull(getCommand("ping")).setExecutor(new PingCommand());
        Objects.requireNonNull(getCommand("setargrupo")).setExecutor(new SetarGrupoCommand(this));
    }

    private void registerEvents() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new Join(this, playerStats), this);
        pm.registerEvents(new Leave(), this);
        pm.registerEvents(new DefaultEvents(this), this);
    }

    public ProtocolManager getProtocolManager() {
        return protocolManager;
    }

    public TagManager getTagManager() {
        return tagManager;
    }

    public PunishDataManager getPunishDataManager() {
        return punishDataManager;
    }

    @Override
    public void onDisable() {
        ConsoleCommandSender console = getServer().getConsoleSender();
        console.sendMessage("§c§m------------------------------");
        console.sendMessage("§c Plugin Desabilitado Com Sucesso ");
        console.sendMessage("§c Plugin  feito  Por    §3§lBuskopan !  ");
        console.sendMessage("§c§m------------------------------");
    }
}
