package evolution.operators;

public abstract class GeneticOperator<O,I> {
	public int arity ;
	protected double rate = 0.0;
	public void setRate(double rate_){
		rate = rate_;
	}
	public double getRate(){
		return rate;
	}
	
	public abstract O[] getIndividuals(I parents[]);
	public abstract O getIndividual(I ind);
}
