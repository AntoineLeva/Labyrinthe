package tests;
import grafix.interfaceGraphique.IG;

public class MaDemoIG {
	public static void main(String[] args) {
		Object parametres[];
		parametres=IG.saisirParametres();
		int nbJoueurs=((Integer)parametres[0]).intValue();
		IG.creerFenetreJeu("Démo Librairie IG version 1.9",nbJoueurs);
		//Changement des pieces du plateau
		for(int i =0;i< 7;i++) {
			for(int j=0;j<7;j++) {
				IG.changerPiecePlateau(i, j, 2, 0);
			}
		}
		
		IG.changerPieceHorsPlateau(1,0);
		
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
		
		// Changement d'objets au premier joueur et second joueur
		for (int i=0;i<6;i++){
			IG.changerObjetJoueur(0,i,i);
			IG.changerObjetJoueur(1,i+6,i);
			IG.changerObjetJoueur(2,i+12,i);
		}
		
		//Affichage des stats des joueurs sur le côté
		IG.changerNomJoueur(0, nomJoueur0+" ("+categorieJoueur0+")");
		IG.changerImageJoueur(0,numImageJoueur0);
		IG.changerNomJoueur(1, nomJoueur1+" ("+categorieJoueur1+")");
		IG.changerImageJoueur(1,numImageJoueur1);
		IG.changerNomJoueur(2, nomJoueur2+" ("+categorieJoueur2+")");
		IG.changerImageJoueur(2,numImageJoueur2);
		
		// Place des objets sur le plateau
		int numObjet=0;
		int ligne = 0;
		int colonne = -1;
		while(numObjet<18) {
			colonne += 1;
			IG.placerObjetPlateau((numObjet++),ligne,colonne);
			if(colonne == 6) {
				ligne += 1;
				colonne = -1;
			}
		}
		
		IG.placerJoueurPrecis(0,3,0,1,0);
        IG.placerJoueurPrecis(1,3,6,1,2);
        
        
        
		IG.rendreVisibleFenetreJeu();
		IG.jouerUnSon(2); // On joue le son numéro 2
		IG.pause(300); // On attend 300 ms
		IG.jouerUnSon(2); // On joue de nouveau le son numéro 2
		
		// Affichage d'un message
		String message[]={
					"",
					"Bonjour !",
					"Cliquer pour continuer ...",
					""
		};
		
		IG.afficherMessage(message); // On change de message de la fenêtre de jeu
		IG.miseAJourAffichage(); // On effectue le rafraichissement de la fenêtre de jeu
		IG.attendreClic();  // On attend un clic de souris
		//1er clic
		// Changement des pièces sur le plateau
		for (int i =0;i<7;i++) {
			for(int j=0;j<7;j++) {
				IG.changerPiecePlateau(i,j,2,1);
			}
		}
		IG.changerPieceHorsPlateau(1,1);
		message[0]="";
		message[1]="Après le clic 1";
		message[2]="Cliquer pour continuer ...";
		message[3]="";
		IG.placerBilleSurPlateau(3,0,1,0,2);
		IG.placerJoueurPrecis(0,3,0,1,1);
		IG.placerBilleSurPlateau(3,6,1,2,2);
		IG.placerJoueurPrecis(1,3,6,1,1);
		
		IG.afficherMessage(message);
		IG.miseAJourAffichage();
		
		
		
		
		
		IG.attendreClic();
		//2eme clic
		for (int i =0;i<7;i++) {
			for(int j=0;j<7;j++) {
				IG.changerPiecePlateau(i,j,2,2);
			}
		}
		IG.changerPieceHorsPlateau(1,0);
		message[0]="";
		message[1]="Après le clic 2";
		message[2]="Cliquer pour continuer ...";
		message[3]="";
		
		IG.placerBilleSurPlateau(3,0,1,1,2);
		IG.placerJoueurPrecis(0,3,0,1,2);
		IG.placerBilleSurPlateau(3,6,1,1,2);
		IG.placerJoueurPrecis(1,3,6,1,0);
		
		IG.afficherMessage(message);
		IG.miseAJourAffichage();

		
		
		
		
		IG.attendreClic();
		//3eme clic
		for (int i =0;i<7;i++) {
			for(int j=0;j<7;j++) {
				IG.changerPiecePlateau(i,j,2,3);
			}
		}
		IG.changerPieceHorsPlateau(1,1);
		message[0]="";
		message[1]="Après le clic 3";
		message[2]="Cliquer pour continuer ...";
		message[3]="";
		
		IG.placerBilleSurPlateau(3,0,1,2,2);
		IG.placerJoueurPrecis(0,3,1,1,0);
		IG.placerBilleSurPlateau(3,6,1,0,2);
		IG.placerJoueurPrecis(1,3,5,1,2);
		
		IG.afficherMessage(message);
		IG.miseAJourAffichage();
		
		
		
		
		IG.attendreClic();
		//4eme clic
		for (int i =0;i<7;i++) {
			for(int j=0;j<7;j++) {
				IG.changerPiecePlateau(i,j,2,0);
			}
		}
		IG.changerPieceHorsPlateau(1,0);
		message[0]="";
		message[1]="Après le clic 4";
		message[2]="Cliquer pour continuer ...";
		message[3]="";
		
		IG.placerBilleSurPlateau(3,1,1,0,2);
		IG.placerJoueurPrecis(0,3,1,1,1);
		IG.placerBilleSurPlateau(3,5,1,2,2);
		IG.placerJoueurPrecis(1,3,5,1,1);
		
		IG.afficherMessage(message);
		IG.miseAJourAffichage();

		
		
		// Change la pièce hors du plateau
		IG.changerPieceHorsPlateau(1,0);
		IG.miseAJourAffichage();
		IG.attendreClic();
		
		message[0]="";
		message[1]="Cliquez sur une flèche";
		message[2]="pour quitter !";
		message[3]="";
		IG.afficherMessage(message);
		IG.afficherGagnant(0);
		IG.miseAJourAffichage();
		//6eme clic
		IG.attendreChoixEntree();
		message[0]="";
		message[1]="Arrêt du programme";
		message[2]="dans 2 secondes !";
		message[3]="";
		IG.afficherMessage(message);
		IG.miseAJourAffichage();
		IG.pause(2000);
		IG.fermerFenetreJeu();
		System.exit(0);
	}
}
