import java.util.*;
import java.lang.*;

public class MovingNums{
	private String field[][] = {{"","","",""},{"","","",""},{"","","",""},{"","","",""}};
	private String winField[][] = {{"1","2","3","4"},
								   {"5","6","7","8"},
								   {"9","10","11","12"},
								   {"13","14","15","0"}};
	private int zero[] = {3,3};				// Keeps track of the zero / "non-pressble" button
	private StatsHandler stats;
	
	public MovingNums(){				//	Constructor
		setField();
		stats = new StatsHandler();
	}
	
	public void addStats(int games, int wins){
		stats.updateStats("./stats/MovingNumsStats.txt", games, wins, games - wins);
	}
	public String getField(int x, int y){				// Method to return string in field[x][y], params: x, y -coordinates
		return field[x][y];
	}
	public void setField(){					//	Method to set the field
		ArrayList<Integer> numbers = new ArrayList<Integer>(16);
		for(int i=1; i < 16; i++){
			numbers.add(i);
		}
		for(int row=0; row < 4; row++){
			for(int col=0; col < 4; col++){
				field[row][col] = get_random(numbers);
			}
		}
	}
	public int[] getZero(){				// Method to return position of zero in int array form
		return zero;
	}
	public int move(int x, int y){
		/*	Method to move the pieces in field
		*	param: x, y -coordinates
		*	return: 1 if pieces were moved or 2 if user won or 0 if pieces were not moved and player didn't win
		*/
		int retVal = 0;
		if(checkZero(x, y)){
			swap(x, y, zero[0], zero[1]);
			retVal = 1;
			if(checkWinField()){
				retVal = 2;
			}
		}
		return retVal;
	}
	public void swap(int x, int y, int xx, int yy){
		/*	Method to swap zero and pressed piece in field
		*	param: x, y -pressed coordinates, xx, yy -zero coordinates
		*/
		
		String current = field[x][y];
		String noll = field[xx][yy];
		
		field[x][y] = noll;
		field[xx][yy] = current;
		
		zero[0] = x;
		zero[1] = y;
	}
	public boolean checkZero(int x, int y){
		/*	Method to check if move is legal
		*	param: x, y -coordinates
		*	return: true if move was legal, false if move is not legal
		*/
		if(x + 1 < field.length && field[x + 1][y] == "0"){
			return true;
		}else if(x - 1 >= 0 && field[x - 1][y] == "0"){
			return true;
		}else if(y + 1 < field[0].length && field[x][y + 1] == "0"){
			return true;
		}else if(y - 1 >= 0 && field[x][y - 1] == "0"){
			return true;
		}else{
			return false;
		}
	}
	public boolean checkWinField(){
		/*	Method to checks if user has won
		*	return: true if user won, false if game is not over
		*/
		
		boolean retVal = true;
		for(int i=0; i<field.length; i++){
			for(int j=0; j<field[i].length; j++){
				if(field[i][j] == winField[i][j] && retVal){
					retVal = true;
				}else{
					return false;
				}
			}
		}
		return retVal;
	}
	public String get_random(ArrayList list){
		/*	Method to return random number as a string to field
		*	param: ArrayList of remaining numbers
		*	return: number as string
		*/
		
		Random r = new Random();
		if(list.isEmpty() != true){
			int num = (int) list.remove(r.nextInt(list.size()));
			return Integer.toString(num);
		}else{
			return "0";
		}
	}
	public void print(String[][] ff){				//	OBSOLETE, used to print the field
		for(int k=0; k < 4; k++){
			for(int l=0; l<4; l++){
				System.out.print(ff[k][l] + ",");
			}
			System.out.println();
		}
	}
}