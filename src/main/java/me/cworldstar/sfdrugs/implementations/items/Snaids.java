package me.cworldstar.sfdrugs.implementations.items;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import me.cworldstar.sfdrugs.SFDrugs;
import me.cworldstar.sfdrugs.utils.Speak;
import net.md_5.bungee.api.ChatColor;

public class Snaids extends SimpleSlimefunItem<ItemUseHandler> {
	private SFDrugs plugin;
	public Snaids(SFDrugs plugin,ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
		super(itemGroup, item, recipeType, recipe);
		this.plugin = plugin;
		// TODO Auto-generated constructor stub
	}
	@Override
	public ItemUseHandler getItemHandler() {
		// TODO Auto-generated method stub
		return (e) -> {
			Player p = e.getPlayer();
			ItemStack item = e.getItem();
			item.setAmount(item.getAmount()-1);
			new Speak(p,"&d&l你现在有snaids了。你将在30秒后死去。");
	        new BukkitRunnable() {
	            @Override
	            public void run() {
	            	ArmorStand nEntity = (ArmorStand) e.getPlayer().getWorld().spawnEntity(e.getPlayer().getLocation(),EntityType.ARMOR_STAND);
	            	nEntity.setCustomNameVisible(false);
	            	nEntity.setVisible(false);
	            	nEntity.setGravity(false);
	            	nEntity.setCustomName(ChatColor.translateAlternateColorCodes('&', "&6Snaids"));
	            	p.damage(60, nEntity);
	            	nEntity.remove();
	           }
	        }.runTaskLater(plugin, 600L);
			
		};
	} 
}
