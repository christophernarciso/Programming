package com.excellentscripts.api.util.enums;

/**
     * A collection of raw fish ids.
     */
    public enum RawFish {
        SHRIMP(317, 1, "Net"),
        SARDINES(-1, 5, "Bait"),
        HERRING(-1, 10, "Bait"),
        ANCHOVIES(321, 15, "Net"),
        TROUT(333, 15, "Lure"),
        PIKE(-1, 25, "Bait"),
        SALMON(329, 25, "Lure"),
        TUNA(359, 30, "Harpoon"),
        LOBSTER(377, 40, "Cage"),
        SWORDFISH(371, 50, "Harpoon"),
        SHARK(383, 76, "Harpoon"),
        LEAPING_TROUT(-1, 48, "Bait"),
        LEAPING_SALMON(-1, 58, "Bait"),
        LEAPING_STURGEON(-1, 70, "Bait");

        private int rawId;
        private int fishingLevel;
        private String action;

        RawFish(int rawId, int fishingLevel, String action) {
            this.rawId = rawId;
            this.fishingLevel = fishingLevel;
            this.action = action;
        }

        public int getRawId() {
            return rawId;
        }

        public int getFishingLevel() {
            return fishingLevel;
        }

        public String getAction() {
            return action;
        }

        /**
         * Gets the {@link RawFish} object associated with the given name, or null if not found.
         * @param name
         * @return
         */
        public static RawFish forName(String name) {
            for (RawFish fish : RawFish.values()) {
                if (fish.toString().equalsIgnoreCase(name)) {
                    return fish;
                }
            }
            return null;
        }
    }