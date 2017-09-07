public class Tour extends Piece {
	
	private boolean bouger;
	
    public Tour(int couleur) {
    	super(couleur);
    	super.type = Piece.TOUR;  
    	this.bouger = false;
    	}
    
    public boolean deplacement (Plateau p,int ld, int cd, int la, int ca, int couleur) {
    	int i = 1;
    	// si la piece destination appartient au joueur ou que la piece source n'appartient pas au joueur
    	if(p.getPieceVide(la,ca)!=true && p.getPiece(la,ca).getCouleur()==couleur || p.getPiece(ld,cd).getCouleur()!=couleur || (cd!=ca && ld!=la))
    		return false;
    	if(ld==la && cd<ca) // parcours ligne (on incremente les colonnes)
		{
			while(cd+i < ca)
			{
				if(p.getPieceVide(ld,cd+i)!=true) // si piece trouvee et piece differente de piece depart
					return false;
				i++;
			}
		}
		else if(ld==la && cd>ca) // parcours ligne (on incremente les colonnes)
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
    	return true;	// aucune piece trouvee sur le trajet
	}
    
    public boolean isBouger()
    {
    	return this.bouger;
    }
    
    public void setBouger(boolean bouger)
    {
    	this.bouger = bouger;
    }
}
