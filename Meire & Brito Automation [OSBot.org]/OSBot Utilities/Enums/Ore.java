package com.excellentscripts.api.util.enums;

public enum Ore {
    /*
    2491: // rune essence
    2109: // clay rock
    2091: // copper rock
    2095: // tin rock
    2093: // iron rock
    2101: // silver rock
    2097: // coal rock
    2099: // gold rock
    2103: // mithril rock
    2105: // adamant rock
    2107: // rune roc
     */
    RUNE_ESSENCE("Rune essence", 2491, 1),
    PURE_ESSENCE("Pure essence", 2491, 1),
    CLAY("Clay", 2109, 1),
    COPPER("Copper", 2091, 1),
    TIN("Tin", 2095, 1),
    IRON("Iron", 2093, 15),
    SILVER("Silver", 2101, 20),
    COAL("Coal", 2097, 30),
    GOLD("Gold", 2099, 40),
    MITHRIL("Mithril", 2103, 55),
    ADAMANT("Adamant", 2105, 70),
    RUNITE("Runite", 2107, 85);

    String name;
    int rockId;
    int level;

    Ore(String name, int rockId, int level) {
        this.name = name;
        this.rockId = rockId;
        this.level = level;
    }

    public String getName() {
        return name + " ore";
    }

    public int getRockId() {
        return rockId;
    }

    public int getLevel() {
        return level;
    }
}
