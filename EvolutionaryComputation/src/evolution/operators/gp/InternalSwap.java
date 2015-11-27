package evolution.operators.gp;

import java.util.List;

import evolution.individual.Individual;
import evolution.individual.gp.Equation;
import evolution.individual.gp.Node;
import evolution.operators.Operator;

public class InternalSwap extends Operator<Equation> {

	@Override
	public List<Individual<Equation>> getIndividuals(List<Individual<Equation>> parents) {
		return parents;
	}

	@Override
	public Individual<Equation> getIndividual(Individual<Equation> ind) {
		int indexEquation1 = (int) (Math.random() * ind.getGenome().size());
		int indexEquation2 = (int) (Math.random() * ind.getGenome().size());

		if (indexEquation2 >= indexEquation1) {
			++indexEquation2;
		}
		indexEquation2 = (indexEquation2 % ind.getGenome().size());

		Node nodeAux1 = ind.getGene(indexEquation1).getRandomNode();
		Node nodeAux2 = ind.getGene(indexEquation2).getRandomNode();
		
		Node n1 = new Node(nodeAux2);
		Node n2 = new Node(nodeAux1);

		nodeAux1.replace(n1);
		nodeAux2.replace(n2);

		return new Individual<Equation>(ind);
	}

}
