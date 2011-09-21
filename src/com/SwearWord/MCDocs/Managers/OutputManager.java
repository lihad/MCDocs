package com.SwearWord.MCDocs.Managers;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

public class OutputManager
{
    private static Logger logger = Logger.getLogger("minecraft");
    private static String prefix = "[MCDocs] ";

    public static void LogInfo(String msg)
    {
        logger.info(prefix + msg);
    }


    public static void DisplayFile(Player player, String file, String command, int page)
    {
        try
        {
            ArrayList<String> lines = LoadFile(file);
            int size = lines.size();
            int pages;
            if(size % 9 == 0){
                pages = size / 9;
            }
            else{
                pages = size / 9 + 1;
            }

            String header = ChatColor.RED + command.toUpperCase() + " - " + "Page "+page+" of " + pages + " | " + "/" +command + " <page>";
            if(pages != 1) player.sendMessage(header);
            int highNum = (page * 9);
            int lowNum = (page - 1) * 9;
            for (int number = lowNum; ((number < highNum) && (number < size)); number++){
                String line = VariableSwap(player,ColorSwap(lines.get(number)));
                player.sendMessage(line);
            }

        }
        catch(FileNotFoundException ex) {player.sendMessage(file + " not found.");}
    }

    private static String VariableSwap(Player player, String line)
    {
        String fixedLine = line.replace("%name", player.getDisplayName());
        fixedLine = fixedLine.replace("%size", player.getServer().getOnlinePlayers().length + "");
        fixedLine = fixedLine.replace("%world", player.getWorld().getName());
        fixedLine = fixedLine.replace("%ip", player.getAddress().getAddress().getHostAddress());
        return fixedLine;
    }

    private static String ColorSwap(String line){
    	String[] Colours = { 	"&0", "&1", "&2", "&3", "&4", "&5", "&6", "&7",
						        "&8", "&9", "&a", "&b", "&c", "&d", "&e", "&f",
						      };
    	ChatColor[] cCode = {	ChatColor.BLACK, ChatColor.DARK_BLUE, ChatColor.DARK_GREEN, ChatColor.DARK_AQUA, ChatColor.DARK_RED, ChatColor.DARK_PURPLE, ChatColor.GOLD, ChatColor.GRAY,
						        ChatColor.DARK_GRAY, ChatColor.BLUE, ChatColor.GREEN, ChatColor.AQUA, ChatColor.RED, ChatColor.LIGHT_PURPLE, ChatColor.YELLOW, ChatColor.WHITE,
						      };

    	for (int x = 0; x < Colours.length; x++) {
    		CharSequence cChk = null;
            String temp = null;

            cChk = Colours[x];
            if (line.contains(cChk)) {
                temp = line.replace(cChk, cCode[x].toString());
                line = temp;
            }
        }
        return line;
    }

    private static ArrayList<String> LoadFile(String file) throws FileNotFoundException
    {
        ArrayList<String> result = new ArrayList<String>();
        file = "plugins/MCDocs/" + file;
        FileInputStream fis = new FileInputStream(file);
        Scanner scanner = new Scanner(fis, "UTF-8");
        while (scanner.hasNextLine())
        {
            result.add(scanner.nextLine());
        }
        return result;
    }


}
