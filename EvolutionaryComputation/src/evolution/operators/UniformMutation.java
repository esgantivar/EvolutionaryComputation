package evolution.operators;

import evolution.individual.RealIndividual;

public class UniformMutation extends GeneticOperator<RealIndividual, RealIndividual> {

	public UniformMutation() {
		arity = 1;
	}

	public RealIndividual mutate(RealIndividual ind) {
		int pick[] = new int[(int) (ind.getDimension() * 0.1)];
		for (int i = 0; i < ((int) (ind.getDimension() * 0.1)); i++) {
			pick[i] = (int) (Math.random() * ind.getDimension());
		}

		for (int i = 0; i < ind.getDimension(); i++) {
			double s = Math.random();
			double value = (double) ind.getGene(pick[i]).getValue();
			if (Math.random() < 0.5) {
				value = s * value + (1 - s) * ind.min;
			} else {
				value = s * value + (1 - s) * ind.max;
			}
			ind.getGene(i).setValue(value);
		}
		return ind;
	}

	@Override
	public RealIndividual getIndividual(RealIndividual ind) {
		return mutate(ind);
	}

	@Override
	public RealIndividual[] getIndividuals(RealIndividual[] parents) {
		return null;
	}

}
