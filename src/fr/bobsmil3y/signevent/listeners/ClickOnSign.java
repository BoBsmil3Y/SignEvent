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
import org.bukkit.inventory.ItemStack;

import fr.bobsmil3y.signevent.EventSign;
import fr.bobsmil3y.signevent.Main;
import net.milkbowl.vault.economy.Economy;

public class ClickOnSign implements Listener {

	@SuppressWarnings("unused")
	private Main main;
	
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
				EventSign signToRemove = null;
				
				for(EventSign sign : signs) {
					if(event.getClickedBlock().equals(sign.getSign())) {

						if(sign.getReward() != null) {
							
							ItemStack reward = sign.getReward();
							
							player.getInventory().addItem(reward);
							
							signToRemove = sign;		
							
							sign.getSign().setType(Material.AIR);
							player.sendMessage("Bravo tu as trouvé un panneau ! Tu gagnes " + sign.getReward().getAmount() + "x " + sign.getReward().getType());
							
						} else {
							
							Economy eco = Main.getEconomy();
							
							eco.depositPlayer(player, sign.getPrice());
							
							sign.getSign().setType(Material.AIR);
							signToRemove = sign;
							
							player.sendMessage("Bravo tu as trouvé un panneau ! Tu gagnes " + sign.getPrice() + "€.");
							
						}
						
					}
				}
				
				if(signToRemove != null) signs.remove(signToRemove);
				
			}
			
		}
		
		
	}
	
}
