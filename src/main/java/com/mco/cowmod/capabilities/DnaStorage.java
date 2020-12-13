package com.mco.cowmod.capabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class DnaStorage implements Capability.IStorage<Dna>
{
	@Nullable
	@Override
	public INBT writeNBT(Capability<Dna> capability, Dna instance, Direction side) {
		CompoundNBT tag = new CompoundNBT();
		tag.putUniqueId("uniqueId", instance.getUniqueIdentifier());
		tag.putUniqueId("familyTag", instance.getFamilyTag());
		tag.putInt("timesInbred", instance.getTimesInbred());
		return tag;
	}

	@Override
	public void readNBT(Capability<Dna> capability, Dna instance, Direction side, INBT nbt) {
		CompoundNBT tag = (CompoundNBT) nbt;
		instance.setUniqueIdentifier(tag.getUniqueId("uniqueId"));
		instance.setFamilyTag(tag.getUniqueId("familyTag"));
		instance.setTimesInbred(tag.getInt("timesInbred"));
	}
}
