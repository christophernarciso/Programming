package com.excellentscripts.api.util.enums;

public enum Tanner{

    LEATHER("Cowhide", 324, 148, "Leather"),
    HARD_LEATHER("Cowhide", 324, 149, "Hard leather"),
    GREEN_LEATHER("Green dragonhide", 324, 152, "Green dragon leather"),
    RED_LEATHER("Red dragonhide", 324, 154, "Red dragon leather"),
    BLUE_LEATHER("Blue dragonhide", 324, 153, "Blue dragon leather"),
    BLACK_LEATHER("Black dragonhide", 324, 107, "Black dragon leather");

    //(324, 148) leather, (324, 149) hard leather, (324, 152) green, (324, 153) blue, (324, 154) red, (324, 107) black
    private int parent, child;
    private String supply, finishedProduct;

    Tanner(String supplies, int parent, int child, String finishedProduct) {
        this.supply = supplies;
        this.parent = parent;
        this.child = child;
        this.finishedProduct = finishedProduct;
    }

    public int getParent() {
        return parent;
    }

    public int getChild() {
        return child;
    }

    public String getSupply() {
        return supply;
    }

    public String getFinishedProduct() {
        return finishedProduct;
    }
}