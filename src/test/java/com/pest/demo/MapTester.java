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
		boolean test = false;
		Game.num_play = 4;
		for(int i=5; i<51; i++)
		{
			test = m.setMapSize(i, i);
			assertEquals(true, test);
		}

		Game.num_play = 8;
		test = m.setMapSize(5, 5);
		assertEquals(false, test);
		
	}
	
	@Test
	public void generate()
	{
		m.size = 5;
		assertEquals(true, m.generate());
	}
	
	

}
