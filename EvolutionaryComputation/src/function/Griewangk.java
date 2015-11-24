package function;

import java.util.List;

public class Griewangk implements Function<Double> {

	@Override
	public Double apply(List<Double> x) {
		double sum = 0.0;
		double pro = 1.0;
		for (Double t : x) {
			sum += Math.pow(t, 2);
		}
		sum /= 4000;
		for (int i = 0; i < x.size(); i++) {
			pro *= (Math.cos(x.get(i) / Math.sqrt(i+1)));
		}
		return (1 + sum - pro);
	}

}
