package ondre.uhc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import ondre.uhc.managers.ChatManager;
import ondre.uhc.managers.LobbyManager;

public class SetLobby implements CommandExecutor {
	
	ChatManager cm = new ChatManager();
	LobbyManager lm = new LobbyManager();


	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			
	if(!(player.hasPermission("admin.lobby"))) {
		player.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
	}
			
		if(args.length != 0) {
			player.sendMessage(cm.prefix + "Wrong usage: /setlobby");
			return true;
			
			}
		
		lm.setLocation("lobby", player.getLocation());
		player.sendMessage(cm.prefix + "You have added a lobby spawn!");
		
		}
		
		return true;
	}
}
