package org.script.orb.nodes;

import org.osbot.rs07.api.Bank;
import org.osbot.rs07.api.filter.ContainsNameFilter;
import org.osbot.rs07.api.map.constants.Banks;
import org.osbot.rs07.api.model.Player;
import org.osbot.rs07.api.ui.EquipmentSlot;
import org.script.orb.data.Constants;
import org.script.orb.engine.Context;
import org.script.orb.engine.Node;

public class BankingNode extends Node {

    public BankingNode(Context context) {
        super(context);
    }

    @Override
    public void execute() throws InterruptedException {
        if (Constants.shouldLeave){
            getContext().sleepUntil(() -> getContext().getWorlds().hopToP2PWorld(), 10000);
            Constants.shouldLeave = false;
        }
        if (getContext().getBank().isOpen()){
            if (getContext().getEquipment().contains("Amulet of glory") && getContext().getInventory().isFull()){
                if (getContext().getBank().depositAll()) {
                     if (getContext().getBank().close())
                         return;
                }
            }
            if (getContext().getInventory().isEmpty() || getContext().getBank().depositAllExcept("Cosmic rune", "Unpowered orb")){
                if (needGear()){
                    for (String itemList : Constants.GEAR) {
                        if (!getContext().getEquipment().contains(new ContainsNameFilter<>(itemList)) && !getContext().getInventory().contains(new ContainsNameFilter<>(itemList))){
                            if (getContext().getBank().contains(new ContainsNameFilter<>(itemList)))
                                getContext().getBank().withdraw(new ContainsNameFilter<>(itemList), 1);
                            else {
                                getContext().log("Missing item: " + itemList);
                                getContext().stop(true);
                            }
                        }
                    }
                    if (getContext().getBank().close()){
                        for (String itemList : Constants.GEAR) {
                            if (getContext().getInventory().contains(new ContainsNameFilter<>(itemList)))
                                getContext().getInventory().getItem(new ContainsNameFilter<>(itemList)).interact();
                        }
                    }
                } else {
                    //add muling here
                    Player mule = getContext().getPlayers().closest(Constants.tradingPlayer.replace(' ', '\u00A0'));
                    if (mule != null && getContext().getBank().getAmount("Air orb") >= 100){
                        Constants.shouldMule = true;
                    } else if (getContext().getBank().isBankModeEnabled(Bank.BankMode.WITHDRAW_NOTE)){
                        getContext().getBank().enableMode(Bank.BankMode.WITHDRAW_ITEM);
                    } else if (getContext().ourHealthPercent() <= 85){
                        if (getContext().getInventory().isFull())
                            getContext().getBank().depositAll();
                        getContext().log("Handler: Food");
                        if (getContext().getBank().contains("Tuna")){
                            if (getContext().getBank().withdraw("Tuna", 1))
                                if (getContext().getBank().close())
                                    getContext().getInventory().interact("Eat", "Tuna");

                        } else {
                            getContext().log("Missing item: Food[Tuna]");
                            getContext().stop(true);
                        }
                    } else if ((int) getContext().getInventory().getAmount("Cosmic rune") < 81){
                        getContext().log("Handler: Cosmic Rune");
                        if (getContext().getBank().contains("Cosmic rune") && getContext().getBank().getAmount("Cosmic rune") > 81)
                            getContext().getBank().withdraw("Cosmic rune",
                                    (getContext().getInventory().contains("Cosmic rune") ? (int) (81 - getContext().getInventory().getAmount("Cosmic rune")) : 81));
                        else {
                            getContext().log("Missing item: Cosmic rune");
                            getContext().stop(true);
                        }
                    } else if (getContext().getInventory().getAmount("Unpowered orb") < 27){
                        getContext().log("Handler: Unpowered Orb");
                        if (getContext().getBank().contains("Unpowered orb"))
                            getContext().getBank().withdraw("Unpowered orb",
                                    (getContext().getInventory().contains("Unpowered orb") ? (int) (27 - getContext().getInventory().getAmount("Unpowered orb")) : 27));
                        else {
                            getContext().log("Missing item: Unpowered orb");
                            getContext().stop(true);
                        }
                    }
                }
            }
        } else {
            if (getContext().getEquipment().contains("Amulet of glory") && !getContext().getInventory().isFull()){
                getContext().getEquipment().unequip(EquipmentSlot.AMULET, "Amulet of glory");
                return;
            }

            if (getContext().getBank().open())
                getContext().sleepUntil(() -> getContext().getBank().isOpen(), 2400, 1200);
        }
    }

    @Override
    public boolean validate() {
        return (Banks.EDGEVILLE.contains(getContext().myPosition()) || Constants.EDGEVILLE_TELEPORT_AREA.contains(getContext().myPosition()))
                && !Constants.shouldMule && (!getContext().getEquipment().contains(new ContainsNameFilter<>("Amulet of glory(")) || !getContext().getEquipment().contains("Staff of air")
                    || !getContext().getInventory().contains("Unpowered orb") || getContext().ourHealthPercent() <= 85 || (int) getContext().getInventory().getAmount("Cosmic rune") < 81
                    || getContext().getInventory().contains("Air orb")
                    || Constants.shouldLeave);
    }

    @Override
    public String status() {
        return "Banking Task";
    }

    private boolean needGear(){
        for (String itemList : Constants.GEAR) {
            if (!getContext().getEquipment().contains(new ContainsNameFilter<>(itemList)))
                return true;
        }
        return false;
    }

}
