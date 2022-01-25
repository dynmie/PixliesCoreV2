package net.pixlies.nations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.pixlies.core.modules.Module;
import net.pixlies.nations.commands.CommandManager;
import net.pixlies.nations.events.ListenerManager;
import net.pixlies.nations.handlers.HandlerManager;
import net.pixlies.nations.handlers.RegisterHandlerManager;
import net.pixlies.nations.nations.NationsManager;

public class Nations extends Module {

    private static Nations instance;

    private HandlerManager handlerManager;
    private ListenerManager listenerManager;
    private CommandManager commandManager;
    private NationsManager nationsManager;

    private final Gson gson = new GsonBuilder()
            .serializeNulls()
            .setPrettyPrinting()
            .create();

    @Override
    public void onLoad() {
        instance = this;

        handlerManager = new HandlerManager();
        new RegisterHandlerManager().registerAllHandlers();

        // LISTENERS
        listenerManager = new ListenerManager();
        listenerManager.registerAllListeners();

        // COMMANDS
        commandManager = new CommandManager();
        commandManager.registerAllCommands();

        // NATIONS
        nationsManager = new NationsManager();

    }

    @Override
    public void onDrop() {
        listenerManager.unregisterAllListeners();
        commandManager.unregisterAllCommands();
        instance = null;
    }

    public HandlerManager getHandlerManager() {
        return handlerManager;
    }

    public ListenerManager getListenerManager() {
        return listenerManager;
    }

    public NationsManager getNationsManager() {
        return nationsManager;
    }

    public Gson getGson() {
        return gson;
    }

    public static Nations getInstance() {
        return instance;
    }

}
