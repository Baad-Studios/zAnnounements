package com.leonisbaad;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AnnounceCommand implements CommandExecutor {
    private final AnnouncePlugin plugin;

    public AnnounceCommand(AnnouncePlugin plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            sender.sendMessage("Usage: /announce <text> (e.g. /announce welcome players)");
            return true;
        }

        String subtitle = String.join(" ", args);
        String title = plugin.getCustomTitle();

        String processedTitle = processColors(title);
        String processedSubtitle = processColors(subtitle);

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendTitle(processedTitle, processedSubtitle, 10, 70, 20);
        }

        sender.sendMessage("Announcement sent! Subtitle: " + subtitle);
        return true;
    }

    private String processColors(String text) {
        String processed = text.replace("&", "§");

        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("<#([A-Fa-f0-9]{6})>");
        java.util.regex.Matcher matcher = pattern.matcher(processed);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String hexCode = matcher.group(1);
            String replacement = "§x§" + hexCode.substring(0,1) + "§" + hexCode.substring(1,2) + "§" + hexCode.substring(2,3) + "§" + hexCode.substring(3,4) + "§" + hexCode.substring(4,5) + "§" + hexCode.substring(5,6);
            matcher.appendReplacement(sb, replacement);
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}