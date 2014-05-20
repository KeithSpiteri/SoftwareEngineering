import java.awt.Color;

public class HazardousMap extends Map {

	public int map_type = 2;

	public HazardousMap(int size) {
		System.out.println("constructing a hazardous map");
		super.size = size;
		this.generate();
	}

	/*
	 * public static Map getInstance(int size) { return new HazardousMap(); }
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
		int ten_percent = (int) Math.floor(total_cells / 10);
		int twentyfive_percent = (int) Math.floor(total_cells * 0.25);

		int total_water = twentyfive_percent
				+ (int) (Math.random() * ten_percent);

		for (int i = 0; i < total_water; i++) {
			int x = (int) (Math.random() * size);
			int y = (int) (Math.random() * size);

			if (grid[x][y] == Color.BLUE) {
				i--;
			} else {
				grid[x][y] = Color.BLUE;
			}
		}

		int ruby_cell_x;
		int ruby_cell_y;

		ruby_cell_x = 0 + (int) (Math.random() * size);
		ruby_cell_y = 0 + (int) (Math.random() * size);

		grid[ruby_cell_x][ruby_cell_y] = Color.YELLOW;

	}

}
