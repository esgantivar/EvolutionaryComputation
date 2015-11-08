package evolution;

import java.util.ArrayList;

import Function.Function;

public class Population {
	public int DIM;
	private double fitnessAll[];
	private Function f;

	private ArrayList<Individual> population;

	public Population(int DIM_, int nPopulation, double min, double max, Function f_) {
		DIM = DIM_;
		f = f_;
		population = new ArrayList<>();
		for (int i = 0; i < nPopulation; i++) {
			population.add(new RealIndividual(DIM, max, min,f));
		}
	}
	
	public Function getF(){
		return f;
	}
	
	public Population(int DIM_,Function f_,ArrayList<Individual> inds){
		population = new ArrayList<>(inds);
		DIM = DIM_;
		f = f_;
	}
	
	public Population(Population pop){
		population = new ArrayList<>(pop.population);
		f = pop.f;
		DIM = pop.DIM;
		fitnessAll();
	}

	public int nPop() {
		return population.size();
	}

	public void setFunction(Function f_) {
		f = f_;
	}

	public Population(int DIM_) {
		DIM = DIM_;
		population = new ArrayList<>();
	}

	public void addIndividual(Individual ind) {
		population.add(new RealIndividual((RealIndividual) ind,f));
	}

	public Individual getIndividual(int index) {
		return population.get(index);
	}

	public double[] fitnessAll() {
		fitnessAll = new double[population.size()];
		int counter = 0;
		for (Individual ind : population) {
			fitnessAll[counter++] = ind.fitness(f);
		}
		return fitnessAll;
	}

	public double fitnessOne(int index) {
		try {
			return fitnessAll[index];
		} catch (Exception e) {
			return population.get(index).fitness(f);
		}
	}
	
	public ArrayList<Individual> getIndividuals(){
		return population;
	}

	@Override
	public String toString() {
		String s = "";
		for (double d : fitnessAll) {
			//s += d.toString() + "\n";
			s += d + "\n";
		}
		return s;
	}

}
