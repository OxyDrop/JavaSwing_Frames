package dialogue;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Option {
	public static void main(String[] args){
		Fen fen = new Fen();
		fen.setVisible(true);
	}
}
class Fen extends JFrame implements ActionListener{
	static public final String[] color = {"rouge", "bleu", "gris", "vert", "jaune", "noir", "blanc", "orange"};
	public Fen(){
		setTitle("Boite d'option");
		setSize(300,200);
		JButton saisie = new JButton("CHOIX");
		getContentPane().add(saisie,"South");
		saisie.addActionListener(this);
	}
	public void actionPerformed(ActionEvent ev){
		System.out.println("** affichage boite d'options");
		String txt = (String)JOptionPane.showInputDialog
		(this,"Choisissez une couleur", "BOITE D'OPTION", JOptionPane.QUESTION_MESSAGE,null,color,color[0]);
		if(txt==null)
			System.out.println("Pas de choix effectué");
		else
			System.out.println("Option choisie : "+txt);
	}
}
