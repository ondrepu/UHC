package ondre.uhc.timers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import ondre.uhc.Main;
import ondre.uhc.managers.ChatManager;
import ondre.uhc.managers.GameManager;
import ondre.uhc.utils.GameState;
import ondre.uhc.utils.GameUtils;

public class LobbyTimer {

	GameManager gm = new GameManager();
	ChatManager cm = new ChatManager();
	PregameTimer pt = new PregameTimer();
	GameUtils gu = new GameUtils();

	public int lobbyTime = gm.getLobbyTime();

	public void startLobbyTimer() {
		Bukkit.broadcastMessage(ChatColor.YELLOW + "There are enough players! The game will begin soon!");

		new Runnable() {
			public int taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.main, this, 0L, 20L);

			@Override
			public void run() {
				if (lobbyTime > 0) {

					lobbyTime--;

					if (lobbyTime == 10 || lobbyTime <= 5 && lobbyTime > 0) {
						for (Player player : Bukkit.getOnlinePlayers()) {
							player.playSound(player.getLocation(), Sound.CLICK, 1, 1);
							player.sendMessage(ChatColor.YELLOW + "The game will start in " + ChatColor.GREEN + lobbyTime + ChatColor.YELLOW + " seconds!");
						}
					}

					if (lobbyTime == 0) {
						GameState.state = GameState.PREGAME;
						gu.addItems(); gu.scatterPlayers(); pt.startPregameTimer();
						Bukkit.getScheduler().cancelTask(taskID);
					}
				}
			}
		};
	}
}
