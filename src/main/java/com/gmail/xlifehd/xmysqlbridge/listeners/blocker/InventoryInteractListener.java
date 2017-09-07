package com.gmail.xlifehd.xmysqlbridge.listeners.blocker;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryInteractEvent;

import com.gmail.xlifehd.xmysqlbridge.Main;

public class InventoryInteractListener implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onInventoryInteract ( InventoryInteractEvent e ) {
		
		//DEBUG
		//Main.getPlugin().getLogger().info("InventoryInteractEvent");
		
		if ( Main.getxUtils().isFrozen(e.getWhoClicked().getUniqueId())) {
			e.setCancelled(true);
		}
		
	}
	
}
