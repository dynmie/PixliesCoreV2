package net.pixlies;

import lombok.Getter;
import net.pixlies.database.MongoDB;
import net.pixlies.events.listeners.ListenerManager;
import org.bukkit.plugin.java.JavaPlugin;

//TODO
public class Main extends JavaPlugin {

    private static @Getter Main instance;

    private @Getter MongoDB database;

    @Override
    public void onEnable() {
        instance = this;
        database = new MongoDB().init();
        ListenerManager.registerAllListeners();
    }

    @Override
    public void onDisable() {

    }

}
