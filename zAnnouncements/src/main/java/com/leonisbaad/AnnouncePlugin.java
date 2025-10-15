package com.leonisbaad;

import org.bukkit.plugin.java.JavaPlugin;

public class AnnouncePlugin extends JavaPlugin {

    public void onEnable() {
        saveDefaultConfig();
        getCommand("announce").setExecutor(new AnnounceCommand(this));
        getCommand("zannounce").setExecutor(new ZAnnounceCommand(this));
    }

    public void onDisable() {
    }

    public String getCustomTitle() {
        return getConfig().getString("title", "Default Title");
    }

    public void reloadConfigFile() {
        reloadConfig();
    }
}