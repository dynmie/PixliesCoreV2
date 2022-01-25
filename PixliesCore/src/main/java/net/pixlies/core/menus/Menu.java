package net.pixlies.core.menus;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class Menu implements InventoryHolder {

    private final Inventory inventory;
    private final Map<Integer, MenuButton> buttons = new HashMap<>();

    public Menu(int size, String title) {
        this.inventory = Bukkit.createInventory(this, size, Component.text(title));
    }

    public Map<Integer, MenuButton> getButtons() {
        return buttons;
    }

    public MenuButton getButton(int slot) {
        if (buttons.isEmpty()) return null;
        return buttons.get(slot);
    }

    public void setButton(int slot, MenuButton button) {
        if (!buttons.isEmpty()) {
            if (buttons.containsKey(slot)) {
                buttons.remove(slot);
                buttons.put(slot, button);
            }
        }
        buttons.putIfAbsent(slot, button);
    }

    @Override
    public @NotNull Inventory getInventory() {
        for (Map.Entry<Integer, MenuButton> entry : buttons.entrySet()) {
            int slot = entry.getKey();
            MenuButton button = entry.getValue();
            inventory.setItem(slot, button.getItem());
        }
        return inventory;
    }

}
