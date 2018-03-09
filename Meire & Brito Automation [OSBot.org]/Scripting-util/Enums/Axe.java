package com.excellentscripts.api.util.enums;

public enum Axe {
    BRONZE("Bronze"),
    IRON("Iron"),
    STEEL("Steel"),
    BLACK("Black"),
    MITHRIL("Mithril"),
    ADAMANT("Adamant"),
    RUNE("Rune"),
    DRAGON("Dragon");

    String name;

    private Axe(String name) {
        this.name = name;
    }

    public String getName() {
        return name + " axe";
    }
}
