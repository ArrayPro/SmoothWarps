package com.arrayprolc.warps.command;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.arrayprolc.warps.Warp;
import com.arrayprolc.warps.util.UtilPermission;
import com.arrayprolc.warps.util.UtilString;
import com.arrayprolc.warps.util.UtilWarp;

public class CommandSetWarp {

	public static boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (!label.equalsIgnoreCase("setwarp")) {
			return false;
		}

		if (!(sender instanceof Player)) {
			sender.sendMessage("Console is not currently supported. Check back later.");
			return false;
		}

		Player p = (Player) sender;

		if (args.length == 0) {
			p.sendMessage("§7/§bsetwarp §7[§bname§7]");
			return true;
		}

		if (!UtilPermission.hasPermission(p, "setwarp")) {
			sender.sendMessage("§7You do not have permission to do that.");
			return true;
		}

		String warpName = args[0].toLowerCase();
		Warp wa = new Warp(p.getLocation(), warpName);
		Material m = Material.ENDER_PEARL;
		try {
			if (!p.getItemInHand().getType().equals(Material.AIR)) {
				m = p.getItemInHand().getType();
			}
		} catch (Exception ex) {

		}
		UtilWarp.setWarpMaterial(warpName, m);
		sender.sendMessage("§bWarp §7named §b" + warpName
				+ "§7 created with icon §b" + UtilString.enumCaps(m.toString())
				+ "§7.");
		wa.save();
		UtilWarp.getWarps();
		return true;
	}
}
