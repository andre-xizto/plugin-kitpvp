package dev.buskopan.kitPvP.tag;

import dev.buskopan.kitPvP.Main;
import dev.buskopan.kitPvP.utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetarGrupoCommand implements CommandExecutor {

    private final Main main;

    public SetarGrupoCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player p) {
            if (p.hasPermission("kitpvp.settag")) {
                if (strings.length != 2) {
                    p.sendMessage(StringUtils.colorize("&cUse: /setargrupo <jogador> <grupo>"));
                    return false;
                }
                TagManager tagManager = main.getTagManager();
                Player player = Bukkit.getPlayer(strings[0]);
                String grupo = strings[1];

                switch (grupo) {
                    case "jogador":
                        tagManager.setTag(player, TagType.JOGADOR);
                        break;
                    case "admin":
                        tagManager.setTag(player, TagType.ADMIN);
                        break;
                    case "mod":
                        tagManager.setTag(player, TagType.MOD);
                        break;
                    case "vip":
                        tagManager.setTag(player, TagType.VIP);
                        break;
                    case "dono":
                        if (player.isOp()) tagManager.setTag(player, TagType.DONO);
                        break;
                    default:
                        p.sendMessage(StringUtils.colorize("&cGrupo inválido!"));
                }

            }
        }
        return true;
    }
}
