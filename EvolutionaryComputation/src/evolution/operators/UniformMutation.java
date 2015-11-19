package evolution.operators;

import java.util.List;

import evolution.individual.Individual;

public class UniformMutation extends Operator<Double> {

	public UniformMutation() {		
	}
	
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
		int pick[] = new int[(int) (ind.getDimension() * 0.01)];
		for (int i = 0; i < ((int) (ind.getDimension() * 0.01)); i++) {
			pick[i] = (int) (Math.random() * ind.getDimension());
		}

		for (int i = 0; i < pick.length; i++) {
			double s = Math.random();
			Double value = ind.getGene(pick[i]);
			if (Math.random() < 0.5) {
				value = s * value + (1 - s) * ind.min;
			} else {
				value = s * value + (1 - s) * ind.max;
			}
			ind.setGene(pick[i],value);
		}
		return ind;
	}

}
