package com.arrayprolc.warps;

import org.bukkit.Location;

public class Warp {

	Location loc;
	String name;

	public Warp(Location loc, String name) {
		this.loc = loc;
		this.name = name;
		save();
	}

	public Warp(String title) {
		name = title.toLowerCase();
		load();
	}

	public String getTitle() {
		return name;
	}

	public void save() {
		Warps.getInstance()
				.getConfig()
				.set("Warps." + name.toLowerCase(),
						new BoxedLocation(loc).toString());
		Warps.getInstance().saveConfig();
	}

	public void load() {
		loc = new BoxedLocation(Warps.getInstance().getConfig()
				.getString("Warps." + name)).unbox();
	}

	public Location getLocation() {
		return loc;
	}

}
