package partie;

import composants.Objet;
import composants.Piece;
import composants.Plateau;
import composants.Utils;
import grafix.interfaceGraphique.IG;
import joueurs.Joueur;
import joueurs.JoueurOrdinateur;

public class Partie {
	static double version=1.1;


	private ElementsPartie elementsPartie; // Les Ã©lÃ©ments de la partie.

	/**
	 * 
	 * (02/06/2021 SB Finalisée)
	 * 
	 * Constructeur permettant de crÃ©er et d'initialiser une nouvelle partie.
	 */
	public Partie(){
		// Initialisation de la partie
		// On affiche l'ensemble des Ã©lÃ©ments
		parametrerEtInitialiser();
		Plateau plateauJeu = elementsPartie.getPlateau();
		Objet[] objetJeu = elementsPartie.getObjets();
		Joueur[] joueurJeu = elementsPartie.getJoueurs();
		//Affichage des stats des joueurs sur le côté
		for(int i=0;i<joueurJeu.length;i++) {
			IG.changerNomJoueur(joueurJeu[i].getNumJoueur(),joueurJeu[i].getNomJoueur()+" ("+joueurJeu[i].getCategorie()+")");
			IG.changerImageJoueur(joueurJeu[i].getNumJoueur(),joueurJeu[i].getNumeroImagePersonnage());
		}
		Piece pieceHorsPlateau = plateauJeu.placerPiecesAleatoirement();
		for(int i =0;i< 7;i++) {
			for(int j=0;j<7;j++) {
				IG.changerPiecePlateau(i, j, plateauJeu.getPiece(i,j).getModelePiece(), plateauJeu.getPiece(i,j).getOrientationPiece());
			}
		}
		IG.changerPieceHorsPlateau(pieceHorsPlateau.getModelePiece(),pieceHorsPlateau.getOrientationPiece());
		// Placement des joueurs sur le plateau
		for(int i =0;i < joueurJeu.length;i++) {
			IG.placerJoueurSurPlateau(joueurJeu[i].getNumeroImagePersonnage(),joueurJeu[i].getPosLigne(),joueurJeu[i].getPosColonne());
		}
		//Positionnement des objets sur le plateau
		for (int i=0;i<objetJeu.length;i++) {
			objetJeu[i].positionneObjet(objetJeu[i].getPosLignePlateau(),objetJeu[i].getPosColonnePlateau());
		}
		// Changement d'objets des joeurus
		for (int i=0;i<joueurJeu.length;i++){
			for(int j=0;j < joueurJeu[i].getObjetsJoueur().length;j++) {
				IG.changerObjetJoueur(i,joueurJeu[i].getObjetsJoueur()[j].getNumeroObjet(),j);
			}
		}
		String message[]={
				"",
				"",
				"Cliquer pour continuer ...",
				""
		};
		IG.afficherMessage(message); // On change de message de la fenêtre de jeu
		IG.rendreVisibleFenetreJeu();
	}

	/**
	 * MÃ©thode permettant de paramÃ¨trer et initialiser les Ã©lÃ©ments de la partie.
	 */
	private void parametrerEtInitialiser(){
		// Saisie des diffÃ©rents paramÃ¨tres
		Object parametresJeu[];
		parametresJeu=IG.saisirParametres();
		int nombreJoueurs=((Integer)parametresJeu[0]).intValue();
		IG.creerFenetreJeu("- Version "+version, nombreJoueurs);
		// CrÃ©ation des joueurs
		Joueur joueurs[]=Joueur.nouveauxJoueurs(parametresJeu);
		// CrÃ©ation des Ã©lÃ©ments de la partie
		elementsPartie=new ElementsPartie(joueurs);
	}


	/**
	 * 
	 * (02/06/2021 SB Finalisée)
	 * 
	 * MÃ©thode permettant de lancer une partie.
	 */
	public void lancer(){
		IG.attendreClic();
		boolean fini = false;
		int joueur = 0;
		String message[]={
				"",
				"",
				"",
				""
		};
		
		while(fini == false) {
			joueur = joueur%3;
			IG.changerJoueurSelectionne(joueur);
			message[0]="Au tour du joueur";
			message[1]=elementsPartie.getJoueurs()[joueur].getNomJoueur();
			message[2]="Selectionnez une flèche";
			message[3]="Pour placer une nouvelle pièce";
			IG.afficherMessage(message); // On change de message de la fenêtre de jeu
			IG.miseAJourAffichage(); // On effectue le rafraichissement de la fenêtre de jeu
			if(elementsPartie.getJoueurs()[joueur].getCategorie() == "Humain") {
				elementsPartie.insertionPieceLibre(IG.attendreChoixEntree());
				IG.deselectionnerFleche();
				IG.miseAJourAffichage(); // On effectue le rafraichissement de la fenêtre de jeu
				int[] caseChoisi = elementsPartie.getJoueurs()[joueur].choisirCaseArrivee(elementsPartie);
				if(elementsPartie.getPlateau().calculeChemin(elementsPartie.getJoueurs()[joueur].getPosLigne(), elementsPartie.getJoueurs()[joueur].getPosColonne(), caseChoisi[0], caseChoisi[1]) != null) {
					elementsPartie.getJoueurs()[joueur].setPosition(caseChoisi[0], caseChoisi[1]);
					IG.placerJoueurSurPlateau(joueur, caseChoisi[0], caseChoisi[1]);
					IG.miseAJourAffichage(); // On effectue le rafraichissement de la fenêtre de jeu
				}
			}
			else {
				elementsPartie.insertionPieceLibre(Utils.genererEntier(27));
				if(elementsPartie.getJoueurs()[joueur].getCategorie() == "OrdiType1") {
					int cpt = 0;
					int[][][] cheminPossible = new int[49][49][2];
					for(int i = 0;i<7;i++) {
						for(int j=0;j<7;j++) {
							int[][] chemin = elementsPartie.getPlateau().calculeChemin(elementsPartie.getJoueurs()[joueur].getPosLigne(), elementsPartie.getJoueurs()[joueur].getPosColonne(), i, j);
							if(chemin != null){
								cheminPossible[cpt] = chemin;
								cpt++;
							}
						}
					}
					int alea = Utils.genererEntier(cpt-1);
					int[] caseChoisi = new int[2];
					caseChoisi[0] = cheminPossible[alea][cheminPossible[alea].length-1][0];
					caseChoisi[1] = cheminPossible[alea][cheminPossible[alea].length-1][1];
					elementsPartie.getJoueurs()[joueur].setPosition(caseChoisi[0], caseChoisi[1]);
					IG.placerJoueurSurPlateau(joueur, caseChoisi[0], caseChoisi[1]);
					IG.miseAJourAffichage(); // On effectue le rafraichissement de la fenêtre de jeu
				}
				else if(elementsPartie.getJoueurs()[joueur].getCategorie() == "OrdiType2") {
					int[] caseChoisi = new int[2];
					caseChoisi[0] = elementsPartie.getJoueurs()[joueur].getProchainObjet().getPosLignePlateau();
					caseChoisi[1] = elementsPartie.getJoueurs()[joueur].getProchainObjet().getPosColonnePlateau();
					if(elementsPartie.getPlateau().calculeChemin(elementsPartie.getJoueurs()[joueur].getPosLigne(), elementsPartie.getJoueurs()[joueur].getPosColonne(), caseChoisi[0], caseChoisi[1]) != null) {
						elementsPartie.getJoueurs()[joueur].setPosition(caseChoisi[0], caseChoisi[1]);
						IG.placerJoueurSurPlateau(joueur, caseChoisi[0], caseChoisi[1]);
						IG.miseAJourAffichage(); // On effectue le rafraichissement de la fenêtre de jeu
					}
				}
				else if(elementsPartie.getJoueurs()[joueur].getCategorie() == "OrdiType3") {
					int cpt = 0;
					int[][][] cheminPossible = new int[49][49][2];
					for(int i = 0;i<7;i++) {
						for(int j=0;j<7;j++) {
							int[][] chemin = elementsPartie.getPlateau().calculeChemin(elementsPartie.getJoueurs()[joueur].getPosLigne(), elementsPartie.getJoueurs()[joueur].getPosColonne(), i, j);
							if(chemin != null){
								cheminPossible[cpt] = chemin;
								cpt++;
							}
						}
					}
					double x = Math.pow(cheminPossible[0][cheminPossible[0].length-1][0]-elementsPartie.getJoueurs()[joueur].getProchainObjet().getPosLignePlateau(), 2);
					double y = Math.pow(cheminPossible[0][cheminPossible[0].length-1][1]-elementsPartie.getJoueurs()[joueur].getProchainObjet().getPosColonnePlateau(), 2);
					double distancePoint = x+y;
					Joueur joueurIA = elementsPartie.getJoueurs()[joueur];
					int[] point = new int[2];
					point[0] = cheminPossible[0][cheminPossible[0].length-1][0];
					point[1] = cheminPossible[0][cheminPossible[0].length-1][1];
					for(int i=1;i < cheminPossible.length;i++) {
						x = Math.pow(cheminPossible[i][cheminPossible[i].length-1][0]-joueurIA.getProchainObjet().getPosLignePlateau(), 2);
						y = Math.pow(cheminPossible[i][cheminPossible[i].length-1][1]-joueurIA.getProchainObjet().getPosColonnePlateau(), 2);
						if(x+y < distancePoint) {
							distancePoint = x+y;
							point[0] = cheminPossible[i][cheminPossible[i].length-1][0];
							point[1] = cheminPossible[i][cheminPossible[i].length-1][1];
						}
					}
					elementsPartie.getJoueurs()[joueur].setPosition(point[0], point[1]);
					IG.placerJoueurSurPlateau(joueur, point[0], point[1]);
					IG.miseAJourAffichage(); // On effectue le rafraichissement de la fenêtre de jeu
				}
			}
			int x = elementsPartie.getJoueurs()[joueur].getPosLigne();
			int y = elementsPartie.getJoueurs()[joueur].getPosColonne();
			Objet objetAct = elementsPartie.getJoueurs()[joueur].getProchainObjet();
			for(int i=0; i < elementsPartie.getJoueurs()[joueur].getObjetsJoueur().length;i++) {
				System.out.println(elementsPartie.getJoueurs()[joueur].getObjetsJoueur()[i]);
			}
			System.out.println();
			System.out.println(objetAct);
			if(x == objetAct.getPosLignePlateau() && y == objetAct.getPosColonnePlateau()) {
				
				IG.changerObjetJoueurAvecTransparence(joueur,elementsPartie.getJoueurs()[joueur].getObjetsJoueur()[elementsPartie.getJoueurs()[joueur].getNombreObjetsRecuperes()].getNumeroObjet(),elementsPartie.getJoueurs()[joueur].getNombreObjetsRecuperes());
				IG.enleverObjetPlateau(x, y);
				elementsPartie.getJoueurs()[joueur].getObjetsJoueur()[elementsPartie.getJoueurs()[joueur].getNombreObjetsRecuperes()].enleveDuPlateau();
				elementsPartie.getJoueurs()[joueur].recupererObjet();
				
				IG.miseAJourAffichage();
			}
			System.out.println(elementsPartie.getJoueurs()[joueur].getNombreObjetsRecuperes());
			if(elementsPartie.getJoueurs()[joueur].getNombreObjetsRecuperes() == 6) {
				fini = true;
			}
			if(fini == false) {
				joueur++;
			}
		}
		
		IG.afficherGagnant(joueur);
		message[0]="Félicitations !";
		message[1]=elementsPartie.getJoueurs()[joueur].getNomJoueur()+ " A gagné !";
		message[2]="";
		message[3]="Cliquez pour quitter";
		IG.afficherMessage(message);
		IG.miseAJourAffichage();
		IG.attendreClic();
		IG.fermerFenetreJeu();
		System.exit(0);
		
	}

	/**
	 * 
	 * Programme principal permettant de lancer le jeu.
	 * 
	 * @param args Les arguments du programmes.
	 */
	public static void main(String[] args) {
		while(true){
			Partie partie=new Partie();
			partie.lancer();
			break;
		}
	}

}