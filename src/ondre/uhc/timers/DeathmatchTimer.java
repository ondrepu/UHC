package ondre.uhc.timers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import ondre.uhc.Main;
import ondre.uhc.managers.ActionManager;
import ondre.uhc.managers.ChatManager;
import ondre.uhc.managers.GameManager;
import ondre.uhc.utils.GameState;
import ondre.uhc.utils.GameUtils;

public class DeathmatchTimer {
	
	ChatManager cm = new ChatManager();
	GameManager gm = new GameManager();
	RestartTimer rt = new RestartTimer();
	GameUtils gu = new GameUtils();
	ActionManager am = new ActionManager();

	public int dmTime = gm.getDeathmatchTime();

	public void startDMTimer() {
		gu.closeDMBorders();
		Bukkit.broadcastMessage(ChatColor.RED +  "" + ChatColor.BOLD + "MAKE YOUR WAY TORWARDS (0.0) FOR MEETUP!");
		
		new Runnable() {
			public int taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.main, this, 0L, 20L);

			@Override
			public void run() {
				if (dmTime > 0) {

					dmTime--;
							
					if(dmTime <= dmTime && dmTime > 0) {
						gu.updateGameBoard();
						
					}
					
						if(dmTime <= dmTime && dmTime > 60) {
							for(Player player : Bukkit.getOnlinePlayers()) {
							am.sendActionbar(player, ChatColor.YELLOW + "Meetup will end in " + ChatColor.GREEN +  dmTime/60 + ":" + dmTime%60 +  ChatColor.YELLOW + " minutes");
							
							}
						}
						
						if(dmTime == 60 || dmTime == 30 || dmTime == 10 || dmTime <= 5 && dmTime > 0) {
							Bukkit.broadcastMessage(ChatColor.YELLOW + "Meetup will end in " + ChatColor.RED + dmTime + ChatColor.YELLOW + " seconds!");
						}
					
					if (dmTime == 0) {
						
						Bukkit.broadcastMessage(ChatColor.YELLOW + "There is no winner this round! The game will now restart!");
						GameState.state = GameState.RESTARTING; rt.startRestartTimer();
						Bukkit.getScheduler().cancelTask(taskID);
					}
				}
			};
		};
	}
}