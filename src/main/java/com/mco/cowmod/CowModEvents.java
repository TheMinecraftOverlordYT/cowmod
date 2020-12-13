package com.mco.cowmod;

import com.mco.cowmod.capabilities.DnaProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CowMod.MODID)
public class CowModEvents
{
	@SubscribeEvent
	public void attachCapabilitiesEntity(final AttachCapabilitiesEvent<Entity> event)
	{
		if(event.getObject() instanceof CowEntity)
		{
			event.addCapability(new ResourceLocation(CowMod.MODID, "dna"), new DnaProvider());
		}
	}

	@SubscribeEvent
	public void breedEvent(final BabyEntitySpawnEvent event)
	{
		if(event.getParentA() instanceof CowEntity && event.getParentB() instanceof CowEntity && event.getChild() instanceof CowEntity)
		{
			CowEntity mom = (CowEntity) event.getParentA();
			CowEntity dad = (CowEntity) event.getParentB();
			CowEntity child = (CowEntity) event.getChild();


		}
	}
}
