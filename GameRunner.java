
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameRunner extends JPanel{
	
	
	
	private GamePanelConnect4 connect4;
	private GamePanelNoughts noughts;
	private GamePanelChess chess;
	private GamePanelMineSweeper minesweeper;
	private JButton returnToMenu;
	//private int width = 1000;
	//private int height = 1000;
		
	GameRunner(char gameType){	
		
		
		this.setLayout(new BorderLayout());
		
		
		if (gameType == 'C'){
			connect4 = new GamePanelConnect4();
			this.add(connect4, BorderLayout.CENTER);
		}
		
		else if (gameType == 'N'){
			noughts = new GamePanelNoughts();
			this.add(noughts, BorderLayout.CENTER);
		}
		
		else if (gameType == 'H') {
			chess = new GamePanelChess();
			this.add(chess, BorderLayout.CENTER);
		}
		
		else if (gameType == 'M'){
			minesweeper = new GamePanelMineSweeper();
			this.add(minesweeper, BorderLayout.CENTER);
		}
		
	}
	
	
	
	

}













	
	
	
	
	
	
	
	
	
	
	