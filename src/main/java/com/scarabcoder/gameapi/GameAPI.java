package com.scarabcoder.gameapi;

import com.scarabcoder.gameapi.listener.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class GameAPI extends JavaPlugin {
	
	private static Plugin plugin;
	
	private static File gameWorlds;
	
	private static boolean debug;
	
	@Override
	public void onEnable(){
		Bukkit.getPluginManager().registerEvents(new PlayerMovementListener(), this);
		Bukkit.getPluginManager().registerEvents(new SettingsListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerPvPListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(), this);
		Bukkit.getPluginManager().registerEvents(new InventoryListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
		Bukkit.getPluginManager().registerEvents(new ServerPingListener(), this);
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		plugin = this;
		
		gameWorlds = new File("GameWorlds");
		
		if(!gameWorlds.exists()){
			gameWorlds.mkdir();
		}
		if(!this.getDataFolder().exists()){
			this.getDataFolder().mkdir();
		}
		
		this.saveDefaultConfig();
		debug = this.getConfig().getBoolean("debug");
		
	}
	
	/**
	 * Get whether or not the API is in debug mode, as set in the config.yml.
	 * @return boolean debug mode.
	 */
	public static boolean debugMode(){
		return debug;
	}
	
	public static File getGameWorldsFolder(){
		return gameWorlds;
	}
	
	public static Plugin getPlugin(){
		return plugin;
		
		
	}
	
	public static void logInfo(String msg){
		System.out.println("[GameAPI] [INFO] " + msg);
	}
	
	public static void sendDebugMessage(String message, Plugin plugin){
		if(GameAPI.debugMode()) System.out.println("[GameAPI]" + (plugin.getName().equals("GameAPI") ? "" : " [" + plugin.getName() + "]") + " [DEBUG] " + message);
	}
	
	
}
