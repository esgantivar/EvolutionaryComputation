package replacements;

import evolution.Population;

public class Generational extends Replacement {
	public Generational(){
		name = "Generational";
	}
	@Override
	public Population replace(Population parents, Population offsprings) {
		return offsprings;
	}
}