package org.script.orb.engine;

import org.osbot.Constants;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.*;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.api.util.LocalPathFinder;
import org.osbot.rs07.event.InteractionEvent;
import org.osbot.rs07.event.WalkingEvent;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.utility.Condition;
import org.script.orb.engine.impl.Sleep;
import org.script.orb.engine.impl.Time;
import org.script.orb.util.TradeHandler;
import org.script.orb.util.WildernessHandler;

import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.function.BooleanSupplier;
import java.util.stream.Stream;

/**
 * Created by Sinatra_PC on 02/17/18.
 */
public abstract class Context extends Script {

    private long startTime;
    private LinkedList<Node> nodes = new LinkedList<Node>();
    private String status = "N/A";
    private Point mP;
    /* DO NOT REMOVE THIS VERIFICATION IF YOU ARE NOT THE ORIGINAL MAKER */
    private String[] verified = {"Chris"};
    private Thread wildernessThread;
    private TradeHandler tradeHandler;


    public void onStart() throws InterruptedException {
        this.startTime = System.currentTimeMillis();
        getExperienceTracker().startAll();
        if (!verified()) System.exit(0);
        this.initialize();
        this.wildernessThread = new Thread(new WildernessHandler(getBot()));
        this.wildernessThread.start();
        this.tradeHandler = new TradeHandler(this);
    }

    @Override
    public int onLoop() throws InterruptedException {
        if (getDialogues().isPendingContinuation() && getDialogues().clickContinue()) {
            status = "Handling dialogue";
            Sleep.sleepUntil(() -> !getDialogues().isPendingContinuation(), 1500);
        }
        for (Node n : nodes) {
            if (n.validate()) {
                this.status = n.status();
                n.execute();
                break;
            } else this.status = "IDLE";
        }
        return 600;
    }

    @Override
    public void onExit() throws InterruptedException {
        super.onExit();
        this.nodes.clear();
        if (wildernessThread != null && wildernessThread.isAlive())
            wildernessThread.interrupt();
        this.terminate();
    }

    @Override
    public void onPaint(Graphics2D graphics) {
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("Arial", 0, 10));
        this.paintable(graphics);
        long realRunTime = System.currentTimeMillis() - this.startTime;
        graphics.drawString("" + Time.format(realRunTime), 203, 374); //runtime
        graphics.drawString("" + this.status + " ", 180, 394); //status
        mP = getMouse().getPosition();
        graphics.drawLine(mP.x - 3, mP.y + 3, mP.x + 3, mP.y - 3);
        graphics.drawLine(mP.x + 3, mP.y + 3, mP.x - 3, mP.y - 3);
        graphics.drawString("(" + mP.x + "," + mP.y + ")", mP.x, mP.y);
    }

    public abstract void initialize() throws InterruptedException;

    public abstract void terminate();

    public abstract void paintable(Graphics2D graphics);



    protected void join(Node... nodes) {
        for (Node n : nodes) {
            if (n != null && !this.nodes.contains(n)) {
                this.nodes.add(n);
            }
        }
    }

    private boolean verified(){
        for (String s : verified){
            if (getClient().getUsername().equals(s))
                return true;
        }
        log("Sorry you are not verified to access this script. " + getClient().getUsername());
        return false;
    }

    public Stream<GroundItem> getGroundItemStream(){
        return getGroundItems().getAll().stream();
    }

    public Stream<NPC> getNPCStream(){
        return getNpcs().getAll().stream();
    }

    public Stream<Player> getPlayerStream(){
        return getPlayers().getAll().stream();
    }



    public void eat() {
        if (getBank().isOpen())
            getBank().close();
        Item food = getInventory().getItem(i -> i != null && i.hasAction("Eat"));
        if (food != null && food.interact("Eat"))
            Sleep.sleepUntil(() -> myPlayer().getAnimation() == 829, 2000, 2000);
    }

    public int ourHealthPercent(){
        int currHealth = getSkills().getDynamic(Skill.HITPOINTS);
        int maxHealth = getSkills().getStatic(Skill.HITPOINTS);
        return ((currHealth*100) / maxHealth);
    }

    public boolean walkToDestination(final Position dest, final BooleanSupplier bc, final boolean walkExact){
        WalkingEvent walkingEvent = new WalkingEvent(dest);
        walkingEvent.setEnergyThreshold(10);
        walkingEvent.setBreakCondition(new Condition() {
            @Override
            public boolean evaluate() {
                return bc.getAsBoolean();
            }
        });
        if (walkExact)
            walkingEvent.setMinDistanceThreshold(0);

        return execute(walkingEvent).hasFinished();
    }

    public boolean walkToDestination(final Position[] positions, final BooleanSupplier bc, final boolean walkExact){
        WalkingEvent walkingEvent = new WalkingEvent();
        walkingEvent.setPath(new LinkedList<>(Arrays.asList(positions)));
        walkingEvent.setBreakCondition(new Condition() {
            @Override
            public boolean evaluate() {
                return bc.getAsBoolean();
            }
        });
        if (walkExact)
            walkingEvent.setMinDistanceThreshold(0);
        walkingEvent.setMiniMapDistanceThreshold(0);

        return execute(walkingEvent).hasFinished();
    }

    public boolean walkToDestination(LinkedList<Position> path, BooleanSupplier bc, boolean walkExact) {
        WalkingEvent walkingEvent = new WalkingEvent();
        walkingEvent.setPath(path);
        walkingEvent.setBreakCondition(new Condition() {
            @Override
            public boolean evaluate() {
                return bc.getAsBoolean();
            }
        });
        if (walkExact)
            walkingEvent.setMinDistanceThreshold(0);
        walkingEvent.setMiniMapDistanceThreshold(0);

        return execute(walkingEvent).hasFinished();
    }

    public boolean aStarWalkToDestination(final Position dest){
        LocalPathFinder pathFinder = new LocalPathFinder(getBot());
        LinkedList<Position> path = pathFinder.findPath(dest);
        if (pathFinder.foundPath()){
            log("Found path [" + (path != null && path.size() > 0 ? path.size() : 0) + "] tiles");
            WalkingEvent event = new WalkingEvent();
            event.setPath(path);
            event.setEnergyThreshold(org.script.orb.data.Constants.LADDER_PATHING_AREA.contains(myPosition())?2:10);
            event.setMiniMapDistanceThreshold(0);
            event.setMinDistanceThreshold(2);
            event.setOperateCamera(false);
            return execute(event).hasFinished();
        }
        log("Could not load a path. try again");
        return false;
    }

    public boolean sleepUntil(final BooleanSupplier supplier, int timeout){
        return Sleep.sleepUntil(supplier, timeout);
    }

    public boolean sleepUntil(final BooleanSupplier supplier, int timeout, int sleepTime){
        return Sleep.sleepUntil(supplier, timeout, sleepTime);
    }

    public boolean interactEntity(final String entity, final String option) { //Interacts with a given ENTITY with ACTION
        Entity entityToInteract = getNpcs().closest(en -> en.hasAction(option) && en.getName().equals(entity));
        if (entityToInteract == null){
            entityToInteract = getObjects().closest(en -> en.hasAction(option) && en.getName().equals(entity));
        }

        if (entityToInteract == null)
            return false;

        InteractionEvent interactionEvent = new InteractionEvent(entityToInteract, option);
        interactionEvent.setMaximumAttempts(1);
        return execute(interactionEvent).hasFinished();
    }

    public TradeHandler getTradeHandler() {
        return tradeHandler;
    }
}
