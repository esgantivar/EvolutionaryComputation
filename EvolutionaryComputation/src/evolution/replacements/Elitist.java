package evolution.replacements;

import java.util.ArrayList;

import evolution.Population;
import evolution.individual.Individual;
import util.Solution;

public class Elitist implements Replacement{

	@Override
	public Population replace(Population parents, Population offsprings) {
		Population p = new Population(parents.DIM);
		parents.fitnessAll();
		offsprings.fitnessAll();
		p.setFunction(parents.getF());
		for (Individual<?> par : parents.getIndividuals()) {
			p.addIndividual(par);
		}
		
		for (Individual<?> off : offsprings.getIndividuals()) {
			p.addIndividual(off);
		}
		
		p.fitnessAll();
		Solution.sort(p);
		ArrayList<Individual<?>> inds = new ArrayList<>();
		for(Individual<?> ind: p.getIndividuals().subList(0, (int)p.getIndividuals().size()/2)){
			inds.add(ind);
		}
		return new Population(parents.DIM,parents.getF(),inds);
	}

}
