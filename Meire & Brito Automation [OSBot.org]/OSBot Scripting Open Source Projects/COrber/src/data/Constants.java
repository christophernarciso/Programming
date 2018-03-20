package org.script.orb.data;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;

import java.util.ArrayList;
import java.util.HashMap;

public class Constants {
    public static int deaths, orbsMade, orbPrice;
    public static boolean shouldMule, shouldLeave;
    public static String tradingPlayer;
    public static final ArrayList<String> GEAR = new ArrayList();
    public static final HashMap<String, Integer> TRADEABLES = new HashMap<>();

    public static final Area AREA_EDGEVILLE_TRAPDOOR = new Area(3092, 3473, 3097, 3468);
    public static final Position EDGEVILLE_TRAPDOOR_WALKABLE = new Position(3094, 3471, 0);

    public static final Area FIRST_GATE_AREA = new Area(3094, 9915, 3103, 9904);
    public static final Area SECOND_GATE_AREA = new Area(3127, 9917, 3136, 9912);
    public static final Area LADDER_AREA = new Area(3082, 9975, 3096, 9965);

    public static final Area LADDER_PATHING_FAILSAFE = new Area(3102, 9944, 3136, 9918);

    public static final Area LADDER_PATHING_AREA = new Area(
            new int[][]{
                    { 3138, 9918 },
                    { 3139, 9965 },
                    { 3091, 9977 },
                    { 3065, 9969 },
                    { 3066, 9928 },
                    { 3120, 9918 }
            }
    );

    public static final Area FIRST_PATHING_GATE_AREA = new Area(
            new int[][]{
                    { 3085, 9865 },
                    { 3113, 9865 },
                    { 3113, 9879 },
                    { 3113, 9890 },
                    { 3101, 9891 },
                    { 3104, 9904 },
                    { 3104, 9917 },
                    { 3086, 9917 }
            }
    );

    public static final Area SECOND_PATHING_GATE_AREA = new Area(
            new int[][]{
                    { 3104, 9915 },
                    { 3104, 9904 },
                    { 3129, 9901 },
                    { 3144, 9901 },
                    { 3143, 9918 },
                    { 3127, 9918 }
            }
    );

    public static final Area EDGEVILLE_TELEPORT_AREA = new Area(
            new int[][]{
                    { 3074, 3506 },
                    { 3076, 3482 },
                    { 3100, 3483 },
                    { 3099, 3488 },
                    { 3091, 3489 },
                    { 3091, 3500 },
                    { 3091, 3505 },
                    { 3091, 3506 }
            }
    );

    public static final Area ORB_POWERING_AREA = new Area(
            new int[][]{
                    { 3081, 3573 },
                    { 3084, 3577 },
                    { 3091, 3577 },
                    { 3095, 3573 },
                    { 3095, 3567 },
                    { 3090, 3563 },
                    { 3084, 3563 },
                    { 3080, 3568 }
            }
    );

    public static final Position[] DEATHWALK_PATH = {
            new Position(3224, 3218, 0),
            new Position(3225, 3219, 0),
            new Position(3227, 3219, 0),
            new Position(3227, 3218, 0),
            new Position(3230, 3219, 0),
            new Position(3233, 3219, 0),
            new Position(3234, 3222, 0),
            new Position(3235, 3224, 0),
            new Position(3238, 3225, 0),
            new Position(3240, 3225, 0),
            new Position(3243, 3226, 0),
            new Position(3249, 3226, 0),
            new Position(3255, 3226, 0),
            new Position(3258, 3228, 0),
            new Position(3259, 3233, 0),
            new Position(3259, 3237, 0),
            new Position(3258, 3242, 0),
            new Position(3254, 3248, 0),
            new Position(3252, 3251, 0),
            new Position(3250, 3254, 0),
            new Position(3248, 3257, 0),
            new Position(3250, 3260, 0),
            new Position(3248, 3264, 0),
            new Position(3250, 3268, 0),
            new Position(3246, 3271, 0),
            new Position(3243, 3275, 0),
            new Position(3240, 3277, 0),
            new Position(3239, 3281, 0),
            new Position(3237, 3284, 0),
            new Position(3238, 3290, 0),
            new Position(3238, 3294, 0),
            new Position(3238, 3299, 0),
            new Position(3237, 3302, 0),
            new Position(3236, 3303, 0),
            new Position(3234, 3307, 0),
            new Position(3231, 3308, 0),
            new Position(3229, 3309, 0),
            new Position(3224, 3311, 0),
            new Position(3221, 3317, 0),
            new Position(3220, 3324, 0),
            new Position(3218, 3326, 0),
            new Position(3215, 3329, 0),
            new Position(3213, 3333, 0),
            new Position(3211, 3335, 0),
            new Position(3206, 3339, 0),
            new Position(3205, 3341, 0),
            new Position(3204, 3348, 0),
            new Position(3202, 3353, 0),
            new Position(3200, 3358, 0),
            new Position(3199, 3361, 0),
            new Position(3199, 3366, 0),
            new Position(3199, 3367, 0),
            new Position(3197, 3369, 0),
            new Position(3196, 3370, 0),
            new Position(3192, 3373, 0),
            new Position(3187, 3377, 0),
            new Position(3181, 3381, 0),
            new Position(3176, 3386, 0),
            new Position(3169, 3393, 0),
            new Position(3160, 3397, 0),
            new Position(3155, 3398, 0),
            new Position(3151, 3400, 0),
            new Position(3148, 3402, 0),
            new Position(3146, 3402, 0),
            new Position(3140, 3406, 0),
            new Position(3134, 3407, 0),
            new Position(3131, 3409, 0),
            new Position(3129, 3412, 0),
            new Position(3125, 3414, 0),
            new Position(3120, 3416, 0),
            new Position(3114, 3420, 0),
            new Position(3109, 3421, 0),
            new Position(3104, 3420, 0),
            new Position(3100, 3422, 0),
            new Position(3099, 3425, 0),
            new Position(3101, 3430, 0),
            new Position(3099, 3435, 0),
            new Position(3096, 3441, 0),
            new Position(3098, 3447, 0),
            new Position(3095, 3452, 0),
            new Position(3092, 3456, 0),
            new Position(3089, 3460, 0),
            new Position(3087, 3464, 0),
            new Position(3092, 3464, 0),
            new Position(3099, 3464, 0),
            new Position(3098, 3473, 0),
            new Position(3098, 3480, 0),
            new Position(3094, 3485, 0),
            new Position(3093, 3491, 0)
    };
}
