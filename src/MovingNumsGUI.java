import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovingNumsGUI{
	private JPanel panel, mmPanel;
	private JButton buttons[][] = new JButton[4][4];
	private JButton back;
	private MovingNums mn;
	private JFrame f;
	private int gamesPlayed=0, wins=0;
	
	public MovingNumsGUI(JFrame frame, JPanel panel){
		/*	Constructor
		*	params: frame is main menu frame, panel is main menu panel
		*/
		
		f = frame;
		mmPanel = panel;
		initGame();
	}
	public void initGame(){				//	Method to start the game and draw the gamepanel
		gamesPlayed++;
		mn = new MovingNums();
		panel = new JPanel();
		panel.setSize(500,500);
		panel.setLayout(null);
		
		back = new JButton("Main menu");
		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				panel.setVisible(false);
				mmPanel.setVisible(true);
			}
		});
		
		back.setBounds(175,380,100,50);
		
		for(int i=0; i<4; i++){				//	loop to create buttons and their actionlisteners for the game
			for(int j=0; j<4; j++){
				final int fi=i, fj=j;
				buttons[i][j] = new JButton(mn.getField(j,i));
				if(buttons[i][j].getText() == "0"){
					buttons[i][j].setEnabled(false);
				}
				buttons[i][j].addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						move(fi,fj);
					}
				});
				buttons[i][j].setBounds(125+(i*50), 50+(j*50), 50,50);
				panel.add(buttons[i][j]);
			}
		}
		panel.add(back);
		f.add(panel);
	}
	public void move(int x, int y){
		/*	Method to move the pieces
		*	params: x, y -coordinates
		*/
		
		
		int zero[] = mn.getZero();
		int zx = zero[0];
		int zy = zero[1];
		
		int ret = mn.move(y,x);
		if(ret == 1 || ret == 2){
			buttons[zy][zx].setEnabled(true);
			buttons[zy][zx].setText(buttons[x][y].getText());
		
			buttons[x][y].setEnabled(false);
			buttons[x][y].setText(mn.getField(y, x));
		}
		if(ret == 2){
			winDialog();
			wins++;
			mn.addStats(gamesPlayed, wins);
		}
	}
	public void winDialog(){				//	Method to draw win dialog and ask user what to do, go back to main menu or play again
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
}