import static org.junit.Assert.*;

import java.awt.Color;
import java.io.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;




public class GameTester {



	Game mygame = null;
	Map map = null;
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void setUp() throws Exception {
		mygame = new Game();
		Game.num_play=4;
		mygame.setNumPlayers(4);
		map = new Map();
		map.setMapSize(5, 5);
		map.generate();

		
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
	public void testStart()
	{
		Game.testing = true;
		Game.num_play = 2;
		
		
		
		Game game= new Game();
		game.startGame();
		game.map.setMapSize(5, 5);


	}

	@Test
	public void generateHTMLTest() throws IOException {
		mygame = new Game();
		Game.num_play=2;
		map = new Map();
		map.size = 5;
		map.generate();
		
		mygame.players = new Player[2];
		mygame.players[0] = new Player();
		mygame.players[0].setFixedStart(map.size);
		mygame.players[1] = new Player();
		mygame.players[1].setFixedStart(map.size);


		for(int i = 0; i<Map.grid.length; i++)
		{
			for(int j = 0; j < Map.grid.length; j++)
			{
				Map.grid[i][j] = Color.GREEN;
			}
		}

		for (int i=0; i < mygame.players.length; i++)
		{
			mygame.players[i].setFixedStart(2);
			Map.grid[mygame.players[i].position.x][mygame.players[i].position.y-1] = Color.BLUE;
			mygame.players[i].move('u');
			Map.grid[mygame.players[i].position.x+1][mygame.players[i].position.y] = Color.GREEN;
			mygame.players[i].move('r');
			Map.grid[mygame.players[i].position.x][mygame.players[i].position.y+1] = Color.YELLOW;
			mygame.players[i].move('d');
		}
		
		

		
		map.generate();
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
