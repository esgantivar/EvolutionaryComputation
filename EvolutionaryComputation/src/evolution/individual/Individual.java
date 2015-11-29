package evolution.individual;

import java.util.ArrayList;
import java.util.List;

import function.Function;

public class Individual<T> implements Comparable<Individual<T>>{
	
	protected Double fitness=null;
	protected int dimension = 0;
	protected List<T> genome;
	protected Function<T> function;
	protected Space<T> space;
	protected Double[] rates;
	
	public Individual (int DIM, Function<T> f_, int nOperator) {
		dimension = DIM;
		fitness = null;
		function = f_;
		rates = new Double[nOperator];
		genome = new ArrayList<>(dimension);
		initilializeRates();
	}

	public Individual (int DIM, Function<T> f_, Space<T> space_, int nOperator) {
		dimension = DIM;
		rates = new Double[nOperator];
		fitness = null;
		function = f_;
		space =space_;
		genome = new ArrayList<>(dimension);
		for (int i = 0; i < dimension; i++) {
			genome.add(space.next());
		}
		initilializeRates();
		computeFitness();
	}
	
	public Individual (final Individual<T> ind) {
		dimension = ind.getDimension();
		rates = ind.rates.clone();
		fitness = ind.getFitness();
		function = ind.getFunction();
		space =ind.space;
		genome = new ArrayList<>(dimension);
		for (T t : ind.genome) {
			genome.add(t);
		}
		computeFitness();
	}
	
	public Individual (Function<T> f_, T values[]){
		dimension = values.length;
		function = f_;
		genome = new ArrayList<>(dimension);
		for (T t : values) {
			genome.add(t);
		}
		computeFitness();
	}
	
	public Individual (Function<T> f_, List<T> values){
		dimension = values.size();
		function = f_;
		genome = new ArrayList<>(dimension);
		for (T t : values) {
			genome.add(t);
		}
		computeFitness();
	}
	
	public Double computeFitness(){
		fitness = function.apply(genome);
		return fitness;
	}
	
	public Function<T> getFunction(){
			return function;
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
	
	
	private void initilializeRates(){
		for (int i = 0; i < rates.length; i++) {
			rates[i]=Math.random();
		}
		normalizeRates();
	}
	
	public void normalizeRates(){
		Double total = 0.0;
		for (int i = 0; i < rates.length; i++) {
			total += rates[i];
		}
		
		for (int i = 0; i < rates.length; i++) {
			rates[i] = rates[i]/total;
		}
	}
	
	public Double[] getRates(){
		return rates;
	}
	
	public void setRates(Double rates_[]){
		rates = rates_.clone();
		normalizeRates();
	}
	
	@Override
	public  String toString(){
		StringBuilder sb = new StringBuilder();
		//sb.append("[");
		for (T gene : genome) {
			sb.append(gene.toString()+" ");
		}
		sb.deleteCharAt(sb.lastIndexOf(" "));
		//sb.append("]");
		return sb.toString();
	}
	
	@Override
	public int compareTo(Individual<T> ind) {
		return this.getFitness().compareTo(ind.getFitness());
	}
}
