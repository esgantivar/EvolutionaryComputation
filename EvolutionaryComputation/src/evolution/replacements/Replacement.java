package evolution.replacements;

import evolution.Population;

public interface Replacement{
	public Population replace(Population parents, Population offsprings);
}
