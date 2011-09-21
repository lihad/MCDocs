package com.SwearWord.MCDocs.Managers;



import org.bukkit.util.config.Configuration;

import java.io.File;

public class ConfigManager
{
    private static File directory = new File("plugins/MCDocs");
    private static File configfile = new File(directory + "/config.yml");
    private static Configuration config;

    public static void Initialize() throws Exception
    {
        if(!directory.exists()) directory.mkdir();
        if(!configfile.exists()) configfile.createNewFile();
        config = new Configuration(configfile);
        config.load();
    }

    public static Boolean IsValid(String command)
    {
        return config.getNodes("commands").containsKey(command);
    }

    public static String GetFile(String command, String group)
    {
        String file = config.getString("commands" + "." + command + "." + group);
        if(file==null) file = config.getString("commands" + "." + command + "." + "default");
        return file;
    }




}
