package com.excellentscripts.api.util.enums;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;

public enum Altars {

    AIR_ALTAR("Pure essence", "Air rune", new Position(2986, 3294,0), new Position(3013, 3356, 0), new Area(2839, 4840, 2849, 4827), new Area(3009, 3358, 3021, 3353), new Area(2970, 3379, 3022, 3275)),
    MIND_ALTAR("Pure essence", "Mind rune", new Position(2980, 3513, 0), new Position(2945, 3370, 0), new Area(2771, 4852, 2798, 4823), new Area(2940, 3375, 2952, 3358), new Area(2922, 3525, 3037, 3308)),
    WATER_ALTAR("Pure essence", "Water rune", new Position(3183, 3166, 0), new Position(3094, 3244, 0), new Area(2706, 4842, 2730, 4828), new Area(3088, 3246, 3097, 3240), new Area(3058, 3278, 3280, 3119)),
    EARTH_ALTAR("Pure essence", "Earth rune", new Position(3305, 3472, 0), new Position(3253, 3421, 0), new Area(2644, 4851, 2663, 4824), new Area(3248, 3427, 3259, 3414), new Area(3225, 3406, 3318, 3500)),
    FIRE_ALTAR("Pure essence", "Fire rune", new Position(3312, 3253, 0), new Position(3382, 3270, 0), new Area(2572, 4852, 2596, 4827), new Area(3375, 3275, 3388, 3266), new Area(3267, 3302, 3412, 3154)),
    BODY_ALTAR("Pure essence", "Body rune", new Position(3055, 3447, 0), new Position(3093, 3490, 0), new Area(2511, 4847, 2529, 4829), new Area(3090, 3499, 3098, 3488), new Area(3039, 3521, 3127, 3408)),
    //COSMIC_ALTAR("Pure essence", "Cosmic rune", new Position(2408, 4379, 0), new Position(2386, 4458, 0), new Area(2119, 4856, 2164, 4811), new Area(2382, 4461, 2390, 4453), new Area(2382, 4461, 2424, 4369)),
    //CHAOS_ALTAR("Pure essence", "Chaos rune", new Position(3062, 3590, 0), new Position(3094, 3497, 0), new Area(), new Area(3090, 3499, 3098, 3488), new Area(3114, 3455, 2959, 3621)),
    //ASTRAL_ALTAR("Pure essence", "Astral rune", new Position(2156, 3865, 0), new Position(2100, 3918, 0), new Area(2150, 3871, 2164, 3859), new Area(2097, 3921, 2104, 3917), new Area(2094, 3924, 2170, 3845))
    ;
    private String essence, crafted;
    private Position ruinsPos, bankPos;
    private Area altarZone, bankZone, pathingZone;

    Altars(String essence, String craftedRune, Position ruinsPos, Position bankPos, Area altarZone, Area bankZone, Area pathingZone) {
        this.essence = essence;
        this.crafted = craftedRune;
        this.ruinsPos = ruinsPos;
        this.bankPos = bankPos;
        this.altarZone = altarZone;
        this.bankZone = bankZone;
        this.pathingZone = pathingZone;
    }

    public String getEssence() {
        return essence;
    }

    public String getCrafted() {
        return crafted;
    }

    public Position getRuinsPos() {
        return ruinsPos;
    }

    public Position getBankPos() {
        return bankPos;
    }

    public Area getAltarZone() {
        return altarZone;
    }

    public Area getBankZone() {
        return bankZone;
    }

    public Area getPathingZone() {
        return pathingZone;
    }

}
