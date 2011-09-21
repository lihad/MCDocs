package com.SwearWord.MCDocs.Events;

import com.SwearWord.MCDocs.Managers.ConfigManager;
import com.SwearWord.MCDocs.Managers.OutputManager;
import com.SwearWord.MCDocs.Managers.PermissionsManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;

public class PlayerEvents extends PlayerListener
{
    @Override
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        Bukkit.getServer().getPluginManager().callEvent(new PlayerCommandPreprocessEvent(event.getPlayer(),"/motd"));
    }



    @Override
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event)
    {
        String[] args = event.getMessage().split(" ");
        String command = args[0].substring(1);
        if(!ConfigManager.IsValid(command)) return;
        Player player = event.getPlayer();
        event.setCancelled(true);
        String group = PermissionsManager.GetGroup(player);
        String file = ConfigManager.GetFile(command,group);
        if(args.length > 1)
        {
            try
            {
                OutputManager.DisplayFile(player,file,command,Integer.parseInt(args[1]));
                return;
            }
            catch (NumberFormatException ex) {};
        }
        OutputManager.DisplayFile(player,file,command,1);
    }
}
