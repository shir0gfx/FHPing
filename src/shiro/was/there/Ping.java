package shiro.was.there;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * 
 * @author Shiro
 *
 */

public class Ping extends JavaPlugin implements Listener {
	
	Logger console = Logger.getLogger("Minecraft");
	
	public String PREFIX = "§8▍ §3Ping §8▏ §7";
	public String NO_PERM = "§cOops, na tento prikaz nemate dostatocne opravnenia.";
	
	public void onEnable() {
		console.info("[PING] Plugin je uspesne zapnuty.");
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}
	
	public void onDisable() {
		console.info("[PING] Plugin je uspesne vypnuty.");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("ping")) {
			if ((sender instanceof Player)) {
				Player p = (Player) sender;
				if (args.length == 0) {
					if ((p.hasPermission("ping.cmd")) || (p.isOp())) {
						int ping = ((CraftPlayer)p).getHandle().ping;
						p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1.0F, 2.0F);
						p.sendMessage(PREFIX + "Tvoj ping je ► §b" + ping + "ms§7!");
					} else {
						p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1.0F, 2.0F);
						p.sendMessage(PREFIX + NO_PERM);
					}
				} else if (args.length == 1) {
					if ((p.hasPermission("ping.cmd.other")) || (p.isOp())) {
						Player target = Bukkit.getPlayer(args[0]);
						if (target != null) {
							int ping1 = ((CraftPlayer)target).getHandle().ping;
							p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1.0F, 2.0F);
							p.sendMessage(PREFIX + "Uzivatel s nickom §6" + target.getName() + "§7ma ping ► §b" + ping1 + "ms§7!");
						} else {
							p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1.0F, 2.0F);
							p.sendMessage(PREFIX + "Tento hrac nieje online!");
						}
					} else {
						p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1.0F, 2.0F);
						p.sendMessage(PREFIX + NO_PERM);
					}
				}
			} else {
				sender.sendMessage(PREFIX + "§cTento prikaz je iba pre hracov!");
			}
		} 
		return true;
	}
}
