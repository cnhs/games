import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;

public class Runner extends JFrame{

	
	
	public static void main(String[] args) {
		
		JFrame window = new JFrame(); 
		
		MenuInterface menu = new MenuInterface();
		
		menu = new MenuInterface();
		
		window.setContentPane(menu);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(1000,1000);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		window.setResizable(false);
	}

}
