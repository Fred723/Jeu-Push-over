exe : principal.o fonctions.o
	gcc principal.o fonctions.o -o exe

fonctions.o : fonctions.c fonctions.h
	gcc -Wall -c fonctions.c

principal.o : principal.c fonctions.h
	gcc -Wall -c principal.c
