package util;

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

	// rastrigin function for F9 ~
	/*
	 * public static double rastrigin(double x[], int dim, int k) { double
	 * result=0.0;
	 * 
	 * for(int i=dim/k-1;i>=0;i--) {
	 * result+=x[Pvector[dim/k+i]]*x[Pvector[dim/k+i]]-10.0*cos(2*PI*x[Pvector[
	 * dim/k+i]])+10.0; } return result; }
	 */

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

	// public static double ackley(double x[], int dim, int k) { double
	// sum1=0.0; double sum2=0.0; double result=0.0; int i;
	// int Pvector[] = new int[dim];
	// readPermVector(id, dim);
	// for(i=dim/k-1;i>=0;i--) { sum1+=x[Pvector[dim/k+i]]*x[Pvector[dim/k+i]];
	// sum2+=cos(2.0*PI*x[Pvector[dim/k+i]]); }
	//
	// result=-20.0*Math.exp(-0.2*Math.sqrt(sum1/(dim/k)))-Math.exp(sum2/(dim/k)
	// )+20.0+Math.E;

	// return(result); }

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
			d[counter++] = Integer.valueOf(string);
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

}
