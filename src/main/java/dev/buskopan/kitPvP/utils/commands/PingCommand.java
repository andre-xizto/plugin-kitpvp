package dev.buskopan.kitPvP.utils.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PingCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player p) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Seu ping é de: &a&l" + p.getPing() + "ms &7!"));
            return true;
        }

        ConsoleCommandSender consoleSender = (ConsoleCommandSender) commandSender;
        consoleSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cApenas jogadores podem executar este comando!"));
        return false;
    }
}
