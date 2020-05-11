package fr.bobsmil3y.signevent.listeners;


import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.bobsmil3y.signevent.EventSign;
import fr.bobsmil3y.signevent.Main;

public class ClickOnSign implements Listener {

	Main main;
	
	public ClickOnSign(Main main) {
		this.main = main;
	}
	
	@EventHandler
	public void onClickOnSign(PlayerInteractEvent event) {
		
		Player player = event.getPlayer();
		Action action = event.getAction();
		

		if(event.getClickedBlock() != null && (action == Action.LEFT_CLICK_BLOCK || action == Action.RIGHT_CLICK_BLOCK)) {
			
			BlockState blockstate = event.getClickedBlock().getState();
			
			if(blockstate instanceof Sign) {
				
				ArrayList<EventSign> signs = CreateSign.getSigns();
				
				for(EventSign sign : signs) {
					if(event.getClickedBlock().equals(sign.getSign())) {
						if(sign.getReward() != null) {
							
							/*
							 * Give the item
							 * Delete the sign in array list
							 * Delete the sign in game
							 * Rename the item by the name of the player (with an option to disabled)
							 * */
							sign.getSign().setType(Material.AIR);
							player.sendMessage("Bravo tu as trouvé un panneau ! Tu gagnes " + sign.getReward().getAmount() + "x " + sign.getReward());
							
						} else {
							
							/*
							 * Give the money
							 * Delete the sign in array list
							 * Delete the sign in game
							 * */
							sign.getSign().setType(Material.AIR);
							player.sendMessage("Bravo tu as trouvé un panneau ! Tu gagnes " + sign.getPrice() + "€.");
							
						}
						
					}
				}
				
				
			}
			
		}
		
		
	}
	
}
