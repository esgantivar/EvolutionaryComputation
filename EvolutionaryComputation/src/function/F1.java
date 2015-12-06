package function;

import java.util.List;

public class F1 implements Function<Double>{

	@Override
	public Double apply(List<Double> x) {
		return Math.pow(x.get(0), 2.0);
	}

}
