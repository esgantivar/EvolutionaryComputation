package evolution.selectors;

import evolution.Population;

public abstract class Inbreeding implements Selector{
	protected Population<?> pop;
	protected int MF;
	
	public void setMF(int MF_){
		MF = MF_;
	}
	@Override
	public void setPopulation(Population pop_) {
		pop = new Population<>(pop_);
	}
}
