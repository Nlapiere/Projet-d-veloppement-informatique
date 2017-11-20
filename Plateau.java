package dames;
/**
 * Création de la classe Plateau réprésentant le plateau du jeu
 */
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * @author Nicolas Lapière 2TL2
 * La classe hérite de la classe JFrame
 */

public class Plateau extends JFrame {
	/**
	 * Déclaration des variables
	 */
	private static Pion[] tableauPion = new Pion[20];	//tableau d'objet Pion 
	private static BoutonSelect[] tableauSelect = new BoutonSelect[2];	//Déplacement du pion
	private static JPanel pan = new PanelPlateau();	//Création d'un panel pan
	
/**
 * Récupérer un pion du tableau
 * @param i : le numéro de la case
 * @return la case indiquée
 */
	public static Pion getPion(int i) {
		return tableauPion[i];
	}
	/**
	 * Méthode permettant de récupérer le tableau des boutons Select
	 * @return un tableau tous les boutons select
	 */
	public BoutonSelect[] getTableauSelect() {
		return tableauSelect;
	}
	/**
	 * Associer le numéro d'une case du tableau tableauSelect à un bouton 
	 * @param bouton : la case
	 * @param i : le numéro de la case
	 */
	public static void setTableauSelect(BoutonSelect bouton, int i) {
		tableauSelect[i] = bouton;
	}
/**
 * Création d'un constructeur qui initialise le plateau de jeu
 */
	public Plateau() {		

		//Instanciation
		this.setTitle("Projet Dames");
		this.setSize(1000, 1030);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setAlwaysOnTop(true);
		this.setUndecorated(false);
		//On utilise le JPanel "pan" comme content pane
		this.setContentPane(pan);
		pan.setLayout(null);
		this.setLayout(null);
		initPions();
		for(int i = 0; i <20;i++){
			System.out.println(tableauPion[i].isEstSelect());
			System.out.println(tableauPion[i].getX());
			System.out.println(tableauPion[i].getY());
		}
		this.setVisible(true);
		
	}
	/**
	 * Méthode permettant de placer et d'initialiser les pions sur le plateau de jeu
	 */
	public void initPions() {
		int compt = 0;	//on initialise un compteur à 0
		for (int i=0;i<20;i++) {
			tableauPion[i]= new Pion();
		}
		for (int i=0;i<5;i++) {
			compt = 0;
			tableauPion[i].setBounds(((i*200)+100)+10, 10, 80, 80);
			tableauPion[i].setX(((i*200)+110));
			tableauPion[i].setY(10);
			tableauPion[i].setEstNoir(true);
			pan.add(tableauPion[i]);
			compt++;
		}
		compt = 0;
		for (int i=5; i<10; i++) {
			tableauPion[i].setBounds((compt*200)+10, 110, 80, 80);
			tableauPion[i].setX((compt*200)+10);
			tableauPion[i].setY(110);
			tableauPion[i].setEstNoir(true);
			pan.add(tableauPion[i]);
			compt++;
		}
		compt = 0;
		for (int i=10; i<15; i++) {
			tableauPion[i].setBounds((compt*200)+110, 210, 80, 80);
			tableauPion[i].setX((compt*200)+110);
			tableauPion[i].setY(210);
			tableauPion[i].setEstNoir(true);
			pan.add(tableauPion[i]);
			compt++;
		}
		compt = 0;
		for (int i=15; i<20; i++) {
			tableauPion[i].setBounds((compt*200)+10, 310, 80, 80);
			tableauPion[i].setX((compt*200)+10);
			tableauPion[i].setY(310);
			tableauPion[i].setEstNoir(false);
			pan.add(tableauPion[i]);
			compt++;
		}
		compt = 0;

	}
	/**
	 * Méthode qui affiche les cases du tableauSelect
	 */
	public static void addSelect() {
		for(int i = 0; i < 2;i++) {
			pan.add(tableauSelect[i]);
			
			pan.repaint();
			pan.revalidate();
		}
	}
	/**
	 * Méthode qui permet d'actualiser la position des pions sur le plateau
	 */
	public static void actualisePions() {
		for(int i = 0; i < 20; i++) {
			tableauPion[i].setBounds(tableauPion[i].getX(), tableauPion[i].getY(), 80, 80);
			
			pan.repaint();
			pan.revalidate();
			
		}
		
		pan.remove(tableauSelect[0]);
		pan.remove(tableauSelect[1]);
	}
}
