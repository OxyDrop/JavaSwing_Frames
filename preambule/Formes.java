package preambule;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
public class Formes {
	public static void main(String[] args){
		Mafenetre fen = new Mafenetre();
		fen.setVisible(true);
	}
}
class Mafenetre extends JFrame implements ActionListener,ItemListener,FocusListener{
	static public final String[] color = {"rouge", "bleu", "gris", "vert", "jaune", "noir", "blanc", "orange"};
	static public final Color[] couleurs = {Color.red, Color.blue, Color.gray, Color.green, Color.yellow, Color.black, Color.white, Color.orange};
	private PanneauDessin panDes;
	private JPanel panCom;
	private JComboBox comb;
	private JTextField txtLargeur, txtHauteur;
	private JCheckBox cOvale, cRectangle;
	public Mafenetre(){
		setTitle("FIGURES");
		setSize(600,300);
		Container contenu = getContentPane();
		/***INSTANCIATIONS***/
		panCom = new JPanel();
		panDes = new PanneauDessin();
		panDes.setBackground(Color.cyan);
		comb = new JComboBox(color);
		JLabel dim = new JLabel("DIMENSIONS");
		txtLargeur = new JTextField("50",5);
		txtHauteur = new JTextField("20",5);
		cOvale = new JCheckBox("Ovale");
		cRectangle = new JCheckBox("Rectangle");
		/***AJOUT PANNEAU COMMANDE***/
		panCom.add(comb);
		panCom.add(dim);
		panCom.add(txtLargeur);
		panCom.add(txtHauteur);
		panCom.add(cOvale);
		panCom.add(cRectangle);
		contenu.add(panCom,"South");
		contenu.add(panDes);
		/***AJOUT ECOUTEUR EVENEMENT***/
		comb.addItemListener(this);
		txtLargeur.addActionListener(this);
		txtLargeur.addFocusListener(this);
		txtHauteur.addActionListener(this);
		txtHauteur.addFocusListener(this);
		cOvale.addActionListener(this);
		cRectangle.addActionListener(this);
	}
	public void actionPerformed(ActionEvent ev){
		if(ev.getSource()==txtLargeur) setLargeur();
		if(ev.getSource()==txtHauteur) setHauteur();
		if(ev.getSource()==cOvale) panDes.setOvale(cOvale.isSelected());
		if(ev.getSource()==cRectangle) panDes.setRectangle(cRectangle.isSelected());
		panDes.repaint();	
	}
	public void focusLost(FocusEvent ev){
		if(ev.getSource()==txtLargeur){
			setLargeur();
			System.out.println("perte focus largeur");
			panDes.repaint();
		}
		if(ev.getSource()==txtHauteur){
			setHauteur();
			panDes.repaint();
		}
	}
	public void focusGained(FocusEvent ev){}
	private void setHauteur(){
		String ch = txtHauteur.getText();
		System.out.println("hauteur "+ch);
		panDes.setHauteur(Integer.parseInt(ch));
	}
	private void setLargeur(){
		String ch = txtLargeur.getText();
		System.out.println("largeur "+ch);
		panDes.setLargeur(Integer.parseInt(ch));
	}
	public void itemStateChanged(ItemEvent ev){
		String couleur = (String)comb.getSelectedItem();
		panDes.setCouleur(couleur);
	}
}
class PanneauDessin extends JPanel{
	private boolean rectangle = false, ovale = false;
	private int largeur=50,hauteur=50;
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(ovale) g.drawOval(10,10,10+largeur,10+hauteur);
		if(rectangle) g.drawRect(10,10,10+largeur,10+hauteur);
	}
	public void setRectangle(boolean b){rectangle=b;}
	public void setOvale(boolean b){ovale=b;}
	public void setLargeur(int l){largeur=l;}
	public void setHauteur(int h){hauteur=h;}
	public void setCouleur(String c){
		for(int i=0;i<Mafenetre.color.length;i++)
			if(c.equals(Mafenetre.color[i])) setBackground(Mafenetre.couleurs[i]);
	}
}
