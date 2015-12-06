package function;

import java.util.ArrayList;
import java.util.List;

public class MultiObjetiveFunction {
	private List<Function<Double>> functions;

	public MultiObjetiveFunction(List<Function<Double>> functions_) {
		functions = functions_;
	}

	public Boolean isDominating(List<Double> X, List<Double> Y) {
		List<Double> funX = new ArrayList<>(functions.size());
		List<Double> funY = new ArrayList<>(functions.size());
		for (Function<Double> f : functions) {
			funX.add(f.apply(X));
			funY.add(f.apply(Y));
		}
		boolean geq[] = new boolean[functions.size()];
		boolean great[] = new boolean[functions.size()];
		for (int i = 0; i < geq.length; i++) {
			geq[i] = funX.get(i) <= funY.get(i) ? true : false;
			great[i] = funX.get(i) < funY.get(i) ? true : false;
		}
		boolean a = geq[0];
		boolean b = great[0];
		for (int i = 1; i < geq.length; i++) {
			a = a && geq[i];
			b = b || great[i];
		}
		return a && b;
	}
}
