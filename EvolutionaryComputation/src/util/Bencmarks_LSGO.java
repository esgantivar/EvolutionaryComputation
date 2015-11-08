package util;

public class Bencmarks_LSGO {

	public static int sign(double x) {
		if (x > 0)
			return -1;
		if (x < 0)
			return 1;
		return 0;
	}

	public static double hat(double x) {
		return (x == 0 ? 0.0 : Math.log10(Math.abs(x)));
	}

	public double c1(double x) {
		return (x > 0 ? 10.0 : 5.5);
	}

	public double c2(double x) {
		return (x > 0 ? 7.9 : 3.1);
	}

	public void transform_osz(double z[], int dim) {
		// apply osz transformation to z
		for (int i = 0; i < dim; ++i) {
			z[i] = sign(z[i])
					* Math.exp(hat(z[i]) + 0.049 * (Math.sin(c1(z[i]) * hat(z[i])) + Math.sin(c2(z[i]) * hat(z[i]))));
		}
	}

	public void transform_asy(double z[], double beta, int dim) {
		for (int i = 0; i < dim; ++i) {
			if (z[i] > 0) {
				z[i] = Math.pow(z[i], 1 + beta * i / ((double) (dim - 1)) * Math.sqrt(z[i]));
			}
		}
	}

}
