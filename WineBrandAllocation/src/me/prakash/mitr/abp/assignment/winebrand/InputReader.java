/**
 * 
 */
package me.prakash.mitr.abp.assignment.winebrand;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import me.prakash.mitr.abp.assignment.winebrand.model.Finalizer;
import me.prakash.mitr.abp.assignment.winebrand.model.Person;
import me.prakash.mitr.abp.assignment.winebrand.model.WineBrand;

/**
 * @author Prakash Ayappan @ ShrewOn
 *
 *         2018
 */
public class InputReader {

	final Map<String, Person>		personMap		= new HashMap<>();
	final Map<String, WineBrand>	wineBrandMap	= new HashMap<>();

	void alpha() {

		one();

		// get file location
		System.out.println("personMap " + personMap.size());

		System.out.println("wineBrandMap " + wineBrandMap.size());

		algo0();

	}

	void one() {

		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("C:\\Users\\praka\\Desktop\\one.txt"));
			String line = reader.readLine();
			while (line != null) {

				String[] a = line.split("\\s+");

				two(a[0], a[1]);

				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	void two(final String personId, final String wineBrandId) {

		// Get Person from List
		final Person person = personMap.computeIfAbsent(personId, k -> new Person());

		// Get Wine Brand from List
		final WineBrand wineBrand = wineBrandMap.computeIfAbsent(wineBrandId, k -> new WineBrand());

		// Allocate Person & Wine Brand appropriately
		three(person, wineBrand);

	}

	void three(final Person person, final WineBrand wineBrand) {

		person.addPreferredWineBrand(wineBrand);
		wineBrand.addRequestedPerson(person);

	}

	void algo0() {

		final Finalizer f = new Finalizer();
		int iter = 0;

		do {

			f.setFalse();

			algo1(f);

			System.out.println("iter " + iter + " : " + f.isState());

			iter++;

		} while (f.isState());

	}

	void algo1(final Finalizer f) {
		wineBrandMap.entrySet().parallelStream().filter(exactlyOnePersonInAnalyse()).forEach(nextIteration(f));
	}

	private final Consumer<Map.Entry<String, WineBrand>> nextIteration(final Finalizer f) {
		return e0 -> wineBrandMap.entrySet().parallelStream().filter(notSameElement(e0))
				.filter(moreThanOnePersonInAnalyse()).map(removeMatchedPersons(e0)).forEach(f::updateStateOnTrue);
	}

	private final Predicate<Map.Entry<String, WineBrand>> exactlyOnePersonInAnalyse() {
		return e -> e.getValue().getanalysePersonsCount() == 1;
	}

	private final Predicate<Map.Entry<String, WineBrand>> moreThanOnePersonInAnalyse() {
		return e -> e.getValue().getAnalysePersons().size() > 1;
	}

	private final Predicate<Map.Entry<String, WineBrand>> notSameElement(final Map.Entry<String, WineBrand> e0) {
		return e1 -> !e1.getKey().equalsIgnoreCase(e0.getKey());
	}

	private final Function<Map.Entry<String, WineBrand>, Boolean> removeMatchedPersons(
			final Map.Entry<String, WineBrand> e0) {
		return e1 -> e1.getValue().getAnalysePersons().removeAll(e0.getValue().getAnalysePersons());
	}

}
