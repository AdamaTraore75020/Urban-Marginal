package modele;

import javax.swing.JPanel;

import controleur.Controle;
import outils.connexion.Connexion;

public class JeuClient extends Jeu {
private Connexion connexion;
private Controle controle;

	public JeuClient(Controle controle){
		this.controle = controle;
	}

	@Override
	public void setConnexion(Connexion connexion) {
		this.connexion = connexion;
	}

	@Override
	public void reception(Object info) {
		if(info instanceof JPanel){
			controle.evenementModele(this, "ajout panel murs", info);
		}
	}

	@Override
	public void deconnexion(Connexion connexion) {
		
	}
	
	public void envoi(Object info){
		super.envoi(connexion, info);
	}

}
