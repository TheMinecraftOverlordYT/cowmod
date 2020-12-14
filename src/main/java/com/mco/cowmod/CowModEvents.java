package com.mco.cowmod;

import com.mco.cowmod.capabilities.Dna;
import com.mco.cowmod.capabilities.DnaProvider;
import com.mco.cowmod.dna.DnaGenerator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

@Mod.EventBusSubscriber(modid = CowMod.MODID)
public class CowModEvents
{
	@SubscribeEvent
	public static void breedEvent(final BabyEntitySpawnEvent event)
	{
		if(event.getParentA() instanceof CowEntity && event.getParentB() instanceof CowEntity && event.getChild() instanceof CowEntity
		 && !event.getChild().world.isRemote)
		{
			CowEntity mom = (CowEntity) event.getParentA();
			CowEntity dad = (CowEntity) event.getParentB();
			CowEntity child = (CowEntity) event.getChild();

			UUID momTag = mom.getCapability(DnaProvider.DNA_CAPABILITY).orElse(new Dna()).getFamilyTag();
			UUID dadTag  = dad.getCapability(DnaProvider.DNA_CAPABILITY).orElse(new Dna()).getFamilyTag();

			child.getCapability(DnaProvider.DNA_CAPABILITY).orElse(new Dna()).setFamilyTag(DnaGenerator.createFamilyTag(momTag, dadTag));
		}
	}

	@SubscribeEvent
	public static void entityTickEvent(final EntityJoinWorldEvent event)
	{
	//	if(event.getEntity() instanceof CowEntity && !event.getWorld().isRemote)
	//	{
		//	System.out.println("UUID: " + event.getEntity().getCapability(DnaProvider.DNA_CAPABILITY).orElse(new Dna()).getUniqueIdentifier());
		//}
	}
}
