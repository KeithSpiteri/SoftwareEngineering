import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class MapTester {

	Map m;
	@Before
	public void before() {

		m = new Map();

	} 



	@Test
	public void setMapSize() 
	{
		Game.num_play = 4;
		for(int i=4; i<51; i++)
		{
			m.setMapSize(i, i);
		}

		Game.num_play = 6;
		for(int i=4; i<51; i++)
		{
			m.setMapSize(i, i);
		}

		Game.num_play = 8;
		for(int i=4; i<51; i++)
		{
			m.setMapSize(i, i);
		}

		Game.num_play = 9;
		for(int i=4; i<51; i++)
		{
			m.setMapSize(i, i);
		}
	}
	
	@Test
	public void generate()
	{
		m.size = 5;
		m.generate();
	}
	
	

}
