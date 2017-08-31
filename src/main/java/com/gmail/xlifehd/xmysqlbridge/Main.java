package com.gmail.xlifehd.xmysqlbridge;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.xlifehd.xmysqlbridge.listeners.OnJoin;
import com.gmail.xlifehd.xmysqlbridge.listeners.OnQuit;
import com.gmail.xlifehd.xmysqlbridge.listeners.blocker.EntityPickupItemListener;
import com.gmail.xlifehd.xmysqlbridge.listeners.blocker.InventoryInteractListener;
import com.gmail.xlifehd.xmysqlbridge.listeners.blocker.InventoryOpenListener;
import com.gmail.xlifehd.xmysqlbridge.listeners.blocker.PlayerCommandPreprocessListener;
import com.gmail.xlifehd.xmysqlbridge.listeners.blocker.PlayerDropItemListener;
import com.gmail.xlifehd.xmysqlbridge.listeners.blocker.PlayerInteractEntityListener;
import com.gmail.xlifehd.xmysqlbridge.listeners.blocker.PlayerInteractListener;
import com.gmail.xlifehd.xmysqlbridge.listeners.blocker.PlayerItemConsumeListener;
import com.gmail.xlifehd.xmysqlbridge.listeners.blocker.PlayerItemHeldListener;
import com.gmail.xlifehd.xmysqlbridge.listeners.blocker.PlayerMoveListener;
import com.gmail.xlifehd.xmysqlbridge.listeners.blocker.PlayerPortalListener;
import com.gmail.xlifehd.xmysqlbridge.listeners.blocker.PlayerSwapHandItemsListener;
import com.gmail.xlifehd.xmysqlbridge.mysql.GeneralHandler;
import com.gmail.xlifehd.xmysqlbridge.mysql.SaveHandler;

public class Main extends JavaPlugin {
	
	private static Main instance;
	
	private FileConfiguration config;
	
	private GeneralHandler mySQLHandler;
	
	private XUtils xUtils;
	
	private static String pluginPrefix = ChatColor.DARK_GRAY + "[" + ChatColor.AQUA + "xMySQL" + ChatColor.GREEN + ChatColor.BOLD + "Bridge" + ChatColor.RESET + ChatColor.DARK_GRAY + "] ";
	private static String errorPrefix = pluginPrefix + ChatColor.RED;
	private static String infoPrefix = pluginPrefix + ChatColor.WHITE;
	
	@Override
	public void onEnable() {
		
		instance = this;
		
		setupConfig();
		
		mySQLHandler = new GeneralHandler(config);
		xUtils = new XUtils();
		
		registerListeners();
		
		//DEBUG
		//Register Commands
		//this.getCommand("xmbr").setExecutor(new TestCommand());
		
		setupSaveTask();
		
	}
	
	@Override
	public void onDisable() {
		getPreparedSaveHandler().savePlayerData();
	}
	
	private void setupConfig() {
		config = getConfig();
		
		config.options().header("xMySQLBridge Config by xLifeHD@gmail.com");
		config.addDefault("CfgVersion", 1);
		
		//TODO Add ssl support
		config.addDefault("mysql.host", "127.0.0.1");
		config.addDefault("mysql.port", 3306);
		config.addDefault("mysql.database", "minecraft");
		config.addDefault("mysql.username", "admin");
		config.addDefault("mysql.password", "foobar");
		config.addDefault("mysql.prefix", "xbr_");
		
		config.addDefault("savetask.enabled", true);
		config.addDefault("savetask.timer", 180);
		
		//TODO Add remaining air, fire ticks, exhaustion, fallheight, bed spawn location, essentials info, gamemode
		String[] tables = {"health", "hunger", "effects", "location", "experience", "inventory", "enderchest", "advancements"};
		
		for ( String table: tables ) {
			config.addDefault("table." + table + ".enabled", false);
			config.addDefault("table." + table + ".name", table);
			
		}
		
		config.addDefault("loadDelayinTicks", 50);
		
		config.options().copyHeader(true);
		config.options().copyDefaults(true);
		saveConfig();
	}
	
	private void registerListeners() {
		PluginManager pluginMgr = getServer().getPluginManager();
		
		pluginMgr.registerEvents(new OnJoin(), this);
		pluginMgr.registerEvents(new OnQuit(), this);
		
		//Blocker listeners
		//DEBUG
		//pluginMgr.registerEvents(new InventoryOpenListener(), this); //Works, is covered "PlayerInteractListener"
		pluginMgr.registerEvents(new InventoryInteractListener(), this);
		//pluginMgr.registerEvents(new PlayerItemHeldListener(), this); //Works
		//pluginMgr.registerEvents(new PlayerSwapHandItemsListener(), this); //Works
		pluginMgr.registerEvents(new PlayerDropItemListener(), this);
		pluginMgr.registerEvents(new EntityPickupItemListener(), this);
		//pluginMgr.registerEvents(new PlayerItemConsumeListener(), this); //Is covered by "PlayerInteractionListener", but still needs testing
		
		//pluginMgr.registerEvents(new PlayerCommandPreprocessListener(), this); //Works
		
		//pluginMgr.registerEvents(new PlayerMoveListener(), this); //Works
		pluginMgr.registerEvents(new PlayerPortalListener(), this);
		//TODO Look if the animation event needs to be blocked
		
		pluginMgr.registerEvents(new PlayerInteractListener(), this); //TODO Test if "PlayerBedEnter" is covered with this; Placing with the bucket is, emptying needs testing
		pluginMgr.registerEvents(new PlayerInteractEntityListener(), this);
		
	}
	
	private void setupSaveTask() {
		if (config.getBoolean("savetask.enabled")) {
			Runnable r = new Runnable() {
				public void run() {
					getPreparedSaveHandler().savePlayerDataAsync();
				}
			};
			long time = config.getLong("savetask.timer") * 20;
			Main.getPlugin().getServer().getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), r, time, time);
		}
	}
	
	
	public SaveHandler getPreparedSaveHandler() {
		Collection<? extends Player> playerCollection = Bukkit.getOnlinePlayers();
		Player[] playerArray = playerCollection.toArray(new Player[playerCollection.size()]);
		SaveHandler saveHandler = new SaveHandler(playerArray);
		return saveHandler;
	}
	
	public static Main getPlugin() {
		return instance;
	}
	
	public static String getPrefix(String str) {
		if ( str.equalsIgnoreCase("info") ) {
			return infoPrefix;
		} else if ( str.equalsIgnoreCase("error") ) {
			return errorPrefix;
		} else {
			return pluginPrefix;
		}
	}
	
	public GeneralHandler getMySQLHandler() {
		return mySQLHandler;
	}

	public XUtils getxUtils() {
		return xUtils;
	}
	
}