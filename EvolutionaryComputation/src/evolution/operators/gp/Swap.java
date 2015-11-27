package evolution.operators.gp;

import java.util.List;

import evolution.individual.Individual;
import evolution.individual.gp.Equation;
import evolution.operators.Operator;

public class Swap extends Operator<Equation>{

	@Override
	public List<Individual<Equation>> getIndividuals(List<Individual<Equation>> parents) {
		return parents;
	}

	@Override
	public Individual<Equation> getIndividual(Individual<Equation> ind) {
		int nEquation = ind.getGenome().size();
		int index1;
		int index2;
		Individual<Equation> newInd;
		Equation e;
		if(nEquation > 1){
			index1 = (int)(Math.random()*nEquation);
			index2 = (int)(Math.random()*(nEquation-2));
			if (index2 >= index1 && index1 < nEquation - 1) {
	            ++index2;
	        }
			e = ind.getGene(index1);
            ind.getGenome().set(index1, ind.getGene(index2));
            ind.getGenome().set(index2, e);
            
            newInd = new Individual<>(ind);
            e = ind.getGenome().get(index1);
            ind.getGenome().set(index1, ind.getGene(index2));
            ind.getGenome().set(index2, e);
			return newInd;
		}else{
			return new Individual<>(ind);
		}
		
	}

}
