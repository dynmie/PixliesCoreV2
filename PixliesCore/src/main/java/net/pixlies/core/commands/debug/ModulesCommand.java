package net.pixlies.core.commands.debug;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import net.pixlies.core.Main;
import net.pixlies.core.modules.ModuleDescription;
import net.pixlies.core.utils.CC;
import org.bukkit.command.CommandSender;

@CommandAlias("modules|modman")
@CommandPermission("pixlies.debug.modules")
public class ModulesCommand extends BaseCommand {

    private static final Main instance = Main.getInstance();

    @Subcommand("list")
    @CommandCompletion("@empty")
    @Description("Returns all loaded Modules")
    @CommandPermission("pixlies.debug.modules.list")
    public void onModules(CommandSender sender) {
        sender.sendMessage("");
        sender.sendMessage("&b&lMODULES");
        for (ModuleDescription description : instance.getModuleManager().getModules().values()) {
            sender.sendMessage(CC.format("&6" + description.getName() + " &7v" + description.getVersion()));
        }
        sender.sendMessage("");
    }

    @Subcommand("reload")
    @CommandCompletion("@empty")
    @Description("Reloads all modules")
    @CommandPermission("pixlies.debug.modules.reload")
    public void onReload(CommandSender sender) {
        instance.getModuleManager().reloadModules();
        sender.sendMessage(CC.format("&7Reloaded all modules!"));
    }

    @Default
    @HelpCommand
    public void onHelp(CommandHelp help) {
        help.showHelp();
    }

}
