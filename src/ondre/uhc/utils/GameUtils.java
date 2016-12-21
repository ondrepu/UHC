package ondre.uhc.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.WorldBorder;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import ondre.uhc.managers.GameManager;
import ondre.uhc.managers.ScoreboardManager;

public class GameUtils {
	
	GameManager gm = new GameManager();
	
	public void addItems() {
		for(Player player : Bukkit.getOnlinePlayers()) {
			player.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
			player.getInventory().addItem(new ItemStack(Material.STONE_PICKAXE));
			player.getInventory().addItem(new ItemStack(Material.STONE_SPADE));
			player.getInventory().addItem(new ItemStack(Material.STONE_AXE));
			player.getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 10));
			player.getInventory().addItem(new ItemStack(Material.STRING, 3));
		}
	}
	
	public void scatterPlayers() {
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "");
			
			int x = 0;  // Marks the x coord of the centre of your map.
			int z = 0;  // Marks the y coord of the centre of your map.
			int minDistance = 1;  // The minimum distance between players / teams.
			int maxRange = 10;  // The maximum range (applies to x and z coordinates.
			boolean respectTeams = true;  // Whether players in teams should be teleported to the same location (if applicable).
			String players = "@a";  // Here you specify a list of player names separated by spaces, or use commandblock specifiers.

			ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
			Bukkit.getServer().dispatchCommand(console, String.format("spreadplayers %d %d %d %d %b %s", x, z, minDistance, maxRange, respectTeams, players));
			
		}	
		
		public void updateGameBoard() {
			for (Player player : Bukkit.getOnlinePlayers()) {				
				WorldBorder wb = Bukkit.getWorld("world").getWorldBorder();
				
				ScoreboardManager sm = new ScoreboardManager(ChatColor.YELLOW + "" + ChatColor.BOLD + "UHC SOLO");
				sm.blankLine();
				sm.add("Alive: " + ChatColor.GREEN + GameManager.alive.size());
				sm.blankLine();
				sm.add("Border:");
				sm.add(ChatColor.GREEN + "" + Math.round(wb.getSize())/2 + "- " + Math.round(wb.getSize())/2 + "+");
				sm.build();
				sm.send(player);
		}
	}
		
		public void closeBorders() {
			for (Player player : Bukkit.getOnlinePlayers()) {
				player.playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
				
				WorldBorder wb = Bukkit.getWorld("world").getWorldBorder();
				wb.setCenter(0, 0);
				wb.setSize(200, gm.getGameTime());
		}
	}
		
		public void closeDMBorders() {
			for (Player player : Bukkit.getOnlinePlayers()) {
				player.playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
				
				WorldBorder wb = Bukkit.getWorld("world").getWorldBorder();
				wb.setCenter(0, 0);
				wb.setSize(2, gm.getDeathmatchTime());
		}
	}
		
		public void setAlive(Player player) {
			GameManager.alive.add(player);
			player.setGameMode(GameMode.SURVIVAL);
			player.getInventory().clear();
			player.setHealth(20);
			player.setFoodLevel(20);
			player.setLevel(0);
			player.setExp(0);
	}
}
