package dev.buskopan.kitPvP.data.punishes;

import dev.buskopan.kitPvP.utils.StringUtils;
import org.bukkit.entity.Player;

import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class PunishData {
    private final PunishDataManager manager;

    public PunishData(PunishDataManager manager) {
        this.manager = manager;
    }

    public void banPlayer(Player player, Player target, String reason) {
        target.ban(reason, (Date) null, player.getName());
        target.kickPlayer(StringUtils.colorize("&cVocê foi banido por "+ reason));

    }
    
}
