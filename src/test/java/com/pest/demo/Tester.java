import static org.junit.Assert.*;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.java.com.pest.demo.observer.Observer;
import main.java.com.pest.demo.observer.Subject;
import main.java.com.pest.demo.factory.Map;
import main.java.com.pest.demo.factory.MapCreator;
import main.java.com.pest.demo.factory.SafeMap;
import main.java.com.pest.demo.factory.HazardousMap;
import main.java.com.pest.demo.factory.SafeMapCreator;
import main.java.com.pest.demo.factory.HazardousMapCreator;

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
		m.setTesting(true);
		assertEquals(m.isTesting(), true);
		Game.num_play = 4;
		g = new Game();
		g.setNumPlayers(4);

		m.setTesting(true);
		m.setSize(5);
		assertEquals(true, g.setMapSize(5));
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

		p.reset = false;
		assertEquals(true, p.move('U'));
		p.reset = true;
		p.move('U');
		Position newP = new Position(0, 0);
		p.position = newP;
		assertEquals(false, p.move('U'));

		p.setFixedStart(2);
		p.reset = false;
		assertEquals(true, p.move('L'));
		p.reset = true;
		p.move('L');
		newP = new Position(0, 0);
		p.position = newP;
		assertEquals(false, p.move('L'));

		p.setFixedStart(2);
		p.reset = false;
		assertEquals(true, p.move('R'));
		p.reset = true;
		p.move('R');
		newP = new Position(4, 0);
		p.position = newP;
		assertEquals(false, p.move('R'));

		p.setFixedStart(2);
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
		m.setSize(5);
		m = creator.generate(2, 5);
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

	@Test
	public void generateHTMLTest() throws IOException {
		g = new Game();
		Game.num_play = 4;
		g.map = creator.generate(2, 5);

		g.players = new Player[4];
		g.players[0] = new Player(g.map);
		g.players[0].setFixedStart(2);
		g.players[1] = new Player(g.map);
		g.players[1].setFixedStart(2);
		g.players[2] = new Player(g.map);
		g.players[2].setFixedStart(2);
		g.players[3] = new Player(g.map);
		g.players[3].setFixedStart(2);

		g.splitTeams(2);

		assertEquals(g.teams.length, 2);

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

	@Test
	public void teamMove() {
		g.num_play = 4;

		g.players = new Player[4];
		g.players[0] = new Player(g.map);
		g.players[0].setFixedStart(2);
		g.players[1] = new Player(g.map);
		g.players[1].setFixedStart(2);
		g.players[2] = new Player(g.map);
		g.players[2].setFixedStart(2);
		g.players[3] = new Player(g.map);
		g.players[3].setFixedStart(2);

		g.splitTeams(2);

		g.players[0].move('u');

		((Team) g.players[0].subject).setTeamVisited(g.players[0].visited);

		boolean test = ((Arrays.deepEquals(g.players[0].visited,
				g.players[1].visited))
				| (Arrays
						.deepEquals(g.players[0].visited, g.players[2].visited)) | (Arrays
				.deepEquals(g.players[0].visited, g.players[3].visited)));

		assertEquals(true, test);

	}
}