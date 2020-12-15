package com.mco.cowmod.capabilities;

import java.util.UUID;

public class Dna implements IDna
{
	private UUID uniqueId;
	private String familyTag;
	private int timesInbred;

	/**
	 * Initialize ids to random values
	 */
	public Dna()
	{
		this.timesInbred = 0;
		this.uniqueId = UUID.randomUUID();
		this.familyTag = UUID.randomUUID().toString();
	}

	@Override
	public void setUniqueIdentifier(UUID uniqueId)
	{
		this.uniqueId = uniqueId;
	}

	@Override
	public void setFamilyTag(String familyTag)
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
	public String getFamilyTag()
	{
		return familyTag;
	}

	@Override
	public int getTimesInbred()
	{
		return timesInbred;
	}
}
