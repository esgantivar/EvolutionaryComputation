package function.distance;

import java.util.List;

import evolution.individual.Individual;

public class Euclidean extends Distance<Double>{

	@Override
	public Double apply(List<Individual<Double>> x) {
		double dis = 0.0;
		for (int i = 0; i < x.get(0).getDimension(); i++) {
			dis += Math.pow(x.get(0).getGene(i)-x.get(1).getGene(i), 2);
		}
		return Math.sqrt(dis);
	}

	
}
