package dames;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PanelPlateau extends JPanel {
	public void paintComponent(Graphics g) {
		//Dessiner cases Noirs(1)
		for (int x = 0; x < 5; x ++) {
			for (int y = 0; y <10; y++) {
				g.fillRect(((x*2)+1)*100, (y*2)*100, 100, 100);	
			}
		}
		//Dessiner cases Noirs(2)
		for (int x = 0; x < 5; x ++) {
			for (int y = 0; y < 10; y++) {
				g.fillRect((x*2)*100, ((y*2)+1)*100, 100, 100);	
			}
		}
		//On change la couleur pour dessiner les prochains rectangles en blanc
		g.setColor(Color.WHITE);
		//Dessiner cases blanches(1)
		for (int x = 0; x < 5; x ++) {
			for (int y = 0; y <10; y++) {
				g.fillRect(((x*2))*100, (y*2)*100, 100, 100);	
			}
		}

		//Dessiner cases blanches(2)
		for (int x = 0; x < 5; x ++) {
			for (int y = 0; y < 10; y++) {
				g.fillRect(((x*2)+1)*100, ((y*2)+1)*100, 100, 100);	
			}
		}
	}
}
