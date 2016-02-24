package me.iran.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Mention extends JavaPlugin implements Listener {

	Boolean isEnabled;

	
	@SuppressWarnings("deprecation")
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(new MentionListener(this), this);
		for(Player p : Bukkit.getOnlinePlayers()) {
			isEnabled = getConfig().getBoolean(p.getUniqueId() + ".enabled");
		}
		
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Player Command Only");
			return true;
		}
		
		Player player = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("mention")) {
			if(args.length > 0) {
				if(player.hasPermission("mention.use")) {
					if(args[0].equalsIgnoreCase("on")) {
						if(getConfig().getBoolean(player.getUniqueId() + ".enabled")) {
							player.sendMessage(ChatColor.RED + "[Mention] Already Enabled");
							return true;
						} else {
							getConfig().set(player.getUniqueId() + ".enabled", true);
							saveConfig();
							player.sendMessage(ChatColor.GOLD + "Mention: " + ChatColor.GREEN.toString() + ChatColor.BOLD + "Enabled");
							return true;
						}
						
					}
					
					if(args[0].equalsIgnoreCase("off")) {
						if(!getConfig().getBoolean(player.getUniqueId() + ".enabled")) {
							player.sendMessage(ChatColor.RED + "[Mention] Already Disabled");
							return true;
						} else {
							getConfig().set(player.getUniqueId() + ".enabled", false);
							saveConfig();
							player.sendMessage(ChatColor.GOLD + "Mention: " + ChatColor.RED.toString() + ChatColor.BOLD + "Disabled");
							return true;
						}
					}
				}
			} else {
				player.sendMessage(ChatColor.RED + "Too few arguments. /mention off | on");
				return true;
			}
		}
		
		return true;
	}

	

}
