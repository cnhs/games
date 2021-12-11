import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.lang.Math;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GamePanelMineSweeper extends JPanel implements ActionListener, MouseListener{

	private int width = 16;
	private int height = 16;
	private int changes = 0;
	private int numOfTiles = width * height;
	private int numOfBombs = 40;
	private int bombsFlagged;
	private int[] bombList = new int[numOfBombs];
	private Tile[] tiles = new Tile[numOfTiles];
	private int newBombLoc;
	private boolean copy = false;
	private String flagText = "True";
	private boolean flag = true;
	private int numOfNeighbourBombs;
	private boolean winner = false;
	private boolean boomboomboom = false;
	private String lastFlagState = "False";
	private Timer myTimer = new Timer(100,this);
	private Color[] colourList = new Color[7];
	private boolean completed = false; 
	private boolean increment = false;
	private JPanel topPanel;
	private JButton resetButton;
	private JButton partyButton;
	private JLabel bombLabel;
	private JLabel timeLabel;
	private String deadMessage = "You Dead";
	private String winMessage = "Winner Winner";
	private boolean party = false;
	private long startTime =0;
	private long time = 0;
	private int wwidth = 800;
	private int wheight = 800;


	private Random rand = new Random();
	
	public GamePanelMineSweeper() {
		
		myTimer.start();
		
		colourList[0] = Color.GREEN;
		colourList[1] = Color.CYAN;
		colourList[2] = Color.CYAN;
		colourList[3] = Color.YELLOW;
		colourList[4] = Color.ORANGE;
		colourList[5] = Color.RED;
		colourList[6] = Color.magenta;
		
		
		
		
		this.setLayout(new BorderLayout());
		
		topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1,2));
		topPanel.setBorder(null);
		
		
		timeLabel = new JLabel(time + "", SwingConstants.CENTER);
		timeLabel.setBackground(Color.BLACK);
		timeLabel.setForeground(Color.WHITE);
		timeLabel.setFont(new Font("Sans Serif",Font.BOLD,wwidth/18));
		topPanel.add(timeLabel);
		
		
		resetButton = new JButton("Reset");
		resetButton.setFont(new Font("Sans Serif",Font.BOLD,wwidth/22));
		resetButton.setBackground(Color.BLACK);
		resetButton.setForeground(Color.WHITE);
		resetButton.setFocusable(false);
		resetButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		resetButton.addActionListener(this);
		topPanel.add(resetButton);;
		

		bombLabel = new JLabel(zfill(numOfBombs, 3), SwingConstants.CENTER);
		bombLabel.setBackground(Color.BLACK);
		bombLabel.setForeground(Color.WHITE);
		bombLabel.setFont(new Font("Sans Serif",Font.BOLD,wwidth/18));
		topPanel.add(bombLabel);
		
		partyButton = new JButton("Party!");
		partyButton.setFont(new Font("Sans Serif",Font.BOLD,wwidth/22));
		partyButton.setBackground(Color.BLACK);
		partyButton.setForeground(Color.WHITE);
		partyButton.addActionListener(this);
		partyButton.setFocusable(false);
		partyButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		topPanel.setBackground(Color.BLACK);
		
		
		
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(height,width,5,5));
		
		
		// makes each of the items in the array a tile and it adds them to the layout and adds an action listener to each of them
		
		for (int i=0; i<this.bombList.length; i++) {
			while (true) {
				Random rand = new Random();
				newBombLoc = rand.nextInt(numOfTiles);
				copy = false;
				for (int loc: bombList) {
					if (loc == newBombLoc){
						copy = true;
					}
				}
				if (copy == false) {
					bombList[i] = newBombLoc;
					break;
				}
			}
		}
		
		for (int num: bombList) {
			
		}
		
		 for (int i=0; i<this.tiles.length; i++) {
			 this.tiles[i] = new Tile(' ');
			 buttonPanel.add(this.tiles[i]);
			 tiles[i].addActionListener(this);
			 tiles[i].setBackground(Color.gray);
			 buttonPanel.setBackground(Color.BLACK);
			 tiles[i].setFont(new Font("Forte", Font.PLAIN, wwidth/35));
			 tiles[i].addMouseListener(this);
			 for (int num: bombList) {
				if (i==num) {
					tiles[i].setIsBomb(true);
				}
			}
		
		 }
		 
		 
		 
		 
		 
		 this.add(buttonPanel, BorderLayout.CENTER);
		 this.add(topPanel, BorderLayout.NORTH);
 
	}
	
	
	private String zfill(long num, long length) {  // This zero fills a number, so with an input of 8,3 it will return 008
		return String.format("%0" + length + "d", num);
	}	
	
	
	
	public int tileLocator(int r, int c) {
		
		int loc;
		loc = (r)*width + (c);
		
		return loc;
	}
		
	
	public void reset() {
		
		winner = false;
		boomboomboom = false;

		time = 0;
		timeLabel.setText(time +"");
		startTime=0;
		
		if (completed == true) {
			topPanel.setLayout(new GridLayout(1,3));
			topPanel.add(timeLabel);
			topPanel.add(resetButton);

			topPanel.add(partyButton);
		}
		
		for (int i=0; i<this.bombList.length; i++) {
			while (true) {
				Random rand = new Random();
				newBombLoc = rand.nextInt(numOfTiles);
				copy = false;
				for (int loc: bombList) {
					if (loc == newBombLoc){
						copy = true;
					}
				}
				if (copy == false) {
					bombList[i] = newBombLoc;
					break;
				}
			}
		}
		
		
		for (int i=0; i<this.tiles.length; i++) {
			tiles[i].setSymbol(' ');
			tiles[i].setBackground(Color.gray);
			tiles[i].setIsBomb(false);
			tiles[i].setIsRevealed(false);
			for (int num: bombList) {
				if (i==num) {
					tiles[i].setIsBomb(true);
				}
			}
		}
		
		
		
		for (int num: bombList) {
			
		}

	}
	
	
	
	
	public int checkOneNeighbour(int row, int col) {
		numOfNeighbourBombs=0;
		if (tiles[tileLocator(row,col)].getIsRevealed() == true) {
			numOfNeighbourBombs=0;
			
			for (int r=-1; r<2; r++) {
				for (int c=-1; c<2; c++) {
					if(row+r >=0 && row+r <=height-1 && col+c >=0 && col+c <=height-1) {
						if (tiles[tileLocator(row+r, col+c)].getIsBomb() == true) {
							numOfNeighbourBombs+=1;
						}
					}
				}
			}			
			
		}
		
	
		return numOfNeighbourBombs;
	}
	

	public void checkNeighbourTiles() {
	
		numOfNeighbourBombs=0;
		changes = 1;
		while (changes != 0) {

			changes = 0;
			for (int row=0;row<width;row++) {
				for (int col=0;col<height;col++) {
					
					if (tiles[tileLocator(row,col)].getIsRevealed() == true) {
						numOfNeighbourBombs=0;
						
						for (int r=-1; r<2; r++) {
							for (int c=-1; c<2; c++) {
								if(row+r >=0 && row+r <=height-1 && col+c >=0 && col+c <=height-1) {
									if (tiles[tileLocator(row+r, col+c)].getIsBomb() == true) {
										numOfNeighbourBombs+=1;
										
									}
								}
							}
						}
						
						if (numOfNeighbourBombs == 0) {
							for (int r=-1; r<2; r++) {
								for (int c=-1; c<2; c++) {
									if(row+r >=0 && row+r <=height-1 && col+c >=0 && col+c <=height-1 ) {
										if (tiles[tileLocator(row+r,col+c)].getSymbol() != 'F' ) {
											if (tiles[tileLocator(row+r,col+c)].getIsRevealed() == false) {
												tiles[tileLocator(row+r, col+c)].setIsRevealed(true);
												changes+=1;
											}
										
											
											tiles[tileLocator(row+r, col+c)].setSymbol(checkOneNeighbour(row+r, col+c));
											if (tiles[tileLocator(row+r, col+c)].getSymbol()=='0') {
												tiles[tileLocator(row+r, col+c)].setSymbol(' ');
											}
										}
									}
										
								}
							}
						}
						
						else {
							
							if (tiles[tileLocator(row, col)].getIsBomb() == false && tiles[tileLocator(row,col)].getSymbol() != 'F'  ) {
								if (numOfNeighbourBombs != 0){
									tiles[tileLocator(row, col)].setSymbol(numOfNeighbourBombs);
								}
							}
						}
						
						
					}
				}
			}
		}
		
		
		
	}
	
	
	
	public void checkWinner() {
		winner = true;
		
		for (int num: bombList) {
			if (tiles[num].getIsRevealed() == true){
				winner = false;
			}
		}
		
		for (Tile tile: tiles) {
			if (tile.getIsRevealed() == false && tile.getIsBomb() == false) {
				winner = false;
			}
		}
		
		if (winner == true) {
			completed = true;

			
			party = true;
		}
	}
	
	
	
	public void youDead(){
		
	
		
		for (int num: bombList) {
			tiles[num].setBackground(Color.red);
		}
		
	}
	
	
	public void mousePressed(MouseEvent e) {
		if (boomboomboom == false){
			if ((e.getModifiers() & InputEvent.BUTTON1_MASK) != 0) {
				 
				for (Tile tile: tiles) {
					if (e.getSource() == tile) {
						reveal(tile); 
					}
				 }
				 
				 
			}
			 
			 
			else if ((e.getModifiers() & InputEvent.BUTTON3_MASK) != 0) {

				for (Tile tile: tiles) {
					if (e.getSource() == tile) {
						 flag(tile);
					}
				}
			}
		}
	}
	
    public void mouseClicked(MouseEvent e) {  
    	 //System.out.println("Mouse Clicked");  
    }  
    public void mouseEntered(MouseEvent e) {  
    	 //System.out.println("Mouse Entered");  
    }  
    public void mouseExited(MouseEvent e) {  
    	 //System.out.println("Mouse Exited");  
    }  

    public void mouseReleased(MouseEvent e) {  
    	 //System.out.println("Mouse Released");  
    }
	
    
    public void flag(Tile tile){
    	if (tile.getIsRevealed() == false) {
	    	if (tile.getSymbol() == 'F') {
				tile.setSymbol(' ');
				tile.setBackground(Color.gray);
			}
			else {
				tile.setSymbol('F');
				tile.setBackground(Color.yellow);
			}
    	}
    }
    
    public void reveal(Tile tile) {

    	
    	if ((tile.getSymbol() != 'F') == true) {
	    	if (tile.getIsBomb() == true) {
				tile.setBackground(Color.RED);
				tile.setIsRevealed(true);
				youDead();
				boomboomboom = true;
			}
			
			else {
				//tile.setSymbol('C');
				tile.setIsRevealed(true);
				checkNeighbourTiles();

				
				
			}
    	}
    	
    	else {

    	}
    }
    
	public void actionPerformed(ActionEvent e) {
		
		if (boomboomboom == false){

			
			if (e.getSource() == partyButton) {
				if (party == false) {
					party = true;
				}
				else {
					party = false;
				}
			}
				
		
		
			if (e.getSource() == myTimer) {
				
				bombsFlagged = 0;
				for (Tile tile:tiles) {
					if (tile.getSymbol() == 'F') {
						bombsFlagged +=1;
					}
					if ((tile.getIsRevealed() == true || tile.getSymbol() == 'F') && winner == false){
						if (startTime==0) {
							startTime = System.currentTimeMillis();
						}
						
						time = Math.round((System.currentTimeMillis()-startTime)/1000);
					}
				}
				bombLabel.setText(zfill((numOfBombs-bombsFlagged),3));
				timeLabel.setText(time +"");

				
				if (party == true) {
					
					for (Tile tile: tiles) {
						tile.setBackground(colourList[rand.nextInt((colourList.length))]);
					}
				}
				else {
					for (Tile tile: tiles) {
						if (tile.getIsRevealed()){
							tile.setBackground(Color.GREEN);
						}
						
						else if (tile.getSymbol() == 'F') {
							tile.setBackground(Color.YELLOW);
						}
						
						else {
							tile.setBackground(Color.GRAY);
						}
					}
				}
			}
		
		}
		
		
		if (e.getSource() == resetButton) {
			this.reset();
			
		}	
		
		checkWinner();
		
	}
}
