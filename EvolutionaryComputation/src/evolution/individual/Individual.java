package evolution.individual;

import java.util.ArrayList;
import java.util.List;

import Function.Function;

public class Individual<T> implements Comparable<Individual<T>>{
	
	protected Double fitness;
	protected int dimension = 0;
	protected List<T> genome;
	protected double fit;
	protected Function<T> f;
	protected Space<T> space;
	
	public double max;
	public double min;
	
	public Individual (int DIM, Function<T> f_) {
		dimension = DIM;
		fitness = null;
		f = f_;
		genome = new ArrayList<>(dimension);
	}

	public Individual (int DIM, Function<T> f_, Space<T> space_) {
		dimension = DIM;
		fitness = null;
		f = f_;
		space =space_;
		genome = new ArrayList<>(dimension);
		for (int i = 0; i < dimension; i++) {
			genome.add(space.next());
		}
	}
	
	public Individual (Individual<T> ind) {
		dimension = ind.getDimension();
		fitness = ind.getFitness();
		f = ind.getFunction();
		space =ind.space;
		genome = new ArrayList<>(dimension);
		for (T t : ind.genome) {
			genome.add(t);
		}
	}
	
	public Individual (Function<T> f_, T values[]){
		dimension = values.length;
		f = f_;
		genome = new ArrayList<>(dimension);
		for (T t : values) {
			genome.add(t);
		}
	}
	
	public Double computeFitness(){
		fitness = f.apply(genome);
		return fitness;
	}
	public Function<T> getFunction(){
			return f;
	}
	
	
	public Double getFitness(){
		if(fitness == null){
			return computeFitness();
		}
		return fitness;
	}
	
	public int getDimension(){
		return dimension;
	}
	
	public List<T> getGenome(){
		return genome;
	}
	
	public T getGene(int index){
		if(index >= genome.size()){
			return null;
		}
		return genome.get(index);
	}
	
	public void setGene(int index,T value){
		genome.set(index, value);
	}
	
	@Override
	public  String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (T gene : genome) {
			sb.append(gene.toString()+", ");
		}
		sb.deleteCharAt(sb.lastIndexOf(","));
		sb.append("]");
		return sb.toString();
	}
	
	@Override
	public int compareTo(Individual<T> ind) {
		return fitness.compareTo(ind.getFitness());
	}
	
}
