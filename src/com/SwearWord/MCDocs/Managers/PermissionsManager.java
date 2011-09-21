package com.SwearWord.MCDocs.Managers;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permissible;
import org.bukkit.permissions.PermissionAttachmentInfo;

public class PermissionsManager
{
    private static PermissionHandler oldperms = null;

    public static void Initialize(Permissions p)
    {
        oldperms = p.getHandler();
        OutputManager.LogInfo("Using Permissions.");
    }

    public static String GetGroup(Player player)
    {
        if(oldperms != null) return GetOldGroup(player);
        return GetSuperGroup(player);
    }

    private static String GetOldGroup(Player player)
    {
        return oldperms.getGroup(player.getWorld().getName(),player.getName());
    }

    private static String GetSuperGroup(Player player)
    {
        Permissible permissible = (Permissible)player;
        for(PermissionAttachmentInfo info : permissible.getEffectivePermissions())
        {
            if(!info.getValue()) continue;
            if(!info.getPermission().startsWith("mcdocs.group."))continue;
            String group = info.getPermission();
            group = group.substring(group.lastIndexOf(".")+1);
            return group;
        }
        return "default";
    }
}
