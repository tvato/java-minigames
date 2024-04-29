import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.lang.Math;

public class StatsHandler{
	private int numOfGames=0, numOfWins=0, numOfLoses=0;
	private double winPercent=0, lossPercent=0;
	
	public String[] readFile(String game){
		/*	Method to read statsfiles
			param: game - gamename
			return: stats: -string array wich holds statistics
		*/
		
		String fileName = getFileName(game);
		String[] stats;
		try{
			File file = new File(fileName);
			Scanner reader = new Scanner(file);
			if(!reader.hasNextLine()){
				writeFile(fileName);
			}else{
				//	Declares string array with size suitable for each stats file
				//	Reads stats file, parses the line, and assigns values to variables
				
				if(fileName == "./stats/TTTStats.txt"){
					stats = new String[5];
					for(int i=0; i<stats.length; i++){
						stats[i] = reader.nextLine();
					}
					numOfGames = Integer.parseInt(stats[0].substring(17,stats[0].length()));
					numOfWins = Integer.parseInt(stats[1].substring(27,stats[1].length()));
					numOfLoses = Integer.parseInt(stats[2].substring(27,stats[2].length()));
					winPercent = Double.parseDouble(stats[3].substring(27,stats[3].length()-1));
					lossPercent = Double.parseDouble(stats[4].substring(27,stats[4].length()-1));
				}else if(fileName == "./stats/RPSStats.txt"){
					stats = new String[4];
					for(int i=0; i<stats.length; i++){
						stats[i] = reader.nextLine();
					}
					numOfGames = Integer.parseInt(stats[0].substring(17,stats[0].length()));
					numOfWins = Integer.parseInt(stats[1].substring(16,stats[1].length()));
					numOfLoses = Integer.parseInt(stats[2].substring(17,stats[2].length()));
					winPercent = Double.parseDouble(stats[3].substring(16,stats[3].length()-1));
				}else{
					stats = new String[3];
					for(int i=0; i<stats.length; i++){
						stats[i] = reader.nextLine();
					}
					numOfGames = Integer.parseInt(stats[0].substring(17,stats[0].length()));
					numOfWins = Integer.parseInt(stats[1].substring(16,stats[1].length()));
					winPercent = Double.parseDouble(stats[2].substring(16,stats[2].length()-1));
				}
				return stats;
			}
			reader.close();
		}catch(FileNotFoundException e){
			// If file not found, make file
			writeFile(fileName);
		}
		return new String[0];			//	returns empty string array if error
	}
	public void updateStats(String fileName, int games, int wins, int loses){
		/*	Method to update stats to file
			param:	fileName - filename, games - number of games played,
					wins - number of wins, loses - number of loses
					if game is TicTacToe, wins - player 1's wins, loses - player 2's wins
		*/
		
		readFile(getFileName(fileName));
		numOfGames += games;
		numOfWins += wins;
		numOfLoses += loses;
		writeFile(fileName);
	}
	public void resetStats(String game){
		// Method to reset stats, param: gamename
		
		numOfGames = 0;
		numOfWins = 0;
		numOfLoses = 0;
		winPercent = 0;
		lossPercent = 0;
		writeFile(getFileName(game));
	}
	public String getFileName(String game){
		// Method to return filename based on the gamename
		
		switch(game){
			case "Number Puzzle":
				return "./stats/MovingNumsStats.txt";
			case "Memory Game":
				return "./stats/MemoryGameStats.txt";
			case "Rock, Paper, Scissors":
				return "./stats/RPSStats.txt";
			case "TicTacToe":
				return "./stats/TTTStats.txt";
													//	These will do the opposite: return gamename based on filename
			case "./stats/RPSStats.txt":
				return "Rock, Paper, Scissors";
			case "./stats/MemoryGameStats.txt":
				return "Memory Game";
			case "./stats/MovingNumsStats.txt":
				return "Number Puzzle";
			case "./stats/TTTStats.txt":
				return "TicTacToe";
		}
		return "";		// return empty string if error
	}
	public double calculateWinPercent(){
		//	Calculates win percent, rounds it to be integer and returns it
		
		if(numOfGames == 0){
			return 0;
		}
		winPercent = Double.valueOf(numOfWins) / Double.valueOf(numOfGames) * 100;
		return Math.round(winPercent);
	}
	public double calculateLossPercent(){
		//	Calculates loss percent, rounds it to be integer and returns it
		
		if(numOfGames == 0){
			return 0;
		}
		lossPercent = 100 - winPercent;
		return Math.round(lossPercent);
	}
	public void writeFile(String fileName){
		/*	Method to write stats to given file
			param: fileName - filename
		*/
		
		try{
			FileWriter writer = new FileWriter(fileName);
			writer.write("Number of games: " + numOfGames + "\n");
			if(fileName == "./stats/TTTStats.txt"){
				writer.write("Player 1's number of wins: " + numOfWins + "\n");
				writer.write("Player 2's number of wins: " + numOfLoses + "\n");
				writer.write("Player 1's win percentage: " + calculateWinPercent() + "%\n");
				writer.write("Player 2's win percentage: " + calculateLossPercent() + "%\n");
			}else{
				writer.write("Number of wins: " + numOfWins + "\n");
				if(fileName == "./stats/RPSStats.txt"){
					writer.write("Number of loses: " + numOfLoses + "\n");
				}
				writer.write("Win percentage: " + calculateWinPercent() + "%\n");
			}
			writer.close();
		}catch(IOException ioe){
			System.out.println("Error writing stats...");
            System.exit(-1);
		}
	}
}