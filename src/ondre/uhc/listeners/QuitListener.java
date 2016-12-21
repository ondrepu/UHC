package ondre.uhc.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import ondre.uhc.Main;
import ondre.uhc.managers.ChatManager;
import ondre.uhc.managers.GameManager;
import ondre.uhc.timers.RestartTimer;
import ondre.uhc.utils.GameState;

public class QuitListener implements Listener {
	
	GameManager gm = new GameManager();
	RestartTimer rt = new RestartTimer();
	ChatManager cm = new ChatManager();
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		
		Player player = event.getPlayer();
		Location location = player.getLocation();
		Inventory inventory = player.getInventory();
		
		event.setQuitMessage(null);
		GameManager.alive.remove(player);
		
		if(GameState.state == GameState.INGAME) {
			
			for(ItemStack items : inventory.getContents()) {
				if(items != null) {
					location.getWorld().dropItemNaturally(location, items.clone());
				}
				inventory.clear();
			}
		}
		
		if(GameState.state == GameState.LOBBY && GameManager.alive.size() < gm.minPlayers) {
			Bukkit.getScheduler().cancelTasks(Main.main);
			
		}
		
		if(GameState.state == GameState.PREGAME && GameManager.alive.size() == 0 || GameState.state == GameState.INGAME && GameManager.alive.size() == 0) {
			Bukkit.getScheduler().cancelTasks(Main.main); rt.startRestartTimer();
			
		}
		
		if(GameState.state == GameState.PREGAME && GameManager.alive.size() == 1 || GameState.state == GameState.INGAME && GameManager.alive.size() == 1) {
			Player winner = GameManager.alive.get(0);
			
			Bukkit.broadcastMessage(ChatColor.GREEN + winner.getDisplayName() + ChatColor.YELLOW + " has bravely won the UHC games! The server will now restart!");
			GameState.state = GameState.RESTARTING; rt.startRestartTimer();
		}
	}
}