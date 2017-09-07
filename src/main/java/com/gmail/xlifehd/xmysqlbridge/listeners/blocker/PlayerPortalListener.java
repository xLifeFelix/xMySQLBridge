package com.gmail.xlifehd.xmysqlbridge.listeners.blocker;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;

import com.gmail.xlifehd.xmysqlbridge.Main;

public class PlayerPortalListener implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerPortal ( PlayerPortalEvent e ) {
		
		//DEBUG
		//Main.getPlugin().getLogger().info("PortalEvent");
		
		if ( Main.getxUtils().isFrozen(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
		}
		
	}
	
}
