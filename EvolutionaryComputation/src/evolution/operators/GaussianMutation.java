package evolution.operators;

import java.util.List;
import java.util.Random;

import evolution.individual.Individual;
import evolution.individual.Space;

public class GaussianMutation extends Operator<Double> {
	private AdaptMutationIntensity intensity;
	private double sigma;
	private double delta[];
	private double rate;
	private Random rnd;
	private boolean flag = false;
	private Space<Double> space;

	public GaussianMutation(double sigma_, AdaptMutationIntensity intensity_, double rate_) {
		intensity = intensity_;
		rnd = new Random();
		rate = rate_;
		flag = true;
		sigma = sigma_;
	}

	public GaussianMutation(double sigma_, double rate_) {
		intensity = null;
		rnd = new Random();
		rate = rate_;
		flag = true;
		sigma = sigma_;
	}

	public GaussianMutation(double sigma_, AdaptMutationIntensity intensity_) {
		sigma = sigma_;
		intensity = intensity_;
		rnd = new Random();
		rate = 0.5;
	}
	
	public GaussianMutation(double sigma_, AdaptMutationIntensity intensity_,Space<Double> space_) {
		sigma = sigma_;
		intensity = intensity_;
		rnd = new Random();
		rate = 0.5;
		space = space_;
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
		Double temp[] = new Double[ind.getDimension()];
		int o = 0;
		for (double d : ind.getGenome()) {
			temp[o++] = d;
		}
		if (flag) {
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
				//ind.setGene(pick[i], ind.getGene(pick[i]) + delta[i]);
				temp[pick[i]]=temp[pick[i]]+delta[i];
			}
			Individual<Double> newInd = new Individual<>(ind.getFunction(), temp);
			double fAfter = newInd.computeFitness();
			double pro = (fAfter / fBefore) - 1.0;
			if (intensity != null) {
				sigma = intensity.apply(sigma, pro);
			}
			return newInd;
		} else {
			int nGene = (int) (ind.getDimension());
			delta = new double[nGene];
			for (int i = 0; i < delta.length; i++) {
				delta[i] = sigma * rnd.nextGaussian();
			}
			double fBefore = ind.getFitness();
			for (int i = 0; i < nGene; i++) {
				if(Math.random() > rate){
					//ind.setGene(i, ind.getGene(i)+delta[i]);
					double min = space.limitLow();
					double max = space.limitHigh();
					temp[i]=(temp[i]+delta[i]);
					double mod = ((temp[i]-min)%(max-min))+min;
					if(mod < min){
						mod += (max-min);
					}
					temp[i] = mod;
				}
			}
			
			
			
			Individual<Double> newInd = new Individual<>(ind.getFunction(), temp);
			double fAfter = newInd.computeFitness();
			double pro = (fAfter / fBefore) - 1.0;
			if (intensity != null) {
				sigma = intensity.apply(sigma, pro);
			}
			newInd.setRates(ind.getRates());
			return newInd;
		}
	}

}
