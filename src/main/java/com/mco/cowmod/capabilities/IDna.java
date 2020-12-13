package com.mco.cowmod.capabilities;

public interface IDna
{
	/**
	 * Returns the cow's unique id
	 * @return the cow's unique identifier
	 */
	String getIdentifier();

	/**
	 * Return's the cow's family tag
	 * @return the cow's family tag
	 */
	String getFamily();

	/**
	 * How many times the cow has been bred
	 * with a family member
	 * @return How many times the cow's been inbred
	 */
	int timesInbred();

	/**
	 * Increments how many times the cow
	 * has been inbred
	 */
	void updateInbred();
}
