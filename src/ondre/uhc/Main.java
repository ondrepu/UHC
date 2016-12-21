package ondre.uhc;

import org.bukkit.Bukkit;
import org.bukkit.WorldBorder;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import ondre.uhc.commands.SetLobby;
import ondre.uhc.commands.TestCmd;
import ondre.uhc.listeners.BlockListener;
import ondre.uhc.listeners.DamageListener;
import ondre.uhc.listeners.DeathListener;
import ondre.uhc.listeners.HungerListener;
import ondre.uhc.listeners.JoinListener;
import ondre.uhc.listeners.QuitListener;
import ondre.uhc.managers.GameManager;
import ondre.uhc.utils.GameState;

public class Main extends JavaPlugin {
	
public static Main main;
GameManager gm = new GameManager();
	
	public void onEnable() {
		main = this;
		
		GameState.state = GameState.LOBBY;
		WorldBorder wb = Bukkit.getWorld("world").getWorldBorder();
		wb.setCenter(0, 0);
		wb.setSize(gm.borderSize);
		
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new DeathListener(), this);
		pm.registerEvents(new JoinListener(), this);
		pm.registerEvents(new DamageListener(), this);
		pm.registerEvents(new HungerListener(), this);
		pm.registerEvents(new QuitListener(), this);
		pm.registerEvents(new BlockListener(), this);
		
		getCommand("setlobby").setExecutor(new SetLobby());
		getCommand("online").setExecutor(new TestCmd());

	}
}

