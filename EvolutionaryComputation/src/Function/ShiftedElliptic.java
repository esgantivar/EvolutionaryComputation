package Function;

import java.io.FileNotFoundException;
import util.Bencmarks;

@SuppressWarnings("unused")
public class ShiftedElliptic implements Function {

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
	public Double apply(double[] x) {
		if (Ovector == null) {
			try {
				Ovector = Bencmarks.readOvector(ID, DIM);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		for (int i = DIM - 1; i >= 0; i--) {
			anotherz[i] = x[i] - Ovector[i];
		}

		return Bencmarks.elliptic(anotherz, DIM);
	}
}
