package composants;
import grafix.interfaceGraphique.IG;

/**
 * 
 * Cette classe permet de représenter chacun des objets du jeu.
 *
 */
public class Objet {

	private int numObjet; // Le numéro de l'objet (un entier entre 0 et 17).
	private int posLignePlateau; // La ligne du plateau sur laquelle est éventuellement posé l'objet (un entier entre -1 et 6, -1:lorsqu'il n'est pas sur le plateau).
	private int posColonnePlateau; // La colonne du plateau sur laquelle est éventuellement posé l'objet (un entier entre -1 et 6, -1:lorsqu'il n'est pas sur le plateau).
	private boolean surPlateau; // Indique si l'objet est sur le plateau ou non (true : sur le plateau, false : hors du plateau).

	/**
    *
    * (14/05/21 Allan Finalis�e)
    *
    * Constructeur permettant de construire un objet qui est initialement hors du plateau.
    *
    * @param numObjet Le numéro de l'objet.
    */
   public Objet(int numObjet) {
       if(numObjet < 0 || numObjet > 17) {
           this.numObjet = 0;
       } else {
           this.numObjet = numObjet;
       }
       this.posLignePlateau = -1;
       this.posColonnePlateau = -1;
       this.surPlateau = false;
   }

	/**
	 * 
	 * (14/05/2021 SB Finalis�e)
	 * 
	 * Méthode permettant de générer un tableau contenant les 18 objets du jeu.
	 * Les objets seront postionnées aléatoirement sur le plateau.  Deux objets ne pourront pas être sur une même case (même ligne et même colonne).
	 * 
	 * @return Un tableau de 18 objets initialisés pour une partie du jeu. Chaque objet a une position générée aléatoirement. Les positions sont différentes pour deux objets distincts.
	 *
	 */
	public static Objet[] nouveauxObjets(){
		Objet objets[]= new Objet[18];
		int cpt = 0;
		int[] posPlateau = new int[36];
		while (cpt < 18) {
			boolean dedans = false;
			int ligne = Utils.genererEntier(6);
			int colonne = Utils.genererEntier(6);
			for (int i = 0; i < posPlateau.length;i+=2) {
				if(posPlateau[i] == ligne && posPlateau[i+1] == colonne) {
					dedans = true;
					break;
				}
			}
			if(dedans == false) {
				posPlateau[2*cpt] = ligne;
				posPlateau[2*cpt+1] = colonne;
				objets[cpt] = new Objet(cpt);
				objets[cpt].posLignePlateau = ligne;
				objets[cpt].posColonnePlateau = colonne;
				objets[cpt].surPlateau = true;
				cpt += 1;
				dedans = false;
			}
		}
		return objets;
	}


	/**
     * 
     * (14/05/2021 Antoine Finalis�)
     * 
     * Méthode retournant le numéro de l'objet.
     * 
     * @return Le numéro de l'objet.
     */
    public int getNumeroObjet() {
        return this.numObjet; // A Modifier
    }


    /**
     * 
     * (14/05/2021 Antoine Finalis�)
     * 
     * Méthode retournant le numéro de la ligne sur laquelle se trouve l'objet.
     * 
     * @return Le numéro de la ligne sur laquelle se trouve l'objet.
     */
    public int getPosLignePlateau() {
        return this.posLignePlateau; // A Modifier
    }

	/**
    *
    * (14/05/21 Allan Finalis�e)
    *
    * Méthode retournant le numéro de la colonne sur laquelle se trouve l'objet.
    *
    * @return Le numéro de la colonne sur laquelle se trouve l'objet.
    */
   public int getPosColonnePlateau() {
       return this.posColonnePlateau;
   }
	
	
   /**
    * 
    *(14/05/2021 Lucas Finalis�e)
    * 
    * Méthode permettant de positionner l'objet sur une ligne et une colonne données en paramètre.
    * 
    * @param lignePlateau Un entier compris entre 0 et 6.
    * @param colonnePlateau Un entier compris entre 0 et 6.
    */
   public void positionneObjet(int lignePlateau,int colonnePlateau){
           this.posLignePlateau=lignePlateau;
           this.posColonnePlateau=colonnePlateau;
           IG.placerObjetPlateau(this.getNumeroObjet(),lignePlateau,colonnePlateau);
           this.surPlateau=true;
   }

   /**
	*
	* (14/05/21 Allan Finalis�e)
	*
	* Méthode permettant d'enlever l'objet du plateau.
	*
	*/
	public void enleveDuPlateau(){
		//IG.enleverObjetPlateau(this.getPosLignePlateau(), this.getPosColonnePlateau());
		this.posLignePlateau = -1;
		this.posColonnePlateau = -1;
		this.surPlateau = false;
	}
	
	/**
     * 
     * (14/05/2021 Antoine Finalis�)
     * 
     * Méthode indiquant si l'objet est sur le plateau au non.
     * 
     * @return true si l'objet est sur le plateau, false sinon.
     */
    public boolean surPlateau() {
        return this.surPlateau;
    }

	/**
	 * Méthode permettant d'obtenir une représentation d'un objet sous forme de chaîne de caractères.
	 */
	@Override
	public String toString() {
		return "Objet [numObjet=" + numObjet + ", posLignePlateau=" + posLignePlateau + ", posColonnePlateau="
				+ posColonnePlateau + ", surPlateau=" + surPlateau + "]";
	}

	/**
	 * 
	 * Méthode permettant de copier l'objet.
	 * 
	 * @return Une copie de l'objet.
	 */
	public Objet copy(){
		Objet objet=new Objet(numObjet);
		objet.posLignePlateau=posLignePlateau;
		objet.posColonnePlateau=posColonnePlateau;
		objet.surPlateau=surPlateau;
		return objet;
	}

	/**
	 * Programme testant quelques méthodes de la classe Objet.
	 * @param args arguments du programme
	 */
	public static void main(String[] args) {
		// Un petit test ...
		System.out.println("*** Génération et affichage des 18 objets ... ***");
		Objet objetsJeu[]=nouveauxObjets();
		for (int i=0;i<objetsJeu.length;i++)
			System.out.println(objetsJeu[i]);
		System.out.println("*** On enlève les 18 objets du plateau ... ***");
		for (int i=0;i<objetsJeu.length;i++)
			 objetsJeu[i].enleveDuPlateau();
		System.out.println("*** On affiche de nouveau les 18 objets ... ***");
		for (int i=0;i<objetsJeu.length;i++)
			System.out.println(objetsJeu[i]);
	}
	
}
