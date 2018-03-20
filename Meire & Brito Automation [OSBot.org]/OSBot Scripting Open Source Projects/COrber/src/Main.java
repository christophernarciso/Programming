package org.script.orb;

import org.osbot.rs07.api.ui.Message;
import org.osbot.rs07.listener.MessageListener;
import org.osbot.rs07.script.ScriptManifest;
import org.script.orb.data.Constants;
import org.script.orb.engine.Context;
import org.script.orb.engine.impl.AttackOption;
import org.script.orb.engine.impl.SettingType;
import org.script.orb.engine.impl.ToggleAttackOptionSettingEvent;
import org.script.orb.nodes.*;
import org.script.orb.util.RsBuddyPriceGrab;

import java.awt.*;
import java.text.NumberFormat;

@ScriptManifest(name = "COrber", info = "", version = 0.1, author = "Chris", logo = "")
public class Main extends Context implements MessageListener {

    private long startTime;

    @Override
    public void initialize() throws InterruptedException {
        Constants.GEAR.add("Staff of air");
        Constants.GEAR.add("Amulet of glory(");
        Constants.TRADEABLES.put("Air orb", 99999);
        Constants.tradingPlayer = getParameters();
        join(new MuleNode(this), new TravelToBankNode(this), new BankingNode(this),
                new TravelToObeliskNode(this), new OrbNode(this));
        ToggleAttackOptionSettingEvent event = new ToggleAttackOptionSettingEvent(SettingType.PLAYER, AttackOption.HIDDEN);
        execute(event);
        startTime = System.currentTimeMillis();
        Constants.orbPrice = RsBuddyPriceGrab.getSellingPrice(573);
    }

    @Override
    public void onMessage(Message message) throws InterruptedException {
        if (message.getType() == Message.MessageType.GAME) {
            if (message.getMessage().contains("Oh dear") || message.getMessage().contains("you are dead!")) {
                Constants.deaths++;
            }
        }
    }

    @Override
    public void terminate() {

    }

    @Override
    public void paintable(Graphics2D graphics) {
        graphics.drawString("Orbs: " + formatIntegers(Constants.orbsMade) + " (" + perHour(Constants.orbsMade) + " /hr)", 20, 90);
        graphics.drawString("Profit: " + formatIntegers(Constants.orbsMade * Constants.orbPrice) + " (" + perHour(Constants.orbsMade * Constants.orbPrice) + " /hr)", 20, 110);
    }

    private static String formatIntegers(int num) {
        return NumberFormat.getInstance().format(num);
    }

    private String perHour(int gained) {
        return formatIntegers((int) ((gained) * 3600000D / (System.currentTimeMillis() - startTime)));
    }

}

