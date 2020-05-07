package fr.bobsmil3y.signevent;

import org.bukkit.plugin.java.JavaPlugin;

import fr.bobsmil3y.signevent.commands.Event;
import fr.bobsmil3y.signevent.listeners.CreateSign;


public class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		
		getCommand("event").setExecutor(new Event());
		
		getServer().getPluginManager().registerEvents(new CreateSign(this), this);
		/*
		 * getCommand("command").setExecutor(new Class());
        	getServer().getPluginManager().registerEvents(new Event(), this);
        	getServer().getPluginManager().registerEvents(new Event(this), this);
		 * 
		 * */
		
	}

}
