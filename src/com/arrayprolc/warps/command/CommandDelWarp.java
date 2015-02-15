package com.arrayprolc.warps.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.arrayprolc.warps.util.UtilPermission;
import com.arrayprolc.warps.util.UtilString;
import com.arrayprolc.warps.util.UtilWarp;

public class CommandDelWarp {

	public static boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (!label.equalsIgnoreCase("delwarp")) {
			return false;
		}

		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (!UtilPermission.hasPermission(p, "delwarp")) {
				p.sendMessage("§7You do not have permission.");
				return true;
			}
		}
		if (args.length == 0) {
			UtilString.sendToSender(sender, "§7/§bdelwarp §7[§bwarp§7]");
			return false;
		}

		String warpName = args[0].toLowerCase();
		UtilWarp.removeWarp(warpName);
		UtilString.sendToSender(sender, "§bWarp §7named §b" + warpName
				+ "§7 disabled.");
		UtilWarp.getWarps();
		return true;
	}
}
