package evolution;

import java.util.ArrayList;
import java.util.List;

import Function.Function;
import evolution.individual.Space;
import evolution.operators.Operator;
import evolution.replacements.Replacement;
import evolution.selectors.Selector;
import evolution.util.Solution;

public class Algorithm<T> {
	private Population<T> pop;
	private Selector selector;
	private Replacement<T> replacement;
	private List<Operator<T>> operators;
	private Space<T> space;
	private int maxIterations;

	public Algorithm(int DIM, Space<T> space_, Selector selector_, Replacement<T> replacement_,
			List<Operator<T>> operators_, int max, Function<T> f) {
		space = space_;
		selector = selector_;
		replacement = replacement_;
		operators = operators_;
		maxIterations = max;
		pop = new Population<>(space.getDimension(), DIM, space_, f);
		selector.setPopulation(pop);
		initializeRatesOperator();
	}

	private void initializeRatesOperator() {
		double tempRates[] = new double[operators.size()];
		double total = 0.0;
		for (int i = 0; i < operators.size(); i++) {
			tempRates[i] = Math.random();
			total += tempRates[i];
		}
		for (int i = 0; i < operators.size(); i++) {
			operators.get(i).setRate(tempRates[i] / total);
		}
	}

	private void normalizeRates() {
		double tempRates[] = new double[operators.size()];
		double total = 0.0;
		for (int i = 0; i < operators.size(); i++) {
			tempRates[i] = operators.get(i).getRate();
			total += tempRates[i];
		}
		for (int i = 0; i < operators.size(); i++) {
			operators.get(i).setRate(tempRates[i] / total);
		}
	}

	private int selectOperator() {
		double range[] = new double[operators.size() + 1];
		range[0] = 0.0;
		for (int i = 1; i < operators.size() + 1; i++) {
			range[i] = range[i - 1] + operators.get(i - 1).getRate();
		}
		int index = range.length - 2;
		double t = Math.random();
		double lowerLim = 0.0;
		for (int i = 1; i < range.length; i++) {
			if (t >= lowerLim && t < range[i]) {
				index = i - 1;
				break;
			} else {
				lowerLim = range[i];
			}
		}
		return index;
	}

	public void iterate() {
		int t = 0;
		while (t < maxIterations) {
			@SuppressWarnings("unchecked")
			Population<T> parents = selector.getParents();
			parents.setFunction(pop.getF());
			Population<T> offsprings = new Population<>(space.getDimension());
			offsprings.setFunction(pop.getF());
			int i = 0;
			int indexOperator;
			while (i < pop.nPop()) {
				indexOperator = selectOperator();
				if (operators.get(indexOperator).arity() == 2) {
					if ((i + 1) < pop.nPop()) {
						offsprings.addIndividuals(operators.get(indexOperator)
								.getIndividuals(new ArrayList<>(parents.getIndividuals().subList(i, i+2 ))));
						i++;
					} else {
						offsprings.addIndividual(operators.get(indexOperator).getIndividual(parents.getIndividual(i)));
					}
				} else if (operators.get(indexOperator).arity() == 1) {
					offsprings.addIndividual(operators.get(indexOperator).getIndividual(parents.getIndividual(i++)));
				}
				i++;
			}
			pop = new Population<T>(replacement.replace(parents, offsprings));
			Solution.sort(pop);
			Solution.printStatistics(pop);
			t++;
		}
	}
}

