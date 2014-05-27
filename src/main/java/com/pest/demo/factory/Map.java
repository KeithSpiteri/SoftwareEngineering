package main.java.com.pest.demo.factory;

import java.awt.Color;

public abstract class Map {

	public int map_type = 0;
	public int size;

	protected static Map map = null;

	protected boolean testing = false;

	public static Color grid[][];

	abstract void generate();

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean isTesting() {
		return testing;
	}

	public void setTesting(boolean testing) {
		this.testing = testing;
	}
}
