package com.mco.cowmod.capabilities;

import java.util.UUID;

public class Dna implements IDna
{
	private UUID uniqueId;
	private UUID familyTag;
	private int timesInbred;

	public Dna()
	{
		this.timesInbred = 0;
		this.uniqueId = UUID.randomUUID();
		this.familyTag = UUID.randomUUID();
	}

	@Override
	public void setUniqueIdentifier(UUID uniqueId)
	{
		this.uniqueId = uniqueId;
	}

	@Override
	public void setFamilyTag(UUID familyTag)
	{
		this.familyTag = familyTag;
	}

	@Override
	public void setTimesInbred(int timesInbred)
	{
		this.timesInbred = timesInbred;
	}

	@Override
	public void updateInbred()
	{
		timesInbred++;
	}

	@Override
	public UUID getUniqueIdentifier()
	{
		return uniqueId;
	}

	@Override
	public UUID getFamilyTag()
	{
		return familyTag;
	}

	@Override
	public int getTimesInbred()
	{
		return timesInbred;
	}
}
