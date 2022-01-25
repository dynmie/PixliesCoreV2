package net.pixlies.core.listeners.menus;

import net.pixlies.core.menus.ClickButton;
import net.pixlies.core.menus.Menu;
import net.pixlies.core.menus.MenuButton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;

public class MenuListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) return;
        if (!(event.getClickedInventory().getHolder() instanceof Menu menu)) return;
        event.setCancelled(true);
        MenuButton button = menu.getButton(event.getRawSlot());
        if (button == null) return;
        if (event.isLeftClick())
            button.getDefaultClickButtons().forEach(ClickButton::onExecute);
        if (event.isRightClick()) {
            if (button.getRightClickButtons().isEmpty())
                button.getDefaultClickButtons().forEach(ClickButton::onExecute);
            else
                button.getRightClickButtons().forEach(ClickButton::onExecute);
        }
    }

    @EventHandler
    public void onDrag(InventoryDragEvent event) {
        if (event.getInventory().getHolder() instanceof Menu) event.setCancelled(true);
    }

    @EventHandler
    public void onMove(InventoryMoveItemEvent event) {
        if (event.getInitiator().getHolder() instanceof Menu) event.setCancelled(true);
        if (event.getDestination().getHolder() instanceof Menu) event.setCancelled(true);
        if (event.getSource().getHolder() instanceof Menu) event.setCancelled(true);
    }

}
