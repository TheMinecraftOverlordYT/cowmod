package com.mco.cowmod.dna;

import net.minecraft.entity.passive.CowEntity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * Singleton class that will handle dna logic for cows
 *
 * @author TheMCO, warrior
 */
public class DnaGenerator
{
	/** Maintain a private instance */
	private static DnaGenerator instance = null;

	/**
	 * Creates instance
	 */
	public DnaGenerator()
	{
		getInstance();
	}

	/**
	 * Creates a new instance if there isn't one,
	 * otherwise returns the instance
	 *
	 * @return the singleton instance
	 */
	public static DnaGenerator getInstance()
	{
		if(instance == null)
			instance = new DnaGenerator();

		return instance;
	}

	/**
	 * Called when 2 completely unrelated cows are bred
	 *
	 * @param mother the UUID of the mother
	 * @param father the UID of the father
	 *
	 * @return a unique family UUID comprised of data from the mother and father
	 */
	public static UUID createFamilyTag(UUID mother, UUID father)
	{
		String mom = mother.toString().substring(0, mother.toString().length() / 2);
		String dad = father.toString().substring(0, father.toString().length() / 2);

		String newId = mom + dad;

		return UUID.fromString(newId);
	}
/*
	*//**
	 * Check both halves of the cow's family tag to see if the cow is breeding with a parent
	 * Will only be called on a first generation cow
	 * @param partner the partner cow's unique id
	 * @return whether this cow is being inbred
	 *//*
	public boolean bredWithParent(UUID partner)
	{
		String partnerString = partner.toString().substring(0, partner.toString().length() / 2);
		String firstHalf = this.getId().toString().substring(0, this.getId().toString().length / 2);
		String secondHalf = this.getId().toString().substring(this.getId().toString().length / 2, this.getId().toString.length);

		return firstHalf.equals(partnerString) || secondHalf.equals(partnerString);
	}*/

}
