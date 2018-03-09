package com.excellentscripts.api.util.enums;

public enum FishingType {
    NET_BAIT("Net", "Bait"),
    LURE_BAIT("Lure", "Bait"),
    CAGE_HARPOON("Cage", "Harpoon"),
    NET_HARPOON("Net", "Harpoon"),
    HARPOON_NET("Harpoon", "Net"),
    USE_ROD("Use-rod");

    private final String[] options;

    FishingType(String... options) {
        this.options = options;
    }

    public String[] getOptions() {
        return options;
    }
}
