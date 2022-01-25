package net.pixlies.core.listeners;

import lombok.Getter;
import net.pixlies.core.Main;
import net.pixlies.core.listeners.menus.MenuListener;
import net.pixlies.core.listeners.moderation.BanListener;
import net.pixlies.core.listeners.moderation.MuteListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class ListenerManager {

    private static final Main instance = Main.getInstance();

    private @Getter static final List<Listener> listeners = new ArrayList<>(){{

        // MODERATION
        add(new BanListener());
        add(new MuteListener());

        // MENUS
        add(new MenuListener());

    }};

    private ListenerManager() {}

    public static void registerAllListeners() {
        for (Listener listener : listeners) {
            Bukkit.getPluginManager().registerEvents(listener, instance);
        }
    }

}
