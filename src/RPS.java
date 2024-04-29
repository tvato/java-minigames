import java.util.*;
import java.lang.*;

public class RPS{
	/*	choice = user choice
	*	COMchoice = computer choice
	*	difficulty 0 = normal
	*	difficulty 0< = impossible
	*/
	private int choice, COMchoice, difficulty;
	private String result;
	private StatsHandler stats;
	private int numOfGames=0, numOfWins=0;
	
	public RPS(int val, int vt){
		/*	Constructor
		*	params: val = your choice, vt = difficulty
		*/
		stats = new StatsHandler();
		choice = val;
		difficulty = vt;
		COMchoice = randomizeCOM();
		result();
	}
	
	public String getCOMinString(){				//	Returns computers choice as a string
		if(COMchoice == 0){
			return "Rock";
		}else if(COMchoice == 1){
			return "Paper";
		}else if(COMchoice == 2){
			return "Scissor";
		}else{
			return "Opponent chose not to play...";
		}
	}
	public String getResult(){				//	Returns result
		return result;
	}
	public int randomizeCOM(){				//	Method to randomize computers choice
		if(difficulty == 0){
			Random r = new Random();
			return r.nextInt(3);
		}else{
			return manipulate();
		}
	}
	public int manipulate(){				//	Method to manipulate computers choice in hard difficulty
		if(choice == 0){
			return 1;
		}else if(choice == 1){
			return 2;
		}else if(choice == 2){
			return 0;
		}else{
			return 578;					//Error
		}
	}
	public void result(){				//	Checks the result of the game
		/*	0 = rock
		* 	1 = paper
		* 	2 = scissors
		*
		* 	0 W 2		2 H 0
		*	1 W 0		0 H 1
		*	2 W 1		1 H 2
		*/	
		if(choice == 0 && COMchoice == 2){
			result = "YOU WON!";
			numOfWins++;
			numOfGames++;
		}else if(choice == 1 && COMchoice == 0){
			result = "YOU WON!";
			numOfWins++;
			numOfGames++;
		}else if(choice == 2 && COMchoice == 1){
			result = "YOU WON!";
			numOfWins++;
			numOfGames++;
		}else if(choice == COMchoice){
			result = "TIE!";
			numOfGames++;
		}else{
			result = "YOU LOST!";
			numOfGames++;
		}
		stats.updateStats("./stats/RPSStats.txt", numOfGames, numOfWins, numOfGames - numOfWins);
	}
}