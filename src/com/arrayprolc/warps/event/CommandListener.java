package com.arrayprolc.warps.event;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.help.HelpTopic;

import com.arrayprolc.warps.util.UtilWarp;

public class CommandListener implements Listener {

	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
		if (!event.isCancelled()) {
			Player player = event.getPlayer();
			String cmd = event.getMessage().split(" ")[0];
			HelpTopic topic = Bukkit.getServer().getHelpMap().getHelpTopic(cmd);
			if (topic == null) {
				String s = event.getMessage().replace("/", "").toLowerCase();
				s = s.replace(" ", "#");
				if (UtilWarp.getWarpsList().contains(s)) {
					event.getPlayer().performCommand("warp " + s);
					event.setCancelled(true);
				}
			}
		}
	}

}
