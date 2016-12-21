package ondre.uhc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ondre.uhc.managers.ChatManager;
import ondre.uhc.managers.GameManager;

public class TestCmd implements CommandExecutor {
	
	ChatManager cm = new ChatManager();
	GameManager gm = new GameManager();
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			
		if(args.length != 0) {
			player.sendMessage(cm.prefix + "Wrong usage: /online");
			return true;
			
			}
		
		if(GameManager.alive.contains(player)) {
				player.sendMessage("TEST TEST TEST!");
			} else {
				player.sendMessage("NOT WORKING!");
			}
		}
		
		return true;
	}
}