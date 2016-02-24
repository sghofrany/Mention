package me.iran.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class MentionListener implements Listener {
	Mention plugin;
	
	public MentionListener(Mention plugin) {
		this.plugin = plugin;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onMention(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (event.getMessage().contains("@" + p.getName())) {
				if (p.getName() != player.getName()) {
					if(plugin.getConfig().getBoolean(p.getUniqueId() + ".enabled")) {
						p.sendMessage(ChatColor.GOLD
								+ "Mentioned By: "
								+ ChatColor.YELLOW + player.getName());

					} else {
						player.sendMessage(ChatColor.RED + "[Mention] " + p.getName() +" has their Metion's turned off!");
					}
					
					
				} else {
					 return;
				}

			}

		}
		
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if(!(plugin.getConfig().contains(player.getUniqueId() + ".enabled"))) {
			plugin.getConfig().set(player.getUniqueId() + ".enabled", true);
			plugin.saveConfig();
			return;
		}
	}

}
