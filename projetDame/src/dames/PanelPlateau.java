package dames;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PanelPlateau extends JPanel {
	Plateau plateau;
	
	public PanelPlateau(Plateau plateau) {
		this.plateau = plateau;
	}
	public void paintComponent(Graphics g) {
		int larg = (plateau.getPan().getSize().width/2) - (plateau.getPan().getSize().height/2);
		int size = plateau.getPan().getHeight()/10;
		//Dessiner fond
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, plateau.getPan().getSize().width, plateau.getPan().getSize().height);
		//Dessiner cases Noirs(1)
		g.setColor(Color.BLACK);
		for (int x = 0; x < 5; x ++) {
			for (int y = 0; y < 5; y++) {
				g.fillRect(larg+((x*2)+1)*size, (y*2)*size, size, size);	
			}
		}
		//Dessiner cases Noirs(2)
		for (int x = 0; x < 5; x ++) {
			for (int y = 0; y < 5; y++) {
				g.fillRect(larg+(x*2)*size, ((y*2)+1)*size, size, size);	
			}
		}
		//On change la couleur pour dessiner les prochains rectangles en blanc
		g.setColor(Color.WHITE);
		//Dessiner cases blanches(1)
		for (int x = 0; x < 5; x ++) {
			for (int y = 0; y < 5; y++) {
				g.fillRect(larg+((x*2))*size, (y*2)*size, size, size);	
			}
		}

		//Dessiner cases blanches(2)
		for (int x = 0; x < 5; x ++) {
			for (int y = 0; y < 5; y++) {
				g.fillRect(larg+((x*2)+1)*size, ((y*2)+1)*size, size, size);	
			}
		}
	}
}
