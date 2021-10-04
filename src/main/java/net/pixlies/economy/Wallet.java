package net.pixlies.economy;

import lombok.Data;
import net.pixlies.utils.Palette;
import org.bukkit.ChatColor;

import java.util.List;

@Data
public class Wallet {

    private String name;
    private String sign;
    private double balance;
    private List<String> history;
    private boolean prefix;
    private Palette palette;

    public void deposit(double amount, String reason) {
        this.balance += amount;
        history.add("+;" + amount + ";" + reason + ";" + System.currentTimeMillis());
    }

    public boolean withdraw(double amount, String reason) {
        if (this.balance - amount < 0) return false;
        this.balance -= amount;
        this.history.add("-;" + amount + ";" + reason + ";" + System.currentTimeMillis());
        return true;
    }

    public String formatBalance() {
        StringBuilder builder = new StringBuilder();
        if (prefix) {
            builder.append(palette.getEffect());
            builder.append(palette.getAccent());
            builder.append(sign);
            builder.append(ChatColor.RESET);
        }
        builder.append(palette.getPrimary());
        builder.append(balance);
        if (!prefix) {
            builder.append(palette.getEffect());
            builder.append(palette.getAccent());
            builder.append(sign);
            builder.append(ChatColor.RESET);
        }
        return builder.toString();
    }

}
