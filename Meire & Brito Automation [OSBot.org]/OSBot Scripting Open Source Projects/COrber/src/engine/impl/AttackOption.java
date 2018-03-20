package org.script.orb.engine.impl;

public enum AttackOption {
    DEPENDS_ON_COMBAT_LEVEL("Depends on combat levels"),
    ALWAYS_RIGHT_CLICK("Always right-click"),
    LEFT_CLICK_WHERE_AVAILABLE("Left-click where available"),
    HIDDEN("Hidden");

    private String option;

    AttackOption(String option) {
        this.option = option;
    }

    public String getOption() {
        return option;
    }
}