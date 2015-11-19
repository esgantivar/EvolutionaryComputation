package evolution.operators;

import java.util.List;
import evolution.individual.Individual;

public abstract class GeneticOperator<T> {
	
	public int arity(){
		return 0;
	}
	protected double rate = 0.0;
	public void setRate(double rate_){
		rate = rate_;
	}
	public double getRate(){
		return rate;
	}
	public abstract List<Individual<T>> getIndividuals(List<Individual<T>> parents);
	public abstract Individual<T> getIndividual(Individual<T> ind);	
}
