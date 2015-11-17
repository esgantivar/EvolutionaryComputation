package evolution.operators;

import evolution.individual.RealIndividual;

public class AllXOver extends GeneticOperator<RealIndividual, RealIndividual>{

	@Override
	public RealIndividual[] getIndividuals(RealIndividual[] parents) {
		int dim = parents[0].getDimension();
		RealIndividual offsprings[] = new RealIndividual[2];

		double temp1[] = new double[parents[0].getDimension()];
		double temp2[] = new double[parents[0].getDimension()];

		for (int i = 0; i < dim; i++) {
			if (Math.random() < 0.5) {
				temp1[i] = (double) parents[0].getGene(i).getValue();
				temp2[i] = (double) parents[1].getGene(i).getValue();
			} else {
				temp1[i] = (double) parents[1].getGene(i).getValue();
				temp2[i] = (double) parents[0].getGene(i).getValue();
			}
		}
		offsprings[0] = new RealIndividual(temp1,parents[0].getFunction());
		offsprings[1] = new RealIndividual(temp2,parents[0].getFunction());
		return offsprings;
	}

	@Override
	public RealIndividual getIndividual(RealIndividual ind) {
		return ind;
	}

}
