package net.pixlies.events.listeners.join;

import net.kyori.adventure.text.Component;
import net.pixlies.entity.User;
import net.pixlies.localization.Lang;
import net.pixlies.moderation.Punishment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.ocpsoft.prettytime.PrettyTime;

import java.util.Collections;
import java.util.Date;

public class LoginListener implements Listener {

    private static final String BAN_BROADCAST_PERMISSION = "earth.ban.broadcast";

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        User user = User.get(event.getPlayer().getUniqueId());
        if (user.getCurrentPunishments().containsKey("ban")) {
            Punishment punishment = user.getBan();
            String banMessage = Lang.BAN_MESSAGE.get(event.getPlayer())
                            .replace("%REASON%", punishment.getReason())
                            .replace("%BAN_ID%", punishment.getID());

            if (punishment.getUntil() == 0) {
                banMessage = banMessage.replace("%DURATION%", "§4§lPERMANENT!");
            } else {
                PrettyTime prettyTime = new PrettyTime();
                banMessage = banMessage.replace("%DURATION%", prettyTime.format(new Date(punishment.getUntil())));
            }

            event.disallow(PlayerLoginEvent.Result.KICK_BANNED, Component.text(banMessage));
            Lang.BANNED_PLAYER_TRIED_TO_JOIN.broadcast(Collections.singletonMap("%PLAYER%", event.getPlayer().getName()), BAN_BROADCAST_PERMISSION);
        }
    }

}
