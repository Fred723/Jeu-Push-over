public class Pion extends Piece {
	private boolean enPassant;
	
    public Pion(int couleur) { 
    	super(couleur); 
    	super.type = Piece.PION;    
    	}
    
    public boolean deplacement (Plateau p,int ld, int cd, int la, int ca, int couleur) {
    	// si la piece destination appartient au joueur ou que la piece source n'appartient pas au joueur
    	if(p.getPieceVide(la,ca)!=true && p.getPiece(la,ca).getCouleur()==couleur || p.getPiece(ld,cd).getCouleur()!=couleur)
    		return false;
    	if(p.getPiece(ld,cd).getCouleur()==Piece.BLANC)
    	{
    		if(sautDouble(p,ld,cd,la,ca)==true&& ld ==6) // s'il n'y a pas de piece sur les deux cases
    			return true;
    		else if(ld-1==la && cd==ca && p.getPieceVide(ld-1,cd)==true)	// s'il n'y a pas de piece sur la case destination
    			return true;
    		else if(ld-1==la && (cd+1==ca || cd-1==ca) && p.getPieceVide(la,ca)!=true && p.getPiece(la,ca).getCouleur()==Piece.NOIR)
    			return true; // s'il y a une piece en diagonale et piece de couleur adverse
    		else if (priseEnPassant(p,ld,cd,la,ca)==true)
    			return true;
    	}
    	else if(p.getPiece(ld,cd).getCouleur()==Piece.NOIR)
    	{
    		if(sautDouble(p,ld,cd,la,ca)==true && ld ==1) // s'il n'y a pas de piece sur les deux cases
    			return true;
    		else if(ld+1==la && cd==ca && p.getPieceVide(ld+1,cd)==true)	// s'il n'y a pas de piece sur la case destination
    			return true;
    		else if(ld+1==la && (cd+1==ca || cd-1==ca)  && p.getPieceVide(la,ca)!=true && p.getPiece(la,ca).getCouleur()==Piece.BLANC)
    			return true; // s'il y a une piece en diagonale et piece de couleur adverse
    	/*	else if (priseEnPassant(p,ld,cd,la,ca)==true)
    		{
    			System.out.println("If enPassant");
    			return true;
    		}*/
    	}
    	//System.out.println(priseEnPassant(p,ld,cd,la,ca));
    	return false;
    }
    
    public boolean promotion(int la, int ca)
    {
    	if(la == 7 || la == 0)
    		return true;
    	return false;
    }
    public boolean priseEnPassant(Plateau p,int ld,int cd,int la,int ca)
    {
    //	System.out.println("Deb fct ld:"+ld+" cd:"+cd+" la:"+la+" ca:"+ca);
    	//System.out.println("en passant : "+enPassant);
    	if(ld-1==la && cd+1==ca &&p.getPieceVide(la,ca)==true &&(p.getPieceVide(la, cd)!=true)
    			&& p.getPiece(la, cd)instanceof Pion
				&&((Pion) p.getPiece(la, cd)).isEnPassant()==true )
    	{
    		
    		return true;
    	}
    	else if(ld+1==la&&cd+1==ca &&p.getPieceVide(la,ca)==true &&(p.getPieceVide(la, cd)!=true )
    			&& p.getPiece(la, cd)instanceof Pion
				&&((Pion) p.getPiece(la, cd)).isEnPassant()==true)
    		
			return true;
    	else if (ld-1==la&&cd-1==ca &&p.getPieceVide(la,ca)==true &&(p.getPieceVide(la, cd)!=true)
    			&& p.getPiece(la, cd)instanceof Pion
    			&&((Pion) p.getPiece(la, cd)).isEnPassant()==true)
    		return true;
    	
    	else if(ld+1==la&&cd-1==ca &&p.getPieceVide(la,ca)==true &&(p.getPieceVide(la, cd)!=true)
    			&& p.getPiece(la, cd)instanceof Pion
				&&((Pion) p.getPiece(la, cd)).isEnPassant()==true)
    		return true;
    	return false;
    }
    public boolean sautDouble(Plateau p,int ld,int cd,int la,int ca)
    {
    	if(ld+2==la && ld==1 && cd==ca && p.getPieceVide(ld+1,cd)==true && p.getPieceVide(la,ca)==true) // s'il n'y a pas de piece sur les deux cases
			return true;
    	else if(ld-2==la && ld==6 && cd==ca && p.getPieceVide(ld-1,cd)==true && p.getPieceVide(la,ca)==true) // s'il n'y a pas de piece sur les deux cases
    		return true;
    	return false;
    }
    
    
	/**
	 * @return the enPassant
	 */
	public boolean isEnPassant() {
		return enPassant;
	}

	/**
	 * @param enPassant the enPassant to set
	 */
	public void setEnPassant(boolean enPassant) {
		this.enPassant = enPassant;
	}
    
    
    
 }
