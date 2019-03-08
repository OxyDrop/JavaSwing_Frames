package dialogue;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Hello {
	public static void main(String[] args){
		fen fen = new fen();
		fen.setVisible(true);
	}
}
class fen extends JFrame{
	public fen(){
		setTitle("Hello");
		setSize(300,150);
		JOptionPane.showMessageDialog(this,"Hello");
	}
}
