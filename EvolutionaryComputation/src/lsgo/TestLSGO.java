package lsgo;

import java.util.Random;

import Function.*;
import evolution.Algorithm;
import geneticOperators.Crossover;
import geneticOperators.GeneticOperator;
import geneticOperators.UniformMutation;
import replacements.Elitist;
import replacements.Generational;
import replacements.Replacement;
import selectors.Roulette;
import selectors.Selector;

public class TestLSGO {
	
	public static void main(String args[]){
		GeneticOperator operators[] = new GeneticOperator[2];
		operators[0] = new Crossover();
		operators[1] = new UniformMutation();
		//operators[2] = new UniformMutation();
		Selector selector = new Roulette();
		Replacement replacement = new Generational();
		//Replacement replacement = new Elitist();
		double min = -100;
		double max = 100;
		int DIM = 1000;
		int nPop = 100;
		int maxIterations = 2000;
		
		Function f = new ShiftedEllipticFunction(1000);
		//Function f = new Schwefel();
		Algorithm search = new Algorithm(DIM, nPop, operators, min, max, selector, replacement, maxIterations, f);	
		search.iterates();
		
	}

}
