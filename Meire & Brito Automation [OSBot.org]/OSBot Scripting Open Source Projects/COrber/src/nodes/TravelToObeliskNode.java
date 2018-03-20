package org.script.orb.nodes;

import org.osbot.rs07.api.filter.ContainsNameFilter;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.api.ui.RS2Widget;
import org.script.orb.data.Constants;
import org.script.orb.engine.Context;
import org.script.orb.engine.Node;

public class TravelToObeliskNode extends Node {

    public TravelToObeliskNode(Context context) {
        super(context);
    }

    //new Position(3102, 9909, 0) 1st gate
    //new Position(3131, 9916, 0) 2nd gate
    //new Position(3088, 9970, 0) ladder

    @Override
    public void execute() throws InterruptedException {
        RS2Widget warning = getContext().getWidgets().getWidgetContainingText(475, "Enter Wilderness");
        if (warning != null && warning.isVisible()) {
            if (warning.interact())
                getContext().sleepUntil(() -> !warning.isVisible(), 3000);
        } else {
            if (getContext().myPosition().getY() < 7000) {
                if (Constants.AREA_EDGEVILLE_TRAPDOOR.contains(getContext().myPosition())) {
                    if (getContext().interactEntity("Trapdoor", "Climb-down"))
                        getContext().sleepUntil(() -> !Constants.AREA_EDGEVILLE_TRAPDOOR.contains(getContext().myPosition()),
                                7000, 600);
                    else if (getContext().interactEntity("Trapdoor", "Open"))
                        if (getContext().sleepUntil(() -> getContext().interactEntity("Trapdoor", "Climb-down"), 7000, 600))
                            getContext().sleepUntil(() -> !Constants.AREA_EDGEVILLE_TRAPDOOR.contains(getContext().myPosition()),
                                    7000, 600);
                } else getContext().aStarWalkToDestination(Constants.EDGEVILLE_TRAPDOOR_WALKABLE);
            } else {
                if (Constants.FIRST_PATHING_GATE_AREA.contains(getContext().myPosition())) {
                    if (Constants.FIRST_GATE_AREA.contains(getContext().myPosition())) {
                        RS2Object gate = getContext().getObjects().closest("Gate");
                        if (gate != null) {
                            if (gate.hasAction("Open")) {
                                if (gate.interact("Open"))
                                    getContext().sleepUntil(() -> gate.hasAction("Close"), 2000);
                            } else getContext().aStarWalkToDestination(new Position(3131, 9916, 0));
                        }
                    } else getContext().aStarWalkToDestination(new Position(3102, 9909, 0));
                } else if (Constants.SECOND_PATHING_GATE_AREA.contains(getContext().myPosition())) {
                    if (Constants.SECOND_GATE_AREA.contains(getContext().myPosition())) {
                        if (getContext().interactEntity("Gate", "Open"))
                            getContext().sleepUntil(() -> !Constants.SECOND_GATE_AREA.contains(getContext().myPosition())
                                    || getContext().getWidgets().getWidgetContainingText(475, "Enter Wilderness") != null, 10000);
                    } else getContext().aStarWalkToDestination(new Position(3131, 9916, 0));
                } else if (Constants.LADDER_PATHING_FAILSAFE.contains(getContext().myPosition())) {
                    getContext().aStarWalkToDestination(new Position(3126, 9951, 0));
                } else if (Constants.LADDER_PATHING_AREA.contains(getContext().myPosition())) {
                    if (Constants.LADDER_AREA.contains(getContext().myPosition())) {
                        if (getContext().interactEntity("Ladder", "Climb-up"))
                            getContext().sleepUntil(() -> !Constants.LADDER_AREA.contains(getContext().myPosition()), 5000);
                    } else getContext().aStarWalkToDestination(new Position(3088, 9970, 0));
                }
            }
        }
    }

    @Override
    public boolean validate() {
        return getContext().getEquipment().contains(new ContainsNameFilter<>("Amulet of glory("), new ContainsNameFilter<>("Staff of air"))
                && getContext().getInventory().getAmount("Cosmic rune") >= 81 && getContext().getInventory().contains("Unpowered orb")
                && !Constants.ORB_POWERING_AREA.contains(getContext().myPosition()) && !Constants.shouldLeave;
    }

    @Override
    public String status() {
        return "Travel Task";
    }

}
