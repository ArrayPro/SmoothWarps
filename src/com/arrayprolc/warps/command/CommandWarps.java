package com.arrayprolc.warps.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.arrayprolc.warps.Warp;
import com.arrayprolc.warps.menu.WarpMenu;
import com.arrayprolc.warps.util.UtilWarp;

public class CommandWarps {
	public static boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (!label.equalsIgnoreCase("warps")) {
			return false;
		}
		try {
			if (!(sender instanceof Player)) {
				boolean start = true;
				String s = "";
				for (Warp warp : UtilWarp.getWarps()) {
					if (start) {
						s = s + warp;
						start = false;
					} else {
						s = s + ", " + warp;
					}
				}
				sender.sendMessage(s);
				return true;
			}
			Player p = (Player) sender;

			if (args.length == 0) {
				WarpMenu.openMenu(p, "");
				return true;
			}
			WarpMenu.openMenu(p, args[0].toLowerCase());
		} catch (Exception ex) {
			sender.sendMessage("§7There are no warps you can teleport to with that name.");
		}
		return true;
	}
}
