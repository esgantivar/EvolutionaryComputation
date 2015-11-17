package evolution.individual;

import Function.Function;

public class RealIndividual extends Individual<Double> {

	public double max;
	public double min;

	public RealIndividual(int DIM, Function f_) {
		dimension = DIM;
		fitness = null;
		f = f_;
		genome = new RealGene[DIM];
		for (int i = 0; i < DIM; i++) {
			genome[i] = new RealGene();
		}
	}

	public RealIndividual(int DIM, double max_, double min_, Function f_) {
		f = f_;
		max = max_;
		min = min_;
		dimension = DIM;
		genome = new RealGene[dimension];

		for (int j = 0; j < dimension; j++) {
			genome[j] = new RealGene(((Math.random() * (max - min)) + min));
		}
	}

	public RealIndividual(double ind[], Function f_) {
		f = f_;
		dimension = ind.length;
		genome = new RealGene[dimension];
		for (int j = 0; j < dimension; j++) {
			genome[j] = new RealGene(ind[j]);
		}
	}

	public RealIndividual(RealIndividual real, Function f_) {
		f = f_;
		dimension = real.dimension;
		genome = new RealGene[dimension];
		for (int j = 0; j < dimension; j++) {
			genome[j] = new RealGene(real.genome[j]);
		}
	}

	public void setMin(double min_){
		min = min_;
	}
	public void setMax(double max_){
		max = max_;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (Gene<Double> gene : genome) {
			s.append(gene.toString() + " ");
		}
		return String.valueOf(fitness);
	}

	@Override
	public int compareTo(Individual<?> ind) {
		return fitness.compareTo(ind.getFitness());
	}

	@Override
	public Double computeFitness() {
		double temp[] = new double[genome.length];
		int count = 0;
		for (Gene<Double> gene : genome) {
			temp[count++] = gene.getValue();
		}
		fitness = f.apply(temp);
		return fitness;
	}

}
