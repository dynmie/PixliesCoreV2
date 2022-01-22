package net.pixlies;

import co.aikar.commands.PaperCommandManager;
import lombok.Getter;
import net.pixlies.commands.CommandManager;
import net.pixlies.database.MongoDB;
import net.pixlies.events.listeners.ListenerManager;
import net.pixlies.modules.ModuleManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static @Getter Main instance;

    private @Getter MongoDB database;
    private @Getter ModuleManager moduleManager;
    private @Getter CommandManager commandManager;


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
