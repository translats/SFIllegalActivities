package me.cworldstar.sfdrugs.implementations.bosses.deathsequences;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

import me.cworldstar.sfdrugs.SFDrugs;
import me.cworldstar.sfdrugs.implementations.DamageType;
import me.cworldstar.sfdrugs.utils.Speak;

public class EscapedTestSubjectDeathSequence {
	public EscapedTestSubjectDeathSequence(SFDrugs plugin,LivingEntity mob) {
		for(Entity e : mob.getNearbyEntities(20, 20, 20)) {
			if (e instanceof LivingEntity) {
				new Speak(((LivingEntity) mob),mob.getNearbyEntities(20, 20, 20),"&a&l[ Escaped Test Subject ]: ROAAAAAAAAAAR!!!!");
				new Speak(((LivingEntity) mob),mob.getNearbyEntities(20, 20, 20),"&aThe Escaped Test Subject is detonating. You have 5 seconds to run.");
				new BukkitRunnable() {
					@Override
					public void run() {
						if(mob.isDead()) {
							this.cancel();
						}
						if(mob.getLocation().getYaw() < 360 && !this.isCancelled()) {
							mob.setRotation(mob.getLocation().getYaw() + 1.0F,mob.getLocation().getPitch());
						} else {
							mob.setRotation(0.0F,mob.getLocation().getPitch());
						}
					}
				}.runTaskTimer(plugin, 0, 2L); // Rotate
				new BukkitRunnable() {
					@Override
					public void run() {
						mob.getWorld().createExplosion(mob.getLocation(), 20.0F,true,true,DamageType.ANTIMATTER_DETONATION.damager(mob));
						for(Entity e2 : mob.getNearbyEntities(7.0, 7.0, 7.0)) {
							if(e2 instanceof LivingEntity) {
								((LivingEntity)e2).damage(50.0,DamageType.ANTIMATTER_DETONATION.damager((LivingEntity) e2));
								mob.setInvulnerable(false);
								mob.remove();
							}
						}
					}
				}.runTaskLater(plugin, 100L); // 5 seconds
			}
		}
		
	}
}
