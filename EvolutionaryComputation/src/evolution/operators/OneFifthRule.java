package evolution.operators;

public class OneFifthRule implements AdaptMutationIntensity {

	protected int G;
	protected double alpha;
	protected int Gcount = 0;
	protected int Gstar = 0;

	public OneFifthRule(int G_, double alpha_) {
		G = G_;
		alpha = alpha_;
	}

	@Override
	public double apply(double sigma, double productivity) {
		
		/*if(productivity < 0){
			sigma = sigma * 1.5;
			System.out.println("Mejoro");
		}else if(productivity > 0){
			sigma = Math.pow(1.5, -1/4) * sigma;
			System.out.println("Empeoro");
		}*/
		if (productivity < 0.0) {
			Gstar++;
		}
		Gcount++;
		if (Gcount == G) {
			double p = (double) Gstar / (double) G;
			if (p < 0.2) {
				sigma /= alpha;
			} else {
				if (p > 0.2) {
					sigma *= alpha;
				}
			}
			Gcount = 0;
			Gstar = 0;
		}
		return sigma;
	}

}
