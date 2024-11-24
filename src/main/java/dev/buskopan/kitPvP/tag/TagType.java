package dev.buskopan.kitPvP.tag;

public enum TagType {
    DONO("00_DONO", "§4DONO "),
    ADMIN("11_ADMIN", "§cADMIN"),
    MOD("22_MOD", "§5MOD "),
    VIP("33_VIP", "§aVIP "),
    JOGADOR("99_JOGADOR", "");

    final String team;
    final String prefix;

    TagType(String team, String prefix) {
        this.team = team;
        this.prefix = prefix;
    }

    public String getTeam() {
        return team;
    }

    public String getPrefix() {
        return prefix;
    }
}
