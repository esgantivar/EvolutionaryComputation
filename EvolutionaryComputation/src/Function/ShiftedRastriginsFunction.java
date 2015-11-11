package Function;

import util.Bencmarks;

public class ShiftedRastriginsFunction implements Function {
	private double Ovector[];
	private int minX;
	private int maxX;
	private int ID;
	private double[] anotherz;
	private int DIM;

	public ShiftedRastriginsFunction(int dimension) {
		minX = -5;
		maxX = 5;
		ID = 2;
		DIM = dimension;
		Ovector = null;
		anotherz = new double[DIM];
	}

	@Override
	public Double apply(double[] x) {
		if (Ovector == null) {
			Ovector = Bencmarks.readOvector(ID);
		}

		for (int i = 0; i < DIM; i++) {
			anotherz[i] = x[i] - Ovector[i];
		}
		return Bencmarks.rastrigin(anotherz, DIM);
	}

}
