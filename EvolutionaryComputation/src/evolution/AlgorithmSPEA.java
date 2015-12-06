package evolution;

import java.util.ArrayList;
import java.util.List;

import evolution.individual.Individual;
import evolution.individual.Space;
import evolution.operators.Operator;
import evolution.selectors.RouletteSPEA;
import function.MultiObjetiveFunction;

public class AlgorithmSPEA<T> {
	private ArrayList<Individual<T>> paretoSet;
	private Population<T> population;
	private MultiObjetiveFunction mFunction;
	private List<Operator<T>> operators;
	private Space<T> space;
	private int maxIteration;
	private double[] stregthParetoSet;
	private double[] fitnessPopulation;
	private boolean[] dom;
	private RouletteSPEA selector;
	private int nPareto;
	private int nPop;
	private ArrayList<Individual<T>> matingPool;

	public AlgorithmSPEA(int nPop_, int nPareto_, int maxIteration_, List<Operator<T>> operators_, Space<T> space_,
			MultiObjetiveFunction mFunction_) {
		nPareto = nPareto_;
		nPop = nPop_;
		maxIteration = maxIteration_;
		space = space_;
		operators = operators_;
		mFunction = mFunction_;
		population = new Population<>(nPop_, space, operators.size());
		paretoSet = new ArrayList<>();
		calculateNonDomain();
		removeNonDomain();
		calculateStrengthParetoSet();
		calculateFitnessValues();
		selector = new RouletteSPEA(fitnessPopulation);
	}

	@SuppressWarnings("unchecked")
	private void calculateNonDomain() {
		dom = new boolean[population.popSize()];
		for (int i = 0; i < dom.length; i++) {
			dom[i] = false;
		}
		for (int i = 0; i < population.popSize(); i++) {
			for (int j = 0; j < population.popSize(); j++) {
				dom[i] = dom[i] || mFunction.isDominating((List<Double>) population.getIndividual(j).getGenome(),
						(List<Double>) population.getIndividual(i).getGenome());
			}
		}
		List<Individual<T>> newPop = new ArrayList<>();
		for (int i = 0; i < dom.length; i++) {
			if (!dom[i]) {
				paretoSet.add(new Individual<>(population.getIndividual(i)));
			} else {
				newPop.add(new Individual<>(population.getIndividual(i)));
			}
		}
		population = new Population<>(newPop, population.getF());
	}

	@SuppressWarnings("unchecked")
	private void removeNonDomain() {
		dom = new boolean[paretoSet.size()];
		for (int i = 0; i < dom.length; i++) {
			dom[i] = false;
		}
		for (int i = 0; i < paretoSet.size(); i++) {
			for (int j = 0; j < paretoSet.size(); j++) {
				dom[i] = dom[i] || mFunction.isDominating((List<Double>) paretoSet.get(j).getGenome(),
						(List<Double>) paretoSet.get(i).getGenome());
			}
		}
		List<Individual<T>> newParetoSet = new ArrayList<>();
		for (int i = 0; i < dom.length; i++) {
			if (!dom[i]) {
				newParetoSet.add(new Individual<>(paretoSet.get(i)));
			} else {
				population.addIndividual(new Individual<>(paretoSet.get(i)));
			}
		}
		paretoSet = new ArrayList<>();
		for (Individual<T> ind : newParetoSet) {
			paretoSet.add(new Individual<>(ind));
		}
		// System.out.println("pareto: "+paretoSet.size()+" pop:
		// "+population.popSize());
	}

	@SuppressWarnings("unchecked")
	private void calculateStrengthParetoSet() {
		stregthParetoSet = new double[paretoSet.size()];
		int i = 0;
		int count = 0;
		for (Individual<T> indNon : paretoSet) {
			count = 0;
			for (Individual<T> ind : population.getIndividuals()) {
				if (mFunction.isDominating((List<Double>) indNon.getGenome(), (List<Double>) ind.getGenome())) {
					count++;
				}
			}
			stregthParetoSet[i++] = (Double.valueOf(count) / Double.valueOf(population.popSize() + 1));
		}
	}

	@SuppressWarnings("unchecked")
	private void calculateFitnessValues() {
		fitnessPopulation = new double[population.popSize()];
		int i = 0;
		for (Individual<T> ind : population.getIndividuals()) {
			double sum = 0.0;
			for (int j = 0; j < paretoSet.size(); j++) {
				if (mFunction.isDominating((List<Double>) paretoSet.get(j).getGenome(),
						(List<Double>) ind.getGenome())) {
					sum += stregthParetoSet[j];
				}
			}
			fitnessPopulation[i++] = (sum + 1.0);
		}
	}

	private void reducedParetoSet() {
		if (paretoSet.size() > nPareto) {
			List<Integer> indexs = new ArrayList<>();
			while (indexs.size() < nPareto) {
				Integer index = (int) (Math.random() * paretoSet.size());
				if (!indexs.contains(index)) {
					indexs.add(index);
				}
			}
			List<Individual<T>> newParetoSet = new ArrayList<>();
			for (Integer index : indexs) {
				newParetoSet.add(new Individual<>(paretoSet.get(index)));
			}
			paretoSet = new ArrayList<>();
			for (Individual<T> ind : newParetoSet) {
				paretoSet.add(new Individual<>(ind));
			}
		}
	}

	public void iterate() {
		int t = 0;
		while (t < maxIteration) {
			double inTicket[] = new double[population.popSize() + paretoSet.size()];
			System.out.println("pop: "+population.popSize()+ " paretoSet: "+paretoSet.size());
			matingPool = new ArrayList<>();
			int i = 0;
			int cPop = 0;
			for (Individual<T> ind : population.getIndividuals()) {
				matingPool.add(new Individual<>(ind));
				inTicket[i++] = fitnessPopulation[cPop++];
			}
			int cPar = 0;
			for (Individual<T> ind : paretoSet) {
				matingPool.add(new Individual<>(ind));
				inTicket[i++] = stregthParetoSet[cPar++];
			}
			selector.setFitness(inTicket);
			int counter = 0;
			List<Individual<T>> newPop = new ArrayList<>();
			while (counter < nPop) {
				// CrossOver
				int idx1 = selector.getTicket();
				int idx2 = selector.getTicket();
				List<Individual<T>> parents = new ArrayList<>();
				parents.add(new Individual<>(matingPool.get(idx1)));
				parents.add(new Individual<>(matingPool.get(idx2)));
				List<Individual<T>> offsprings = operators.get(0).getIndividuals(parents);
				offsprings.get(0).setRates(parents.get(0).getRates());
				offsprings.get(1).setRates(parents.get(1).getRates());

				// Gaussian Mutation
				newPop.add(operators.get(1).getIndividual(offsprings.get(0)));
				newPop.add(operators.get(1).getIndividual(offsprings.get(1)));
				counter += 2;
			}
			population.cleanPopulation();
			population.addIndividuals(newPop);
			calculateNonDomain();
			removeNonDomain();
			reducedParetoSet();
			calculateStrengthParetoSet();
			calculateFitnessValues();

			t++;
		}
	}
}
