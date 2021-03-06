package com.gmail.xlifehd.xmysqlbridge.mysql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import com.gmail.xlifehd.xmysqlbridge.BukkitSerialization;
import com.gmail.xlifehd.xmysqlbridge.Main;

public class LoadHandler {
	
	private UUID uuid;
	private FileConfiguration config;
	private String mySQLPrefix;
	private Connection con;
	
	public LoadHandler ( UUID uuid ) {
		this.uuid = uuid;
		config = Main.getPlugin().getConfig();
		mySQLPrefix = config.getString("mysql.prefix");
		con = Main.getPlugin().getMySQLHandler().getConnection();
	}
	
	public Double getHealth () {
		String tableName = config.getString("table.health.name");
		String query = "SELECT * FROM `" + mySQLPrefix + tableName + "` WHERE uuid = '" + uuid.toString() + "';";
		
		try {
			ResultSet rs = con.createStatement().executeQuery( query );
			
			if ( rs.next() ) {
				Double health =  rs.getDouble("health");
				return health;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Number[] getHunger() {
		String tableName = config.getString("table.hunger.name");
		String query = "SELECT * FROM `" + mySQLPrefix + tableName + "` WHERE uuid = '" + uuid.toString() + "';";
		
		try {
			ResultSet rs = con.createStatement().executeQuery( query );
			
			if ( rs.next() ) {
				Integer hunger = rs.getInt("hunger");
				Float saturation = rs.getFloat("saturation");
				
				return new Number[] {(Number) (hunger), (Number) (saturation)};
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Collection<PotionEffect> getEffects() {
		String tableName = config.getString("table.effects.name");
		String query = "SELECT * FROM `" + mySQLPrefix + tableName + "` WHERE uuid = '" + uuid.toString() + "';";
		
		try {
			ResultSet rs = con.createStatement().executeQuery( query );
			
			if ( rs.next() ) {
				String effectsString = rs.getString("effects");
				Collection<PotionEffect> effects = BukkitSerialization.potionEffectsFromBase64(effectsString);
				return effects;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Location getLocation() {
		String tableName = config.getString("table.location.name");
		String query = "SELECT * FROM `" + mySQLPrefix + tableName + "` WHERE uuid = '" + uuid.toString() + "';";
		
		try {
			ResultSet rs = con.createStatement().executeQuery( query );

			if ( rs.next() ) {
				World world = Bukkit.getWorld(rs.getString("world"));
				Double x = rs.getDouble("x");
				Double y = rs.getDouble("y");
				Double z = rs.getDouble("z");
				Float yaw = rs.getFloat("yaw");
				Float pitch = rs.getFloat("pitch");
				Location loc = new Location(world, x, y, z, yaw, pitch);
				return loc;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Number[] getExperience() {
		String tableName = config.getString("table.experience.name");
		String query = "SELECT * FROM `" + mySQLPrefix + tableName + "` WHERE uuid = '" + uuid.toString() + "';";
		
		try {
			ResultSet rs = con.createStatement().executeQuery( query );
			
			if ( rs.next() ) {
				Integer totalExp = rs.getInt("totalExp");
				Integer level = rs.getInt("level");
				Float exp = rs.getFloat("exp");
				
				return new Number[] {(Number) (totalExp), (Number) (level), (Number) (exp)};
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ItemStack[][] getInventory() {
		String tableName = config.getString("table.inventory.name");
		String query = "SELECT * FROM `" + mySQLPrefix + tableName + "` WHERE uuid = '" + uuid.toString() + "';";
		
		try {
			ResultSet rs = con.createStatement().executeQuery( query );
			
			if ( rs.next() ) {
				ItemStack[] storage = BukkitSerialization.itemStackArrayFromBase64(rs.getString("inventory"));
				ItemStack[] armor = BukkitSerialization.itemStackArrayFromBase64(rs.getString("armor"));
				ItemStack[] offhand = new ItemStack[] { BukkitSerialization.itemStackFromBase64(rs.getString("offhand"))};
				
				return new ItemStack[][] {storage, armor, offhand};
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ItemStack[] getEnderchest() {
		String tableName = config.getString("table.enderchest.name");
		String query = "SELECT * FROM `" + mySQLPrefix + tableName + "` WHERE uuid = '" + uuid.toString() + "';";
		
		try {
			ResultSet rs = con.createStatement().executeQuery( query );
			
			if (rs.next() ) {
				ItemStack[] enderchest = BukkitSerialization.itemStackArrayFromBase64(rs.getString("enderchest"));
				return enderchest;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public HashMap<Advancement, AdvancementProgress> getAdvancements() {
		String tableName = config.getString("table.advancements.name");
		String query = "SELECT * FROM `" + mySQLPrefix + tableName + "` WHERE uuid = '" + uuid.toString() + "';";
		
		try {
			ResultSet rs = con.createStatement().executeQuery( query );
			
			if ( rs.next() ) {
				String advancementsString = rs.getString("advancements");
				HashMap<Advancement, AdvancementProgress> hashMap = BukkitSerialization.advancementsFromBase64(advancementsString);
				return hashMap;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}















