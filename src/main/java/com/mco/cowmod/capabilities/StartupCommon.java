package com.mco.cowmod.capabilities;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class StartupCommon
{
	@SubscribeEvent
	public static void onCommonSetupEvent(FMLCommonSetupEvent event){
		//CapabilityCow.register();
		//MinecraftForge.EVENT_BUS.register(CapabilityAttachEventHandler.class);
	}
}
