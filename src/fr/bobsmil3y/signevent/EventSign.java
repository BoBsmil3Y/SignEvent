package fr.bobsmil3y.signevent;

import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;


public class EventSign {

	private int total;
	
	private Block sign;
	private Double price;
	
	private ItemStack reward;
	private int amount;
	
	
	public EventSign(Block sign, Double price) {
		this.sign = sign;
		this.price = price;
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

	
	public Block getSign() {
		return sign;
	}

	
	public Double getPrice() {
		return price;
	}


	public ItemStack getReward() {
		return reward;
	}

}
