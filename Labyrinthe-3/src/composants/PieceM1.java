package composants;

/**
 *
 * Cette classe permet de représenter les pièces du jeu de modèle 1.
 *
 */
public class PieceM1 extends Piece {
	private int orientationPiece;
    /**
     * A Faire (01/05/21 Allan Ponchaut Finalis�e)
     *
     * Constructeur permettant de construire une pièce de modèle 1 et d'orientation 0.
     */
    public PieceM1() {
        // A Modifier !!!
        super(1,true,false,true,false);
        this.orientationPiece = 0;
    }
    
    public PieceM1(int orientationPiece) {
    	super(1,false,true,true,false);
    	this.orientationPiece = orientationPiece;
    }

    /**
     * A Faire (01/05/2021 Lucas Finalis�e)
     *
     * Méthode permettant de créer une copie de la pièce (un nouvelle objet Java).
     * @return Une copie de la pièce.
     */
    public Piece copy(){
        Piece piece=null;
        piece.setModelePiece(this.getModelePiece());
        piece.setOrientation(this.getOrientationPiece());
        piece.setPointsEntree(this.getPointsEntree());
        return piece;
    }
}