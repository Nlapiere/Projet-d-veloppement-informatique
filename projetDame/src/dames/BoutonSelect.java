package dames;

import javax.swing.JButton;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.awt.Color;
import java.awt.Dimension;

public class BoutonSelect extends JButton implements MouseListener{
	
	private int x;
	private int y;
	private boolean estSelect;
	private boolean prise = false;
	private boolean valide;
	private Pion victime;
	private Plateau plateau;
	private int size;
	
	public BoutonSelect(int x, int y, Plateau plateau) {
		
		this.plateau = plateau;
		this.size = plateau.getPan().getHeight()/10;
		this.x = x;
		this.y = y;
		this.setBounds(x, y, size, size);
		//Génération du graphisme du Select
		this.setBackground(Color.cyan);
		this.setPreferredSize(new Dimension(size, size));
		//Implémentation d'un mouse listener
		this.addMouseListener(this);
	}
	//Le deuxième constructeur reçoit un pion à supprimer
	public BoutonSelect(int x, int y, Pion victime, Plateau plateau) {
		
		this.plateau = plateau;
		this.size = plateau.getPan().getHeight()/10;
		this.victime = victime;
		this.x = x;
		this.y = y;
		this.setBounds(x, y, size, size);
		//Génération du graphisme du Select
		this.setBackground(Color.cyan);
		this.setPreferredSize(new Dimension(size, size));
		//Implémentation d'un mouse listener
		this.addMouseListener(this);
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isEstSelect() {
		return estSelect;
	}

	public void setEstSelect(boolean estSelect) {
		this.estSelect = estSelect;
	}
	
	public boolean isPrise() {
		return prise;
	}

	public void setPrise(boolean prise) {
		this.prise = prise;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//TOUR BLANC
			for(Pion b : plateau.getPionsBlancs()) {
				if(b.isEstSelect()) {
					b.setEstSelect(false);
					b.setX(this.x);
					b.setY(this.y);
					if(!this.prise) {
						//On annule le booléen "estMort" car il n'y aura pas de prise à l'actualisation du plateau
						for(Pion n : plateau.getPionsNoirs()) {
							n.setEstMort(false);
						}
						//On actualise le plateau
						plateau.actualisePions();
						plateau.multiUpdateTmp();
						//On vide le tableauSelect pour le libérer
						for(int s = 0;s<20;s++) {
							plateau.setSelectToNull(s);
						}
						//Le tour passe à l'adversaire, on désacitve nos pions et on active les siens
						plateau.setTourBlanc(!plateau.isTourBlanc());
						if(!plateau.isMultijoueur()) {
							for(Pion i : plateau.getPionsBlancs()) {
								i.setEnabled(false);	
							}
							for(Pion i : plateau.getPionsNoirs()) {
								i.setEnabled(true);
							}
						}
						
					} else if(this.prise) {
						//On signale le pion victime comme étant mort
						plateau.getPionNoir(victime).setEstMort(true);
					
						//Gestion de la multi prise, on désactive tous les pions que pion PION[i]
						for(Pion i : plateau.getPionsBlancs()) {
							i.setEnabled(false);
						}
						//On refait jouer un tour à PION[i] en stipulant que nous sommes en multiprise
						plateau.actualisePions();
						plateau.multiUpdateTmp();
						for(int s = 0;s<20;s++) {
							plateau.setSelectToNull(s);
						}
						b.setMulti(true);
						b.jouerTour();
						//Si PION[i] n'est pas en mesure de rejouer son tour, on passe au joueur suivant
						if(plateau.getTableauSelect(0) == null && plateau.getTableauSelect(1) == null && plateau.getTableauSelect(2) == null && plateau.getTableauSelect(3) == null) {
							if(!plateau.isMultijoueur()) {
								plateau.setTourBlanc(!plateau.isTourBlanc());
								for(Pion i : plateau.getPionsBlancs()) {
									i.setEnabled(false);	
								}
								for(Pion i : plateau.getPionsNoirs()) {
									i.setEnabled(true);
								}
							} else {
								for(Pion i : plateau.getPionsBlancs()) {
									i.setEnabled(true);	
								}
							}
							
						}
						b.setMulti(false);
					}
		
				}
		
				
			}
			
	
		
	//TOUR NOIR
			for(Pion n : plateau.getPionsNoirs()) {
				if(n.isEstSelect()) {
					n.setEstSelect(false);
					n.setX(this.x);
					n.setY(this.y);
					if(!this.prise) {
						//On annule le booléen "estMort" car il n'y aura pas de prise à l'actualisation du plateau
						for(Pion b : plateau.getPionsBlancs()) {
							b.setEstMort(false);
						}
						//On actualise le plateau
						plateau.actualisePions();
						plateau.multiUpdateTmp();
						//On vide le tableauSelect pour le libérer
						for(int s = 0;s<20;s++) {
							plateau.setSelectToNull(s);
						}
						//Le tour passe à l'adversaire, on désacitve nos pions et on active les siens
						plateau.setTourBlanc(!plateau.isTourBlanc());
						//Activation Pions Blancs
						if(!plateau.isMultijoueur()) {
							for(Pion i : plateau.getPionsBlancs()) {
								i.setEnabled(true);	
							}
							//Activation Pions Noirs
							for(Pion i : plateau.getPionsNoirs()) {
								i.setEnabled(false);
							}
						}
						
					} else if(this.prise) {
						//On signale le pion victime comme étant mort
						plateau.getPionBlanc(victime).setEstMort(true);
						//Gestion de la multi prise, on désactive tous les pions que pion PION[i]
						for(Pion i : plateau.getPionsNoirs()) {
							i.setEnabled(false);
						}
						//On refait jouer un tour à PION[i] en stipulant que nous sommes en multiprise
						plateau.actualisePions();
						plateau.multiUpdateTmp();
						for(int s = 0;s<20;s++) {
							plateau.setSelectToNull(s);
						}
						n.setMulti(true);
						n.jouerTour();
						//Si PION[i] n'est pas en mesure de rejouer son tour, on passe au joueur suivant
						if(plateau.getTableauSelect(0) == null && plateau.getTableauSelect(1) == null && plateau.getTableauSelect(2) == null && plateau.getTableauSelect(3) == null) {
							plateau.setTourBlanc(!plateau.isTourBlanc());
							if(!plateau.isMultijoueur()) {
								for(Pion i : plateau.getPionsBlancs()) {
									i.setEnabled(true);	
								}
								for(Pion i : plateau.getPionsNoirs()) {
									i.setEnabled(false);
								}
							} else {
								for(Pion i : plateau.getPionsBlancs()) {
									i.setEnabled(true);	
								}
							}
							
							
						}
						n.setMulti(false);
					}
	
				}
			}
			if(plateau.isMultijoueur()) {
				//On envoie les données
				plateau.multiUpdate();
				plateau.setMonTour(false);
				System.out.println("Les données sont lancées!!");
			}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}