package dev.buskopan.kitPvP.tag;

import dev.buskopan.kitPvP.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class TagManager {

    private final Main main;

    public TagManager(Main main) {
        this.main = main;
    }

    public void setup(Player player) {
        // Cria a scoreboard nova
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

        // Registra as equipes e suas tags
        for (TagType tagType : TagType.values()) {
            Team tag = scoreboard.getTeam(tagType.getTeam());
            if (tag == null) {
                tag = scoreboard.registerNewTeam(tagType.getTeam());
                tag.setPrefix(tagType.getPrefix());
                tag.setColor(ChatColor.GRAY);
            }
        }

        // Adiciona a entrada de 'player' no time "99_JOGADOR"
        Team tag99 = scoreboard.getTeam("99_JOGADOR");
        if (tag99 != null) {
            tag99.addEntry(player.getName());
        }


        // Aplica as tags aos jogadores online
        for (Player player1 : Bukkit.getOnlinePlayers()) {
            if (!player1.getUniqueId().equals(player.getUniqueId())) {
                Scoreboard otherPlayerScoreboard = player1.getScoreboard();

                // Garantir que a equipe de player1 existe
                Team otherPlayerTeam = otherPlayerScoreboard.getEntryTeam(player1.getName());
                if (otherPlayerTeam != null) {
                    scoreboard.getTeam(otherPlayerTeam.getName()).addEntry(player1.getName());
                }

                // Garantir que a equipe do 'player' existe
                Team playerTeam = scoreboard.getEntryTeam(player.getName());
                if (playerTeam != null) {
                    otherPlayerScoreboard.getTeam(playerTeam.getName()).addEntry(player.getName());
                }
            }
        }

        // Define a scoreboard do jogador
        player.setScoreboard(scoreboard);

        // Exibe as equipes para depuração
        System.out.println(player.getScoreboard().getTeams());
    }


    public void setTag(Player player, TagType tag) {
        Scoreboard scoreboard = player.getScoreboard();
        Team tagTeam = scoreboard.getTeam(tag.getTeam());
        if (tagTeam == null) {
            return;
        }
        scoreboard.getTeam(tag.getTeam()).addEntry(player.getName());

        for (Player player1 : Bukkit.getOnlinePlayers()) {
            if (!player1.getUniqueId().equals(player.getUniqueId())) {
                Scoreboard otherPlayerScoreboard = player1.getScoreboard();
                otherPlayerScoreboard.getTeam(tagTeam.getName()).addEntry(player.getName());
            }
        }
    }

}
