import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class MenuInterface  extends JPanel implements ActionListener{

	private JPanel gamePanel = new JPanel();
	private JButton noughtsStartButton;
	private JButton connectStartButton;
	private JButton chessStartButton;
	private JButton mineStartButton;
	private JLabel selectionLabel;
	private GameRunner game;
	private JButton returnToMenuButton;
	private boolean isMenu;
	private boolean isGame;
	
	MenuInterface(){
		
		isMenu = true;
		isGame = false;
		
		this.setLayout(new BorderLayout());
		
		gamePanel.setLayout(new GridLayout(4,1));
		
		
		returnToMenuButton = new JButton("Return");
		returnToMenuButton.setFont(new Font("Sans Serif",Font.BOLD,50));
		returnToMenuButton.setBackground(Color.BLACK);
		returnToMenuButton.setForeground(Color.WHITE);
		returnToMenuButton.setFocusable(false);
		returnToMenuButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		returnToMenuButton.addActionListener(this);
		
		this.add(returnToMenuButton, BorderLayout.NORTH);
		
		
		
		//selectionLabel = new JLabel("Sen le  ct: ", SwingConstants.CENTER);
		//selectionLabel.setBackground(Color.BLACK);
		//selectionLabel.setForeground(Color.WHITE);
		//selectionLabel.setFont(new Font("Sans Serif",Font.BOLD,50));
		//
		//topPanel.add(selectionLabel, BorderLayout.NORTH);
		
		
		
		
		noughtsStartButton = new JButton("Noughts & Crosses");
		noughtsStartButton.setFont(new Font("Sans Serif",Font.BOLD,50));
		noughtsStartButton.setBackground(Color.BLACK);
		noughtsStartButton.setForeground(Color.WHITE);
		noughtsStartButton.setFocusable(false);
		noughtsStartButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		noughtsStartButton.addActionListener(this);
		
		gamePanel.add(noughtsStartButton);
		
		
		
		connectStartButton = new JButton("Connect4");
		connectStartButton.setFont(new Font("Sans Serif",Font.BOLD,50));
		connectStartButton.setBackground(Color.BLACK);
		connectStartButton.setForeground(Color.WHITE);
		connectStartButton.setFocusable(false);
		connectStartButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		connectStartButton.addActionListener(this);
		
		gamePanel.add(connectStartButton);
		
		
		
		chessStartButton = new JButton("Chess (In Progress)");
		chessStartButton.setFont(new Font("Sans Serif",Font.BOLD,50));
		chessStartButton.setBackground(Color.BLACK);
		chessStartButton.setForeground(Color.WHITE);
		chessStartButton.setFocusable(false);
		chessStartButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		chessStartButton.addActionListener(this);
		
		gamePanel.add(chessStartButton);
		
		
		mineStartButton = new JButton("Mine Sweeper");
		mineStartButton.setFont(new Font("Sans Serif",Font.BOLD,50));
		mineStartButton.setBackground(Color.BLACK);
		mineStartButton.setForeground(Color.WHITE);
		mineStartButton.setFocusable(false);
		mineStartButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		mineStartButton.addActionListener(this);
		
		gamePanel.add(mineStartButton);
		
		
		this.add(gamePanel, BorderLayout.CENTER);
		
		//game = new GameRunner('N');
			
	}
	
	
	

//	public JButton getNoughtsJButton() {
//		return noughtsStartButton;
//	}
//	
//	public JButton getConnectJButton() {
//		return connectStartButton;
//	}
//	
//	public JButton getReturnButton() {
//		return returnToMenu;
//	}
	
	public GameRunner getNoughtsGame() {
		game = new GameRunner('N');
		return game;
	}
	
	public GameRunner getConnectGame() {
		game = new GameRunner('C');
		return game;
	}
	
	public GameRunner getChessGame() {
		game = new GameRunner('H');
		return game;
	}
	
	public GameRunner getMineGame() {
		game = new GameRunner('M');
		return game;
	}
	
	
	public void startGame(GameRunner game) {
		//gamePanel.remove(noughtsStartButton);
		//gamePanel.remove(connectStartButton);
		this.remove(gamePanel);
		this.add(game, BorderLayout.CENTER);
		//gamePanel.add(game);
		isMenu = false;
		isGame = true;
	}
	
	public void returnToMenu() {
		//gamePanel.remove(game);
		//gamePanel.add(noughtsStartButton);
		//gamePanel.add(connectStartButton);
		this.remove(game);
		game = null;
		this.add(gamePanel, BorderLayout.CENTER);
		isMenu = true;
		isGame = false;
		
	}
	
	
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == noughtsStartButton) {
			if (isMenu == true) {
				game = getNoughtsGame();
				startGame(game);
				SwingUtilities.updateComponentTreeUI(this);
			}
		}
		
		if (e.getSource() == connectStartButton) {
			if (isMenu == true) {
				game = getConnectGame();
				startGame(game);
				SwingUtilities.updateComponentTreeUI(this);
			}
		}
		
		if (e.getSource() == chessStartButton) {
			if (isMenu == true) {
				game = getChessGame();
				startGame(game);
				SwingUtilities.updateComponentTreeUI(this);
			}
		}
		
		
		if (e.getSource() == mineStartButton) {
			if (isMenu == true) {
				game = getMineGame();
				startGame(game);
				SwingUtilities.updateComponentTreeUI(this);
			}
		}
		
		
		
		if (e.getSource() == returnToMenuButton) {
			if (isGame == true) {
				returnToMenu();
				game = null;
				SwingUtilities.updateComponentTreeUI(this);
			}
		}
		
		
		
	}
	
		
	
}
