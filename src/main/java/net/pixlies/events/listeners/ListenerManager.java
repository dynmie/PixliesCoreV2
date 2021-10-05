package net.pixlies.events.listeners;

import net.pixlies.Main;
import org.bukkit.Bukkit;

public class ListenerManager {

    private static final Main instance = Main.getInstance();

    public static void registerAllListeners() {
        Bukkit.getPluginManager().registerEvents(new LoginListener(), instance);
    }

}
