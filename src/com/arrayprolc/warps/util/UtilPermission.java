package com.arrayprolc.warps.util;

import org.bukkit.entity.Player;

public class UtilPermission {

	private static String prefix = "warps.";

	public static boolean hasPermission(Player p, String permission) {
		if (p.isOp()) {
			return true;
		}
		if (permission.startsWith("warp.")) {
			if (p.hasPermission("warps.warp.*")) {
				return true;
			}
		}
		return p.hasPermission(prefix + permission);

	}
}
