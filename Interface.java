//import java.awt.event.*;
//import javax.swing.*;
//import java.awt.*;
//
//
//public class Interface extends JPanel implements ActionListener{
//
//	private GamePanel game;
//	private GamePanelConnect4 connect4 = new GamePanelConnect4();
//	private JPanel topPanel = new JPanel();
//	private JButton noughtsStartButton;
//	private JLabel selectionLabel;
//	
//	
//	
//	public Interface() {
//		this.setLayout(new BorderLayout());
//
//	}
//		
//	public void startNoughts() {
//		//this.setLayout(new BorderLayout());
//		this.remove(topPanel);
//		//this.removeAll();
//
//		game = new GamePanel();
//		this.add(game, BorderLayout.CENTER);
//		System.out.println("WWWWWWWWWWWWW");
//		
//	}
//	
//	
//	
//	public void resetToMenu() {
//		
//		selectionLabel = new JLabel("Sel  ect: ", SwingConstants.CENTER);
//		selectionLabel.setBackground(Color.BLACK);
//		selectionLabel.setForeground(Color.WHITE);
//		selectionLabel.setFont(new Font("Sans Serif",Font.BOLD,50));
//		
//		topPanel.add(selectionLabel);
//		
//		
//		noughtsStartButton = new JButton("Noughts & Crosses");
//		noughtsStartButton.setFont(new Font("Sans Serif",Font.BOLD,50));
//		noughtsStartButton.setBackground(Color.BLACK);
//		noughtsStartButton.setForeground(Color.WHITE);
//		noughtsStartButton.setFocusable(false);
//		noughtsStartButton.setBorder(null);
//		noughtsStartButton.addActionListener(this);
//		
//		topPanel.add(noughtsStartButton);
//		//topPanel.setBackground(Color.BLACK);
//		
//		this.add(topPanel, BorderLayout.CENTER);
//	}
//	
//	
//	
//	
//	
//	public void actionPerformed(ActionEvent e) {
//		
//		if (e.getSource() == noughtsStartButton) {
//			startNoughts();
//			
//		}
//		
//		
//	}
//	
//	
//}
//
//
//


























