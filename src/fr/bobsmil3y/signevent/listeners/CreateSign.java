package fr.bobsmil3y.signevent.listeners;


import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.inventory.ItemStack;

import fr.bobsmil3y.signevent.Main;
import fr.bobsmil3y.signevent.commands.SignEvent;
import fr.bobsmil3y.signevent.EventSign;

public class CreateSign implements Listener {

	private Main main;
	private ArrayList<EventSign> signs = new ArrayList<EventSign>();
	
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

		FileConfiguration config = main.getConfig();
		
		if(event.getLine(0).equalsIgnoreCase("[event]")) {
			
			// For item reward
			if(event.getLine(1).equalsIgnoreCase("item")) {	
				
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
						
						EventSign signevent = new EventSign(sign, item, amount);
						signs.add(signevent);
						player.sendMessage("§aSE §7| §7Your sign as been §aadded§7 to the list !");
						
						editSignLines(event, config, signevent, "block");
						
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
			
			// For money reward
			else if (event.getLine(1).equalsIgnoreCase("money")) {
				
				double amount;
				
				try {
					amount = Integer.parseInt(event.getLine(2));
				}catch(NumberFormatException e) {
					player.sendMessage("§cSE §7| §cYou do not provide a correct number at the 3th line.");
					sign.setType(Material.AIR);
					return;
				}
				
				
				if(amount != 0) {
									
					EventSign signevent = new EventSign(sign, amount);
					signs.add(signevent);
					player.sendMessage("§aSE §7| §7Your sign as been §aadded§7 to the list !");

					if(config.getBoolean("options.replaceLines")) editSignLines(event, config, signevent, "money");
				
				} 
				else {
					player.sendMessage("§cSE §7| §cYou can't give 0 money to a player ! That's illegal !!!");
					sign.setType(Material.AIR);
					return;
				}
				
			} else {
				if(! main.getConfig().getBoolean("options.disabledWarning")) player.sendMessage("§cSE §7| §cIf you were trying to put SignEvent sign, type /se help to see how to created them !");
				return;
			}
			
		}
		
	}


	public void editSignLines(SignChangeEvent event, FileConfiguration config, EventSign sign, String type) {

		event.setLine(0, config.getString("options.firstLine").replace("&", "§"));
		event.setLine(1, config.getString("options.secondLine").replace("&", "§"));
		event.setLine(2, config.getString("options.thirdLine").replace("&", "§"));
		event.setLine(3, config.getString("options.fourthLine").replace("&", "§"));
		
		int line = main.getConfig().getInt("options.putRewardOnSign") -1;
		
		if(line <= 3 && line >= 0) {
			
			if(type.equals("block")) {
				
				event.setLine(line, sign.getReward().getItemMeta().getDisplayName());
				
			} else {
				
				String symbol = config.getString("options.symbol");
				String moneyLine = config.getString("options.symbolOrder").replace("{amount}", sign.getPrice().toString()).replace("{symbol}", symbol);	
				event.setLine(line, moneyLine);
			}
			
		}
			
	}

}
