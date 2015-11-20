package evolution.operators;

import java.util.List;

import evolution.individual.Individual;
import evolution.individual.Space;

public class UniformMutation extends Operator<Double> {
	private Space<Double> space;
	private double rate;
	public UniformMutation(Space<Double> space2, double rate_) {
		space=space2;
		rate = rate_;
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
		int pick[] = new int[(int) (ind.getDimension() * rate)];
		for (int i = 0; i < ((int) (ind.getDimension() * rate)); i++) {
			pick[i] = (int) (Math.random() * ind.getDimension());
		}

		for (int i = 0; i < pick.length; i++) {
			double s = Math.random();
			Double value = ind.getGene(pick[i]);
			if (Math.random() < 0.5) {
				value = s * value + (1 - s) * space.limitLow();
			} else {
				value = s * value + (1 - s) * space.limitHigh();
			}
			ind.setGene(pick[i],value);
		}
		return ind;
	}

}
