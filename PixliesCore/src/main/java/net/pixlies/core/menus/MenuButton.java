package net.pixlies.core.menus;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class MenuButton {

    private final List<ClickButton> clickButtons = new ArrayList<>();
    private final ItemStack item;

    public MenuButton(ItemStack item) {
        this.item = item;
    }

    public MenuButton addEvent(ClickButton clickButton) {
        clickButtons.add(clickButton);
        return this;
    }

    public List<ClickButton> getClickButtons() {
        return clickButtons;
    }

    public ItemStack getItem() {
        return item;
    }

}
