package selectors;

import java.util.Random;
import evolution.Population;

public class Roulette extends Selector{
	
	private double tickets[];
	private Population pop;
	
	public Roulette(){
		name = "Roulette";
	}
	
	public void setPopulation(Population pop_) {
		pop = pop_;
		zscore(pop.fitnessAll());
	}
	public void recalcTickets(){
		zscore(pop.fitnessAll());
	}
	public double[] zscore(double X[]){
		double mean = 0;
		for (int i = 0; i < X.length; i++) {
			X[i] = -X[i];
		}
		for (double x : X) {
			mean += x;
		}
		mean /= X.length;
		double sd = 0;
		for (double x : X) {
			sd += Math.pow(x - mean, 2);
		}
		sd /= X.length;
		sd = Math.sqrt(sd);
		double zscoreValues[] = new double[X.length];
		for (int i = 0; i < zscoreValues.length; i++) {
			zscoreValues[i] = (((X[i] - mean) / sd) + 3);
		}
		double sum = 0;
		for (double zValue : zscoreValues) {
			sum+=zValue;
		}
		for (int i = 0; i < zscoreValues.length; i++) {
			zscoreValues[i]=zscoreValues[i]/sum;
		}
		
		tickets = new double[zscoreValues.length + 1];
		tickets[0]=0;
		for (int i = 1; i < tickets.length; i++) {
			tickets[i] = tickets[i-1]+zscoreValues[i-1];
		}
		return tickets;
	}
	
	public int getTicket(){
		int index = tickets.length -2;
		
		Random rnd = new Random();
		double t = rnd.nextDouble();
		double lowerLim = 0.0;
		for (int i = 1; i < tickets.length; i++) {
			if (t >= lowerLim && t < tickets[i]){
				index = i-1;
				break;
			}else{
				lowerLim = tickets[i];
			}
		}
		return index;
	}

	@Override
	public Population getParents() {
		Population parents = new Population(pop.DIM);
		recalcTickets();
		for (int i = 0; i < pop.nPop(); i++) {
			parents.addIndividual(pop.getIndividual(getTicket()));
		}
		return parents;
	}
}
