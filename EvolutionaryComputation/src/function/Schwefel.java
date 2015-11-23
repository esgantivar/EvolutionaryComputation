package function;

import java.util.List;

public class Schwefel implements Function<Double>{
	@Override
	public Double apply(List<Double> x) {
		double res = 418.9829 * x.size();
		for (double t : x) {
			res += - t * Math.sin(Math.sqrt(Math.abs( t)));
		}
		return res;
	}
}
