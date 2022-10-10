package me.cworldstar.sfdrugs.events;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

import me.cworldstar.sfdrugs.SFDrugs;
import me.cworldstar.sfdrugs.implementations.DamageType;
import me.cworldstar.sfdrugs.implementations.dot.Burning;
import me.cworldstar.sfdrugs.implementations.dot.Decay;

public class LaserProjectileHit implements Listener {
	private SFDrugs plugin;
    public LaserProjectileHit(SFDrugs plugin) {
    	this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
	@EventHandler
	private void onProjectileHit(EntityDamageByEntityEvent e) {
        if(e.getDamager() instanceof Projectile & e.getDamager().hasMetadata("SFDRUGS_IS_LASER_PROJECTILE")) {
            if(e.getEntity() instanceof LivingEntity) {
            	LivingEntity Entity = (LivingEntity) e.getEntity();
            	Entity.damage(10,DamageType.LASER_PROJECTILE.damager(Entity));
            	new Decay(Entity, plugin);
            }
        }
	}
    @EventHandler
    public void onProjectileHitBlock(ProjectileHitEvent e) {
        if (e.getEntity().hasMetadata("SFDRUGS_IS_LASER_PROJECTILE") && e.getHitBlock() != null) {
            e.getEntity().getWorld().createExplosion(e.getEntity().getLocation(), e.getEntity().getMetadata("SFDRUGS_IS_LASER_PROJECTILE").get(0).asFloat(), true, true);
            for(Entity Entities : e.getEntity().getNearbyEntities(2, 2, 2)) {
            	if(Entities instanceof LivingEntity) {
            		((LivingEntity) Entities).damage(8, DamageType.LASER_PROJECTILE.damager((LivingEntity) Entities));
            		new Burning((LivingEntity) Entities, plugin);
            	}
            }
        }
    }
}
