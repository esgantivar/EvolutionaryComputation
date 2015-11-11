package Function;

import util.Bencmarks;

public class ShiftedAckleysFunction implements Function {
	private double[] Ovector;
	private int minX;
	private int maxX;
	private int ID;
	private double[] anotherz;
	private int DIM;

	public ShiftedAckleysFunction(int dimension) {
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
			Ovector = Bencmarks.readOvector(ID);
		}
		for (int i = DIM - 1; i >= 0; i--) {
			anotherz[i] = x[i] - Ovector[i];
		}
		return Bencmarks.ackley(anotherz, DIM);
	}
}
