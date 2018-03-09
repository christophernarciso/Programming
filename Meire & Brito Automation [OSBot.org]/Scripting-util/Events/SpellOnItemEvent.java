package com.excellentscripts.api.util.events;

import org.osbot.rs07.api.ui.MagicSpell;
import org.osbot.rs07.api.ui.Tab;
import org.osbot.rs07.event.Event;
import org.osbot.rs07.input.mouse.InventorySlotDestination;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.utility.ConditionalSleep;

public class SpellOnItemEvent extends Event {

	private MagicSpell spell;
	private String item;
	
	public SpellOnItemEvent(MagicSpell spell, String item) {
		this.spell = spell;
		this.item = item;
	}
	
	@Override
	public int execute() throws InterruptedException {
		if(getMagic().isSpellSelected()) {
			int slot = getInventory().getSlot(item);
			long count = getInventory().getAmount(item);
			if(getMouse().click(new InventorySlotDestination(getBot(), slot))) {
				if(new ConditionalSleep(2400, 200) {
					@Override
					public boolean condition() throws InterruptedException {
						return getInventory().getAmount(item) != count;
					}
				}.sleep()) {
					setFinished();
				}
				else {
					setFailed();
				}
			}
		}
		else if(getMagic().castSpell(spell)) {
			if(!new ConditionalSleep(2400, 200) {
				@Override
				public boolean condition() throws InterruptedException {
					return getTabs().getOpen() == Tab.INVENTORY;
				}
			}.sleep()) {
				setFailed();
			}
		}
		return MethodProvider.random(50, 150);
	}

}