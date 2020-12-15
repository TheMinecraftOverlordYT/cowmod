package com.mco.cowmod.dna;

import com.mojang.datafixers.types.templates.Check;
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
	public static String createFamilyTag(String mother, String father)
	{
		String mom = mother.substring(0, mother.length() / 2);
		String dad = father.substring(0, father.length() / 2);
		String newId = mom + dad;

		System.out.println(mom);
		System.out.println(dad);

		return newId;
	}

	/**
	 * Check both halves of the cow's family tag to see if the cow is breeding with a parent
	 * Will only be called on a first generation cow
	 * @param partner the partner cow's unique id
	 * @return whether this cow is being inbred
	*/
	public boolean bredWithParent(UUID id, UUID partner)
	{
		String partnerString = partner.toString().substring(0, partner.toString().length() / 2);
		String firstHalf = id.toString().substring(0, id.toString().length() / 2);
		String secondHalf = id.toString().substring(id.toString().length() / 2);

		return firstHalf.equals(partnerString) || secondHalf.equals(partnerString);
	}



	public static double similarity(String s1, String s2) {
		String longer = s1, shorter = s2;
		if (s1.length() < s2.length()) { // longer should always have greater length
			longer = s2; shorter = s1;
		}
		int longerLength = longer.length();
		if (longerLength == 0) { return 1.0; /* both strings are zero length */ }
        /* // If you have StringUtils, you can use it to calculate the edit distance:
        return (longerLength - StringUtils.getLevenshteinDistance(longer, shorter)) /
                                                             (double) longerLength; */
		return (longerLength - editDistance(longer, shorter)) / (double) longerLength;

	}

	public static int editDistance(String s1, String s2) {
		s1 = s1.toLowerCase();
		s2 = s2.toLowerCase();

		int[] costs = new int[s2.length() + 1];
		for (int i = 0; i <= s1.length(); i++) {
			int lastValue = i;
			for (int j = 0; j <= s2.length(); j++) {
				if (i == 0)
					costs[j] = j;
				else {
					if (j > 0) {
						int newValue = costs[j - 1];
						if (s1.charAt(i - 1) != s2.charAt(j - 1))
							newValue = Math.min(Math.min(newValue, lastValue),
									costs[j]) + 1;
						costs[j - 1] = lastValue;
						lastValue = newValue;
					}
				}
			}
			if (i > 0)
				costs[s2.length()] = lastValue;
		}
		return costs[s2.length()];
	}


}
