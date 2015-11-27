package evolution.operators.gp;

import java.util.ArrayList;
import java.util.List;

import evolution.individual.Individual;
import evolution.individual.gp.Equation;
import evolution.operators.Operator;

public class XOverEquation extends Operator<Equation> {

	@Override
	public List<Individual<Equation>> getIndividuals(List<Individual<Equation>> parents) {
		List<Individual<Equation>> inds =  new ArrayList<>(parents.size());
		for (int i = 0; i < parents.size(); i++) {
			inds.add(new Individual<>(parents.get(i)) );
		}
		List<Equation> list1 = inds.get(0).getGenome();
		List<Equation> list2 = inds.get(1).getGenome();
		
		int index1 = (int)(Math.random()*list1.size());
		int index2 = (int)(Math.random()*list2.size());
		
		Equation a = list1.get(index1);
		Equation b = list1.get(index2);
		Equation e1 = new Equation(b);
		Equation e2 = new Equation(a);
		
		int k1 = (int)(Math.random()*list1.size());
		int k2 = (int)(Math.random()*list2.size());
		
		list1.set(k1, e1);
		list2.set(k2, e2);
		return inds;
	}

	@Override
	public Individual<Equation> getIndividual(Individual<Equation> ind) {
		return ind;
	}
	
}
