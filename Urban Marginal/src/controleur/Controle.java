package controleur;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modele.Jeu;
import modele.JeuClient;
import modele.JeuServeur;
import modele.Label;
import outils.connexion.ClientSocket;
import outils.connexion.Connexion;
import outils.connexion.ServeurSocket;
import vue.Arene;
import vue.ChoixJoueur;
import vue.EntreeJeu;

public class Controle implements Global {
	private EntreeJeu frmEntreeJeu;
	private Jeu leJeu;
	private Arene frmArene;
	private ChoixJoueur frmChoixJoueur;
	private Connexion connection;

	public static void main(String[] args) {
		new Controle();

	}

	public void setConnection(Connexion connection) {
		this.connection = connection;
		if (leJeu instanceof JeuServeur) {
			leJeu.setConnection(connection);
		}
	}

	public Controle() {
		this.frmEntreeJeu = new EntreeJeu(this);
		frmEntreeJeu.setVisible(true);

	}

	// Gestion des evenements du modele

	public void receptionInfo(Connexion connection, Object info) {
		this.leJeu.reception(connection, info);
	}

	public void evenementVue(JFrame uneFrame, Object info) {
		if (uneFrame instanceof EntreeJeu) {
			evenementEntreeJeu(info);
		}
		if (uneFrame instanceof ChoixJoueur) {
			evenementChoixJoueur(info);
		}
		if (uneFrame instanceof Arene) {
			evenementArene(info);
		}
	}

	private void evenementArene(Object info) {
		((JeuClient) this.leJeu).envoi(info);
	}

	private void evenementChoixJoueur(Object info) {
		((JeuClient) this.leJeu).envoi(info);
		frmChoixJoueur.dispose();
		frmArene.setVisible(true);

	}

	private void evenementEntreeJeu(Object info) {
		if ((String) info == "serveur") {
			new ServeurSocket(this, PORT);
			leJeu = new JeuServeur(this);
			frmEntreeJeu.dispose();
			frmArene = new Arene("serveur", this);
			((JeuServeur) this.leJeu).constructionMurs();
			((JeuServeur) this.leJeu).afficheBonus();
			frmArene.setVisible(true);
		} else {
			if ((new ClientSocket((String) info, PORT, this)).isConnexionOk()) {
				leJeu = new JeuClient(this);
				frmArene = new Arene("client", this);
				leJeu.setConnection(connection);
				frmEntreeJeu.dispose();
				frmChoixJoueur = new ChoixJoueur(this);
				frmChoixJoueur.setVisible(true);
			}
		}

	}

	
	
	public void evemenementModele(Object unJeu, String ordre, Object info) {
		if (unJeu instanceof JeuServeur) {
			evenementJeuServeur(ordre, info);
		}
		if (unJeu instanceof JeuClient) {
			evenementJeuClient(ordre, info);
		}

	}

	private void evenementJeuClient(String ordre, Object info) {
		if (ordre == "ajout panel murs") {
			frmArene.ajoutPanelMurs((JPanel) info);
		}
		if (ordre == "ajout joueur") {
			frmArene.ajoutModifJoueur(((Label) info).getNumLabel(), ((Label) info).getjLabel());
		}
		if(ordre == "remplace chat"){
			frmArene.remplaceChat((String)info);
		}
		if(ordre == "son"){
			frmArene.joueSon((Integer)info);
		}
		if(ordre == "ajout JLabel bonus"){
			frmArene.ajoutBonus((JLabel)info);
		}
	}

	private void evenementJeuServeur(String ordre, Object info) {
		if (ordre == "ajout mur") {
			frmArene.ajoutMur((JLabel) info);
		}
		if (ordre == "envoi panel murs") {
			((JeuServeur) this.leJeu).envoi((Connexion) info, frmArene.getJpnMurs());
		}
		if(ordre == "ajout bonus"){
			frmArene.ajoutBonus(((Label)info).getjLabel());
		}
		if(ordre == "envoi panel bonus"){
			((JeuServeur) this.leJeu).envoi((Connexion) info, frmArene.getJpnBonus());
		}
		if (ordre == "ajout joueur") {
			frmArene.ajoutJoueur(((Label) info).getjLabel());
		}
		if (ordre == "ajout phrase"){
			frmArene.ajoutChat(((String)info));
			((JeuServeur)this.leJeu).envoi(frmArene.getTxtChat());
		}
		
		

	}
	
	public void deconnexion(Connexion connexion){
		leJeu.deconnection(connexion);
	}

	/**
	 * @return the leJeu
	 */
	public Jeu getLeJeu() {
		return leJeu;
	}
	
	
}
