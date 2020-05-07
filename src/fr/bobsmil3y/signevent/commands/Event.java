package fr.bobsmil3y.signevent.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Event implements CommandExecutor {

	private static boolean event = false;
	
	@SuppressWarnings("static-access")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if(this.event) {
			this.event = false;
			sender.sendMessage("§7Event mode §cdisabled §7!");
		} else {
			this.event = true;
			sender.sendMessage("§7Event mode §aenable §7!");
		}
		
		return true;
	}
	
	
	public static boolean getEvent() {
		return event;
	}

}
