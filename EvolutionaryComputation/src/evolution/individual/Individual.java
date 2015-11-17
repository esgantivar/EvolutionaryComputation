package evolution.individual;

import Function.Function;

public abstract class Individual<T> implements Comparable<Individual<?>>{
	protected Double fitness;
	protected int dimension = 0;
	protected Gene<T> genome[];
	protected double fit;
	protected Function f;
	
	public abstract Double computeFitness();
	public Function getFunction(){
			return f;
	}
	
	public abstract String toString();
	
	public Double getFitness(){
		if(fitness == null){
			return computeFitness();
		}
		return fitness;
	}
	
	public int getDimension(){
		return dimension;
	}
	
	public Gene<T>[] getGenome(){
		return genome;
	}
	
	public Gene<T> getGene(int index){
		if(index >= genome.length){
			return null;
		}
		return genome[index];
	}
	
}
