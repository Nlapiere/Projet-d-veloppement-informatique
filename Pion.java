package dames;
/**
 * @author Thomas Feyereisen 2TL2
 * Création de la classe Pion représentant un pion du jeu
 */
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JButton;
import java.io.File;
import java.awt.Image;
import java.io.IOException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.imageio.ImageIO;
import java.awt.Dimension;
/**
 * La classe Pion hérite de la classe JButton
 * On implémente l'interface MouseListener
 */
public class Pion extends JButton implements MouseListener{
	/**
	 * Déclaration des variables de classe
	 */
	private int x;
	private int y;
	private boolean estSelect = false;
	private boolean estBloque;
	private boolean estNoir;
	private Image img;
	/**
	 * Création d'un constructeur Pion permettant d'initialiser un pion
	 * On lui attribue l'image "thomasPion.jpg"
	 */
	public Pion() {
		//Génération du graphisme du pion
		try {
			img = ImageIO.read(new File("thomasPion.jpg"));	//attribution de l'image sur le pion
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setPreferredSize(new Dimension(100, 100));	//dimension du pion
		this.addMouseListener(this);

	}
	//Méthodes appelées par la souris
	@Override
		public void mouseClicked(MouseEvent arg0) {
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {

		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {

		
	}
	/**
	 * Création d'une méthode permettant d'attribuer une fonction au clic de la souris
	 */
	@Override
	public void mousePressed(MouseEvent event) {
		for(int i = 1; i < 20; i++) {
			if(Plateau.getPion(i).estSelect) {
				Plateau.getPion(i).estSelect = false;
				Plateau.actualisePions();
			}
		}
		this.setEstSelect(true);
		
	}
	/**
	 * Méthode permettant d'attribuer une fonction au relachement du clic de la souris
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		int x;	//déclaration de variable
		int y;	//déclaration de variable
		
		for(int i=0;i<20;i++) {
			if(Plateau.getPion(i).estSelect)
			{
				x = (Plateau.getPion(i).x)-10;
				y = (Plateau.getPion(i).y)-10;
				Plateau.setTableauSelect(new BoutonSelect((x+100), (y+100)), 0);				
				Plateau.setTableauSelect(new BoutonSelect((x-100), (y+100)), 1);
				Plateau.addSelect();
			}
		}
	}
	/**
	 * Méthode pour changer l'image source du pion
	 */
	//TODO ajouter une variante pion blanc/noir
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}
	/**
	 * Récupérer la variable x
	 */
	public int getX() {
		return x;
	}
	/**
	 * Associer une valeur à x
	 * @param x : la coordonnée horizontale
	 */

	public void setX(int x) {
		this.x = x;
	}
	

	public int getY() {
		return y;
	}
	/**
	 * Associer une valeur à y
	 * @param y : la coordonnée verticale
	 */

	public void setY(int y) {
		this.y = y;
	}
	/**
	 * Récupérer la variable estSelect
	 */

	public boolean isEstSelect() {
		return estSelect;
	}
	/**
	 * Associer une valeur à estSelect
	 * @param estSelect : l'état de séléction d'un pion
	 */

	public void setEstSelect(boolean estSelect) {
		this.estSelect = estSelect;
	}
	/**
	 * Récupérer la variable estBloque
	 */

	public boolean isEstBloque() {
		return estBloque;
	}
	/**
	 * Associer une valeur à estBloque
	 * @param estBloque : l'état de blocage d'un pion
	 */

	public void setEstBloque(boolean estBloque) {
		this.estBloque = estBloque;
	}
	/**
	 * Récupérer la variable estNoir
	 */

	public boolean isEstNoir() {
		return estNoir;
	}
	/**
	 * Associer une valeur à estNoir
	 * @param estNoir : l'état de couleur d'un pion
	 */

	public void setEstNoir(boolean estNoir) {
		this.estNoir = estNoir;
	}
}

	