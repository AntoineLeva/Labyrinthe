package partie;
import grafix.interfaceGraphique.IG;
import composants.Objet;
import composants.Piece;
import composants.Plateau;
import composants.Utils;
import grafix.interfaceGraphique.IG;
import joueurs.Joueur;
import joueurs.JoueurHumain;

/**
 * 
 * Cette classe permet de représenter un ensemble d'élements composant une partie de jeu.
 * 
 */
public class ElementsPartie {

	private Joueur[] joueurs; 	// Les joueurs de la partie.
	private Objet[] objets; 	// Les 18 objets de la partie dans l'ordre de leurs numéros.
	private Plateau plateau; 	// Le plateau des pièces.
	private Piece pieceLibre; 	// La pièce libre.
	private int nombreJoueurs; 	// Le nombre de joueurs.

	/**
	 * 
	 * (29/05/2021 SB Finalis�e)
	 *  
	 * Constructeur permettant de générer et d'initialiser l'ensemble des éléments d'une partie (sauf les joueurs qui sont donnés en paramètres).
	 * 
	 * Un plateau est créé en placant 49 oièces de manière aléatoire (utilisation de la méthode placerPiecesAleatoierment de la classe Plateau).
	 * La pièce restante (celle non présente sur le plateau) est affectée �  la pièce libre.
	 * Les 18 objets sont créés avec des positions aléatoires sur le plateau (utilisation de la méthode Objet.nouveauxObjets)
	 * et distribuées aux différents joueurs (utilisation de la méthode attribuerObjetsAuxJoueurs).
	 * 
	 * @param joueurs Les joueurs de la partie. Les objets des joueurs ne sont pas encore attribués (c'est au constructeur de le faire).
	 */
	public ElementsPartie(Joueur[] joueurs) {
		this.joueurs = joueurs;
		nombreJoueurs=joueurs.length;
		this.plateau = new Plateau();
		this.pieceLibre = this.plateau.placerPiecesAleatoirement();
		this.objets = Objet.nouveauxObjets();
		attribuerObjetsAuxJoueurs();
	}

	/**
	 * Un simple constructeur.
	 * 
	 * @param joueurs Les joueurs de la partie.
	 * @param objets Les 18 objets de la partie.
	 * @param plateau Le plateau de jeu.
	 * @param pieceLibre La pièce libre (la pièce hors plateau).
	 */
	public ElementsPartie(Joueur[] joueurs,Objet[] objets,Plateau plateau,Piece pieceLibre) {
		this.joueurs=joueurs;
		nombreJoueurs=joueurs.length;
		this.objets=objets;
		this.plateau=plateau;
		this.pieceLibre=pieceLibre;
		
	}

	/**
     * (29/05/2021 Lucas Finalis�
     * 
     * Méthode permettant d'attribuer les objets aux différents joueurs de manière aléatoire.
     */
    private void attribuerObjetsAuxJoueurs(){
        Objet[] objetsJoueur = new Objet[18];
        int[] tabEntier = Utils.genereTabIntAleatoirement(18);
        for (int i =0; i < objetsJoueur.length;i++) {
        	objetsJoueur[i] = this.objets[tabEntier[i]];
        }
        for (int j = 0; j < nombreJoueurs; j++) {
        	this.joueurs[j].setObjetsJoueur(objetsJoueur);
        }
    }

	/**
     * (29/05/2021 Allan Ponchaut Finalis�e)
     * 
     * M�thode permettant de r�cup�rer les joueurs de la partie.
     * @return Les joueurs de la partie.
     */
    public Joueur[] getJoueurs() {
        return this.joueurs;
    }


    /**
     * (29/05/2021 Allan Ponchaut Finalis�e)
     * 
     * M�thode permettant de r�cup�rer les pi�ces de la partie.
     * @return Les objets de la partie.
     */
    public Objet[] getObjets() {
        return this.objets;
    }


    /**
     * (29/05/2021 Allan Ponchaut Finalis�e)
     * 
     * M�thode permettant de r�cup�rer le plateau de pi�ces de la partie.
     * @return Le plateau de pi�ces.
     */
    public Plateau getPlateau() {
        return this.plateau;
    }


    /**
     * (29/05/2021 Antoine Finalis�)
     * 
     * Méthode permettant de récupérer la pièce libre de la partie.
     * @return La pièce libre.
     */
    public Piece getPieceLibre() {
        return this.pieceLibre; 
    }


    /**
     * (29/05/2021 Antoine Finalis�)
     * 
     * Méthode permettant de récupérer le nombre de joueurs de la partie.
     * @return Le nombre de joueurs.
     */
    public int getNombreJoueurs() {
        return this.nombreJoueurs; 
    }


	/**
	 * (29/05/2021 SB AP EnCours)
	 * 
	 * Méthode modifiant les différents éléments de la partie suite �  l'insertion de la pièce libre dans le plateau.
	 * 
	 * @param choixEntree L'entrée choisie pour réaliser l'insertion (un nombre entre 0 et 27).
	 */
	public void insertionPieceLibre(int choixEntree){
		Objet[] objets = new Objet[7];
		if(choixEntree < 7) {
			int cpt = 6;
			Piece stockPiece = plateau.getPiece(6, choixEntree);
			while(cpt > 0) {
				plateau.positionnePiece(plateau.getPiece(cpt-1, choixEntree),cpt,choixEntree);
				IG.changerPiecePlateau(cpt, choixEntree, plateau.getPiece(cpt, choixEntree).getModelePiece(),plateau.getPiece(cpt, choixEntree).getOrientationPiece());
				cpt--;
			}
			
			for(int i=0;i < this.objets.length;i++) {
				if(this.objets[i].getPosColonnePlateau() == choixEntree) {
					objets[6-this.objets[i].getPosLignePlateau()] = this.objets[i];
				}
			}
			System.out.println();
			
			for(int i =0;i<objets.length;i++) {
				int lig = 0;
				int col = 0;
				if(objets[i] != null) {
					if(objets[i].getPosLignePlateau() != 6) {
						lig = objets[i].getPosLignePlateau()+1;
						col = objets[i].getPosColonnePlateau();
						IG.enleverObjetPlateau(objets[i].getPosLignePlateau(), objets[i].getPosColonnePlateau());
					}
					else {
						lig = 0;
						col = objets[i].getPosColonnePlateau();
						IG.enleverObjetPlateau(objets[i].getPosLignePlateau(), objets[i].getPosColonnePlateau());
					}
					objets[i].positionneObjet(lig, col);
				}
			}
			
			for(int i=0;i< this.joueurs.length;i++) {
				if(this.joueurs[i].getPosColonne() == choixEntree) {
					if(this.joueurs[i].getPosLigne() != 6) {
						this.joueurs[i].setPosition(this.joueurs[i].getPosLigne()+1, this.joueurs[i].getPosColonne());
						IG.placerJoueurSurPlateau(i, this.joueurs[i].getPosLigne(), this.joueurs[i].getPosColonne());
					}
					else {
						this.joueurs[i].setPosition(0, this.joueurs[i].getPosColonne());
						IG.placerJoueurSurPlateau(i, this.joueurs[i].getPosLigne(), this.joueurs[i].getPosColonne());
					}
				}
			}
			plateau.positionnePiece(pieceLibre,0,choixEntree);
			this.pieceLibre = stockPiece;
			IG.changerPiecePlateau(cpt, choixEntree, plateau.getPiece(cpt, choixEntree).getModelePiece(),plateau.getPiece(cpt, choixEntree).getOrientationPiece());
			IG.changerPieceHorsPlateau(pieceLibre.getModelePiece(), pieceLibre.getOrientationPiece());
			System.out.println("----------");
			for(int i =0;i<objets.length;i++) {
				System.out.println(i + " "+objets[i]);
			}
		}
		
		
		/*-----------------------------------------------------------------------------------------*/
		
		
		else if(choixEntree < 14) {
			int cpt = 0;
			Piece stockPiece = plateau.getPiece(choixEntree%7, 0);
			while(cpt < 6) {
				plateau.positionnePiece(plateau.getPiece(choixEntree%7, cpt+1),choixEntree%7,cpt);
				IG.changerPiecePlateau(choixEntree%7,cpt, plateau.getPiece(choixEntree%7,cpt).getModelePiece(),plateau.getPiece(choixEntree%7,cpt).getOrientationPiece());
				cpt++;
			}
			
			for(int i=0;i < this.objets.length;i++) {
				if(this.objets[i].getPosLignePlateau() == choixEntree%7) {
					objets[this.objets[i].getPosColonnePlateau()] = this.objets[i];
				}
			}
			System.out.println();
			for(int i =0;i<objets.length;i++) {
				int lig = 0;
				int col = 0;
				if(objets[i] != null) {
					if(objets[i].getPosColonnePlateau() != 0) {
						lig = objets[i].getPosLignePlateau();
						col = objets[i].getPosColonnePlateau()-1;
						IG.enleverObjetPlateau(objets[i].getPosLignePlateau(), objets[i].getPosColonnePlateau());
					}
					else {
						lig = objets[i].getPosLignePlateau();
						col = 6;
						IG.enleverObjetPlateau(objets[i].getPosLignePlateau(), objets[i].getPosColonnePlateau());
					}
					objets[i].positionneObjet(lig, col);
				}
			}
			System.out.println("----------");
			for(int i =0;i<objets.length;i++) {
				System.out.println(i + " "+objets[i]);
			}
			for(int i=0;i < this.joueurs.length;i++) {
				if(this.joueurs[i].getPosLigne() == choixEntree%7) {
					if(this.joueurs[i].getPosColonne() != 0) {
						this.joueurs[i].setPosition(this.joueurs[i].getPosLigne(), this.joueurs[i].getPosColonne()-1);
						IG.placerJoueurSurPlateau(i, this.joueurs[i].getPosLigne(), this.joueurs[i].getPosColonne());
					}
					else {
						this.joueurs[i].setPosition(this.joueurs[i].getPosLigne(),6);
						IG.placerJoueurSurPlateau(i, this.joueurs[i].getPosLigne(), this.joueurs[i].getPosColonne());
					}
				}
			}
			plateau.positionnePiece(pieceLibre,choixEntree%7, 6);
			this.pieceLibre = stockPiece;
			IG.changerPiecePlateau(choixEntree%7,6, plateau.getPiece(choixEntree%7,cpt).getModelePiece(),plateau.getPiece(choixEntree%7,cpt).getOrientationPiece());
			IG.changerPieceHorsPlateau(pieceLibre.getModelePiece(), pieceLibre.getOrientationPiece());
		}
		
		/*-----------------------------------------------------------------------------------------*/

		else if(choixEntree < 21) {
			int cpt = 0;
			Piece stockPiece = plateau.getPiece(0, 6-choixEntree%7);
			while(cpt < 6) {
				plateau.positionnePiece(plateau.getPiece(cpt+1, 6-choixEntree%7),cpt,6-choixEntree%7);
				IG.changerPiecePlateau(cpt, 6-choixEntree%7, plateau.getPiece(cpt, 6-choixEntree%7).getModelePiece(), plateau.getPiece(cpt, 6-choixEntree%7).getOrientationPiece());
				cpt++;
			}
			
			for(int i=0;i < this.objets.length;i++) {
				if(this.objets[i].getPosColonnePlateau() == 6-choixEntree%7) {
					objets[this.objets[i].getPosLignePlateau()] = this.objets[i];
				}
			}
			for(int i =0;i<objets.length;i++) {
				int lig = 0;
				int col = 0;
				if(objets[i] != null) {
					if(objets[i].getPosLignePlateau() != 0) {
						lig = objets[i].getPosLignePlateau()-1;
						col = objets[i].getPosColonnePlateau();
						IG.enleverObjetPlateau(objets[i].getPosLignePlateau(), objets[i].getPosColonnePlateau());
					}
					else {
						lig = 6;
						col = objets[i].getPosColonnePlateau();
						IG.enleverObjetPlateau(objets[i].getPosLignePlateau(), objets[i].getPosColonnePlateau());
					}
					objets[i].positionneObjet(lig, col);
				}
			}
			System.out.println("----------");
			for(int i =0;i<objets.length;i++) {
				System.out.println(i + " "+objets[i]);
			}
			
			for(int i=0;i < this.joueurs.length;i++) {
				if(this.joueurs[i].getPosColonne() == 6-choixEntree%7) {
					if(this.joueurs[i].getPosLigne() != 0) {
						this.joueurs[i].setPosition(this.joueurs[i].getPosLigne()-1, this.joueurs[i].getPosColonne());
						IG.placerJoueurSurPlateau(i, this.joueurs[i].getPosLigne(), this.joueurs[i].getPosColonne());
					}
					else {
						this.joueurs[i].setPosition(6,this.joueurs[i].getPosColonne());
						IG.placerJoueurSurPlateau(i, this.joueurs[i].getPosLigne(), this.joueurs[i].getPosColonne());
					}
				}
			}
			plateau.positionnePiece(pieceLibre,6, 6-choixEntree%7);
			this.pieceLibre = stockPiece;
			IG.changerPiecePlateau(6,6-choixEntree%7,plateau.getPiece(cpt, 6-choixEntree%7).getModelePiece(),plateau.getPiece(cpt, 6-choixEntree%7).getOrientationPiece());
			IG.changerPieceHorsPlateau(pieceLibre.getModelePiece(), pieceLibre.getOrientationPiece());
		}
		/*-----------------------------------------------------------------------------------------*/
		else if(choixEntree < 28) {
			int cpt = 6;
			Piece stockPiece = plateau.getPiece(6-choixEntree%7,6);
			while(cpt > 0) {
				plateau.positionnePiece(plateau.getPiece(6-choixEntree%7, cpt-1),6-choixEntree%7,cpt);
				IG.changerPiecePlateau(6-choixEntree%7, cpt, plateau.getPiece(6-choixEntree%7, cpt).getModelePiece(), plateau.getPiece(6-choixEntree%7, cpt).getOrientationPiece());
				cpt--;
			}
			
			for(int i=0;i < this.objets.length;i++) {
				if(this.objets[i].getPosLignePlateau() == 6-choixEntree%7) {
					objets[6-this.objets[i].getPosColonnePlateau()] = this.objets[i];
				}
			}
			
			for(int i =0;i<objets.length;i++) {
				int lig = 0;
				int col = 0;
				if(objets[i] != null) {
					if(objets[i].getPosColonnePlateau() != 6) {
						lig = objets[i].getPosLignePlateau();
						col = objets[i].getPosColonnePlateau()+1;
						IG.enleverObjetPlateau(objets[i].getPosLignePlateau(), objets[i].getPosColonnePlateau());
					}
					else {
						lig = objets[i].getPosLignePlateau();
						col = 0;
						IG.enleverObjetPlateau(objets[i].getPosLignePlateau(), objets[i].getPosColonnePlateau());
					}
					objets[i].positionneObjet(lig, col);
				}
			}
			System.out.println("----------");
			for(int i =0;i<objets.length;i++) {
				System.out.println(i + " "+objets[i]);
			}
			for(int i=0;i<this.joueurs.length;i++) {
				if(this.joueurs[i].getPosLigne() == 6-choixEntree%7) {
					if(this.joueurs[i].getPosColonne() != 6) {
						this.joueurs[i].setPosition(this.joueurs[i].getPosLigne(), this.joueurs[i].getPosColonne()+1);
						IG.placerJoueurSurPlateau(i, this.joueurs[i].getPosLigne(), this.joueurs[i].getPosColonne());
					}
					else {
						this.joueurs[i].setPosition(this.joueurs[i].getPosLigne(),0);
						IG.placerJoueurSurPlateau(i, this.joueurs[i].getPosLigne(), this.joueurs[i].getPosColonne());
					}
				}
			}
			
			
			plateau.positionnePiece(pieceLibre, 6-choixEntree%7, 0);
			this.pieceLibre = stockPiece;
			IG.changerPiecePlateau(6-choixEntree%7, 0, plateau.getPiece(6-choixEntree%7, 0).getModelePiece(),plateau.getPiece(6-choixEntree%7, 0).getOrientationPiece());
			IG.changerPieceHorsPlateau(pieceLibre.getModelePiece(), pieceLibre.getOrientationPiece());
		}
	}


	/**
	 * Méthode retournant une copie.
	 * 
	 * @return Une copie des éléments.
	 */
	public ElementsPartie copy(){
		Objet[] nouveauxObjets=new Objet[(this.objets).length];
		for (int i=0;i<objets.length;i++)
			nouveauxObjets[i]=(this.objets[i]).copy();
		Joueur[] nouveauxJoueurs=new Joueur[nombreJoueurs];
		for (int i=0;i<nombreJoueurs;i++)
			nouveauxJoueurs[i]=(this.joueurs[i]).copy(objets);
		Plateau nouveauPlateau=(this.plateau).copy();
		Piece nouvellePieceLibre=(this.pieceLibre).copy();
		ElementsPartie nouveauxElements=new  ElementsPartie(nouveauxJoueurs,nouveauxObjets,nouveauPlateau,nouvellePieceLibre); 
		return nouveauxElements;
	}
}