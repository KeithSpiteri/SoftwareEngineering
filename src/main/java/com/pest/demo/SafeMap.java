import java.awt.Color;

public class SafeMap extends Map {

	public int map_type = 1;

	// Color grid[][];

	public SafeMap(int size) {
		super.size = size;
		this.generate();
	}

	/*
	 * public static Map getInstance() { return new SafeMap(); }
	 */

	public void generate() {
		grid = new Color[size][size];

		// set all cells to green by default
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				grid[i][j] = Color.GREEN;
			}
		}

		int total_cells = size * size;
		int ten_percent = (int) (Math.floor(total_cells / 10));

		int total_water = (int) (Math.random() * ten_percent);

		for (int i = 0; i < total_water; i++) {
			int x = (int) (Math.random() * size);
			int y = (int) (Math.random() * size);

			grid[x][y] = Color.BLUE;
		}

		int ruby_cell_x;
		int ruby_cell_y;

		ruby_cell_x = (int) (Math.random() * size);
		ruby_cell_y = (int) (Math.random() * size);

		grid[ruby_cell_x][ruby_cell_y] = Color.YELLOW;

	}

}
