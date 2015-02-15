package com.arrayprolc.warps;

import org.bukkit.Location;

public class WarpManager {

	private static WarpManager instance;

	public WarpManager() {
		instance = this;
	}

	public static WarpManager getInstance() {
		if (instance == null) {
			new WarpManager();
		}
		return instance;
	}

	public Warp getWarp(String name) {
		return new Warp(name);
	}

	public Warp createWarp(Location loc, String name) {
		return new Warp(loc, name);
	}

}
