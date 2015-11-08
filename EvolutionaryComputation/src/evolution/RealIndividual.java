package evolution;

import java.util.ArrayList;
import java.util.Random;
import Function.Function;

public class RealIndividual extends Individual {
	
	private Random rnd;
	public double max;
	public double min;
	private double fitness = Double.NaN;
	private Function f;	
	
	public RealIndividual(int DIM, double max_, double min_,Function f_) {
		type = "RealIndividual";
		f = f_;
		max = max_;
		min = min_;
		rnd = new Random();
		dimension = DIM;
		genome = new RealGene[dimension];

		for (int j = 0; j < dimension; j++) {
			genome[j] = new RealGene((rnd.nextDouble() * (max - min)) + min);
		}
	}

	public RealIndividual(double ind[],Function f_) {
		f = f_;
		dimension = ind.length;
		genome = new RealGene[dimension];
		for (int j = 0; j < dimension; j++) {
			genome[j] = new RealGene(ind[j]);
		}
	}
	
	public RealIndividual(RealIndividual real, Function f_){
		f = f_;
		dimension = real.dimension;
		genome = new RealGene[dimension];
		for (int j = 0; j < dimension; j++) {
			genome[j] = new RealGene(real.genome[j]);
		}
	}

	public double fitness(Function f) {
		if(((Double)fitness).isNaN()){
			double temp[] = new double[genome.length];
			int count = 0;
			for (Gene gene : genome) {
				temp[count++]=(double)gene.getValue();
			}
			fitness = f.apply(temp);
		}
		return fitness;
	}
	
	public double getFitness(){
		if(((Double)fitness).isNaN()){
			double temp[] = new double[genome.length];
			int count = 0;
			for (Gene gene : genome) {
				temp[count++]=(double)gene.getValue();
			}
			fitness = f.apply(temp);
		}
		return fitness;
	}

	@Override
	public String toString() {
		String s = "";
		for (Gene gene : genome) {
			s += gene.toString() + " ";
		}	
		return String.valueOf(fitness);
	}

	@Override
	public int compareTo(Individual o) {
		return ((Double)fitness).compareTo((Double)(o.getFitness()));
	}

	@Override
	public Function getF() {
		return f;
	}

}
