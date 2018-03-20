package org.script.orb.nodes;

import org.osbot.rs07.api.filter.ContainsNameFilter;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.api.ui.RS2Widget;
import org.osbot.rs07.api.ui.Spells;
import org.script.orb.data.Constants;
import org.script.orb.engine.Context;
import org.script.orb.engine.Node;

public class OrbNode extends Node {

    public OrbNode(Context context) {
        super(context);
    }

    @Override
    public void execute() throws InterruptedException {
        RS2Object ob = getContext().getObjects().closest("Obelisk of Air");
        RS2Widget chargeInterface = getContext().getWidgets().getWidgetContainingText(270, "How many would you like to charge?");
        if (chargeInterface != null && chargeInterface.isVisible()){
            if (getContext().getKeyboard().typeString("1")) {
                getContext().sleep(getContext().random(1000, 2000));
                if (getContext().getMouse().isOnScreen() && getContext().random(0, 2) == 1)
                    getContext().getMouse().moveOutsideScreen();
                getContext().sleepUntil(() -> !getContext().getInventory().contains("Unpowered orb") || Constants.shouldLeave || getContext().getDialogues().inDialogue(),
                        300000);
                if (getContext().getInventory().getAmount("Air orb") > 0)
                    Constants.orbsMade += getContext().getInventory().getAmount("Air orb");
            }
        } else {
            if (getContext().getMagic().castSpellOnEntity(Spells.NormalSpells.CHARGE_AIR_ORB, ob))
                getContext().sleepUntil(() -> getContext().getWidgets().getWidgetContainingText(270, "How many would you like to charge?") != null, 2000);
        }
    }

    @Override
    public boolean validate() {
        return getContext().getEquipment().contains(new ContainsNameFilter<>("Amulet of glory("), new ContainsNameFilter<>("Staff of air"))
                && getContext().getInventory().contains("Cosmic rune") && getContext().getInventory().contains("Unpowered orb") && !getContext().myPlayer().isAnimating()
                && Constants.ORB_POWERING_AREA.contains(getContext().myPosition())  && !Constants.shouldLeave;
    }

    @Override
    public String status() {
        return "Orb Task";
    }

}
