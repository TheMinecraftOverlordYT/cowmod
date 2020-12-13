package com.mco.cowmod.capabilities;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class DnaProvider implements ICapabilitySerializable<INBT>
{
	@CapabilityInject(Dna.class)
	public static final Capability<Dna> DNA_CAPABILITY = null;

	private LazyOptional<Dna> instance = LazyOptional.of(DNA_CAPABILITY::getDefaultInstance);

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		return cap == DNA_CAPABILITY ? instance.cast() : LazyOptional.empty();
	}

	@Override
	public INBT serializeNBT() {
		return DNA_CAPABILITY.getStorage().writeNBT(DNA_CAPABILITY, this.instance.orElseThrow(() ->
				new IllegalArgumentException("LazyOptional must not be empty!")), null);
	}

	@Override
	public void deserializeNBT(INBT nbt) {
		DNA_CAPABILITY.getStorage().readNBT(DNA_CAPABILITY, this.instance.orElseThrow(() ->
				new IllegalArgumentException("LazyOptional must not be empty!")), null, nbt);
	}
}
