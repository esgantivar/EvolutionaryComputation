package evolution.operators.gp;

import java.util.List;

import evolution.individual.Individual;
import evolution.operators.Operator;
import fplearning.interpreter.Equation;

public class ExternalDeepSwap extends Operator<Equation>{

	@Override
	public int arity(){
		return 2;
	}

	@Override
	public List<Individual<Equation>> getIndividuals(List<Individual<Equation>> parents) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Individual<Equation> getIndividual(Individual<Equation> ind) {
		return ind;
	}
}
