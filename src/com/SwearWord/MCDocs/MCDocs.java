package com.SwearWord.MCDocs;

import com.SwearWord.MCDocs.Events.PlayerEvents;
import com.SwearWord.MCDocs.Events.ServerEvents;
import com.SwearWord.MCDocs.Managers.ConfigManager;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MCDocs extends JavaPlugin
{

    private PlayerEvents pe = new PlayerEvents();

    public void onDisable()
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void onEnable()
    {
        try {
            ConfigManager.Initialize();
        } catch (Exception e) {
            return;
        }

        PluginManager pluginManager = this.getServer().getPluginManager();

        //Register Events
        pluginManager.registerEvent(Event.Type.PLUGIN_ENABLE,new ServerEvents(), Event.Priority.Normal,this);

        //Player Events
        pluginManager.registerEvent(Event.Type.PLAYER_JOIN,pe, Event.Priority.Normal,this);
        pluginManager.registerEvent(Event.Type.PLAYER_COMMAND_PREPROCESS,pe, Event.Priority.Normal,this);
    }
}
