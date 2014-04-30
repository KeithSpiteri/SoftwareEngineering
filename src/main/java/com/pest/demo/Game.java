import java.awt.Color;
import java.io.*;
import java.util.*;

public class Game {

	static boolean testing = false;


	static int num_play;

	Map map;
	static Game g;
	Player[] players;

	public static void main()
	{
		if(!testing){
			g = new Game();
			g.startGame();
		}
	}

	void startGame()
	{

		Scanner sc = new Scanner(System.in);

		if(!testing){
			map = new Map();
			int map_size;
			do
			{	
				System.out.print("Enter number of players (2-8): ");
				if(!testing)
					num_play = sc.nextInt();
			}while(!g.setNumPlayers(num_play));

			do
			{
				if(num_play < 5)
					System.out.print("Enter Square Map Size (5x5 - 50x50): ");
				else
					System.out.print("Enter Square Map Size (8x8 - 50x50): ");

				map_size = sc.nextInt();
			}while(!map.setMapSize(map_size, map_size));
			map.generate();
		}

		players = new Player[num_play];

		for(int i = 0; i < num_play; i++)
		{
			players[i] = new Player();
			players[i].setStartPosition(map.size);
		}


		//Starting Game Loop

		char move = 'a';
		int wins;
		do{
			wins = 0;
			for(int i=0; i<players.length; i++)
			{
				if(!players[i].reached_end && !testing){

					do {
						System.out.print("Player " + (i+1) + " choose a direction (U, D, L, R): ");
						if(!testing)
							move = sc.next().charAt(0); 
						else
							move='U';
						System.out.println();
					} while (!players[i].move(move) && !testing);
				}else
					wins++;
				generateHTMLFiles();
			}
		}while(wins < players.length);

		System.out.println("Congratulations ! All players have found the Ruby");

		sc.close();

	}

	boolean setNumPlayers(int n)
	{
		if(n < 2 || n > 8)
			return false;

		return true;
	}

	void generateHTMLFiles()
	{
		try{

			for (int i=0; i < players.length; i++)
			{

				FileWriter fWriter = null;
				BufferedWriter writer = null;
				fWriter = new FileWriter("map_" + "player_" + (i+1)+".html");
				writer = new BufferedWriter(fWriter);

				writer.write("<html>\n<body>\n<table style=\"border-collapse: collapse;\">\n<tbody>\n");

				Color[][] tiles = Map.grid;

				for (int j=0; j < tiles.length; j++)
				{
					writer.write("<tr>\n");
					for (int k=0; k < tiles.length; k++)
					{
						Position currentPosition = players[i].getPosition();


						Color color = tiles[k][j];
						String hex = "#"+Integer.toHexString(color.getRGB()).substring(2);
						//writer.write("<td style=\"background-color:" + hex + 
						//		";width:50px;height:50px;border:1px solid black;" + "\">\n");
						int pixels = (49 * j) + 8;

						if(players[i].visited[k][j] == true || (j == currentPosition.y && k == currentPosition.x)){
							writer.write("<td style=\"background-color:" + hex + 
									";width:50px;height:50px;border:1px solid black;" + "\">\n");
							if(color == Color.YELLOW)
								writer.write("<img src=\"images/diamond.png\" width=\"53px\" height=\"55px\" style=\"z-index:2;position:absolute;top:" + pixels + "px;\">");
						}else{
							Color c = Color.GRAY;
							String h = "#"+Integer.toHexString(c.getRGB()).substring(2);
							writer.write("<td style=\"background-color:" + h + 
									";width:50px;height:50px;border:1px solid black;" + "\">\n");
						}

						if (j == currentPosition.y && k == currentPosition.x)
						{
							pixels = (50 * j) + 10;
							if(!players[i].reached_end)
								writer.write("<img src=\"images/face.png\" width=\"50px\" height=\"50px\" style=\"z-index:2;position:absolute;top:" + pixels + "px;\">");
						}

						writer.write("</td>\n");
					}
					writer.write("</tr>\n");	
				}
				writer.write("</tbody>\n</table>\n</body>\n</html>\n");
				writer.close();
			}

		}catch(Exception ex){}
	}


}


