package com.excellentscripts.api.util.enums;

import org.osbot.rs07.api.filter.Filter;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.script.MethodProvider;

public enum Potion {
    ATTACK("Attack Potion", PotionClass.REGULAR, PotionType.ATTACK),
    SUPER_ATTACK("Super Attack Potion", PotionClass.SUPER, PotionType.ATTACK),
    EXTREME_ATTACK("Extreme Attack Potion", PotionClass.EXTREME, PotionType.ATTACK),
    STRENGTH("Strength Potion", PotionClass.REGULAR, PotionType.STRENGTH),
    SUPER_STRENGTH("Super Strength Potion", PotionClass.SUPER, PotionType.STRENGTH),
    EXTREME_STRENGTH("Extreme Strength Potion", PotionClass.EXTREME, PotionType.STRENGTH),
    DEFENCE("Defence Potion", PotionClass.REGULAR, PotionType.DEFENCE),
    SUPER_DEFENCE("Super Defence Potion", PotionClass.SUPER, PotionType.DEFENCE),
    EXTREME_DEFENCE("Extreme Defence Potion", PotionClass.EXTREME, PotionType.DEFENCE),
    RANGING("Ranging Potion", PotionClass.REGULAR, PotionType.RANGING),
    SUPER_RANGING("Super Ranging Potion", PotionClass.SUPER, PotionType.RANGING),
    EXTREME_RANGING("Extreme Ranging Potion", PotionClass.EXTREME, PotionType.RANGING),
    MAGIC("Magic Potion", PotionClass.REGULAR, PotionType.MAGIC),
    SUPER_MAGIC("Super Magic Potion", PotionClass.SUPER, PotionType.MAGIC),
    EXTREME_MAGIC("Extreme Magic Potion", PotionClass.EXTREME, PotionType.MAGIC),
    ANTIPOISON("Antipoison", PotionClass.REGULAR, PotionType.ANTIPOISON),
    PRAYER("Prayer Potion", PotionClass.REGULAR, PotionType.PRAYER);
    
    private final String name;
    private final PotionClass potionClass;
    private final PotionType potionType;

    Potion(String name, PotionClass potionClass, PotionType potionType) {
        this.name = name;
        this.potionClass = potionClass;
        this.potionType = potionType;
    }

    public PotionClass getPotionClass() {
        return this.potionClass;
    }

    public String getName() {
        return this.name;
    }

    public PotionType getPotionType() {
        return this.potionType;
    }

    public boolean hasItem(MethodProvider context) {
        return this.getItem(context) != null;
    }

    public Item getItem(MethodProvider context) {
        Filter<Item> itemFilter = item -> {
            String name = item.getName();
            name = name.toLowerCase();
            return Potion.this.potionClass.equals(PotionClass.REGULAR) ? name.startsWith(Potion.this.potionType.getText())
                    : name.startsWith(Potion.this.potionClass.getText()) && name.contains(Potion.this.potionType.getText());
        };
        Item item = context.getInventory().getItem(itemFilter);
        return item;
    }

    public static String getAction() {
        return "Drink";
    }

}

