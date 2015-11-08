package selectors;

import evolution.Population;

public abstract class Selector {
	protected String name;
	public abstract void setPopulation(Population pop_);
	public abstract Population getParents();
}
