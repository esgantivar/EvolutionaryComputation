package geneticOperators;

import java.util.Random;
import evolution.Individual;
import evolution.RealIndividual;

public class UniformMutation extends GeneticOperator{
	
	public UniformMutation(){
		arity = 1;
	}
	public RealIndividual mutate(RealIndividual ind){
		double percMutation = 10.0  /1000.0;
		Random rnd = new Random();
		int gen = rnd.nextInt(ind.getDimension());
		
		for (int i = 0; i < ind.getDimension(); i++) {
			if(rnd.nextDouble()<= percMutation){
				
				double s = rnd.nextDouble();
				double value = (double)ind.getGene(i).getValue();
				if(rnd.nextDouble() < 0.5){
					value = s*value+(1-s)*ind.min;
				}else{
					value = s*value+(1-s)*ind.max;
				}
				ind.getGene(i).setValue(value);
			}
		}
		return ind;
	}
	@Override
	public Individual[] getIndividuals(Individual[] parents) {
		return null;
	}

	@Override
	public RealIndividual getIndividual(RealIndividual ind) {
		return mutate(ind);
	}

	
}
