package com.mco.cowmod;

import com.mco.cowmod.capabilities.Dna;
import com.mco.cowmod.capabilities.DnaProvider;
import com.mco.cowmod.dna.DnaUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CowMod.MODID)
public class CowModEvents
{
	@SuppressWarnings("unused")
	@SubscribeEvent
	public static void breedEvent(final BabyEntitySpawnEvent event)				//Handles inbreeding logic
	{
		//Make sure the breeding entities and resultant entities are all vanilla cows
		if(event.getParentA() instanceof CowEntity && event.getParentB() instanceof CowEntity && event.getChild() instanceof CowEntity
		 && !event.getChild().world.isRemote) {
			//Local variables for all entities in the relationship
			CowEntity mom = (CowEntity) event.getParentA();
			CowEntity dad = (CowEntity) event.getParentB();
			CowEntity child = (CowEntity) event.getChild();

			//Local variables for all the respective capabilities
			Dna childCap = child.getCapability(DnaProvider.DNA_CAPABILITY).orElse(new Dna());
			Dna momCap = mom.getCapability(DnaProvider.DNA_CAPABILITY).orElse(new Dna());
			Dna dadCap = dad.getCapability(DnaProvider.DNA_CAPABILITY).orElse(new Dna());

			//Local vars to keep track of family tag
			String momTag = momCap.getFamilyTag();
			String dadTag = dadCap.getFamilyTag();

			//If parent tags are greater than 0.5% similar, they have likely been bred together at least once.
			if(DnaUtils.similarity(momTag, dadTag) >= 0.5)
			{
				//Update amount of times inbred
				childCap.setTimesInbred(Math.max(momCap.getTimesInbred(), dadCap.getTimesInbred()) + 1);
				//Keep the same family tag
				childCap.setFamilyTag(momTag);
			}
			//If they are unrelated, create a new tag using half the tag from each parent
			else
				childCap.setFamilyTag(DnaUtils.createFamilyTag(momTag, dadTag));
		}
	}


	@SuppressWarnings("unused")
	@SubscribeEvent
	public static void tickEvent(final TickEvent.WorldTickEvent event)					//Used in testing to grow cow immediately
	{
		if(event.world.getPlayers().size() > 0){
			for(Entity entity : event.world.getEntitiesWithinAABB(CowEntity.class, event.world.getPlayers().get(0).getBoundingBox().grow(10)))
			{
				if(entity instanceof CowEntity) {
					CowEntity cow = (CowEntity)entity;
					cow.ageUp(3000, true);
				}
			}
		}
	}


	@SuppressWarnings("unused")
	@SubscribeEvent
	public static void entityTickEvent(final EntityJoinWorldEvent event)				//Used in testing to set cow's nametag to their family tag
	{
		if(event.getEntity() instanceof CowEntity && !event.getWorld().isRemote)
		{
			event.getEntity().setCustomName(new StringTextComponent(event.getEntity().getCapability(DnaProvider.DNA_CAPABILITY)
					.orElse(new Dna()).getFamilyTag()));
		}
	}
}
