package evolution;

import java.util.ArrayList;
import java.util.List;

import evolution.individual.Individual;
import evolution.individual.Space;
import function.Function;

public class Population<T> {
	public int pSize;
	private double fitnessAll[];
	private Function<T> f;
	private Space<T> space;
	private ArrayList<Individual<T>> population;

	public Population(int popSize_, int nPopulation, Space<T> space_, Function<T> f_, int nOperators) {
		pSize = popSize_;
		f = f_;
		space = space_;
		population = new ArrayList<>();
		for (int i = 0; i < nPopulation; i++) {
			population.add(new Individual<T>(pSize, f, space, nOperators));
		}
	}

	public Population(int DIM_, Function<T> f_, ArrayList<Individual<T>> inds) {
		population = new ArrayList<>(inds);
		pSize = DIM_;
		f = f_;
	}

	public Population(Population<T> pop) {
		population = new ArrayList<>(pop.popSize());
		for (Individual<T> ind : pop.getIndividuals()) {
			population.add(new Individual<>(ind));
		}
		
		f = pop.f;
		pSize = pop.pSize;
		fitnessAll();
	}
	
	public Population(List<Individual<T>> inds, Function<T> f_){
		population = new ArrayList<>(inds.size());
		for (Individual<T> ind : inds) {
			population.add(new Individual<>(ind));
		}
		f = f_;
	}

	public Function<T> getF() {
		return f;
	}

	public int popSize() {
		return population.size();
	}

	public void setFunction(Function<T> f_) {
		f = f_;
	}

	public Population(int popSize_) {
		pSize = popSize_;
		population = new ArrayList<>();
	}

	public void addIndividual(Individual<T> ind) {
		population.add(new Individual<T>(ind));
	}

	public void addIndividuals(List<Individual<T>> inds) {
		for (Individual<T> ind : inds) {
			population.add(ind);
		}
	}

	public Individual<T> remove(int index) {
		if (index < population.size()) {
			return population.remove(index);
		} else {
			return null;

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

	public List<Individual<T>> getIndividuals() {
		return population;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (double d : fitnessAll) {
			s.append(d + "\n");
		}
		return s.toString();
	}
}