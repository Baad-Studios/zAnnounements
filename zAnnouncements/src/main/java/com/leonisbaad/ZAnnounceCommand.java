package com.leonisbaad;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ZAnnounceCommand implements CommandExecutor {
    private final AnnouncePlugin plugin;

    public ZAnnounceCommand(AnnouncePlugin plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("zannounce.reload")) {
            sender.sendMessage("You don't have permission to use this command.");
            return true;
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            plugin.reloadConfigFile();
            sender.sendMessage("Configuration reloaded successfully!");
            return true;
        }

        sender.sendMessage("Usage: /zannounce reload");
        return true;
    }
}