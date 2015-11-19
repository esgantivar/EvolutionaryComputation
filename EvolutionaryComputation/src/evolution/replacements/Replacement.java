package evolution.replacements;

import evolution.Population;

public interface Replacement<T>{
	public Population<T> replace(Population<T> parents, Population<T> offsprings);
}
