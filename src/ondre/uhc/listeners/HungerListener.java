package ondre.uhc.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import ondre.uhc.utils.GameState;

public class HungerListener implements Listener {
	
	@EventHandler
	public void onHunger(FoodLevelChangeEvent event) {
		if(GameState.state == GameState.LOBBY || GameState.state == GameState.RESTARTING) {
			event.setCancelled(true);
			
		} else {
			
			event.setCancelled(false);
		}
	}	
}
