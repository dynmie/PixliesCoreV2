package net.pixlies.commands;

import org.bukkit.command.CommandSender;

public interface ICommand {

    String getName();

    String getDescription();

    boolean onExecute(CommandSender sender, String aliasUsed, String[] args);

}
