package FunctionLSGO;

import Function.*;
import unalcol.optimization.OptimizationFunction;

public class ShiftedEllipticFunction extends OptimizationFunction<double[]> {
	private int DIM ;
	private ShiftedElliptic f; 
	
	public ShiftedEllipticFunction(int DIM_){
		DIM = DIM_;
		f = new ShiftedElliptic(DIM);
	}

	@Override
	public Double apply(double[] x) {
		return f.apply(x);
	}

}
