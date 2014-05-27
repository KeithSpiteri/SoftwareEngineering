import java.util.ArrayList;

public class Team implements Subject {

	private ArrayList<Observer> observers;
	private boolean[][] visited;
	private boolean moved;

	public Team() {
		this.observers = new ArrayList<Observer>();
	}

	@Override
	public void register(Observer obs) {
		observers.add(obs);
		notifyObservers();
	}

	@Override
	public void notifyObservers() {
		if (moved) {
			for (Observer obs : observers) {
				obs.update();
			}

			this.moved = false;
		}

	}

	@Override
	public Object getUpdate(Observer obs) {
		return this.visited;
	}

	public void setTeamVisited(boolean[][] vis) {
		this.visited = vis;
		this.moved = true;
		notifyObservers();
	}
}
