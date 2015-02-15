package com.arrayprolc.warps.util;

import org.bukkit.entity.Player;

import com.arrayprolc.warps.Warps;

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
		try {
			String list = Warps.getInstance().getConfig()
					.getString("allowedDefault");
			String[] allowed = list.split(",");
			for (String s : allowed) {
				if (permission.startsWith("warp.")) {
					if (s.equalsIgnoreCase("warps.warp.*")) {
						return true;
					}
				}
				if (s.equalsIgnoreCase(permission)) {
					return true;
				}
			}
		} catch (Exception ex) {
			// It's probably a NPE, that's okay.
			Warps.getInstance().getConfig().set("allowedDefault", "info.*");
			Warps.getInstance().saveConfig();
		}
		return p.hasPermission(prefix + permission);

	}
}
