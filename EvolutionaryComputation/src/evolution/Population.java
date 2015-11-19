package evolution;

import java.util.ArrayList;
import java.util.List;

import Function.Function;
import evolution.individual.Individual;
import evolution.individual.Space;

public class Population<T> {
	public int popSize;
	private double fitnessAll[];
	private Function<T> f;
	private Space<T> space;

	private ArrayList<Individual<T>> population;

	public Population(int popSize_, int nPopulation, Space<T> space_, Function<T> f_) {
		popSize = popSize_;
		f = f_;
		space = space_;
		population = new ArrayList<>();
		for (int i = 0; i < nPopulation; i++) {
			population.add(new Individual<T>(popSize, f,space));
		}
	}
	
	public Function<T> getF(){
		return f;
	}
	
	public Population(int DIM_,Function<T> f_,ArrayList<Individual<T>> inds){
		population = new ArrayList<>(inds);
		popSize = DIM_;
		f = f_;
	}
	
	public Population(Population<T> pop){
		population = new ArrayList<>(pop.population);
		f = pop.f;
		popSize = pop.popSize;
		fitnessAll();
	}

	public int nPop() {
		return population.size();
	}

	public void setFunction(Function<T> f_) {
		f = f_;
	}

	public Population(int popSize_) {
		popSize= popSize_;
		population = new ArrayList<>();
	}

	public void addIndividual(Individual<T> ind) {
		population.add(new Individual<T>(ind));
	}
	
	public void addIndividuals(List<Individual<T>> inds){
		for (Individual<T> ind : inds) {
			population.add(ind);
		}
	}

	public Individual<T> getIndividual(int index) {
		return population.get(index);
	}

	public double[] fitnessAll() {
		fitnessAll = new double[population.size()];
		int counter = 0;
		for (Individual<?> ind : population) {
			fitnessAll[counter++] = ind.getFitness();
		}
		return fitnessAll;
	}

	public double fitnessOne(int index) {
		try {
			return fitnessAll[index];
		} catch (Exception e) {
			return population.get(index).computeFitness();
		}
	}
	
	public List<Individual<T>> getIndividuals(){
		return population;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (double d : fitnessAll) {
			s.append(d + "\n") ;
		}
		return s.toString();
	}

}
