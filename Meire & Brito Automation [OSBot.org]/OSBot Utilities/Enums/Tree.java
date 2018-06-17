package com.excellentscripts.api.util.enums;

public enum Tree {
    REGULAR("Tree"),
    DEAD("Dead tree"),
    EVERGREEN("Evergreen"),
    ACHEY("Achey tree"),
    OAK("Oak"),
    WILLOW("Willow"),
    MAPLE("Maple"),
    TEAK("Teak"),
    YEW("Yew"),
    MAGIC("Magic");
    private String name;

    Tree(String name) {
        this.name = name;
    }

    String format(String str) {
        char[] stringArray = str.trim().toCharArray();
        stringArray[0] = Character.toUpperCase(stringArray[0]);
        return new String(stringArray);
    }

    String getName() {
        return name;
    }

    public String getTreeName() {
        return this == MAPLE ? this.getName() + " tree" : this.getName();
    }

    public String getLogName() {
        return this == REGULAR || this == DEAD || this == EVERGREEN ? "Logs" : this.getName() + " logs";
    }

    public String getShortbowName(boolean strung) {
        return format(getName() + " shortbow" + (strung ? " (u)" : ""));
    }

    public String getLongbowName(boolean strung) {
        return format(getName() + " longbow" + (strung ? " (u)" : ""));
    }
}

