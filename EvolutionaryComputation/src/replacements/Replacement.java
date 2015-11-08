package replacements;

import evolution.Population;

public abstract class Replacement {
	protected String name;
	public abstract Population replace(Population parents, Population offsprings);
}
