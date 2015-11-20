package evolution.operators;

import java.util.List;
import evolution.individual.Individual;

public abstract class Operator<T> {

	public int arity() {
		return 0;
	}
	
	public abstract List<Individual<T>> getIndividuals(List<Individual<T>> parents);

	public abstract Individual<T> getIndividual(Individual<T> ind);
}
