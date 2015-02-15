package com.arrayprolc.warps.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.arrayprolc.warps.util.UtilPermission;
import com.arrayprolc.warps.util.UtilTeleport;

public class CommandTP {

	public static boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (!label.equalsIgnoreCase("tp")) {
			return false;
		}
		
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (!UtilPermission.hasPermission(p, "tp")) {
				p.sendMessage("§7You do not have permission.");
				return true;
			}
		}
		
		if (!(sender instanceof Player)) {
			sender.sendMessage("Console is not currently supported. Check back later.");
			return false;
		}
		Player p = (Player) sender;

		if (args.length == 0) {
			p.sendMessage("§7/§btp §7[§bname§7] or §7/§btp §7[§bname§7] §7[§bname§7]");
			return true;
		}
		Player target = p;
		Player location = p;

		if (args.length == 1) {
			location = getPlayer(args[0].toLowerCase());
			if (location == null) {
				sender.sendMessage("§7" + args[0] + " §bis not a player.");
				return false;
			}
		} else {
			target = getPlayer(args[0].toLowerCase());
			if (target == null) {
				sender.sendMessage("§7" + args[0] + " §bis not a player.");
				return false;
			}
			location = getPlayer(args[1].toLowerCase());
			if (location == null) {
				sender.sendMessage("§7" + args[1] + " §bis not a player.");
				return false;
			}
		}
		UtilTeleport.teleport(target, location.getLocation());
		sender.sendMessage("§7Teleporting §b" + target.getName() + " §7to §b"
				+ location.getName() + "§7.");

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
