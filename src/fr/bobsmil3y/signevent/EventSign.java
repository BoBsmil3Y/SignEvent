package fr.bobsmil3y.signevent;

import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;


public class EventSign {

	private int total;
	
	private Block sign;
	private int price;
	
	private ItemStack reward;
	private int amount;
	
	
	public EventSign(Block sign, int amount2) {
		this.sign = sign;
		this.price = amount2;
		this.total++;
	}
	
	
	public EventSign(Block sign, ItemStack reward, int amount) {
		this.sign = sign;
		this.reward = reward;
		this.amount = amount;
		this.total++;
	}
	


	public int getAmount() {
		return amount;
	}

	
	public void setSign(Block sign) {
		this.sign = sign;
	}
	
	public Block getSign() {
		return sign;
	}

	
	public int getPrice() {
		return price;
	}


	public ItemStack getReward() {
		return reward;
	}

}
