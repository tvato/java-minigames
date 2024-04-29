import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class TTTGameGUI implements ActionListener{
	private JPanel askPanel, panel, mainMenuPanel;
	private JButton player, com, b1, b2, b3, b4, b5, b6, b7, b8, b9, mainMenu, newGame, back;
	private JFrame f;
	private JLabel turns;
	private JDialog d;
	private TTT ttt = new TTT();
	private int turn;			// Tracks whose turn it is
	private int playedGames, winsByP1, winsByP2;
	
	public TTTGameGUI(JFrame frame, JPanel mmPanel){
		/*	Constructor
		*	param: frame, mmPanel
		*	frame is main menu frame, panel is main menu panel
		*/	
		mainMenuPanel = mmPanel;
		f = frame;
		f.setTitle("Tic Tac Toe");			// Set the game title
		initGame();
	}
	public void initGame(){
		// Start game and draws gamepanel
		
		turn = 1;
		panel = new JPanel();
		panel.setSize(500,500);
		panel.setLayout(null);
		playedGames++;
		
		b1 = new JButton();
		b2 = new JButton();
		b3 = new JButton();
		b4 = new JButton();
		b5 = new JButton();
		b6 = new JButton();
		b7 = new JButton();
		b8 = new JButton();
		b9 = new JButton();
		back = new JButton("Main menu");
		
		turns = new JLabel("Player 1's turn");
		
		b1.setBounds(100,50,50,50);
		b2.setBounds(150,50,50,50);
		b3.setBounds(200,50,50,50);
		b4.setBounds(100,100,50,50);
		b5.setBounds(150,100,50,50);
		b6.setBounds(200,100,50,50);
		b7.setBounds(100,150,50,50);
		b8.setBounds(150,150,50,50);
		b9.setBounds(200,150,50,50);
		back.setBounds(125,380,100,50);
		
		turns.setBounds(125, 20, 100, 20);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);
		b8.addActionListener(this);
		b9.addActionListener(this);
		back.addActionListener(this);
		
		panel.add(b1);
		panel.add(b2);
		panel.add(b3);
		panel.add(b4);
		panel.add(b5);
		panel.add(b6);
		panel.add(b7);
		panel.add(b8);
		panel.add(b9);
		panel.add(back);
		
		panel.add(turns);
		
		f.add(panel);
	}
	public void actionPerformed(ActionEvent e){
		// ActionListener for buttons
		
		if(e.getSource() == player){
			askPanel.setVisible(false);
			initGame();
			
		}else if(e.getSource() == com){
			askPanel.setVisible(false);
			initGame();
			
		}else if(e.getSource() == mainMenu){
			d.dispose();
			panel.setVisible(false);
			mainMenuPanel.setVisible(true);
			ttt.addStats(playedGames, winsByP1, winsByP2);
			f.setTitle("RetroGames");
		}else if(e.getSource() == newGame){
			d.dispose();
			panel.setVisible(false);
			ttt.clearBoard();
			initGame();
		}else if(e.getSource() == back){
			panel.setVisible(false);
			mainMenuPanel.setVisible(true);
			f.setTitle("RetroGames");
		}else{
			JButton b = (JButton) e.getSource();
			place(b);
		}
		
	}
	public void place(JButton button){
		/*	Method to communicate with TTT class
		*	params: button
		*/	
		
		if(button == b1){
			turn = ttt.play(0,0,turn);			// ttt.play() returns turn number 0/1 and -1 if something went wrong
		}else if(button == b2){
			turn = ttt.play(0,1,turn);
		}else if(button == b3){
			turn = ttt.play(0,2,turn);
		}else if(button == b4){
			turn = ttt.play(1,0,turn);
		}else if(button == b5){
			turn = ttt.play(1,1,turn);
		}else if(button == b6){
			turn = ttt.play(1,2,turn);
		}else if(button == b7){
			turn = ttt.play(2,0,turn);
		}else if(button == b8){
			turn = ttt.play(2,1,turn);
		}else if(button == b9){
			turn = ttt.play(2,2,turn);
		}else{
			System.out.println("Something went very wrong here....");
		}
		
		button.setEnabled(false);
		
		if(turn == 0){
			button.setText("X");
			turns.setText("Player 2's turn");
		}else if(turn == 1){
			button.setText("O");
			turns.setText("Player 1's turn");
		}
		
		if(ttt.checkBoard() && ttt.getWinner() == 'X'){
			winsByP1++;
			turns.setText("Player 1 won!");
			turns.setForeground(Color.red);
			buildDialog();
		}else if(ttt.checkBoard() && ttt.getWinner() == 'O'){
			winsByP2++;
			turns.setText("Player 2 won!");
			turns.setForeground(Color.red);
			buildDialog();
		}
	}
	public void buildDialog(){
		// Draws win dialog and asks what the user wants to do?
		// Go back to main menu or play again.
		
		d = new JDialog(f, "Tic Tac Toe", true);
		mainMenu = new JButton("Main menu.");
		newGame = new JButton("Play again.");
		
		d.setSize(300,200);
		d.setLayout(null);
		
		mainMenu.setBounds(20, 100, 100, 20);
		newGame.setBounds(150, 100, 100, 20);
		turns.setBounds(100, 20, 100, 20);
		
		mainMenu.addActionListener(this);
		newGame.addActionListener(this);
		
		d.add(turns);
		d.add(mainMenu);
		d.add(newGame);
		d.setVisible(true);
	}
	public void initAskPanel(){
		//	OBSOLETE
		//	Method to ask whether to play with a human or computer
		
		askPanel = new JPanel();
		askPanel.setSize(500,500);
		askPanel.setLayout(null);
		
		player = new JButton("Player vs Player");
		com = new JButton("Player vs Computer");
		
		player.setBounds(20, 200, 200, 20);
		com.setBounds(250, 200, 200, 20);
		
		player.addActionListener(this);
		com.addActionListener(this);
		
		askPanel.add(player);
		askPanel.add(com);
		
		f.add(askPanel);
	}
}