import org.osbot.rs07.api.filter.Filter;
import org.osbot.rs07.api.model.GroundItem;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;
import org.osbot.rs07.utility.ConditionalSleep;

import java.awt.*;

/** LESSON 1
 * Created by Chris on 12/10/2017.
 */
@ScriptManifest(author = "Chris", info = "", name = "lesson1", logo = "", version = 1.0)
public class Main extends Script{
    // ALT + ENTER implement methods
    //CRTL + O : implement methods

    @Override
    public void onStart() throws InterruptedException {
        //Anything placed here in once

    }

    @Override
    public int onLoop() throws InterruptedException {
        GroundItem logs = getGroundItems().closest(gi -> gi != null && getMap().canReach(gi));

        if (logs != null){ //if it exists
            int lastCount = getInventory().getEmptySlotCount();
            if (logs.interact("Take")){
                new ConditionalSleep(3000) {
                    @Override
                    public boolean condition() throws InterruptedException {
                        return getInventory().getEmptySlotCount() < lastCount;
                    }
                }.sleep();
            }
        }



        /*RS2Object tree = getObjects().closest(obj -> obj != null && obj.getName().equals("Willow") && getMap().canReach(obj));
        Woodcutting script
        if (getInventory().isFull()){
            log("Dropping the inventory because it is full!");
            getInventory().dropAll("Willow logs");
        } else {
            if (!myPlayer().isAnimating()){ //if we arent animated
                if (tree != null){ //if the tree exists
                    if (tree.interact("Chop down")){ //chop the tree
                        new ConditionalSleep(3000, 1000) {
                            @Override
                            public boolean condition() throws InterruptedException { //sleep until we animate
                                return myPlayer().isAnimating();
                            }
                        }.sleep();
                    }
                }
            }
        }



        /*RS2Object door = getObjects().closest("Door");

        RS2Object door2 = getObjects().closest(new Filter<RS2Object>() {
            @Override
            public boolean match(RS2Object rs2Object) {
                return rs2Object != null && (rs2Object.getName().equals("Door") || rs2Object.getName().equals("Large door")) && rs2Object.hasAction("Open") && getMap().canReach(rs2Object);
            }
        });

        RS2Object door3 = getObjects().closest(rs2Object -> rs2Object != null && rs2Object.getName().equals("Door") && rs2Object.hasAction("Open") && getMap().canReach(rs2Object));

        if (door2 != null){
            log("Door action Open? " + door2.hasAction("Open"));
            if (door2.interact("Open")){
                new ConditionalSleep(2000, 1000) {
                    @Override
                    public boolean condition() throws InterruptedException {
                        return door2.hasAction("Close");
                    }
                }.sleep();
                log("Door action close? " + door2.hasAction("Close"));
            }
        }

        /*using ther Filter Class
        NPC goblin = getNpcs().closest(new Filter<NPC>() {
            @Override
            public boolean match(NPC npc) {
                return npc != null && npc.getName().equals("Goblin") && npc.exists()
                        && getMap().canReach(npc) && npc.getHealthPercent() > 0 && npc.getInteracting() == null;
            }
        });

        NPC goblin2 = getNpcs().closest(npc -> npc != null && npc.getName().equals("Goblin") && npc.exists()
                && getMap().canReach(npc) && npc.getHealthPercent() > 0 && npc.getInteracting() == null);

        if (!getCombat().isFighting()){ //not in combat
            if (goblin != null){ //if it exists
                if (goblin.interact("Attack")){ //attack
                    new ConditionalSleep(3000, 1000) { //sleep for 3 seconds or until the condition is true
                        @Override
                        public boolean condition() throws InterruptedException {
                            return getCombat().isFighting();
                        }
                    }.sleep();
                }
            }
        }

        /*Anything placed will be looped every 600ms | 1000ms = 1 second
        NPC npc = getNpcs().closest("Goblin");

        if (!getCombat().isFighting()){ //checks if we are not in combat
            if (npc != null && npc.getHealthPercent() > 0){ //if it exists
                log("It exists!");

                if (npc.isVisible()){ //if its visible
                    log("Its visible!");
                    if (npc.interact("Attack")){ //interact attack option
                        log("We interacted with the NPC! " + npc.getName());
                        new ConditionalSleep(3000, 1000) { //sleep for 3000ms or 3 seconds until the condition true
                            @Override
                            public boolean condition() throws InterruptedException {
                                return getCombat().isFighting();
                            }
                        }.sleep();
                    }
                } else { //else we will run the code here
                    log("Moving camera!");
                    getCamera().toEntity(npc);
                }
            }
        } else {
            log("In combat!");
        }*/





        return 600;
    }

    @Override
    public void onExit() throws InterruptedException {
        //Anything placed here executes when the script stops
    }

    @Override
    public void onPaint(Graphics2D g) {

    }

}
