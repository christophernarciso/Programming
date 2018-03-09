package com.excellentscripts.api.util.enums;

public enum Minigame {
    BARBARIAN_ASSAULT("Barbarian Assault"),
    BLAST_FURNACE("Blast Furnace"),
    BURTHORPE_GAMES_ROOM("Burhorpe Games Room"),
    CASTLE_WARS("Castle Wars"),
    CLAN_WARS("Clan Wars"),
    DAGGANNOTH_KINGS("Dagannoth Kings"),
    FISHING_TRAWLER("Fishing Trawler"),
    GOD_WARS("God Wars"),
    LAST_MAN_STANDING("Last Man Standing"),
    NIGHTMARE_ZONE("Nightmare Zone"),
    PEST_CONTROL("Pest Control"),
    PLAYER_OWNED_HOUSES("Player Owned Houses"),
    RAT_PITS("Rat Pits"),
    SHADES_OF_MORTTON("Shades of Mort'ton"),
    SHIELD_OF_ARRAV("Shield of Arrav"),
    TITHE_FARM("Tithe Farm"),
    TROUBLE_BREWING("Trouble Brewing"),
    TZHAAR_FIGHT_PIT("TzHaar Fight Pit"),
    ;

    private final String text;

    Minigame(final String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}