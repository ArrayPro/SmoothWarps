package com.arrayprolc.warps;

import java.io.Serializable;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class BoxedLocation implements Serializable {

	private static final long serialVersionUID = -3939940323003538249L;
	
	double x, y, z;
	float yaw, pitch;
	String worldName;
	
	public BoxedLocation(Location loc){
		x = loc.getX(); y = loc.getY(); z = loc.getZ(); worldName = loc.getWorld().getName();
		yaw = loc.getYaw(); pitch = loc.getPitch();
	}
	
	public BoxedLocation(String s){
		String[] ss = s.split(",");
		worldName = ss[0]; x = Double.parseDouble(ss[1]); y = Double.parseDouble(ss[2]); z = Double.parseDouble(ss[3]); 
		try {
		yaw = Float.parseFloat(ss[4]); pitch = Float.parseFloat(ss[5]); 
		}catch(Exception ex) {
			yaw = 0; pitch = 0;
		}
	}
	
	public Location unbox(){
		return new Location(Bukkit.getWorld(worldName), x, y, z, yaw, pitch);
	}
	
	@Override
	public boolean equals(Object b){
		
		if(!(b instanceof BoxedLocation)){
			return false;
		}
		BoxedLocation bl = (BoxedLocation)b;
		
		return x == bl.x && y == bl.y && z == bl.z && worldName.equals(bl.worldName);
	}
	
	public String toString(){
		return worldName + "," + x + "," + y + "," + z + "," + yaw + "," + pitch;
	}

}
