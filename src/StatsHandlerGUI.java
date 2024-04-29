import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatsHandlerGUI{
	private JFrame frame;
	private JPanel mmPanel, panel;
	private JButton back, reset;
	private StatsHandler statsHandler;
	private JLabel[] statsLabels;
	private String[] statsArray;
	
	public StatsHandlerGUI(String game, JFrame f, JPanel mmP){
		/*	Constructor
			param: game - gamename, f - mainmenu frame, mmP - mainmenu panel
		*/
		
		mmPanel = mmP;
		frame = f;
		initPanel(game);
	}
	public void initPanel(String game){
		/*	Method to draw statistics panel
			param: game - gamename
		*/
		
		panel = new JPanel();
		panel.setSize(500,500);
		panel.setLayout(null);
		statsHandler = new StatsHandler();
		
		while(statsArray == null || statsArray.length<=0){		// checks if file is empty
			statsArray = statsHandler.readFile(game);
		}
		
		statsLabels = new JLabel[statsArray.length];
		
		
		back = new JButton("Main menu");
		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				panel.setVisible(false);
				mmPanel.setVisible(true);
				statsArray = null;
				frame.setTitle("RetroGames");
			}
		});
		
		reset = new JButton("Reset stats");
		reset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				statsHandler.resetStats(game);
				panel.setVisible(false);
				statsArray = null;
				initPanel(game);
			}
		});
		
		back.setBounds(50, 350, 100, 50);
		reset.setBounds(330, 350, 100, 50);
		
		for(int i=0; i<statsArray.length; i++){
			statsLabels[i] = new JLabel(statsArray[i]);
			statsLabels[i].setBounds(50,50+(50*i),200,20);
			panel.add(statsLabels[i]);
		}
		frame.setTitle(game + " Statistics");
		panel.add(back);
		panel.add(reset);
		
		frame.add(panel);
	}
}