package evolution.individual;

public class RealGene implements Gene<Double>{
	public Double value;
	
	public RealGene(){
		value = null;
	}
	
	public RealGene(Double value_){
		value = value_;
	}
	public RealGene(Gene<Double> gene){
		value = gene.getValue();
	}

	@Override
	public void setValue(Double value_) {
		value = value_;
	}

	@Override
	public void setValue(Gene<Double> gene) {
		value = gene.getValue();
	}

	@Override
	public Double getValue() {
		return value;
	}
}
