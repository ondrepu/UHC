package ondre.uhc.timers;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import ondre.uhc.Main;
import ondre.uhc.managers.ChatManager;
import ondre.uhc.managers.GameManager;

public class RestartTimer {

	ChatManager cm = new ChatManager();
	GameManager gm = new GameManager();

	public int restartTime = gm.getEndTime();

	public void startRestartTimer() {

		new Runnable() {
			public int taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.main, this, 0L, 20L);

			@Override
			public void run() {
				if (restartTime > 0) {

					restartTime--;

					if (restartTime <= 5 && restartTime > 0) {
						for (Player player : Bukkit.getOnlinePlayers()) {
							player.playSound(player.getLocation(), Sound.NOTE_PLING, 1, 1);
						}
					}

					if (restartTime == 0) {
						Bukkit.getServer().shutdown();
						Bukkit.getScheduler().cancelTask(taskID);
					}
				}
			}
		};
	}
}