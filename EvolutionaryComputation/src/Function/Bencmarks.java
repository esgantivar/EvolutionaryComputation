package Function;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Bencmarks {

	public static double elliptic(double x[], int dim) {
		double result = 0.0;
		int i;
		x = transform_osz(x, dim);

		for (i = 0; i < dim; i++) {
			result += Math.pow(1.0e6, i / ((double) (dim - 1))) * x[i] * x[i];
		}

		return result;
	}

	// rastrigin function for F1~F8
	public static double rastrigin(double x[], int dim) {
		double sum = 0;
		// T_{osz}
		x = transform_osz(x, dim);
		// T_{asy}^{0.2}
		x = transform_asy(x, 0.2, dim);
		// lambda
		x = Lambda(x, 10, dim);

		for (int i = dim - 1; i >= 0; i--) {
			sum += x[i] * x[i] - 10.0 * Math.cos(2 * Math.PI * x[i]) + 10.0;
		}

		return sum;
	}

	// ackley function for single group non-separable
	public static double ackley(double x[], int dim) {
		double sum1 = 0.0;
		double sum2 = 0.0;
		double sum;
		int i;

		// T_{osz}
		transform_osz(x, dim);

		// T_{asy}^{0.2}
		transform_asy(x, 0.2, dim);

		// lambda
		Lambda(x, 10, dim);

		for (i = dim - 1; i >= 0; i--) {
			sum1 += (x[i] * x[i]);
			sum2 += Math.cos(2.0 * Math.PI * x[i]);
		}

		sum = -20.0 * Math.exp(-0.2 * Math.sqrt(sum1 / dim)) - Math.exp(sum2 / dim) + 20.0 + Math.E;
		return sum;
	}

	// ackley function for m-group non-separable
	double ackley(double x[], int dim, int k, int Pvector[]) {
		double sum1 = 0.0;
		double sum2 = 0.0;
		double result = 0.0;
		int i;
		for (i = dim / k - 1; i >= 0; i--) {
			sum1 += x[Pvector[dim / k + i]] * x[Pvector[dim / k + i]];
			sum2 += Math.cos(2.0 * Math.PI * x[Pvector[dim / k + i]]);
		}
		result = -20.0 * Math.exp(-0.2 * Math.sqrt(sum1 / (dim / k))) - Math.exp(sum2 / (dim / k)) + 20.0 + Math.E;
		return (result);
	}

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

	public static double c1(double x) {
		return (x > 0 ? 10.0 : 5.5);
	}

	public static double c2(double x) {
		return (x > 0 ? 7.9 : 3.1);
	}

	public static double[] transform_osz(double z[], int dim) {
		// apply osz transformation to z
		for (int i = 0; i < dim; ++i) {
			z[i] = sign(z[i])
					* Math.exp(hat(z[i]) + 0.049 * (Math.sin(c1(z[i]) * hat(z[i])) + Math.sin(c2(z[i]) * hat(z[i]))));
		}
		return z;
	}

	public static double[] transform_asy(double z[], double beta, int dim) {
		for (int i = 0; i < dim; ++i) {
			if (z[i] > 0) {
				z[i] = Math.pow(z[i], 1 + beta * i / ((double) (dim - 1)) * Math.sqrt(z[i]));
			}
		}
		return z;
	}

	public static double[] Lambda(double z[], double alpha, int dim) {
		for (int i = 0; i < dim; ++i) {
			z[i] = z[i] * Math.pow(alpha, 0.5 * i / ((double) (dim - 1)));
		}
		return z;
	}

	public static double[] readOvector(int id, int dim) throws FileNotFoundException {
		double xopt[] = new double[1000];
		String file_path = "cdatafiles/F" + id + "-xopt.txt";
		FileReader fr = new FileReader(file_path);
		BufferedReader textReader = new BufferedReader(fr);
		String sCurrentLine;
		int counter = 0;
		try {
			while ((sCurrentLine = textReader.readLine()) != null) {
				xopt[counter++] = Double.valueOf(sCurrentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (textReader != null)
					textReader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return xopt;
	}

	public static int[] readPermVector(int id, int dim) throws FileNotFoundException {
		int d[] = new int[dim];
		String file_path = "cdatafiles/F" + id + "-p.txt";
		FileReader fr = new FileReader(file_path);
		BufferedReader textReader = new BufferedReader(fr);
		String sCurrentLine;
		ArrayList<String> ss = new ArrayList<>();

		try {
			while ((sCurrentLine = textReader.readLine()) != null) {
				ss.add(sCurrentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (textReader != null)
					textReader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		String p[] = ss.get(0).split(",");
		int counter = 0;
		for (String string : p) {
			d[counter++] = Integer.valueOf(string) - 1;
		}
		return d;
	}

	public static double[][] readR(int id, int sub_dim) throws FileNotFoundException {
		double m[][];
		m = new double[sub_dim][sub_dim];
		String s[] = new String[sub_dim];
		String file_path = "cdatafiles/F" + id + "-R" + sub_dim + ".txt";
		FileReader fr = new FileReader(file_path);
		BufferedReader textReader = new BufferedReader(fr);
		String sCurrentLine;
		int counter = 0;
		try {
			while ((sCurrentLine = textReader.readLine()) != null) {
				s[counter++] = sCurrentLine;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (textReader != null)
					textReader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		for (int i = 0; i < s.length; i++) {
			String ss[] = s[i].split(",");
			for (int j = 0; j < ss.length; j++) {
				m[i][j] = Double.valueOf(ss[j]);
			}
		}
		return m;
	}

	public static int[] readS(int id, int num) throws FileNotFoundException {
		int s[] = new int[num];
		String file_path = "cdatafiles/F" + id + "-s.txt";
		FileReader fr = new FileReader(file_path);
		BufferedReader textReader = new BufferedReader(fr);
		String sCurrentLine;
		int counter = 0;
		try {
			while ((sCurrentLine = textReader.readLine()) != null) {
				s[counter++] = Integer.valueOf(sCurrentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (textReader != null)
					textReader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return s;
	}

	public static double[] readW(int id, int num) throws FileNotFoundException {
		double w[] = new double[num];
		String file_path = "cdatafiles/" + "F" + id + "-w.txt";
		FileReader fr = new FileReader(file_path);
		BufferedReader textReader = new BufferedReader(fr);
		String sCurrentLine;
		int counter = 0;
		try {
			while ((sCurrentLine = textReader.readLine()) != null) {
				w[counter++] = Double.valueOf(sCurrentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (textReader != null)
					textReader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return w;
	}

	public static double[] rotateVector(int i, int c, int s[], int Pvector[], double anotherz[], double r25[][],
			double r50[][], double r100[][]) {
		double z[] = new double[s[i]];
		double anotherz1[] = new double[anotherz.length];

		for (int j = c; j < c + s[i]; ++j) {
			z[j - c] = anotherz[Pvector[j]];
		}

		if (s[i] == 25) {
			anotherz1 = multiply(z, r25, s[i]);
		} else if (s[i] == 50) {
			anotherz1 = multiply(z, r50, s[i]);
		} else if (s[i] == 100) {
			anotherz1 = multiply(z, r100, s[i]);
		} else {
			System.out.println("size of rotation matrix out of range");
		}
		return anotherz1;
	}

	public static double[] multiply(double vector[], double matrix[], int dim) {
		int i, j;
		double result[] = new double[dim];

		for (i = dim - 1; i >= 0; i--) {
			result[i] = 0;

			for (j = dim - 1; j >= 0; j--) {
				result[i] += vector[j] * matrix[dim * j + i];
			}
		}
		return result;
	}

	public static double[] multiply(double vector[], double matrix[][], int dim) {
		double result[] = new double[dim];
		for (int i = dim - 1; i >= 0; i--) {
			result[i] = 0;
			for (int j = dim - 1; j >= 0; j--) {
				result[i] += vector[j] * matrix[i][j];
			}
		}
		return result;
	}

	// for single group non-separable function
	public static double schwefel(double[] x, int dim) {
		int j;
		double s1 = 0;
		double s2 = 0;
		x = transform_osz(x, dim);
		x = transform_asy(x, 0.2, dim);
		for (j = 0; j < dim; j++) {
			s1 += x[j];
			s2 += (s1 * s1);
		}
		return s2;
	}

	// for m groups non-separable function
	double schwefel(double x[], int dim, int k, int Pvector[]) {
		double sum1 = 0.0;
		double sum2 = 0.0;
		int i;
		for (i = 0; i < dim; i++) {
			sum1 += x[Pvector[(k - 1) * dim + i]];
			sum2 += sum1 * sum1;
		}
		return sum2;
	}

	// for single group non-separable function
	public static double sphere(double[] x, int dim) {
		double sum = 0;
		int i;
		for (i = dim - 1; i >= 0; i--) {
			sum += Math.pow(x[i], 2);
		}
		return sum;
	}

	// for m groups non-separable function
	double sphere(double x[], int dim, int k, int Pvector[]) {
		double result = 0.0;
		int i;
		for (i = dim / k - 1; i >= 0; i--) {
			result += x[Pvector[dim / k + i]] * x[Pvector[dim / k + i]];
		}

		return (result);
	}

	// single group non-separable function
	public static double rosenbrock(double[] x, int dim) {
		double s = 0.0;
		for (int i = 0; i < dim - 1; i++) {
			s += ((100 * (Math.pow(Math.pow(x[i], 2) - x[i + 1], 2))) + Math.pow(x[i] - 1, 2));
		}
		return s;
	}

	// m groups non-separable function
	public static double rosenbrock(double x[], int dim, int k, int Pvector[]) {
		int j;
		double oz, t;
		double result = 0.0;
		j = dim - 1;
		for (--j; j >= 0; j--) {
			oz = x[Pvector[(k - 1) * dim + j + 1]];
			t = ((x[Pvector[(k - 1) * dim + j]] * x[Pvector[(k - 1) * dim + j]]) - oz);
			result += (100.0 * t * t);
			t = (x[Pvector[(k - 1) * dim + j]] - 1.0);
			result += (t * t);
		}
		return result;
	}

	public static double[][] readOvectorVec(int ID, int s_size, int s[]) throws FileNotFoundException {
		double d[][] = new double[s_size][];
		String file_path = "cdatafiles/" + "F" + ID + "-xopt.txt";
		FileReader fr = new FileReader(file_path);
		BufferedReader textReader = new BufferedReader(fr);
		String sCurrentLine;
		int c = 0; // index over 1 to dim
		int i = -1; // index over 1 to s_size
		int up = 0; // current upper bound for one group
		try {
			while ((sCurrentLine = textReader.readLine()) != null) {
				if (c == up) {
					i++;
					d[i] = new double[s[i]];
					up += s[i];
				}
				d[i][c - (up - s[i])] = Double.valueOf(sCurrentLine);
				c++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (textReader != null)
					textReader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return d;
	}

	public static double[] rotateVectorConflict(int i, int c, double x[], int overlap, int s[], int Pvector[],
			double OvectorVec[][], double r25[][], double r50[][], double r100[][]) {
		double z[] = new double[s[i]];
		double anotherz1[] = null;

		for (int j = c - i * overlap; j < c + s[i] - i * overlap; ++j) {
			z[j - (c - i * overlap)] = x[Pvector[j]] - OvectorVec[i][j - (c - i * overlap)];
		}

		if (s[i] == 25) {
			anotherz1 = multiply(z, r25, s[i]);
		} else if (s[i] == 50) {
			anotherz1 = multiply(z, r50, s[i]);
		} else if (s[i] == 100) {
			anotherz1 = multiply(z, r100, s[i]);
		} else {
			System.out.println("out of range");
		}

		return anotherz1;
	}
}
