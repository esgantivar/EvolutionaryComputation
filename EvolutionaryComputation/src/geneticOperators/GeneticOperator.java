package geneticOperators;

import evolution.Individual;
import evolution.RealIndividual;

public abstract class GeneticOperator {
	public int arity ;
	protected double rate = 0.0;
	public void setRate(double rate_){
		rate = rate_;
	}
	public double getRate(){
		return rate;
	}
	
	public abstract Individual[] getIndividuals(Individual parents[]);
	public abstract RealIndividual getIndividual(RealIndividual ind);
}
