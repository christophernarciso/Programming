package org.chris.util;

/**
 * Created by Chris on 12/27/2017.
 */
public enum Targets {

    Attack("Attack", 3),
    Defence("Defence", 5),
    Strength("Strength", 7),
    Hitpoints("Hitpoints", 9),
    Range("Range", 11),
    Prayer("Prayer", 13),
    Magic("Magic", 15),
    Cooking("Cooking", 17),
    Woodcutting("Woodcutting", 19),
    Fletching("Fletching", 21),
    Fishing("Fishing", 23),
    Firemaking("Firemaking", 25),
    Crafting("Crafting", 27),
    Smithing("Smithing", 29),
    Mining("Mining", 31),
    Herblore("Herblore", 33),
    Agility("Agility", 35),
    Theiving("Theiving", 37),
    Slayer("Slayer", 39),
    Farming("Farming", 41),
    Runecrafting("Runecrafting", 43),
    Hunter("Hunter", 45),
    Construction("Construction", 47)

    ;


    private final String name;
    private final int searchValue;

    Targets(final String name, final int searchValue) {
        this.name = name;
        this.searchValue = searchValue;
    }

    public String getName() {
        return name;
    }

    public int getSearchValue() {
        return searchValue;
    }
}
