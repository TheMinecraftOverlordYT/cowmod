package com.mco.cowmod;

import com.mco.cowmod.capabilities.Dna;
import com.mco.cowmod.capabilities.DnaProvider;
import com.mco.cowmod.dna.DnaGenerator;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

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

			String momTag = mom.getCapability(DnaProvider.DNA_CAPABILITY).orElse(new Dna()).getFamilyTag();
			String dadTag  = dad.getCapability(DnaProvider.DNA_CAPABILITY).orElse(new Dna()).getFamilyTag();

			child.getCapability(DnaProvider.DNA_CAPABILITY).orElse(new Dna()).setFamilyTag(DnaGenerator.createFamilyTag(momTag, dadTag));
			System.out.println(child.getCapability(DnaProvider.DNA_CAPABILITY).orElse(new Dna()).getFamilyTag());
		}
	}

	@SubscribeEvent
	public static void entityTickEvent(final EntityJoinWorldEvent event)
	{
		if(event.getEntity() instanceof CowEntity && !event.getWorld().isRemote)
		{
			event.getEntity().setCustomName(new StringTextComponent(event.getEntity().getCapability(DnaProvider.DNA_CAPABILITY)
					.orElse(new Dna()).getFamilyTag()));
			System.out.println("UUID: " + event.getEntity().getCapability(DnaProvider.DNA_CAPABILITY).orElse(new Dna()).getUniqueIdentifier());
		}
	}
}
