package me.cockatoo2x.plugins.vipwelcome;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class VIPWelcome extends JavaPlugin implements Listener{

	public void onEnable(){
		getLogger().info("VIP Welcome Now Enabled!");
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		getConfig().options().copyDefaults(true);
		saveConfig();
		if(getConfig().contains("Message")){
			
		}else{
			getConfig().createSection("Message");
		}
	}
	public void onDisable(){
		getLogger().info("VIP Welcome Now Disabled!");
		saveConfig();
	}

	@EventHandler
	public void onPlayerJoin (final PlayerJoinEvent pje) {
		final Player player = pje.getPlayer();
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable(){
			public void run(){
				if(player.hasPermission("vip.join.firework")){
					Firework f = (Firework) pje.getPlayer().getWorld().spawn(pje.getPlayer().getLocation(), Firework.class);                   
					FireworkMeta fm = f.getFireworkMeta();
					fm.addEffect(FireworkEffect.builder()
							.flicker(false)
							.trail(true)
							.with(Type.BALL)
							.with(Type.BALL_LARGE)
							.with(Type.STAR)
							.withColor(Color.ORANGE)
							.withColor(Color.YELLOW)
							.withFade(Color.PURPLE)
							.withFade(Color.RED)
							.build());
					fm.setPower(2);
					f.setFireworkMeta(fm);
				}
				       
			}
		}, 20);
	}
	@EventHandler
	public void onPlayerJoin2(PlayerJoinEvent pje){
		final Player player = pje.getPlayer();
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable(){
			public void run(){
				if(player.hasPermission("vip.join.message")){
					Bukkit.broadcastMessage(ChatColor.GREEN + getConfig().getString("Message") + " " + player.getName() + "!");
				}
			}
		}, 20);
	}
}