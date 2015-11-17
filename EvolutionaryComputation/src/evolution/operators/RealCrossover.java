package evolution.operators;

import java.util.Random;

import evolution.individual.Individual;
import evolution.individual.RealIndividual;

public class RealCrossover extends GeneticOperator<RealIndividual,RealIndividual> {
	private Random rnd;

	public RealCrossover() {
		arity = 2;
		rnd = new Random();
	}

	public Individual[] CrossPivot(Individual parents[]) {
		int pivot = rnd.nextInt(parents[0].getDimension());
		Individual offsprings[] = new Individual[2];
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
		return null;
	}

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

}
