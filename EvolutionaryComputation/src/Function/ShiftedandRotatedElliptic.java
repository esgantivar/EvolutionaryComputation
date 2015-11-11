package Function;

import java.io.FileNotFoundException;

import util.Bencmarks;

@SuppressWarnings("unused")
public class ShiftedandRotatedElliptic implements Function {

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

	public ShiftedandRotatedElliptic(int dimension) {
		Ovector = null;
		Pvector = null;
		r25 = null;
		r50 = null;
		r100 = null;
		s = null;
		w = null;
		minX = -100;
		maxX = 100;
		ID = 4;
		s_size = 7;
		DIM = dimension;
		anotherz = new double[dimension];
	}

	@Override
	public Double apply(double[] x) {
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

		for (int i = 0; i < DIM; i++) {
			anotherz[i] = x[i] - Ovector[i];
		}
		double anotherz1[];
		int c = 0;
		
		for (int i = 0; i < s_size; i++) {
			anotherz1 = Bencmarks.rotateVector(i, c, s, Pvector, anotherz, r25, r50, r100);
			c = c + s[i];
			result += w[i] * Bencmarks.elliptic(anotherz1, s[i]);
		}
		
		double z[] = new double[DIM - c];
		
		for (int i = c ; i < DIM; i++) {
			z[i - c] = anotherz[Pvector[i]];
		}
		result += Bencmarks.elliptic(z, DIM - c);
		return result;
	}

}
