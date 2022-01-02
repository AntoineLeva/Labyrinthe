package tests;

import composants.Objet;
import composants.Plateau;
import grafix.interfaceGraphique.IG;
import joueurs.Joueur;
import partie.ElementsPartie;

public class TestElementsPartie {
	public static void main(String[] args) {
		Object parametresJeu[];
		parametresJeu=IG.saisirParametres();
		int  nbJoueurs=((Integer)parametresJeu[0]).intValue();
		IG.creerFenetreJeu("- TestElementsPartie",nbJoueurs);
		Joueur joueurs[]=Joueur.nouveauxJoueurs(parametresJeu);
		ElementsPartie elementsPartie=new ElementsPartie(joueurs);
		
		Plateau plateauJeu = elementsPartie.getPlateau();
		Objet[] objetJeu = elementsPartie.getObjets();
		Joueur[] joueurJeu = elementsPartie.getJoueurs();
		
		
		//Changement des pieces du plateau
		for(int i =0;i< 7;i++) {
			for(int j=0;j<7;j++) {
				IG.changerPiecePlateau(i, j, plateauJeu.getPiece(i,j).getModelePiece(), plateauJeu.getPiece(i,j).getOrientationPiece());
			}
		}
		IG.changerPieceHorsPlateau(elementsPartie.getPieceLibre().getModelePiece(),elementsPartie.getPieceLibre().getOrientationPiece());
		
		//Positionnement des objets sur le plateau
		for (int i=0;i<objetJeu.length;i++) {
			objetJeu[i].positionneObjet(objetJeu[i].getPosLignePlateau(),objetJeu[i].getPosColonnePlateau());
		}
		
		
		//Initialisation des stats des joueurs sur le côté
		int numImageJoueur0=((Integer)parametresJeu[3]).intValue();
		String nomJoueur0=(String)parametresJeu[1];
		String categorieJoueur0=(String)parametresJeu[2];

		int numImageJoueur1=((Integer)parametresJeu[6]).intValue();
		String nomJoueur1=(String)parametresJeu[4];
		String categorieJoueur1=(String)parametresJeu[5];
		
		int numImageJoueur2=((Integer)parametresJeu[9]).intValue();
		String nomJoueur2=(String)parametresJeu[7];
		String categorieJoueur2=(String)parametresJeu[8];
		// Placement des joueurs sur le plateau
		for(int i =0;i < joueurJeu.length;i++) {
			IG.placerJoueurSurPlateau(joueurJeu[i].getNumeroImagePersonnage(),joueurJeu[i].getPosLigne(),joueurJeu[i].getPosColonne());
		}
		// Changement d'objets au premier joueur et second joueur
		for (int i=0;i<elementsPartie.getNombreJoueurs();i++){
			for(int j=0;j < 6;j++) {
				IG.changerObjetJoueurAvecTransparence(i,joueurJeu[i].getObjetsJoueur()[j].getNumeroObjet(),j);
			}
		}
		//Affichage des stats des joueurs sur le côté
		IG.changerNomJoueur(0, nomJoueur0+" ("+categorieJoueur0+")");
		IG.changerImageJoueur(0,numImageJoueur0);
		IG.changerNomJoueur(1, nomJoueur1+" ("+categorieJoueur1+")");
		IG.changerImageJoueur(1,numImageJoueur1);
		IG.changerNomJoueur(2, nomJoueur2+" ("+categorieJoueur2+")");
		IG.changerImageJoueur(2,numImageJoueur2);
		IG.rendreVisibleFenetreJeu();
		IG.jouerUnSon(2); // On joue le son numéro 2
		String message[]={
				"",
				"",
				"Cliquer pour continuer ...",
				""
		};
		IG.afficherMessage(message); // On change de message de la fenêtre de jeu
		IG.miseAJourAffichage(); // On effectue le rafraichissement de la fenêtre de jeu
		IG.attendreClic();
		//1er clic
		message[0]="";
		message[1]="Choisissez une entrée ...";
		message[2]="(une des flèches)";
		message[3]="";
		IG.afficherMessage(message); // On change de message de la fenêtre de jeu
		IG.miseAJourAffichage(); // On effectue le rafraichissement de la fenêtre de jeu
		int cpt = 0;
		while(cpt < 60) {
			elementsPartie.insertionPieceLibre(IG.attendreChoixEntree());
			IG.miseAJourAffichage();
			cpt++;
			IG.deselectionnerFleche();
		}
	}
}
