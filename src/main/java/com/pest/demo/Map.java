import java.awt.*;

public class Map {

	int size;
	boolean testing = false;

	static Color grid[][];


	boolean setMapSize(int x, int y)
	{
		if(Game.num_play >= 2 && Game.num_play <=4)
		{
			if(x < 5 || x > 50)
				return false;
		}
		else
		{
			if(x < 8 || x > 50)
				return false;
		}
		size =  x;
		return true;
	}

	void generate()
	{
		int chance = 0; //chance determines probability of water tile
		grid = new Color[size][size];

		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
			{
				chance = 0 + (int)(Math.random()*100);
				if(testing)
					chance = 100;

				if(chance < 25)
				{
					grid[i][j] = Color.BLUE;
				}
				else
					grid[i][j] = Color.GREEN;
			}
		}
		int water_cell_x;
		int water_cell_y;

		water_cell_x = 0+ (int)(Math.random()*size); 
		water_cell_y = 0+ (int)(Math.random()*size); 

		grid[water_cell_x][water_cell_y] = Color.YELLOW;

	}



}
