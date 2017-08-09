package com.gmail.xlifehd.xmysqlbridge.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

import com.gmail.xlifehd.xmysqlbridge.Main;

public class GeneralHandler {
	
	private Connection con;
	private String host, database, username, password;
	private int port;
	
	public GeneralHandler( FileConfiguration config ) {
		
		host = config.getString("mysql.host");
		port = config.getInt("mysql.port");
		database = config.getString("mysql.database");
		username = config.getString("mysql.username");
		password = config.getString("mysql.password");
		
		try {
			openConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		setupTables(config);
	}
	
	private void setupTables( FileConfiguration config ) {
		
		BukkitRunnable r = new BukkitRunnable() {
			
			public void run() {
				
				FileConfiguration config = Main.getPlugin().getConfig();
				
				//TODO Create Querys
				String queryHealth =		"CREATE TABLE IF NOT EXISTS `" + config.getString("mysql.prefix") + config.getString("table.health.name") + "` (" + 
						"  `uuid` varchar(36) NOT NULL," + 
						"  `health` double NOT NULL," + 
						"  PRIMARY KEY (`uuid`)" + 
						")";
				String queryHunger =		"CREATE TABLE IF NOT EXISTS `" + config.getString("mysql.prefix") + config.getString("table.hunger.name") + "` (" + 
						"  `uuid` varchar(36) NOT NULL," + 
						"  `hunger` int NOT NULL," + 
						"  PRIMARY KEY (`uuid`)" + 
						")";
				String queryEffects =		"";
				String queryLocation =		"CREATE TABLE IF NOT EXISTS `" + config.getString("mysql.prefix") + config.getString("table.location.name") + "` (" + 
						"  `uuid` varchar(36) NOT NULL," + 
						"  `world` varchar(200) NOT NULL," + 
						"  `x` double NOT NULL," + 
						"  `y` double NOT NULL," + 
						"  `z` double NOT NULL," + 
						"  `yaw` float NOT NULL," + 
						"  `pitch` float NOT NULL," + 
						"  PRIMARY KEY (`uuid`)" + 
						")";
				String queryExperience =	"CREATE TABLE IF NOT EXISTS `" + config.getString("mysql.prefix") + config.getString("table.experience.name") + "` (" + 
						"  `uuid` varchar(36) NOT NULL," + 
						"  `experience` float NOT NULL," + 
						"  PRIMARY KEY (`uuid`)" + 
						")";
				String queryMoney =			"CREATE TABLE IF NOT EXISTS `" + config.getString("mysql.prefix") + config.getString("table.money.name") + "` (" + 
						"  `uuid` varchar(36) NOT NULL," + 
						"  `money` double NOT NULL," + 
						"  PRIMARY KEY (`uuid`)" + 
						")";
				String queryInventory =		"CREATE TABLE IF NOT EXISTS `" + config.getString("mysql.prefix") + config.getString("table.inventory.name") + "` (" + 
						"  `uuid` varchar(36) NOT NULL," + 
						"  `inventory` MEDIUMTEXT NOT NULL," + 
						"  `armor` MEDIUMTEXT NOT NULL," + 
						"  PRIMARY KEY (`uuid`)" + 
						")";
				String queryEnderchest =	"CREATE TABLE IF NOT EXISTS `" + config.getString("mysql.prefix") + config.getString("table.enderchest.name") + "` (" + 
						"  `uuid` varchar(36) NOT NULL," + 
						"  `enderchest` MEDIUMTEXT NOT NULL," + 
						"  PRIMARY KEY (`uuid`)" + 
						")";
				String queryAchievements =	"";
				
				try {
					
					Main.getPlugin().getMySQLHandler().openConnection();
					Connection con = Main.getPlugin().getMySQLHandler().getConnection();
					Statement statement = con.createStatement();
					//TODO Handle sql exception per result set, to give proper error message
					if ( config.getBoolean("table.health.enabled"))			{ statement.executeQuery(queryHealth); }
					if ( config.getBoolean("table.hunger.enabled"))			{ statement.executeQuery(queryHunger); }
					if ( config.getBoolean("table.effects.enabled"))		{ statement.executeQuery(queryEffects); }
					if ( config.getBoolean("table.location.enabled"))		{ statement.executeQuery(queryLocation); }
					if ( config.getBoolean("table.experience.enabled"))		{ statement.executeQuery(queryExperience); }
					if ( config.getBoolean("table.money.enabled"))			{ statement.executeQuery(queryMoney); }
					if ( config.getBoolean("table.inventory.enabled"))		{ statement.executeQuery(queryInventory); }
					if ( config.getBoolean("table.enderchest.enabled"))		{ statement.executeQuery(queryEnderchest); }
					if ( config.getBoolean("table.achievements.enabled"))	{ statement.executeQuery(queryAchievements); }
					
					con.close();
					
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		};
		
		r.runTaskAsynchronously(Main.getPlugin());
		
	}
	
	//TODO Make a connection pool for better performance, for now everything will go over one connection which could be a problem
	public void openConnection() throws SQLException, ClassNotFoundException {
		if ( con != null && !con.isClosed() ) {
			return;
		}
		
		synchronized (this) {
			if ( con != null && !con.isClosed() ) {
				return;
			}
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database, this.username, this.password);
			
		}
	}
	
	public Connection getConnection() {
		return con;
	}
	
}