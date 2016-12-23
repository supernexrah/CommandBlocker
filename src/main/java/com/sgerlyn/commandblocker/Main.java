package com.sgerlyn.commandblocker;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("commandblocker.override")) {
            return;
        }
        String command = event.getMessage().toLowerCase();

        for (String blacklist : getConfig().getStringList("commands")) {
            if (command.contains(blacklist.toLowerCase())) {
                event.setCancelled(true);
                player.sendMessage(getConfig().getString("no-permission"));
            }
        }
    }
}
