package me.cworldstar.sfdrugs.implementations.items;


import org.bukkit.Axis;
import org.bukkit.Effect;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import me.cworldstar.sfdrugs.SFDrugs;
import me.cworldstar.sfdrugs.utils.Speak;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.ProtectionType;
import io.github.thebusybiscuit.slimefun4.core.attributes.ProtectiveArmor;
import io.github.thebusybiscuit.slimefun4.core.attributes.Rechargeable;
import io.github.thebusybiscuit.slimefun4.implementation.items.armor.SlimefunArmorPiece;

public class DrugSuit extends SlimefunArmorPiece implements ProtectiveArmor, Rechargeable {
	public SFDrugs plugin;

		public DrugSuit(SFDrugs plugin, ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType,
				ItemStack[] recipe, PotionEffect[] effects) {
			super(itemGroup, item, recipeType, recipe, effects);
			this.plugin = plugin;
		}

	@Override
	public ProtectionType[] getProtectionTypes() {
		return new ProtectionType[] { ProtectionType.RADIATION };
	}

	@Override
	public boolean isFullSetRequired() {
		return true;
	}

	public boolean PlayerIsWearingFullSet(Player p) {
		Inventory I = p.getInventory();
		int Wearing = 0;
		for (int i = 5; i <= 8 ;i++) {
			if (I.getItem(i).getItemMeta().getDisplayName().contains("企业危险品")) {
				Wearing += 1;
			}
		}
		return (Wearing >=4);
	}
	
	@Override
	public NamespacedKey getArmorSetId() {
		// TODO Auto-generated method stub
		return new NamespacedKey(this.plugin, "SFDRUGS_DRUGSET");
	}

	@Override
	public float getMaxItemCharge(ItemStack item) {
		// TODO Auto-generated method stub
		return 1200F;
	}

	public boolean Damage(PlayerItemDamageEvent e, ItemStack Item, int dmg) {
		if (dmg > 0 & this.getItemCharge(Item) - new Float(dmg) > 0) {
			if(this.removeItemCharge(Item, new Float(dmg))) {
				e.setDamage(0);
				return true;
			}
		}
		return false;
	}

	public void PlayerDamaged(EntityDamageByEntityEvent e,Player p,ItemStack item, double dmg) {
		if(dmg > 0 & this.getItemCharge(item) - new Float(dmg) > 0) {
			if(this.removeItemCharge(item, new Float(dmg))) {
				p.getWorld().playEffect(p.getLocation(), Effect.ELECTRIC_SPARK,Axis.Y);
				p.getWorld().playSound(p.getLocation(), Sound.BLOCK_ANVIL_PLACE, 0.4F, 0.5F);
				new Speak(p,"&7&l[ 内部广播 ]: 阻挡 " + Double.toString(dmg/2) + " 伤害.");
				e.setDamage(dmg / 2);
			}
		}
	}
}
