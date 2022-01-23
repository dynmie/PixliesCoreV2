package net.pixlies.nations;

import net.pixlies.nations.commands.CommandManager;
import net.pixlies.nations.events.ListenerManager;
import net.pixlies.nations.handlers.HandlerManager;
import net.pixlies.nations.handlers.RegisterHandlerManager;
import net.pixlies.core.modules.Module;

public class Nations extends Module {

    private static Nations instance;
    private HandlerManager handlerManager;

    @Override
    public void onLoad() {
        instance = this;
        handlerManager = new HandlerManager();
        new RegisterHandlerManager().registerAllHandlers();
        new ListenerManager().registerAllListeners();
        new CommandManager().registerAllCommands();
    }

    @Override
    public void onDrop() {
        instance = null;
        handlerManager = null;
    }

    public HandlerManager getHandlerManager() {
        return handlerManager;
    }

    public static Nations getInstance() {
        return instance;
    }

}
