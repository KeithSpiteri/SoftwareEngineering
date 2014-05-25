import java.awt.Color;

public class Player implements Observer {
	Position position;

	Position start_pos;

	public boolean visited[][];

	boolean reached_end = false;

	boolean reset = false;

	public Subject subject;

	Map map;

	public Player(Map m) {
		this.map = m;
	}

	boolean move(char direction) {
		if (Character.toUpperCase(direction) == 'U') {
			Position newP = new Position(position.x, position.y - 1);
			if (setPosition(newP)) {
				if (!reset) {
					position = newP;
					this.visited[position.x][position.y + 1] = true;
				} else {
					this.visited[newP.x][newP.y + 1] = true;
					this.visited[newP.x][newP.y] = true;
				}
				reset = false;
			} else {
				System.out
						.println("Movement not Allowed. Choose another direction ");
				return false;
			}
		} else if (Character.toUpperCase(direction) == 'L') {
			Position newP = new Position(position.x - 1, position.y);
			if (setPosition(newP)) {
				if (!reset) {
					position = newP;
					this.visited[position.x + 1][position.y] = true;
				} else {
					this.visited[newP.x + 1][newP.y] = true;
					this.visited[newP.x][newP.y] = true;
				}
				reset = false;
			} else {
				System.out
						.println("Movement not Allowed. Choose another direction ");
				return false;
			}

		} else if (Character.toUpperCase(direction) == 'R') {
			Position newP = new Position(position.x + 1, position.y);
			if (setPosition(newP)) {
				if (!reset) {
					position = newP;
					this.visited[position.x - 1][position.y] = true;
				} else {
					this.visited[newP.x - 1][newP.y] = true;
					this.visited[newP.x][newP.y] = true;
				}

				reset = false;
			} else {
				System.out
						.println("Movement not Allowed. Choose another direction ");
				return false;
			}

		} else if (Character.toUpperCase(direction) == 'D') {
			Position newP = new Position(position.x, position.y + 1);
			if (setPosition(newP)) {
				if (!reset) {
					position = newP;
					this.visited[position.x][position.y - 1] = true;
				} else {
					this.visited[newP.x][newP.y - 1] = true;
					this.visited[newP.x][newP.y] = true;
				}
				reset = false;
			} else {
				System.out
						.println("Movement not Allowed. Choose another direction ");
				return false;
			}

		}

		updateTrail(position);
		return true;
	}

	boolean setPosition(Position p) {
		if (p.x >= Map.grid.length || p.y >= Map.grid.length || p.x < 0
				|| p.y < 0)
			return false;
		else if (Map.grid[p.x][p.y] == Color.BLUE) {
			System.out
					.println("Your just drowned... Bottom line, you have to start from the beginning\n");
			Position newP = new Position(start_pos.x, start_pos.y);
			position = newP;
			reset = true;
			return true;
		} else if (Map.grid[p.x][p.y] == Color.YELLOW) {
			System.out.println("You have reached the promised land !\n");
			reached_end = true;
			return true;
		} else {
			position = p;
			return true;
		}
	}

	Position getPosition() {
		return position;
	}

	public boolean setStartPosition(int length) {
		if (length < 5 || length > 50)
			return false;

		int row = 0;
		int col = 0;
		Color c = null;

		do {
			row = 0 + (int) (Math.random() * length);
			col = 0 + (int) (Math.random() * length);
			c = map.grid[row][col];
		} while (c != Color.GREEN);

		start_pos = new Position(row, col);
		position = new Position(row, col);

		visited = new boolean[length][length];
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++)
				visited[i][j] = false;
		}

		return true;
	}

	public void setFixedStart(int x) {
		start_pos = new Position(x, x);
		position = new Position(x, x);

		visited = new boolean[Map.grid.length][Map.grid.length];
		for (int i = 0; i < Map.grid.length; i++) {
			for (int j = 0; j < Map.grid.length; j++)
				visited[i][j] = false;
		}
	}

	public void updateTrail(Position p)
	{
		visited[p.getX()][p.getY()] = true;
	}
	
	@Override
	public void update() {
		visited = (boolean[][]) subject.getUpdate(this);

	}

	@Override
	public void setSubject(Subject sub) {
		this.subject = sub;

	}

}
