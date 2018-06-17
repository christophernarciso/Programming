package com.excellentscripts.api.util.enums;

public enum Equipment {
    BRONZE("Bronze", 1),
    IRON("Iron", 1),
    STEEL("Steel", 5),
    BLACK("Black", 10),
    MITHRIL("Mithril", 20),
    ADAMANT("Adamant", 30),
    RUNE("Rune", 40),
    DRAGON("Dragon", 60);

    private String name;
    private int level;

    Equipment(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public String getFullHelm() {
        return name + " full helm";
    }

    public String getMedHelm() {
        return name + " med helm";
    }

    public String getPlatebody() {
        return name + " platebody";
    }

    public String getChainbody() {
        return name + " chainbody";
    }

    public String getPlatelegs() {
        return name + " platelegs";
    }

    public String getBoots() {
        return name + " boots";
    }

    public String getKiteshield() {
        return name + " kiteshield";
    }

    public String getShield() {
        return name + " sq shield";
    }

    public String getAxe() {
        return name + " axe";
    }

    public String getPickaxe() {
        return name + " pickaxe";
    }

}
