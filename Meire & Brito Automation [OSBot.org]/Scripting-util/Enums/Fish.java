package com.excellentscripts.api.util.enums;

import java.util.ArrayList;

public enum Fish {
    SHRIMP("Shrimp", 1, FishingType.NET_BAIT, "Net", FishingEquipment.SMALL_NET),
    ANCHOVIES("Anchovies", 1, FishingType.NET_BAIT, "Net", FishingEquipment.SMALL_NET),
    SARDINE("Sardine", 5, FishingType.NET_BAIT, "Bait", FishingEquipment.FISHING_ROD, FishingEquipment.FISHING_BAIT),
    HERRING("Herring", 10, FishingType.NET_BAIT, "Bait", FishingEquipment.FISHING_ROD, FishingEquipment.FISHING_BAIT),
    TROUT("Trout", 15, FishingType.LURE_BAIT, "Lure", FishingEquipment.FLY_FISHING_ROD, FishingEquipment.FEATHERS),
    SALMON("Salmon", 25, FishingType.LURE_BAIT, "Lure", FishingEquipment.FLY_FISHING_ROD, FishingEquipment.FEATHERS),
    LEAPING_TROUT("Leaping trout", 48, FishingType.USE_ROD, "Use-rod", FishingEquipment.BARBARIAN_ROD, FishingEquipment.FEATHERS),
    LEAPING_SALMON("Leaping salmon", 58, FishingType.USE_ROD, "Use-rod", FishingEquipment.BARBARIAN_ROD, FishingEquipment.FEATHERS),
    LEAPING_STURGEON("Leaping sturgeon", 70, FishingType.USE_ROD, "Use-rod", FishingEquipment.BARBARIAN_ROD, FishingEquipment.FEATHERS),
    TUNA("Tuna", 30, FishingType.CAGE_HARPOON, "Harpoon", FishingEquipment.HARPOON),
    //BAREHAND_TUNA("Tuna (Barehand)", 55, FishingType.CAGE_HARPOON, "Harpoon", FishingEquipment.HARPOON),
    LOBSTER("Lobster", 40, FishingType.CAGE_HARPOON, "Cage", FishingEquipment.LOBSTER_POT),
    BASS("Bass", 43, FishingType.NET_HARPOON, "Net", FishingEquipment.LARGE_NET),
    SWORDFISH("Swordfish", 45, FishingType.CAGE_HARPOON, "Harpoon", FishingEquipment.HARPOON),
    //BAREHAND_SWORDFISH("Swordfish (Barehand)", 70, FishingType.CAGE_HARPOON, "Harpoon", null),
    MONKFISH("Monkfish", 62, FishingType.NET_BAIT, "Harpoon", FishingEquipment.HARPOON),
    SHARK("Shark", 80, FishingType.NET_BAIT, "Harpoon", FishingEquipment.HARPOON),
    //BAREHAND_SHARK("Shark (Barehand)", 96, FishingType.NET_BAIT, "Harpoon", null),
    MANTA_RAY("Manta ray", 91, FishingType.NET_BAIT, "Harpoon", FishingEquipment.HARPOON);

    private final FishingEquipment[] equipment;
    private final FishingType fishingType;

    private final String option;

    private String name;
    private int level;

    Fish(String name, int level, FishingType fishingType, String option, FishingEquipment... equipment) {
        this.name = name;
        this.level = level;
        this.equipment = equipment;
        this.fishingType = fishingType;
        this.option = option;
    }

    public String getName() {
        return name;
    }

    public String getRawName() {
        return "Raw " + name.substring(0, 1).toLowerCase() + name.substring(1, name.length() - 1);
    }

    public int getLevel() {
        return level;
    }

    public ArrayList<String> getEquipment() {
        ArrayList<String> equipment = new ArrayList<>();
        for (FishingEquipment e : this.equipment)
            equipment.add(e.getName());
        return equipment;
    }

    public FishingType getFishingType() {
        return fishingType;
    }

    public String getOption() {
        return option;
    }

    @Override
    public String toString() {
        return name;
    }
}
