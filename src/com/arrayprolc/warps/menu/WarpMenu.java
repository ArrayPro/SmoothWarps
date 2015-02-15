package com.arrayprolc.warps.menu;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.arrayprolc.warps.Warp;
import com.arrayprolc.warps.util.UtilItem;
import com.arrayprolc.warps.util.UtilMath;
import com.arrayprolc.warps.util.UtilPermission;
import com.arrayprolc.warps.util.UtilWarp;

public class WarpMenu {

	public static void openMenu(Player p, String startsWith){
		ArrayList<Warp> warps = new ArrayList<Warp>();
		ArrayList<Warp> allowed = new ArrayList<Warp>();
		warps.addAll(UtilWarp.getWarps());
		for(Warp warp : warps){
			if(hasPermissionForWarp(p, warp.getTitle())){
				if(startsWith(warp.getTitle(), startsWith)){
					allowed.add(warp);
				}
			}
		}
		
		Inventory i = Bukkit.createInventory(null, UtilMath.roundToNearest(9, allowed.size()), "Warps");
		for(Warp warp : allowed){
			if(UtilPermission.hasPermission(p, "setwarp")) {
				i.addItem(UtilItem.setName(new ItemStack(UtilWarp.getWarpMaterial(warp.getTitle())), "§7Warp: §b" + warp.getTitle(), new String[] { "§7§oClick to teleport." , "§7§oRight-Click to move warp."}));
			}else{
				i.addItem(UtilItem.setName(new ItemStack(UtilWarp.getWarpMaterial(warp.getTitle())), "§7Warp: §b" + warp.getTitle(), new String[] { "§7§oClick to teleport." }));
			}
		}
		p.openInventory(i);
	}

	
	private static boolean hasPermissionForWarp(Player target, String warp){
		return UtilPermission.hasPermission(target, "warp." + warp);
	}
	
	private static boolean startsWith(String s, String start){
		if(start.equals("")){
			return true;
		}
		return s.startsWith(start);
	}
}
