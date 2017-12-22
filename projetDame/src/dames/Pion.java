package dames;

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

public class Pion extends JButton implements MouseListener{
	private int larg;
	private int x;
	private int y;
	private boolean estSelect;
	//private boolean estBloque = false; => G�rer via la variable enabled h�rit�e de JButton
	private boolean estMort = false ;
	//Le bool�en multi permet d'indiquer si le pion est en situation de multiprise, dans ce cas il ne peut se d�placer qu'en prenant des pions.
	private boolean multi = false;
	//Le bool�en estDame indique quand le pion est devenu une Dame et qu'il doit changer sa fa�on de se d�placer.
	private boolean estDame = false;
	private Image img;
	private int size;
	private Plateau plateau;
	
	public Pion(boolean noir, Plateau plateau) {
		//G�n�ration du graphisme du pion
		if(!noir) {
			try {
				img = ImageIO.read(new File("pionBlanc.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				img = ImageIO.read(new File("pionNoir.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.setPreferredSize(new Dimension(size, size));
		this.addMouseListener(this);
		this.setRolloverEnabled(false);
		this.setFocusable(false);
		this.setBorderPainted(false);
		//On y associe le plateau
		this.plateau = plateau;
		this.size = plateau.getPan().getSize().height/10;
		this.larg = (plateau.getPan().getSize().width/2) - (plateau.getPan().getSize().height/2);
	}
	
	//M�thodes appel�es par la souris
	@Override
		//	
		
		public void mouseClicked(MouseEvent arg0) {
		if(this.isEnabled() && plateau.isMonTour()) {
			this.jouerTour();
		} else if(this.isEnabled() && !plateau.isMonTour()) {
			plateau.multiUpdate2();
		}
	}
		
	
	@Override
	public void mouseEntered(MouseEvent arg0) {

		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {

		
	}
	@Override
	public void mousePressed(MouseEvent event) {
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {

	}
	
	//M�thode pour changer l'image source du pion
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}
	
	//Les variables seront modifi�es via les setters
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

	public boolean isMulti() {
		return multi;
	}

	public void setMulti(boolean multi) {
		this.multi = multi;
	}

	public boolean isEstMort() {
		return estMort;
	}

	public void setEstMort(boolean estMort) {
		this.estMort = estMort;
	}
	
	//M�thode pour initialiser le pion
	public void setPos(int x, int y) {
		this.setBounds(larg+x, y, size, size);
		this.setX(larg+x);
		this.setY(y);
	}
	
	public void jouerTour() {
		
		// On cherche si un autre pion �tait s�lectionn�, si oui on le d�selectionne et on actualise le plateau
				for(Pion e : plateau.getPionsBlancs()) {
					if (e.estMort) {e.estMort = false;}
				}
				for(Pion e : plateau.getPionsNoirs()) {
						if (e.estMort) {e.estMort = false;}
				}
				for(Pion e : plateau.getPionsBlancs()) {
					if(e.estSelect) {e.estSelect = false;}
				}
				for(Pion e : plateau.getPionsNoirs()) {
					if(e.estSelect) {e.estSelect = false;}
				}
				plateau.actualisePions();
				plateau.setSelectToNull(0);
				plateau.setSelectToNull(1);
				//On s�lectionne ce pion
				this.setEstSelect(true);
				//On cr�e les boutons indicatifs de d�placement
				int x;
				int y;
				
				for(Pion e : plateau.getPionsBlancs()) {
					//On v�rifie si un pion blanc est s�lectionn�
					if(e.estSelect)
					{
						
						//On enregistre la position du pion dans des variables pour l'utiliser plus tard
						x = (e.x);
						y = (e.y);
						//On d�fini des bool�ens qui serviront � autoriser ou non les d�placements � gauche et � droite
						boolean stopG = true;
						boolean stopD = true;
						boolean stopD2 = true;
						boolean stopG2 = true;
						boolean stopArD = true;
						boolean stopArG = true;

						//On param�tre les bool�ens en fonction de la position des pions alli�s & ennemis
						for(Pion b : plateau.getPionsBlancs()) {
							if(b.getY() == y+size && b.getX() == x+size) {
								stopD = false;
							}
							if (b.getY() == y+size && b.getX() == x-size) {
								stopG = false;
							}
							if(b.getY() == y+2*size && b.getX() == x+2*size) {
								stopD2 = false;
							}
							if(b.getY() == y+2*size && b.getX() == x-2*size) {
								stopG2 = false;
							}
							if(b.getY() == y-2*size && b.getX() == x+2*size) {
								stopArD = false;
							}
							if(b.getY() == y-2*size && b.getX() == x-2*size) {
								stopArG = false;
							}
						}
						for(Pion n : plateau.getPionsNoirs()) {
							if(n.getY() == y+size && n.getX() == x+size) {
								stopD = false;
							}
							if (n.getY() == y+size && n.getX() == x-size) {
								stopG = false;
							}
							if(n.getY() == y+2*size && n.getX() == x+2*size) {
								stopD2 = false;
							}

							if(n.getY() == y+2*size && n.getX() == x-2*size) {
								stopG2 = false;
							}
							if(n.getY() == y-2*size && n.getX() == x+2*size) {
								stopArD = false;
							}
							
							if(n.getY() == y-2*size && n.getX() == x-2*size) {
								stopArG = false;
							}
						}
						//On param�tre des bool�ens en fonction des limites du plateau
						boolean stopBorderD = true;
						boolean stopBorderG = true;
						boolean stopBorderD2 = true;
						boolean stopBorderG2 = true;
						boolean stopHaut = true;
						boolean stopBas  = true;
						boolean stopHaut2 = true;
						boolean stopBas2  = true;
						if(e.getX() + size >= larg + 10*size) {
							stopBorderD = false;
						}
						if(e.getX() - size < larg) {
							stopBorderG = false;
						}
						if(e.getX() + 2*size >= larg + 10*size) {
							stopBorderD2 = false;
						}
						if(e.getX() - 2*size < larg) {
							stopBorderG2 = false;
						}
						if(e.getY() + size >= 10*size) {
							stopBas = false;
						}
						if(e.getY() - size < 0) {
							stopHaut = true;
						}
						if(e.getY() + 2*size >= 10*size) {
							stopBas2 = false;
						}
						if(e.getY() - 2*size < 0) {
							stopHaut2 = true;
						}
						//On positionne ensuite les cases turquoises balisants les d�placements
						for(Pion n : plateau.getPionsNoirs()) {
							//Prise arri�re
							if(n.getY() == y-size && n.getX() == x+size && stopArD && stopBorderD2 && stopHaut2){
								plateau.setTableauSelect(new BoutonSelect((x+2*size), (y-2*size), n, plateau), 2);
								
								plateau.getTableauSelect(2).setPrise(true);
							}
							if(n.getY() == y-size && n.getX() == x-size && stopArG && stopBorderG2 && stopHaut2) {
								plateau.setTableauSelect(new BoutonSelect((x-2*size), (y-2*size), n, plateau), 3);
								
								plateau.getTableauSelect(3).setPrise(true);
							}
							//Prise classique
							if(n.getY() == y+size && n.getX() == x+size && stopD2 && stopBorderD2 && stopBas2) {
								plateau.setTableauSelect(new BoutonSelect((x+2*size), (y+2*size), n, plateau), 0);
								
								plateau.getTableauSelect(0).setPrise(true);
							} else if(plateau.getTableauSelect(0) == null && stopD && stopBorderD && stopBas && !multi){
								plateau.setTableauSelect(new BoutonSelect((x+size), (y+size), plateau), 0);
							}	
							if (n.getY() == y+size && n.getX() == x-size && stopG2 && stopBorderG2 && stopBas2) {
								plateau.setTableauSelect(new BoutonSelect((x-2*size), (y+2*size), n, plateau), 1);
								
								plateau.getTableauSelect(1).setPrise(true);
							} else if(plateau.getTableauSelect(1) == null && stopG && stopBorderG && stopBas && !multi){
								plateau.setTableauSelect(new BoutonSelect((x-size), (y+size), plateau), 1);
							}
							
						}
						plateau.addSelect();
						
					}
				}
					//On v�rifie si un pion Noir est s�lectionn�
					for(Pion e : plateau.getPionsNoirs()){
						if(e.estSelect) {
						//On enregistre la position du pion dans des variables pour l'utiliser plus tard
						x = (e.x);
						y = (e.y);
						//On d�fini des bool�ens qui serviront � autoriser ou non les d�placements � gauche et � droite
						boolean stopD = true;
						boolean stopG = true;
						boolean stopD2 = true;
						boolean stopG2 = true;
						boolean stopArD = true;
						boolean stopArG = true;
						//On param�tre les bool�ens en fonction de la position des pions alli�s & ennemis
						for(Pion b : plateau.getPionsBlancs()) {
							if(b.getY() == y-size && b.getX() == x+size) {
								stopD = false;
							}
							if (b.getY() == y-size && b.getX() == x-size) {
								stopG = false;
							}
							if(b.getY() == y-2*size && b.getX() == x+2*size) {
								stopD2 = false;
							}
							if(b.getY() == y-2*size && b.getX() == x-2*size) {
								stopG2 = false;
							}
							
							if(b.getY() == y+2*size && b.getX() == x+2*size) {
								stopArD = false;
							}
							if(b.getY() == y+2*size && b.getX() == x-2*size) {
								stopArG = false;
							}
						}
						for(Pion n : plateau.getPionsNoirs()) {
							if(n.getY() == y-size && n.getX() == x+size) {
								stopD = false;
							}
							if (n.getY() == y-size && n.getX() == x-size) {
								stopG = false;
							}
							if(n.getY() == y-2*size && n.getX() == x+2*size) {
								stopD2 = false;
							}
							if(n.getY() == y-2*size && n.getX() == x-2*size) {
								stopG2 = false;
							}

							if(n.getY() == y+2*size && n.getX() == x+2*size) {
								stopArD = false;
							}

							if(n.getY() == y+2*size && n.getX() == x-2*size) {
								stopArG = false;
							}
						}
						//On param�tre des bool�ens en fonction des limites du plateau
						boolean stopBorderD = true;
						boolean stopBorderG = true;
						boolean stopBorderD2 = true;
						boolean stopBorderG2 = true;
						boolean stopHaut = true;
						boolean stopBas  = true;
						boolean stopHaut2 = true;
						boolean stopBas2  = true;
						if(e.getX() + size >= larg + 10*size) {
							stopBorderD = false;
						}
						if(e.getX() - size < larg) {
							stopBorderG = false;
						}
						if(e.getX() + 2*size >= larg + 10*size) {
							stopBorderD2 = false;
						}
						if(e.getX() - 2*size < larg) {
							stopBorderG2 = false;
						}
						if(e.getY() + size >= 10*size) {
							stopBas = false;
						}
						if(e.getY() - size < 0) {
							stopHaut = true;
						}
						if(e.getY() + 2*size >= 10*size) {
							stopBas2 = false;
						}
						if(e.getY() - 2*size < 0) {
							stopHaut2 = true;
						}
						//On positionne ensuite les cases turquoises balisants les d�placements
						for(Pion b : plateau.getPionsBlancs()) {
							//Prise arri�re
							if(b.getY() == y+size && b.getX() == x+size && stopArD && stopBorderD2 && stopBas2){
								plateau.setTableauSelect((new BoutonSelect(x+2*size, y+2*size, b, plateau)), 2);
								
								plateau.getTableauSelect(2).setPrise(true);
							}
							if(b.getY() == y+size && b.getX() == x-size && stopArG && stopBorderG2 && stopBas2) {
								plateau.setTableauSelect(new BoutonSelect(x-2*size, y+2*size, b, plateau), 3);
								
								plateau.getTableauSelect(3).setPrise(true);
							}
							//Prise classique
							if(b.getY() == y-size && b.getX() == x+size && stopD2 && stopBorderD2 && stopHaut2) {
								plateau.setTableauSelect(new BoutonSelect((x+2*size), (y-2*size), b, plateau), 0);
								
								plateau.getTableauSelect(0).setPrise(true);
							} else if(plateau.getTableauSelect(0) == null && stopD && stopBorderD && stopHaut && !multi){
								plateau.setTableauSelect(new BoutonSelect((x+size), (y-size), plateau), 0);	
							}
							if(b.getY() == y-size && b.getX() == x-size && stopG2 && stopBorderG2 && stopHaut2) {
								plateau.setTableauSelect(new BoutonSelect((x-2*size), (y-2*size), b, plateau), 1);
								
								plateau.getTableauSelect(1).setPrise(true);
							} else if(plateau.getTableauSelect(1) == null && stopG && stopBorderG && stopHaut && !multi){
								plateau.setTableauSelect(new BoutonSelect((x-size), (y-size), plateau), 1);
							}
						}
						plateau.addSelect();
					}
				}
				
	}
}

	