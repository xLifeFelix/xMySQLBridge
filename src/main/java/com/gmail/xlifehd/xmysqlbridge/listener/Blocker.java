package com.gmail.xlifehd.xmysqlbridge.listener;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.player.PlayerBucketEvent;
import org.bukkit.event.player.PlayerChangedMainHandEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupArrowEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerShearEntityEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import com.gmail.xlifehd.xmysqlbridge.Main;

public class Blocker implements Listener{
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onCommand(PlayerCommandPreprocessEvent e) {
		Main.getPlugin().getLogger().info(Main.getPrefix("info") + "CommandPreProcessEvent");
		if ( Main.getPlugin().getxUtils().isFrozen(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerMove( PlayerMoveEvent e ) {
		Main.getPlugin().getLogger().info(Main.getPrefix("info") + "CommandPreProcessEvent");
		if ( Main.getPlugin().getxUtils().isFrozen(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST) 
	public void onPlayerDropItem ( PlayerDropItemEvent e ) {
		Main.getPlugin().getLogger().info(Main.getPrefix("info") + "CommandPreProcessEvent");
		if ( Main.getPlugin().getxUtils().isFrozen(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerEditBook ( PlayerEditBookEvent e) {
		Main.getPlugin().getLogger().info(Main.getPrefix("info") + "CommandPreProcessEvent");
		if ( Main.getPlugin().getxUtils().isFrozen(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
		}
	}
	
//	@EventHandler(priority = EventPriority.HIGHEST) 
//	public void onPlayerEggThrow ( PlayerEggThrowEvent e ) {
//		if ( Main.getPlugin().getxUtils().isFrozen(e.getPlayer().getUniqueId())) {
//			e.
//		}
//	}
	
//	@EventHandler(priority = EventPriority.HIGHEST) 
//	public void onPlayerPickupArrow ( PlayerPickupArrowEvent e ) {
//		if ( Main.getPlugin().getxUtils().isFrozen(e.getPlayer().getUniqueId())) {
//			e.setCancelled(true);
//		}
//	}
	
	@EventHandler(priority = EventPriority.HIGHEST) 
	public void onEntityrPickupItem ( EntityPickupItemEvent e ) {
		Main.getPlugin().getLogger().info(Main.getPrefix("info") + "CommandPreProcessEvent");
		if ( Main.getPlugin().getxUtils().isFrozen(((OfflinePlayer) e).getPlayer().getUniqueId())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerSwapHandItems ( PlayerSwapHandItemsEvent e ) {
		Main.getPlugin().getLogger().info(Main.getPrefix("info") + "CommandPreProcessEvent");
		if ( Main.getPlugin().getxUtils().isFrozen(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST) 
	public void onPlayerShearEntity ( PlayerShearEntityEvent e ) {
		Main.getPlugin().getLogger().info(Main.getPrefix("info") + "CommandPreProcessEvent");
		if ( Main.getPlugin().getxUtils().isFrozen(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerPortal ( PlayerPortalEvent e ) {
		Main.getPlugin().getLogger().info(Main.getPrefix("info") + "CommandPreProcessEvent");
		if ( Main.getPlugin().getxUtils().isFrozen(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST) 
	public void onEntityPickupItem ( EntityPickupItemEvent e) {
		Main.getPlugin().getLogger().info(Main.getPrefix("info") + "CommandPreProcessEvent");
		if ( Main.getPlugin().getxUtils().isFrozen(((OfflinePlayer) e).getPlayer().getUniqueId())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST) 
	public void onPlayerItemHeld ( PlayerItemHeldEvent e ) {
		Main.getPlugin().getLogger().info(Main.getPrefix("info") + "CommandPreProcessEvent");
		if ( Main.getPlugin().getxUtils().isFrozen(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST) 
	public void onPlayerItemConsume ( PlayerItemConsumeEvent e ) {
		Main.getPlugin().getLogger().info(Main.getPrefix("info") + "CommandPreProcessEvent");
		if ( Main.getPlugin().getxUtils().isFrozen(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST) 
	public void onPlayerInteract ( PlayerInteractEvent e ) {
		Main.getPlugin().getLogger().info(Main.getPrefix("info") + "CommandPreProcessEvent");
		if ( Main.getPlugin().getxUtils().isFrozen(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST) 
	public void onPlayerInteractEntity ( PlayerInteractEntityEvent e ) {
		Main.getPlugin().getLogger().info(Main.getPrefix("info") + "CommandPreProcessEvent");
		if ( Main.getPlugin().getxUtils().isFrozen(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST) 
	public void onPlayerInteractAtEntity ( PlayerInteractAtEntityEvent e ) {
		Main.getPlugin().getLogger().info(Main.getPrefix("info") + "CommandPreProcessEvent");
		if ( Main.getPlugin().getxUtils().isFrozen(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST) 
	public void onPlayerFish ( PlayerFishEvent e ) {
		Main.getPlugin().getLogger().info(Main.getPrefix("info") + "CommandPreProcessEvent");
		if ( Main.getPlugin().getxUtils().isFrozen(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerBucket ( PlayerBucketEvent e ) {
		Main.getPlugin().getLogger().info(Main.getPrefix("info") + "CommandPreProcessEvent");
		if ( Main.getPlugin().getxUtils().isFrozen(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
		}
	}
	
	
	@EventHandler(priority = EventPriority.HIGHEST) 
	public void onPlayerBedEnter ( PlayerBedEnterEvent e ) {
		Main.getPlugin().getLogger().info(Main.getPrefix("info") + "CommandPreProcessEvent");
		if ( Main.getPlugin().getxUtils().isFrozen(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST) 
	public void onPlayerArmorStandManipulate ( PlayerArmorStandManipulateEvent e ) {
		Main.getPlugin().getLogger().info(Main.getPrefix("info") + "CommandPreProcessEvent");
		if ( Main.getPlugin().getxUtils().isFrozen(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST) 
	public void onPlayerAnimation ( PlayerAnimationEvent e ) {
		Main.getPlugin().getLogger().info(Main.getPrefix("info") + "CommandPreProcessEvent");
		if ( Main.getPlugin().getxUtils().isFrozen(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
		}
	}
	
}