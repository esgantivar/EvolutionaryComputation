package evolution.operators;

import java.util.ArrayList;
import java.util.List;

import evolution.individual.Individual;

public class AllXOver extends GeneticOperator<Double>{

	public AllXOver() {}
	
	@Override
	public int arity(){
		return 2;
	}
	
	@Override
	public List<Individual<Double>> getIndividuals(List<Individual<Double>> parents) {
		int dim = parents.get(0).getDimension();
		Double temp1[] = new Double[dim];
		Double temp2[] = new Double[dim];
		for (int i = 0; i < dim; i++) {
			if (Math.random() < 0.5) {
				temp1[i] = parents.get(0).getGene(i);
				temp2[i] = parents.get(1).getGene(i);
			} else {
				temp1[i] = parents.get(1).getGene(i);
				temp2[i] = parents.get(0).getGene(i);
			}
		}
		ArrayList<Individual<Double>> offsprings = new ArrayList<>(2);
		offsprings.add(new Individual<Double>(parents.get(0).getFunction(), temp1));
		offsprings.add(new Individual<Double>(parents.get(0).getFunction(), temp2));
		return offsprings;
	}

	@Override
	public Individual<Double> getIndividual(Individual<Double> ind) {
		return ind;
	}

}
