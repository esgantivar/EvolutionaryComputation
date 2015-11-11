package Function;

import java.io.FileNotFoundException;

import util.Bencmarks;

@SuppressWarnings("unused")
public class ShiftedAckleys implements Function {
	private double[] Ovector;
	private int minX;	
	private int maxX;
	private int ID;
	private double[] anotherz;
	private int DIM;

	public ShiftedAckleys(int dimension) {
		Ovector = null;
		minX = -32;
		maxX = 32;
		ID = 3;
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
		return Bencmarks.ackley(anotherz, DIM);
	}
}
