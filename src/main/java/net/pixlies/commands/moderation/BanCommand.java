package net.pixlies.commands.moderation;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import jdk.jfr.Description;
import net.pixlies.entity.User;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

//TODO: With Reason
@CommandAlias("ban")
@CommandPermission("pixlies.moderation.ban")
public class BanCommand extends BaseCommand {

    @Syntax("<player>")
    @CommandCompletion("@players")
    @Description("Bans player with the default reason")
    public static void onBan(CommandSender sender, @Single String target) {
        OfflinePlayer targetOP = Bukkit.getOfflinePlayerIfCached(target);
        if (targetOP == null) {
            //TODO: Message
            return;
        }
        User user = User.get(targetOP.getUniqueId());
        //TODO: Default reason
        user.ban("DEFAULT", sender);
    }

    @Default
    @HelpCommand
    public static void onHelp(CommandSender sender, CommandHelp help) {
        help.showHelp();
    }

    public BanCommand() {}

}
