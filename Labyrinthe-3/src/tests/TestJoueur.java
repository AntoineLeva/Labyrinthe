package tests;

import composants.Piece;
import composants.Plateau;
import grafix.interfaceGraphique.IG;
import joueurs.Joueur;

public class TestJoueur {
	public static void main(String[] args) {
		Object parametres[];
		parametres=IG.saisirParametres();
		int nbJoueurs=((Integer)parametres[0]).intValue();
		IG.creerFenetreJeu("Démo Librairie IG version 1.9",nbJoueurs);
		//Changement des pieces du plateau
		for(int i =0;i< 7;i++) {
			for(int j=0;j<7;j++) {
				IG.changerPiecePlateau(i, j, 0, 0);
			}
		}
		
		//Initialisation des stats des joueurs sur le côté
		int numImageJoueur0=((Integer)parametres[3]).intValue();
		String nomJoueur0=(String)parametres[1];
		String categorieJoueur0=(String)parametres[2];

		int numImageJoueur1=((Integer)parametres[6]).intValue();
		String nomJoueur1=(String)parametres[4];
		String categorieJoueur1=(String)parametres[5];
		
		int numImageJoueur2=((Integer)parametres[9]).intValue();
		String nomJoueur2=(String)parametres[7];
		String categorieJoueur2=(String)parametres[8];
		
		IG.changerPieceHorsPlateau(0,0);
		
		//Affichage des stats des joueurs sur le côté
		IG.changerNomJoueur(0, nomJoueur0+" ("+categorieJoueur0+")");
		IG.changerImageJoueur(0,numImageJoueur0);
		IG.changerNomJoueur(1, nomJoueur1+" ("+categorieJoueur1+")");
		IG.changerImageJoueur(1,numImageJoueur1);
		IG.changerNomJoueur(2, nomJoueur2+" ("+categorieJoueur2+")");
		IG.changerImageJoueur(2,numImageJoueur2);
		Plateau plateau = new Plateau();
		Piece pieceHorsPlateau = plateau.placerPiecesAleatoirement();
		Joueur joueurs[]=Joueur.nouveauxJoueurs(parametres);
		for(int i =0;i< 7;i++) {
			for(int j=0;j<7;j++) {
				IG.changerPiecePlateau(i, j, plateau.getPiece(i,j).getModelePiece(), plateau.getPiece(i,j).getOrientationPiece());
			}
		}
		IG.changerPieceHorsPlateau(pieceHorsPlateau.getModelePiece(),pieceHorsPlateau.getOrientationPiece());
		IG.placerJoueurSurPlateau(numImageJoueur0, joueurs[0].getPosLigne(), joueurs[0].getPosColonne());
		IG.placerJoueurSurPlateau(numImageJoueur1, joueurs[1].getPosLigne(), joueurs[1].getPosColonne());
		IG.placerJoueurSurPlateau(numImageJoueur2, joueurs[2].getPosLigne(), joueurs[2].getPosColonne());
		
		String message[]={
				"",
				"",
				"Cliquer pour continuer ...",
				""
		};
		IG.afficherMessage(message);
		IG.rendreVisibleFenetreJeu();
		IG.jouerUnSon(2); // On joue le son numéro 2
		IG.attendreClic();
		//1er clic
		int[] caseChoisis = null;
		int numJoueur = 0;
		while(caseChoisis  == null) {
			message[0]="";
			message[1]="Au tour de "+parametres[numJoueur*3+1];
			message[2]="Selectionnez une case...";
			message[3]="";
			IG.afficherMessage(message);
			IG.changerJoueurSelectionne(numJoueur);
			IG.miseAJourAffichage();
			if(parametres[numJoueur*3+2] == "Humain") {
				caseChoisis = joueurs[numJoueur].choisirCaseArrivee(null);
			}
			else {
				caseChoisis = new int[2];
				caseChoisis[0] = joueurs[numJoueur].getPosLigne();
				caseChoisis[1] = joueurs[numJoueur].getPosColonne();
			}
			int[][] chemin = plateau.calculeChemin(joueurs[numJoueur].getPosLigne(),joueurs[numJoueur].getPosColonne(), caseChoisis[0], caseChoisis[1]);
			if(chemin != null) {
				joueurs[numJoueur].setPosition(caseChoisis[0], caseChoisis[1]);
				IG.placerJoueurSurPlateau(joueurs[numJoueur].getNumeroImagePersonnage(), joueurs[numJoueur].getPosLigne(), joueurs[numJoueur].getPosColonne());
				for(int i =0;i< chemin.length;i++) {
					IG.placerBilleSurPlateau(chemin[i][0], chemin[i][1], 1, 1, numJoueur+1);
				}
				IG.miseAJourAffichage();
				if(numJoueur == 2) {
					message[0]="";
					message[1]="C'est terminé !";
					message[2]="Cliquez pour quitter...";
					message[3]="";
					IG.afficherMessage(message);
					IG.miseAJourAffichage();
					break;
				}
				else {numJoueur++;}
			}
			else {
				System.out.println("Case incorrect pour le joueur: "+parametres[numJoueur*3+1]);
			}
			caseChoisis = null;
		}
		IG.attendreClic();
		IG.fermerFenetreJeu();
		System.exit(0);
		
		
		
		
		
		
		

		
	}
}
