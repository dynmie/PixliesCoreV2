package net.pixlies.entity;

import lombok.Data;
import net.pixlies.economy.Wallet;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
public class User {

    private UUID uuid;
    private String discordId;
    private Map<String, Wallet> wallets;
    private List<String> knownUsernames;


    
}
