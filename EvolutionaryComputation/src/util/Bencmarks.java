package util;

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
	/*public static double rastrigin(double x[], int dim, int k)
	{
	  double result=0.0;
	  
	  for(int i=dim/k-1;i>=0;i--)
	    {
	      result+=x[Pvector[dim/k+i]]*x[Pvector[dim/k+i]]-10.0*cos(2*PI*x[Pvector[dim/k+i]])+10.0;
	    }
	  return result;
	}*/

	// ackley function for single group non-separable 
	public static double ackley(double x[],int dim){
	  double sum1 = 0.0;
	  double sum2 = 0.0;
	  double sum;
	  int    i;

	  // T_{osz}
	  transform_osz(x,dim);
	  
	  // T_{asy}^{0.2}
	  transform_asy(x, 0.2, dim);

	  // lambda
	  Lambda(x, 10, dim);

	  for(i = dim - 1; i >= 0; i--) {
	    sum1 += (x[i] * x[i]);
	    sum2 += Math.cos(2.0 * Math.PI * x[i]);
	  }

	  sum = -20.0 * Math.exp(-0.2 * Math.sqrt(sum1 / dim)) - Math.exp(sum2 / dim) + 20.0 + Math.E;
	  return sum;
	}

	// ackley function for m-group non-separable 
	/*public static double ackley(double x[], int dim, int k)
	{
	  double sum1=0.0;
	  double sum2=0.0;
	  double result=0.0;
	  int i;

	  for(i=dim/k-1;i>=0;i--)
	    {
	      sum1+=x[Pvector[dim/k+i]]*x[Pvector[dim/k+i]];
	      sum2+=cos(2.0*PI*x[Pvector[dim/k+i]]);
	    }

	  result=-20.0*Math.exp(-0.2*Math.sqrt(sum1/(dim/k)))-Math.exp(sum2/(dim/k))+20.0+Math.E;

	  return(result);
	}*/

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
	
	public static double[] readOvector(int id){
		return null;
	}

}
