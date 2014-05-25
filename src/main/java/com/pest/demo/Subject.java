
public interface Subject {

	public void register(Observer obs);
	
	public void notifyObservers();
	
	public Object getUpdate(Observer obs);
}
