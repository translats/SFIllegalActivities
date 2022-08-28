package me.cworldstar.sfdrugs.utils;


import java.util.List;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.DamageableItem;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactive;
import io.github.thebusybiscuit.slimefun4.core.handlers.ToolUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import net.md_5.bungee.api.ChatColor;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactivity;
import io.github.thebusybiscuit.slimefun4.core.attributes.Rechargeable;

public class WorldEater extends SimpleSlimefunItem<ToolUseHandler> implements Radioactive, DamageableItem, Rechargeable {
	public WorldEater(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
		super(itemGroup, item, recipeType, recipe);
		// TODO Auto-generated constructor stub
	}
	@Override
    public Radioactivity getRadioactivity() {
        return Radioactivity.VERY_DEADLY;
    }
    public float getMaxItemCharge(ItemStack item) {
        return 12000F;
    }
    @Override
    public ToolUseHandler getItemHandler() {
        return (e, tool, fortune, drops) -> {
            Player p = e.getPlayer();
            	if(this.removeItemCharge(tool,2F)) {
                    Block b = e.getBlock();
                    b.getWorld().spawnParticle(Particle.VILLAGER_HAPPY,b.getLocation(),8);
                    b.getWorld().playSound(b.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.2F, 1F);
                    /*if(p.isSneaking()) {
                    	int currentItemStack = 0;
                    	for(ItemStack i : drops) {
                    		if(i.getItemMeta().getDisplayName().contains("Ore")) {
                    			drops.set(i,i.)
                    		}
                    		currentItemStack += 1;
                    	}
                    }*/
                    b.breakNaturally(tool);
            	} else {
            		p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l[ SFDrugs ]: &dYour tool has run out of power!"));
            		e.setCancelled(true);
            	}
        };
    }
	@Override
	public boolean isDamageable() {
		// TODO Auto-generated method stub
		return false;
	}
}
