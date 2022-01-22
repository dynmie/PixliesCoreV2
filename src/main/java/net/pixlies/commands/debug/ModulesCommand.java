package net.pixlies.commands.debug;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import net.pixlies.Main;
import net.pixlies.modules.ModuleDescription;
import net.pixlies.modules.Module;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CommandAlias("modules")
@CommandPermission("pixlies.debug.modules")
public class ModulesCommand extends BaseCommand {

    @Default
    @CommandCompletion("")
    @Description("Returns all loaded Modules")
    public void onModules(CommandSender commandSender) {
        commandSender.sendMessage("PixliesCoreV2 Modules: " + getModuleNames());
    }

    @HelpCommand
    public void onHelp(CommandSender sender, CommandHelp help) {
        help.showHelp();
    }

    public ModulesCommand() {

    }

    private Map<Module, ModuleDescription> modules = Main.getInstance().getModuleManager().getModules();

    private List<String> getModuleNames() {
        List<String> moduleNames = new ArrayList<>();
        for(Map.Entry<Module, ModuleDescription> entry : modules.entrySet()) {
            moduleNames.add(entry.getValue().getName());
        }
        return moduleNames;
    }

}
