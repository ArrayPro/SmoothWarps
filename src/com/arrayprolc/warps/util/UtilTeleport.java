package com.arrayprolc.warps.util;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

public class UtilTeleport {

	public static void teleport(Player p, Location loc) {
		if (p.getAllowFlight() || p.getGameMode().equals(GameMode.CREATIVE)) {
			if (loc.getBlock().getRelative(BlockFace.DOWN).getType()
					.equals(Material.AIR)) {
				p.setFlying(true);
			}
		}
		p.teleport(loc);
	}

}
