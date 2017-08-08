package com.gmail.xlifehd.xmysqlbridge;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

public class MySQLHandler {
	
	private Connection con;
	private String host, database, username, password;
	private int port;
	
	public MySQLHandler( FileConfiguration config ) {
		
		host = config.getString("mysql.host");
		port = config.getInt("mysql.port");
		database = config.getString("mysql.database");
		username = config.getString("mysql.username");
		password = config.getString("mysql.password");
		
		setupTables(config);
	}
	
	private void setupTables( FileConfiguration config ) {
		
		BukkitRunnable r = new BukkitRunnable() {
			
			public void run() {
				
				FileConfiguration config = Main.getPlugin().getConfig();
				
				//TODO Create Querys
				String queryHealth =		"CREATE TABLE IF NOT EXISTS `" + config.getString("mysql.prefix") + config.getString("table.health.name") + "` (\r\n" + 
						"  `uuid` varchar(16) NOT NULL,\r\n" + 
						"  `health` double NOT NULL,\r\n" + 
						"  PRIMARY KEY (`uuid`)\r\n" + 
						")";
				String queryHunger =		"";
				String queryEffects =		"";
				String queryPosition =		"";
				String queryExperience =	"";
				String queryMoney =			"";
				String queryInventory =		"";
				String queryEnderchest =	"";
				String queryAchievments =	"";
				
				try {
					
					Main.getPlugin().getMySQLHandler().openConnection();
					Connection con = Main.getPlugin().getMySQLHandler().getConnection();
					Statement statement = con.createStatement();
					//TODO Handle sql exception per result set, to give proper error message
					if ( config.getBoolean("table.health.enabled"))			{ statement.executeQuery(queryHealth); }
					if ( config.getBoolean("table.hunger.enabled"))			{ statement.executeQuery(queryHunger); }
					if ( config.getBoolean("table.effects.enabled"))		{ statement.executeQuery(queryEffects); }
					if ( config.getBoolean("table.position.enabled"))		{ statement.executeQuery(queryPosition); }
					if ( config.getBoolean("table.experience.enabled"))		{ statement.executeQuery(queryExperience); }
					if ( config.getBoolean("table.money.enabled"))			{ statement.executeQuery(queryMoney); }
					if ( config.getBoolean("table.inventory.enabled"))		{ statement.executeQuery(queryInventory); }
					if ( config.getBoolean("table.enderchest.enabled"))		{ statement.executeQuery(queryEnderchest); }
					if ( config.getBoolean("table.achievments.enabled"))	{ statement.executeQuery(queryAchievments); }
					
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
