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

public class GameTimer {

	ChatManager cm = new ChatManager();
	GameManager gm = new GameManager();
	DeathmatchTimer dt = new DeathmatchTimer();
	GameUtils gu = new GameUtils();
	ActionManager am = new ActionManager();

	public int gameTime = gm.getGameTime();

	public void startGameTimer() {
		gu.closeBorders();
		
		new Runnable() {
			public int taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.main, this, 0L, 20L);

			@Override
			public void run() {
				if (gameTime > 0) {

					gameTime--;
							
					if(gameTime <= gameTime && gameTime > 0) {
						gu.updateGameBoard();
						
					}
					
						if(gameTime <= gameTime && gameTime > 60) {
							for(Player player : Bukkit.getOnlinePlayers()) {
							am.sendActionbar(player, ChatColor.YELLOW + "Meetup begins in " + ChatColor.GREEN +  gameTime/60 + ":" + gameTime%60 +  ChatColor.YELLOW + " minutes");
							
							}
						}
						
						if(gameTime == 60 || gameTime == 30 || gameTime == 10 || gameTime <= 5 && gameTime > 0) {
							Bukkit.broadcastMessage(ChatColor.YELLOW + "Meetup begins in " + ChatColor.RED + gameTime + ChatColor.YELLOW + " seconds!");
						}
					
					if (gameTime == 0) {
						GameState.state = GameState.DEATHMATCH; dt.startDMTimer();
						Bukkit.getScheduler().cancelTask(taskID);
					}
				}
			};
		};
	}
}
