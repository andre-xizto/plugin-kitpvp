package dev.buskopan.kitPvP.data.player;

import dev.buskopan.kitPvP.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerStats {

    private final PlayerDataManager playerData;
    private final Main main;

    public PlayerStats(PlayerDataManager playerDataManager, Main main) {
        this.playerData = playerDataManager;
        this.main = main;
    }

    public void registerPlayer(Player player) {
        Bukkit.getScheduler().runTaskAsynchronously(this.main, () -> {
            String uuid = player.getUniqueId().toString();
            if (!playerData.getConfig().contains(uuid)) {
                playerData.getConfig().set(uuid + ".kills", 0);
                playerData.getConfig().set(uuid + ".deaths", 0);
                playerData.getConfig().set(uuid + ".killstreak", 0);
                playerData.getConfig().set(uuid + ".coins", 0);
                playerData.getConfig().set(uuid + ".cash", 0);
                playerData.getConfig().set(uuid + ".kits", List.of());
                playerData.getConfig().set(uuid + ".tags", List.of());
                playerData.saveConfig();
            }
        });
    }

    public void playerKills(Player player) {
        Bukkit.getScheduler().runTaskAsynchronously(main, () -> {
            String uuid = player.getUniqueId().toString();
            if (playerData.getConfig().contains(uuid)) {
                int kills = getKills(player);
                playerData.getConfig().set(uuid + ".kills", kills + 1);
            }
            playerData.saveConfig();
        });
    }
    public void playerKilled(Player player) {
        Bukkit.getScheduler().runTaskAsynchronously(main, () -> {
            String uuid = player.getUniqueId().toString();
            if (playerData.getConfig().contains(uuid)) {
                int deaths = getDeaths(player);
                playerData.getConfig().set(uuid + ".deaths", deaths + 1);
                setKillStreak(player, 0);
            }
            playerData.saveConfig();
        });
    }

    public void setKillStreak(Player player, int value) {
        Bukkit.getScheduler().runTaskAsynchronously(main, () -> {
            String uuid = player.getUniqueId().toString();
            if (playerData.getConfig().contains(uuid)) {
                playerData.getConfig().set(uuid + ".killstreak", value);
            }
            playerData.saveConfig();
        });
    }

    public void setCoins(Player player, int value) {
        Bukkit.getScheduler().runTaskAsynchronously(main, () -> {
            String uuid = player.getUniqueId().toString();
            if (playerData.getConfig().contains(uuid)) {
                playerData.getConfig().set(uuid + ".coins", value);
            }
            playerData.saveConfig();
        });
    }

    public void setCash(Player player, int value) {
        Bukkit.getScheduler().runTaskAsynchronously(main, () -> {
            String uuid = player.getUniqueId().toString();
            if (playerData.getConfig().contains(uuid)) {
                playerData.getConfig().set(uuid + ".cash", value);
            }
            playerData.saveConfig();
        });
    }

    public void setKits(Player player, String kit) {
        Bukkit.getScheduler().runTaskAsynchronously(main, () -> {
            String uuid = player.getUniqueId().toString();
            if (playerData.getConfig().contains(uuid)) {
                List<String> kits = getKits(player);
                playerData.getConfig().set(uuid + ".kits", kits.add(kit));
            }
            playerData.saveConfig();
        });
    }

    public void setTags(Player player, String tag) {
        Bukkit.getScheduler().runTaskAsynchronously(main, () -> {
            String uuid = player.getUniqueId().toString();
            if (playerData.getConfig().contains(uuid)) {
                List<String> tags = getTags(player);
                playerData.getConfig().set(uuid + ".tags", tags.add(tag));
            }
            playerData.saveConfig();
        });
    }

    public int getKills(Player player) {
        String uuid = player.getUniqueId().toString();
        if (playerData.getConfig().contains(uuid)) {
            return playerData.getConfig().getInt(uuid + ".kills");
        }
        return 0;
    }

    public int getKillStreak(Player player) {
        String uuid = player.getUniqueId().toString();
        if (playerData.getConfig().contains(uuid)) {
            return playerData.getConfig().getInt(uuid + ".killstreak");
        }
        return 0;
    }

    public int getDeaths(Player player) {
        String uuid = player.getUniqueId().toString();
        if (playerData.getConfig().contains(uuid)) {
            return playerData.getConfig().getInt(uuid + ".deaths");
        }
        return 0;
    }

    public List<String> getKits(Player player) {
        String uuid = player.getUniqueId().toString();
        if (playerData.getConfig().contains(uuid)) {
            return playerData.getConfig().getStringList(uuid + ".kits");
        }
        return new ArrayList<>();
    }

    public List<String> getTags(Player player) {
        String uuid = player.getUniqueId().toString();
        if (playerData.getConfig().contains(uuid)) {
            return playerData.getConfig().getStringList(uuid + ".tags");
        }
        return new ArrayList<>();
    }

    public int getCoins(Player player) {
        String uuid = player.getUniqueId().toString();
        if (playerData.getConfig().contains(uuid)) {
            return playerData.getConfig().getInt(uuid + ".coins");
        }
        return 0;
    }

    public int getCash(Player player) {
        String uuid = player.getUniqueId().toString();
        if (playerData.getConfig().contains(uuid)) {
            return playerData.getConfig().getInt(uuid + ".cash");
        }
        return 0;
    }
}
