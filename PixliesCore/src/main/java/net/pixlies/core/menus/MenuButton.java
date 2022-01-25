package net.pixlies.core.menus;

import lombok.Getter;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class MenuButton {

    @Getter private final List<ClickButton> defaultClickButtons = new ArrayList<>();
    @Getter private final List<ClickButton> rightClickButtons = new ArrayList<>();
    @Getter private final ItemStack item;

    public MenuButton(ItemStack item) {
        this.item = item;
    }

    public MenuButton addDefaultEvent(ClickButton clickButton) {
        defaultClickButtons.add(clickButton);
        return this;
    }

    public MenuButton addRightEvent(ClickButton clickButton) {
        rightClickButtons.add(clickButton);
        return this;
    }

}
