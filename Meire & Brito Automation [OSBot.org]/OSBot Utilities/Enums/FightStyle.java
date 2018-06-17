package com.excellentscripts.api.util.enums;

import com.excellentscripts.api.util.FConditionalSleep;
import org.osbot.rs07.api.ui.RS2Widget;
import org.osbot.rs07.api.ui.Tab;
import org.osbot.rs07.script.MethodProvider;

public enum FightStyle {
	ATT("Attack", 0, 3), STR("Strength", 1, 7), DEF("Defence", 3, 15);

	public static final FightStyle[] STYLES = FightStyle.values();

	private String name;
	private int child, id, parent = 593;

	FightStyle(String name, int id, int child) {
		this.name = name;
		this.child = child;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public int getChild() {
		return child;
	}

	public int getId() {
		return id;
	}

	public static FightStyle getFightStyle(MethodProvider p) {

		for (FightStyle f : FightStyle.STYLES)
			if (f.getId() == p.configs.get(43))
				return f;

		return null;
	}

	public void changeTo(MethodProvider p) {

		if (p.configs.get(43) == id)
			return;

		RS2Widget w = p.getWidgets().get(parent, child);
		if (w != null && w.isVisible()) {
			w.interact();
		} else {
			p.getTabs().open(Tab.ATTACK);
			new FConditionalSleep(() -> p.getTabs().getOpen().equals(Tab.ATTACK), 2000);
		}
	}

}