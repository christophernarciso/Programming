import org.osbot.rs07.api.ui.Message;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;
import util.MuleSettings;
import util.customevents.DisableAudioEvent;
import util.customevents.LoginEvent;
import util.gamehandlers.TradeHandler;
import util.timers.Time;
import util.timers.Timer;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Usage:
 *       Local based mule handling
 *
 *       Execute with parameters -allow norandoms
 *       -script MuleScript:user_pass
 *
 *
 *
 *
 */
@ScriptManifest(name = "MuleScript", version = 1.0, author = "Chris", logo = "", info = "Mule until 30mins is up")
public class Main extends Script {

    private MuleSettings muleSettings;
    private TradeHandler tradeHandler;
    private LoginEvent loginEvent;
    private DisableAudioEvent disableAudioEvent;
    private List<String> cacheRequests;
    private long startTime;
    private String status = "N/A", username, password, requestor;
    private Timer antiLogout, closeScriptTimer;
    private int tradesCompleted = 0, ticks = 0;

    @Override
    public void onStart() throws InterruptedException {
        // Idling sleep before starting
        sleep(7000);
        String parameters = getParameters();

        if (parameters != null) {
            String[] param = parameters.split("_");
            status = "Grabbing login info";

            username = param[0];
            password = param[1];
            tradeHandler = new TradeHandler(getBot());

            status = "Logging in to the game";
            loginEvent = new LoginEvent(username, password);
            disableAudioEvent = new DisableAudioEvent();

            getBot().addLoginListener(loginEvent);
            execute(loginEvent);
            status = "Logged in";
            sleep(5000);

            // Save a mule file
            muleSettings = new MuleSettings(this, (myPlayer().getName() + ".txt"));
            muleSettings.saveSettings(myPlayer().getName(), getWorlds().getCurrentWorld(), myPosition());

            // Set timer variables
            antiLogout = new Timer(270_000);
            closeScriptTimer = new Timer(30 * 60_000);
            startTime = System.currentTimeMillis();
            getCamera().toTop();
            execute(disableAudioEvent);

            // setup cache
            cacheRequests = new ArrayList<>();
            cacheRequests.add(myPlayer().getName());
        }
    }

    @Override
    public int onLoop() throws InterruptedException {
        if (!getClient().isLoggedIn()) {
            execute(loginEvent);
            return 200;
        }

        // If timer is done (30mins) close the client
        if (closeScriptTimer != null && !closeScriptTimer.isRunning()) {
            stop(true);
            return 600;
        }

        // Checks for last trade request & handles other logic
        requestor = getTrade().getLastRequestingPlayer() != null ? getTrade().getLastRequestingPlayer().getName() : null;
        if (tradeHandler.isTrading()) {
            requestor = null;
            status = "In trade";
            if (ticks > 50){
                if (tradeHandler.declineTrade())
                    sleep(2000);
            } else {
                tradeHandler.acceptFrom(null);
            }
        } else if (requestor != null && !cacheRequests.contains(requestor)) {
            status = "Trading " + requestor;
            if (tradeHandler.sentTradeRequest(requestor))
                cacheRequests.add(requestor);
        } else {
            if (antiLogout != null && !antiLogout.isRunning()) {
                status = "Staying logged in...";
                int id = random(0, 2) == 1 ? KeyEvent.VK_RIGHT : KeyEvent.VK_LEFT;
                getKeyboard().pressKey(id);
                sleep(random(50, 250));
                getKeyboard().releaseKey(id);
                antiLogout.reset();
            }
            status = "Waiting for new request...";
        }
        return 1000;
    }

    @Override
    public void onExit() throws InterruptedException {
        // Reset the file then close the client.
        muleSettings.reset();
        System.exit(0);
    }

    @Override
    public void onMessage(Message msg) throws InterruptedException {
        if (msg.getType() == Message.MessageType.GAME) {
            if (msg.getMessage().equals("Accepted trade.")) {
                log("Trade done.");
                sleep(3000);
                status = "Finished trade";
                tradesCompleted++;
                ticks = 0;
                requestor = null;
            }
        }
    }

    @Override
    public void onPaint(Graphics2D graphics) {
        graphics.setColor(Color.ORANGE);
        long realRunTime = System.currentTimeMillis() - this.startTime;
        graphics.drawString("Runtime: " + Time.format(realRunTime), 20, 50);
        graphics.drawString("Status: " + this.status, 20, 70);
        graphics.drawString("Trades Done: " + this.tradesCompleted, 20, 90);
        Point mP = getMouse().getPosition();
        graphics.drawLine(mP.x - 3, mP.y + 3, mP.x + 3, mP.y - 3);
        graphics.drawLine(mP.x + 3, mP.y + 3, mP.x - 3, mP.y - 3);
        graphics.drawString("(" + mP.x + "," + mP.y + ")", mP.x, mP.y);
    }
}
