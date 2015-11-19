package evolution.operators;

import java.util.ArrayList;
import java.util.List;

import evolution.individual.Individual;

public class LinearXOver extends GeneticOperator<Double> {

	public LinearXOver() {
	}

	@Override
	public int arity() {
		return 2;
	}

	@Override
	public List<Individual<Double>> getIndividuals(List<Individual<Double>> parents) {
		List<Individual<Double>> offsprings = new ArrayList<>(2);
		double s;
		Double temps[][] = new Double[2][parents.get(0).getDimension()];

		for (int i = 0; i < parents.get(0).getDimension(); i++) {
			s = Math.random();
			temps[0][i] = (s * parents.get(0).getGene(i)) + ((s - 1) * parents.get(1).getGene(i));
			temps[1][i] = ((s - 1) * parents.get(0).getGene(i)) + (s * parents.get(1).getGene(i));
		}
		offsprings.add(new Individual<Double>(parents.get(0).getFunction(), temps[0]));
		offsprings.add(new Individual<Double>(parents.get(0).getFunction(), temps[1]));
		return offsprings;
	}

	@Override
	public Individual<Double> getIndividual(Individual<Double> ind) {
		return ind;
	}

}
