package evolution.individual;

public abstract class Space<T> {
	public String type(){
		return "NaN";
	}
	public abstract T[] getBoundaries();
	public abstract T limitLow();
	public abstract T limitHigh();
	public abstract boolean isFeasible(T ind);
	public abstract T next();
}
