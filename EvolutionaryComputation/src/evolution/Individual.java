package evolution;

import Function.Function;

public abstract class Individual implements Comparable<Individual>{
	public static String type;
	protected int dimension = 0;
	protected Gene genome[];
	protected double fit;
	public abstract double fitness(Function f);
	public abstract double getFitness();
	public abstract Function getF();
	
	public int getDimension(){
		return dimension;
	}
	public Gene[] getGenome(){
		return genome;
	}
	
	public Gene getGene(int index){
		return genome[index];
	}
	public abstract String toString();
	//public abstract ArrayList<Individual> getIndividuals();
}
