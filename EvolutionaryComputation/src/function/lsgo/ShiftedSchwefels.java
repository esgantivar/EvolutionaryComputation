package function.lsgo;

import java.io.FileNotFoundException;
import java.util.List;

import function.Function;

@SuppressWarnings("unused")
public class ShiftedSchwefels implements Function<Double> {

	private double[] Ovector;
	private int minX;	
	private int maxX;
	private int ID;
	private double[] anotherz;
	private int DIM;

	public ShiftedSchwefels(int dimension) {
		Ovector = null;
		minX = -100;
		maxX = 100;
		ID = 15;
		DIM = dimension;
		anotherz = new double[dimension];
	}

	@Override
	public Double apply(List<Double> x) {
		int i;
		double result = 0.0;
		if (Ovector == null) {
			try {
				Ovector = Bencmarks.readOvector(ID, DIM);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		for (i = 0; i < DIM; i++) {
			anotherz[i] = x.get(i) - Ovector[i];
		}
		result = Bencmarks.schwefel(anotherz, DIM);
		return result;
	}

}
