package function.lsgo;

import java.io.FileNotFoundException;
import java.util.List;

import function.Function;

@SuppressWarnings("unused")
public class ShiftedSchwefelsVII implements Function<Double> {

	private double Ovector[];
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
	private double anotherz[];

	public ShiftedSchwefelsVII(int dimension) {
		Ovector = null;
		Pvector = null;
		r25 = null;
		r50 = null;
		r100 = null;
		s = null;
		w = null;
		minX = -100;
		maxX = 100;
		ID = 7;
		s_size = 7;
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
				Pvector = Bencmarks.readPermVector(ID, DIM);
				r25 = Bencmarks.readR(ID, 25);
				r50 = Bencmarks.readR(ID, 50);
				r100 = Bencmarks.readR(ID, 100);
				s = Bencmarks.readS(ID, s_size);
				w = Bencmarks.readW(ID, s_size);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		for (i = 0; i < DIM; i++) {
			anotherz[i] = x.get(i) - Ovector[i];
		}

		// s_size non-separable part with rotation
		int c = 0;
		double anotherz1[];
		for (i = 0; i < s_size; i++) {
			anotherz1 = Bencmarks.rotateVector(i, c, s, Pvector, anotherz, r25, r50, r100);
			result += w[i] * Bencmarks.schwefel(anotherz1, s[i]);
		}

		// one separable part without rotation
		double z[] = new double[DIM - c];
		for (i = c; i < DIM; i++) {
			z[i - c] = anotherz[Pvector[i]];
		}

		result += Bencmarks.sphere(z, DIM - c);
		return result;
	}

}
