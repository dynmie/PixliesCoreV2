package net.pixlies.events.listeners;

import lombok.Getter;
import net.pixlies.Main;
import net.pixlies.events.listeners.chat.ChatListener;
import net.pixlies.events.listeners.join.LoginListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class ListenerManager {

    private static final Main instance = Main.getInstance();

    private @Getter static final List<Listener> listeners = new ArrayList<>(){{
        add(new LoginListener());
        add(new ChatListener());
    }};

    private ListenerManager() {}

    public static void registerAllListeners() {
        for (Listener listener : listeners) {
            Bukkit.getPluginManager().registerEvents(listener, instance);
        }
    }

}
