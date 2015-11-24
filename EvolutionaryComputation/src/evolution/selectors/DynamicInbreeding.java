package evolution.selectors;

import java.util.ArrayList;
import java.util.List;

import evolution.Population;
import evolution.individual.Individual;

@SuppressWarnings("unchecked")
public class DynamicInbreeding extends Inbreeding{
	
	@SuppressWarnings("rawtypes")
	@Override
	public Population<?> getParents() {
		List<Integer>indexs = new ArrayList<>();
		int idx;
		while(indexs.size() < MF){
			idx = (int)(Math.random()*pop.popSize());
			if(!indexs.contains(idx)){
				indexs.add(idx);
			}
		}
				
		List<Individual<?>> inds = new ArrayList<>(MF);
		for (int i = 0; i < indexs.size(); i++) {
			inds.add(pop.getIndividual(indexs.get(i)));
		}
		
		Population p = new Population(inds, pop.getF()); 
		return p;
	}
}
