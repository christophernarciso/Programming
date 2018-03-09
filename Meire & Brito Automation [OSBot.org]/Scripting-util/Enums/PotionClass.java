package com.excellentscripts.api.util.enums;

public enum PotionClass {
    REGULAR(""),
    SUPER("super"),
    EXTREME("extreme"),
    OVERLOAD("overload");
    
    private final String text;

    private PotionClass(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}

