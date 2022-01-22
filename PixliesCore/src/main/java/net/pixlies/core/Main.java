package net.pixlies.core;

import lombok.Getter;
import net.pixlies.core.commands.CommandManager;
import net.pixlies.core.database.MongoDB;
import net.pixlies.core.modules.ModuleManager;
import net.pixlies.core.listeners.ListenerManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static @Getter Main instance;

    @Getter private MongoDB database;
    @Getter private ModuleManager moduleManager;
    @Getter private CommandManager commandManager;

    @Override
    public void onEnable() {
        instance = this;

        database = new MongoDB().init();
        moduleManager = new ModuleManager();
        moduleManager.loadModules();

        ListenerManager.registerAllListeners();
        commandManager = new CommandManager();
    }

    @Override
    public void onDisable() {
        moduleManager.unloadModules();
    }

}
