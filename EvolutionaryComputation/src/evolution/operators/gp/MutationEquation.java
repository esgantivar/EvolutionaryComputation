package evolution.operators.gp;

import java.util.List;

import evolution.individual.EquationSpace;
import evolution.individual.Individual;
import evolution.individual.gp.Equation;
import evolution.operators.Operator;

public class MutationEquation extends Operator<Equation>{

	private EquationSpace space;
	public MutationEquation(EquationSpace space_) {
		space= space_;
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
		Individual<Equation> nInd = new Individual<Equation>(ind);
		int indexEquation = (int) (Math.random() * nInd.getGenome().size());
		nInd.getGenome().set(indexEquation, space.generateEquation(3));
		return new Individual<Equation>(nInd);
	}
}
