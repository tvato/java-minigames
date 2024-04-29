public class TTT{
	private char board[][] = {{' ',' ',' '},{' ',' ',' '},{' ',' ',' '}};
	private char winner;
	private StatsHandler stats;
	
	public TTT(){
		stats = new StatsHandler();
	}
	
	public void addStats(int games, int p1Wins, int p2Wins){
		// Method to update stats
		// Calls StatsHandler's function
		
		stats.updateStats("./stats/TTTStats.txt", games, p1Wins, p2Wins);
	}
	public int play(int x, int y, int turn){
		/*	Method which draws to board
		*	params: x, y -coordinates and turn -who made the move
		*/
		
		if(board[x][y] == ' ' && turn == 1){
			board[x][y] = 'X';
			return 0;
		}else if(board[x][y] == ' ' && turn == 0){
			board[x][y] = 'O';
			return 1;
		}else{
			return -1;
		}
		
	}
	public char getWinner(){return winner;}
	
	public void clearBoard(){
		// Clears the board for new game
		
		for(int i=0; i<board.length; i++){
			for(int j=0; j<board.length; j++){
				board[i][j] = ' ';
			}
		}
	}
	
	public boolean checkBoard(){
		/*	Checks the board for winner
		*	return: true if there is three in a row (winner)
					false if there is not three in a row (game is still going or it is a tie)
		*/
		
		if(board[0][0] == board[0][1] && board[0][0] == board[0][2] && board[0][0] != ' '){
			winner = board[0][0];
			return true;
		}else if(board[1][0] == board[1][1] && board[1][0] == board[1][2] && board[1][0] != ' '){
			winner = board[1][0];
			return true;
		}else if(board[2][0] == board[2][1] && board[2][0] == board[2][2] && board[2][0] != ' '){
			winner = board[2][0];
			return true;
		}else if(board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] != ' '){
			winner = board[0][0];
			return true;
		}else if(board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] != ' '){
			winner = board[0][2];
			return true;
		}else if(board[0][1] == board[1][1] && board[0][1] == board[2][1] && board[0][1] != ' '){
			winner = board[0][1];
			return true;
		}else if(board[0][2] == board[1][2] && board[0][2] == board[2][2] && board[0][2] != ' '){
			winner = board[0][2];
			return true;
		}else if(board[0][0] == board[1][0] && board[0][0] == board[2][0] && board[0][0] != ' '){
			winner = board[0][0];
			return true;
		}else{
			return false;
		}
	}
	public void printBoard(){
		// OBSOLETE
		// Method to print
		
		for(int i=0; i<board.length; i++){
			for(int j=0; j<board[i].length; j++){
				System.out.print(board[i][j]);
				if(j != 2){
					System.out.print("|");
				}
			}
			System.out.println();
		}
	}
}