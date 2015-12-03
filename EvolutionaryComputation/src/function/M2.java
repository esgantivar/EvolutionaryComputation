package function;

import java.util.List;

public class M2 implements Function<Double>{
	@Override
	public Double apply(List<Double> x) {
		return -1.0*Math.exp(-2.0*(Math.log(2))*Math.pow((x.get(0)-0.1/0.8), 2))*Math.pow(Math.sin(5*Math.PI*x.get(0)), 6);
	}
}
