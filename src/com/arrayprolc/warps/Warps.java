package com.arrayprolc.warps;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.arrayprolc.warps.command.CommandDelWarp;
import com.arrayprolc.warps.command.CommandSetWarp;
import com.arrayprolc.warps.command.CommandTP;
import com.arrayprolc.warps.command.CommandWarp;
import com.arrayprolc.warps.command.CommandWarps;
import com.arrayprolc.warps.event.ClickListener;
import com.arrayprolc.warps.event.CommandListener;

public class Warps extends JavaPlugin {

	private static Warps instance;

	public void onEnable() {
		instance = this;

		Bukkit.getServer().getPluginManager()
				.registerEvents(new ClickListener(), this);
		Bukkit.getServer().getPluginManager()
				.registerEvents(new CommandListener(), this);

		getConfig();
	}

	public static Warps getInstance() {
		return instance;
	}

	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {

		if (CommandWarp.onCommand(sender, command, label, args))
			return true;

		if (CommandSetWarp.onCommand(sender, command, label, args))
			return true;

		if (CommandTP.onCommand(sender, command, label, args))
			return true;

		if (CommandWarps.onCommand(sender, command, label, args))
			return true;

		if (CommandDelWarp.onCommand(sender, command, label, args))
			return true;

		return false;
	}

}
