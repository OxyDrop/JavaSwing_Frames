package dialogue;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class TexteDialog {
	public static void main(String[] args){
		MaFen fen = new MaFen();
		fen.setVisible(true);
	}
}
class MaFen extends JFrame implements ActionListener{
	private JButton lancement;
	private String texte;
	public MaFen(){
		setTitle("Essai boite de dialogue");
		setSize(300,200);
		lancement = new JButton("Lancement dialogue");
		getContentPane().add(lancement,"North");
		lancement.addActionListener(this);
	}
	public void actionPerformed(ActionEvent ev){
		MonDialog md = new MonDialog(this);
		texte = md.lanceDialog();
		if(texte!=null)
			System.out.println("Vous avez écrit : "+texte);
		else
			System.out.println("dialogue abandonné");
		md.dispose();
	}
}
class MonDialog extends JDialog implements ActionListener{
	private JButton okay;
	private JTextField champ;
	private boolean ok;
	public MonDialog(JFrame proprio){
		super(proprio,"dialogue de saisie",true);
		setSize(250,100);
		okay = new JButton("OK");
		champ = new JTextField(20);
		okay.addActionListener(this);
		getContentPane().setLayout(new FlowLayout());
		getContentPane().add(okay);
		getContentPane().add(champ);
		
	}
	public void actionPerformed(ActionEvent ev){
		if(ev.getSource()==okay){
			ok=true;
			setVisible(false);
		}
	}
	public String lanceDialog(){
		ok=false;
		setVisible(true);
		if(ok) return champ.getText();
		else return null;
	}
}