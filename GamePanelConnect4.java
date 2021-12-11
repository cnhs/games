import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class GamePanelConnect4 extends JPanel implements ActionListener{
	
	private int width =7;
	private int height = 6;
	private int numOfTiles = width * height;
	private int numToWin = 4;
	private int inARow;
	private Tile[] tiles = new Tile[numOfTiles];
	private char turn = 'O';
	private char check = ' ';
	private char winner = ' ';
	private JLabel turnLabel;
	private JButton resetButton;
	private int wwidth = 800;
	private int wheight = 800;
	private boolean change = false;
	private int[] winTiles = new int[numToWin];
	private boolean filled = false;
	
	

	public GamePanelConnect4(){

		
		this.setLayout(new BorderLayout());
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1,2));
		
		turnLabel = new JLabel("Turn: " + this.getTurn(), SwingConstants.CENTER);
		turnLabel.setBackground(Color.BLACK);
		turnLabel.setForeground(Color.WHITE);
		turnLabel.setFont(new Font("Sans Serif",Font.BOLD,wwidth/18));
		topPanel.add(turnLabel);
		
		resetButton = new JButton("Reset");
		resetButton.setFont(new Font("Sans Serif",Font.BOLD,wwidth/18));
		resetButton.setBackground(Color.BLACK);
		resetButton.setForeground(Color.WHITE);
		resetButton.setFocusable(false);
		resetButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		resetButton.addActionListener(this);
		topPanel.add(resetButton);
		topPanel.setBackground(Color.BLACK);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(height,width,5,5));
	
		
		// makes each of the items in the array a tile and it adds them to the layout and adds an action listener to each of them
		
		for (int i=0; i<this.tiles.length; i++) {
			 this.tiles[i] = new Tile(' ');
			 buttonPanel.add(this.tiles[i]);
			 tiles[i].addActionListener(this);
			 tiles[i].setBackground(Color.gray);
			 buttonPanel.setBackground(Color.BLACK);
			 tiles[i].setFont(new Font("Forte", Font.PLAIN, wwidth/12));
			 
		this.add(buttonPanel, BorderLayout.CENTER);
		this.add(topPanel, BorderLayout.NORTH);
		 }
		 
		 
		 
		 
		 
	}
	
	
	//public int getWidth() {
	//	return wwidth;
	//}
	//
	//public int getHeight() {
	//	return wheight;
	//}
	
	
	public void reset() {
		
		for (Tile tile: tiles) {
			tile.setSymbol(' ');
			tile.setBackground(Color.gray);
		}
		
		turn = 'O';
		check = ' ';
		winner = ' ';
		turnLabel.setText("Turn: " + turn);
	}
	
	public char getTurn() {
		return turn;
	}
	
	public int tileLocator(int r, int c) {
		
		int loc;
		loc = (r)*width + (c);
		
		return loc;
	}
	
	
	private void notInARow() {
		if (winner == ' '){
			inARow = 0;
			winTiles = new int[numToWin];
		}
	}
	
	private void yesInARow(int row, int col) {
		if (winner == ' '){
			inARow +=1;

			winTiles[inARow-1] = tileLocator(row,col);
		}
	}
	
	private void nextTurn() {
		
		if (turn == 'O') {
			turn = 'X';
		}
		
		else {
			turn = 'O';
		}
		
		turnLabel.setText("Turn: "+ turn);
		
	}
	
	
	public char checkWinner() {
		
		// Noughts
		//char winner = ' ';
		check = 'O';
		
		for (int q=0; q<2; q++) {
			if (q==1) {
				check = 'X';
			}
			
			// Checks Rows
			
			for (int row=0; row<(height); row++ ){
				notInARow();
				
				if (winner == ' ') {
					for (int col=0; col<(width); col++ ){
						
						if (tiles[tileLocator(row,col)].getSymbol() == check) {
							yesInARow(row,col);
							if (inARow >= numToWin){
								winner = check;
								turnLabel.setText("Winner Winner: " + winner);
							}
							
						}
						
						else {
							notInARow();
						}
						
						
						
						
					}
				}
			}
			
			notInARow();
			
			//Checks Columns
			
			for (int col=0; col<(width); col++ ){
				notInARow();
				
				if (winner == ' ') {
					for (int row=0; row<(height); row++ ){
						

						if (tiles[tileLocator(row,col)].getSymbol() == check) {
							yesInARow(row,col);
							if (inARow >= numToWin){
								winner = check;
								turnLabel.setText("Winner Winner: " + winner);
							}
							
						}
						
						else {
							notInARow();
						}
						
						
						
						
					}
				}
			}
			
			notInARow();
			
			// Checks negative (\) diagonals
			
			for (int row=0; row < (height-numToWin)+1; row++) {
				if (winner == ' ') {
					for (int col=0; col < (width-numToWin)+1; col++) {
						notInARow();
						if (tiles[tileLocator(row,col)].getSymbol() == check) {
							yesInARow(row,col);
							
							
							for (int d=1; d < numToWin; d++) {
								
								if (tiles[tileLocator(row+d,col+d)].getSymbol() == check) {
									yesInARow(row+d,col+d);
									if (inARow >= numToWin){
										winner = check;
										turnLabel.setText("Winner Winner: " + winner);
									}
								}
								
								else {
									notInARow();
								}
								
								
	
							}
						}
					}
				}
				
			}
			
			notInARow();
			
			// Checks positive (/) diagonals
			
			for (int row=height-1; row > numToWin-2; row--) {
				if (winner == ' ') {
					for (int col=0; col < (width-numToWin)+1; col++) {
						notInARow();
						if (tiles[tileLocator(row,col)].getSymbol() == check) {
							yesInARow(row,col);
							
							for (int d=1; d < numToWin; d++) {
								
								if (tiles[tileLocator(row-d,col+d)].getSymbol() == check) {
									yesInARow(row-d,col+d);
									if (inARow >= numToWin){
										winner = check;
										turnLabel.setText("Winner Winner: " + winner);
									}
								}
								
								else {
									notInARow();
								}
								
								
	
							}
						}
					}
				}
				
			}
		}
		
		
		
		
		if (winner != ' ') {
			for (int num: winTiles) {
				tiles[num].setBackground(Color.green);
			}
		}
		
		else {
			filled = true;
			for (Tile tile: tiles) {
				if (tile.getSymbol() == ' ' && filled == true){
						filled = false;
				}
			}
				
			if (filled == true) {
				for (Tile tile: tiles) {
					tile.setBackground(Color.red);
					turnLabel.setText("Both Players Lose");
				}
			}
		}
	
		return winner;
	
	}
	
	
	
	public void actionPerformed(ActionEvent e) {

		for (int row=0; row<height; row++) {
			for (int col=0; col<width; col++) {
				
				if (e.getSource() == tiles[tileLocator(row,col)] && winner == ' ') {
					for (int r=height-1; r>-1; r--) {
						
						if (tiles[tileLocator(r,col)].getSymbol() == ' ' && change == false) {
							tiles[tileLocator(r,col)].setSymbol(turn);
							change = true;
							
							nextTurn();
						}
					}
				}
			}
		}
		
		
		change = false;
		winner = checkWinner();
		
		
		if (e.getSource() == resetButton) {
			this.reset();
		}	
	}
			
}




