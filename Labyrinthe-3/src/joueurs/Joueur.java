package joueurs;

import composants.Objet;
import partie.ElementsPartie;

/**
 * 
 * Cette classe abstraite représente un joueur du jeu. Ce joueur peut être un joueur humain ou un joueur ordinateur.
 * 
 *
 */
public abstract class Joueur {
	
	private int numJoueur; // le numéro du jouer
	private String nomJoueur; // Le nom du joueur
	private int numeroImagePersonnage; // Le numéro de l'image représentant le joueur
	private Objet objetsJoueur[]; // Les objets qui sont attribués au joueur et qui devront être récupérés dans l'ordre
	private int nombreObjetsRecuperes; // le nombre d'objets récupérés
	private int posLigne; // La ligne correspondant �  la position du joueur sur le plateau
	private int posColonne; // La colonne correspondant �  la position du joueur sur le plateau

	/**
     * (22/05/2021 Lucas Finalis�e)
     * 
     * Constructeur permettant de créer un joueur �  partir de son nom, son type et 
     * le numéro de l'image le représentant. La position du joueur sur le plateau doit être
     * également indiquée. Aucun objet n'est attribué au joueur (l'attribut objetsJoueur vaudra null après la construction du joueur).
     * 
     * @param numJoueur Le numéro du joueur.
     * @param nomJoueur Le nom du joueur.
     * @param numeroImagePersonnage Le numéro de l'image représentant le joueur.
     * @param posLignePlateau La ligne du plateau sur laquelle est positionnée le joueur.
     * @param posColonnePlateau La colonne du plateau sur laquelle est positionnés le joueur.
     */
    public Joueur(int numJoueur,String nomJoueur, int numeroImagePersonnage,int posLignePlateau,int posColonnePlateau) {
        this.numJoueur=numJoueur;
        this.nomJoueur=nomJoueur;
        this.numeroImagePersonnage=numeroImagePersonnage;
        this.posLigne=posLignePlateau;
        this.posColonne=posColonnePlateau;
        this.objetsJoueur=null;
        this.nombreObjetsRecuperes = 0;
    }

	/**
	 * 
	 * Méthode permettant de créer des joueurs �  partir de paramètres obtenus �  partir des fenêtres de paramétrages.
	 * Les joueurs sont positionnés dans les différents coins du plateau en fonction de leur rang 
	 * (le premier au coin en haut �  gauche, le deuxième au coin en haut �  droite, le troisième en bas �  droite).
	 * Le joueur de numéro 0 est le premier élément du tableau retourné, le joueur de numéro 1 est le deuxième élément du tableau retourné et
	 * l'éventuel joueur de numéro 2 est l'éventuel troisième élément du tableau retourné. 
	 * 
	 * @param parametresJeu Les paramètres du jeu.
	 * @return Un tableau de joueurs initialisés.
	 */
	public static Joueur[] nouveauxJoueurs(Object parametresJeu[]){
		int nombreJoueurs=((Integer)parametresJeu[0]).intValue();
		Joueur joueurs[]=null;
		String nomJoueur,typeJoueur;
		int posJoueurs[][]={{0,0},{0,6},{6,6}};
		int numImageJoueur;
		joueurs=new Joueur[nombreJoueurs];
		for (int i=0;i<nombreJoueurs;i++){
			nomJoueur=(String)parametresJeu[1+(3*i)];
			typeJoueur=(String)parametresJeu[2+(3*i)];
			numImageJoueur=((Integer)parametresJeu[3+(3*i)]).intValue();
			if (typeJoueur.equals("Humain")) 
				joueurs[i]=new JoueurHumain(i,nomJoueur,numImageJoueur,posJoueurs[i][0],posJoueurs[i][1]);
			else if (typeJoueur.equals("OrdiType0")) 
				joueurs[i]=new JoueurOrdinateurT0(i,nomJoueur,numImageJoueur,posJoueurs[i][0],posJoueurs[i][1]);
			else if (typeJoueur.equals("OrdiType1")) 
				joueurs[i]=new JoueurOrdinateurT1(i,nomJoueur,numImageJoueur,posJoueurs[i][0],posJoueurs[i][1]);
			else if (typeJoueur.equals("OrdiType2")) 
				joueurs[i]=new JoueurOrdinateurT2(i,nomJoueur,numImageJoueur,posJoueurs[i][0],posJoueurs[i][1]);
			else if (typeJoueur.equals("OrdiType3")) 
				joueurs[i]=new JoueurOrdinateurT3(i,nomJoueur,numImageJoueur,posJoueurs[i][0],posJoueurs[i][1]);
			else {
				System.err.println("Type de joueur non géré : "+typeJoueur+" création d'un joueur du type par défaut (Humain) ...");
				joueurs[i]=new JoueurHumain(i,nomJoueur,numImageJoueur,posJoueurs[i][0],posJoueurs[i][1]);
			}
		}
		return joueurs;
	}

	/**
     * 
     * (22/05/2021 Antoine Finalis�)
     *  
     * Méthode retournant le nombre d'objets récupérés par le joueur.
     * 
     * @return Le nombre d'objets récupérés par le joueur.
     */
    public int getNombreObjetsRecuperes() {
        return this.nombreObjetsRecuperes; 
    }


    /**
     * (22/05/2021 Antoine Finalis�)
     *  
     * Méthode retournant la ligne du plateau sur laquelle se trouve le joueur.
     * @return  La ligne du plateau sur laquelle se trouve le joueur.
     */
    public int getPosLigne() {
        return this.posLigne; 
    }


    /**
     * (22/05/2021 Antoine Finalis�)
     * 
     * Méthode retournant la colonne du plateau sur laquelle se trouve le joueur.
     * @return La colonne du plateau sur laquelle se trouve le joueur.
     */
    public int getPosColonne() {
        return this.posColonne; 
    }


    /**
     * (22/05/2021 Antoine Finalis�)
     * 
     * Méthode retournant le nom du joueur.
     * @return Le nom du joueur.
     */
    public String getNomJoueur() {
        return this.nomJoueur; 
    }

	/**
     * (22/05/2021 Lucas Finalis�)
     * 
     * Méthode retournant le numéro de l'image représentant le joueur.
     * @return Le numéro de l'image représentant le joueur.
     */
    public int getNumeroImagePersonnage() {
        return this.numeroImagePersonnage;
    }

	/**
	 * (22/05/2021 SB Finalis�e)
	 * 
	 * Méthode permettant d'affecter au joueur les objets qu'il devra récupérer durant le jeu.
	 * Attention : cette méthode devra créer un nouveau tableau pour l'attribut this.objetsARecuperer.
	 * 
	 * @param objetsARecuperer Un tableau contenant les objets �  récupérer dans l'ordre.
	 */
	public void setObjetsJoueur(Objet objetsARecuperer[]){
		this.objetsJoueur = new Objet[6];
		for(int i = 0;i < this.objetsJoueur.length;i++) {
			this.objetsJoueur[i] = objetsARecuperer[(numJoueur*this.objetsJoueur.length)+i];
		}
	}
	
	/**
     * (22/05/2021 LGR Finalis�e)
     * 
     * Méthode retournant un nouveau tableau contenant les objets attribués au joueur. Des objets �  récupérer devront être
     * affectés au joueur avant tout appel de cette méthode (on suppose donc que l'attribut objetsJoueur est non null).
     * 
     * @return Un tableau d'Objet correspondant aux objets �  récupérer du joueur.
     */
    public Objet[] getObjetsJoueur(){     
        return this.objetsJoueur;
    }
	
	
	/**
	 * (22/05/2021 AL SB Finalis�e)
	 * 
	 * Méthode retournant le prochain objet �  récupérer par le joueur.
	 * Avant d'appeler cette méthode il est nécessaire de s'assurer qu'il existe encore des objets �  récupérer.
	 * 
	 * @return Le prochain objet �  récupérer par le joueur.
	 */
	public Objet getProchainObjet(){
		if(this.getNombreObjetsRecuperes() == this.objetsJoueur.length) {
			return null;
		}
		Objet[] objets = this.getObjetsJoueur();
		return objets[this.getNombreObjetsRecuperes()];
	}
	
	/**
     * 
     * (22/05/2021 LGR Finalis�e)
     * 
     * Méthode permettant de récupérer un nouvel objet. Cette méthode incrémente simplement de 1 le nombre d'objets qui ont été récupérés.
     */
    public void recupererObjet(){
        this.nombreObjetsRecuperes++;
    }
    
	

	/**
	 * (22/05/2021 SB Finalis�e)
	 * 
	 * Méthode retournant le numéro du joueur.
	 * 
	 * @return Le numéro du joueur.
	 */
	public int getNumJoueur(){
		return this.numJoueur;
	}
	
	/**
     * 
     * (22/05/2021 LGR Finalis�e)
     * 
     * Méthode permettant le changement de position du joueur.
     * @param posLigne La ligne de la nouvelle position.
     * @param posColonne La colonne de la nouvelle position.
     */
    public void setPosition(int posLigne,int posColonne) {
        this.posLigne=posLigne;
        this.posColonne=posColonne;
    }
	
	/**
	 * 
	 * Méthode retournant un String représentant la catégorie du joueur. Par défaut retourne
	 * le nom de la classe du joueur.
	 * @return Un String représentant la catégorie du joueur.
	 */
	public String getCategorie() {
		return this.getClass().getName();
	}
	
	/**
	 * 
	 * Cette méthode est appelée lorsque le joueur doit jouer en début de son tour. Il doit choisir une orientation de la pièce qui est hors plateau
	 * et une entrée (une flêche) dans le plateau. Ce choix se fera �  travers l'interface graphique pour un joueur humain et par calcul pour un joueur
	 * ordinateur. Les éléments de la partie sont passés en paramètre pour qu'on joueur ordinateur puisse faire ses calculs. Dans le cas d'un joueur humain,
	 * ce paramètre n'est pas utile.
	 * @param elementsPartie Les éléments de la partie.
	 * @return Un tableau contenant deux entiers, le premier correspond �  l'orientation choisie de la pièce hors plateau (un nombre entre 0 et 3) et le second �  l'entrée du plateau (un nombre entre 0 et 27).
	 */
	
	abstract public int[] choisirOrientationEntree(ElementsPartie elementsPartie);
	
	
	/**
	 * 
	 * Cette méthode est appelée lorsque le joueur doit se déplacer et donc choisir une case sur le plateau. Pour un joueur humain ce choix se fera �  l'aide
	 * de l'interface graphique tandis que pour un ordinateur elle se fera totalement par calcul. Il n'existe pas forcément un chemin entre la case du joueur et la case choisie.
	 * 
	 * @param elementsPartie Les éléments de la partie.
	 * @return Un tableau contenant deux entiers, le premier correspond �  la ligne de la case choisie, le second �  la colonne de la case choisie. 
	 */
	abstract public int[] choisirCaseArrivee(ElementsPartie elementsPartie);

	
	/**
	 * 
	 * Méthode retournant un nouveau tableau contenant les éléments du tableau objets donné en paramètre qui ont même numéro qu'un objet appartenant �  l'attribut
	 * objetsJoueur. L'ordre des éléments du tableau retourné doit suivre l'ordre des objets se trouvant dans objetsJoueur.
	 * 
	 * @param objets L'ensemble de tous les objets du jeu dans un ordre indéfini.
	 * @return Un nouveau tableau contenant les éléments du tableau objets donné en paramètre qui ont même numéro qu'un objet appartenant �  l'attribut
	 * objetsARecuperer (ordonné de la même manière que les objets attribués au joueur).
	 */
	public Objet[] getObjetsJoueurGeneral(Objet objets[]){
		Objet resultat[]=new Objet[objetsJoueur.length];
		for (int i=0;i<objetsJoueur.length;i++)
			for (int j=0;j<objets.length;j++)
				if (objets[j].getNumeroObjet()==objetsJoueur[i].getNumeroObjet())
					resultat[i]=objets[j];
		return resultat;
	}
	
	/**
	 * Méthode retournant une copie du joueur.
	 * @param objets Les objets du jeu.
	 * @return Une copie du joueur.
	 */
	public abstract Joueur copy(Objet objets[]);
	
	
}