package evolution.operators;

import java.util.ArrayList;
import java.util.List;
import evolution.individual.Individual;

public class PivotXOver extends GeneticOperator<Double>{

	public PivotXOver() {
	}
	
	@Override
	public int arity(){
		return 2;
	}

	@Override
	public List<Individual<Double>> getIndividuals(List<Individual<Double>> parents) {
		int pivot = (int)(Math.random()*parents.get(0).getDimension());
		int dim = parents.get(0).getDimension();
		List<Individual<Double>> offsprings = new ArrayList<>(2);
		Double temp1[] = new Double[dim];
		Double temp2[] = new Double[dim];

		for (int i = 0; i < pivot; i++) {
			temp1[i] = (double) parents.get(0).getGene(i);
			temp2[i] = (double) parents.get(1).getGene(i);
		}

		for (int i = pivot; i < dim; i++) {
			temp1[i] = (double) parents.get(1).getGene(i);
			temp2[i] = (double) parents.get(0).getGene(i);
		}
		offsprings.add(new Individual<Double>(parents.get(0).getFunction(),temp1));
		offsprings.add(new Individual<Double>(parents.get(0).getFunction(),temp2));
		return offsprings;
	}

	@Override
	public Individual<Double> getIndividual(Individual<Double> ind) {
		return ind;
	}

}
