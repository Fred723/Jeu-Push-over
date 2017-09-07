import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class Fenetre extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	Sauvegarde save = new Sauvegarde();
	Font f = new Font("Dialog",Font.PLAIN,20);
	private Icon [][] images = new Icon[6][2];

    static final int WIDTH=350 , HEIGHT = 350;  
    private Plateau plat;
  
    Audio au = null;
    

    

	private int caseSrcX;
	private int caseSrcY;
	private int caseDestX;
	private int caseDestY;
	
	JPanel pan = new JPanel();
	JPanel panCentre = new JPanel();
	JPanel panEst = new JPanel();
	JPanel panSud = new JPanel();

	//<--- layout --->
	BorderLayout lay = new BorderLayout();
	BoxLayout layEst = new BoxLayout(panEst,BoxLayout.Y_AXIS);
	GridLayout laySud = new GridLayout(1,8);
	GridLayout layCentre = new GridLayout(8,8);
	
	//<--- panEst --->
	JTextField jException = new JTextField();
	JTextField jBlanc = new JTextField(30);
	JTextField jNoir = new JTextField(30);
	JTextArea aCoup = new JTextArea(50,30);
	JScrollPane sp = new JScrollPane(aCoup);
	JLabel lException = new JLabel("Informations");
	JLabel lBlanc = new JLabel("Pieces blanches prises");
	JLabel lNoir = new JLabel("Pieces noirs prises");
	JLabel lCoup = new JLabel("Coups joués");
	
	
	
	
	//<--- panSud --->
	JButton nouvellePartie = new JButton("Nouvelle partie");
	JButton charger = new JButton("Charger une partie");
	JButton sauver = new JButton("Sauvegarder la partie");
	JButton reprendre = new JButton("Reprendre la partie en cours");
	JButton abandon = new JButton("Abandonner la partie");
	JButton quitter = new JButton("Quitter le jeu");
	JButton regles = new JButton("Regles du jeu");
	JButton mute = new JButton("Mute");
	
	//<--- panCentre --->

	
	public Fenetre(int w, int h, Audio au) {
		this.pack();
		JFrame.setDefaultLookAndFeelDecorated(true);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setContentPane(pan);
		pan.setLayout(lay);
		this.au=au;
		au.setFic("Musique_jeu");
	    au.start();
		initPan();
		this.setVisible(true);
	}
	
	public void initPan(){
		pan.add(panEst,BorderLayout.EAST);
		pan.add(panSud,BorderLayout.SOUTH);
		pan.add(panCentre,BorderLayout.CENTER);
		
		initPanEst();
		initPanSud();
		initPanCentre();
		init();
	}
	
	public void initPanEst(){
		panEst.add(lException);
		panEst.add(jException);
		panEst.add(lBlanc);
		panEst.add(jBlanc);
		panEst.add(lNoir);
		panEst.add(jNoir);
		panEst.add(lCoup);
		panEst.add(aCoup);
		jNoir.setFont(f);//Taille tu texte pour les pions prises
		jBlanc.setFont(f);
		aCoup.setFont(f);
		aCoup.setText("");
		
		jException.setHorizontalAlignment(JTextField.CENTER);
		jBlanc.setHorizontalAlignment(JTextField.CENTER);
		jNoir.setHorizontalAlignment(JTextField.CENTER);
		
		jException.setEditable(false);
		jBlanc.setEditable(false);
		jNoir.setEditable(false);
		aCoup.setEditable(false);
		
		jException.setBackground(Color.WHITE);
		jBlanc.setBackground(Color.WHITE);
		jNoir.setBackground(Color.WHITE);
		aCoup.setBackground(Color.WHITE);
		
		JScrollPane scrollPane = new JScrollPane(aCoup, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panEst.add(scrollPane);
		
		//aCoup.setText("a\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\n");
		   
		panEst.setLayout(layEst);
	}
	
	public void initPanSud(){
		panSud.add(nouvellePartie);
		panSud.add(charger);
		panSud.add(sauver);
		panSud.add(reprendre);
		panSud.add(abandon);
		panSud.add(quitter);
		panSud.add(regles);
		panSud.add(mute);
		
		nouvellePartie.addActionListener(this);
		charger.addActionListener(this);
		sauver.addActionListener(this);
		reprendre.addActionListener(this);
		abandon.addActionListener(this);
		quitter.addActionListener(this);
		regles.addActionListener(this);
		mute.addActionListener(this);
		
		panSud.setLayout(laySud);
	}

	public void initPanCentre(){
	
		//Pour l'affichage (blanc/marron)
		for(int i=1; i<9; i++) 
    		for(int j=1; j<9; j++)
    		{
    		 	
    			//Pour l'affichage (blanc/marron)
    		
    	    			Color c;
    	    			if((i+j)%2==0)
    	    				c = new Color(224,220,224); 
    	    			else
    	    				c = new Color(158,79,55);

    	    			

    				panCentre.add(new Case(i,j,c));
    			
    			
    		}
		
		panCentre.setLayout(layCentre);
	}
	
	public void afficheDepla(Plateau p, int srcX, int srcY){
	
		
			for (int i = 0; i <8; i++) {
				for (int j = 0; j < 8; j++) {
					if(p.getPiece(srcX, srcY).deplacement(p, srcX, srcY, i, j, p.getTour()) ==true)
					{
						panCentre.getComponent(i*8+j).setBackground(Color.GREEN);
						
					}
					
				}
				
			}
	
	}//Fin AfficheDepla

	    public void setCaseColor()
	    {
	 	   
			for (int x = 0; x < 8; x++) {
				for (int y = 0; y < 8; y++) {
					Color c;
					if((x+y)%2==0)
					{
						c = new Color(224,220,224);
						((Case) panCentre.getComponent(y+x*8)).setBackground(c);
					}
					else
					{	
						c = new Color(158,79,55);
						((Case) panCentre.getComponent(y+x*8)).setBackground(c);
					}
				}
			}
			
	    }
	    public void init () {
	    	//Chargement des images et stockage dans le tableau images
	    	try{
	    	   	images [Piece.ROI][Piece.BLANC] = new ImageIcon("images/roiB.png"); 
	    	    images [Piece.ROI][Piece.NOIR] = new ImageIcon("images/roiN.png");
	    	    images [Piece.DAME][Piece.BLANC] = new ImageIcon("images/dameB.png");  
	    	    images [Piece.DAME][Piece.NOIR] = new ImageIcon("images/dameN.gif");
	    	    images [Piece.CAV][Piece.BLANC] = new ImageIcon("images/cavalierB.png"); 
	    	    images [Piece.CAV][Piece.NOIR] = new ImageIcon("images/cavalierN.png");
	    	    images [Piece.TOUR][Piece.BLANC] = new ImageIcon("images/tourB.png"); 
	    	    images [Piece.TOUR][Piece.NOIR] = new ImageIcon("images/tourN.png");  
	    	    images [Piece.FOU][Piece.BLANC] = new ImageIcon("images/fouB.png"); 
	    	    images [Piece.FOU][Piece.NOIR] = new ImageIcon("images/fouN.png");  
	    	    images [Piece.PION][Piece.BLANC] = new ImageIcon("images/pionB.png");  
	    	    images [Piece.PION][Piece.NOIR] = new ImageIcon("images/pionN.png");
	    	
	    	}catch(NullPointerException ex){
	    		System.out.println(ex);
	    	}
	    }
	    
	 /*******************************Classe interne***************************************/
    class Case extends JButton implements MouseListener {

    	/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private int ligne, col;
// Construit une case avec son numéro de ligne, de colonne et sa couleur

    	public Case(int _ligne, int _col ,Color couleur){
		super();
		ligne = _ligne;
		col = _col; 
		setBackground(couleur);
	
		addMouseListener(this); 
		} 
    	
    	public void setCouleur(Color couleur)
    	{
    		setBackground(couleur);
    	}

	//Affecte une piece à la case en fonction du type et de la couleur de la pièce
	public void setPiece(int type, int couleur) {    
		switch(type) {  
		case Piece.ROI :
			if(couleur == Piece.BLANC) 
				setIcon(images[Piece.ROI][Piece.BLANC]);
			if(couleur == Piece.NOIR) 
				setIcon(images[Piece.ROI][Piece.NOIR]); break;
		case Piece.DAME : 
			if(couleur == Piece.BLANC) 
				setIcon(images[Piece.DAME][Piece.BLANC]);
			if(couleur == Piece.NOIR) setIcon(images[Piece.DAME][Piece.NOIR]); break; 
		case Piece.CAV : 
			if(couleur == Piece.BLANC) 
				setIcon(images[Piece.CAV][Piece.BLANC]);
			if(couleur == Piece.NOIR) 
				setIcon(images[Piece.CAV][Piece.NOIR]); break;  
		case Piece.TOUR : 
			if(couleur == Piece.BLANC)
				setIcon(images[Piece.TOUR][Piece.BLANC]); 
			if(couleur == Piece.NOIR) 
				setIcon(images[Piece.TOUR][Piece.NOIR]); break;
		case Piece.FOU : 
			if(couleur == Piece.BLANC) 
				setIcon(images[Piece.FOU][Piece.BLANC]); 
			if(couleur == Piece.NOIR) 
				setIcon(images[Piece.FOU][Piece.NOIR]); break;
		case Piece.PION :
			if(couleur == Piece.BLANC) 
				setIcon(images[Piece.PION][Piece.BLANC]); 
			if(couleur == Piece.NOIR)
				setIcon(images[Piece.PION][Piece.NOIR]); break; 
		default : setIcon(null);   
		}
	}
	public Icon getP(int type, int couleur) {    
		switch(type) {  
		case Piece.ROI :
			if(couleur == Piece.BLANC) 
				return(images[Piece.ROI][Piece.BLANC]);
			if(couleur == Piece.NOIR) 
				return(images[Piece.ROI][Piece.NOIR]); break;
		case Piece.DAME : 
			if(couleur == Piece.BLANC) 
				return(images[Piece.DAME][Piece.BLANC]);
			if(couleur == Piece.NOIR) return(images[Piece.DAME][Piece.NOIR]); break; 
		case Piece.CAV : 
			if(couleur == Piece.BLANC) 
				return(images[Piece.CAV][Piece.BLANC]);
			if(couleur == Piece.NOIR) 
				return(images[Piece.CAV][Piece.NOIR]); break;  
		case Piece.TOUR : 
			if(couleur == Piece.BLANC)
				return(images[Piece.TOUR][Piece.BLANC]); 
			if(couleur == Piece.NOIR) 
				return(images[Piece.TOUR][Piece.NOIR]); break;
		case Piece.FOU : 
			if(couleur == Piece.BLANC) 
				return(images[Piece.FOU][Piece.BLANC]); 
			if(couleur == Piece.NOIR) 
				return(images[Piece.FOU][Piece.NOIR]); break;
		case Piece.PION :
			if(couleur == Piece.BLANC) 
				return(images[Piece.PION][Piece.BLANC]); 
			if(couleur == Piece.NOIR)
				return(images[Piece.PION][Piece.NOIR]); break; 
		default : return(null);   
		}
		return null;
	}
	
	//retourne la ligne de la case
	public int getL(){  
		return ligne; 
	} //retourne la colone de la case 
	public int getC(){  
		return col;
	}
	
	//dessine un bord autour de la case sélectionnée si la case n'est pas vide
	
	public void paintComponent(Graphics g){ 
		try{
			for(int i=0;i<8;i++)
				for(int j=0;j<8;j++)
				{
					if(plat.getPieceVide(i, j)!=true)
						((Case) panCentre.getComponent(j+i*8)).setPiece(plat.getPiece(i, j).type,plat.getPiece(i, j).couleur);
					else
						((Case) panCentre.getComponent(j+i*8)).setIcon(null);
				}
		}catch(NullPointerException ex){
			System.out.println(ex);
		}
		super.paintComponent(g);
		
		}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	
		try{
			
			int i = ((Case)e.getSource()).ligne -1;
			int j = ((Case)e.getSource()).col -1;
			System.out.println(i+" "+j);
			setCaseColor();
			
				//	System.out.println(x+" "+y+" "+((Case) panCentre.getComponent(y+x*8)).getBackground());
			
			
			if( plat.getPieceVide(i,j)!=true && plat.getCouleur(i,j)==plat.getTour() )
			{
				
				
				caseSrcX = i;
				caseSrcY = j;
				
				afficheDepla(plat, i, j);
				
			}
			else if( (plat.getPieceVide(i,j)==true || plat.getPieceVide(i,j)!=true && plat.getCouleur(i,j)!=plat.getTour()) && caseSrcX>=0 && caseSrcY>=0 )
			{
				caseDestX = i;
				caseDestY = j;
				
				boolean testCoup = coup(caseSrcX, caseSrcY, caseDestX, caseDestY);
				if(testCoup == true)
					((Case) panCentre.getComponent(caseSrcY+caseSrcX*8)).setIcon(null);
				repaint();
				
				caseSrcX = -1;
				caseSrcY = -1;
			}
			
		}catch(Exception ex){
			System.out.println(ex);
		}
		
	}
	
	public boolean coup(int srcX, int srcY, int destX, int destY){
		
		if(plat.getPiece(srcX, srcY).deplacement(plat, srcX, srcY, destX, destY, plat.getTour()))
		{				
			
			//System.out.println("Deplacement possible");
			Plateau pTest = new Plateau(plat);	// plateau par copie pour effectuer les tests
			pTest.deplace(srcX, srcY, destX, destY);
			
			if(plat.echec(pTest,plat.getTour())==false)	// si le roi n'est pas ou n'est plus en echec, on autorise le deplacement
			{
				//roque
				if(plat.getPiece(srcX, srcY)instanceof Roi && (destY-srcY >1 || destY+srcY>1)&& ((Roi) plat.getPiece(srcX, srcY)).roque(plat, srcX, srcY, destX, destY, plat.getTour()) )
				{
					if(destY==2)
						plat.deplace(srcX, 0, destX, 3);
					else if(destY ==6)
						plat.deplace(srcX, 7, destX, 5);
					
				}
				
				
					
				//Deplacement "normale"
				String s;//plat.getPiece(srcX, srcY).toString()+" "+srcX+""+srcY+" > "+" "+destX+""+destY+"\n";
				String pSrc = plat.piecePrises[plat.getPiece(srcX, srcY).getType()][plat.getPiece(srcX,srcY).getCouleur()]+" ["+srcX+","+srcY+"]";
				String pDest = "["+destX+","+destY+"]";
				if(plat.getPieceVide(destX, destY)==false)
				{
					s = pSrc + " X " + plat.piecePrises[plat.getPiece(destX, destY).getType()][plat.getPiece(destX,destY).getCouleur()]+ pDest;
				}
				else
				{
					s = pSrc + " > " + pDest;
				}
				s+="\n";

				aCoup.setText(aCoup.getText()+s);
				//System.out.println(aCoup.getText());
				plat.deplace(srcX, srcY, destX, destY);
				
				
				jBlanc.setText(plat.getPiecesPricesB());
				jNoir.setText(plat.getPiecesPrisesN());
				//System.out.println("Deplacement effectue");
				
				//Promotion
				if(plat.getPiece(destX, destY)instanceof Pion && ((Pion) plat.getPiece(destX, destY)).promotion(destX,destY))
				{
				
					Object select[] = { "Dame", "Cavalier", "Fou", "Tour" };
					
				    int result = JOptionPane.showOptionDialog(this,null, "Choisissez une pièce pour remplacer votre pion",
				        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, select,
				        null);
				   
					System.out.println(result);
					Piece p = null;
				    if (result == 0)
				    
				    	p = new Dame(plat.getTour());
				    	
				    
				    else if(result == 1)
				    	p=new Cavalier(plat.getTour());
				    else if(result == 2)
				    	p = new Fou(plat.getTour());
				    else
				    	p= new Tour(plat.getTour());
				   plat.setPiece(destX, destY,p);
				}
				//echec
				if(plat.isEchec()==true)
					plat.setEchec(false);
				
				//Changement de tour
				if(plat.getTour()==Piece.BLANC)
				{
					plat.setTour(Piece.NOIR);
					
				}
				else
				{
					plat.setTour(Piece.BLANC);
					
				}
				mettreText(plat);
				//Echec + mat => Fin de partie
				if(plat.echec(plat, plat.getTour()))
				{
					jException.setText(jException.getText()+" Vous êtes en echecs !");
					if(plat.mat(plat, plat.getTour()))
					{
						jException.setText("Vous avez gagnez !!!");
						partieFinie(plat.getTour(), 0);
					}
						
				}
				else if(plat.pat(plat))
					partieFinie(plat.getTour(), 2);
				else if(plat.matchNul(plat))
					partieFinie(plat.getTour(), 3);
				
				return true;
			}
			else
				System.out.println("Deplacement non effectue");
		}
		else
			System.out.println("Deplacement non possible");
		return false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	  }
    
    
    public int getCaseSrcX() {
		return caseSrcX;
	}

	public void setCaseSrcX(int caseSrcX) {
		this.caseSrcX = caseSrcX;
	}

	public int getCaseSrcY() {
		return caseSrcY;
	}

	public void setCaseSrcY(int caseSrcY) {
		this.caseSrcY = caseSrcY;
	}

	public int getCaseDestX() {
		return caseDestX;
	}

	public void setCaseDestX(int caseDestX) {
		this.caseDestX = caseDestX;
	}

	public int getCaseDestY() {
		return caseDestY;
	}

	public void setCaseDestY(int caseDestY) {
		this.caseDestY = caseDestY;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		setCaseColor();
		if( ((JButton)e.getSource()==nouvellePartie ))
		{
			if(plat!=null)
			{
				int n = JOptionPane.showConfirmDialog(null, "La partie en cours sera écrasée.\nVoulez-vous continuer ?", "Confirmation", JOptionPane.YES_NO_OPTION);
				System.out.println(n+"\n");
				if(n!=0)
					return;
				else
				{
					plat = new Plateau();
					plat.init();
					jBlanc.setText("");
					jNoir.setText("");
					aCoup.setText("");
				
				}
			}
			else
			{
				plat = new Plateau();
				plat.init();
			}
			mettreText(plat);
			repaint();
		}
		else if( ((JButton)e.getSource()==charger ))
		{
			try{
				plat = new Plateau();
				save.charger("Partie.txt",plat);
				JOptionPane.showMessageDialog(null, "Partie chargée !", "Information",JOptionPane.INFORMATION_MESSAGE);
			}catch(NullPointerException ex){

				JOptionPane.showMessageDialog(null, "La partie n'a pu être chargée !\nLe fichier de sauvegarde n'existe pas.", "Information",JOptionPane.INFORMATION_MESSAGE);
			}
			mettreText(plat);
			repaint();
		}
		else if( ((JButton)e.getSource()==sauver ) && plat!=null)
		{
			try{
				save.sauvegarder("Partie.txt", plat);
				JOptionPane.showMessageDialog(null, "Partie sauvegardée !", "Information",JOptionPane.INFORMATION_MESSAGE);
			}catch(Exception ex){
				System.out.println(ex);
			}
		}
		else if( ((JButton)e.getSource()==reprendre ))
		{
			try{
				plat = new Plateau();
				save.charger("Partie2.txt", plat);
				JOptionPane.showMessageDialog(null, "Partie chargée !", "Information",JOptionPane.INFORMATION_MESSAGE);
			}catch(NullPointerException ex){
			
				JOptionPane.showMessageDialog(null, "La partie n'a pu être chargée !\nAucune partie en cours.", "Information",JOptionPane.INFORMATION_MESSAGE);
			}
			repaint();
		}
		else if( ((JButton)e.getSource()==abandon ) && plat!=null)
		{
			
			partieFinie(plat.getTour(),1);
			plat = null;
		}
		else if( ((JButton)e.getSource()==quitter ) )
		{
			 if(plat!= null)
				 save.sauvegarder("Partie2.txt", plat);
			System.exit(0);
		}
		else if( ((JButton)e.getSource()==regles ))
		{
				JOptionPane.showMessageDialog(null, "Faire échec et mat, (<— définition et tableaux de mats) évidemment, l’adversaire peut abandonner et vous avez la possibilité de gagner au temps."
						+ "\nOn dit que le Roi est en échec, lorsque la case qu’il occupe est contrôlée par une pièce adverse. (en d’autres termes, lorsqu’une pièce peut le manger). "
						+ "\n\n–> le Roi doit donc OBLIGATOIREMENT parer cet échec."
						+ "\nSi le Roi ne peut parer l’échec, il perd la partie, puisqu’il est échec et mat."
						+ "\n\n—> Caractéristiques de l’échec et mat :"
						+ "\n\t– Le Roi ne peut plus se déplacer\n"
						+ "\t– Aucune pièce alliée ne peut s’interposer pour parer l’échec\n"
						+ "\t– La pièce qui fait l’échec ne peut être éliminée", "Regle du jeu",JOptionPane.INFORMATION_MESSAGE);
		
			
		}
		else if( ((JButton)e.getSource()==mute ))
		{
			if(au.isMute()==false)
				au.setMute(true);
			else
				au.setMute(false);
		}
		
	}
	private void mettreText(Plateau plat) {
		if (plat.getTour() == Piece.BLANC)
			jException.setText("C'est au blancs aux jouer !");
		
		else
			jException.setText("C'est au noirs aux jouer !");
	}

	private void partieFinie(int tour, int mode)
	{
		if(mode==0) // victoire par MAT
		{
			if(tour == Piece.BLANC)
				JOptionPane.showMessageDialog(null, "Victoire des noirs par MAT !", "Partie finie !",JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, "Victoire des blancs par MAT !", "Partie finie !",JOptionPane.INFORMATION_MESSAGE);
		}
		else if(mode==1) // victoire par ABANDON
		{
			if(tour==Piece.BLANC)
				JOptionPane.showMessageDialog(null, "Victoire des noirs par abandon !", "Partie finie !",JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, "Victoire des blancs par abandon !", "Partie finie !",JOptionPane.INFORMATION_MESSAGE);
				
		}
		else if(mode==2) // égalité par PAT
		{
				JOptionPane.showMessageDialog(null, "Match nul par PAT !", "Partie finie !",JOptionPane.INFORMATION_MESSAGE);
		}
		else if(mode==3) // égalité
		{
			JOptionPane.showMessageDialog(null, "Match nul !", "Partie finie !",JOptionPane.INFORMATION_MESSAGE);
		}
		else
			JOptionPane.showMessageDialog(null, "Erreur", "Partie finie !",JOptionPane.INFORMATION_MESSAGE);
	}
	
	
	
	
}
