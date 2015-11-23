package function.lsgo;

import java.io.FileNotFoundException;
import java.util.List;

import function.Function;
@SuppressWarnings("unused")
public class ShiftedSchwefelsConflictingOverlapping implements Function<Double> {
	private double OvectorVec[][];
	private int Pvector[];
	private double r25[][];
	private double r50[][];
	private double r100[][];
	private int s[];
	private double w[];
	private double minX;
	private double maxX;
	private int ID;
	private int s_size;
	private int DIM;
	private int overlap;

	public ShiftedSchwefelsConflictingOverlapping(int dimension) {
		OvectorVec = null;	
		DIM = 905;
		r25 = null;
		r50 = null;
		r100 = null;
		s = null;
		w = null;
		minX = -100;
		maxX = 100;
		ID = 14;
		s_size = 20;
		DIM = 905;
		overlap = 5;
	}

	@Override
	public Double apply(List<Double> x) {
		int i;
		double result = 0.0;

		if (OvectorVec == null) {
			try {
				Pvector = Bencmarks.readPermVector(ID, DIM);
				r25 = Bencmarks.readR(ID, 25);
				r50 = Bencmarks.readR(ID, 50);
				r100 = Bencmarks.readR(ID, 100);
				s = Bencmarks.readS(ID, s_size);
				w = Bencmarks.readW(ID, s_size);
				OvectorVec = Bencmarks.readOvectorVec(ID,s_size,s);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		// s_size non-separable part with rotation
		int c = 0;
		double anotherz1[];
		double a[] = new double[x.size()];
		for (int j = 0; j < a.length; j++) {
			a[j] = x.get(j);
		}
		for (i = 0; i < s_size; i++) {
			anotherz1 = Bencmarks.rotateVectorConflict(i, c, a, overlap, s, Pvector, OvectorVec, r25, r50, r100);
			result += w[i] * Bencmarks.schwefel(anotherz1, s[i]);
			c = c + s[i];
		}
		return result;
	}
}
