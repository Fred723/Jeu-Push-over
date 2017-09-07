public class Cavalier extends Piece {

    public Cavalier(int couleur) { 
    	super(couleur); 
    	super.type = Piece.CAV;    
    	}

    public boolean deplacement (Plateau p,int ld, int cd, int la, int ca, int couleur) {
    	if(p.getPieceVide(la,ca)!=true && p.getPiece(la,ca).getCouleur()==couleur || p.getPiece(ld,cd).getCouleur()!=couleur)
    		return false;    	
    	if( ((ld+1==la || ld-1==la) && (cd+2==ca || cd-2==ca)) // droite et gauche
    			|| ((ld+2==la || ld-2==la) && (cd+1==ca || cd-1==ca)) // haut et bas
    			&& (p.getPieceVide(la,ca)==true	// s'il n'y a pas de piece
    			|| p.getPieceVide(la,ca)!=true
    			&& p.getPiece(la,ca).getCouleur()!=couleur	// si la piece destination n'appartient pas au joueur
    			&& p.getPiece(ld,cd).getCouleur()==couleur) )	// si la piece source appartient au joueur
    		return true;
		return false;	
    }
}
