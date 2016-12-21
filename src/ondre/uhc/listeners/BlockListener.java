package ondre.uhc.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import ondre.uhc.utils.GameState;

public class BlockListener implements Listener {
	
	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		
		if(player.getGameMode().equals(GameMode.CREATIVE) || GameState.state == GameState.INGAME || GameState.state == GameState.PREGAME || GameState.state == GameState.DEATHMATCH) {
			event.setCancelled(false);
			
		} else {
			event.setCancelled(true);
		}
	}
}