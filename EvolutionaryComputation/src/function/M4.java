package function;

import java.util.List;

public class M4 implements Function<Double> {

	@Override
	public Double apply(List<Double> x) {
		double a = Math.pow(x.get(0), 0.75);
		double b = Math.sin(5 * Math.PI * (a - 0.05));
		double c = Math.pow(b, 6);
		double d = -2.0 * (Math.log(2.0)) * Math.pow(((x.get(0) - 0.08) / 0.854), 2);
		return -1.0*Math.exp(d) * c;
	}

}
