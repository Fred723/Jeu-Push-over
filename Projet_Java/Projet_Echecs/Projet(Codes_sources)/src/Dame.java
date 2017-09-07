public class Dame extends Piece {

    public Dame(int couleur) { 
    	super(couleur); 
    	super.type = Piece.DAME;
    	}
    
    public boolean deplacement (Plateau p,int ld, int cd, int la, int ca, int couleur) {
    	int i = 1;
    	// si la piece destination appartient au joueur ou que la piece source n'appartient pas au joueur
    	if(p.getPieceVide(la,ca)!=true && p.getPiece(la,ca).getCouleur()==couleur || p.getPiece(ld,cd).getCouleur()!=couleur)
    		return false;
    	if(ld==la || cd==ca)
    	{
    		if(ld==la && cd<ca) // parcours ligne (on incremente les colonnes)
    		{
    			while(cd+i < ca)
    			{
    				if(p.getPieceVide(ld,cd+i)!=true) // si piece trouvee et piece differente de piece depart
    					return false;
    				i++;
    			}
    		}
    		else if(ld==la && cd>ca) // parcours ligne (on decremente les colonnes)
    		{
    			while(cd-i > ca)
    			{
    				if(p.getPieceVide(ld,cd-i)!=true) // si piece trouvee et piece differente de piece depart
    					return false;
    				i++;
    			}
    		}
    		else if(cd==ca && ld<la) // parcours colonne (on incremente les lignes)
    		{
    			while(ld+i < la)
    			{
    				if(p.getPieceVide(ld+i,cd)!=true) // si piece trouvee et piece differente de piece depart
    					return false;
    				i++;
    			}
    		}
    		else if(cd==ca && ld>la) // parcours colonne (on decremente les lignes)
    		{
    			while(ld-i > la)
    			{
    				if(p.getPieceVide(ld-i,cd)!=true) // si piece trouvee et piece differente de piece depart
    					return false;
    				i++;
    			}
    		}
    		return true;
		}
    	else
    	{
        	boolean diagonale = false;
        	for(int j=0; j<p.getPlat().length; j++)
        	{
        		if( (ld+j==la || ld-j==la)&& (cd+j==ca || cd-j==ca) )
        				diagonale = true;
        	}
        	if(diagonale==false)	// la case ciblee n'est pas en diagonale
        		return false;
        	else if(ld<la && cd<ca) // parcours diagonale BAS DROITE
    		{
    			while(ld+i<la && cd+i<ca)
    			{
    				if(p.getPieceVide(ld+i,cd+i)!=true)
    					return false;
    				i++;
    			}
    		}
    		else if(ld<la && cd>ca) // parcours diagonale BAS GAUCHE
    		{
    			while(ld+i<la && cd-i>ca)
    			{
    				if(p.getPieceVide(ld+i,cd-i)!=true)
    					return false;
    				i++;
    			}
    		}
    		else if(ld>la && cd<ca) // parcours diagonale HAUT DROITE
    		{
    			while(ld-i>la && cd+i<ca)
    			{
    				if(p.getPieceVide(ld-i,cd+i)!=true)
    					return false;
    				i++;
    			}
    		}
    		else if(ld>la && cd>ca) // parcours diagonale HAUT GAUCHE
    		{
    			while(ld-i>la && cd-i>ca)
    			{
    				if(p.getPieceVide(ld-i,cd-i)!=true)
    					return false;
    				i++;
    			}
    		}
    		return true;	// aucune piece trouvee sur le trajet
    	}
	}
}