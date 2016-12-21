package ondre.uhc.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import ondre.uhc.Main;
import ondre.uhc.managers.ChatManager;
import ondre.uhc.managers.GameManager;
import ondre.uhc.timers.RestartTimer;
import ondre.uhc.utils.GameState;

public class DeathListener implements Listener {

	GameManager gm = new GameManager();
	ChatManager cm = new ChatManager();
	RestartTimer rt = new RestartTimer();

	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		Player dead = event.getEntity().getPlayer();
		GameManager.alive.remove(dead);
		GameManager.dead.add(dead);

		dead.setHealth(20);
		dead.setGameMode(GameMode.SPECTATOR);
		event.setDeathMessage(null);
		Bukkit.broadcastMessage(ChatColor.RED + dead.getDisplayName() + " has been eliminated!");

		if (GameState.state == GameState.INGAME && GameManager.alive.size() == 1 || GameState.state == GameState.PREGAME && GameManager.alive.size() == 1) {
			
			Player winner = GameManager.alive.get(0);
			Bukkit.broadcastMessage(ChatColor.GREEN + winner.getDisplayName() + ChatColor.YELLOW + " has bravely won the UHC games! The server will now restart!");
			GameState.state = GameState.RESTARTING; Bukkit.getScheduler().cancelTasks(Main.main); rt.startRestartTimer();

			} else {
			
		}

		if (GameState.state == GameState.INGAME && GameManager.alive.size() == 0 || GameState.state == GameState.PREGAME && GameManager.alive.size() == 0) {
			Bukkit.broadcastMessage(ChatColor.YELLOW + "There is no winner this round! The game will now restart!");
			GameState.state = GameState.RESTARTING; Bukkit.getScheduler().cancelTasks(Main.main); rt.startRestartTimer();

		}

		for (Player player : Bukkit.getOnlinePlayers()) {
			Location location = event.getEntity().getLocation(); player.playSound(location, Sound.AMBIENCE_THUNDER, 1, 1);
		}
	}
}
