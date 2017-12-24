package org.chris.script.md.data;

import org.tribot.api.General;
import org.tribot.api.types.generic.Condition;
import org.tribot.api.types.generic.Filter;
import org.tribot.api2007.*;
import org.tribot.api2007.types.*;

import java.util.Arrays;

public final class Constants {

    public static final double SCRIPT_VERSION = 1.0;
    public static final String SCRIPT_AUTHOR = "AIM";

    public static final String[] LOOTABLES = {};
    public static final String[] ALCHABLES = {};

    public static final RSArea METAL_DRAGONS_ROOM = new RSArea(
            new RSTile(2690, 9466),
            new RSTile(2757, 9407)
    );


    public static class Conditions {
        public static final Condition ANIMATING = new Condition() {
            @Override
            public boolean active() {
                General.sleep(50, 100);
                return Player.getAnimation() != -1;
            }
        };
        public static final Condition POSSIBLE_LOOT = new Condition() {
            @Override
            public boolean active() {
                return GroundItems.find(Filters.GROUNDITEM.RS_GROUND_ITEM).length > 0
                        || Player.getRSPlayer().getHealthPercent() <= Variables.get().eatAt;
            }
        };
        public static final Condition NOT_FULL = new Condition() {
            @Override
            public boolean active() {
                return !Inventory.isFull();
            }
        };
        public static final Condition SPELL_SELECTED = new Condition() {
            @Override
            public boolean active() {
                return Magic.isSpellSelected();
            }
        };
    }

    public static class Filters {
        public static class NPCs {
            public static final Filter<RSNPC> INTERACTING = new Filter<RSNPC>() {
                @Override
                public boolean accept(RSNPC rsnpc) {
                    return rsnpc.isInteractingWithMe();
                }
            };
            public static final Filter<RSNPC> OUT_OF_COMBAT = new Filter<RSNPC>() {
                @Override
                public boolean accept(RSNPC rsnpc) {
                    return !rsnpc.isInCombat() && rsnpc.getPosition().distanceTo(Player.getRSPlayer().getPosition()) > 4
                            && rsnpc.getAnimation() == -1 && rsnpc.getInteractingCharacter() == null;
                }
            };
        }

        public static class ITEM {
            public static final Filter<RSItem> EDIBLE = new Filter<RSItem>() {
                @Override
                public boolean accept(RSItem rsItem) {
                    return rsItem.getDefinition() != null && rsItem.isClickable() &&
                            rsItem.getDefinition().getActions().length > 0 && Arrays.asList(rsItem.getDefinition().getActions()).contains("Eat");
                }
            };
            public static final Filter<RSItem> POTION_ANTIFIRE = new Filter<RSItem>() {
                @Override
                public boolean accept(RSItem rsItem) {
                    return rsItem.getDefinition() != null && rsItem.isClickable() && rsItem.getDefinition().getName().contains("antifire") &&
                            rsItem.getDefinition().getActions().length > 0 && Arrays.asList(rsItem.getDefinition().getActions()).contains("Drink");
                }
            };
            public static final Filter<RSItem> POTION_RANGING = new Filter<RSItem>() {
                @Override
                public boolean accept(RSItem rsItem) {
                    return rsItem.getDefinition() != null && rsItem.isClickable() && rsItem.getDefinition().getName().contains("Ranging potion") &&
                            rsItem.getDefinition().getActions().length > 0 && Arrays.asList(rsItem.getDefinition().getActions()).contains("Drink");
                }
            };
            public static final Filter<RSItem> ALCHABLE_ITEM = new Filter<RSItem>() {
                @Override
                public boolean accept(RSItem rsItem) {
                    return rsItem.getDefinition() != null && rsItem.isClickable() && rsItem.getDefinition().getActions().length > 0
                            && Arrays.stream(Constants.ALCHABLES).anyMatch(i -> i.contains(rsItem.getDefinition().getName()));
                }
            };
        }

        public static class GROUNDITEM {
            public static final Filter<RSGroundItem> RS_GROUND_ITEM = new Filter<RSGroundItem>() {
                @Override
                public boolean accept(RSGroundItem rsGroundItem) {
                    return rsGroundItem.getDefinition() != null && rsGroundItem.isClickable() && rsGroundItem.getDefinition().getActions().length > 0
                            && Arrays.stream(Constants.LOOTABLES).anyMatch(i -> i.contains(rsGroundItem.getDefinition().getName()));
                }
            };
        }
    }


    private Constants(){

    }

}
