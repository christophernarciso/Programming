package com.excellentscripts.api.util.events;

import com.excellentscripts.api.util.CachedWidget;
import com.excellentscripts.api.util.WidgetActionFilter;
import org.osbot.rs07.api.ui.Tab;
import org.osbot.rs07.event.Event;

public final class ToggleRoofsHiddenEvent extends Event {

    private final CachedWidget advancedOptionsWidget = new CachedWidget("Advanced options");
    private final CachedWidget displaySettingsWidget = new CachedWidget(new WidgetActionFilter("Display"));
    private final CachedWidget toggleRoofHiddenWidget = new CachedWidget(new WidgetActionFilter("Roof-removal"));

    @Override
    public final int execute() throws InterruptedException {
        if (Tab.SETTINGS.isDisabled(getBot())) {
            setFailed();
        } else if (getSettings().areRoofsEnabled()){
            setFinished();
        } else if (getTabs().getOpen() != Tab.SETTINGS) {
            getTabs().open(Tab.SETTINGS);
        } else if (!advancedOptionsWidget.get(getWidgets()).isPresent()) {
            displaySettingsWidget.get(getWidgets()).ifPresent(widget -> widget.interact());
        } else if (!toggleRoofHiddenWidget.get(getWidgets()).isPresent()) {
            advancedOptionsWidget.get(getWidgets()).get().interact();
        } else if (toggleRoofHiddenWidget.get(getWidgets()).get().interact()) {
            setFinished();
        }
        return 200;
    }
}