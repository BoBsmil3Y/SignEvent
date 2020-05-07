package fr.bobsmil3y.signevent.listeners;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import fr.bobsmil3y.signevent.Main;
import fr.bobsmil3y.signevent.commands.Event;

public class CreateSign implements Listener {

	private Main main;
	
	public CreateSign(Main main) {
		this.main = main;
	}
		
	
	@EventHandler
	public void onSignEdit(SignChangeEvent event) {
		System.out.println("event");
		
		//if(! Event.getEvent()) return;
		
		Player player = (Player) event.getPlayer();
		if(! player.hasPermission("signevent.admin")) return;
		
		String line1 = event.getLine(0);
		String line2 = event.getLine(1);
		String line3 = event.getLine(2);
		String line4 = event.getLine(3);

		if(line1.equalsIgnoreCase("[event]")) {
			
		}
		
	}

	
}
