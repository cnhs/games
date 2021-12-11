import java.awt.*;

import javax.swing.*;

public class Tile extends JButton{
	
	private char symbol;
	private boolean isBomb;
	private boolean isRevealed;
	private boolean isSelected;
	
	
	Tile(char s){
		this.symbol = s;
		this.setText(""+symbol);
		this.setFocusable(false);
		this.setBorder(null);
		this.setFont(new Font("Forte", Font.PLAIN, 60));
		this.setForeground(Color.BLACK);
		this.setBackground(Color.red);
		//this.setContentAreaFilled(false);
		
	}
	
	public void setSymbol(char setter) {
		symbol = setter;
		this.setText(""+symbol);
	}
	
	public void setSymbol(int setter) {
		symbol = (char)(setter + '0');
		this.setText(""+symbol);
	}
	
	
	public char getSymbol() {
		return symbol;
	}
	
	public void clear() {
		symbol = ' ';
		this.setText("" +symbol);
	}
	
	public void setIsBomb(boolean isBomb) {
		this.isBomb = isBomb;
	}
	
	public boolean getIsBomb() {
		return this.isBomb;
	}
	
	public void setIsRevealed(boolean isRevealed) {
		this.isRevealed = isRevealed;
	}
	
	public boolean getIsRevealed() {
		return this.isRevealed;
	}
	
	public void setIsSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	
	public boolean getIsSelected() {
		return this.isSelected;
	}
	
	
	

}
