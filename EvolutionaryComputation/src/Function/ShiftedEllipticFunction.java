package Function;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ShiftedEllipticFunction implements Function {

	private double xopt[];
	private int DIM;

	public ShiftedEllipticFunction(int DIM_) {
		DIM = 1000;
	}

	private double[] Tosz(double[] x_) {
		double x_i;
		double x_est;
		double c_1;
		double c_2;
		double x[] = new double[DIM];
		try {
			ReadFile("src/data/F1-xopt.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < x_.length; i++) {
			x_i = x_[i] - xopt[i];
			x_est = (x_i != 0 ? Math.log(Math.abs(x_i)) : 0.0);
			c_1 = (x_i > 0 ? 10.0 : 5.5);
			c_2 = (x_i > 0 ? 7.9 : 3.1);
			x[i] = signum(x_i) * Math.exp(x_est + 0.049 * (Math.sin(c_1 * x_est) + Math.sin(c_2 * x_est)));
		}
		return x;
	}

	private double signum(double a) {
		double sig = 0.0;
		if (a > 0) {
			sig = 1.0;
		}
		if (a < 0) {
			sig = -1.0;
		}
		return sig;
	}

	private double evaluate(double[] X_) {
		double Z[] = Tosz(X_);
		double exp;
		double result = 0.0;
		for (int i = 0; i < DIM; i++) {
			exp = 6 * ((i - 1) / (DIM - 1));
			result += (Math.pow(10, exp) * Math.pow(Z[i], 2));
		}
		return result;
	}

	private void ReadFile(String file_path) throws IOException {
		xopt = new double[DIM];
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
	}

	@Override
	public Double apply(double[] x) {
		return evaluate(x);
	}

}
