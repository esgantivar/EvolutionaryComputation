package evolution.operators;

import java.util.List;
import java.util.Random;

import evolution.individual.Individual;

public class GaussianMutation extends Operator<Double> {
	private AdaptMutationIntensity intensity;
	private double sigma;
	private double delta[];
	private double rate;
	private Random rnd;

	public GaussianMutation(double sigma_, AdaptMutationIntensity intensity_, double rate_) {
		intensity = intensity_;
		rnd = new Random();
		rate = rate_;
	}
	
	public GaussianMutation(double sigma_,double rate_) {
		intensity = null;
		rnd = new Random();
		rate = rate_;
	}

	@Override
	public int arity() {
		return 1;
	}

	@Override
	public List<Individual<Double>> getIndividuals(List<Individual<Double>> parents) {
		return parents;
	}

	@Override
	public Individual<Double> getIndividual(Individual<Double> ind) {
		int nGene = (int) (ind.getDimension() * rate);
		delta = new double[nGene];
		int pick[] = new int[nGene];
		for (int i = 0; i < nGene; i++) {
			pick[i] = (int) (Math.random() * ind.getDimension());
		}
		for (int i = 0; i < delta.length; i++) {
			delta[i] = sigma * rnd.nextGaussian();
		}
		double fBefore = ind.getFitness();
		for (int i = 0; i < pick.length; i++) {
			ind.setGene(pick[i], ind.getGene(pick[i]) + delta[i]);
		}
		double fAfter = ind.computeFitness();
		double pro = (fAfter / fBefore) - 1.0;
		if(intensity != null){
			sigma = intensity.apply(sigma, pro);
		}
		
		return ind;
	}

}
