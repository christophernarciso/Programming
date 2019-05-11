package util.customevents;


import org.osbot.rs07.api.Client;
import org.osbot.rs07.api.ui.RS2Widget;
import org.osbot.rs07.constants.ResponseCode;
import org.osbot.rs07.event.Event;
import org.osbot.rs07.input.mouse.RectangleDestination;
import org.osbot.rs07.listener.LoginResponseCodeListener;
import org.osbot.rs07.utility.ConditionalSleep;

import java.awt.*;

// Original Author: Explv | Modified By: Chris
public final class LoginEvent extends Event implements LoginResponseCodeListener {

    private final String username, password;
    private boolean members;

    public LoginEvent(final String username, final String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public final int execute() throws InterruptedException {
        log(getClient().getLoginUIState());
        if (getClient().isLoggedIn() && getLobbyButton() == null){
            setFinished();
        } else if (getClient().getLoginState() != Client.LoginState.LOGGED_OUT && getLobbyButton() != null) {
            setMembers(getWidgets().containingText("You are not a member.") == null);
            clickLobbyButton();
        } else if (isOnWorldSelectorScreen()) {
            cancelWorldSelection();
        } else if (!isPasswordEmpty()) {
            clickCancelLoginButton();
        } else {
            login();
        }
        return random(100, 150);
    }

    private boolean isOnWorldSelectorScreen() {
        return getColorPicker().isColorAt(50, 50, Color.BLACK);
    }

    private void cancelWorldSelection() {
        if (getMouse().click(new RectangleDestination(getBot(), 712, 8, 42, 8))) {
            new ConditionalSleep(3000) {
                @Override
                public boolean condition() throws InterruptedException {
                    return !isOnWorldSelectorScreen();
                }
            }.sleep();
        }
    }

    private boolean isPasswordEmpty() {
        return !getColorPicker().isColorAt(350, 260, Color.WHITE);
    }

    private boolean clickCancelLoginButton() {
        return getMouse().click(new RectangleDestination(getBot(), 398, 308, 126, 27));
    }

    private void login() throws InterruptedException {
        switch (getClient().getLoginUIState()) {
            case 0:
                clickExistingUsersButton();
                break;
            case 1:
                clickLoginButton();
                break;
            case 2:
                enterUserDetails();
                break;
            case 3:
                clickTryAgainButton();
                sleep(2000);
                clickCancelLoginButton();
                sleep(2000);
                break;
            case 4:
                log("Authenticator message visible backing out");
                sleep(2000);
                getMouse().click(442, 319, false);
                sleep(2000);
                break;
            case 12:
                log("disabled message up backing out");
                sleep(2000);
                getMouse().click(380, 330, false);
                sleep(2000);
                clickCancelLoginButton();
                sleep(2000);
                break;
        }
    }

    private void clickExistingUsersButton() {
        getMouse().click(new RectangleDestination(getBot(), 400, 280, 120, 20));
    }

    private void clickLoginButton() {
        getMouse().click(new RectangleDestination(getBot(), 240, 310, 120, 20));
    }

    private void enterUserDetails() throws InterruptedException {
        try {
            sleep(random(2000, 4000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (!getKeyboard().typeString(username)) {
            setFailed();
            return;
        }

        if (!getKeyboard().typeString(password)) {
            setFailed();
            return;
        }

        new ConditionalSleep(30_000) {
            @Override
            public boolean condition() throws InterruptedException {
                return getLobbyButton() != null && getLobbyButton().isVisible()
                        || getClient().getLoginUIState() == 3
                        || isDisabledMessageVisible()
                        || isBadLoginStatus()
                        || isAuthenticatorVisible();
            }
        }.sleep();

        if (!getClient().isLoggedIn()) {
            setFailed();
        }
    }

    private boolean clickTryAgainButton() {
        return getMouse().click(new RectangleDestination(getBot(), 318, 262, 130, 26));
    }

    private boolean isDisabledMessageVisible() {
        //return getColorPicker().isColorAt(483, 195, Color.YELLOW);
        return getColorPicker().isColorAt(392, 327, Color.WHITE);
    }

    private boolean isAuthenticatorVisible() {
        return getColorPicker().isColorAt(275, 275, Color.WHITE);
    }

    private boolean isBadLoginStatus() {
        return getColorPicker().isColorAt(483, 195, Color.YELLOW);
    }

    private boolean hasMembership(){
        return this.members;
    }

    private void setMembers(boolean members){
        this.members = members;
    }

    private void clickLobbyButton() {
        if (getLobbyButton().interact()) {
            new ConditionalSleep(10_000) {
                @Override
                public boolean condition() throws InterruptedException {
                    return getLobbyButton() == null;
                }
            }.sleep();
        }
    }

    public RS2Widget getLobbyButton() {
        return getWidgets().getWidgetContainingText("CLICK HERE TO PLAY");
    }

    @Override
    public final void onResponseCode(final int responseCode) throws InterruptedException {

        if (ResponseCode.isDisabledError(responseCode)) {
            log("Login failed, account is disabled");
            getBot().getScriptExecutor().stop(true);
            return;
        }

        if (ResponseCode.isConnectionError(responseCode)) {
            log("Connection error, attempts exceeded");
            log("Sleeping for 5 minutes.");
            sleep(300_000);
            setFailed();
            return;
        }

        if (responseCode == 9) {
            log("Connection error 9, login attempts exceeded");
            log("Sleeping for 5 minutes.");
            sleep(300_000);
            setFailed();
            return;
        }
    }
}