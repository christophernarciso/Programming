package org.script.orb.nodes;

import org.osbot.rs07.api.filter.ContainsNameFilter;
import org.osbot.rs07.api.map.constants.Banks;
import org.osbot.rs07.api.ui.EquipmentSlot;
import org.script.orb.data.Constants;
import org.script.orb.engine.Context;
import org.script.orb.engine.Node;

public class TravelToBankNode extends Node {

    public TravelToBankNode(Context context) {
        super(context);
    }

    @Override
    public void execute() throws InterruptedException {
        if (Constants.ORB_POWERING_AREA.contains(getContext().myPosition())){
            if (getContext().getEquipment().interact(EquipmentSlot.AMULET, "Edgeville"))
                getContext().sleepUntil(() -> Constants.EDGEVILLE_TELEPORT_AREA.contains(getContext().myPosition()), 10000, 3000);
        } else if (getContext().getEquipment().contains(new ContainsNameFilter<>("Amulet of glory("))){
            if (getContext().getEquipment().interact(EquipmentSlot.AMULET, "Edgeville"))
                getContext().sleepUntil(() -> Constants.EDGEVILLE_TELEPORT_AREA.contains(getContext().myPosition()), 10000, 3000);
        } else if (getContext().getInventory().contains(new ContainsNameFilter<>("Amulet of glory("))){
            if (getContext().getDialogues().isPendingOption()){
                if (getContext().getDialogues().selectOption(1))
                    getContext().sleepUntil(() -> Constants.EDGEVILLE_TELEPORT_AREA.contains(getContext().myPosition()), 10000, 3000);
            } else {
                if (getContext().getInventory().interact("Rub", new ContainsNameFilter<>("Amulet of glory(")))
                    getContext().sleepUntil(() -> getContext().getDialogues().isPendingOption(), 4000);
            }
        } else getContext().stop(true);
    }

    @Override
    public boolean validate() {
        return  !Banks.EDGEVILLE.contains(getContext().myPosition()) && !Constants.EDGEVILLE_TELEPORT_AREA.contains(getContext().myPosition())
                && !Constants.shouldMule && (!getContext().getEquipment().contains(new ContainsNameFilter<>("Amulet of glory(")) || !getContext().getEquipment().contains("Staff of air")
                || !getContext().getInventory().contains("Unpowered orb") || getContext().ourHealthPercent() <= 40 || !getContext().getInventory().contains("Cosmic rune")
                || Constants.shouldLeave);
    }

    @Override
    public String status() {
        return "Travel Task";
    }

}
