package fr.bobsmil3y.signevent;

import org.bukkit.plugin.java.JavaPlugin;

import fr.bobsmil3y.signevent.commands.SignEvent;
import fr.bobsmil3y.signevent.listeners.ClickOnSign;
import fr.bobsmil3y.signevent.listeners.CreateSign;


public class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		
		getCommand("signevent").setExecutor(new SignEvent());
		
		getServer().getPluginManager().registerEvents(new CreateSign(this), this);
		getServer().getPluginManager().registerEvents(new ClickOnSign(this), this);
		
		saveDefaultConfig();

	}

}
