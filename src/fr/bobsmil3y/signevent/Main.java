package fr.bobsmil3y.signevent;

import java.util.logging.Logger;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import fr.bobsmil3y.signevent.commands.SignEvent;
import fr.bobsmil3y.signevent.listeners.ClickOnSign;
import fr.bobsmil3y.signevent.listeners.CreateSign;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;


public class Main extends JavaPlugin {

	private static final Logger log = Logger.getLogger("Minecraft");
	private static Economy econ = null;
    private static Permission perms = null;

    
	@Override
	public void onEnable() {
		
		if (!setupEconomy() ) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        setupPermissions();
        
		getCommand("signevent").setExecutor(new SignEvent());
		
		getServer().getPluginManager().registerEvents(new CreateSign(this), this);
		getServer().getPluginManager().registerEvents(new ClickOnSign(this), this);
		
		saveDefaultConfig();

	}
	
	
	private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
    
    
    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }
	
    public static Economy getEconomy() {
        return econ;
    }
    
    public static Permission getPermissions() {
        return perms;
    }
    

}
