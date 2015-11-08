package evolution;

import java.util.Random;

import Function.Function;
import geneticOperators.GeneticOperator;
import replacements.Replacement;
import selectors.Selector;
import util.Solution;

public class Algorithm {
	private Population pop;
	private Selector selectorP;
	private Replacement replacement;
	private int maxIterations;
	private int DIM;
	private GeneticOperator[] operators;
	private Random rnd;
	private Function f;
	private double rates[];

	public Algorithm(int DIM_, int nPop, GeneticOperator operators_[], double min, double max, Selector selectorP_,
			Replacement replacement_, int maxIterations_, Function f_) {
		f = f_;
		DIM = DIM_;
		pop = new Population(DIM, nPop, min, max, f);
		operators = operators_;
		rates = new double[operators.length + 1];
		selectorP = selectorP_;
		selectorP.setPopulation(pop);
		replacement = replacement_;
		maxIterations = maxIterations_;
		initializeRatesOperator();
		rangeOperator();
	}

	private void initializeRatesOperator() {
		rnd = new Random();
		double tempRates[] = new double[operators.length];
		double total = 0.0;
		for (int i = 0; i < operators.length; i++) {
			tempRates[i] = rnd.nextDouble();
			total += tempRates[i];
		}
		for (int i = 0; i < operators.length; i++) {
			operators[i].setRate(tempRates[i] / total);
		}
	}

	private void normalizeRates() {
		double tempRates[] = new double[operators.length];
		double total = 0.0;
		for (int i = 0; i < operators.length; i++) {
			tempRates[i] = operators[i].getRate();
			total += tempRates[i];
		}
		for (int i = 0; i < operators.length; i++) {
			operators[i].setRate(tempRates[i] / total);
		}
		rangeOperator();
	}

	private void rangeOperator() {
		rates = new double[operators.length + 1];
		rates[0] = 0;
		for (int i = 1; i < operators.length + 1; i++) {
			rates[i] = rates[i - 1] + operators[i - 1].getRate();
		}
	}

	private int selectOperator(double ticket) {
		int index = operators.length;
		double lowLimit = 0.0;
		for (int i = 1; i < rates.length; i++) {
			if (ticket >= lowLimit && ticket < rates[i]) {
				index = i - 1;
				break;
			} else {
				lowLimit = rates[i];
			}
		}
		return index;
	}

	public void iterate() {
		rnd = new Random();
		int t = 0;
		while (t < maxIterations) {
			Population parents = selectorP.getParents();
			parents.setFunction(f);
			Population offsprings = new Population(pop.DIM);
			offsprings.setFunction(f);

			int i = 0;
			while (i < pop.nPop()) {
				double delta = rnd.nextDouble(); // probability ticket
				int index = selectOperator(delta);
				if (operators[index].arity < 2) {
					offsprings.addIndividual(new RealIndividual(
							operators[index].getIndividual((RealIndividual) parents.getIndividual(i)), f));

					delta = rnd.nextDouble(); // learning rate
					if (offsprings.fitnessOne(i) >= parents.fitnessOne(i)) {
						operators[index].setRate((1 + delta) * operators[index].getRate());// reward
					} else {
						operators[index].setRate((1 - delta) * operators[index].getRate());// punish
					}
					normalizeRates();

				} else {
					Individual par[];
					par = new Individual[2];
					if ((i + 1) < pop.nPop()) {
						par[0] = parents.getIndividual(i++);
						par[1] = parents.getIndividual(i);
						Individual offspring[] = operators[index].getIndividuals(par);
						offsprings.addIndividual(offspring[0]);
						offsprings.addIndividual(offspring[1]);
					} else {
						par[0] = parents.getIndividual(i);
						par[1] = parents.getIndividual(i);
						Individual offspring[] = operators[index].getIndividuals(par);
						offsprings.addIndividual(offspring[0]);
					}

					delta = rnd.nextDouble(); // learning rate
					if (offsprings.fitnessOne(i - 1) >= parents.fitnessOne(i - 1)) {
						operators[index].setRate((1 + delta) * operators[index].getRate());// reward
					} else {
						operators[index].setRate((1 - delta) * operators[index].getRate());// punish

					}
					normalizeRates();
					delta = rnd.nextDouble(); // learning rate
					if (offsprings.fitnessOne(i) >= parents.fitnessOne(i)) {
						operators[index].setRate((1 + delta) * operators[index].getRate());// reward
					} else {
						operators[index].setRate((1 - delta) * operators[index].getRate());// punish
					}
					normalizeRates();
				}
				i++;
			}
			normalizeRates();
			pop = new Population(replacement.replace(parents, offsprings));
			selectorP.setPopulation(pop);
			Solution.sort(pop);
			Solution.printStatistics(pop);
			t++;
		}
		// System.out.println(pop.toString());

	}

	public void iterates() {
		rnd = new Random();
		int t = 0;
		while (t < maxIterations) {
			Population parents = selectorP.getParents();
			parents.setFunction(f);
			Population offsprings = new Population(pop.DIM);
			offsprings.setFunction(f);

			int i = 0;
			while (i < pop.nPop()) {
				Individual par[];
				par = new Individual[2];
				par[0] = parents.getIndividual(i++);
				par[1] = parents.getIndividual(i);
				Individual offspring[] = operators[0].getIndividuals(par);
				offsprings.addIndividual(new RealIndividual(
						operators[1].getIndividual((RealIndividual) offspring[0]), f));
				offsprings.addIndividual(new RealIndividual(
						operators[1].getIndividual((RealIndividual) offspring[1]), f));
				i++;
			}
			normalizeRates();
			pop = new Population(replacement.replace(parents, offsprings));
			selectorP.setPopulation(pop);
			Solution.sort(pop);
			Solution.printStatistics(pop);
			t++;
		}
		// System.out.println(pop.toString());

	}
}
