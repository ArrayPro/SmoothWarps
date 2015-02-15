package com.arrayprolc.warps.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.arrayprolc.warps.Warp;
import com.arrayprolc.warps.util.UtilPermission;
import com.arrayprolc.warps.util.UtilTeleport;

public class CommandWarp {

	public static boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (!label.equalsIgnoreCase("warp")) {
			return false;
		}

		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (args.length == 0) {
				sender.sendMessage("§cUsage: §7/§bwarp§7 [§bname§7]");
				return true;
			}
			String warp = args[0].toLowerCase();
			Player target = p;
			if (args.length == 2) {
				if (!UtilPermission.hasPermission(p, "send.other")) {
					sender.sendMessage("§7You do not have permission §bto warp others§7.");
					return true;
				}
				Player working = getPlayer(args[1].toLowerCase());
				if (working == null) {
					sender.sendMessage("§7That is §bnot a player§7.");
					return true;
				}
				target = working;
			}

			if (target.getUniqueId().equals(p.getUniqueId())) {
				if (!UtilPermission.hasPermission(target, "warp." + warp)) {
					sender.sendMessage("§7You §bdo not §7have §bpermission§7.");
					return true;
				}
			}

			try {
				UtilTeleport.teleport(target, new Warp(warp).getLocation());
				sender.sendMessage("§7Teleporting §b" + target.getName()
						+ " §7to §b" + warp.toLowerCase().replace("#", " ")
						+ "§7.");
				return true;
			} catch (Exception ex) {
				ex.printStackTrace();
				sender.sendMessage("§7That warp §bdoes not exist§7.");
				return true;
			}
		}

		sender.sendMessage("Console is currently not supported. Check back later!");

		return true;
	}

	@SuppressWarnings("deprecation")
	private static Player getPlayer(String name) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (p.getName().equalsIgnoreCase(name)) {
				return p;
			}
		}
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (p.getName().toLowerCase().startsWith(name.toLowerCase())) {
				return p;
			}
		}
		return null;
	}
}
