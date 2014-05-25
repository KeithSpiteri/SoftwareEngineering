import java.util.ArrayList;

public class Team implements Subject {

	private ArrayList<Observer> observers;
	private boolean[][]visited;
	private boolean changed;

	public Team() {
		this.observers = new ArrayList<Observer>();
	}

	public ArrayList<Observer> getPlayers() {
		return observers;
	}

	@Override
	public void register(Observer obs) {
		//if (!observers.contains(obs))
			observers.add(obs);

	}

	@Override
	public void notifyObservers() {
		if (changed) {
			for (Observer obs : observers) {
				obs.update();
			}

			this.changed = false;
		}

	}

	@Override
	public Object getUpdate(Observer obs) {
		return this.visited;
	}

	public void setTeamTrail(boolean[][] vis) {
		this.visited = vis;
		this.changed = true;
		notifyObservers();
	}

	public ArrayList<Observer> getObservers() {
		return this.observers;
	}
}
