package com.arrayprolc.warps.event;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ClickListener implements Listener {

	@EventHandler
	public void click(InventoryClickEvent e) {
		if (!e.getInventory().getTitle().equals("Warps")) {
			return;
		}
		try {

			String title = ChatColor.stripColor(
					e.getCurrentItem().getItemMeta().getDisplayName()).split(
					"Warp: ")[1];
			if (e.getClick().equals(ClickType.RIGHT)) {
				((Player) e.getWhoClicked()).performCommand("setwarp " + title);
			} else {
				((Player) e.getWhoClicked()).performCommand("warp " + title);
			}
			e.setCancelled(true);

		} catch (Exception ex) {

		}
	}
}
