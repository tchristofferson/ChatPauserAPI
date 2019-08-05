package com.tchristofferson.chatpauserapi;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class ChatPauserAPI implements Listener {

    private final List<Player> pausedPlayers = new ArrayList<>();

    public ChatPauserAPI(Plugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public boolean pauseChat(Player player) {
        if (pausedPlayers.contains(player)) return false;
        pausedPlayers.add(player);
        return true;
    }

    public boolean resumeChat(Player player) {
        return pausedPlayers.remove(player);
    }

    public boolean isPaused(Player player) {
        return pausedPlayers.contains(player);
    }

    @EventHandler
    public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent event) {
        event.getRecipients().removeAll(pausedPlayers);
    }

}
