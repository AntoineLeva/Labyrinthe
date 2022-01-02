package composants;


/**
 *
 * Cette classe permet de représenter les différentes pièces du jeu.
 *
 */
abstract public class Piece {

    private int modelePiece; 		// Le modèle de la pièce
    private int orientationPiece; 	// L'orientation de la pièce
    private boolean[] pointsEntree; // Les points d'entrée indice 0 pour le haut, 1 pour la droite, 2 pour le bas et 3 pour la gauche.

    /**
     * A Faire (01/05/2021 Antoine Finalis�e)
     *
     * Constructeur permettant de créer une pièce d'un modèle avec l'orientation 0.
     * @param modelePiece Le modèle de la pièce.
     * @param pointEntreeHaut Un booléen indiquant si la pièce a un point d'entrée en haut.
     * @param pointEntreeDroite Un booléen indiquant si la pièce a un point d'entrée �  droite.
     * @param pointEntreeBas Un booléen indiquant si la pièce a un point d'entrée en bas.
     * @param pointEntreeGauche Un booléen indiquant si la pièce a un point d'entrée �  gauche.
     */
    public Piece(int modelePiece,boolean pointEntreeHaut,boolean pointEntreeDroite,boolean pointEntreeBas,boolean pointEntreeGauche){

        this.modelePiece = modelePiece;
        this.pointsEntree = new boolean[4];
        pointsEntree[0] = pointEntreeHaut;
        pointsEntree[1] = pointEntreeDroite;
        pointsEntree[2] = pointEntreeBas;
        pointsEntree[3] = pointEntreeGauche;
        this.orientationPiece = 0;

    }

    /**
     * Méthoide retournant un String représentant la pièce.
     */
    @Override
    public String toString() {
        return "[m:"+modelePiece+"|o:"+orientationPiece+"|pe:"+pointsEntree[0]+" "+pointsEntree[1]+" "+pointsEntree[2]+" "+pointsEntree[3]+"]";
    }

    /**
     * A Faire (01/05/2021 Lucas Finalis�e)
     *
     * Méthode permettant de rotationner une pièce dans le sens d'une horloge.
     */
    public void rotation(){
        if(modelePiece == 1){
            if(orientationPiece == 1){
                orientationPiece = -1;
            }
        }
        else{
            if(orientationPiece == 3){
                orientationPiece = -1;
            }
        }
        orientationPiece+= 1;
        this.setOrientation(this.orientationPiece);
    }

    // (01/05/21 Antoine Finalis�e)
    public void setModelePiece(int newModelePiece) {
        this.modelePiece = newModelePiece;
    }

    // (01/05/21 Antoine Finalis�e)
    public void setPointsEntree(boolean[] pointsEntree) {
        this.pointsEntree = pointsEntree;
    }

    /**
     * A Faire (01/05/2021 Allan Ponchaut EnCours)
     *
     * Méthode permettant d'orienter une pièce vers une orientation spécifique.
     * @param orientationPiece Un entier correspondant �  la nouvelle orientation de la pièce.
     */
    public void setOrientation(int orientationPiece){
        if(getModelePiece() == 0) {
            if(orientationPiece == 0) {
                this.setPointsEntree(new boolean[]{false, true, true, false});
                this.orientationPiece = 0;
            }
            else if(orientationPiece == 1) {
                this.setPointsEntree(new boolean[]{false, false, true, true});
                this.orientationPiece = 1;
            }
            else if(orientationPiece == 2) {
                this.setPointsEntree(new boolean[]{true, false, false, true});
                this.orientationPiece = 2;
            }
            else if(orientationPiece == 3) {
                this.setPointsEntree(new boolean[]{true, true, false, false});
                this.orientationPiece = 3;
            }
        }
        else if(getModelePiece() == 1) {
            if(orientationPiece == 0) {
                this.setPointsEntree(new boolean[]{true, false, true, false});
                this.orientationPiece = 0;
            }
            else if(orientationPiece == 1) {
                this.setPointsEntree(new boolean[]{false, true, false, true});
                this.orientationPiece = 1;
            }
        }
        else if(getModelePiece() == 2) {
            if(orientationPiece == 0) {
                this.setPointsEntree(new boolean[]{true, true, false, true});
                this.orientationPiece = 0;
            }
            else if(orientationPiece == 1) {
                this.setPointsEntree(new boolean[]{true, true, true, false});
                this.orientationPiece = 1;
            }
            else if(orientationPiece == 2) {
                this.setPointsEntree(new boolean[]{false, true, true, true});
                this.orientationPiece = 2;
            }
            else if(orientationPiece == 3) {
                this.setPointsEntree(new boolean[]{true, false, true, true});
                this.orientationPiece = 3;
            }
        }
    }

    /**
     * A Faire (01/05/2021 Lucas Finalis�e)
     *
     * Méthode retournant le modèle de la pièce.
     * @return Un entier corrspondant au modèle de la pièce.
     */
    public int getModelePiece() {
        return modelePiece;
    }

    /**
     * A Faire (01/05/21 Allan Ponchaut Finalis�e)
     *
     * Méthode retournant l'orientation de la pièce.
     * @return un entier retournant l'orientation de la pièce.
     */
    public int getOrientationPiece() {
        return orientationPiece;
    }

    /**
     * A Faire (01/05/21 Allan Ponchaut Finalis�e)
     *
     * Méthode indiquant si il existe un point d'entrée �  une certaine position (0: en haut, 1: �  droite, 2: en bas, 3: �  gauche).
     * @param pointEntree L'indice/la position du point d'entrée.
     * @return true si il y a un point d'entrée, sinon false.
     */
    public boolean getPointEntree(int pointEntree){
        return pointsEntree[pointEntree];
    }

    public boolean[] getPointsEntree(){
        return this.pointsEntree;
    }

    /**
     * A Faire (Quand Qui Statut)
     *
     * Méthode permettant de créer un tableau contenant toutes les pièces du jeu (les 50 pièces).
     * Le tableau contiendra 20 pièces du modèle 0, 12 pièces du modèle 1 et 18 pièces du modèle 2.
     * L'orientation de chaque pièce sera aléatoire.
     * @return Un tableau contenant toutes les pièces du jeu.
     */
    public static Piece[] nouvellesPieces(){
        Piece pieces[]= new Piece[50];
        int i = 0;
        int cpt0 = 0;
        int cpt1 = 0;
        int cpt2 = 0;
        // A Compléter (A Faire apr�s les classes PieceM0, PieceM1 et PieceM2)
        while(i < 50) {
        	int alea = Utils.genererEntier(2);
        	if(alea == 0) {
        		if(cpt0 < 20) {
        			pieces[i] = new PieceM0();
        			pieces[i].setOrientation(Utils.genererEntier(3));
        			cpt0 += 1;
        			i += 1;
        		}
        	}
        	else if(alea == 1) {
        		if(cpt1 < 12) {
        			pieces[i] = new PieceM1();
        			pieces[i].setOrientation(Utils.genererEntier(1));
        			cpt1 += 1;
        			i += 1;
        		}
        	}
        	else if(alea == 2) {
        		if(cpt2 < 18) {
        			pieces[i] = new PieceM2();
        			pieces[i].setOrientation(Utils.genererEntier(3));
        			cpt2 += 1;
        			i += 1;
        		}
        	}
        }
        return pieces;
    }

    /**
     * Méthode permettant de créer une copie de la pièce (un nouvelle objet Java).
     * @return Une copie de la pièce.
     */
    public abstract Piece copy();
}