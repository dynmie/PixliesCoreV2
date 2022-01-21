package net.pixlies.commands.debug;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import org.bukkit.command.CommandSender;

import net.pixlies.modules.ModuleManager;

@CommandAlias("modules")
@CommandPermission("pixlies.debug.modules")
public class ListModulesCommand extends BaseCommand {

    @Description("Returns a list of all loaded Modules")
    public static void onModules(CommandSender sender, String player, @Optional String reason) {
        sender.sendMessage("PixliesCoreV2 Modules: " + ModuleManager.getModuleList());
    }

    @Default
    @HelpCommand
    public static void onHelp(CommandSender sender, CommandHelp help) {
        help.showHelp();
    }

    public ListModulesCommand() {

    }
}
