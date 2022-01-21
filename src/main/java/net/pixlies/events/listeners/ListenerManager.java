package net.pixlies.events.listeners;

import lombok.Getter;
import net.pixlies.Main;
import net.pixlies.events.listeners.moderation.MuteListener;
import net.pixlies.events.listeners.moderation.BanListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class ListenerManager {

    private static final Main instance = Main.getInstance();

    private @Getter static final List<Listener> listeners = new ArrayList<>(){{
        add(new BanListener());
        add(new MuteListener());
    }};

    private ListenerManager() {}

    public static void registerAllListeners() {
        for (Listener listener : listeners) {
            Bukkit.getPluginManager().registerEvents(listener, instance);
        }
    }

}
