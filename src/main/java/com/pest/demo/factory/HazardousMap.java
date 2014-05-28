package main.java.com.pest.demo.factory;

import java.awt.Color;

public class HazardousMap extends Map {

	public int map_type = 2;

	private HazardousMap(int size) {
		super.size = size;
		this.generate();
	}

	public static Map getInstance(int size) {
		if (Map.map == null || !(Map.map instanceof HazardousMap))
			map = new HazardousMap(size);
		return Map.map;
	}

	public void generate() {
		grid = new Color[getSize()][getSize()];

		// set all cells to green by default
		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize(); j++) {
				grid[i][j] = Color.GREEN;
			}
		}

		int total_cells = getSize() * getSize();
		int ten_percent = (int) Math.floor(total_cells / 10);
		int twentyfive_percent = (int) Math.floor(total_cells * 0.25);

		int total_water = twentyfive_percent
				+ (int) (Math.random() * ten_percent);

		for (int i = 0; i < total_water; i++) {
			int x = (int) (Math.random() * getSize());
			int y = (int) (Math.random() * getSize());

			if (!testing) {
				if (grid[x][y] == Color.BLUE) {
					i--;
				} else {
					grid[x][y] = Color.BLUE;
				}
			}
		}

		int ruby_cell_x;
		int ruby_cell_y;

		ruby_cell_x = 0 + (int) (Math.random() * getSize());
		ruby_cell_y = 0 + (int) (Math.random() * getSize());

		grid[ruby_cell_x][ruby_cell_y] = Color.YELLOW;

	}

}
