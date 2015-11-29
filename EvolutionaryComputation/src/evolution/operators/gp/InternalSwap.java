package evolution.operators.gp;

import java.util.ArrayList;
import java.util.List;

import evolution.individual.Individual;
import evolution.individual.gp.Equation;
import evolution.individual.gp.Node;
import evolution.operators.Operator;

public class InternalSwap extends Operator<Equation> {

	@Override
	public int arity(){
		return 1;
	}
	
	@Override
	public List<Individual<Equation>> getIndividuals(List<Individual<Equation>> parents) {
		return parents;
	}

	@Override
	public Individual<Equation> getIndividual(final Individual<Equation> ind) {
		 
		Individual<Equation> nInd = new Individual<>(ind);
		
		List<Equation> list = new ArrayList<>();
		for (Equation e : ind.getGenome()) {
			list.add(new Equation(e));
		}
		
		int indexEquation1 = (int) (Math.random() * nInd.getGenome().size());
		int indexEquation2 = (int) (Math.random() * nInd.getGenome().size());

		if (indexEquation2 >= indexEquation1) {
			++indexEquation2;
		}
		indexEquation2 = (indexEquation2 % nInd.getGenome().size());

		Node nodeAux1 = list.get(indexEquation1).getRandomNode();
		Node nodeAux2 = list.get(indexEquation2).getRandomNode();
		
		Node n1 = new Node(nodeAux2);
		Node n2 = new Node(nodeAux1);

		nodeAux1.replace(n1);
		nodeAux2.replace(n2);
		Individual<Equation> newInd = new Individual<>(ind.getFunction(),list);
		newInd.setRates(ind.getRates());
		return newInd;
	}
}
