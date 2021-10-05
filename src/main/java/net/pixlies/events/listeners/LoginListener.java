package net.pixlies.events.listeners;

import net.pixlies.entity.User;
import net.pixlies.moderation.Punishment;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class LoginListener implements Listener {

    private static final String BAN_BROADCAST_PERMISSION = "earth.ban.broadcast";

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        User user = User.get(event.getPlayer().getUniqueId());
        if (user.getCurrentPunishments().containsKey("ban")) {
            Punishment punishment = user.getCurrentPunishments().get("ban");
            //TODO BAN MESSAGE
            event.disallow(PlayerLoginEvent.Result.KICK_BANNED, "hi");
            Bukkit.broadcast("hi", BAN_BROADCAST_PERMISSION);
        }
    }

}
