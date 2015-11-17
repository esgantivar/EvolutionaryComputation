package evolution.selectors;

import evolution.Population;

public interface Selector {
	public abstract void setPopulation(Population pop_);
	public abstract Population getParents();
}
