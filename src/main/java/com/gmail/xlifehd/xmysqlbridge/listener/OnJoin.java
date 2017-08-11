package com.gmail.xlifehd.xmysqlbridge.listener;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.gmail.xlifehd.xmysqlbridge.Main;
import com.gmail.xlifehd.xmysqlbridge.mysql.LoadHandler;

public class OnJoin implements Listener {
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		final Player player = event.getPlayer();
		Main.getPlugin().getxUtils().freezePlayer( player );
		
		Runnable r = new Runnable() {
			
			public void run() {
				FileConfiguration config = Main.getPlugin().getConfig();
				LoadHandler loadHandler = new LoadHandler(player.getUniqueId());
				
				if ( config.getBoolean("table.health.enabled") ) {
					Double health = loadHandler.getHealth();
					if ( health != null ) {
						player.setHealth(health);
					}
				}
				
				if ( config.getBoolean("table.hunger.enabled") ) {
					Number[] data = loadHandler.getHunger();
					if ( data != null ) {
						player.setFoodLevel(data[0].intValue());
						player.setSaturation(data[1].floatValue());
					}
				}
				
				if ( config.getBoolean("table.effects.enabled") ) {
					//TODO SET EFFECTS
					//loadHandler.getEffects();
				}
				
				if ( config.getBoolean("table.location.enabled") ) {
					Location loc = loadHandler.getLocation();
					if ( loc != null ) {
						player.teleport(loc);
					}
				}
				
				if ( config.getBoolean("table.experience.enabled") ) {
					Number[] data = loadHandler.getExperience();
					if ( data != null ) {
						player.setTotalExperience(data[0].intValue());
						player.setLevel(data[1].intValue());
						player.setExp(data[2].floatValue());
					}
				}
				
				if ( config.getBoolean("table.money.enabled") ) {
					Double money = loadHandler.getMoney();
					if ( money != null ) {
						double difference = money - Main.getEconomy().getBalance(player);
						if ( difference > 0 ) {
							Main.getEconomy().depositPlayer(player, difference);
						} else {
							Main.getEconomy().withdrawPlayer(player, -difference);
						}
					}
				}
				
				if ( config.getBoolean("table.inventory.enabled") ) {
					ItemStack[][] contents = loadHandler.getInventory();
					
					if ( contents != null ) {
						PlayerInventory playerInventory = player.getInventory();
						playerInventory.setStorageContents(contents[0]);
						playerInventory.setArmorContents(contents[1]);
						playerInventory.setItemInOffHand(contents[2][0]);
					}
				}
				
				if ( config.getBoolean("table.enderchest.enabled") ) {
					ItemStack[] enderchest = loadHandler.getEnderchest();
					if ( enderchest != null ) {
						player.getEnderChest().setStorageContents(enderchest);
					}
				}
				
				if ( config.getBoolean("table.achievements.enabled") ) {
					//TODO ACHIEVEMENTS
				}
				
				Main.getPlugin().getxUtils().unfreezePlayer( player );
				
			}//Run end
			
		};//Runnable end
		
		Runnable safeUnfreeze = new Runnable() {
			
			public void run() {
				Main.getPlugin().getxUtils().unfreezePlayer(player);
			}
			
		};
		
		Main.getPlugin().getServer().getScheduler().runTaskLaterAsynchronously(Main.getPlugin(), r, Main.getPlugin().getConfig().getLong("loadDelayinTicks"));
		Main.getPlugin().getServer().getScheduler().runTaskLaterAsynchronously(Main.getPlugin(), safeUnfreeze, 20*30); //30 secs
	}
	
}
