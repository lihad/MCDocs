package com.SwearWord.MCDocs.Events;

import com.SwearWord.MCDocs.Managers.PermissionsManager;
import com.nijikokun.bukkit.Permissions.Permissions;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.event.server.ServerListener;

public class ServerEvents extends ServerListener
{
    @Override
    public void onPluginEnable(PluginEnableEvent event)
    {
        if(event.getPlugin().getDescription().getName().equals("Permissions")) PermissionsManager.Initialize((Permissions)event.getPlugin());
    }
}
