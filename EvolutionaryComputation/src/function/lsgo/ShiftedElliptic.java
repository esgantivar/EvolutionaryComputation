package function.lsgo;

import java.io.FileNotFoundException;
import java.util.List;

import function.Function;

@SuppressWarnings("unused")
public class ShiftedElliptic implements Function<Double> {

	private double[] Ovector;
	private int minX;
	private int maxX;
	private int ID;
	private int DIM;
	private double[] anotherz;

	public ShiftedElliptic(int dimension) {
		Ovector = null;
		minX = -100;
		maxX = 100;
		ID = 1;
		DIM = dimension;
		anotherz = new double[DIM];
	}

	@Override
	public Double apply(List<Double> x) {
		if (Ovector == null) {
			try {
				Ovector = Bencmarks.readOvector(ID, DIM);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		for (int i = DIM - 1; i >= 0; i--) {
			anotherz[i] = x.get(i) - Ovector[i];
		}

		return Bencmarks.elliptic(anotherz, DIM);
	}
}
