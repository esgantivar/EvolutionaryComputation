package evolution.replacements;

import evolution.Population;

public class Generational implements Replacement<Double> {
	@Override
	public Population<Double> replace(Population<Double> parents, Population<Double> offsprings) {
		return offsprings;
	}
}