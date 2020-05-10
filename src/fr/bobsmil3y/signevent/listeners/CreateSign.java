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

		if(event.getLine(2) == null || event.getLine(1) == null || event.getLine(0) == null) {
			player.sendMessage("§cSE §7| §cSomes lines are missing. Lines 1 to 3 are essential.");
			return;
		}

		if(event.getLine(0).equalsIgnoreCase("[event]")) {
			
			//If block reward
			if(event.getLine(1).equalsIgnoreCase("block")) {	
				
				if(event.getLine(3) != "" && event.getLine(3) != null) {
										
					Material material = Material.getMaterial(event.getLine(3).toUpperCase());
					int amount;
					
					try {
						amount = Integer.parseInt(event.getLine(2));
					}catch(NumberFormatException e) {
						
						player.sendMessage("§cSE §7| §cYou do not provide a correct number at the 3th line.");
						sign.setType(Material.AIR);
						return;
						
					}
					
					if(material != null && amount != 0 && amount >= 1) {
						
						ItemStack item = new ItemStack(material, amount);
						
						EventSigns signevent = new EventSigns(sign, item, amount);
						signs.add(signevent);
						player.sendMessage("§aSE §7| §7Your sign as been §aadded§7 to the list !");
						
						editSign(event);
						
					} else {
						player.sendMessage("§cSE §7| §cYou do not provide a correct Materiel name or a valid amount of block. See a complete list of material here : ");
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
				
				double amount;
				
				try {
					amount = Integer.parseInt(event.getLine(2));
				}catch(NumberFormatException e) {
					player.sendMessage("§cSE §7| §cYou do not provide a correct number at the 3th line.");
					return;
				}
				
				if(amount != 0) {
					
					
					EventSigns signevent = new EventSigns(sign, amount);
					signs.add(signevent);
					player.sendMessage("§aSE §7| §7Your sign as been §aadded§7 to the list !");

					editSign(event);
				
				} 
				else {
					player.sendMessage("§cYou can't give 0 money to a player ! That's illegal !!!");
					sign.setType(Material.AIR);
					return;
				}
				
			}
			
		}
		
	}


	public void editSign(SignChangeEvent event) {
		String one, two, three, four;
		
		one = main.getConfig().getString("options.firstLine").replace("&", "§");
		two = main.getConfig().getString("options.secondLine").replace("&", "§");
		three = main.getConfig().getString("options.thirdLine").replace("&", "§");
		four =  main.getConfig().getString("options.fourthLine").replace("&", "§");
		
		if(one != "") event.setLine(0, one);
		if(two != "") event.setLine(1, two);
		if(three != "") event.setLine(2, three);
		
		int line = main.getConfig().getInt("options.putRewardOnSign") -1;

		if(line <= 4 && line > 0) {
			event.setLine(line, event.getLine(3).toUpperCase());
			System.out.println(line);
			System.out.println(event.getLine(3).toUpperCase());
		} 
		else if(four != "") {
			event.setLine(3, four);
		}
		
	}
}
