/**
 * 
 */
package me.prakash.mitr.abp.assignment.winebrand.model;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Prakash Ayappan @ ShrewOn
 *
 *         2018
 */
public class Person {

	private Set<WineBrand> analyseWineBrands;

	public Person() {
		super();
		this.analyseWineBrands = new HashSet<>();
	}

	public final void addPreferredWineBrand(final WineBrand wineBrand) {

		if (analyseWineBrands.size() == 10) {
			throw new RuntimeException("Number of Preferred Brands for a person shall be 10. ");
		}
		analyseWineBrands.add(wineBrand);

	}

	public final int getAnalyseWineBrandsCount() {
		return analyseWineBrands.size();
	}
}
