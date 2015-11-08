package util;

import evolution.Individual;
import evolution.Population;
import java.util.*;

public class Solution {

	public static void sort(Population pop) {
		Collections.sort(pop.getIndividuals());
	}

	public static void printStatistics(Population pop) {
		double best = pop.getIndividual(0).getFitness();
		double worst = pop.getIndividual(pop.getIndividuals().size()-1).getFitness();
		double total = 0.0;
		for (Individual ind : pop.getIndividuals()) {
			total += ind.getFitness();
		}
		double mean = total / pop.getIndividuals().size();
		double median = pop.getIndividual((int) pop.getIndividuals().size() / 2).getFitness();
		double stdev = 0.0;
		for (Individual ind : pop.getIndividuals()) {
			stdev += Math.pow(ind.getFitness() - mean, 2);
		}
		stdev /= (pop.getIndividuals().size() - 1);
		stdev = Math.sqrt(stdev);
		
		System.out.println(best+";"+median+";"+worst+";"+mean+";"+stdev);
	}

}
