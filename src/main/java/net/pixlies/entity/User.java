package net.pixlies.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.pixlies.Main;
import net.pixlies.economy.Wallet;
import net.pixlies.moderation.Punishment;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

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

    public static User get(UUID uuid) {
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
            instance.getDatabase().getUserCollection().insertOne(profile);
            data = new User(
                    uuid,
                    System.currentTimeMillis(),
                    "NONE",
                    Wallet.getDefaultWallets(),
                    new ArrayList<>(),
                    new ArrayList<>(),
                    new HashMap<>()
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
                    Punishment.getFromMongo((Map<String, Map<String, Object>>) found.get("currentPunishments"))
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
        instance.getDatabase().getUserCollection().replaceOne(found, profile);
    }

    public void save() {
        instance.getDatabase().getUserCache().put(uuid, this);
    }
    
}
