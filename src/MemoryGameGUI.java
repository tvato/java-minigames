import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class MemoryGameGUI implements ActionListener{
	private JPanel askPanel, panel, mmPanel;
	private JButton buttons[][];
	private JButton easy, normal, hard, back;
	private JButton tmpB1, b;
	private Memory m;
	private JFrame f;
	private int dif=0, gamesPlayed=0, wins=0;
	private char[] pairs;
	
	
	public MemoryGameGUI(JFrame frame, JPanel p){
		/*	Constructor
		*	params: frame is main menu frame, p is main menu panel
		*/
		
		mmPanel = p;
		f = frame;
		f.setTitle("Memory Game");
		initAskPanel();
	}
	public void initAskPanel(){				//	Method to draw panel and ask user difficulty
		JLabel difficulty = new JLabel("Choose difficulty:");
		askPanel = new JPanel();
		askPanel.setSize(500,500);
		askPanel.setLayout(null);
		
		easy = new JButton("Easy (4x4 grid)");
		normal = new JButton("Normal (6x6 grid)");
		hard = new JButton("Hard (8x8 grid)");
		
		difficulty.setBounds(150,100,100,20);
		easy.setBounds(130,120,150,20);
		normal.setBounds(130,145,150,20);
		hard.setBounds(130,170,150,20);
		
		easy.addActionListener(this);
		normal.addActionListener(this);
		hard.addActionListener(this);
		
		askPanel.add(difficulty);
		askPanel.add(easy);
		askPanel.add(normal);
		askPanel.add(hard);
		
		f.add(askPanel);
		
	}
	public void initGame(){				//	Method to start the game and draw gamepanle
		gamesPlayed++;
		pairs = new char[2];
		buttons = new JButton[dif][dif];
		panel = new JPanel();
		panel.setSize(500,500);
		panel.setLayout(null);
		int modifier = 12/dif;				// modifier to center the cards
		
		// Generates gameboard with buttons, and adds actionlisteners to buttons
		for(int i=0; i<dif; i++){
			for(int j=0; j<dif; j++){
				final int final_i = i;
				final int final_j = j;
				buttons[i][j] = new JButton("?");
				buttons[i][j].addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						openCard(final_i, final_j, e);
					}
				});
				buttons[i][j].setBounds((50*i)+modifier*45, 15+(j*50), 50, 50);
				panel.add(buttons[i][j]);
			}
		}
		back = new JButton("Main menu");
		
		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				panel.setVisible(false);
				mmPanel.setVisible(true);
				f.setTitle("RetroGames");
			}
		});
		
		back.setBounds(145,425,200,20);
		
		panel.add(back);
		
		f.add(panel);
	}
	public void winDialog(){				//	Method to draw win dialog and ask what user wants to do, go back to main menu or play again
		JDialog win = new JDialog();
		win.setSize(500,200);
		win.setLayout(null);
		
		JLabel ask = new JLabel("What do you want to do?");
		
		JButton back = new JButton("Main menu");
		JButton reset = new JButton("New game");
		
		
		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				win.dispose();
				panel.setVisible(false);
				mmPanel.setVisible(true);
				
			}
		});
		reset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				win.dispose();
				panel.setVisible(false);
				initGame();
			}
		});
		
		ask.setBounds(170,20,300,20);
		back.setBounds(45,50,190,20);
		reset.setBounds(245,50,190,20);
		
		win.add(ask);
		win.add(back);
		win.add(reset);
		
		
		win.setVisible(true);
	}
	public void openCard(int x, int y, ActionEvent e){
		/*	Method to open card
		*	param: x,y -coordinates, e = actionevent to get pressed button
		*/
		
		if(pairs[0] == ' ' && pairs[1] == ' '){
			tmpB1.setText("?");
			b.setText("?");
		}
		char c = m.getChar(x,y);
		
		b = (JButton) e.getSource();
		b.setText(String.valueOf(c));
		
		if(pairs[0] == 0 | pairs[0] == ' '){
			pairs[0] = c;
		}else if(pairs[1] == 0 | pairs[1] == ' '){
			pairs[1] = c;
		}
		checkPair();
		if(checkWin()){
			winDialog();
			wins++;
			m.addStats(gamesPlayed, wins);
		}
		
	}
	public boolean checkWin(){				//	Method to check if there is any more cards left, returns true if game has ended
		boolean retVal = false;
		for(int i=0; i<buttons.length; i++){
			for(int j=0; j<buttons[i].length; j++){
				if(buttons[i][j].getText() != "?"){
					retVal = true;
				}else{
					return false;
				}
			}
		}
		return retVal;
	}
	public void checkPair(){				//	Method to check if pressed cards are pairs, if they are sets buttons disabled
		if(pairs[0] == pairs[1] && pairs[0] != ' '){
			b.setEnabled(false);
			tmpB1.setEnabled(false);
			b = new JButton();
			tmpB1 = new JButton();
			pairs[0] = ' ';
			pairs[1] = ' ';
		}else if(pairs[0] == 0 || pairs[0] == ' ' || pairs[1] == 0 || pairs[1] == ' '){
			tmpB1 = b;
		}else{
			pairs[0] = ' ';
			pairs[1] = ' ';
		}
	}
	public void actionPerformed(ActionEvent e){				//	ActionListener for buttons
		if(e.getSource() == easy){
			dif = 4;
			askPanel.setVisible(false);
			initGame();
		}else if(e.getSource() == normal){
			dif = 6;
			askPanel.setVisible(false);
			initGame();
		}else if(e.getSource() == hard){
			dif = 8;
			askPanel.setVisible(false);
			initGame();
		}
		m = new Memory(dif);
	}
}