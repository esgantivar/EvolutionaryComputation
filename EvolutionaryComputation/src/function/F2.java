package function;

import java.util.List;

public class F2 implements Function<Double>{

	@Override
	public Double apply(List<Double> x) {
		return Math.pow(x.get(0) - 2, 2.0);
	}

}
