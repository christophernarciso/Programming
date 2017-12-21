import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.map.constants.Banks;
import org.osbot.rs07.api.model.GroundItem;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.event.InteractionEvent;
import org.osbot.rs07.event.WalkingEvent;
import org.osbot.rs07.event.WebWalkEvent;
import org.osbot.rs07.event.webwalk.PathPreferenceProfile;
import org.osbot.rs07.input.mouse.EntityDestination;
import org.osbot.rs07.input.mouse.MouseDestination;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;
import org.osbot.rs07.utility.Condition;
import org.osbot.rs07.utility.ConditionalSleep;

import java.awt.*;
import java.util.Arrays;

/**
 * Created by Chris on 12/21/2017.
 * Lesson two
 * Interaction Event
 * WalkingEvent
 * WebWalkEvent
 * Custom Event Creation
 * Creation of a basic script using our events
 */

@ScriptManifest(author = "Chris", info = "", name = "lesson-2", logo = "", version = 1)
public class Main extends Script{

    private final Position POSITION = new Position(3224, 3218, 0);
    private final String[] HIGH_LEVEL_LOOT = {"Armadyl godsword", "Whip", "Coins", "Rune arrows"};
    @Override
    public void onStart() throws InterruptedException {

    }

    @Override
    public int onLoop() throws InterruptedException {
        //Custom looting script | Loot anything near us
        if (!getInventory().isFull()) { //"Our inventory is not full"
            log("Inventory is not full!....");
            GroundItem lootable = getGroundItems().closest(gi -> gi != null && Arrays.asList(HIGH_LEVEL_LOOT).contains(gi.getName()) && getMap().canReach(gi) && gi.exists());
            if (lootable != null){ // if the ground item exists on the floor ingame
                log("Looting: " + lootable.getName());
                int countLast = getInventory().getEmptySlotCount();
                InteractionEvent lootingEvent = new InteractionEvent(lootable, "Take");
                lootingEvent.setWalkTo(true);
                lootingEvent.setOperateCamera(true);
                if (execute(lootingEvent).hasFinished()){ //Interact "Take" on item
                    new ConditionalSleep(10000, 5000) { //sleep until true or 10 seconds has passed
                        @Override
                        public boolean condition() throws InterruptedException {
                            return getInventory().getEmptySlotCount() < countLast;
                        }
                    }.sleep();
                }
            }
        } else { //"Our invetory is full"
            log("Inventory is full!.....");
            if (getBank().isOpen()){ //if the bank is open
                log("Bank is open!....");
                if (getBank().depositAll()){ //deposit all items
                    log("We deposit all!");
                    new ConditionalSleep(2000, 1000) { //sleep until true or 2 seconds has passed
                        @Override
                        public boolean condition() throws InterruptedException {
                            return !getInventory().isFull();
                        }
                    }.sleep();
                }
            } else { //if the bank is closed
                log("The bank is closed...");
                if (getBank().open()) { //Open the [Bank booth, chest, etc]
                    log("We opened the bank");
                    new ConditionalSleep(3000, 1500) { //sleep until true or 3 seconds has passed
                        @Override
                        public boolean condition() throws InterruptedException {
                            return getBank().isOpen();
                        }
                    }.sleep();
                }
            }
        }

        /*CustomEvent event = new CustomEvent();
        if (execute(event).hasFinished())
            log("event finished!!!!");

        /*log("Creating walking event");
        //getWalking().walk(POSITION); Using a default WalkEvent of 2 min distance and 6 minimap distance
        WalkingEvent walkingEvent = new WalkingEvent(POSITION);
        walkingEvent.setMinDistanceThreshold(0); //0 for exact tile.
        walkingEvent.setBreakCondition(new Condition() {
            @Override
            public boolean evaluate() {
                return myPlayer().getHealthPercent() <= 50;
            }
        });
        walkingEvent.setEnergyThreshold(15);
        execute(walkingEvent);
        log("Walking event completed");


        getWalking().webWalk(POSITION); // Using a default WebWalkWalking event

        log("Creating a webwalkevent");
        WebWalkEvent webWalkEvent = new WebWalkEvent(Banks.GRAND_EXCHANGE);
        webWalkEvent.setEnergyThreshold(15);
        PathPreferenceProfile pathPreferenceProfile = new PathPreferenceProfile();
        pathPreferenceProfile.setAllowObstacles(true);
        pathPreferenceProfile.setAllowTeleports(true);
        pathPreferenceProfile.checkInventoryForItems(true);
        webWalkEvent.setPathPreferenceProfile(pathPreferenceProfile);
        execute(webWalkEvent);
        log("Finished with webwalkevent");

        */




        /*NPC npc = getNpcs().closest("goblin");
        /*if (george != null){ //if george exists
            InteractionEvent interactionEvent = new InteractionEvent(george, "Talk-to"); //creation of an InteractionEvent
            interactionEvent.setOperateCamera(false); //Set the camera use to false.
            interactionEvent.setWalkTo(true); //Set the walk use to true. Walk to george.
            if (execute(interactionEvent).hasFinished()){ // check if the event executed successfully.
                new ConditionalSleep(5000, 2500) { //Sleep for 5 seconds of timeout, and 2.5seconds of condition secs per loop & it will twice
                    @Override
                    public boolean condition() throws InterruptedException {
                        return getDialogues().inDialogue();
                    }
                }.sleep();
            }
        }

        InteractionEvent ie2 = new InteractionEvent(getBot(), safeSpot, "Walk here");
        ie2.setOperateCamera(true);
        ie2.setWalkTo(true);
        if (execute(ie2).hasFinished()){
            new ConditionalSleep(5000, 2500) {
                @Override
                public boolean condition() throws InterruptedException {
                    return myPosition().equals(safeSpot) || safeSpot.distance(myPosition()) < 6;
                }
            }.sleep();
        }

        InteractionEvent ie3 = new InteractionEvent(new EntityDestination(getBot(), npc), "Attack");
        ie3.setWalkTo(true);
        ie3.setOperateCamera(true);
        if (execute(ie3).hasFinished()){
            sleep(10000);
        }*/


        return 700;
    }

    @Override
    public void onExit() throws InterruptedException {

    }

    @Override
    public void onPaint(Graphics2D graphics) {

    }

}
