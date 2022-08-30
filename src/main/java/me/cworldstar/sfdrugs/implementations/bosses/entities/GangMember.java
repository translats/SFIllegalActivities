package me.cworldstar.sfdrugs.implementations.bosses.entities;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.LlamaSpit;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.cworldstar.sfdrugs.SFDrugs;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import me.cworldstar.sfdrugs.implementations.loot.CorporationEnemyLootTable;
import me.cworldstar.sfdrugs.utils.ParticleUtils;
import me.cworldstar.sfdrugs.utils.Speak;
import net.md_5.bungee.api.ChatColor;

public class GangMember {
	public GangMember(SFDrugs plugin,Zombie z) {
		z.setCustomName(ChatColor.translateAlternateColorCodes('&', "&c&l&k|||&r &4&l⚠ 红狼黑帮 ⚠&r &c&l&k|||&r"));
		z.setMaxHealth(750.0);
		z.setHealth(750.0);
		z.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,999999,5));
		z.setLootTable(new CorporationEnemyLootTable(plugin));
		BossBar EnemyBossBar = Bukkit.getServer().createBossBar(ChatColor.translateAlternateColorCodes('&',"&c&l&k|||&r &4&l⚠ 红狼黑帮 ⚠&r &c&l&k|||&r"),BarColor.RED, BarStyle.SEGMENTED_12);
		EnemyBossBar.setVisible(true);
		EnemyBossBar.setProgress(1.0);
		List<Player> Players = new ArrayList<Player>();
		new BukkitRunnable() {
			@Override
			public void run() {
				if(z.isDead()) {
					EnemyBossBar.setVisible(false);
					this.cancel();
				} else {
					double Health = Double.parseDouble(new DecimalFormat("#.###").format(z.getHealth() / z.getMaxHealth()));
					EnemyBossBar.setProgress(Health);
					for(Entity e : z.getNearbyEntities(20.0, 20.0, 20.0)) {
						if (e instanceof Player) {
							if(!Players.contains((Player) e)) {
								Players.add((Player) e);
								EnemyBossBar.addPlayer((Player) e);
							}
						}
					}
					for(Player p : EnemyBossBar.getPlayers()) {
						if(!Players.contains(p)) {
							EnemyBossBar.removePlayer(p);
						}
					}
				}
			}
		}.runTaskTimer(plugin, 0L, 20L);
		
		
		ItemStack ZombieHead = SlimefunUtils.getCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmYzNjhhODZjZmZkMjllYTI1YjFhYjdlNTVhYzFkMDQ5NGY3MjZhODUzZmRiNDA0MmU3YzcxY2QwNmI5NmQzNyJ9fX0=");
		ItemStack Boots = new ItemStack(Material.LEATHER_BOOTS);
		
		LeatherArmorMeta BootsMeta = (LeatherArmorMeta) Boots.getItemMeta();
		new Speak(BootsMeta,"&c 红狼背心");
		BootsMeta.setColor(Color.RED);
		BootsMeta.setUnbreakable(true);
		Boots.setItemMeta(BootsMeta);
		
		ItemStack Leggings = new ItemStack(Material.LEATHER_LEGGINGS);
		
		LeatherArmorMeta LeggingsMeta = (LeatherArmorMeta) Leggings.getItemMeta();
		new Speak(LeggingsMeta,"&c 红狼牛仔裤");
		LeggingsMeta.setColor(Color.RED);
		LeggingsMeta.setUnbreakable(true);
		Leggings.setItemMeta(LeggingsMeta);
		
		ItemStack Chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
		Chestplate.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 20);
		
		LeatherArmorMeta ChestplateMeta = (LeatherArmorMeta) Chestplate.getItemMeta();
		new Speak(ChestplateMeta,"&c 红狼背心");
		ChestplateMeta.setColor(Color.BLACK);
		ChestplateMeta.setUnbreakable(true);
		Chestplate.setItemMeta(ChestplateMeta);
		z.getEquipment().setArmorContents(new ItemStack[] {
				Boots,
				Leggings,
				Chestplate,
				ZombieHead
		});
		new BukkitRunnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(z.isDead()) {
					this.cancel();
				}
				ParticleUtils.SpawnInCircle(z.getLocation().add(0,1,0), Particle.SOUL_FIRE_FLAME, 0.5, 30, 2);
			}
			
		}.runTaskTimer(plugin, 0, 5L);
		new BukkitRunnable() {
			@Override
			public void run() {
				if(z.isDead()) {
					this.cancel();
				} else if(z.hasAI() & z.getTarget() != null & z.getTarget() instanceof LivingEntity) {
					int RandomNumber = new Random().nextInt(3);
					switch(RandomNumber) {
						case 0:
							break;
						case 1:
							new Speak(z,z.getNearbyEntities(15.0, 15.0, 15.0),"&c&l[&k|||&r &4&l⚠ 红狼黑帮 ⚠&r &c&l&k|||&r&c&l]:&r &c过来这里!");
							z.getTarget().teleport(z.getLocation().add(new Random().nextInt(3), 0, new Random().nextInt(3)));
							z.getWorld().playSound(z.getTarget().getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 0.5F, 0.5F);
							z.setInvulnerable(true);
							z.getWorld().createExplosion(z.getLocation(), 3,true,false);
							z.setInvulnerable(false);
							for(Entity entities : z.getNearbyEntities(3, 3, 3)) {
								z.swingMainHand();
								z.getTarget().setVelocity(z.getTarget().getLocation().toVector().normalize().multiply(-1).multiply(20));
								if(entities instanceof LivingEntity) {
									((LivingEntity) entities).damage(new Random().nextDouble() * 20,z);
								}
							}
						case 2:
							new Speak(z,z.getNearbyEntities(15.0, 15.0, 15.0),"&c&l[&k|||&r &4&l⚠ 红狼黑帮 ⚠&r &c&l&k|||&r&c&l]:&r &c去死吧!");
							for(int i=0;i<4;i++) {
								LlamaSpit LaserProjectile = z.launchProjectile(LlamaSpit.class);
								Vector source = z.getLocation().getDirection().normalize().multiply(50);
								Vector v = z.getTarget().getLocation().toVector().subtract(source);
								BukkitTask LaserProjectileVelocityTask = new BukkitRunnable() {
									@Override
									public void run() {
										LaserProjectile.setVelocity(v);
										LaserProjectile.getWorld().spawnParticle(Particle.DRAGON_BREATH,LaserProjectile.getLocation(),2);
										if(LaserProjectile.isDead()) {
											this.cancel();
										}
									}
								}.runTaskTimer(plugin, 0L, 1L);
								new BukkitRunnable() {
									@Override
									public void run() {
										if(!LaserProjectile.isDead()) {
											LaserProjectile.remove();
											this.cancel();
											return;
										}
										if(!LaserProjectileVelocityTask.isCancelled()) {
											LaserProjectileVelocityTask.cancel();
										}
										this.cancel();
									}
								}.runTaskLater(plugin, 100L); // Remove after 10 seconds
								break;
							}
					}
				}
			}
			
		}.runTaskTimer(plugin,0L,60L);
	}
}
