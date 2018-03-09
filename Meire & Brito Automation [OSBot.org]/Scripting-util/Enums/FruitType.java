package org.chris.script.sorcgarden.data;

public enum FruitType {

    Winter(5),
    Spring(4),
    Autumn(3),
    Summer(2);

    private int getAmount;

    FruitType(int amountPerGlass) {
        this.getAmount = amountPerGlass;
    }

    @Override
    public String toString() {
        return super.toString() + " sq'irk";
    }

    public int getGetAmount() {
        return getAmount;
    }

}
