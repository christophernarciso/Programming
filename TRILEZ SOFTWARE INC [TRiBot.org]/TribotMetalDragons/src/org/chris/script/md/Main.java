package org.chris.script.md;

import org.chris.script.md.data.Constants;
import org.chris.script.md.data.Variables;
import org.tribot.api.Clicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api.util.abc.ABCUtil;
import org.tribot.api2007.*;
import org.tribot.api2007.ext.Filters;
import org.tribot.api2007.types.*;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.*;

import java.awt.*;

@ScriptManifest(authors = "AliveInMe", name = "CMetalDragons", category = "Combat")
public class Main extends Script implements Starting, Painting, Ending, MessageListening07{

    private ABCUtil abc;

    @Override
    public void onStart() {
        //Start AntibanCompliance instance
        General.useAntiBanCompliance(true);
        abc = new ABCUtil();
        //Drink when the script first starts inside the dragon room.
        Variables.get().shouldDrinkAntifire = true;
        //Generate our eatAt from the abc instance
        Variables.get().eatAt = abc.generateEatAtHP();
        //Set to use ranged potions if the inventory contains them
        Variables.get().shouldDrinkRangingPotion = Inventory.find(Constants.Filters.ITEM.POTION_RANGING).length > 0;
    }
    
    @Override
    public void run() {
        while (true) {
            if (Constants.METAL_DRAGONS_ROOM.contains(Player.getPosition())){
                /* Check item consumables food & potions*/
                consumables();

                if (!isAttacking()){
                    /* checks for loot if shouldLoot */
                    loot();
                    /* checks for alchable | attack valid npc */
                    attack();
                } else {
                    /* abcl usage */
                    antibanCompliance();
                    /* in combat checks */
                    interacting();
                }
            } else {
                General.println("Please start in the metal dragons room!");
                break;
            }
            General.sleep(1000);
        }
    }

    @Override
    public void onPaint(Graphics graphics) {
        graphics.drawString("Metal Dragon Killer v" + Constants.SCRIPT_VERSION + " by " + Constants.SCRIPT_AUTHOR, 10, 20);
    }

    @Override
    public void onEnd() {
        //Close the abcl instance.
        abc.close();
    }

    @Override
    public void tradeRequestReceived(String s) {

    }

    @Override
    public void duelRequestReceived(String s, String s1) {

    }

    @Override
    public void playerMessageReceived(String s, String s1) {

    }

    @Override
    public void personalMessageReceived(String s, String s1) {

    }

    @Override
    public void serverMessageReceived(String s) {
        if (s.contains("Your antifire potion is about to expire") || s.contains("Your antifire potion has expired"))
            Variables.get().shouldDrinkAntifire = true;
        else if (s.contains("You drink some of your antifire") || s.equals("You drink some of your extended antifire potion."))
            Variables.get().shouldDrinkAntifire = false;
    }

    @Override
    public void clanMessageReceived(String s, String s1) {

    }

    /**
     * Methods created by Encoded
     * Adjusted to my liking
     */
    private boolean isAttacking() {
        RSPlayer player = Player.getRSPlayer();

        //If player doesnt exist return false
        if (player == null) {
            return false;
        }

        //Gets the interacting character
        RSCharacter interacting = player.getInteractingCharacter();

        return interacting != null && interacting.getName().contains("dragon") && (interacting.isInteractingWithMe()
                && (interacting.isInCombat() || interacting.getHealthPercent() <= 0.34)
                || Timing.waitCondition(Constants.Conditions.ANIMATING, General.random(2000, 3000)));
    }

    private RSNPC getValidNPC() {
        //Grab the interacting NPC if available.
        RSNPC interacting = getInteractingNPC();
        if (interacting != null) {
            return interacting;
        }

        //Grab available NPCs
        RSNPC[] available = getAvailableValidNPCS();
        return available.length > 0 ? available[0] : null;
    }

    private RSNPC getInteractingNPC() {
        RSNPC[] interacting = NPCs.findNearest(
                Filters.NPCs.nameContains("dragon")
                        .combine(Filters.NPCs.inArea(Constants.METAL_DRAGONS_ROOM), false)
                        .combine(Constants.Filters.NPCs.INTERACTING, false)
        );
        return interacting.length > 0 ? interacting[0] : null;
    }

    private RSNPC[] getAvailableValidNPCS() {
        return NPCs.findNearest(
                Filters.NPCs.nameContains("dragon")
                        .combine(Filters.NPCs.inArea(Constants.METAL_DRAGONS_ROOM), false)
                        .combine(Constants.Filters.NPCs.OUT_OF_COMBAT, false)
        );
    }

    /**
     * Tribot Antiban Compliance Methods
     */
    private void antibanCompliance(){
        if (this.abc.shouldCheckTabs())
            this.abc.checkTabs();

        if (this.abc.shouldCheckXP())
            this.abc.checkXP();

        if (this.abc.shouldExamineEntity())
            this.abc.examineEntity();

        if (this.abc.shouldMoveMouse())
            this.abc.moveMouse();

        if (this.abc.shouldPickupMouse())
            this.abc.pickupMouse();

        if (this.abc.shouldRightClick())
            this.abc.rightClick();

        if (this.abc.shouldRotateCamera())
            this.abc.rotateCamera();

        if (this.abc.shouldLeaveGame())
            this.abc.leaveGame();
    }

    /**
     * Checks for valid alchable
     * Grab a valid NPC interactable & interact with it
     */
    //TODO: ADD SPELL NAME
    private void attack(){
        RSItem[] alchable = Inventory.find(Constants.Filters.ITEM.ALCHABLE_ITEM);
        if (alchable.length > 0 && Inventory.getCount("Fire rune", "Nature rune") > 5){
            if (Magic.isSpellSelected()){
                if (alchable[0].click("Cast"))
                    Timing.waitCondition(Constants.Conditions.ANIMATING, 2000);
            } else {
                if (Magic.selectSpell(""))
                    Timing.waitCondition(Constants.Conditions.SPELL_SELECTED, 4000);
            }
        } else {
            RSNPC dragon = getValidNPC();

            if (dragon != null && !Variables.get().shouldLoot) {
                if (dragon.isClickable()) {
                    if (Clicking.click("Attack", dragon))
                        Timing.waitCondition(Constants.Conditions.ANIMATING, 3000);
                } else {
                    Camera.turnToTile(dragon.getPosition());
                    Timing.waitCondition(new Condition() {
                        @Override
                        public boolean active() {
                            General.sleep(300);
                            return dragon.isClickable();
                        }
                    }, 5000);
                }
            }
        }
    }

    /**
     * Grab the interactable & check if it has died.
     * Once the death animation starts -> sleep until possible loot has appeared || health low
     */
    private void interacting(){
        RSNPC dragon = getInteractingNPC();
        if (dragon != null){
            if (dragon.getAnimation() == 91 || dragon.getAnimation() == 80){
                General.println("Melee range avoidance");
                walkAwayFromDanger();
            }

            if (dragon.getHealthPercent() == 0){
                General.println("Dragon has died. Waiting for possible loot.");
                if (Timing.waitCondition(Constants.Conditions.POSSIBLE_LOOT, General.random(6000, 8000)))
                    Variables.get().shouldLoot = true;
            }
        }
    }

    /**
     * Checks the surrounding game for lootables from our array of options.
     * If valid -> interact with the lootable
     */
    private void loot(){
        RSGroundItem[] lootable = GroundItems.find(Constants.Filters.GROUNDITEM.RS_GROUND_ITEM);

        if (lootable.length > 0 && Variables.get().shouldLoot){
            if (Inventory.isFull()){
                if (Inventory.find(Constants.Filters.ITEM.EDIBLE).length > 0){
                    if (Inventory.find(Constants.Filters.ITEM.EDIBLE)[0].click("Eat"))
                        Timing.waitCondition(Constants.Conditions.NOT_FULL, 2000);
                } else if (Inventory.find("Vial").length > 0){
                    if (Inventory.find("Vial")[0].click("Eat"))
                        Timing.waitCondition(Constants.Conditions.NOT_FULL, 2000);
                } else Variables.get().shouldLoot = false;
            } else {
                if (lootable[0].click("Take"))
                    General.sleep(1200, 2200); //placeholder until I can think of a timing condition
            }
        }

    }

    /**
     * Checks the inventory for consumables & checks each condition before interaction
     * FOOD | POTION
     */
    private void consumables(){
        if (Player.getRSPlayer().getHealthPercent() <= Variables.get().eatAt){
            RSItem[] edible = Inventory.find(Constants.Filters.ITEM.EDIBLE);
            if (edible.length > 0){
                if (edible[0].click("Eat")){
                    Timing.waitCondition(Constants.Conditions.ANIMATING, 2000);
                }
            }
        } else if (Variables.get().shouldDrinkAntifire){
            RSItem[] potion = Inventory.find(Constants.Filters.ITEM.POTION_ANTIFIRE);
            if (potion.length > 0){
                if (potion[0].click("Drink")){
                    Timing.waitCondition(Constants.Conditions.ANIMATING, 2000);
                    Variables.get().shouldDrinkAntifire = false;
                }
            } else General.println("No antifire found.");
        } else if (Variables.get().shouldDrinkRangingPotion && Skills.getActualLevel(Skills.SKILLS.RANGED) == Skills.getCurrentLevel(Skills.SKILLS.RANGED)){
            RSItem[] potion = Inventory.find(Constants.Filters.ITEM.POTION_RANGING);
            if (potion.length > 0){
                if (potion[0].click("Drink")){
                    Timing.waitCondition(Constants.Conditions.ANIMATING, 2000);
                }
            } else General.println("No antifire found.");
        }
    }

    /**
     * Grabs a safe tile by condition to get away from melee distance
     */
    private void walkAwayFromDanger(){
        for (RSTile tile : Constants.METAL_DRAGONS_ROOM.getAllTiles()) {
            if (tile != null && tile.isClickable() && tile.distanceTo(Player.getRSPlayer().getPosition()) > 5
                    && tile.distanceTo(Player.getRSPlayer().getPosition()) < 9){
                if (Walking.walkTo(tile))
                    break;
            }
        }
    }

}
