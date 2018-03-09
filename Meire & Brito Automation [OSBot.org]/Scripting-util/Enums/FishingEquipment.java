package com.excellentscripts.api.util.enums;

public enum FishingEquipment {

    SMALL_NET("Small fishing net"),
    LARGE_NET("Big fishing net"),
    FISHING_ROD("Fishing rod"),
    FLY_FISHING_ROD("Fly fishing rod"),
    FISHING_BAIT("Fishing bait"),
    FEATHERS("Feather"),
    LOBSTER_POT("Lobster pot"),
    HARPOON("Harpoon"),
    BARBARIAN_ROD("Barbarian rod");

    String name;

    private FishingEquipment(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
