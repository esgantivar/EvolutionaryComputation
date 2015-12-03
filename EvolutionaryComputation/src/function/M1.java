package function;

import java.util.List;

public class M1 implements Function<Double>{
	@Override
	public Double apply(List<Double> x) {
		return -1.0*Math.pow(Math.sin(5*Math.PI*x.get(0)), 6);
	}
}
