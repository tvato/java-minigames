import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class RPSGameGUI implements ActionListener{
	
	private JButton r, p, s, back, close, normal, hard;		// r = rock, p = paper, s = scissors
	private JLabel you, opponent;
	private JLabel difficulty, result;
	private JFrame frame, f;
	private JPanel panel, mainMenuPanel, difPanel;
	private int vaikeustaso;			// 0 = normal, 1 = hard
	private RPS rps;
	
	public RPSGameGUI(JFrame frame, JPanel mmPanel){
		/*	Constructor
		*	params: frame is main menu frame, panel is main menu panel
		*/
		
		mainMenuPanel = mmPanel;
		f = frame;
		f.setTitle("Rock, Paper, Scissor");
		chooseDifficulty();
	}
	public void chooseDifficulty(){				// Method to ask difficulty
		
		difPanel = new JPanel();
		
		difPanel.setSize(500,500);
		difPanel.setLayout(null);
		
		difficulty = new JLabel("Choose a difficulty:");
		
		normal = new JButton("Normal");
		hard = new JButton("Impossible");
		
		difficulty.setBounds(160, 150, 200, 20);
		normal.setBounds(100, 200, 100, 20);
		hard.setBounds(220, 200, 100, 20);
		
		normal.addActionListener(this);
		hard.addActionListener(this);
		
		difPanel.add(difficulty);
		difPanel.add(normal);
		difPanel.add(hard);
		
		f.add(difPanel);
	}
	public void initGame(){				//	Method to start the game and draws the gamepanel
		
		panel = new JPanel();
		panel.setSize(500,500);
		panel.setLayout(null);
		
		r = new JButton("Rock");
		p = new JButton("Paper");
		s = new JButton("Scissor");
		back = new JButton("Main menu");
		close = new JButton("Close");
		
		you = new JLabel("You picked: ");
		opponent = new JLabel("Your opponent picked: ");
		result = new JLabel();
		result.setForeground(Color.red);
		
		r.setBounds(50, 70, 100, 50);
		p.setBounds(50, 140, 100, 50);
		s.setBounds(50, 210, 100, 50);
		back.setBounds(50, 350, 100, 50);
		close.setBounds(330, 350, 100, 50);
		
		you.setBounds(270, 70, 200, 100);
		opponent.setBounds(270, 100, 200, 100);
		result.setBounds(270, 150, 200, 100);
		
		r.addActionListener(this);
		p.addActionListener(this);
		s.addActionListener(this);
		back.addActionListener(this);
		close.addActionListener(this);
		
		panel.add(r);
		panel.add(p);
		panel.add(s);
		panel.add(back);
		panel.add(close);
		
		panel.add(you);
		panel.add(opponent);
		panel.add(result);
		
		f.add(panel);
		
	}
	public void play(int valinta){
		/*	Method to start the game
		*	params: valinta = rock/paper/scissors
		*/
		
		rps = new RPS(valinta, vaikeustaso);
	}
	public void actionPerformed(ActionEvent e){				// ActionListener for buttons
		if(e.getSource() == r){
			you.setText("You picked: Rock");
			play(0);
			opponent.setText("Your opponent picked: " + rps.getCOMinString());
			result.setText(rps.getResult());
		}else if(e.getSource() == p){
			you.setText("You picked: Paper");
			play(1);
			opponent.setText("Your opponent picked: " + rps.getCOMinString());
			result.setText(rps.getResult());
		}else if(e.getSource() == s){
			you.setText("You picked: Scissor");
			play(2);
			opponent.setText("Your opponent picked: " + rps.getCOMinString());
			result.setText(rps.getResult());
		}else if(e.getSource() == hard){
			vaikeustaso = 1;
			difPanel.setVisible(false);
			initGame();
		}else if(e.getSource() == normal){
			vaikeustaso = 0;
			difPanel.setVisible(false);
			initGame();
		}else if(e.getSource() == back){
			panel.setVisible(false);
			mainMenuPanel.setVisible(true);
			f.setTitle("RetroGames");
		}else if(e.getSource() == close){
			System.exit(0);
		}else{
			System.out.println("Whoops, something went wrong...");
		}
	}
}