package evolution.operators;

import evolution.individual.RealIndividual;

public class PivotXOver extends GeneticOperator<RealIndividual, RealIndividual>{

	public PivotXOver() {
		arity = 1;
	}
	@Override
	public RealIndividual[] getIndividuals(RealIndividual[] parents) {
		int pivot = (int)(Math.random()*parents[0].getDimension());
		RealIndividual offsprings[] = new RealIndividual[2];
		double temp1[] = new double[parents[0].getDimension()];
		double temp2[] = new double[parents[0].getDimension()];

		for (int i = 0; i < pivot; i++) {
			temp1[i] = (double) parents[0].getGene(i).getValue();
			temp2[i] = (double) parents[1].getGene(i).getValue();
		}

		for (int i = pivot; i < parents[0].getDimension(); i++) {
			temp1[i] = (double) parents[1].getGene(i).getValue();
			temp2[i] = (double) parents[0].getGene(i).getValue();
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
