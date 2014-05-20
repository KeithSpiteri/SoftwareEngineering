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
	MapCreator creator;

	@Before
	public void setup() {
		creator = new MapCreator();
		m = creator.generate(1, 5);
		p = new Player(m);
		m.testing = true;
		Game.num_play = 4;
		g = new Game();
		g.setNumPlayers(4);
		m.testing = true;
		m.size = 5;
		assertEquals(true, g.setMapSize(5));
		// assertEquals(true, g.generate());
	}

	@Test
	public void startTest() {
		assertEquals(true, p.setStartPosition(5));
		assertEquals(false, p.setStartPosition(55));
		assertEquals(false, p.setStartPosition(4));
	}

	@Test
	public void setPos() {
		Position pos = new Position(0, 0);
		Map.grid[0][0] = Color.YELLOW;
		assertEquals(true, p.setPosition(pos));
	}

	@Test
	public void moveTest() {
		p.setFixedStart(2);
		// p.start_pos = new Position(2,2);
		p.reset = false;
		assertEquals(true, p.move('U'));
		p.reset = true;
		p.move('U');
		Position newP = new Position(0, 0);
		p.position = newP;
		assertEquals(false, p.move('U'));

		// p.setStartPosition(5);
		p.setFixedStart(2);
		// p.start_pos = new Position(2,2);
		p.reset = false;
		assertEquals(true, p.move('L'));
		p.reset = true;
		p.move('L');
		newP = new Position(0, 0);
		p.position = newP;
		assertEquals(false, p.move('L'));

		// p.setStartPosition(5);
		p.setFixedStart(2);
		// p.start_pos = new Position(2,2);
		p.reset = false;
		assertEquals(true, p.move('R'));
		p.reset = true;
		p.move('R');
		newP = new Position(4, 0);
		p.position = newP;
		assertEquals(false, p.move('R'));

		// p.setStartPosition(5);
		p.setFixedStart(2);
		// p.start_pos = new Position(2,2);
		p.reset = false;
		assertEquals(true, p.move('D'));
		p.reset = true;
		p.move('D');
		newP = new Position(0, 4);
		p.position = newP;
		assertEquals(false, p.move('D'));
	}

	@Test
	public void getPosTest() {
		Position newP = new Position(0, 0);
		p.position = newP;

		assertEquals(newP, p.getPosition());
	}

	// MAP

	// Map m;
	@Before
	public void beforeMap() {
		m = creator.generate(2, 5);
		m.testing = true;
	}

	@Test
	public void setMapSize() {
		boolean test = false;
		Game.num_play = 4;
		for (int i = 5; i < 51; i++) {
			test = g.setMapSize(i);
			assertEquals(true, test);
		}

		Game.num_play = 8;
		test = g.setMapSize(5);
		assertEquals(false, test);

	}

	@Test
	public void generate() {
		m.size = 5;
		// assertEquals(true, m.generate());
	}

	// Position

	Position pos;

	@Before
	public void before() {

		pos = new Position(0, 5);
	}

	@Test
	public void xyTest() {
		assertEquals(pos.x, 0);
	}

	@Test
	public void testSetandGet() {
		int x = 10;
		int y = 2;
		Position b = new Position(10, 2);

		int value = b.getX();
		assertEquals(value, x);

		value = b.getY();
		assertEquals(value, y);

	}

	@Test
	public void testDefaultCreator() {
		m = creator.generate(5);
	}

	// Game

	@Test
	public void testSetNumPlayers() {
		for (int i = 2; i < 9; i++) {
			assertEquals(true, g.setNumPlayers(i));
		}
		assertEquals(false, g.setNumPlayers(9));
		assertEquals(false, g.setNumPlayers(1));
	}

	@Test
	public void testMain() {
		Game.testing = true;
		Game.main(new String[0]);
	}

	/*
	 * @Test public void testStart() { Game.testing = true; Game.num_play = 2;
	 * Game game= new Game(); game.map.testing = true; game.setMapSize(5);
	 * game.map = creator.generate(2,5); game.map.generate(); game.startGame();
	 * Game.testing = false; }
	 */

	@Test
	public void generateHTMLTest() throws IOException {
		g = new Game();
		Game.num_play = 2;
		g.map = creator.generate(2, 5);
		// g.map.size = 5;
		// map.generate();

		g.players = new Player[2];
		g.players[0] = new Player(g.map);
		g.players[0].setFixedStart(2);
		g.players[1] = new Player(g.map);
		g.players[1].setFixedStart(2);

		for (int i = 0; i < Map.grid.length; i++) {
			for (int j = 0; j < Map.grid.length; j++) {
				Map.grid[i][j] = Color.GREEN;
			}
		}

		for (int i = 0; i < g.players.length; i++) {
			Map.grid[g.players[i].position.x][g.players[i].position.y - 1] = Color.BLUE;
			g.players[i].move('u');
			Map.grid[g.players[i].position.x + 1][g.players[i].position.y] = Color.GREEN;
			g.players[i].move('r');
			Map.grid[g.players[i].position.x][g.players[i].position.y + 1] = Color.YELLOW;
			g.players[i].move('d');
		}

		g.generateHTMLFiles();

		File file1 = new File("map_player_1.html");
		File file2 = new File("map_player_1.html");

		assertEquals(file1, file2);
	}

}