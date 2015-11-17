package evolution.operators;

import evolution.individual.RealIndividual;

public class LinearXOver extends GeneticOperator<RealIndividual, RealIndividual> {

	@Override
	public RealIndividual[] getIndividuals(RealIndividual[] parents) {
		RealIndividual offsprings[] = new RealIndividual[2];
		double s;
		double temps[][] = new double[2][parents[0].getDimension()];
		
		for (int i = 0; i < parents[0].getDimension(); i++) {
			s = Math.random();
			temps[0][i] = (s * parents[0].getGene(i).getValue()) + ((s - 1) * parents[1].getGene(i).getValue());
			temps[1][i] = ((s - 1) * parents[0].getGene(i).getValue()) + (s * parents[1].getGene(i).getValue());
		}
		
		for (int i = 0; i < offsprings.length; i++) {
			offsprings[i] = new RealIndividual(temps[i],parents[0].getFunction());
		}
		return offsprings;
	}

	@Override
	public RealIndividual getIndividual(RealIndividual ind) {
		return ind;
	}

}
