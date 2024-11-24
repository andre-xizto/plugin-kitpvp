package dev.buskopan.kitPvP.tablist;

import dev.buskopan.kitPvP.config.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class TabList{

    public static void updateTabList(Player p) {
        p.setPlayerListHeaderFooter(formatString(ConfigManager.getHeaderTabList(), p), formatString(ConfigManager.getFooterTabList(), p));
    }

    private static String formatString(String str, Player p) {
        return ChatColor.translateAlternateColorCodes('&', str
                .replace("%server%", ConfigManager.getServerName())
                .replace("%site%", ConfigManager.getServerSite())
                .replace("%count%", String.valueOf(Bukkit.getOnlinePlayers().size()))
                .replace("%ping%", String.valueOf(p.getPing())));
    }
}
