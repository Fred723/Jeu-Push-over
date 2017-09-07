public  abstract class Piece {
	  //Une piece est caracterisee par sa couleur et son type    //(roi, Dame ....)    // ces attributs sont accessibles aux classes filles
    protected int couleur;   
    protected int type;
    //Constantes  
    static final int VIDE = -1; 
   // static final int BORD = -2;    
    
    static final int ROI = 0;
    static final int DAME = 1;
    static final int CAV = 2; 
    static final int TOUR = 3;   
    static final int FOU = 4; 
    static final int PION = 5;   
    
    final static int BLANC = 1;
    final static int NOIR = 0;
    
    // constructeur    
    public Piece(int couleur) { 
    	this.couleur = couleur; 
    	}
    // constructeur de recopie    
    public Piece(Piece piece) { 
    	this.couleur = piece.couleur; 
    	this.type = piece.type;  
    	}
    //retourne la couleur de la piece    
    public int getCouleur() { 
    	if (this.couleur== BLANC) 
    		return BLANC;
    	else
    		return NOIR;
    	}
    //retourne le type de la piece  
    protected int getType() {
    	return type;  
    	}
    public String toString(){
		switch(type){ 
		case Piece.ROI :    
			if(couleur==Piece.BLANC)
				return "RB"; 
			if(couleur==Piece.NOIR) 
				return "RN";   
			break;
		case Piece.DAME : 
			if(couleur==Piece.BLANC) 
				return "DB";   
			if(couleur==Piece.NOIR) 
				return "DN"; 
			break;
		case Piece.CAV :  
			if(couleur==Piece.BLANC)
				return "CB"; 
			if(couleur==Piece.NOIR)
				return "CN";  
			break;
		case Piece.FOU :   
			if(couleur==Piece.BLANC) 
				return "FB";
		if(couleur==Piece.NOIR) 
			return "FN"; 
		break;
		case Piece.TOUR :   
			if(couleur==Piece.BLANC)
				return "TB";  
			if(couleur==Piece.NOIR) 
				return "TN"; 
			break;
		case Piece.PION :    
			if(couleur==Piece.BLANC) 
				return "PB";
						
			if(couleur==Piece.NOIR)
				return "PN";   
			break;
		
			default : return ""; 
			} 
		return "";
    }

	public abstract boolean deplacement(Plateau p,int ld, int cd, int la, int ca, int couleur);
	
}
