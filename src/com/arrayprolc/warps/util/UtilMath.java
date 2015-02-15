package com.arrayprolc.warps.util;

public class UtilMath {

	public static int roundToNearest(int round, int total) {
		int i = 0;
		while (i < total) {
			i = i + round;
		}
		return i;
	}

}
