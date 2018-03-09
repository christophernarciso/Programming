package org.base.scripting.data;

/**
 * Created by Sinatra_PC on 07/17/2016.
 */

public enum SmithingBar {
    SELECT_CRAFTABLE(null, 0),
    AXE("Axe", 1),
    ARROW_TIPS("Arrowtips", 1),
    BOLTS("Bolts", 1),
    BATTLE_AXE("Battle axe", 3),
    BRONZE_WIRE("Bronze wire", 1),
    CHAIN_BODY("Chain body", 3),
    CLAWS("Claws", 2),
    DAGGER("Dagger", 1),
    DART_TIPS("Dart tips", 1),
    FULL_HELM("Full helm", 2),
    JAVELIN_HEAD("Javelin heads", 1),
    KNIVES("Knives", 1),
    LONG_SWORD("Long sword", 2),
    LIMBS("Limbs", 1),
    MEDIUM_HELM("Medium helm", 1),
    MACE("Mace", 1),
    PLATELEGS("Plate legs", 3),
    PLATE_SKIRT("Plate skirt", 3),
    KITE_SHIELD("Kite shield", 3),
    PLATE_BODY("Plate body", 5),
    SQUARE_SHIELD("Square shield", 2),
    SWORD("Sword", 1),
    SCIMITAR("Scimitar", 2),
    WARHAMMER("Warhammer", 3),
    TWO_HAND_SWORD("2-hand sword", 3),
    NAILS("Nails", 1)
    ;

    private String widgetext;
    private int barCost;

    SmithingBar(String widgetText, int barCost){
        this.widgetext = widgetText;
        this.barCost = barCost;
    }

    public String getWidgetext() {
        return widgetext;
    }

    public int getBarCost() {
        return barCost;
    }
}
