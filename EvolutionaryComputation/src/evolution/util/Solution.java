package evolution.util;

import evolution.Population;
import evolution.individual.Individual;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.*;

public class Solution {

	public static void sort(Population<?> pop) {
		Collections.sort(pop.getIndividuals());
	}
	
	public static void fileStatistics(Population<?> pop, String name){
		
	}

	public static void printStatistics(Population<?> pop) {
		Double rates[] = new Double[pop.getIndividual(0).getRates().length];
		for (int i = 0; i < rates.length; i++) {
			rates[i] = 0.0;
		}
		double best = pop.getIndividual(0).getFitness();
		double worst = pop.getIndividual(pop.getIndividuals().size()-1).getFitness();
		double total = 0.0;
		for (Individual<?> ind : pop.getIndividuals()) {
			total += ind.getFitness();
		}
		double mean = total / pop.getIndividuals().size();
		double median = pop.getIndividual((int) pop.getIndividuals().size() / 2).getFitness();
		double stdev = 0.0;
		for (Individual<?> ind : pop.getIndividuals()) {
			stdev += Math.pow(ind.getFitness() - mean, 2);
		}
		stdev /= (pop.getIndividuals().size() - 1);
		stdev = Math.sqrt(stdev);
		
		for (Individual<?> ind : pop.getIndividuals()) {
			for (int i = 0; i < rates.length; i++) {
				rates[i]+=ind.getRates()[i];
			}	
		}
		
		for (int i = 0; i < rates.length; i++) {
			rates[i]/=pop.popSize();
		}
		
		double totalR = 0.0;
		for (int i = 0; i < rates.length; i++) {
			totalR += rates[i];
		}
		
		for (int i = 0; i < rates.length; i++) {
			rates[i]/=totalR;
		}
		StringBuilder sb = new StringBuilder();
		for (Double rate : rates) {
			sb.append(rate + ";");
		}
		System.out.println(best+";"+median+";"+worst+";"+mean+";"+stdev+";"+sb.toString());
	}

}
