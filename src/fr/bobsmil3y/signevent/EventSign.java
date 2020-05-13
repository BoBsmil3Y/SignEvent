package fr.bobsmil3y.signevent;

import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;


public class EventSign {

	private Block sign;
	private int price; // For a money reward
	private ItemStack reward; // For an item reward
	
	
	public EventSign(Block sign, int price) {
		this.sign = sign;
		this.price = price;
	}
	
	public EventSign(Block sign, ItemStack reward) {
		this.sign = sign;
		this.reward = reward;
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
