package com.excellentscripts.api.util.enums;

import org.osbot.rs07.api.ui.Skill;

public enum PotionType {
    ATTACK("attack", Skill.ATTACK),
    STRENGTH("strength", Skill.STRENGTH),
    DEFENCE("defence", Skill.DEFENCE),
    MAGIC("magic", Skill.MAGIC),
    RANGING("ranging", Skill.RANGED),
    OVERLOAD("overload", Skill.ATTACK, Skill.STRENGTH, Skill.DEFENCE, Skill.RANGED, Skill.MAGIC),
    ANTIPOISON("antipoison"),
    PRAYER("prayer", Skill.PRAYER);
    
    private final String text;
    private final Skill[] boostedSkills;

    PotionType(String text, Skill... boostedSkills) {
        this.text = text;
        this.boostedSkills = boostedSkills;
    }

    public String getText() {
        return this.text;
    }

    public Skill[] getBoostedSkills() {
        return this.boostedSkills;
    }
}

