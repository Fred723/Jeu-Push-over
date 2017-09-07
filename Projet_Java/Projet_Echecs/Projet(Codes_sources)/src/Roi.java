public class Roi extends Piece {

	private boolean bouger;
	
    public Roi(int couleur) {
    	super(couleur); 
    	super.type = Piece.ROI;
    	this.bouger = false;
    }
    public boolean deplacement (Plateau p,int ld, int cd, int la, int ca, int couleur){
    	// si la piece destination appartient au joueur ou que la piece source n'appartient pas au joueur
    	if(p.getPieceVide(la,ca)!=true && p.getPiece(la,ca).getCouleur()==couleur || p.getPiece(ld,cd).getCouleur()!=couleur)
    		return false;
    	
    	if(roque(p,ld,cd,la,ca,couleur)==true || saut(p,ld,cd,la,ca,couleur)==true)
    	{
    		
    				Plateau pTest = new Plateau(p);
    				pTest.deplace(ld, cd, la, ca);
    				
    				if(pTest.echec(pTest, couleur)==false)
    					return true;
    				
			
    	}
    	
    	return false;
    }
    public boolean roque (Plateau p,int ld, int cd, int la, int ca, int couleur){
    	
    	if( (la==0 || la ==7) && ca==2 && p.getPiece(la,0) instanceof Tour && ((Tour) p.getPiece(la,0)).isBouger()==false && this.isBouger()==false)
    	{
    		int i=1;
    		while(cd-i>ca)
    		{
    			// si le roi est en echec, il ne peut pas effectuer le roque
    			if((p.getPieceVide(ld,cd-i)!=true && cd-i!=cd) || p.echec(p,couleur)) // si piece trouvee et piece differente de piece depart
					return false;
				i++;
    		}
    		
    		if(p.getPieceVide(ld,cd-i)!=true)
    			return false;
    		return true;
    	}
    	
    	
    	else if( (la==0 || la ==7) && ca==6 && p.getPiece(la,7) instanceof Tour && ((Tour) p.getPiece(la,7)).isBouger()==false && this.isBouger()==false)
    	{
    		int i=1;
    		while(cd+i<ca)
    		{	
    			// si le roi est en echec, il ne peut pas effectuer le roque
    			if((p.getPieceVide(ld,cd+i)!=true && cd+i!=cd) || p.echec(p,couleur)) // si piece trouvee et piece differente de piece depart
					return false;
				i++;
    		}
    		
    	
    		return true;
    	}
    	
    	return false;
    }
    
    public boolean saut (Plateau p,int ld, int cd, int la, int ca, int couleur){
    	if((ld+1==la || ld==la || ld-1==la) && (cd+1==ca || cd==ca || cd-1==ca) )	// deplacement d'une case autour de lui-meme
    		return true;
    	return false;
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

