import org.osbot.rs07.event.Event;

/**
 * Created by Chris on 12/21/2017.
 */
public class CustomEvent extends Event {
    @Override
    public int execute() throws InterruptedException {
        if (isShiftClickActive())
            setFinished();
        else {
            //other checks to set it active
            if (clickShiftClickButton())
                setFinished();
            else setFailed();
        }

        return 600;
    }

    private boolean isShiftClickActive(){
        return true;
    }

    private boolean clickShiftClickButton() throws InterruptedException {
        return mouse.click(6, 6, false);
    }

}
