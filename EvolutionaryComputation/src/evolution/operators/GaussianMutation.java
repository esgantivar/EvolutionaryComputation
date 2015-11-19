package evolution.operators;

import java.util.List;

import evolution.individual.Individual;

public class GaussianMutation  extends Operator<Double>{

	public GaussianMutation() {}
	
	@Override
	public int arity(){
		return 1;
	}

	@Override
	public List<Individual<Double>> getIndividuals(List<Individual<Double>> parents) {
		return parents;
	}

	@Override
	public Individual<Double> getIndividual(Individual<Double> ind) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
