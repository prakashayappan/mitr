/**
 * 
 */
package me.prakash.mitr.abp.assignment.winebrand.model;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

/**
 * @author Prakash Ayappan @ ShrewOn
 *
 *         2018
 */
public class WineBrand {

	private Set<Person> analysePersons;

	public WineBrand() {
		super();
		this.analysePersons = new HashSet<>();
	}

	public final void addRequestedPerson(final Person person) {
		analysePersons.add(person);
	}

	public final int getanalysePersonsCount() {
		return analysePersons.size();
	}

	public final boolean isPersonFromAnalyse(final Set<Person> personSet) {
		return analysePersons.containsAll(personSet);
	}

	public final Set<Person> getAnalysePersons() {
		return analysePersons;
	}

	public final Predicate<WineBrand> exactlyOneAnalysedPerson() {
		return w -> w.getAnalysePersons().size() == 1;
	}

}
