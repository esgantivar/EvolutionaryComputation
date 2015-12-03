package function;

import java.util.List;

public class M3 implements Function<Double> {
	@Override
	public Double apply(List<Double> x) {
		double a = 5.0 * Math.PI * (Math.pow(x.get(0), 0.75) - 0.05);
		return -1.0*Math.pow(Math.sin(a), 6);
	}
}
