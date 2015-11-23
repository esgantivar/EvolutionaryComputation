package function.lsgo;

import java.util.ArrayList;
import java.util.List;

import unalcol.optimization.OptimizationFunction;

public class FactoryHaEa {
	public static OptimizationFunction<double[]> HaEaCEC2013_LSGO(String function){
		return new OptimizationFunction<double[]>() {
			@Override
			public Double apply(double[] x) {
				List<Double> a = new ArrayList<Double>();
				for (Double d : x) {
					a.add(d);
				}
				return Factory.CEC2013_LSGO(function).apply(a);
			}
		};
	}

}
