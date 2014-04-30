import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;


public class PlayTest {

	Player p;
	Map m;
	Game g;
	
	@Before
	public void setup()
	{
		p = new Player();
		m = new Map();
		Game.num_play = 4;
		g = new Game();
		g.setNumPlayers(4);
		m.testing = true;
		m.size = 5;
		m.setMapSize(5, 5);
		m.generate();
	}
	
	@Test
	public void startTest()
	{
		p.setStartPosition(5);
	}
	
	@Test
	public void setPos()
	{
		Position pos = new Position(0,0);
		Map.grid[0][0] = Color.YELLOW;
		p.setPosition(pos);
	}
	
	@Test
	public void moveTest()
	{
		p.setFixedStart(2);
		//p.start_pos = new Position(2,2);
		p.reset =false;
		p.move('U');
		p.reset =true;
		p.move('U');
		Position newP = new Position(0,0);
		p.position = newP;
		p.move('U');

		
		//p.setStartPosition(5);
		p.setFixedStart(2);
		//p.start_pos = new Position(2,2);
		p.reset =false;
		p.move('L');
		p.reset =true;
		p.move('L');
		newP = new Position(0,0);
		p.position = newP;
		p.move('L');
		
		//p.setStartPosition(5);
		p.setFixedStart(2);
		//p.start_pos = new Position(2,2);
		p.reset =false;
		p.move('R');
		p.reset =true;
		p.move('R');
		newP = new Position(4,0);
		p.position = newP;
		p.move('R');
		
		//p.setStartPosition(5);
		p.setFixedStart(2);
		//p.start_pos = new Position(2,2);
		p.reset =false;
		p.move('D');
		p.reset =true;
		p.move('D');
		newP = new Position(0,4);
		p.position = newP;
		p.move('D');
	}
	
	@Test
	public void getPosTest()
	{
		Position newP = new Position(0,0);
		p.position = newP;
		
		assertEquals(newP, p.getPosition());
	}

}
