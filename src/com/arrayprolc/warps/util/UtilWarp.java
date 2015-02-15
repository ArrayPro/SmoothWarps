package com.arrayprolc.warps.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.configuration.MemorySection;

import com.arrayprolc.warps.Warp;
import com.arrayprolc.warps.Warps;

public class UtilWarp {

	public static ArrayList<Warp> getWarps() {
		Set<String> s = ((MemorySection) Warps.getInstance().getConfig()
				.get("Warps")).getKeys(false);
		ArrayList<Warp> list = new ArrayList<Warp>();
		for (String ss : s) {
			try {
				list.add(new Warp(ss));
			} catch (Exception ex) {

			}
		}
		return list;
	}

	public static ArrayList<String> getWarpsList() {
		Set<String> s = ((MemorySection) Warps.getInstance().getConfig()
				.get("Warps")).getKeys(false);
		ArrayList<String> ss = new ArrayList<String>();
		ss.addAll(s);
		return ss;
	}

	public static ArrayList<Warp> getWarpsAlph() {
		Set<String> s1 = ((MemorySection) Warps.getInstance().getConfig()
				.get("Warps")).getKeys(false);
		ArrayList<String> s = new ArrayList<String>(s1);
		Collections.sort(s);
		ArrayList<Warp> list = new ArrayList<Warp>();
		for (String ss : s) {
			try {
				list.add(new Warp(ss));
			} catch (Exception ex) {

			}
		}
		return list;
	}

	public static void removeWarp(String warpName) {
		Warps.getInstance().getConfig()
				.set("Warps." + warpName.toLowerCase(), null);
		Warps.getInstance().saveConfig();
	}

	public static Material getWarpMaterial(String warpName) {
		warpName = warpName.toLowerCase();
		try {
			Material m = (Material) (Material.getMaterial(Warps.getInstance()
					.getConfig()
					.getString("WarpMaterial." + warpName.toLowerCase())));
			if (m != null) {
				return m;
			}
		} catch (Exception ex) {

		}
		setWarpMaterial(warpName, Material.ENDER_PEARL);
		return Material.ENDER_PEARL;

	}

	public static void setWarpMaterial(String warpName, Material m) {
		Warps.getInstance().getConfig()
				.set("WarpMaterial." + warpName.toLowerCase(), m.toString());
	}

}
