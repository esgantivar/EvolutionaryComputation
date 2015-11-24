package function;

import java.util.List;

public class Rosenbrock implements Function<Double>{

	@Override
	public Double apply(List<Double> x) {
		double s = 0.0;
		for (int i = 0; i < x.size() - 1; i++) {
			s += ((100 * (Math.pow(Math.pow(x.get(i), 2) - x.get(i+1), 2))) + Math.pow(1.0 - x.get(i) , 2));
		}
		return s;
	}

}
