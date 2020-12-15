package com.mco.cowmod.capabilities;

import java.util.UUID;

public interface IDna
{
	/**
	 * Returns the cow's unique id
	 * @return the cow's unique identifier
	 */
	UUID getUniqueIdentifier();

	/**
	 * Return's the cow's family tag
	 * @return the cow's family tag
	 */
	String getFamilyTag();

	/**
	 * How many times the cow has been bred
	 * with a family member
	 * @return How many times the cow's been inbred
	 */
	int getTimesInbred();

	/**
	 * Increments how many times the cow
	 * has been inbred
	 */
	void updateInbred();

	/**
	 * Sets the unique identifier
	 * @param identifier the unique identifier
	 */
	void setUniqueIdentifier(UUID identifier);

	/**
	 * Sets the family tag
	 * @param tag the family tag
	 */
	void setFamilyTag(String tag);

	/**
	 * Sets how many times the cow's been inbred
	 * @param timesInbred how many times it's been inbred
	 */
	void setTimesInbred(int timesInbred);
}
