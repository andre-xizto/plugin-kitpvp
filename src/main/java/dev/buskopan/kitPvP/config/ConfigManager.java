package dev.buskopan.kitPvP.config;

import dev.buskopan.kitPvP.Main;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class ConfigManager {

    private static FileConfiguration config;

    public static void loadConfig(Main main) {
        ConfigManager.config = main.getConfig();
        main.saveDefaultConfig();
    }

    public static String getServerName() {
        return config.getString("nome-servidor");
    }

    public static String getJoinMessage(String playerName) {
        StringBuilder sb = new StringBuilder();
        List<String> messages = config.getStringList("mensagem-ao-entrar");
        for (String message : messages) {
            String newMessage = message.replace("%player%", playerName);
            sb.append(newMessage).append("\n");
        }
        return sb.toString();
    }

    public static List<String> getPlayerInfoListMessage() {
        return config.getStringList("mensagem-na-lista");
    }

    public static List<String> getBroadcastMessages() {
        return config.getStringList("mensagens-aviso");
    }

    public static Integer getTimeBetweenBroadcastMessage() {
        return config.getInt("tempo-entre-avisos");
    }

    public static String getMotd() {
        StringBuilder sb = new StringBuilder();
        String line1 = config.getString("motd-linha1");
        String line2 = config.getString("motd-linha2");

        return sb.append(line1).append("\n").append(line2).toString();
    }

    public static String getServerSite() {
        return config.getString("site");
    }

    public static String getHeaderTabList() {
        StringBuilder sb = new StringBuilder();
        String line1 = config.getString("tab-cabecalho-linha1");
        String line2 = config.getString("tab-cabecalho-linha2");
        return sb.append("\n").append(line1).append("\n").append("\n").append(line2).append("\n").toString();
    }

    public static String getFooterTabList() {
        StringBuilder sb = new StringBuilder();
        String line1 = config.getString("tab-rodape-linha1");
        String line2 = config.getString("tab-rodape-linha2");
        return sb.append("\n").append(line1).append("\n").append("\n").append(line2).append("\n").toString();
    }

}
