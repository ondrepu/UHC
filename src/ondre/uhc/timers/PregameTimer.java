package ondre.uhc.timers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import ondre.uhc.Main;
import ondre.uhc.managers.ActionManager;
import ondre.uhc.managers.ChatManager;
import ondre.uhc.managers.GameManager;
import ondre.uhc.utils.GameState;
import ondre.uhc.utils.GameUtils;

public class PregameTimer {

	ChatManager cm = new ChatManager();
	GameManager gm = new GameManager();
	GameTimer gt = new GameTimer();
	GameUtils gu = new GameUtils();

	public int pregameTime = gm.getpregameTime();
	ActionManager am = new ActionManager();

	public void startPregameTimer() {

		new Runnable() {
			public int taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.main, this, 0L, 20L);

			@Override
			public void run() {
				if (pregameTime > 0) {

					pregameTime--;

					if (pregameTime <= 5 && pregameTime > 0) {
						for (Player player : Bukkit.getOnlinePlayers()) {
							player.playSound(player.getLocation(), Sound.CLICK, 1, 1);
							Bukkit.broadcastMessage(ChatColor.YELLOW + "PVP will be enabled in " + ChatColor.RED + pregameTime + ChatColor.YELLOW + " seconds!");
						}
					}

					if (pregameTime == 0) {
						for (Player player : Bukkit.getOnlinePlayers()) {
							player.playSound(player.getLocation(), Sound.ENDERDRAGON_GROWL, 10, 1);
							Bukkit.broadcastMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "PVP IS NOW ENABLED!");
							GameState.state = GameState.INGAME; gt.startGameTimer(); Bukkit.getScheduler().cancelTask(taskID);
						}
					}
					
					if(pregameTime <= pregameTime || pregameTime > 60) {
						for(Player player : Bukkit.getOnlinePlayers()) {
							am.sendActionbar(player, ChatColor.YELLOW + "PVP will enable in " + ChatColor.GREEN +  pregameTime/60 + ":" + pregameTime%60 +  ChatColor.YELLOW + " minutes");
						}
					}
				}
			}
		};
	}
}
