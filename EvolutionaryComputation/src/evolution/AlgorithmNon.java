package evolution;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import evolution.individual.Individual;
import evolution.individual.Space;
import evolution.operators.Operator;
import evolution.selectors.Selector;
import evolution.util.Solution;
import function.Function;

public class AlgorithmNon<T> {
	private Population<T> pop;
	private Population<T> parents;
	private Population<T> parentsRou;
	private Population<T> offsprings;
	private Selector selector;
	private List<Operator<T>> operators;
	private Space<T> space;
	private int maxIterations;
	private Writer out = null;

	public AlgorithmNon(int nPop_, Space<T> space_, Selector selector_, List<Operator<T>> operators_, int max,
			Function<T> f) {
		space = space_;
		selector = selector_;
		operators = operators_;
		maxIterations = max;
		pop = new Population<>(space.getDimension(), nPop_, space_, f, operators.size());
		selector.setPopulation(pop);
		openFile("seguimientoNoN.txt");
	}

	private int selectOperator(Double rates[]) {
		double range[] = new double[rates.length + 1];
		range[0] = 0.0;
		for (int i = 1; i < rates.length + 1; i++) {
			range[i] = range[i - 1] + rates[i - 1];
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

	@SuppressWarnings("unchecked")
	public void iterate() {
		int t = 0;
		
		while (t<maxIterations && pop.getIndividual(0).getFitness() != -1.0) {
			parents = new Population<>(pop);
			parentsRou = selector.getParents();
			offsprings = new Population<>(pop.popSize());
			offsprings.setFunction(pop.getF());
			int i = 0;
			int indexOperator;
			Double rates[];
			while (i < pop.popSize()) {

				Individual<T> ind = selectParent();

				rates = ind.getRates(); // extrac_rates(ind)
				indexOperator = selectOperator(rates); // OP_SELECT
														// (operators,rates)

				if (operators.get(indexOperator).arity() == 2) {
					if (parents.popSize() >= 1) {
						/************* ParentSelection(Pt,ind) ***************/
						List<Individual<T>> par = new ArrayList<>(2);
						par.add(ind);
						par.add(parentsRou.getIndividual((int) (Math.random() * parentsRou.popSize())));

						/************************ apply ******************************/
						List<Individual<T>> off = operators.get(indexOperator).getIndividuals(par);
						/*********************** dammit *****************************/
						Individual<T> bestOffspring = new Individual<T>(best(off.get(0), off.get(1)));
						// Individual<T> bestParent = new
						// Individual<T>(best(par.get(0), par.get(1)));
						/*************
						 * child= BEST(offspring, ind)
						 ******************/
						Individual<T> child = new Individual<T>(best(bestOffspring, ind));

						/*********************
						 * learning rate
						 ***********************/
						child.setRates(adaptRates(rates, indexOperator, child, ind));
						offsprings.addIndividual(child);
					} else {
						Individual<T> bestOffspring = operators.get(indexOperator).getIndividual(ind);
						Individual<T> child = new Individual<T>(best(bestOffspring, ind));

						child.setRates(adaptRates(rates, indexOperator, child, ind));
						offsprings.addIndividual(child);

					}
				} else if (operators.get(indexOperator).arity() == 1) {
					Individual<T> offspring = operators.get(indexOperator).getIndividual(ind);
					Individual<T> child = new Individual<T>(best(offspring, ind));
					child.setRates(adaptRates(rates, indexOperator, child, ind));
					offsprings.addIndividual(child);
				}
				i++;
			}

			pop = new Population<T>(offsprings);

			selector.setPopulation(pop);
			Solution.sort(pop);
			try {
				out.write(Solution.printStatistics(pop, t + 1) + "\n");
			} catch (IOException e) {
			}
			t++;
		}
		closeFile();
		System.out.println(pop.getIndividual(0).toString());
	}

	private Individual<T> best(Individual<T> offspring, Individual<T> ind) {

		if (offspring.compareTo(ind) <= 0) {// Minimizing
			return new Individual<>(offspring);
		} else {
			return new Individual<>(ind);
		}
	}

	private Individual<T> selectParent() {
		int index = (int) (Math.random() * parents.popSize());
		return parents.remove(index);
	}

	private Double[] adaptRates(Double rates[], int indexOperator, Individual<T> child, Individual<T> ind) {
		double delta = Math.random(); // learning rate
		if (child.compareTo(ind) < 0) {
			rates[indexOperator] = ((1 + delta) * rates[indexOperator]);
		} else {
			rates[indexOperator] = ((1 - delta) * rates[indexOperator]);
		}
		return rates;
	}

	private void openFile(String name) {
		out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(name), "UTF-8"));
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
		}
	}

	private void closeFile() {
		try {
			out.close();
		} catch (IOException ex3) {
		}
	}
}
