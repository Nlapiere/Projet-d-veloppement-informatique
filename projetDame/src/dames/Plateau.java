package dames;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * 
 * @author Groupe 13
 * Classe reprenant les caractéristiques et méthodes pour créer le plateau de jeu
 */
public class Plateau extends JFrame{
	//déclaration des variables de classe
	private ArrayList<Pion> pionsBlancs = new ArrayList<Pion>();	//on déclare deux arrays lists de pions noirs et blancs
	private ArrayList<Pion> pionsNoirs = new ArrayList<Pion>();
	
	private boolean tourBlanc = true;
	private boolean monTour = true;
	private boolean multijoueur = false;
	private boolean joueurBlanc = false;
	
	private BoutonSelect[] tableauSelect = new BoutonSelect[20];
	
	private JPanel pan = new PanelPlateau(this);
	private int size;
	
	private Menus np = new Menus("Nouvelle Partie", this);
	private Menus ab = new Menus("Abandonner", this);
	private Menus qt = new Menus("Quitter", this);
	private Menus l = new Menus("Local", this);
	private Menus r = new Menus("Réseau", this);
	private Menus an = new Menus("Annuler", this);
	private Menus ch = new Menus("Chercher", this);
	private Menus ho = new Menus("Host", this);
	
	private JTextField ipField = new JTextField();
	private JTextField portField = new JTextField();
	
	private ServerSocket serveur;
	private Socket socket;
	
	private DataOutputStream dos;
	private DataInputStream dis;
	
	private String actualise;
	
	
	
	public Menus getHo() {
		return ho;
	}
	public void setHo(Menus ho) {
		this.ho = ho;
	}
	public Socket getSocket() {
		return socket;
	}
	public Menus getCh() {
		return ch;
	}
	public void setCh(Menus ch) {
		this.ch = ch;
	}
	public boolean isJoueurBlanc() {
		return joueurBlanc;
	}
	public void setJoueurBlanc(boolean joueurBlanc) {
		this.joueurBlanc = joueurBlanc;
	}
	public boolean isMultijoueur() {
		return multijoueur;
	}
	public void setMultijoueur(boolean multijoueur) {
		this.multijoueur = multijoueur;
	}
	public DataOutputStream getDos() {
		return dos;
	}
	public void setDos(DataOutputStream dos) {
		this.dos = dos;
	}
	public DataInputStream getDis() {
		return dis;
	}
	public void setDis(DataInputStream dis) {
		this.dis = dis;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	public ServerSocket getServeur() {
		return serveur;
	}
	public void setServeur(ServerSocket serveur) {
		this.serveur = serveur;
	}
	public boolean isMonTour() {
		return monTour;
	}
	public void setMonTour(boolean monTour) {
		this.monTour = monTour;
	}
	
	public Menus getL() {
		return l;
	}
	public void setL(Menus l) {
		this.l = l;
	}
	public Menus getR() {
		return r;
	}
	public void setR(Menus r) {
		this.r = r;
	}
	public Menus getA() {
		return an;
	}
	public void setA(Menus an) {
		this.an = an;
	}
	public JTextField getIpField() {
		return ipField;
	}
	public void setIpField(JTextField ipField) {
		this.ipField = ipField;
	}
	public JTextField getPortField() {
		return portField;
	}
	public void setPortField(JTextField portField) {
		this.portField = portField;
	}
	public Menus getAb() {
		return ab;
	}
	public void setAb(Menus ab) {
		this.ab = ab;
	}
	public Menus getQt() {
		return qt;
	}
	public void setQt(Menus qt) {
		this.qt = qt;
	}
	public Menus getNp() {
		return np;
	}
	public void setNp(Menus np) {
		this.np = np;
	}
	public  ArrayList<Pion> getPionsBlancs() {
		return pionsBlancs;
	}
	public  Pion getPionBlanc(Pion e) {
		int index = pionsBlancs.indexOf(e);
		return pionsBlancs.get(index);
	}
	public  void setPionsBlancs(ArrayList<Pion> pionsBlancs) {
		this.pionsBlancs = pionsBlancs;
	}
	public  ArrayList<Pion> getPionsNoirs() {
		return pionsNoirs;
	}
	public  Pion getPionNoir(Pion e) {
		int index = pionsNoirs.indexOf(e);
		return pionsNoirs.get(index);
	}
	public  void setPionsNoirs(ArrayList<Pion> pionsNoirs) {
		this.pionsNoirs = pionsNoirs;
	}
	public  JPanel getPan() {
		return pan;
	}
	public  void setPan(JPanel pan) {
		this.pan = pan;
	}
	public  BoutonSelect getTableauSelect(int i) {
		return tableauSelect[i];
	}
	public  void setTableauSelect(BoutonSelect bouton, int i) {
		this.tableauSelect[i] = bouton;
	}
	public  void setSelectToNull(int i) {
		this.tableauSelect[i] = null;
	}
	public  boolean isTourBlanc() {
		return tourBlanc;
	}
	public  void setTourBlanc(boolean tour) {
		tourBlanc = tour;
	}
	

	public Plateau() {		

		//Instanciation
		this.setTitle("Projet Dames");
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setUndecorated(true);
		//On utilise le JPanel "pan" comme content pane
		this.setContentPane(pan);
		pan.setLayout(null);
		this.setLayout(null);
		this.setVisible(true);

	}
	public  void initMenu() {
		this.size = pan.getSize().height/10;
		
		InetAddress LocaleAdresse = null;
		try {
			LocaleAdresse = InetAddress.getLocalHost();
			System.out.println("IP:"+LocaleAdresse);
		} catch (UnknownHostException e) {
	
			e.printStackTrace();
		}
		
		np.setBounds(size*15, size*1, size*2, size/2);
		np.setText("Nouvelle Partie");
		np.setVisible(true);
		np.setEnabled(true);
		pan.add(np);
		
		qt.setBounds(size*15, size*2, size*2, size/2);
		qt.setText("Quitter");
		qt.setVisible(true);
		qt.setEnabled(true);
		pan.add(qt);
		
		l.setBounds(size*15, size*1, size*2, size/2);
		l.setText("Jouer sur ce PC");
		l.setVisible(false);
		l.setEnabled(false);
		pan.add(l);
		
		r.setBounds(size*15, size*2, size*2, size/2);
		r.setText("Jouer en réseau");
		r.setVisible(false);
		r.setEnabled(false);
		pan.add(r);
		
		an.setBounds(size*15, size*3, size*2, size/2);
		an.setText("Annuler");
		an.setVisible(false);
		an.setEnabled(false);
		pan.add(an);
		
		ipField.setBounds(size*15, size*1, size*2, size/4);
		ipField.setVisible(false);
		ipField.setEnabled(false);
		ipField.setText("Entrez l'adresse ip du serveur. Votre machine: "+ LocaleAdresse);
		pan.add(ipField);
		
		portField.setBounds(size*15, size*2, size*2, size/4);
		portField.setVisible(false);
		portField.setEnabled(false);
		portField.setText("Entrez le n° de port (exple:2020)");
		pan.add(portField);
		
		ch.setBounds(size*15, size*4, size*2, size*1/2);
		ch.setText("Chercher une partie");
		ch.setVisible(false);
		ch.setEnabled(false);
		pan.add(ch);
		
		ho.setBounds(size*15, size*5, size*2, size*1/2);
		ho.setText("Héberger une partie");
		ho.setVisible(false);
		ho.setEnabled(false);
		pan.add(ho);
		
		pan.repaint();
		pan.revalidate();
		
	}
	public  void initPions() {
		this.size = pan.getSize().height/10;
		
		ab.setBounds(size*15, size*2, size*2, size/2);
		ab.setText("Abandonner");
		pan.add(ab);
	
		int compt = 0;
		int compt1 = 0;
		int compt2 = 0;
		int compt3 = 0;
		
		//On crée les pions blancs avec "false" en argument pour y associer le bon graphisme
		for (int i=0;i<20;i++) {
			Pion p = new Pion(false, this);
			pionsBlancs.add(p);
		}
		//On crée les pions noirs avec "true" en argument pour y associer le bon graphisme
		for (int i=0;i<20;i++) {
			Pion p = new Pion(true, this);
			pionsNoirs.add(p);
		}
		//On instancie les pions à leur emplacement initial sur le plateau, ligne par ligne
		//Init des pions Blancs
		for(Pion e : pionsBlancs) {
			if(compt < 5) {
				e.setPos((compt*2*size)+size, 0);
				pan.add(e);
			}
			if(compt >= 5 && compt < 10) {
				e.setPos(compt1*size*2, size);
				pan.add(e);
				compt1++;
			}
			if(compt >= 10 && compt < 15) {
				e.setPos((compt2*size*2)+size, 2*size);
				pan.add(e);
				compt2++;
			}
			if(compt >= 15 && compt < 20) {
				e.setPos(compt3*2*size, 3*size);
				pan.add(e);
				compt3++;
			}
			compt++;
		}
		//remise à zéro des compteurs
		compt = 0;
		compt1 = 0;
		compt2 = 0;
		compt3 = 0;
		
		//Init des pions Noirs
		for(Pion e : pionsNoirs) {
			if(compt < 5) {
				e.setPos((compt*2*size), 9*size);
				pan.add(e);
			}
			if(compt >= 5 && compt < 10) {
				e.setPos((compt1*size*2)+size, 8*size);
				pan.add(e);
				compt1++;
			}
			if(compt >= 10 && compt < 15) {
				e.setPos(compt2*size*2, 7*size);
				pan.add(e);
				compt2++;
			}
			if(compt >= 15 && compt < 20) {
				e.setPos((compt3*2*size)+size, 6*size);
				pan.add(e);
				compt3++;
			}
			compt++;
		}
		//remise à zéro des compteurs
		compt = 0;
		compt1 = 0;
		compt2 = 0;
		compt3 = 0;
		
		for(Pion e : pionsNoirs) {
			e.setEnabled(false);
		}
		tourBlanc = true;
	}
	public  void addSelect() {
		for(int i = 0; i < 20;i++) {
			if(tableauSelect[i] != null) {
				pan.add(tableauSelect[i]);
			}
			pan.repaint();
			pan.revalidate();
		}
	}
	public  void actualisePions() {
		for(Pion b : pionsBlancs) {
			b.setBounds(b.getX(), b.getY(), size, size);
			if(b.isEstMort()) {
				b.setX(22*size);
				pan.remove(b);
			}
			
		}
		for(Pion n : pionsNoirs) {
			n.setBounds(n.getX(), n.getY(), size, size);
			if(n.isEstMort()) {
				n.setX(22*size);
				pan.remove(n);
			}

		}
		for(int s = 0;s<20;s++) {
			if(tableauSelect[s] != null ) {
				pan.remove(tableauSelect[s]);
			}
		}
		if(multijoueur) {
			if(joueurBlanc) {
				for(Pion e:pionsBlancs) {
					e.setEnabled(true);
				}
				for(Pion e:pionsNoirs) {
					e.setEnabled(false);
				}
			} else if(!joueurBlanc){
				for(Pion e:pionsNoirs) {
					e.setEnabled(true);
				}
				for(Pion e:pionsBlancs)
					e.setEnabled(false);
			}
		}
		
		pan.repaint();
		pan.revalidate();
	}

	public  void reset() {
		pionsBlancs.removeAll(pionsBlancs);
		pionsNoirs.removeAll(pionsNoirs);
		pan.removeAll();
		
		pan.add(np);
		pan.add(qt);
		pan.add(an);
		pan.add(ab);
		pan.add(l);
		pan.add(r);
		pan.add(ipField);
		pan.add(portField);
		
		pan.repaint();
		pan.revalidate();
		if(tourBlanc) {
			JOptionPane.showMessageDialog(null, "Le joueur blanc déclare forfait!");
		} else if (!tourBlanc) {
			JOptionPane.showMessageDialog(null, "Le joueur noir déclare forfait!");
		}
	}
	// ---------------- PARTIE RESEAU ------------------------------------
	//On utilise "multijoueur" quand le PC va devenir serveur de la partie
	public void initialiseServeur(int port) {
		try {
			
			serveur = new ServerSocket(port);
			System.out.println("Port:"+serveur.getLocalPort());	
			socket = serveur.accept();
			
			dis = new DataInputStream(socket.getInputStream());
		    dos = new DataOutputStream(socket.getOutputStream());
		    
			multijoueur = true;
			joueurBlanc = true;
			
		
			System.out.println("Connexion établie !");

		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	//On utilise "connexion" quand le PC va se connecter à un serveur
	public void connexion(InetAddress ip,int port) {
		try {
			
		     socket = new Socket(ip, port);
		     
		     dis = new DataInputStream(socket.getInputStream());
		     dos = new DataOutputStream(socket.getOutputStream());
		     
		   
		}catch (UnknownHostException e) {	
			e.printStackTrace();
			
		}catch (IOException e) {	
			e.printStackTrace();
		}
		monTour = false;
	    multijoueur = true;
	     
	}
	
	public void multiUpdateTmp() {
		String tmp = "";
		for(Pion b : pionsBlancs) {
				
				tmp += Integer.toString(b.getX()) +"/";
		
				tmp += Integer.toString(b.getY()) +"/";
		}
		for(Pion n : pionsNoirs) {
			
				tmp += Integer.toString(n.getX()) +"/";
				
				tmp += Integer.toString(n.getY()) +"/";
		}
		actualise = tmp;
	}
	public void multiUpdate(){
		
		try {
			dos.writeUTF(actualise);
			System.out.println("J'ai envoyé les données:" + actualise);
			dos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void multiUpdate2(){
		if(!monTour && multijoueur) {
			if(dis != null) {
				try {
					String tmp = dis.readUTF();
					actualise = tmp;
					System.out.println(actualise);
				} catch (IOException e) {
					e.printStackTrace();
				}
				String[] cut = actualise.split("/");
				for(Pion b:pionsBlancs) {
					b.setX(Integer.parseInt(cut[pionsBlancs.indexOf(b)*2]));
					b.setY(Integer.parseInt(cut[(pionsBlancs.indexOf(b)*2)+1]));
					if(b.getX() > size*15) {
						pan.remove(b);
					}
				}
				for(Pion n:pionsNoirs) {
					n.setX(Integer.parseInt(cut[40+(pionsNoirs.indexOf(n)*2)]));
					n.setY(Integer.parseInt(cut[40+((pionsNoirs.indexOf(n)*2)+1)]));
					if(n.getX() > size*15) {
						pan.remove(n);
					}
				}
				actualisePions();
				monTour = true;
			}
		}
	}
}
