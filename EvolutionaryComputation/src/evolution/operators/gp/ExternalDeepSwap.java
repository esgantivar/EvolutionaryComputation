package evolution.operators.gp;

import java.util.ArrayList;
import java.util.List;

import evolution.individual.Individual;
import evolution.individual.gp.Equation;
import evolution.individual.gp.Node;
import evolution.operators.Operator;

public class ExternalDeepSwap extends Operator<Equation>{

	@Override
	public int arity(){
		return 2;
	}

	@Override
	public List<Individual<Equation>> getIndividuals(List<Individual<Equation>> parents) {
		List<Equation> list1 = new ArrayList<>();
		List<Equation> list2 = new ArrayList<>();
		
		for (Equation e : parents.get(0).getGenome()) {
			list1.add(new Equation(e));
		}
		
		for (Equation e : parents.get(1).getGenome()) {
			list2.add(new Equation(e));
		}
		
		int index1 = (int)(Math.random()*list1.size());
		int index2 = (int)(Math.random()*list1.size());
		
		Node auxNode1 = list1.get(index1).getRandomNode();
		Node auxNode2 = list2.get(index2).getRandomNode();
		
		Node temp1 = new Node(auxNode1);
        Node temp2 = new Node(auxNode2);
		auxNode1.replace(temp2);
		auxNode2.replace(temp1);
		Individual<Equation> off1 = new Individual<>(parents.get(0).getFunction(),list1);
		Individual<Equation> off2 = new Individual<>(parents.get(1).getFunction(),list2);
		off1.setRates(parents.get(0).getRates());
		off2.setRates(parents.get(1).getRates());
		
		List<Individual<Equation>> offsprings = new ArrayList<>(2);
		offsprings.add(off1);
		offsprings.add(off2);
		return offsprings;
	}

	@Override
	public Individual<Equation> getIndividual(Individual<Equation> ind) {
		return ind;
	}
}
