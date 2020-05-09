package fr.bobsmil3y.signevent;

import java.util.ArrayList;

import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public class EventSigns {

	private int code;
	private Block sign;
	private Double price;
	private ItemStack reward;
	private int amount;
	
	private int total;
	
	
	public EventSigns(Block sign, Double price) {
		this.setSign(sign);
		this.setPrice(price);
		this.code = total;
		this.total++;
	}
	
	
	public EventSigns(Block sign, ItemStack reward, int amount) {
		this.setSign(sign);
		this.setReward(reward);
		this.amount = amount;
		this.code = total;
		this.total++;
	}
	
	
	public Block getSign() {
		return sign;
	}

	public void setSign(Block sign) {
		this.sign = sign;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public ItemStack getReward() {
		return reward;
	}

	public void setReward(ItemStack reward) {
		this.reward = reward;
	}

	
}
