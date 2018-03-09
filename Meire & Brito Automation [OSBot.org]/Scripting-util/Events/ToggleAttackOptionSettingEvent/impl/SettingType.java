package com.excellentscripts.api.util.enums;

public enum SettingType {
    PLAYER(1107), NPC(1306);
    private int configValue;

    SettingType(int configValue) {
        this.configValue = configValue;
    }

    public int getConfigValue() {
        return configValue;
    }

}