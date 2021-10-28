package net.pixlies.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.pixlies.Main;
import net.pixlies.economy.Wallet;
import net.pixlies.localization.Lang;
import net.pixlies.moderation.Punishment;
import net.pixlies.moderation.PunishmentType;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerKickEvent;
import org.ocpsoft.prettytime.PrettyTime;

import java.util.*;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class User {

    private static final Main instance = Main.getInstance();

    private UUID uuid;
    private long joined;
    private String discordId;
    private Map<String, Wallet> wallets;
    private List<String> knownUsernames;
    private List<UUID> blockedUsers;
    private Map<String, Punishment> currentPunishments;
    private String lang;

    public OfflinePlayer getAsOfflinePlayer() {
        return Bukkit.getOfflinePlayer(uuid);
    }

    public Punishment getBan() {
        if (!currentPunishments.containsKey("ban")) return null;
        Punishment punishment = currentPunishments.get("ban");
        if (punishment.getUntil() - System.currentTimeMillis() <= 0) {
            currentPunishments.remove("ban");
            return null;
        }
        return punishment;
    }

    //TODO: Broadcast
    public Punishment ban(String reason, CommandSender punisher, boolean silent) {
        UUID punisherUUID = punisher.getName().equalsIgnoreCase("console") ? UUID.fromString("f78a4d8d-d51b-4b39-98a3-230f2de0c670") : ((Player)punisher).getUniqueId();
        Punishment punishment = new Punishment(UUID.randomUUID().toString(), PunishmentType.BAN, punisherUUID, System.currentTimeMillis(), reason, 0);
        currentPunishments.put("ban", punishment);
        if (silent)
            Lang.PLAYER_PERMANENTLY_BANNED.broadcastPermission("pixlies.moderation.silent", "%PLAYER%;" + this.getAsOfflinePlayer().getName(), "%EXECUTOR%;" + punisher.getName(), "%REASON%;" + reason);
        else
            Lang.PLAYER_PERMANENTLY_BANNED.broadcast("%PLAYER%;" + this.getAsOfflinePlayer().getName(), "%EXECUTOR%;" + punisher.getName(), "%REASON%;" + reason);
        return punishment;
    }

    //TODO: Broadcast
    public Punishment tempBan(String reason, CommandSender punisher, long duration, boolean silent) {
        UUID punisherUUID = punisher.getName().equalsIgnoreCase("console") ? UUID.fromString("f78a4d8d-d51b-4b39-98a3-230f2de0c670") : ((Player)punisher).getUniqueId();
        Punishment punishment = new Punishment(UUID.randomUUID().toString(), PunishmentType.BAN, punisherUUID, System.currentTimeMillis(), reason, duration + System.currentTimeMillis());
        currentPunishments.put("ban", punishment);
        if (silent)
            Lang.PLAYER_TEMPORARILY_BANNED.broadcastPermission("pixlies.moderation.silent", "%PLAYER%;" + this.getAsOfflinePlayer().getName(), "%EXECUTOR%;" + punisher.getName(), "%REASON%;" + reason, "%TIME%;" + new PrettyTime().format(new Date(punishment.getUntil())));
        else
            Lang.PLAYER_TEMPORARILY_BANNED.broadcast("%PLAYER%;" + this.getAsOfflinePlayer().getName(), "%EXECUTOR%;" + punisher.getName(), "%REASON%;" + reason, "%TIME%;" + new PrettyTime().format(new Date(punishment.getUntil())));
        return punishment;
    }

    public static User get(UUID uuid) {
        return instance.getDatabase().getUserCache().getOrDefault(uuid, getFromDatabase(uuid));
    }

    public static User getFromDatabase(UUID uuid) {
        Document profile = new Document("uniqueId", uuid.toString());
        Document found = instance.getDatabase().getUserCollection().find(profile).first();
        User data;
        if (found == null) {
            profile.append("joined", System.currentTimeMillis());
            profile.append("discordId", "NONE");
            profile.append("wallets", Wallet.getDefaultWallets());
            profile.append("knownUsernames", new ArrayList<>());
            profile.append("blockedUsers", new ArrayList<>());
            profile.append("currentPunishments", new HashMap<>());
            profile.append("lang", "ENG");

            instance.getDatabase().getUserCollection().insertOne(profile);

            data = new User(
                    uuid,
                    System.currentTimeMillis(),
                    "NONE",
                    Wallet.getDefaultWallets(),
                    new ArrayList<>(),
                    new ArrayList<>(),
                    new HashMap<>(),
                    "ENG"
            );

            Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "Profile for " + uuid + " created in Database.");
        } else {
            data = new User(
                    UUID.fromString(found.getString("uuid")),
                    found.getLong("joined"),
                    found.getString("discordId"),
                    Wallet.getFromMongo((Map<String, Map<String, Object>>) found.get("wallets", Map.class)),
                    found.getList("knownUsernames", String.class),
                    found.getList("blockedUsers", String.class).stream().map(UUID::fromString).collect(Collectors.toList()),
                    Punishment.getFromMongo((Map<String, Map<String, Object>>) found.get("currentPunishments")),
                    found.getString("lang")
            );
        }
        return data;
    }

    public void backup() {
        Document profile = new Document("uuid", uuid);
        Document found = instance.getDatabase().getUserCollection().find(profile).first();
        if (found == null) return;
        profile.append("joined", joined);
        profile.append("discordId", discordId);
        profile.append("wallets", Wallet.mapAllForMongo(wallets));
        profile.append("knownUsernames", knownUsernames);
        profile.append("blockedUsers", blockedUsers.stream()
                .map(UUID::toString)
                .collect(Collectors.toList())
        );
        profile.append("currentPunishments", Punishment.mapAllForMongo(currentPunishments));
        profile.append("lang", lang);
        instance.getDatabase().getUserCollection().replaceOne(found, profile);
    }

    public void save() {
        instance.getDatabase().getUserCache().put(uuid, this);
    }
    
}
