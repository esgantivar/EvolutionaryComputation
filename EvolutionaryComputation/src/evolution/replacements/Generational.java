package evolution.replacements;

import evolution.Population;

public class Generational implements Replacement {
	@Override
	public Population replace(Population parents, Population offsprings) {
		return offsprings;
	}
}