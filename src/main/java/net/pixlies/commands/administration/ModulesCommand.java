package net.pixlies.commands.administration;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import lombok.val;
import net.pixlies.Main;
import net.pixlies.utils.CC;
import org.bukkit.command.CommandSender;

@CommandAlias("module|modules")
@CommandPermission("pixlies.admin.module")
public class ModulesCommand extends BaseCommand {

    private static final Main instance = Main.getInstance();

    // TODO: Add language support
    @Subcommand("list")
    @Description("Returns a list of all loaded Modules")
    public void onList(CommandSender sender, String player, @Optional String reason) {
        val modules = instance.getModuleManager().getModules();
        sender.sendMessage(CC.format("&b&lMODULES")); // could replace this
        modules.values().forEach(description -> {
            sender.sendMessage(CC.format("&6" + description.getName() + "&7 v" + description.getVersion())); // and this
        });
        // Prob not the best way to do this, but it should work for now.
    }

    @Default
    @HelpCommand
    public void onHelp(CommandSender sender, CommandHelp help) {
        help.showHelp();
    }

}
