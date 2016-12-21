package ondre.uhc.managers;

import java.util.ArrayList;
import org.bukkit.entity.Player;

public class GameManager {
	
	public int minPlayers = 1;
	public int maxPlayers = 100;
	
	public int lobbyTime = 30 + 1;
	public int pregameTime = 600 + 1;
	public static int gameTime = 1800 + 1;
	public int deathmatchTime = 600 + 1;
	public int endTime = 10 + 1;
	
	public int borderSize = 2000;
	
	public static ArrayList<Player> alive = new ArrayList<Player>();
	public static ArrayList<Player> dead = new ArrayList<Player>();
	
	public int getLobbyTime() {
		return lobbyTime;	
	}
	
	public int getpregameTime() {
		return pregameTime;
	}
	
	public int getGameTime() {
		return gameTime;
	}
	
	public int getDeathmatchTime() {
		return deathmatchTime;
	}
	
	public int getEndTime() {
		return endTime;
	}
	
}
