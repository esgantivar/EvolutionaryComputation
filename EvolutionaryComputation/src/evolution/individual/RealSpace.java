package evolution.individual;

public class RealSpace extends Space<Double> {
	private Double bound[];

	public RealSpace(Double min_, Double max_) {
		bound = new Double[2];
		bound[0] = min_;
		bound[1] = max_;
	}

	@Override
	public Double[] getBoundaries() {
		return bound;
	}

	@Override
	public Double limitLow() {
		return bound[0];
	}

	@Override
	public Double limitHigh() {
		return bound[1];
	}

	@Override
	public boolean isFeasible(Double ind) {
		return (ind >= limitLow() && ind <= limitHigh());
	}

	@Override
	public Double next() {
		return (Math.random() * (limitHigh() - limitLow())) + limitLow();
	}

}
