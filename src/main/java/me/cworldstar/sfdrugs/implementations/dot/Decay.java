package me.cworldstar.sfdrugs.implementations.dot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import me.cworldstar.sfdrugs.SFDrugs;
import me.cworldstar.sfdrugs.utils.Speak;

public class Decay {
	private static List<PotionEffect> DecayPotionEffects = new ArrayList<PotionEffect>();
	private static boolean ShouldEnd = false;
	static {
		Collection<PotionEffect> PotionEffectCollection = new ArrayList<PotionEffect>();
		PotionEffectCollection.add(new PotionEffect(PotionEffectType.WITHER,120,2));
		PotionEffectCollection.add(new PotionEffect(PotionEffectType.WEAKNESS,120,1));
		PotionEffectCollection.add(new PotionEffect(PotionEffectType.HUNGER,120,1));

		DecayPotionEffects.addAll(PotionEffectCollection);
	}
	public Decay(LivingEntity p,SFDrugs sfdrugs) {
		for(PotionEffect potion : Decay.DecayPotionEffects) {
			p.addPotionEffect(potion);
		}
		new Speak(p,"&7&l你正在腐烂。考虑逃跑吧。");
		new BukkitRunnable() {
			@Override
			public void run() {
				if(Decay.ShouldEnd == true) {
					this.cancel();
				}
				p.getWorld().spawnParticle(Particle.SMOKE_LARGE,p.getLocation(),10,1.0,2.0,1.0);
			}
		}.runTaskTimer(sfdrugs, 0, 20);
		new BukkitRunnable() {
			@Override
			public void run() {
				Decay.ShouldEnd = true;
				this.cancel();
			}
		}.runTaskLater(sfdrugs, 120);
	}
}
