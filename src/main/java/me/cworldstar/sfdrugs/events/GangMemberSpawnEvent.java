package me.cworldstar.sfdrugs.events;

import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

import me.cworldstar.sfdrugs.SFDrugs;
import me.cworldstar.sfdrugs.implementations.bosses.entities.SmallerGangMember;
import me.cworldstar.sfdrugs.utils.RandomUtils;

public class GangMemberSpawnEvent implements Listener {
	
	private SFDrugs plugin;

	public GangMemberSpawnEvent(SFDrugs plugin) {
		this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onEntitySpawn(EntitySpawnEvent e) {
		if(e.getEntity() instanceof Zombie) {
			switch(RandomUtils.getRandom().nextInt(20)) {
			/**
			 * Removed GangMember -> SmallerGangMember.
			 * Gang Member boss will be handled with an item dropped
			 * by smaller gang members.
			 * 
			 * Reason: It would be a tragedy for
			 * a Gang Member to spawn
			 * in your base.
			 * 
			 * @link {SmallerGangMember}
			 */
			
				case 1:
					new SmallerGangMember(plugin,(Zombie) e.getEntity());
					break;
				case 2:
					new SmallerGangMember(plugin,(Zombie) e.getEntity());
					break;								
				default:
					break;
			}
		}
	}
}
