package evolution.individual;

public interface Gene<T> {
	public void setValue(T value_);
	public void setValue(Gene<T> gene);
	public T getValue();
	public String toString();
}