public class Plateau {
	private Piece plat[][];
	private boolean enCours = true;
	private int tour = Piece.BLANC;
	private String piecesPricesB="";
	private String piecesPrisesN="";
	private int nbCoups = 0;
	private boolean echec = false;
    public String [][] piecePrises= new String[6][2];
	
	public Plateau()
	{
		setPlat(new Piece[8][8]);
	}
	public Plateau(Plateau p)
	{
		setPlat(new Piece[8][8]);
		init(p);
	}
	public void setPiece(int l, int c, Piece piece)
	{
		getPlat()[l][c] = piece;
	}
	public void setPieceVide(int l, int c)
	{
		getPlat()[l][c] = null;
	}
	public boolean getPieceVide(int l, int c)
	{
		if (getPlat()[l][c]==null)
			return true;
		else
			return false;
	}
	public void init(){ 
		   setPiece(7,4,new Roi(Piece.BLANC)); 
		   setPiece(0,4,new Roi(Piece.NOIR)); 
		   setPiece(7,3,new Dame(Piece.BLANC));
		   setPiece(0,3,new Dame(Piece.NOIR)); 
		   
		   setPiece(7,2,new Fou(Piece.BLANC)); 
		   setPiece(7,5,new Fou(Piece.BLANC)); 
		   setPiece(0,2,new Fou(Piece.NOIR)); 
		   setPiece(0,5,new Fou(Piece.NOIR)); 
		   
		   setPiece(7,1,new Cavalier(Piece.BLANC));
		   setPiece(7,6,new Cavalier(Piece.BLANC)); 
		   setPiece(0,1,new Cavalier(Piece.NOIR)); 
		   setPiece(0,6,new Cavalier(Piece.NOIR)); 
		   
		   setPiece(7,0,new Tour(Piece.BLANC)); 
		   setPiece(7,7,new Tour(Piece.BLANC));
		   setPiece(0,0,new Tour(Piece.NOIR));
		   setPiece(0,7,new Tour(Piece.NOIR));
		 //pions
		   for(int j = 0; j< 8 ; j++)  
				setPiece(6,j,new Pion(Piece.BLANC)); 
		   for(int j = 0; j< 8 ; j++)
				setPiece(1,j,new Pion(Piece.NOIR));
			//vides 
		   for(int i=3; i<6; i++) 
			   for(int j=0; j<8; j++)
				   setPieceVide(i,j);
		   
		
		   
		   
	}
	public void init(Plateau p)
	{
		for(int l=0; l<getPlat().length; l++)
		{
			for(int c=0; c<getPlat().length; c++)
			{
				this.getPlat()[l][c]=p.getPlat()[l][c];
			}
		}
		
	}
	//Methode permettant d'acceder aux pieces
   public Piece getPiece (int l, int c) { 
    	return getPlat()[l][c];   
    	}
    public int getCouleur (int l , int c) { 
    	return getPiece(l,c).getCouleur(); 
    	}
    public int getType (int l , int c) { 
    	return getPlat()[l][c].getType();  
    	}
    

    public void JoueurSuivant()
    {
    	if(tour == Piece.BLANC)
    		tour = Piece.NOIR;
    	else
    		tour = Piece.BLANC;
    }
    
    //l, c poition intiales ld cd position de deplacement
    public void deplace (int ld, int cd, int la, int ca){
    	//Si la positions demander est le roi affichage erreur => ECHEC
    	//if(getPiece(la,ca) instanceof Roi)    throw new EchiquierException("ECHEC");
    			
    	//Prise d'une piece
    	if(getPieceVide(la,ca)!=true) 
    	{
    		if(tour==Piece.NOIR) 
    			piecesPricesB += " "+piecePrises[getPiece(la,ca).getType()][getPiece(la,ca).getCouleur()]; 
    		else 
    			piecesPrisesN += " "+piecePrises[getPiece(la,ca).getType()][getPiece(la,ca).getCouleur()];
    	}
    	if(getPiece(ld,cd) instanceof Roi)
    		((Roi) getPiece(ld,cd)).setBouger(true);
    	else if(getPiece(ld,cd) instanceof Tour)
    		 ((Tour) getPiece(ld,cd)).setBouger(true);
    	
    	setPiece(la,ca,getPiece(ld,cd));
    	setPieceVide(ld,cd);
    }
    public String afficheTour(){ 
    	if(tour==Piece.BLANC)
    		return "Aux blancs de jouer"; 
    	if(tour==Piece.NOIR)
    		return "Aux noirs de jouer";
    	return ""; 
    	}
    public String nCoups(){ 
    	return "Coups joues: "+nbCoups;
    	}
	
    public boolean pieceAdverse(int l, int c, int couleur)
    {
    	if (couleur==Piece.BLANC)
    		return (getCouleur(l,c) == Piece.NOIR) ; 
    	if (couleur==Piece.NOIR) 
    		return (getCouleur(l,c) == Piece.BLANC) ; 
    	return false;  
    }
	
	public String getPiecesPrisesN() {
		return piecesPrisesN;
	}
	public void setPiecesPrisesN(String piecesPrisesN) {
		this.piecesPrisesN = piecesPrisesN;
	}
	public String getPiecesPricesB() {
		return piecesPricesB;
	}
	public void setPiecesPricesB(String piecesPricesB) {
		this.piecesPricesB = piecesPricesB;
	}
	public int getTour() {
		return tour;
	}
	public void setTour(int tour) {
		this.tour = tour;
	}
	public boolean isEnCours() {
		return enCours;
	}
	public void setEnCours(boolean enCours) {
		this.enCours = enCours;
	}
	public int getNbCoups() {
		return nbCoups;
	}
	public void setNbCoups(int nbCoups) {
		this.nbCoups = nbCoups;
	}
	public String toString()
	{
		int l;
		
		int tiret;
		int c;
		String s = "   ";

		// parcours les lignes
		for( l = 0; l < getPlat().length; l += getPlat().length )
		{
			// parcours les colonnes de la premiere ligne
			for( tiret = 0; tiret < getPlat().length; tiret++ )
			{
				s = s + "-----";
			}

			s = s + "-\n";

			// parcours les colonnes
			for( l = 0; l < getPlat().length; l++ )
			{
				s =s+ " " + (getPlat().length - l)+" ";
				for(c = 0; c<getPlat().length; c++)
					if(getPiece(l,c)==null)
						 s = s + "| "+ "   ";
					else
	                        s = s + "| "+ getPiece(l,c)+" ";
				s = s + "|\n   ";
				for( tiret = 0; tiret < getPlat().length; tiret++ )
				{
					s = s + "-----";
				}

				s = s + "-\n";

			}

			
		}
		s = s + "     ";
		//Lettre
		for(l= 0; l<getPlat().length;l++)
		{
			s = s + (char)(65+l)+"    ";
		}
	

		return s;
				
				
	}
	public Piece[][] getPlat() {
		return plat;
	}
	public void setPlat(Piece plat[][]) {
		this.plat = plat;
		try{    
			  piecePrises [Piece.ROI][Piece.BLANC] = new String("♔");
		         piecePrises [Piece.ROI][Piece.NOIR] = new String("♚");
		         piecePrises [Piece.DAME][Piece.BLANC] = new String("♕");
		         piecePrises [Piece.DAME][Piece.NOIR] = new String("♛");  
		         piecePrises [Piece.CAV][Piece.BLANC] = new String("♘"); 
		         piecePrises [Piece.CAV][Piece.NOIR] = new String("♞");  
		         piecePrises [Piece.TOUR][Piece.BLANC] = new String("♖");  
		         piecePrises [Piece.TOUR][Piece.NOIR] = new String("♜");  
		         piecePrises [Piece.FOU][Piece.BLANC] = new String("♗");  
		         piecePrises [Piece.FOU][Piece.NOIR] = new String("♝");   
		         piecePrises [Piece.PION][Piece.BLANC] = new String("♙"); 
		         piecePrises [Piece.PION][Piece.NOIR] = new String("♟");  
		   }catch(Exception e)
		   {
		    System.out.println(e);
		   }
	}
	
	public boolean matchNul(Plateau p)
	{
		boolean flagColor1 = false;
		boolean flagColor2 = false;
		for(int x=0; x<p.getPlat().length; x++)
		{
			for(int y=0; y<p.getPlat().length; y++)
			{
				if(p.getPieceVide(x, y)!=true)
				{
					if(p.getPiece(x, y).getCouleur()==p.getTour()&& !(p.getPiece(x, y)instanceof Roi))
						flagColor1 = true;
					else if(p.getPiece(x, y).getCouleur()!=p.getTour()&& !(p.getPiece(x, y)instanceof Roi))
						flagColor2 = true;
				}
			}
		}
		if(flagColor1==false && flagColor2==false)
			return false;	// pas assez de pièce pour pouvoir Mater (il reste seulement les 2 Rois)
		
		for(int ld=0; ld<p.getPlat().length; ld++)
		{
			for(int cd=0; cd<p.getPlat().length; cd++)
			{
				for(int la=0; la<p.getPlat().length; la++)
				{
					for(int ca=0; ca<p.getPlat().length; ca++)
					{
						if(p.getPieceVide(ld, cd)!=true && p.getPiece(ld, cd).deplacement(p, ld, cd, la, ca, p.getTour())==true)
							return false;	// aucune piece du joueur ne peut se déplacer
					}
				}
			}
		}
		return true;
	}
	
	public boolean pat(Plateau p)
	{
		boolean flag=false;
		//System.out.println("methode pat test\n");
		
		for(int x=0; x<p.getPlat().length; x++)
		{
			for(int y=0; y<p.getPlat().length; y++)
			{
				if(p.getPieceVide(x, y)!=true && p.getPiece(x, y).getCouleur()==p.getTour() && p.getPiece(x, y)instanceof Roi==false)
				{
					System.out.println("Piece trouvée = "+p.getPiece(x, y));
					Plateau pTest = new Plateau(p);
					for(int la=0; la<p.getPlat().length; la++)
					{
						for(int ca=0; ca<p.getPlat().length; ca++)
						{
							if(pTest.getPiece(x,y).deplacement(pTest, x, y, la, ca, p.getTour())==true)
								return false;
						}
					}
				}
				else if(p.getPieceVide(x, y)!=true && p.getPiece(x, y).getCouleur()==p.getTour() && p.getPiece(x, y)instanceof Roi==true)
				{
					//System.out.println("PAT Roi trouvé "+x+" "+y);
					Plateau pTest = new Plateau(p);
					for(int la=0; la<p.getPlat().length; la++)
					{
						for(int ca=0; ca<p.getPlat().length; ca++)
						{
							if(p.getPiece(x,y).deplacement(pTest, x, y, la, ca, p.getTour()))
								return false;
							else
							{
								for(int la2=0; la2<p.getPlat().length; la2++)
								{
									for(int ca2=0; ca2<p.getPlat().length; ca2++)
									{
										if(((Roi) p.getPiece(x,y)).roque(pTest, x, y, la, ca, p.getTour())==true || ((Roi) p.getPiece(x,y)).saut(pTest, x, y, la, ca, p.getTour())==true)
										{
											
											Plateau pTest2 = new Plateau(p);
											//System.out.println("Nb test = "+(la2*8+ca2));
											//System.out.println(la2+" "+ca2);
										
												pTest2.deplace(x, y, la2, ca2);
												
												//System.out.println("le roi peut se déplacer "+la2 +" "+ca2);
												if(pTest.echec(pTest2, p.getTour())==true)
													flag= true;
											
											/*else
												System.out.println("le roi ne peut se déplacer");*/
	
											//System.out.println("flag = "+flag+"\n");
										}
									}
								}
								
							}

						}
					}
				}
			}
		}
		
		return flag;
	}
	
	public boolean mat(Plateau p, int tour) {
		int l=0;
		//System.out.println("methode mat test\n");
		while(l<p.getPlat().length)
		{
			int c=0;;
			while(c<p.getPlat().length)
			{
				if(p.getPlat()[l][c]!=null && p.getPlat()[l][c] instanceof Roi && p.getPlat()[l][c].getCouleur()==tour) // on cherche le roi du joueur
				{
					for(int x=0; x<p.getPlat().length; x++)
					{
						for(int y=0; y<p.getPlat().length; y++)
						{
							 // on cherche les pieces du joueur
							if(p.getPlat()[x][y]!=null && p.getPlat()[x][y].getCouleur()==tour)
							{
								for(int la=0; la<p.getPlat().length; la++)
								{
									for(int ca=0; ca<p.getPlat().length; ca++)
									{
										Plateau pTest = new Plateau(p);
										if(pTest.getPlat()[x][y].deplacement(pTest,x,y,la,ca, tour)==true)	// si deplacement possible
										{
											//System.out.println(x+" "+y+" "+pTest.getPiece(x,y)+ " "+la+" "+ca+"\n");
											pTest.deplace(x, y, la, ca);	// on test tous les deplacements des pieces du joueur
											if(echec(pTest,tour)==false)	// si au moins une piece permet d'enlever la mise en echec
											{
												return false;				// alors le joueur peut continuer de jouer
											}
										}
									}
									
								}
							}
						}
					}
				}
				c++;
			}
			l++;
		}
		return true; // aucun deplacement ne permet de retirer la mise en echec du roi
	}

	public boolean echec(Plateau p, int tour) {
		int l=0;
		//System.out.println("methode echec test position\n");
		while(l<p.getPlat().length)
		{
			int c=0;;
			while(c<p.getPlat().length)
			{
				if(p.getPlat()[l][c]!=null && p.getPlat()[l][c] instanceof Roi && p.getPlat()[l][c].getCouleur()==tour) // on cherche le roi du joueur
				{
					int tourAdv;
					if(tour==Piece.BLANC)
						tourAdv=Piece.NOIR;
					else
						tourAdv=Piece.BLANC;
					
					for(int x=0; x<p.getPlat().length; x++)
					{
						for(int y=0; y<p.getPlat().length; y++)
						{
							 // deplacement de toutes les pieces adverses vers le roi du joueur
							if(p.getPlat()[x][y]!=null && p.getPlat()[x][y].getCouleur()==tourAdv && p.getPlat()[x][y].deplacement(p, x, y, l, c, tourAdv))
							{
								System.out.println(x+" "+y+" "+p.getPiece(x, y));
								return true; // une piece adverse peut se deplacer sur la case du roi
							}
						}
					}
				}
				c++;
			}
			l++;
		}
		return false; // aucune piece ne peut se deplacer sur la case du roi
	}
	public boolean isEchec() {
		return echec;
	}
	public void setEchec(boolean echec) {
		this.echec = echec;
	}
}
