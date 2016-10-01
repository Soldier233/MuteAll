package net.mcmhsj.MuteAll;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

public class main
extends JavaPlugin
implements Listener
{
	public static boolean enable;
	public void onEnable()
	{
		Bukkit.getPluginManager().registerEvents(this, this);
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&l[&b&l全员禁言&a&l]插件加载完成"));
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&l[&b&l全员禁言&a&l]有疑问可以加作者QQ:1224954468进行咨询"));
	}
	public boolean onCommand(CommandSender sender,Command cmd,String label,String args[])
	{
		if(cmd.getName().equals("muteall"))
		{
			if(sender.isOp())
			{
				if(enable)
				{
					enable=false;
					Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&a&l管理员关闭了全员禁言"));
				}
				else
				{
					enable=true;
					Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&a&l管理员开启了全员禁言，无特定权限无法说话"));
				}
			}
			else
			{
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "权限不足"));
			}
		}
		return true;
	}
	@EventHandler
	public void OnChat(PlayerChatEvent e)
	{
		Player p=e.getPlayer();
		if(enable)
		{
			if(!(p.hasPermission("muteall.bypass")||p.isOp()))
			{
				e.setCancelled(true);
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l对不起，管理员开启了全员禁言，你无法说话"));
			}
		}
	}
}
