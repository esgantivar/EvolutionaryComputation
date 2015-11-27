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
	public List<Individual<Equation>> getIndividuals(List<Individual<Equation>> parents) {
		return parents;
	}

	@Override
	public Individual<Equation> getIndividual(Individual<Equation> ind) {
		int indexEquation = (int) (Math.random() * ind.getGenome().size());
		ind.getGenome().set(indexEquation, space.generateEquation(3));
		return new Individual<Equation>(ind);
	}
}
