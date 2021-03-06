import java.awt.Color;
import java.io.*;
import java.util.*;

import main.java.com.pest.demo.factory.Map;
import main.java.com.pest.demo.factory.MapCreator;


public class Game {

	static boolean testing = false;

	static int num_play;

	Map map;

	MapCreator creator;
	static Game g;
	Player[] players;
	Team[] teams;

	public static void main(String args[]) {
		if (!testing) {
			g = new Game();
			g.startGame();
		}
	}

	void startGame() {

		Scanner sc = new Scanner(System.in);

		if (!testing) {
			creator = new MapCreator();
			int map_type = 0;
			int map_size;

			System.out.print("Choose Map Type: (1)Safe, (2)Hazardous: ");

			if (!testing)
				map_type = sc.nextInt();
			do {
				System.out.print("Enter number of players (2-8): ");
				if (!testing)
					num_play = sc.nextInt();
			} while (!g.setNumPlayers(num_play));

			do {
				if (num_play < 5)
					System.out.print("Enter Square Map Size (5x5 - 50x50): ");
				else
					System.out.print("Enter Square Map Size (8x8 - 50x50): ");

				map_size = sc.nextInt();
			} while (!setMapSize(map_size));

			char collaborative_op = ' ';
			do {
				System.out
						.println("Would you like to play in collaborative mode? (y/n)");
				collaborative_op = (char) sc.next().charAt(0);
			} while (!(collaborative_op == 'y' || collaborative_op == 'Y'
					|| collaborative_op == 'n' || collaborative_op == 'N'));

			players = new Player[num_play];

			map = creator.generate(map_type, map_size);

			generateHTMLFiles();

			for (int i = 0; i < num_play; i++) {
				players[i] = new Player(map);
				players[i].setStartPosition(map.getSize());
			}

			int number_Of_Teams = 1;

			if (collaborative_op == 'y' || collaborative_op == 'Y') {
				System.out.println("Enter number of teams");
				number_Of_Teams = sc.nextInt();
			} else
				number_Of_Teams = num_play;
			splitTeams(number_Of_Teams);
		}

		// Starting Game Loop

		char move = 'a';
		int wins;
		do {
			wins = 0;
			for (int i = 0; i < players.length; i++) {
				if (!players[i].reached_end && !testing) {

					do {
						System.out.print("Player " + (i + 1)
								+ " choose a direction (U, D, L, R): ");
						if (!testing)
							move = sc.next().charAt(0);
						else
							move = 'U';
						System.out.println();
					} while (!players[i].move(move) && !testing);
					((Team) players[i].subject)
							.setTeamVisited(players[i].visited);
				} else
					wins++;
				generateHTMLFiles();
			}
		} while (wins < players.length);

		System.out.println("Congratulations ! All players have found the Ruby");

		sc.close();

	}

	boolean setNumPlayers(int n) {
		if (n < 2 || n > 8)
			return false;

		return true;
	}

	boolean setMapSize(int x) {
		if (num_play >= 2 && num_play <= 4) {
			if (x < 5 || x > 50)
				return false;
		} else {
			if (x < 8 || x > 50)
				return false;
		}
		return true;
	}

	void splitTeams(int totTeams) {
		teams = new Team[totTeams];
		for (int i = 0; i < totTeams; i++) {
			teams[i] = new Team();
		}

		ArrayList<Player> temp = new ArrayList<Player>(Arrays.asList(players));
		int team = 0;

		while (temp.size() > 0) {
			int nxtPlayer = (int) (Math.random() * (temp.size() - 1));

			teams[team].register(temp.get(nxtPlayer));
			temp.get(nxtPlayer).setSubject(teams[team]);
			temp.remove(nxtPlayer);

			team++;
			if (team == totTeams)
				team = 0;
		}
	}

	void generateHTMLFiles() {
		try {

			for (int i = 0; i < players.length; i++) {

				FileWriter fWriter = null;
				BufferedWriter writer = null;
				fWriter = new FileWriter("map_" + "player_" + (i + 1) + ".html");
				writer = new BufferedWriter(fWriter);

				writer.write("<html>\n<body>\n<table style=\"border-collapse: collapse;\">\n<tbody>\n");

				Color[][] tiles = map.grid;

				for (int j = 0; j < tiles.length; j++) {
					writer.write("<tr>\n");
					for (int k = 0; k < tiles.length; k++) {
						Position currentPosition = players[i].getPosition();

						Color color = tiles[k][j];
						String hex = "#"
								+ Integer.toHexString(color.getRGB())
										.substring(2);

						int pixels = (49 * j) + 8;

						if (players[i].visited[k][j] == true
								|| (j == currentPosition.y && k == currentPosition.x)) {
							writer.write("<td style=\"background-color:"
									+ hex
									+ ";width:50px;height:50px;border:1px solid black;"
									+ "\">\n");
							if (color == Color.YELLOW)
								writer.write("<img src=\"images/diamond.png\" width=\"53px\" height=\"55px\" style=\"z-index:2;position:absolute;top:"
										+ pixels + "px;\">");
						} else {
							Color c = Color.GRAY;
							String h = "#"
									+ Integer.toHexString(c.getRGB())
											.substring(2);
							writer.write("<td style=\"background-color:"
									+ h
									+ ";width:50px;height:50px;border:1px solid black;"
									+ "\">\n");
						}

						if (j == currentPosition.y && k == currentPosition.x) {
							pixels = (50 * j) + 10;
							if (!players[i].reached_end)
								writer.write("<img src=\"images/face.png\" width=\"50px\" height=\"50px\" style=\"z-index:2;position:absolute;top:"
										+ pixels + "px;\">");
						}

						writer.write("</td>\n");
					}
					writer.write("</tr>\n");
				}
				writer.write("</tbody>\n</table>\n</body>\n</html>\n");
				writer.close();
			}

		} catch (Exception ex) {
		}
	}
}