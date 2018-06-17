package com.excellentscripts.api.util.enums;

import org.osbot.rs07.api.ui.MagicSpell;
import org.osbot.rs07.api.ui.Spells.NormalSpells;

public enum AutocastSpell {

	WIND_STRIKE(NormalSpells.WIND_STRIKE, 3),
    WATER_STRIKE(NormalSpells.WATER_STRIKE, 5),
	EARTH_STRIKE(NormalSpells.EARTH_STRIKE, 7),
	FIRE_STRIKE(NormalSpells.FIRE_STRIKE, 9),
	WIND_BOLT(NormalSpells.WIND_BOLT, 11),
	WATER_BOLT(NormalSpells.WATER_BOLT, 13),
	EARTH_BOLT(NormalSpells.EARTH_BOLT, 15),
	FIRE_BOLT(NormalSpells.FIRE_BOLT, 17),
	WIND_BLAST(NormalSpells.WIND_BLAST, 19),
	WATER_BLAST(NormalSpells.WATER_BLAST, 21),
	EARTH_BLAST(NormalSpells.EARTH_BLAST, 23),
	FIRE_BLAST(NormalSpells.FIRE_BLAST, 25),
	WIND_WAVE(NormalSpells.WIND_WAVE, 27),
	WATER_WAVE(NormalSpells.WATER_WAVE, 29),
	EARTH_WAVE(NormalSpells.EARTH_WAVE, 31),
	FIRE_WAVE(NormalSpells.FIRE_WAVE, 33);
	
	private MagicSpell spell;
	private int configValue;
	
	private AutocastSpell(MagicSpell spell, int configValue) {
		this.spell = spell;
		this.configValue = configValue;
	}
	
	public MagicSpell getSpell() {
		return spell;
	}
	
	public int getConfigValue() {
		return configValue;
	}
	
	public static AutocastSpell forSpell(MagicSpell spell) {
		for (AutocastSpell a : values()) {
			if(a.spell == spell) return a;
		}
		return null;
	}
	
	public static AutocastSpell forConfigValue(int configValue) {
		for (AutocastSpell a : values()) {
			if(a.configValue == configValue) return a;
		}
		return null;
	}
	
}
