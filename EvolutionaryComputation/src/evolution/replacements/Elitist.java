package evolution.replacements;

import java.util.ArrayList;

import evolution.Population;
import evolution.individual.Individual;
import evolution.util.Solution;

public class Elitist implements Replacement<Double>{

	@Override
	public Population<Double> replace(Population<Double> parents, Population<Double> offsprings) {
		Population<Double> p = new Population<Double>(parents.popSize);
		parents.fitnessAll();
		offsprings.fitnessAll();
		p.setFunction(parents.getF());
		
		for (Individual<Double> par : parents.getIndividuals()) {
			p.addIndividual(par);
		}
		
		for (Individual<Double> off : offsprings.getIndividuals()) {
			p.addIndividual(off);
		}
		
		p.fitnessAll();
		Solution.sort(p);
		ArrayList<Individual<Double>> inds = new ArrayList<>();
		for(Individual<Double> ind: p.getIndividuals().subList(0, (int)p.getIndividuals().size()/2)){
			inds.add(ind);
		}
		return new Population<Double>(parents.popSize,parents.getF(),inds);
	}
}
