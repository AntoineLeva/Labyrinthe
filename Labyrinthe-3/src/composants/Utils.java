package composants;

import java.util.Date;
import java.util.Random;

/**
 * 
 * Classe contenant quelques fonctions utilitaires.
 * 
 */
public class Utils {
	
	private static Random generateur=new Random((new Date().getTime()));

	/**
	 * (01/05/2021 SB Finalisée)
	 * 
	 * Méthode permettant de générer aléatoirement un nombre entier.
	 * 
	 * @param max Le nombre entier maximal pouvant être retourné.
	 * @return Un nombre entier compris entre 0 et max (inclus).
	 */
	
	public static int genererEntier(int max){
		//Random rand = new Random();
		return generateur.nextInt(max+1);
	}
	
	
	/**
	 * (01/05/2021 SB Finalisée)
	 * 
	 * Méthode permettant de générer un tableau d'entiers dont la longueur longTab est donnée en parametre.
	 * Le tableau généré doit contenir chaque entier compris entre 0 et longTab-1. La position de ces entiers
	 * dans le tableau doit être aléatoire.
	 * 
	 * @param longTab La longueur du tableau.
	 * @return Un tableau contenant les entiers 0,...,longTab-1 placés aléatoirement dans le tableau.
	 */
	public static int[] genereTabIntAleatoirement(int longTab){
		int tab[]= new int[longTab];
		int cpt = 0;
		while(cpt < longTab) {
			int i = genererEntier(longTab-1);
			if(tab[i] == 0) {
				tab[i] = cpt;
				cpt += 1;
			}
		}
		return tab;
	}
	/**
	 * Programme testant la mÃ©thode genereTabIntAleatoirement.
	 * @param args arguments du programme
	 */
	public static void main(String[] args) {
		// Un petit test ...
		int tab[]=genereTabIntAleatoirement(18);
		for (int i=0;i<tab.length;i++)
			System.out.print(tab[i]+" ");

	}

}
