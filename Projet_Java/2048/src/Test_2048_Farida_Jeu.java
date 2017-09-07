import java.util.Scanner;

import com.farida.android_2048.modele.MoteurJeu;

public class Test_2048_Farida_Jeu {

	public static final Scanner sc = new Scanner(System.in);
	
	
	public static int menu(){
		int choix;
		String promptDirectionAutorisees = "Menu:\n0 - haut\n1 - droite\n2 - bas\n3 - gauche";
		
		do {
			System.out.println(promptDirectionAutorisees);
			System.out.println("Votre choix ? ");
			choix = sc.nextInt();
		} while (choix !=MoteurJeu.GAUCHE
				&& choix !=MoteurJeu.DROITE
				&& choix !=MoteurJeu.BAS
				&& choix !=MoteurJeu.HAUT			
				);
		
		return choix;
	}
	
	public static void afficherEtatJeu(MoteurJeu jeuDeFarida){
		System.out.println("Score : " + jeuDeFarida.getScore());
		System.out.println("Nombre de deplacements : " + jeuDeFarida.getNombreDeplacements());
		System.out.println(jeuDeFarida.getGrilleTuiles().toString());
	}
	

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/**
		 * On récupère une instance du jeu 2048 de Farida
		 */
		MoteurJeu moteurJeu =  MoteurJeu.getInstance();
		
		/**
		 * Initialisation du jeu
		 */
		moteurJeu.setup();
		int direction;
		do {
			Test_2048_Farida_Jeu.afficherEtatJeu(moteurJeu);
			direction = Test_2048_Farida_Jeu.menu();	
			moteurJeu.deplacer(direction);
		} while (!moteurJeu.partieTerminee());
		
		if (moteurJeu.partieGagnee()){
			System.out.println("Vous avez gagne la partie !");
		} else {
			System.out.println("Vous avez gagne la partie !");
		}
		
		
	}

}
