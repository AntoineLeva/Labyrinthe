package composants;

import java.util.Arrays;

/**
 * Cette classe permet de gérer un plateau de jeu constitué d'une grille de pièces (grille de 7 lignes sur 7 colonnes).
 *
 */
public class Plateau {

	private Piece plateau[][]; // La grille des pièces.

	/**
	 * (14/05/21 SB Finalis�e)
	 * 
	 * Constructeur permettant de construire un plateau vide (sans pièces) et d'une taille de 7 lignes sur 7 colonnes.
	 */
	public Plateau() {
		this.plateau = new Piece[7][7];
	}
	
	
	/**
	 * (14/05/21 SB Finalis�e)
	 * 
	 * Méthode permettant de placer une pièce sur le plateau.
	 * 
	 * @param piece La pièce �  placer.
	 * @param lignePlateau La ligne du plateau sur laquelle sera placée la pièce (un entier entre 0 et 6).
	 * @param colonnePlateau La colonne du plateau sur laquelle sera placée la pièce (une entier entre 0 et 6).
	 */
	public void positionnePiece(Piece piece,int lignePlateau,int colonnePlateau){
		this.plateau[lignePlateau][colonnePlateau] = piece;
	}

	/**
	 * (14/05/21 SB Finalis�e)
	 * 
	 * Méthode retournant une pièce se trouvant sur le plateau �  un emplacement spécifique.
	 * 
	 * @param lignePlateau La ligne du plateau  (un entier entre 0 et 6).
	 * @param colonnePlateau La colonne du plateau (un entier entre 0 et 6).
	 * @return La pièce se trouvant sur la ligne lignePlateau et la colonne colonnePlateau. Si il n'y a pas de pièce, null est retourné.
	 */
	public Piece getPiece(int lignePlateau,int colonnePlateau){
		return this.plateau[lignePlateau][colonnePlateau];
	}

	/**
	 * 
	 * (14/05/21 SB Finalis�e)
	 *  
	 * Méthode permettant de placer aléatoirment 49 pièces du jeu sur le plateau.
	 * L'orientation des pièces est aléatoire. Les pièces utilisées doivent être des nouvelles pièces générées �  partir de la méthode Piece.nouvellesPieces.
	 * Parmi les 50 pièces du jeu, la pièce qui n'a pas été placée sur le plateau est retournée par la méthode.
	 * 
	 * @return La seule pièce qui n'a pas été placée sur le plateau
	 */
	public Piece placerPiecesAleatoirement(){
		// A Compléter
		Piece[] pieces = Piece.nouvellesPieces();
		for(int i = 0;i < this.plateau.length;i++) {
			for(int j = 0;j < this.plateau[i].length;j++) {
				this.plateau[i][j] = pieces[i*7+j];
			}
		}
		return pieces[49]; // A Modfier
	}

	/**
	 * 
	 * Méthode utilitaire permettant de tester si les positions passées en paramètre sont les positions de deux cases différentes et adjacentes d'une grille de 7 lignes sur 7 colonnes.
	 *  
	 * @param posLigCase1 Un entier quelconque.
	 * @param posColCase1 Un entier quelconque.
	 * @param posLigCase2 Un entier quelconque.
	 * @param posColCase2 Un entier quelconque.
	 * @return true si les les positions passées en paramètre sont les positions de deux cases différentes et adjacentes d'une grille de 7 lignes sur 7 colonnes, false sinon.
	 */
	private static boolean casesAdjacentes(int posLigCase1,int posColCase1,int posLigCase2,int posColCase2){
		if ((posLigCase1<0)||(posLigCase2<0)||(posLigCase1>6)||(posLigCase2>6)) return false;
		if ((posColCase1<0)||(posColCase2<0)||(posColCase1>6)||(posColCase2>6)) return false;
		int distLigne=posLigCase1-posLigCase2;
		if (distLigne<0) distLigne=-distLigne;
		int distColonne=posColCase1-posColCase2;
		if (distColonne<0) distColonne=-distColonne;
		if ((distLigne>1)||(distColonne>1)||((distColonne+distLigne)!=1))
			return false;
		return true;
	}

	/**
	 * 
	 * (14/05/21 SB EnCours)
	 * 
	 * Méthode permettant de tester si les positions passées en paramètre sont les positions de deux cases différentes et adjacentes 
	 * de la grille de jeu et qu'il est possible de passer d'une cas �  l'autre compte tenu des deux pièces posées sur les deux cases du plateau.
	 * 
	 * @param posLigCase1 Un entier quelconque.
	 * @param posColCase1 Un entier quelconque.
	 * @param posLigCase2 Un entier quelconque.
	 * @param posColCase2 Un entier quelconque.
	 * @return true si les positions passées en paramètre sont les positions de deux cases différentes et adjacentes de la grille de jeu et qu'il est possible de passer d'une cas �  l'autre compte tenu des deux pièces posées sur les deux cases du plateau, false sinon.
	 */
	private boolean passageEntreCases(int posLigCase1,int posColCase1,int posLigCase2,int posColCase2){
		if(casesAdjacentes(posLigCase1,posColCase1,posLigCase2,posColCase2) == true){
			boolean[] pointsEntree1 = this.plateau[posLigCase1][posColCase1].getPointsEntree();
			boolean[] pointsEntree2 = this.plateau[posLigCase2][posColCase2].getPointsEntree();
			if(posLigCase1 < posLigCase2) {// case 1 est au dessus de case 2
				if (pointsEntree1[2] == true && pointsEntree2[0] == true) {
					return true;
				}
			}
			if(posLigCase2 < posLigCase1) {// case 2 est au dessus de case 1
				if (pointsEntree2[2] == true && pointsEntree1[0] == true) {
					return true;
				}
			}
			if(posColCase1 < posColCase2) {// case 1 � gauche de case 2
				if (pointsEntree1[1] == true && pointsEntree2[3] == true) {
					return true;
				}
			}
			if(posColCase2 < posColCase1) {// case 2 � gauche de case 1
				if (pointsEntree2[1] == true && pointsEntree1[3] == true) {
					return true;
				}
			}
				
		}
		
		return false; // A Modifier
	}

	/**
	 * 
	 * (14/05/21 SB EnCours)
	 * 
	 * Méthode permettant de retourner un éventuel chemin entre deux cases du plateau compte tenu des pièces posées sur le plateau.
	 * Dans le cas où il n'y a pas de chemin entre les deux cases, la valeur null est retournée.
	 * Dans le cas où il existe un chemin, un chemin possible est retourné sous forme d'un tableau d'entiers �  deux dimensions.
	 * La première dimension correspond aux cases du plateau �  emprunter pour aller de la case de départ �  la case d'arrivée.
	 * Dans ce tableau, chaque case est un tableau de deux entiers avec le premier entier qui correspond �  la ligne de la case et
	 * le second entier qui correspond �  la colonne de la case. La première case d'un chemin retourné correspond toujours 
	 * �  la case (posLigCaseDep,posColCaseDep) et la dernière case correspond toujours �  la case (posLigCaseArr,posColCaseArr).
	 *
	 * @param posLigCaseDep La ligne de la case de départ (un entier compris entre 0 et 6).
	 * @param posColCaseDep La colonne de la case de départ (un entier compris entre 0 et 6).
	 * @param posLigCaseArr La ligne de la case d'arrivée (un entier compris entre 0 et 6).
	 * @param posColCaseArr La colonne de la case d'arrivée (un entier compris entre 0 et 6).
	 * @return null si il n'existe pas de chemin entre les deux case, un chemin sinon.
	 */
	public int[][] calculeChemin(int posLigCaseDep,int posColCaseDep,int posLigCaseArr,int posColCaseArr){
		int resultat[][]= null;
		int[][] plateauTempo = new int[7][7];
		for(int i = 0;i<plateauTempo.length;i++) {
			for(int j =0; j < plateauTempo[i].length;j++) {
				plateauTempo[i][j] = -1;
			}
		}
		plateauTempo[posLigCaseDep][posColCaseDep] = 0;
		
		boolean changement = true;
		while(changement) {
			changement = false;
			for(int i = 0;i<plateauTempo.length;i++) {
				for(int j =0; j < plateauTempo[i].length;j++) {
					if(plateauTempo[i][j] == -1) {
						if(passageEntreCases(i,j,i-1,j) && plateauTempo[i-1][j] != -1) {//On teste si la case du haut contient un chiffre ou non
							plateauTempo[i][j] = (plateauTempo[i-1][j]) + 1;
							changement = true;
						}
						
						if(passageEntreCases(i,j,i+1,j) && plateauTempo[i+1][j] != -1) {//On teste si la case du bas contient un chiffre ou non
							plateauTempo[i][j] = (plateauTempo[i+1][j]) + 1;
							changement = true;
						}
						
						if(passageEntreCases(i,j,i,j-1) && plateauTempo[i][j-1] != -1) {//On teste si la case de gauche contient un chiffre ou non
							plateauTempo[i][j] = (plateauTempo[i][j-1]) + 1;
							changement = true;
						}
						
						if(passageEntreCases(i,j,i,j+1) && plateauTempo[i][j+1] != -1) {//On teste si la case de droite contient un chiffre ou non
							plateauTempo[i][j] = (plateauTempo[i][j+1]) + 1;
							changement = true;
						}
					}
				}
			}
		}
		int max = (plateauTempo[posLigCaseArr][posColCaseArr]);
		if(max == -1) {
			return null;
		}
		else {
			resultat = new int[max+1][2];
			resultat[0][0] = posLigCaseDep;
			resultat[0][1] = posColCaseDep;
			int x = posLigCaseArr;
			int y = posColCaseArr;
			while(max > 0) {
				if(x != 0 && plateauTempo[x-1][y] == max-1) {
					resultat[max][0] = x;
					resultat[max][1] = y;
					x -= 1;
					max -= 1;
				}
				else if(x != plateauTempo.length-1 && plateauTempo[x+1][y] == max-1) {
					resultat[max][0] = x;
					resultat[max][1] = y;
					x += 1;
					max -= 1;
					
				}
				else if(y != 0 && plateauTempo[x][y-1] == max-1) {
					resultat[max][0] = x;
					resultat[max][1] = y;
					y -= 1;
					max -= 1;
				}
				else if(y != plateauTempo.length-1 && plateauTempo[x][y+1] == max-1) {
					resultat[max][0] = x;
					resultat[max][1] = y;
					y += 1;
					max -= 1;
				}
			}
		}
		System.out.print("Chemin entre les cases ("+posLigCaseDep+","+posColCaseDep+") et ("+posLigCaseArr+","+posColCaseArr+") : ");
		for(int i=0;i< resultat.length;i++) {
			System.out.print("("+resultat[i][0]+","+resultat[i][1]+")");
		}
		System.out.println();
		return resultat;
	}
	
	public String toString() {
        return "Plateau{" +
                "plateau=" + Arrays.toString(plateau) +
                '}';
    }
	
	public static void afficheTableau(int[][] tab) {
		String result = "";
		for(int i = 0;i < tab.length;i++) {
			for(int j = 0;j < tab[i].length;j++) {
				result += String.valueOf(tab[i][j])+ " ";
			}
			result += "\n";
		}
		System.out.println(result);
	}


	/**
	 * 
	 * Méthode permettant de calculer un chemin détaillé (chemin entre sous-cases) �  partir d'un chemin entre cases.
	 *  
	 * @param chemin Un tableau représentant un chemin de cases.
	 * @param numJoueur Le numéro du joueur pour lequel nous souaitons construire un chemin détaillé.
	 * 
	 * @return Le chemin détaillé correspondant au chemin de positions de pièces données en paramètre et pour le numéro de joueur donné.
	 */
	public int[][] calculeCheminDetaille(int[][] chemin,int numJoueur){
		if (chemin.length==1)
			return new int[0][0];
		int[][] cheminDetaille=new int[chemin.length*5][4];
		int pos=0;
		int col,lig,colS,ligS;
		for (int i=0;i<chemin.length-1;i++){
			lig=chemin[i][0];
			col=chemin[i][1];
			ligS=chemin[i+1][0];
			colS=chemin[i+1][1];
			if (ligS<lig){
				cheminDetaille[pos][0]=lig;
				cheminDetaille[pos][1]=col;
				cheminDetaille[pos][2]=1;
				cheminDetaille[pos++][3]=1;
				cheminDetaille[pos][0]=lig;
				cheminDetaille[pos][1]=col;
				cheminDetaille[pos][2]=0;
				cheminDetaille[pos++][3]=1;
				cheminDetaille[pos][0]=ligS;
				cheminDetaille[pos][1]=colS;
				cheminDetaille[pos][2]=2;
				cheminDetaille[pos++][3]=1;
			}
			else if (ligS>lig){
				cheminDetaille[pos][0]=lig;
				cheminDetaille[pos][1]=col;
				cheminDetaille[pos][2]=1;
				cheminDetaille[pos++][3]=1;
				cheminDetaille[pos][0]=lig;
				cheminDetaille[pos][1]=col;
				cheminDetaille[pos][2]=2;
				cheminDetaille[pos++][3]=1;
				cheminDetaille[pos][0]=ligS;
				cheminDetaille[pos][1]=colS;
				cheminDetaille[pos][2]=0;
				cheminDetaille[pos++][3]=1;
			} else if (colS<col){
				cheminDetaille[pos][0]=lig;
				cheminDetaille[pos][1]=col;
				cheminDetaille[pos][2]=1;
				cheminDetaille[pos++][3]=1;
				cheminDetaille[pos][0]=lig;
				cheminDetaille[pos][1]=col;
				cheminDetaille[pos][2]=1;
				cheminDetaille[pos++][3]=0;
				cheminDetaille[pos][0]=ligS;
				cheminDetaille[pos][1]=colS;
				cheminDetaille[pos][2]=1;
				cheminDetaille[pos++][3]=2;
			} else if (colS>col){
				cheminDetaille[pos][0]=lig;
				cheminDetaille[pos][1]=col;
				cheminDetaille[pos][2]=1;
				cheminDetaille[pos++][3]=1;
				cheminDetaille[pos][0]=lig;
				cheminDetaille[pos][1]=col;
				cheminDetaille[pos][2]=1;
				cheminDetaille[pos++][3]=2;
				cheminDetaille[pos][0]=ligS;
				cheminDetaille[pos][1]=colS;
				cheminDetaille[pos][2]=1;
				cheminDetaille[pos++][3]=0;
			}
		}
		cheminDetaille[pos][0]=chemin[chemin.length-1][0];
		cheminDetaille[pos][1]=chemin[chemin.length-1][1];
		cheminDetaille[pos][2]=1;
		cheminDetaille[pos++][3]=1;

		int debut=0;
		if ((numJoueur==0)&&((cheminDetaille[pos-2][2]==0)||(cheminDetaille[pos-2][3]==2))) pos--;
		if ((numJoueur==1)&&((cheminDetaille[pos-2][2]==2)||(cheminDetaille[pos-2][3]==2))) pos--;
		if ((numJoueur==2)&&((cheminDetaille[pos-2][2]==2)||(cheminDetaille[pos-2][3]==0))) pos--;
		if ((numJoueur==0)&&((cheminDetaille[1][2]==0)||(cheminDetaille[0][3]==2))) debut++;
		if ((numJoueur==1)&&((cheminDetaille[1][2]==2)||(cheminDetaille[0][3]==2))) debut++;
		if ((numJoueur==2)&&((cheminDetaille[1][2]==2)||(cheminDetaille[0][3]==0))) debut++;

		int[][] resultat=new int[pos-debut][4];
		for (int i=debut;i<pos;i++)
			for (int j=0;j<4;j++)
				resultat[i-debut][j]=cheminDetaille[i][j];
		return resultat;	
	}

	/**
	 * 
	 * Méthode retournant une copie du plateau avec des copies de ses pièces.
	 * 
	 * @return Une copie du plateau avec une copie de toutes ses pièces.
	 */
	public Plateau copy(){
		Plateau plateau=new Plateau();
		for (int ligne=0;ligne<7;ligne++)
			for (int colonne=0;colonne<7;colonne++)
				plateau.positionnePiece((this.plateau[ligne][colonne]).copy(), ligne, colonne);
		return plateau;
	}

}