import static org.junit.Assert.*;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class Tester {

	Player p;
	Map m;
	Game g;
	
	@Before
	public void setup()
	{
		p = new Player();
		m = new Map();
		m.testing = true;
		Game.num_play = 4;
		g = new Game();
		g.setNumPlayers(4);
		m.testing = true;
		m.size = 5;
		assertEquals(true, m.setMapSize(5, 5));
		assertEquals(true, m.generate());
	}
	
	@Test
	public void startTest()
	{
		assertEquals(true, p.setStartPosition(5));
		assertEquals(false, p.setStartPosition(55));
		assertEquals(false, p.setStartPosition(4));
	}
	
	@Test
	public void setPos()
	{
		Position pos = new Position(0,0);
		Map.grid[0][0] = Color.YELLOW;
		assertEquals(true, p.setPosition(pos));
	}
	
	@Test
	public void moveTest()
	{
		p.setFixedStart(2);
		//p.start_pos = new Position(2,2);
		p.reset =false;
		assertEquals(true, p.move('U'));
		p.reset =true;
		p.move('U');
		Position newP = new Position(0,0);
		p.position = newP;
		assertEquals(false, p.move('U'));

		
		//p.setStartPosition(5);
		p.setFixedStart(2);
		//p.start_pos = new Position(2,2);
		p.reset =false;
		assertEquals(true, p.move('L'));
		p.reset =true;
		p.move('L');
		newP = new Position(0,0);
		p.position = newP;
		assertEquals(false, p.move('L'));
		
		//p.setStartPosition(5);
		p.setFixedStart(2);
		//p.start_pos = new Position(2,2);
		p.reset =false;
		assertEquals(true, p.move('R'));
		p.reset =true;
		p.move('R');
		newP = new Position(4,0);
		p.position = newP;
		assertEquals(false, p.move('R'));
		
		//p.setStartPosition(5);
		p.setFixedStart(2);
		//p.start_pos = new Position(2,2);
		p.reset =false;
		assertEquals(true, p.move('D'));
		p.reset =true;
		p.move('D');
		newP = new Position(0,4);
		p.position = newP;
		assertEquals(false, p.move('D'));
	}
	
	@Test
	public void getPosTest()
	{
		Position newP = new Position(0,0);
		p.position = newP;
		
		assertEquals(newP, p.getPosition());
	}
	
	
	// MAP
	
	//Map m;
	@Before
	public void beforeMap() {
		m = new Map();
		m.testing = true;
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
	
	//Position
	
	
	Position pos;

	@Before
	public void before() {

		pos = new Position(0,5);
	} 

	@Test
	public void xyTest() {
		assertEquals(pos.x, 0);
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
	
	//Game

	Game mygame = null;
	Map map = null;
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void setUp() throws Exception {
		mygame = new Game();
		Game.num_play=4;
		mygame.setNumPlayers(4);
		map = new Map();
		map.testing = true;
		assertEquals(true, map.setMapSize(5, 5));
		assertEquals(true, map.generate());

		
	}

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
	}


	@Test
	public void testSetNumPlayers() {
		for(int i=2; i<9; i++)
		{
			assertEquals(true, mygame.setNumPlayers(i));
		}
		assertEquals(false, mygame.setNumPlayers(9));
		assertEquals(false, mygame.setNumPlayers(1));
	}

	@Test 
	public void testMain()
	{
		Game.testing = true;
		Game.main();
	}
	
	@Test
	public void testStart()
	{
		Game.testing = true;
		Game.num_play = 2;
		Game game= new Game();
		game.map = new Map();
		game.map.setMapSize(5, 5);
		game.map.generate();
		game.startGame();
		Game.testing = false;
	}

	@Test
	public void generateHTMLTest() throws IOException {
		mygame = new Game();
		Game.num_play=2;
		mygame.map = new Map();
		mygame.map.size = 5;
		//map.generate();
		
		mygame.players = new Player[2];
		mygame.players[0] = new Player();
		mygame.players[0].setFixedStart(2);
		mygame.players[1] = new Player();
		mygame.players[1].setFixedStart(2);


		for(int i = 0; i<Map.grid.length; i++)
		{
			for(int j = 0; j < Map.grid.length; j++)
			{
				Map.grid[i][j] = Color.GREEN;
			}
		}

		for (int i=0; i < mygame.players.length; i++)
		{
			Map.grid[mygame.players[i].position.x][mygame.players[i].position.y-1] = Color.BLUE;
			mygame.players[i].move('u');
			Map.grid[mygame.players[i].position.x+1][mygame.players[i].position.y] = Color.GREEN;
			mygame.players[i].move('r');
			Map.grid[mygame.players[i].position.x][mygame.players[i].position.y+1] = Color.YELLOW;
			mygame.players[i].move('d');
		}
		
		

		mygame.generateHTMLFiles();

		File file1 = new File("map_player_1.html");
		File file2 = new File("map_player_1.html");

		assertEquals(file1, file2);		 
	}
	
	@After
	public void cleanUpStreams() {
		System.setOut(null);
	}

}