package com.scarabcoder.gameapi.listener;

import com.scarabcoder.gameapi.game.Game;
import com.scarabcoder.gameapi.manager.GameManager;
import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.StringUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerPingListener implements Listener {
	
	@EventHandler
	public void onServerListPing(ServerListPingEvent e){
		boolean motdSet = false;
		boolean playerCountSet = false;
		for(Game g : GameManager.getGames()){
			if(g.getGameSettings().doesSetMOTD() && !motdSet){
				e.setMotd(StringUtils.capitalize(StringUtils.lowerCase(g.getGameStatus().toString())));
				motdSet = true;
			}
			if(g.getGameSettings().doesSetListPlayerCount() && !playerCountSet){
				e.setMaxPlayers(g.getGameSettings().getMaximumPlayers());
				playerCountSet = true;
			}
				
		}
	}
	
}
