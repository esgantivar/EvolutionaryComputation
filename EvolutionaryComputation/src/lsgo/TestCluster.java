package lsgo;

import java.util.ArrayList;
import java.util.List;

import evolution.AlgorithmDC;
import evolution.individual.RealSpace;
import evolution.individual.Space;
import evolution.operators.AdaptMutationIntensity;
import evolution.operators.GaussianMutation;
import evolution.operators.LinearXOver;
import evolution.operators.OneFifthRule;
import evolution.operators.Operator;
import evolution.operators.UniformMutation;
import evolution.replacements.Generational;
import evolution.replacements.Replacement;
import evolution.selectors.DynamicInbreeding;
import evolution.selectors.Inbreeding;
import evolution.selectors.Roulette;
import evolution.selectors.Selector;
import function.FitnessCentroid;
import function.Function;
import function.M4;
import function.distance.Distance;
import function.distance.Euclidean;

public class TestCluster {

	public static void main(String[] args) {
		
		double min = 0.0;
		double max = 1.0;
		int DIM = 2;
		int nPop = 100;
		int maxIterations = 50;
		Space<Double> space = new RealSpace(min, max, DIM);
		Selector selector = new Roulette();
		AdaptMutationIntensity adapt = new OneFifthRule(100, 0.5);
		Replacement<Double> replacement = new Generational();
		Distance<Double> distance = new Euclidean();
		List<Operator<Double>> operators = new ArrayList<>();
		Inbreeding strategy = new DynamicInbreeding();
		operators.add(new LinearXOver());
		operators.add(new GaussianMutation(0.5, adapt));
		operators.add(new UniformMutation(space, 0.01));
		Function<Double> f = new FitnessCentroid("data-cam-2");

		AlgorithmDC<Double> searchDC = new AlgorithmDC<>(nPop, space, strategy, replacement, operators, maxIterations,
				f, distance);
		searchDC.iterate();
		

	}

}
