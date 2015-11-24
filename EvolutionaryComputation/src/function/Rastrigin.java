package function;

import java.util.List;

public class Rastrigin implements Function<Double> {

	@Override
	public Double apply(List<Double> x) {
		double s = x.size() * 10;
		for (Double t : x) {
			s += (Math.pow(t, 2) - 10 * Math.cos(2 * Math.PI * t));
		}
		return s;
	}

}
