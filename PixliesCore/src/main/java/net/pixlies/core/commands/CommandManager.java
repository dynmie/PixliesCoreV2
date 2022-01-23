package net.pixlies.core.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.PaperCommandManager;
import lombok.Getter;
import net.pixlies.core.Main;
import net.pixlies.core.commands.debug.ModulesCommand;
import net.pixlies.core.commands.moderation.BanCommand;
import net.pixlies.core.commands.moderation.MuteCommand;
import net.pixlies.core.commands.moderation.TempBanCommand;
import net.pixlies.core.commands.moderation.TempMuteCommand;

public class CommandManager {

    private static final Main instance = Main.getInstance();

    private @Getter final PaperCommandManager pcm;

    public CommandManager() {
        pcm = new PaperCommandManager(instance);

        pcm.enableUnstableAPI("help");
        pcm.enableUnstableAPI("brigadier");

        registerAllCommands();
    }

    public void registerAllCommands() {
        // MODERATION
        register(new BanCommand());
        register(new TempBanCommand());
        register(new MuteCommand());
        register(new TempMuteCommand());

        // DEBUG
        register(new ModulesCommand());
    }

    public void register(BaseCommand command) {
        pcm.registerCommand(command);
    }

}
