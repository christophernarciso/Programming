package util.gamehandlers;

import org.osbot.rs07.Bot;
import org.osbot.rs07.api.model.Player;
import org.osbot.rs07.api.ui.RS2Widget;
import org.osbot.rs07.utility.ConditionalSleep;

import java.util.HashMap;

/**
 * Created by Chris on 05/06/2019.
 */
public class TradeHandler {

    private Bot context;

    public TradeHandler(Bot context){
        this.context = context;
    }

    public void acceptFrom(final String name) throws InterruptedException {
        if (name != null){
            sentTradeRequest(name);
        }

        if (isTrading() && context.getMethods().getTrade().isFirstInterfaceOpen()) {
            if (acceptTrade()){
                new ConditionalSleep(3000) {
                    @Override
                    public boolean condition() throws InterruptedException {
                        return context.getMethods().getTrade().isSecondInterfaceOpen();
                    }
                }.sleep();
            }
        } else if (isTrading() && context.getMethods().getTrade().isSecondInterfaceOpen()) {
            if (acceptTrade()){
                new ConditionalSleep(3000) {
                    @Override
                    public boolean condition() throws InterruptedException {
                        return !context.getMethods().getTrade().isSecondInterfaceOpen();
                    }
                }.sleep();
            }
        }
    }

    public void tradeTo(String name, HashMap<String, Integer> offers) {
        sentTradeRequest(name);

        if (isTrading() && context.getMethods().getTrade().isFirstInterfaceOpen()) {
            if (!tradeOfferMatches(offers)) {
                for (String item : offers.keySet()) {
                    if (!context.getMethods().getTrade().getOurOffers().contains(item)) {
                        int amount = offers.get(item);
                        if (context.getMethods().getTrade().offer(item, amount)) {
                            context.getMethods().log("Added trade offer: " + item + " x" + amount);
                            new ConditionalSleep(3000, 1000) {
                                @Override
                                public boolean condition() {
                                    return context.getMethods().getTrade().getOurOffers().contains(item);
                                }
                            }.sleep();
                        }
                    }
                }
            } else if (acceptTrade()){
                new ConditionalSleep(3000) {
                    @Override
                    public boolean condition() throws InterruptedException {
                        return context.getMethods().getTrade().isSecondInterfaceOpen();
                    }
                }.sleep();
            }
        } else if (isTrading() && context.getMethods().getTrade().isSecondInterfaceOpen()) {
            if (acceptTrade()){
                new ConditionalSleep(3000) {
                    @Override
                    public boolean condition() throws InterruptedException {
                        return !context.getMethods().getTrade().isSecondInterfaceOpen();
                    }
                }.sleep();
            }
        }
    }

    public void tradeTo(String name, String offer) {
        sentTradeRequest(name);

        if (isTrading() && context.getMethods().getTrade().isFirstInterfaceOpen()) {
            if (!context.getMethods().getTrade().getOurOffers().contains(offer)) {
                if (context.getMethods().getTrade().offerAll(offer)) {
                    context.getMethods().log("Added trade offer: " + offer);
                    new ConditionalSleep(3000, 1000) {
                        @Override
                        public boolean condition() {
                            return context.getMethods().getTrade().getOurOffers().contains(offer);
                        }
                    }.sleep();
                }
            } else if (acceptTrade()){
                new ConditionalSleep(3000) {
                    @Override
                    public boolean condition() throws InterruptedException {
                        return context.getMethods().getTrade().isSecondInterfaceOpen();
                    }
                }.sleep();
            }
        } else if (isTrading() && context.getMethods().getTrade().isSecondInterfaceOpen()) {
            if (acceptTrade()){
                new ConditionalSleep(5000) {
                    @Override
                    public boolean condition() throws InterruptedException {
                        return !context.getMethods().getTrade().isSecondInterfaceOpen();
                    }
                }.sleep();
            }
        }
    }

    public boolean sentTradeRequest(String name) {
        Player player = context.getMethods().getPlayers().closest(name.replaceAll(" ", "\u00A0"));
        if (player != null && !isTrading()) {
            if (player.interact("Trade with")) {
                return new ConditionalSleep(5000) {
                    @Override
                    public boolean condition() throws InterruptedException {
                        return isTrading();
                    }
                }.sleep();
            }
        }
        return false;
    }

    public boolean isTrading() {
        return context.getMethods().getTrade().isCurrentlyTrading()
                || context.getMethods().getTrade().isFirstInterfaceOpen()
                || context.getMethods().getTrade().isSecondInterfaceOpen();
    }


    private boolean tradeOfferMatches(HashMap<String, Integer> itemSet) {
        for (String item : itemSet.keySet()) {
            if (!context.getMethods().getInventory().contains(item)) continue;

            if (isTrading() && context.getMethods().getTrade().getOurOffers().getItem(item) == null) {
                context.getMethods().log("Trade Offer Missing: " + item);
                return false;
            }
        }
        return true;
    }

   public int getFreeSlots(){
        if (!context.getMethods().getTrade().isFirstInterfaceOpen())
            return 0;

        RS2Widget slotDisplayWidget = context.getMethods().getWidgets().getWidgetContainingText(335, "free inventory slots.");

        if (slotDisplayWidget != null && slotDisplayWidget.isVisible()){
            String message = slotDisplayWidget.getMessage().replaceAll("[^\\d]", "").trim();

            if (!message.contains("no"))
                return Integer.parseInt(message);
        }

        return 0;
    }

    public boolean acceptTrade(){
        int root = context.getMethods().getTrade().isFirstInterfaceOpen() ? 335 : 334;
        RS2Widget acceptButton = context.getMethods().getWidgets().getWidgetContainingText(root, "Accept");

        return acceptButton != null && acceptButton.isVisible() && acceptButton.interact();
    }

    public boolean declineTrade(){
        int root = context.getMethods().getTrade().isFirstInterfaceOpen() ? 335 : 334;
        RS2Widget acceptButton = context.getMethods().getWidgets().getWidgetContainingText(root, "Decline");

        return acceptButton != null && acceptButton.isVisible() && acceptButton.interact();
    }

}
