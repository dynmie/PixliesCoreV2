package net.pixlies.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.PaperCommandManager;
import lombok.Getter;
import net.pixlies.Main;
import net.pixlies.commands.moderation.BanCommand;

public class CommandManager {

    private static final Main instance = Main.getInstance();

    private @Getter final PaperCommandManager pcm;

    public CommandManager() {
        this.pcm = new PaperCommandManager(instance);
        pcm.enableUnstableAPI("help");
        pcm.enableUnstableAPI("brigadier");
    }

    public void registerAllCommands() {
        register(new BanCommand());
    }

    public void register(BaseCommand command) {
        pcm.registerCommand(command);
    }

}
