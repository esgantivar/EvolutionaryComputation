package evolution;

public class RealGene extends Gene{
	public double value;
	
	public RealGene(double value_){
		value = value_;
	}
	
	public RealGene(Gene real){
		value = ((RealGene)real).value;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public void setValue(Object value_) {
		value = (double)value_;
	}
	
	public String toString(){
		return String.valueOf(value);
	}
}
