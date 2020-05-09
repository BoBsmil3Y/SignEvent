package fr.bobsmil3y.signevent.listeners;


import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.inventory.ItemStack;

import fr.bobsmil3y.signevent.Main;
import fr.bobsmil3y.signevent.commands.SignEvent;
import fr.bobsmil3y.signevent.EventSigns;

public class CreateSign implements Listener {

	private Main main;
	private ArrayList<EventSigns> signs = new ArrayList<EventSigns>();
	
	public CreateSign(Main main) {
		this.main = main;
	}

	@EventHandler
	public void onSignEdit(SignChangeEvent event) {
		
		//if(! Event.getEvent()) return;
		
		Player player = (Player) event.getPlayer();
		if(! player.hasPermission("signevent.admin")) return;

		Block sign = event.getBlock();
		int amount;

		if(event.getLine(2) == null || event.getLine(1) == null || event.getLine(0) == null) {
			player.sendMessage("§cSE §7| §cSomes lines are missing. Lines 1 to 3 are essential.");
			return;
		}

		if(event.getLine(0).equalsIgnoreCase("[event]")) {
			
			//If block reward
			if(event.getLine(1).equalsIgnoreCase("block")) {	
				
				if(event.getLine(3) != null) {
										
					Material material = Material.getMaterial(event.getLine(3).toUpperCase());
					
					try {
						amount = Integer.parseInt(event.getLine(2));
					}catch(NumberFormatException e) {
						player.sendMessage("§cSE §7| §cYou do not provide a correct number at the 3th line.");
						sign.setType(Material.AIR);
						return;
					}
					
					
					if(material != null || amount != 0) {
						
						ItemStack item = new ItemStack(material);
						
						EventSigns signevent = new EventSigns(sign, item, amount);
						signs.add(signevent);
						player.sendMessage("§aSE §7| §7Your sign as been §aadded§7 to the list !");
						
						/*
						 * 
						 * Change content of the sign to the config file variable
						 * 
						 * */
						
					} else {
						player.sendMessage("§cSE §7| §cYou do not provide a correct Materiel name. See a complete list of them here : ");
						player.sendMessage("§7https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Material.html");
						sign.setType(Material.AIR);
						return;
					}
					
				} else {
					player.sendMessage("§cSE §7| §cYou do not provide a Materiel name. See a complete list of them here : ");
					player.sendMessage("§7https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Material.html");
					sign.setType(Material.AIR);
					return;
				}
				
			}
			
			//If money reward
			else if (event.getLine(1).equalsIgnoreCase("money")) {
				
				try {
					amount = Integer.parseInt(event.getLine(2));
				}catch(NumberFormatException e) {
					player.sendMessage("§cSE §7| §cYou do not provide a correct number at the 3th line.");
					return;
				}
				
				if(amount != 0) {
					
				} 
				else {
					player.sendMessage("§cYou can't give 0 money to a player ! That's illegal !!!");
					sign.setType(Material.AIR);
					return;
				}
				
			}
			
		}
		
	}

	
}
