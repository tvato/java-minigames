import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenu implements ActionListener{
	
	private JButton numberPuz, memory, rPS, ticTac, close;
	private JComboBox<String> stats;
	private RPSGameGUI rps;
	private TTTGameGUI ttt;
	private MemoryGameGUI mg;
	private MovingNumsGUI mn;
	private JFrame frame;
	private JPanel panel;
	private String statsArray[] = {"Number Puzzle","Memory Game","Rock, Paper, Scissors","TicTacToe"};
	
	public MainMenu(){				//	Constructor
		frame = new JFrame("RetroGames");
		panel = new JPanel();
		
		frame.setSize(500,500);
		frame.setLayout(null);
		
		panel.setSize(500,500);
		panel.setLayout(null);
		
		numberPuz = new JButton("Number Puzzle");
		memory = new JButton("Memory Game");
		rPS = new JButton("Rock, paper, Scissors");
		ticTac = new JButton("TicTacToe");
		close = new JButton("Exit game");
		stats = new JComboBox<>(statsArray);
		
		stats.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				panel.setVisible(false);
				String gameName = (String) stats.getSelectedItem();
				StatsHandlerGUI statsHandler = new StatsHandlerGUI(gameName, frame, panel);
			}
		});
		
		numberPuz.setBounds(50,50,165,50);
		memory.setBounds(50,120,165,50);
		rPS.setBounds(50,190,165,50);
		ticTac.setBounds(50,260,165,50);
		close.setBounds(280,370,165,50);
		stats.setBounds(280,50,165,50);
	
		panel.add(numberPuz);
		panel.add(memory);
		panel.add(rPS);
		panel.add(ticTac);
		panel.add(close);
		panel.add(stats);
		
		numberPuz.addActionListener(this);
		memory.addActionListener(this);
		rPS.addActionListener(this);
		ticTac.addActionListener(this);
		close.addActionListener(this);
		
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	public void actionPerformed(ActionEvent e){				//	ActionListener for buttons
		if(e.getSource() == numberPuz){
			panel.setVisible(false);
			mn = new MovingNumsGUI(frame, panel);
		}else if(e.getSource() == memory){
			panel.setVisible(false);
			mg = new MemoryGameGUI(frame, panel);
		}else if(e.getSource() == rPS){
			panel.setVisible(false);
			rps = new RPSGameGUI(frame, panel);
		}else if(e.getSource() == ticTac){
			panel.setVisible(false);
			ttt = new TTTGameGUI(frame, panel);
		}else if(e.getSource() == close){
			System.exit(0);
		}else{
			System.out.println("Whoops, something went wrong!");
			
		}
	}
}