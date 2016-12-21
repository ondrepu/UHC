package ondre.uhc.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import ondre.uhc.managers.GameManager;
import ondre.uhc.managers.LobbyManager;
import ondre.uhc.timers.LobbyTimer;
import ondre.uhc.utils.GameState;
import ondre.uhc.utils.GameUtils;

public class JoinListener implements Listener {
	
	GameManager gm = new GameManager();
	LobbyManager lm = new LobbyManager();
	LobbyTimer lt = new LobbyTimer();
	GameUtils gu = new GameUtils();
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		event.setJoinMessage(null);
		
		if(GameState.state == GameState.LOBBY) {
			gu.setAlive(player);
			player.teleport(lm.getLocation("lobby"));	
			Bukkit.broadcastMessage(ChatColor.YELLOW + player.getDisplayName() + " has joined the fight! " + ChatColor.RED + "(" + GameManager.alive.size() + "/" + gm.maxPlayers + ")");
			
		}
		
		if(GameState.state == GameState.LOBBY && Bukkit.getOnlinePlayers().size() == gm.minPlayers) {
			lt.startLobbyTimer();
			
		} else {
			
			GameManager.alive.remove(player);
			GameManager.dead.add(player);
			player.setGameMode(GameMode.SPECTATOR);			
		}
	}
}