import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GamePanelChess extends JPanel implements ActionListener, MouseListener{

	private int width = 8;
	private int height = 8;
	private int numOfTiles = width * height;
	private Tile[] tiles = new Tile[numOfTiles];
	private JLabel turnLabel;
	private JButton resetButton;
	private char turn = 'W';  // 'W' or 'B'
	private int wwidth = 800;
	private int wheight = 800;
	private Tile selectedTile = null;
	private Tile lastMovedPiece = null;
	
	public GamePanelChess() {
		
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
			 tiles[i].addMouseListener(this);
			 tiles[i].setBackground(Color.gray);
			 buttonPanel.setBackground(Color.BLACK);
			 tiles[i].setFont(new Font("Forte", Font.PLAIN, wwidth/12));
		
		 }
		 this.add(buttonPanel, BorderLayout.CENTER);
		 this.add(topPanel, BorderLayout.NORTH);
		 
		 for (int i=0; i<10; i++) {
			 tiles[i].setSymbol('H');
		 }
		 
		 
	}
	
	
	
	public char getTurn() {
		return turn;
	}
	
	public int tileLocator(int r, int c) {
		
		int loc;
		loc = (r)*width + (c);
		
		return loc;
	}
		
	
	public void reset() {
		
		for (Tile tile: tiles) {
			tile.setSymbol(' ');
			tile.setBackground(Color.gray);
		}
		
		turn = 'O';
		turnLabel.setText("Turn: " + turn);
	}
	
	
	public void setSelectedTile(Tile tile) {
		if (selectedTile != null) {
			selectedTile.setBackground(Color.GRAY);
		}
		if (lastMovedPiece != null) {
			lastMovedPiece.setBackground(Color.GRAY);
		}
		tile.setIsSelected(true);
		tile.setBackground(Color.yellow);
		selectedTile = tile;
	}
	
	public void moveSelectedTo(Tile tile) {
		tile.setSymbol(selectedTile.getSymbol());
		tile.setBackground(Color.red);
		selectedTile.setSymbol(' ');
		selectedTile.setBackground(Color.gray);
		selectedTile = null;
		lastMovedPiece = tile;
			
	}
	
	public void removeSelectedTile() {
		selectedTile.setBackground(Color.GRAY);
		selectedTile = null;
	}
	
	
	public void mousePressed(MouseEvent e) {
			if ((e.getModifiers() & InputEvent.BUTTON1_MASK) != 0) {
				for (Tile tile: tiles) {
					if (e.getSource() == tile) {
						if (selectedTile == null){
							if (tile.getSymbol() != ' ') {
								setSelectedTile(tile);			
							}
						}
						
						else {
							if (tile.getSymbol() == ' ') {
								moveSelectedTo(tile);
							}
							
							else {
								removeSelectedTile();
							}
						}
					}
				}
				
				 
			}
			 
			//else if ((e.getModifiers() & InputEvent.BUTTON3_MASK) != 0) {
			//}
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
	
	
	
	
	
	public void actionPerformed(ActionEvent e) {
		
		for (Tile tile: tiles) {
			if(e.getSource() == tile) {					
						
					
			}
		}
		
		
		if (e.getSource() == resetButton) {
			this.reset();
		}	
		
	}
}
