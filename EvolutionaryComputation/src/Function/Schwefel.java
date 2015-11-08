package Function;

import evolution.Gene;

public class Schwefel implements Function{

	public double evaluate(Gene X[]) {
		double res = 418.9829 * X.length;
		for (Gene x : X) {
			res += -(double) x.getValue() * Math.sin(Math.sqrt(Math.abs((double) x.getValue())));
		}
		return res;
	}
	@Override
	public Double apply(double[] x) {
		double res = 418.9829 * x.length;
		for (double t : x) {
			res += - t * Math.sin(Math.sqrt(Math.abs( t)));
		}
		return res;
	}

}
