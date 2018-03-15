import org.osbot.rs07.api.ui.RS2Widget;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;
import org.osbot.rs07.utility.ConditionalSleep;

@ScriptManifest(version = 1.0, author = "", info = "", logo = "", name = "Testing2222")
public class Main extends Script{
    @Override
    public int onLoop() throws InterruptedException {
        /*
            Config & Widget Basics
         */
        /*
            Basic Config checking.
            getConfigs().get(int id) returns a value set from the parameter id
            In our case 173 = run game setting. if we toggled run on or off.
            returns 0 value if the setting is off and we are on walking mode.
            returns 1 value if the setting is on and we are on running mode.
         */
        switch (getConfigs().get(173)){
            case 0:
                log("It's off!");
                break;
            case 1:
                log("It's on!");
                break;
        }
        /*
            For your widget check the color type.
            RWG: RED, WHITE, GREEN
            WHatever is visible will display its widget ids

            CLIENT DEBUGGER MENU
            R -= PARENT/ROOT ID
              -= CHILD ID
             --= GRANDCHILD/SUBCHILD ID
         */

        /* Part 1 finding widget by static ids */
        RS2Widget tutorial = getWidgets().get(261, 4);
        if (tutorial != null && tutorial.isVisible()){
            log("It's visible");
        }

        /* Part 2 finding widget by text */
        RS2Widget tutorial = getWidgets().getWidgetContainingText(446, "Xeric's Heart");
        if (tutorial != null && tutorial.isVisible()){
            log("It's visible");

        }
        /* Part 3 Crafting mould gold ring interaction */
        RS2Widget tutorial = getWidgets().singleFilter(446,
                w -> w != null && w.isVisible() && w.getItemId() == 1635 && w.getInteractActions().length > 0);
        if (tutorial != null && tutorial.isVisible()){
            log("It's visible");
            if (tutorial.interact("Make-All")){
                log("We clicked it!");
                //Sleep for 30 seconds and check condition every 5 seconds or if the condition is true, break the sleep.
                new ConditionalSleep(30000, 5000) {
                    @Override
                    public boolean condition() throws InterruptedException {
                        return getDialogues().isPendingContinuation() || !getInventory().contains("Gold bar");
                    }
                }.sleep();
            }
        } else log("We skipped widget interaction");
        return 3000;
    }
}
