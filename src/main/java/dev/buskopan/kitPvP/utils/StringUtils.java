package dev.buskopan.kitPvP.utils;

import org.bukkit.ChatColor;

public class StringUtils {

    public static String colorize(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
