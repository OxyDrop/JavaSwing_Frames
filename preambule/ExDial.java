package preambule;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class ExDial {
	public static void main(String[] args){
		mafen fen = new mafen();
		fen.setVisible(true);
	}
}
class mafen extends JFrame implements ActionListener{
	static public final String[] color = {"rouge", "bleu", "gris", "vert", "jaune", "noir", "blanc", "orange"};
	static public final Color[] couleurs = {Color.red, Color.blue, Color.gray, Color.green, Color.yellow, Color.black, Color.white, Color.orange};
	private Panneaudessin panDes; 
	private JButton lanceDial; 
	private Dialogue dialogue; 
	private Infos infos;
	public mafen(){ //Constructeur de fenetre
		setTitle("FIGURES");
		setSize(600,300);
		Container contenu = getContentPane();
		/***INSTANCIATIONS***/
		lanceDial = new JButton("MODIFICATIONS");
		panDes = new Panneaudessin();
		panDes.setBackground(Color.white);
		/***AJOUT PANNEAU COMMANDE***/
		contenu.add(panDes); 
		contenu.add(lanceDial,"South");
		/***AJOUT ECOUTEUR EVENEMENT***/
		lanceDial.addActionListener(this);
	}
	public void actionPerformed(ActionEvent ev){
		if(dialogue==null){
			dialogue=new Dialogue(this); //nouvelle boite de dialogue
			infos=new Infos(); //nouvelle objet Info
		}
		/* Recup infos courantes dans infos */
		infos.largeur=panDes.getLargeur();
		infos.hauteur=panDes.getHauteur();
		infos.rectangle=panDes.getRectangle();
		infos.ovale=panDes.getOvale();
		infos.couleur=panDes.getNomCouleur();
		/* Lancement dialogue */
		dialogue.lanceDial(infos);
		panDes.setLargeur(infos.largeur);
		panDes.setHauteur(infos.hauteur);
		panDes.setRectangle(infos.rectangle);
		panDes.setOvale(infos.ovale);
		panDes.setCouleur(infos.couleur);
		panDes.repaint();
	}
}
class Dialogue extends JDialog implements ActionListener{
	private JButton okBouton, cancelBouton;
	private boolean ok = false;
	private JComboBox comb;
	private JTextField txtLargeur, txtHauteur;
	private JCheckBox cOvale, cRectangle;
	public Dialogue(JFrame parent){
		super(parent,"COULEURS, FORMES, TAILLES",true);
		setSize(420,100);
		//INITIALISATION
		Container contenu = getContentPane();
		contenu.setLayout(new FlowLayout());
		okBouton = new JButton("OK");
		cancelBouton = new JButton("Cancel");
		comb = new JComboBox(mafen.color);
		JLabel dim = new JLabel("DIMENSIONS");
		txtLargeur=new JTextField(5);
		txtHauteur=new JTextField(5);
		cOvale = new JCheckBox("Ovale");
		cRectangle = new JCheckBox("Rectangle");
		//AJOUT CONTENEUR
		contenu.add(comb);
		contenu.add(dim);
		contenu.add(txtLargeur);
		contenu.add(txtHauteur);
		contenu.add(cOvale);
		contenu.add(cRectangle);
		contenu.add(okBouton);
		contenu.add(cancelBouton);
		//AJOUT ECOUTEUR EVENEMENT
		okBouton.addActionListener(this);  
		cancelBouton.addActionListener(this);
	}
	public void lanceDial(Infos infos){
		/* Placer infos dans controles */
		txtLargeur.setText(""+infos.largeur);
		txtHauteur.setText(""+infos.hauteur);
		cOvale.setSelected(infos.ovale);
		cRectangle.setSelected(infos.rectangle);
		comb.setSelectedItem(infos.couleur);
		/* Lancer le dialogue */
		ok = false;
		setVisible(true);
		/* Si Ok on recupère les infos du dialogue */
		if(ok){
			infos.largeur=Integer.parseInt(txtLargeur.getText());
			infos.hauteur=Integer.parseInt(txtHauteur.getText());
			infos.rectangle=cRectangle.isSelected();
			infos.ovale=cOvale.isSelected();
			infos.couleur=(String)comb.getSelectedItem();
		}
	}
	public void actionPerformed(ActionEvent ev){
		if (ev.getSource()==okBouton){
			ok = true;
			setVisible(false);
		}else
			setVisible(false);
	}
}
class Infos{
	public boolean ovale,rectangle;
	public int largeur, hauteur;
	public String couleur;
}
class Panneaudessin extends JPanel{
	private boolean rectangle = false, ovale = false;
	private int largeur=50,hauteur=50;
	private String nomCouleur = mafen.color[0];
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(ovale) g.drawOval(10,10,10+largeur,10+hauteur);
		if(rectangle) g.drawRect(10,10,10+largeur,10+hauteur);
	}
	//ACCESSEURS ET MODIFICATEURS
	public void setRectangle(boolean b){rectangle=b;}
	public boolean getRectangle(){return rectangle;}
	public void setOvale(boolean b){ovale=b;}
	public boolean getOvale(){return ovale;}
	public void setLargeur(int l){largeur=l;}
	public int getLargeur(){return largeur;}
	public void setHauteur(int h){hauteur=h;}
	public int getHauteur(){return hauteur;}
	public void setCouleur(String c){
		for(int i=0;i<mafen.color.length;i++)
			if(c.equals(mafen.color[i])) setBackground(mafen.couleurs[i]);
		nomCouleur = c;
	}
	public String getNomCouleur(){return nomCouleur;}
}
