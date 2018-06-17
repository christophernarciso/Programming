package org.base.scripting.api;

import org.osbot.rs07.api.ui.RS2Widget;
import org.osbot.rs07.event.Event;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.utility.ConditionalSleep;
//Author: EXPLV
public class MakeAllInterface extends MethodProvider {

    public boolean makeAll(final String itemName) throws InterruptedException {
        if (!isInterfaceOpen()) {
            return false;
        }

        int itemNameKeyboardNum = getItemNameKeyboardNum(itemName);
        log(itemNameKeyboardNum);

        if (itemNameKeyboardNum == -1) {
            itemNameKeyboardNum = 1; // Assume there is only 1 option
        }

        return makeAll(itemNameKeyboardNum);
    }

    public int getItemNameKeyboardNum(final String itemName) {
        RS2Widget itemButtonWidget = getWidgets().singleFilter(getWidgets().getAll(), widget -> widget.getSpellName().contains(">" + itemName + "<"));

        if (itemButtonWidget == null) {
            return -1;
        }

        RS2Widget itemNumberWidget = getWidgets().singleFilter(itemButtonWidget.getRootId(),
                rs2Widget -> rs2Widget.getWidth() == itemButtonWidget.getWidth()
                        && rs2Widget.getAbsY() > itemButtonWidget.getAbsY()
                        && rs2Widget.getMessage() != null
                        && rs2Widget.getMessage().matches("\\d+"));

        if (itemNumberWidget == null) {
            return -1;
        }

        return Integer.parseInt(itemNumberWidget.getMessage());
    }

    public boolean makeAll(final int keyboardShortcut) throws InterruptedException {
        if (!isInterfaceOpen()) {
            return false;
        }

        return execute(new Event() {
            @Override
            public int execute() throws InterruptedException {
                if (!isAllButtonSelected()) {
                    if (selectAllButton()) {
                        new ConditionalSleep(1000) {
                            @Override
                            public boolean condition() throws InterruptedException {
                                return isAllButtonSelected();
                            }
                        }.sleep();
                    }
                    return 0;
                }

                if (getKeyboard().typeString(String.valueOf(keyboardShortcut))) {
                    new ConditionalSleep(5_000, random(600, 1600)) {
                        @Override
                        public boolean condition() throws InterruptedException {
                            return !isInterfaceOpen();
                        }
                    }.sleep();
                    setFinished();
                }
                return 0;
            }
        }).hasFinished();
    }

    private boolean isInterfaceOpen() {
        return getWidgets().getWidgetContainingText("What would you like to", "How many bars would you like to smith?") != null;
    }

    private boolean isAllButtonSelected() {
        return !getWidgets().getWidgetContainingText("All").getMessage().equals("All");
    }

    private boolean selectAllButton() {
        return getWidgets().getWidgetContainingText("All").interact();
    }
}