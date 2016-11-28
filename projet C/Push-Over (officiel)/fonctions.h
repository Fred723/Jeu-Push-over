#ifndef FONCTIONS_H
#define FONCTIONS_H

typedef struct // Définition de la structure plateau.
{
 int *tableau;
 int n;
}plateau;

typedef struct // Définition de la structure partie.
{
    char nomJoueur1 [128]; // Une case de plus pour \0.
    char nomJoueur2 [128];
    int pionJoueurCourant; // Pion du joueur.
    plateau *p;
}partie;


// Partie 1 : Fonctions de base.
plateau* creerPlateau (int);
void detruirePlateau (plateau *);
int getCase(plateau *, int, int);
void setCase(plateau *, int, int, int);
void affichage (plateau *);

// Partie 2 : Fonctions pour les déplacements.
int indiceValide(plateau *, int);
int caseValide(plateau *, int, int);
int caseVide(plateau *, int, int);
int insertionPionPossible (plateau *, int, int, int, int, int);
void insertionPion(plateau *, int, int, int, int, int);
int gagne(plateau *);

// Partie 3 : Fonctions pour le déroulement de la partie.
partie * creerPartie ();
void detruirePartie(partie *);
int saisieJoueur (partie *, int *, int *, int *, int *);
void changePionJoueurCourant(partie *);
int tourJoueurCourant(partie *);
int jouer(partie *);

// Partie 4 : Fonctions pour le menu, la sauvegarde et le chargement.
int sauvegardePartie(partie *);
partie * chargementPartie();
int menu(partie *);

#endif


















