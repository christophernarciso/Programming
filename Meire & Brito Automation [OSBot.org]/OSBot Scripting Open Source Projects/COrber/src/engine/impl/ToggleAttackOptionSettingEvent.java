package org.script.orb.engine.impl;

import org.osbot.rs07.api.ui.MediaType;
import org.osbot.rs07.api.ui.RS2Widget;
import org.osbot.rs07.api.ui.Tab;
import org.osbot.rs07.event.Event;

import java.awt.*;

/**
 * Created by Chris on 6/4/2017.
 * Using concept data from the user Dihn
 */
public class ToggleAttackOptionSettingEvent extends Event {

    private SettingType settingType;
    private AttackOption option;

    public ToggleAttackOptionSettingEvent(SettingType settingType, AttackOption attackOption){
        this.settingType = settingType;
        this.option = attackOption;
    }



    @Override
    public int execute() throws InterruptedException {
        if (isAttackOptionSet(settingType, option)){
            log("[DEBUG] " + option.getOption() + " is already enabled.");
            setFinished();
        } else if (Tab.SETTINGS.isDisabled(getBot())) { //Setting tab disabled.
            log("[DEBUG] Settings can not be accessed.");
            setFailed();
        } else if (getTabs().getOpen() != Tab.SETTINGS) { //Open settings
            log("[DEBUG] Opening Settings");
            getTabs().open(Tab.SETTINGS);
        }  else if(getControlsWidgetEnabled() == null || !getControlsWidgetEnabled().isVisible()) { //Control button not enabled
            log("[DEBUG] Opening controls");
            getGameSettingsControlsWidget().interact();
        } else if ((getDropDownArrowEnabled() == null || !getDropDownArrowEnabled().isVisible())){ //Open the dropdown menu
            log("[DEBUG] Clicking to open menu");
            getClickableDropDownBox().interact();
        } else {
            if (setAttackOption(option)) { //Select the option
                log("[DEBUG] Selected: " + option.getOption());
                Sleep.sleepUntil(() -> isAttackOptionSet(settingType, option), 3000);
                setFinished();
            }
        }
        return 600;
    }

    private RS2Widget getGameSettingsControlsWidget() { //Open the controls menu by clicking the button
        return getWidgets().singleFilter(getWidgets().getAll(), w -> w != null && w.getType() == 5 && w.getEnabledMediaType() == MediaType.MODEL
                && w.getSpriteIndex1() == 761 && w.getInteractActions()[0].equals("Controls"));
    }

    private RS2Widget getControlsWidgetEnabled() { //Check if the controls button is enabled
        return getWidgets().singleFilter(getWidgets().getAll(), w -> w != null && w.getType() == 5 && w.getEnabledMediaType() == MediaType.MODEL
                && w.getSpriteIndex1() == 762 && w.getInteractActions()[0].equals("Controls"));
    }

    private RS2Widget getDropDownArrowEnabled() { //Check if the menu arrow is enabled.
        return getWidgets().singleFilter(getWidgets().getAll(), w -> w != null && w.getType() == 5 && w.getEnabledMediaType() == MediaType.MODEL
                && w.getSpriteIndex1() == 773 && w.getBounds().contains(new Point(715, (settingType == SettingType.PLAYER ? 368 : 406))));
    }

    private RS2Widget getClickableDropDownBox() { //Click to open the full menu
        return getWidgets().singleFilter(getWidgets().getAll(), w -> w != null && w.getType() == 4 && w.getEnabledMediaType() == MediaType.MODEL
                && w.getBounds().contains(new Point(633, (settingType == SettingType.PLAYER ? 368 : 406))));
    }

    private boolean isAttackOptionSet(SettingType settingType, AttackOption attackOption) { //If the attack option is enabled
        return (getConfigs().get(settingType.getConfigValue()) & attackOption.ordinal()) == attackOption.ordinal();
    }

    private boolean setAttackOption(AttackOption attackOption) { //Select the option
        RS2Widget selection = getWidgets().getWidgetContainingText(attackOption.getOption());
        return selection != null && selection.isVisible() && selection.interact();
    }

}
