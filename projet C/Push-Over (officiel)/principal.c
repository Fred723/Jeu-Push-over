#include <stdio.h>
#include <stdlib.h>
#include "fonctions.h"

int main()
{
	int valretour;
	partie  * pa = NULL;
	
	do
	{
		
		valretour=menu(pa);
		printf("Valeurs retourné par le MENU : %d\n",valretour);//test pour le menu
		
		if((valretour == 4) && (pa != NULL)) // Reprendre
			jouer(pa);
		else if(valretour == 1) // New
		{
			pa = creerPartie();
			jouer(pa);
		}
		else if(valretour == 2) // Charger
		{
			pa = chargementPartie();
			jouer(pa);
		}
		else if((valretour == 3) && (pa != NULL)) // Sauvegarder
			sauvegardePartie(pa);
	
	}while(valretour != 0); // Si la fonction jouer renvoie 0, le programme continue, valretour != 0 don reaffiche 
	
	
	if(pa != NULL)
		detruirePartie(pa);
    return 0;
}
