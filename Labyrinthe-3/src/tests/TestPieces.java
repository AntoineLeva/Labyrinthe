package tests;
import composants.Piece;
import grafix.interfaceGraphique.IG;


public class TestPieces {
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
		Piece[] piece = Piece.nouvellesPieces();
		for(int i =0;i<7;i++) {
			for(int j=0;j<7;j++) {
				IG.changerPiecePlateau(i, j, piece[i*7+j].getModelePiece(), piece[i*7+j].getOrientationPiece());
			}
		}
		piece[49].rotation();
		IG.changerPieceHorsPlateau(piece[49].getModelePiece(), piece[49].getOrientationPiece());
		IG.miseAJourAffichage();
		IG.attendreClic();
		//2eme clic
		for(int i=0;i<7;i++) {
			for(int j=0;j<7;j++) {
				piece[i*7+j].rotation();
				IG.changerPiecePlateau(i, j, piece[i*7+j].getModelePiece(), piece[i*7+j].getOrientationPiece());
			}
		}
		piece[49].rotation();
		IG.changerPieceHorsPlateau(piece[49].getModelePiece(), piece[49].getOrientationPiece());
		IG.miseAJourAffichage();
		System.out.println(piece[49]);
		
		
		IG.attendreClic();
		//3eme clic
		for(int i=0;i<7;i++) {
			for(int j=0;j<7;j++) {
				piece[i*7+j].rotation();
				IG.changerPiecePlateau(i, j, piece[i*7+j].getModelePiece(), piece[i*7+j].getOrientationPiece());
			}
		}
		piece[49].rotation();
		IG.changerPieceHorsPlateau(piece[49].getModelePiece(), piece[49].getOrientationPiece());
		IG.miseAJourAffichage();
		System.out.println(piece[49]);
		
		
		IG.attendreClic();
		//4eme clic
		for(int i=0;i<7;i++) {
			for(int j=0;j<7;j++) {
				piece[i*7+j].rotation();
				IG.changerPiecePlateau(i, j, piece[i*7+j].getModelePiece(), piece[i*7+j].getOrientationPiece());
			}
		}
		piece[49].rotation();
		IG.changerPieceHorsPlateau(piece[49].getModelePiece(), piece[49].getOrientationPiece());
		IG.miseAJourAffichage();
		System.out.println(piece[49]);
		
		IG.attendreClic();
		//5eme clic
		for(int i=0;i<7;i++) {
			for(int j=0;j<7;j++) {
				piece[i*7+j].rotation();
				IG.changerPiecePlateau(i, j, piece[i*7+j].getModelePiece(), piece[i*7+j].getOrientationPiece());
			}
		}
		piece[49].rotation();
		IG.changerPieceHorsPlateau(piece[49].getModelePiece(), piece[49].getOrientationPiece());
		IG.miseAJourAffichage();
		System.out.println(piece[49]);
		IG.attendreClic();
		IG.fermerFenetreJeu();
		System.exit(0);
	}
}
