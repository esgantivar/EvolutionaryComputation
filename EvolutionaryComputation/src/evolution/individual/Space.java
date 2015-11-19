package evolution.individual;

public abstract class Space<T> {
	protected int DIM=0;
	public String type(){
		return "NaN";
	}
	public  int getDimension(){
		return DIM;
	}
	public abstract T[] getBoundaries();
	public abstract T limitLow();
	public abstract T limitHigh();
	public abstract boolean isFeasible(T ind);
	public abstract T next();
}
