package net.pixlies.commands.moderation;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.pixlies.Main;
import net.pixlies.entity.User;
import net.pixlies.localization.Lang;
import net.pixlies.utils.TimeUnit;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

@CommandAlias("tempmute")
@CommandPermission("pixlies.moderation.tempmute")
public class TempMuteCommand extends BaseCommand {

    @CommandCompletion("@players")
    @Description("Temporarily mutes player with the default reason")
    public static void onTempMute(CommandSender sender, String player, String duration, @Optional String reason) {
        boolean silent = false;
        long durationLong = TimeUnit.getDuration(duration);
        String muteReason = Main.getInstance().getConfig().getString("moderation.defaultReason", "No reason given");
        if (reason != null && !reason.isEmpty()) {
            muteReason = reason.replace("-s", "");
            if (reason.endsWith("-s"))
                silent = true;
        }

        OfflinePlayer targetOP = Bukkit.getOfflinePlayerIfCached(player);
        if (targetOP == null) {
            Lang.PLAYER_DOESNT_EXIST.send(sender);
            return;
        }

        User user = User.get(targetOP.getUniqueId());
        user.tempMute(muteReason, sender, durationLong, silent);
    }

    @Default
    @HelpCommand
    public static void onHelp(CommandSender sender, CommandHelp help) {
        help.showHelp();
    }

    public TempMuteCommand() {}

}
