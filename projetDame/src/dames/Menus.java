package dames;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JButton;
/**
 * Classe cr�ant les boutons permettant d'affecter un �v�nement aux boutons sur la droite du plateau de jeu
 * @author Groupe 13
 *
 */
public class Menus extends JButton implements MouseListener{
	/**
	 * D�claration de variables de classe
	 */
	private String name;
	private Plateau plateau;
	
	/**
	 * Constructeur de la classe Menus comprenant 2 param�tres
	 * @param name le nom du bouton
	 * @param plateau le plateau de jeu
	 */
	public Menus(String name, Plateau plateau) {
		this.name = name;
		this.addMouseListener(this);
		this.plateau = plateau;
	}
	@Override
	/**
	 * On �tudie les possibilit�s de clique sur les boutons
	 */
	public void mouseClicked(MouseEvent arg0) {
		//si on clique sur le bouton Nouvelle Partie
		//on rend visible et actifs les boutons de r�seau et abandonner
		if(this.name == "Nouvelle Partie") {
			plateau.getL().setVisible(true);
			plateau.getL().setEnabled(true);
			
			plateau.getR().setVisible(true);
			plateau.getR().setEnabled(true);
			
			plateau.getA().setVisible(true);
			plateau.getA().setEnabled(true);
			
			plateau.getNp().setVisible(false);
			plateau.getNp().setEnabled(false);
			
			plateau.getQt().setVisible(false);
			plateau.getQt().setEnabled(false);
			
			plateau.repaint();
			plateau.revalidate();
			//on recr�e un plateau de jeu et on donne la possibilit� de jouer en r�seau en rendant visible les options de jeu en r�seau
		} else if (this.name == "Abandonner") {
			//si on clique sur Abandonner
			//on remet le plateau � z�ro
			plateau.reset();
			
			plateau.getNp().setEnabled(true);
			plateau.getNp().setVisible(true);
	
			plateau.getQt().setVisible(true);
			plateau.getQt().setEnabled(true);
			
			this.setVisible(false);
			this.setEnabled(false);
			
			plateau.repaint();
			plateau.revalidate();
			//on dit que le joueur adverse perd la partie
			//on remet les boutons Nouvelle Partie et quitter
		} else if (this.name == "Quitter") {
			//si on clique sur Quitter
			//si il s'agit d'un jeu en r�seau
			if(plateau.isMultijoueur()) {
				try {
					plateau.getDos().close();	//on ferme les entr�es de r�seau
					plateau.getDis().close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//sinon on ferme le plateau (on le cache) et on le quitte
			plateau.setVisible(false);
			plateau.dispose();
		}
		else if (this.name == "Local") {
			//si on clique sur une partie locale
			//le plateau se lance normalement et les pions se placent ensuite sur le plateau
			plateau.initPions();
			plateau.actualisePions();
			plateau.getPan().setVisible(true);
			
			plateau.getQt().setVisible(false);
			plateau.getQt().setEnabled(false);
			
			plateau.getR().setVisible(false);
			plateau.getR().setEnabled(false);
			
			plateau.getA().setVisible(false);
			plateau.getA().setEnabled(false);
			
			plateau.getAb().setVisible(true);
			plateau.getAb().setEnabled(true);
			
			this.setVisible(false);
			this.setEnabled(false);
			
			plateau.repaint();
			plateau.revalidate();
		} else if(this.name == "R�seau") {
			//si on lance une partie en r�seau
			plateau.getA().setVisible(true);
			plateau.getA().setEnabled(true);
			
			plateau.getPortField().setVisible(true);	//on r�cup�re le num�ro de port
			plateau.getPortField().setEnabled(true);
			
			plateau.getIpField().setVisible(true);	//on r�cup�re l'adresse IP
			plateau.getIpField().setEnabled(true);
			
			plateau.getCh().setVisible(true);
			plateau.getCh().setEnabled(true);
			
			plateau.getHo().setVisible(true);	//on r�cup�re l'h�te
			plateau.getHo().setEnabled(true);
			
			plateau.getL().setVisible(false);
			plateau.getL().setEnabled(false);
			
			this.setVisible(false);
			this.setEnabled(false);
			
		} else if(this.name == "Chercher") {
		//si on clique sur chercher un h�te
		//on tente une connexion TCP avec un h�te qui h�berge une partie gr�ce aux sockets
			
			try {
				plateau.connexion(InetAddress.getByName(plateau.getIpField().getText()), Integer.parseInt(plateau.getPortField().getText()));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		//si la connexion abouti, on lance un plateau de jeu
		plateau.initPions();
		plateau.actualisePions();
		plateau.getPan().setVisible(true);
		
		plateau.getAb().setVisible(true);
		plateau.getAb().setEnabled(true);
		
		plateau.getIpField().setVisible(false);
		plateau.getIpField().setEnabled(false);
		
		plateau.getPortField().setVisible(false);
		plateau.getPortField().setEnabled(false);
		
		plateau.getHo().setVisible(false);
		plateau.getHo().setEnabled(false);
		
		plateau.getCh().setVisible(false);
		plateau.getCh().setEnabled(false);
		
		plateau.getA().setVisible(false);
		plateau.getA().setEnabled(false);
		} else if(this.name == ("Host")){
			//si on h�berge une partie, on devient h�te
			//on lance un ServerSocket
			plateau.initialiseServeur(Integer.parseInt(plateau.getPortField().getText()));
			plateau.initPions();
			plateau.actualisePions();
			plateau.getPan().setVisible(true);
			
			plateau.getAb().setVisible(true);
			plateau.getAb().setEnabled(true);
			
			plateau.getIpField().setVisible(false);
			plateau.getIpField().setEnabled(false);
			
			plateau.getPortField().setVisible(false);
			plateau.getPortField().setEnabled(false);
			
			plateau.getHo().setVisible(false);
			plateau.getHo().setEnabled(false);
			
			plateau.getCh().setVisible(false);
			plateau.getCh().setEnabled(false);
			
			plateau.getA().setVisible(false);
			plateau.getA().setEnabled(false);
		}
		else if (this.name == "Annuler") {
			//si on annule, on revient au menu principal et on r�initialise tout
			plateau.initMenu();
			
			plateau.repaint();
			plateau.revalidate();
		}

	}
	/**
	 * M�thode r�cup�rant les �venements de la souris
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
