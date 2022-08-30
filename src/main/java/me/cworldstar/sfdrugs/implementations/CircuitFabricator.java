package me.cworldstar.sfdrugs.implementations;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotHopperable;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactive;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactivity;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import net.md_5.bungee.api.ChatColor;

public class CircuitFabricator extends AContainer implements NotHopperable,RecipeDisplayItem,Radioactive {

	public CircuitFabricator(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType,
			ItemStack[] recipe) {
		super(itemGroup, item, recipeType, recipe);
		this.setCapacity(480);
		this.setEnergyConsumption(120);
		this.setProcessingSpeed(20);
		// TODO Auto-generated constructor stub
	}
    @Override
    public void registerDefaultRecipes() {
    	addRecipe(60,new ItemStack[] { new CustomItemStack(SlimefunItems.SILICON,4), new CustomItemStack(SlimefunItems.REDSTONE_ALLOY,8) },  new ItemStack[] { new CustomItemStack(SlimefunItems.BASIC_CIRCUIT_BOARD) });
    }
	private void addRecipe(int i, ItemStack[] input, ItemStack[] output) {
		registerRecipe(i,input,output);
	}
	@Override
	public ItemStack getProgressBar() {
		// TODO Auto-generated method stub
		ItemStack ProgressBar = new ItemStack(Material.ANVIL);
		ProgressBar.getItemMeta().setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&l伪造..."));
		return ProgressBar;
	}

	@Override
	public String getMachineIdentifier() {
		// TODO Auto-generated method stub
		return "SFDRUGS_CIRCUIT_FABRICATOR";
	}
	@Override
	public Radioactivity getRadioactivity() {
		// TODO Auto-generated method stub
		return Radioactivity.VERY_HIGH;
	}

}
