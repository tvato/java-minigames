import java.util.*;

public class Memory{
	private char[][] field;
	private ArrayList<Character> pairs = new ArrayList<Character>();
	private StatsHandler stats;
	
	public Memory(int x){
		/*	Constructor
		*	param: x = size of the field (4x4, 6x6 or 8x8)
		*/
		stats = new StatsHandler();
		field = new char[x][x];
		generate_field(x);
	}
	
	public void addStats(int games, int wins){
		stats.updateStats("./stats/MemoryGameStats.txt", games, wins, games - wins);
	}
	public void generate_field(int size){
		/*	Method to generate the field
		*	param: size = size of the field
		*/
		
		int numOfPairs = (size * size) / 2;
		char c = 'A';						//	game uses characters as a cards, in 8x8 numbers are included
		for(int i=0; i<numOfPairs; i++){
			pairs.add(c);		// 'A'
			pairs.add(c);
			if(c == 'Z'){		//	if alphabets ran out, starts using numbers from 2 upwards
				c = '2';
			}else{
				c+=1;
			}
		}
		
		Random r = new Random();
		
		int a=0;
		for(int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				a = r.nextInt(pairs.size());
				field[i][j] = pairs.get(a);
				pairs.remove(a);
			}
		}
	}
	public char getChar(int x, int y){				//	Method to return character at x,y -coordinates in field
		return field[x][y];
	}
	public void print(){				//	OBSOLETE, used to print field
		for(int i=0; i<field.length; i++){
			for(int j=0; j<field[i].length; j++){
				System.out.print(field[i][j] + " ");
			}
			System.out.println();
		}
	}
}