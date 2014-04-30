import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class PositionTester {

	
	Position p;

	@Before
	public void before() {

		p = new Position(0,5);
	} 

	@Test
	public void xyTest() {
		assertEquals(p.x, 0);
	}
	
	@Test
	public void testSetandGet(){
	    int x = 10;
	    int y = 2;
	    Position b = new Position(10, 2);
	    
	    int value = b.getX();
	    assertEquals(value, x);
	    
	    value = b.getY();
	    assertEquals(value, y);

	}
}
