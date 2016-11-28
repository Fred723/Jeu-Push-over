#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include "fonctions.h"

/*!
*
* Fonction allouant dynamiquement un plateau dont l'adresse est retournée.
*
* \param n : taille d'un côté
*
*/
plateau* creerPlateau (int n)
{
    int i;
    assert(n >= 2 && n <= 8 && "n n'est pas compris entre 2 et 8 (inclus)");

    plateau *p = (plateau*) malloc(sizeof(plateau)); // Declare et alloue dynamiquement une taille à *p.
    (*p).n = n;
    (*p).tableau = (int*) malloc(n*n*sizeof(int)); // Alloue dynamiquement une taille au champs tableau.

    for (i = 0; i < (*p).n * (*p).n; i++) // Initialise chaque case du champs tableau à 0.
        (*p).tableau[i] = 0;

    return p;
}


/*!
* Fonction désallouant dynamiquement le plateau passé en paramètre
*
* \param p : plateau à désallouer
*/
void detruirePlateau(plateau *p)
{
	assert(p!=NULL);
	free(p->tableau);
	free(p);
	p->tableau=NULL;
}

/*!
* Fonction retournant la valeur de la case (i,j) du plateau p
*
* \param p : plateau
* \param i : entier correspondant au numéro de ligne
* \param j : entier correspondant au numéro de colonne
*/
int getCase(plateau *p, int i, int j)
{	
	assert(i>=0 && i<=((p->n)-1));	// Vérifie que i est compris entre 0 et n-1 inclus.
	assert(j>=0 && j<=((p->n)-1));	// Vérifie que j est compris entre 0 et n-1 inclus.
	
	int coordonnee = i*(p->n) + j;	//calcule la position dans le tableau	
	int vcase = p->tableau[coordonnee];		//Stock la valeur contenu de la case du tableau
	
	return vcase;
}


/*!
*
* Fonction modifiant la valeur de la case (i,j) du plateau p
*
* \param p : plateau
* \param i : entier compris entre [0,n-1]
* \param j : entier compris entre [0,n-1]
* \param valeur : entier compris entre [-1,1]
*/
void setCase(plateau *p, int i, int j, int val)
{
    assert(i >= 0 && i <= (*p).n - 1); // Vérifie que i est compris entre 0 et n-1 inclus.
    assert(j >= 0 && j <= (*p).n - 1); // Vérifie que j est compris entre 0 et n-1 inclus.
    assert(val >= -1 && val <= 1 ); // Vérifie que val est compris entre -1 et 1.

    (*p).tableau[(*p).n * i + j] = val;
}


/*!
* Fonction affichant le plateau sur le terminal,
*
* \param p : pointeur sur la partie que l'on souhaite afficher
*/
void affichage (plateau *p)
{
    int i,j;

    for(i = 0; i < (*p).n; i++) // Boucle pour parcourir i.
	{
        for(j = 0; j < (*p).n; j++) // Boucle pour parcourir le tableau avec j.
            printf("%3d",(*p).tableau[(*p).n * i + j]);
        printf("\n");
    }
}


/*!
* Fonction retournant 1 si l’indice est valide par rapport au plateau
*
* \param p : plateau
* \param indice : entier compris entre [0,n-1]
*/
int indiceValide(plateau *p, int indice)
{
	if (indice >= 0 && indice <= (*p).n - 1)
		return 1; // L'indice est valide.
	else
		return 0;
}


/*!
* Fonction retournant 1 si la case (indiceLigne,indiceColonne) existe sur le plateau
*
* \param p : plateau
* \param indiceLigne : entier correspondant à un indice de ligne
* \param indiceColonne : entier correspondant à un indice de colonne
*/
int caseValide(plateau * p, int indiceLigne, int indiceColonne)
{
    if (indiceValide(p, indiceLigne) == 1 && indiceValide(p, indiceColonne) == 1)
        return 1; // La case est valide.
    else
        return 0;
}


/*!
* Fonction retournant 1 si la case (i,j) du plateau p est vide
*
* \param p : plateau
* \param i : entier correspondant à un indice de ligne
* \param j : entier correspondant à un indice de colonne
*/
int caseVide(plateau *p, int i, int j)
{
	int res;
	res = getCase(p,i,j); // Prend la valeur de la case.
	if(res == 0)
		return 1;
	else
		return 0;
}


/*!
* \param di : direction sur les lignes
*
// -1 si l’on insère vers le haut,
*
// 0 si l’on reste sur la même ligne,
*
// 1 si l’on insère vers le haut
* \param dj : direction sur les colonnes
*
// -1 si l’on insère vers la gauche,
*
// 0 si l’on reste sur la même colonne,
*
// 1 si l’on insère vers la droite
* \param pion : 1 ou -1 suivant le joueur
*/
int insertionPionPossible (plateau * p, int ligne, int col, int di, int dj, int pion)
{
    assert(ligne >= 0 && ligne <= (*p).n - 1); // Vérifie que ligne est compris entre 0 et n-1 inclus.
    assert(col >= 0 && col <= (*p).n - 1); // Vérifie que col est compris entre 0 et n-1 inclus.

    assert(di >= -1 && di <= 1); // Vérifie que di est compris entre -1 et 1.
    assert(dj >= -1 && dj <= 1); // Vérifie que dj est compris entre -1 et 1.

    assert(pion == -1 || pion == 1); // Vérifie que pion possède une valeur égale à -1 OU 1.

    if(caseValide(p, ligne, col) == 1 && caseVide(p, ligne, col) == 1 && (col == 0 || col == ((*p).n - 1)|| ligne == 0 || ligne == ((*p).n - 1))) // Condition d'insertion si la case est vide.
		return 1;
    else if(caseValide(p, ligne, col) == 1 && caseVide(p, ligne, col) == 0)
    {
        if(col == 0 && di == 0 && dj == 1 && (getCase(p, ligne, (*p).n - 1) == pion || caseVide(p, ligne, (*p).n - 1) == 1)) // Condition pour l'insertion de gauche à droite.
            return 1;
        else if(col == (*p).n - 1 && di == 0 && dj == -1 && (getCase(p, ligne, 0) == pion || caseVide(p, ligne, 0) == 1)) // Condition pour l'insertion de droite à gauche.
            return 1;
        else if(ligne == 0 && di == 1 && dj == 0 && (getCase(p, (*p).n - 1, col) == pion || caseVide(p, (*p).n - 1, col) == 1)) // Condition pour l'insertion de haut en bas.
            return 1;
        else if(ligne == (*p).n - 1  && di == -1 && dj == 0 && (getCase(p, 0, col) == pion || caseVide(p, 0, col) == 1)) // Condition pour l'insertion de bas en haut.
            return 1;
    }
		return 0;
}


/*!
* Fonction insérant le pion à partir de la case (ligne,col)
* dans la direction donnée par di,dj
*
* \param p : plateau
* \param ligne : ligne de la case où l’on insère le pion
* \param col : colonne de la case où l’on insère le pion
* \param di : direction sur les lignes
*
// -1 si l’on insère vers le haut,
*
// 0 si l’on reste sur la même ligne,
*
// 1 si l’on insère vers le haut
* \param dj : direction sur les colonnes
*
// -1 si l’on insère vers la gauche,
*
// 0 si l’on reste sur la même colonne,
*
// 1 si l’on insère vers la droite
* \param pion : 1 ou -1 suivant le joueur
*/
void insertionPion(plateau * p, int ligne, int col, int di, int dj, int pion)
{
	int stock = 0;
	int i;
	for(i = 0; i < (*p).n && pion != 0; i++)
	{
		if(di == 0 && dj== 1) // Mouvement vers la droite.
		{
			stock = getCase(p,ligne,i);
			setCase(p,ligne, i,pion);
			pion = stock;
		}
		else if(di == 0 && dj == -1) // Mouvement vers la gauche.
		{
			stock = getCase(p,ligne,col-i); // Parcourt les colonnes dans le sens inverse de leur numérotation.
			setCase(p,ligne,col-i,pion);
			pion = stock;
		}
		else if(di == 1 && dj == 0) // Mouvement vers le bas.
		{
			stock = getCase(p,i,col);
			setCase(p,i,col,pion);
			pion = stock;
		}
		else if (di == -1 && dj == 0) // Mouvement vers le haut.
		{
			stock = getCase(p,i,col);
			setCase(p,i,col,pion);
			pion = stock;
		}
	}
}


/*!
* Fonction retournant 1 ou -1 si l’un des joueurs a gagné, 2 si match nul et 0 si la partie
* n’est pas finie.
*
* \param p : plateau
*/
int gagne(plateau *p)
{
	int a=0;
	int b=0;
	int c=0;
	int d=0;
	
	int i;
	int j;
 
	for(i=0;i<p->n;i++)					 
 
		{  	for(j=0;j<(p->n);j++)                    /*ligne*/
			{		if(getCase(p, i, j) == 1)	
						{	a++; }
 
					if(getCase(p, i, j) == -1)
					{b++;}
			 }
 
			if (a==p->n) c++;
			if (b==p->n) d++;
			a=0;
			b=0;
		} 
 
	int x,y;
    for(x=0;x<p->n;x++)					 
		{  	for(y=0;y<(p->n);y++)                    /*colonne*/
			{		if(getCase(p, y, x) == 1)	
						{	a++; }
 
					if(getCase(p, y, x) == -1)
					{b++;}
			 }
 
			if (a==p->n) c++;
			if (b==p->n) d++;
			a=0;
			b=0;
		}
 
 
 
		if (c==0 && d==0)	//Partie non terminé
			{	printf("Partie non terminée.\n");
			    return 0;
			}
 
		else if (c==d && c>0 && d>0)		//Match nul
			{ 	printf("Match nul.\n");
			    return 2;
			}
 
		else if (c<d)	//Premier joueur gagne
			{ 	printf("Le premier joueur à gagné.\n");
			    return -1;
			}
 
		else	//Second joueur gagne
			{
				printf("Le second joueur à gagné.\n");
				return 1;
			}

}

partie * creerPartie ()
{
    int n;
    partie *pa = malloc(sizeof(partie)); // Déclaration et allocation dynamique d'un pointeur de type partie.
	printf("\nCréation d'une nouvelle partie !\n");
    printf("Rentrez le nom du premier joueur (Blanc) :\n"); // Demande à l'utilisateur de rentrer le nom du joueur 1.
    scanf("%s", (*pa).nomJoueur1);

    printf("Rentrez le nom du deuxième joueur (Noir)\n"); // Demande à l'utilisateur de rentrer le nom du joueur 2.
    scanf("%s", (*pa).nomJoueur2);
	
    printf("Saisissez la taille du plateau de jeu.\n");  // Demande à l'utilisateur de saisir la taille du plateau.
    scanf("%d", &n);
    
    (*pa).pionJoueurCourant = -1; //J1
	(*pa).p = creerPlateau( n ); // Crée le plateau en fonction de la valeur de n rentrée.

    return pa;
}


/*!
* Fonction désallouant dynamiquement la partie passée en paramètre
*
* \param pa : partie à désallouer
*/
void detruirePartie(partie *pa) 
{ 
	detruirePlateau(pa->p);	// Désalloue la mémoire réservée au champs plateau en appelant la fonction

	free(pa);	//Suppresion de la partie allouée
	pa=NULL;
}


/*!
 * Fonction modifiant les variables ligne, col, di et dj passées en paramètre avec
 * les valeurs saisies par l’utilisateur. Elle retourne 0 si le joueur a appuyé
 * sur la lettre M et 1 sinon.
 *
 * \param p : partie
 * \param ligne : ligne du plateau où l’on souhaite insérer le pion du joueur courant de la partie
 * \param col : colonne du plateau où l’on souhaite insérer le pion du joueur courant de la partie
 * \param di : directi	on d’insertion (ligne)
 * \param dj : direction d’insertion (colonne)
 */
int saisieJoueur (partie * pa, int * ligne, int * col, int * di, int * dj)
{
	char rep[3];
	int repE = 0;

	int faux = 0;

	do {

		if(faux == 0) 
		{
			printf("\nVoulez vous retourner au menu principal (M)\n  ou saisir un mouvement (H/B/G/D + ent) : ");
		}
		else if(faux != 0)
		{
			printf ("\nM ou H/B/G/D + ent dans [1;%d] : ", pa->p->n);
		}

		scanf("%s", rep);


		if(rep[0] == 'M' || rep[0] == 'm')  // Retour au menu principal
		{
			return 0;
		}

		repE = (rep[1] - '0');  // On récupère la valeur numérique si mouvement

		repE--; // On se remet dans [0; n-1]
		
		faux++; // pour afficher un autre message au début

	//////

		if(rep[0] == 'H' || rep[0] == 'h') // en haut
		{
			*ligne = 0;
			*col = repE;
			*di = 1;
			*dj = 0;
		}
		else if(rep[0] == 'B' || rep[0] == 'b') // en bas
		{
			*ligne = (pa->p->n-1);
			*col = repE;
			*di = -1;
			*dj = 0;
		}
		else if(rep[0] == 'D' || rep[0] == 'd') // à droite
		{
			*ligne = repE;
			*col = (pa->p->n-1);
			*di = 0;
			*dj = -1;
		}
		else if(rep[0] == 'G' || rep[0] == 'g') // à gauche
		{
			*ligne = repE;
			*col = 0;
			*di = 0;
			*dj = 1;
		}

	//////

	}	while( (repE < 0) || (repE > (pa->p->n-1)) || (insertionPionPossible(pa->p, *ligne, *col, *di, *dj, pa->pionJoueurCourant)) == 0 );
	// on vérifie tout d'abord que la variable fournie est dans [0;n-1] mais aussi que le mouvement soit valide

	return 1;


}


/*!
* Fonction changeant le pion du joueur courant (1 ou -1)
*
* \param pa : partie en cours
*/
void changePionJoueurCourant(partie * pa)
{
	(*pa).pionJoueurCourant = -(*pa).pionJoueurCourant; // Change le pion du joueur en courant en affectant l'opposé de la valeur du pion.
}


/*!
* Fonction permettant au joueur courant de jouer.
* La fonction retourne 1 si le joueur joue, et 0 sinon (s’il appuie sur la touche M).
*
* \param pa : partie en cours
*/

int tourJoueurCourant (partie *pa)
{
	int rep = -1;

  int ligne;
  int colonne;
  int di;
	int dj;

	printf("\e[1;1H\e[2J");
	affichage(pa->p);

	if(pa->pionJoueurCourant == -1)
	{
		printf("\n\nJoueur Courant : %s (B)", pa->nomJoueur1);
	}
	else
	{
		printf("\n\nJoueur Courant : %s (N)", pa->nomJoueur2);
	}

	//////

	rep = saisieJoueur(pa, &ligne, &colonne, &di, &dj);

	//////

	if(rep == 0) // Pour bien différencier le retour menu
	{
		printf("\nRetour menu !\n");
		return 0;
	}

	//////

	insertionPion(pa->p, ligne, colonne, di, dj, pa->pionJoueurCourant);

	//////

	return 1;
}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*!
	Fonction permettant de jouer à Push over.
	param pa : pointeur sur une partie en cours (elle doit être allouée). La partie peut ne pas être un début de partie.

	Retourne 0 si un joueur veut accéder au menu principal, 1 si la partie s’est terminée normalement
	(Partie gagnée ou nulle)
*/

int jouer (partie *pa) 
{				

	while (gagne(pa->p) == 0) 
	{
		if (tourJoueurCourant(pa))
		{
			changePionJoueurCourant(pa);
		}
		else
		{
			return 0;
		}
	}

	printf("\n");
	affichage(pa->p);

	if (gagne(pa->p) == -1)
	{
		printf("\nVictoire de %s\n\n", pa->nomJoueur1);
		detruirePartie(pa);
	}
	else if (gagne(pa->p) == 1)
	{
		printf("\nVictoire de %s\n\n", pa->nomJoueur2);
		detruirePartie(pa);
	}
	else
	{
		printf("\nMatch nul\n");
		detruirePartie(pa);
	}

	return 1;
}
//-------------------------------------------------Partie 4 --------------------------------------------------------------------------
/*!
* Fonction sauvegardant la partie passée en paramètre. Retourne 0 en cas de problème, 1 sinon.
*
*/
int sauvegardePartie(partie *pa)
{
    int i;
    plateau *p = (*pa).p; // Pointeur de pointeur sur (*pa).p pour accéder au tableau du plateau.

    FILE *fichier = fopen("sauvegarde.txt", "wt"); // Ouvre le fichier sauvegarde.txt en écriture.
    if (fichier == NULL) // Vérifie si le fichier s'ouvre correctement.
    {
        printf ("Problème à l’ouverture\n");
        return 0;
    }

    /* Inscrit les différents champs de la partie dans sauvegarde.txt */
    fprintf (fichier, "%s\n", (*pa).nomJoueur1);
    fprintf (fichier, "%s\n", (*pa).nomJoueur2);
    fprintf (fichier, "%d\n", (*pa).pionJoueurCourant);

	fprintf (fichier, "%d\n", (*p).n); // Ecrit la taille du tableau.

    for (i = 0; i < (*p).n * (*p).n; i++) // Parcourt toutes les cases (n*n) du tableau.
        fprintf (fichier, "%d\n", (*p).tableau[i]); // Ecrit chaque case du tableau dans le fichier.

    fclose(fichier); // Ferme le fichier.
	
	printf("\nPartie sauvegardé !\n");
    return 1;
}

/*!
* Fonction chargeant la partie en cours. Retourne l’adresse de la partie nouvellement créée.
*
* Si aucune partie n’a été sauvegardée, une nouvelle partie est créée.
*/
partie * chargementPartie()
{
	partie *pa = NULL;
	
	int i, j;
	int val = 0; // récupére les valeurs du tableau
	int n = 0; // récupére la taille du tableau


    FILE *fichier = fopen("sauvegarde.txt", "rt"); // Ouvre le fichier sauvegarde.txt en lecture.
    if (fichier == NULL) // Vérifie si le fichier existe.
    {
        printf ("Problème à l’ouverture\n");
        return 0;
    }

    /* Lit les différents champs de la partie dans sauvegarde.txt */
	pa = malloc(sizeof(partie));

	fscanf(fichier, "%s\n%s\n%d\n%d\n", pa->nomJoueur1, pa->nomJoueur2, &pa->pionJoueurCourant, &n); //prends les 4 premiere valeurs pour initialise la partie

	pa->p = creerPlateau(n); //cree le plateau
	
	for(i=0; i < pa->p->n; i++) // lit chaque champs
	{
		for(j=0; j < pa->p->n; j++)
		{
			fscanf(fichier, "%d ", &val);
			setCase(pa->p, i, j, val); //et on y rentre la valeur lu.
		}
	}

    fclose(fichier); // Ferme le fichier.

    return pa;
}


/*!
* Fonction affichant le menu (si p!=NULL, on permet de reprendre une partie et de
* sauvegarder la partie)
*
* \param p : pointeur sur la partie courante.
* Valeur de retour :
*
0 si les joueurs veulent quitter le jeu,
*
1 s’ils veulent commencer une nouvelle partie,
*
2 s’ils veulent charger la dernière partie sauvegardée,
*
3 s’ils veulent sauvegarder la partie courante,
*
4 s’ils veulent reprendre la partie courante.
*/
int menu(partie *pa)
{
	int choix;
	
	printf("\t\t\t\t\tMenu :\n");

	printf("\t\t1.\tCommencer une nouvelle partie\n");

	printf("\t\t2.\tCharger la derniere partie\n");

	printf("\t\t3.\tSauvegarder la partie en cours\n");

	printf("\t\t4.\tReprendre la partie en cours\n");

	printf("\t\t0.\tQuitter le jeu\n");

	do
	{
		printf("\n\tFaites votre choix : ");
		scanf("%d",&choix);
	}while(choix <0 || choix>4);

	if(choix == 1)
		return 1;
	else if(choix == 2)
		return 2;
	else if(choix == 3)
		return 3;
	else if(choix == 4)
		return 4;
	else
		return 0;
}
