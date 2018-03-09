package org.base.scripting.data;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

/*Author credit: Botre*/
public enum SmeltingBar {

	BRONZE("Bronze bar", 1, 1),
	IRON("Iron bar", 15, 2),
	SILVER("Silver bar", 20, 3),
	STEEL("Steel bar", 30, 4),
	GOLD("Gold bar", 40, 5),
	MITHRIL("Mithril bar", 50, 6),
	ADAMANTITE("Adamantite bar", 70, 7),
	RUNITE("Runite bar", 85, 8);
	
	private static final Map<SmeltingBar, Map<Ore, Integer>> ORE_RECIPES = Collections.unmodifiableMap(initOres());
	
	private String barName;
	private int requiredSmithingLevel, key;
	
	SmeltingBar(String barName, int requiredSmithingLevel, int key) {
		this.barName = barName;
		this.key = key;
		this.requiredSmithingLevel = requiredSmithingLevel;
	}
	
	public String getName() {
		return barName;
	}

	public String getBarSelectionString() {
		return this == ADAMANTITE || this == RUNITE ?
				barName.replace("ite bar", this == RUNITE ? "e bar" : " bar") : barName;
	}

	public String getBarActionString() {
		return "Smelt X " + getBarSelectionString();
	}
	
	public int getRequiredSmithingLevel() {
		return requiredSmithingLevel;
	}
	
	public Map<Ore, Integer> getOres() {
		return ORE_RECIPES.get(this);
	}
	
	@Override
	public String toString() {
		return getName();
	}

	private static Map<SmeltingBar, Map<Ore, Integer>> initOres() {
		Map<SmeltingBar, Map<Ore, Integer>> map = new EnumMap<>(SmeltingBar.class);
		for (SmeltingBar bar : values()) {
			Map<Ore, Integer> ores = new EnumMap<>(Ore.class);
			switch (bar) {
			case BRONZE:
				ores.put(Ore.COPPER, 1);
				ores.put(Ore.TIN, 1);
				break;
			case IRON:
				ores.put(Ore.IRON, 1);
				break;
			case SILVER:
				ores.put(Ore.SILVER, 1);
				break;
			case STEEL:
				ores.put(Ore.IRON, 1);
				ores.put(Ore.COAL, 2);
				break;
			case GOLD:
				ores.put(Ore.GOLD, 1);
				break;
			case MITHRIL:
				ores.put(Ore.MITHRIL, 1);
				ores.put(Ore.COAL, 4);
				break;
			case ADAMANTITE:
				ores.put(Ore.ADAMANTITE, 1);
				ores.put(Ore.COAL, 6);
				break;
			case RUNITE:
				ores.put(Ore.RUNITE, 1);
				ores.put(Ore.COAL, 8);
				break;
			}
			map.put(bar, ores);
		}
		return map;
	}

	public int getKey() {
		return key;
	}
}