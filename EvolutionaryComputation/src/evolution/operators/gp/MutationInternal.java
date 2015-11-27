package evolution.operators.gp;

import java.util.List;

import evolution.individual.EquationSpace;
import evolution.individual.Individual;
import evolution.individual.gp.Equation;
import evolution.individual.gp.Node;
import evolution.operators.Operator;

public class MutationInternal extends Operator<Equation> {
	private EquationSpace space;

	public MutationInternal(EquationSpace space_) {
		space = space_;
	}
	
	@Override
	public int arity(){
		return 1;
	}

	@Override
	public List<Individual<Equation>> getIndividuals(List<Individual<Equation>> parents) {
		return parents;
	}

	@Override
	public Individual<Equation> getIndividual(Individual<Equation> ind) {
		int indexEquation = (int) (Math.random() * ind.getGenome().size());
		Node nodeAux = ind.getGene(indexEquation).getRandomNode();
		int depth = nodeAux.depth();
		if (nodeAux.getType() == Node.EQUATION && indexEquation < 1) {
			nodeAux.replace(space.generateFunction(depth).getRoot());
		} else {
			int option = Math.random() < 0.5 ? 0 : 1;
			if (option == 0) {
				nodeAux.replace(space.generateTerminal().getRoot());
			} else {
				nodeAux.replace(space.generateFunction(depth).getRoot());
			}
		}
		return new Individual<Equation>(ind);
	}

}
