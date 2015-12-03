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
import evolution.replacements.Replacement;
import evolution.selectors.Inbreeding;
import evolution.util.Solution;
import function.Function;
import function.distance.Distance;

public class AlgorithmDC<T> {
	private Population<T> pop;
	private Population<T> parents;
	private Population<T> parentsSel;
	private Population<T> offsprings;
	private Inbreeding selector;
	@SuppressWarnings("unused")
	private Replacement<T> replacement;
	private List<Operator<T>> operators;
	private Space<T> space;
	private int maxIterations;
	private Distance<T> distance;
	private int poolMF;
	private Writer out = null;
	public String name;

	public AlgorithmDC(int DIM, Space<T> space_, Inbreeding selector_, Replacement<T> replacement_,
			List<Operator<T>> operators_, int max, Function<T> f, Distance<T> dis) {
		space = space_;
		selector = selector_;
		replacement = replacement_;
		operators = operators_;
		maxIterations = max;
		distance = dis;
		poolMF = (int)(0.1*DIM);
		selector.setMF(poolMF);

		pop = new Population<>(space.getDimension(), DIM, space_, f, operators.size());
		selector.setPopulation(pop);
		name = f.getClass().getName() + "DC_pop_ini.txt";
		openFile();
		for (Individual<T> ind : pop.getIndividuals()) {
			try {
				out.write(ind.toString()+" "+(-1*ind.getFitness())+"\n");
			} catch (IOException e) {
			}
		}
		closeFile();
		
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
		while (t < maxIterations) {
			parents = new Population<>(pop);
			offsprings = new Population<>(space.getDimension());
			offsprings.setFunction(pop.getF());
			int i = 0;
			int indexOperator;
			Double rates[];
			while (i < pop.popSize()) {
				Individual<T> ind = selectParent();
				rates = ind.getRates(); // extrac_rates(ind)
				indexOperator = selectOperator(rates); // OP_SELECT (operators,
														// rates)
				
				if (operators.get(indexOperator).arity() == 2) {
					/************* ParentSelection(Pt,ind) ***************/
					List<Individual<T>> par = new ArrayList<>(2);
					par.add(ind);
					selector.setPopulation(pop);
					parentsSel = selector.getParents();
					par.add(bestDC(ind,parentsSel.getIndividuals()));
					/************************ apply ******************************/
					List<Individual<T>> off = operators.get(indexOperator).getIndividuals(par);
					/*************child= BEST(offspring, ind)********************/
					Individual<T> child = bestDC(ind, off);
					/*********************
					 * learning rate
					 ***********************/
					child.setRates(adaptRates(rates, indexOperator, child, ind));
					offsprings.addIndividual(child);

				} else if (operators.get(indexOperator).arity() == 1) {
					
					Individual<T> offspring = operators.get(indexOperator).getIndividual(ind);
					List<Individual<T>> temp = new ArrayList<>(1);
					temp.add(offspring);
					Individual<T> child = bestDC(ind, temp);
					child.setRates(adaptRates(rates, indexOperator, child, ind));
					offsprings.addIndividual(child);
				}
				i++;
			}
			pop = new Population<T>(offsprings);
			selector.setPopulation(pop);
			Solution.sort(pop);
			//Solution.printStatistics(pop,t+1);
			if((t+1)%10==0){
				name = pop.getF().getClass().getName() + "DC"+(t+1)+".txt";
				openFile();
				for (Individual<T> ind : pop.getIndividuals()) {
					try {
						out.write(ind.toString()+" "+(-1*ind.getFitness())+"\n");
					} catch (IOException e) {
					}
				}
				closeFile();
			}
			
			/*try {
				out.write(Solution.printStatistics(pop,t+1)+"\n");
			} catch (IOException e) {
			}*/
			t++;
		}
		closeFile();
	}

	private Individual<T> best(Individual<T> offspring, Individual<T> ind) {
		if (offspring.compareTo(ind) <= 0) {// Minimizing
			return offspring;
		} else {
			return ind;
		}
	}

	private Individual<T> bestDC(Individual<T> parent, List<Individual<T>> offsprings) {
		List<Individual<T>> temp = new ArrayList<>(2);
		temp.add(parent);
		temp.add(offsprings.get(0));
		Individual<T> off = offsprings.get(0);
		double min = distance.apply(temp);
		double dis;
		for (int i = 1; i < offsprings.size(); i++) {
			temp.remove(offsprings.get(i - 1));
			temp.add(offsprings.get(i));
			dis = distance.apply(temp);
			if (dis < min && dis > 0) {
				min = distance.apply(temp);
				off = offsprings.get(i);
			}
		}
		return best(off, parent);
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
	
	private void openFile() {
		out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(name), "UTF-8"));
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
		}
	}
	private void closeFile(){
		try {
			out.close();
		} catch (IOException ex3) {
		}
	}
}
