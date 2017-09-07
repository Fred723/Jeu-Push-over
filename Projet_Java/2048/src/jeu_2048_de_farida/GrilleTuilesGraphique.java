package jeu_2048_de_farida;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import com.farida.android_2048.modele.GrilleTuiles;
import com.farida.android_2048.modele.Tuile;

public class GrilleTuilesGraphique extends JPanel
{
	/* Espace entre le dessin de la tuile et le bord de la grille */
	public static final int MARGE = 4;
	
	public static final int FORME_RONDE = 0;
	public static final int FORME_CARREE = 1;
	
	public static final int EPAISSEUR = 2;
	
	/* Forme de dessin de la tuile */
	private int formeTuile;
	private GrilleTuiles grille;
	
	private Image image; // Image de fond de la grille.
	
	public GrilleTuilesGraphique(GrilleTuiles grilleTuiles)
	{
		this.image = Toolkit.getDefaultToolkit().getImage("/home/usager/11500697/Bureau/jeu_2048_fond_ecran.png"); // Dessine l'image de fond.
		this.formeTuile = GrilleTuilesGraphique.FORME_CARREE; // Forme de dessin des tuiles.
		this.grille = grilleTuiles;
	}
	
	public void setFormeTuile(int formeTuile)
	{
		this.formeTuile = formeTuile;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g); // Appel de la méthode paintComponent(Graphics g) de JPanel.
		
		Graphics2D g2D = (Graphics2D) g; // Caste la variable-référence g sur une instance de la classe Graphics en Graphics2D pour pouvoir utiliser des méthodes spécifiques.        
		
		int largeurEcran = this.getWidth(); // Récupère la largeur du panneau.
		int hauteurEcran = this.getHeight(); // Récupère la hauteur du panneau.
		
		int tailleJeu = this.grille.getSize();
		
	    int hauteurCase = hauteurEcran / tailleJeu; // Récupération de la hauteur d'une tuile.
	    int largeurCase = largeurEcran / tailleJeu; // Récupération de la largeur d'une tuile.
	        
	    g.drawImage(this.image, 0, 0, largeurEcran, hauteurEcran, this); // Dessine l'image de fond de la grille.
	    g.setColor(Color.black); // Dessine les bordures des tuiles.
	      
	    
	    /* Délimitations des lignes */
	    for(int i = 0; i <= tailleJeu; i ++)
	    {
	    	if(i == 0)
	    		g.fillRect(0,i * hauteurCase,largeurEcran, GrilleTuilesGraphique.EPAISSEUR);	       
	    	else if(i >= tailleJeu)
	        	g.fillRect(0, tailleJeu * hauteurCase - GrilleTuilesGraphique.EPAISSEUR,largeurEcran, GrilleTuilesGraphique.EPAISSEUR);
	        else
	        	g.fillRect(0,i * (hauteurCase - GrilleTuilesGraphique.EPAISSEUR), largeurEcran, GrilleTuilesGraphique.EPAISSEUR * 2);
	    }

	    /* Délimitations des colonnes */
	    for(int j = 0; j <= tailleJeu; j++)
	    { 	
	        if(j == 0)
	        	g.fillRect(j * largeurCase, 0, GrilleTuilesGraphique.EPAISSEUR, hauteurEcran);
	        else if(j >= tailleJeu)
	        	g.fillRect(tailleJeu * largeurCase - GrilleTuilesGraphique.EPAISSEUR, 0, GrilleTuilesGraphique.EPAISSEUR, hauteurEcran);
	        else
	        	g.fillRect(j * (largeurCase - GrilleTuilesGraphique.EPAISSEUR), 0, GrilleTuilesGraphique.EPAISSEUR * 2, hauteurEcran);
	    }
	    
	    /* Dessin des tuiles */
	    for(int i = 0; i < tailleJeu; i++)
	    {
	    	for(int j = 0; j < tailleJeu; j++) 
	    	{
	    		Tuile tuile = this.grille.getUneTuile(j, i);
	    		
	    		if(tuile != null)
	    		{
	    			g.setColor(GrilleTuiles.getCouleurTuile(tuile)); // Remplissage de la tuile avec la couleur qui convient.
	        
	    			switch(this.formeTuile)
	    			{
	        
	    				case GrilleTuilesGraphique.FORME_CARREE:
	    					g.setColor(Color.BLACK);
	    					g.drawRect(i * largeurCase + 1 + GrilleTuilesGraphique.MARGE, j * hauteurCase + 1 + GrilleTuilesGraphique.MARGE,largeurCase - 1 - 4 * GrilleTuilesGraphique.MARGE, hauteurCase - 1 - 4 * GrilleTuilesGraphique.MARGE);
	    					g.setColor(GrilleTuiles.getCouleurTuile(tuile)); // Remplissage de la tuile avec la couleur qui convient.
	    					g.fillRect(i * largeurCase + 1 + GrilleTuilesGraphique.MARGE, j * hauteurCase + 1 + GrilleTuilesGraphique.MARGE, largeurCase - 1 - 4 * GrilleTuilesGraphique.MARGE, hauteurCase - 1 - 4 * GrilleTuilesGraphique.MARGE);
	    					break;
	        
	    				case GrilleTuilesGraphique.FORME_RONDE: 
	    					g.setColor(Color.BLACK);
	    					g.drawOval(i * largeurCase + 1 + GrilleTuilesGraphique.MARGE, j * hauteurCase + 1 + GrilleTuilesGraphique.MARGE, largeurCase - 1 - 2 * GrilleTuilesGraphique.MARGE, hauteurCase - 1 - 2 * GrilleTuilesGraphique.MARGE);
	    					g.setColor(GrilleTuiles.getCouleurTuile(tuile)); // Remplissage de la tuile avec la couleur qui convient.
	    					g.fillOval(i * largeurCase + 1 + GrilleTuilesGraphique.MARGE, j * hauteurCase + 1 + GrilleTuilesGraphique.MARGE, largeurCase - 1 - 2 * GrilleTuilesGraphique.MARGE, hauteurCase - 1 - 2 * GrilleTuilesGraphique.MARGE);
	    					break;
	    			}
	        
	    			String s = "" + tuile.getValue();
	    			
	    			g.setFont(new Font("Arial", Font.BOLD, 24)); // Choix de la police de caractères.
	    			FontMetrics fm = getFontMetrics(g.getFont()); // Détermination de la dimension de la fonte utisée.
	    			
	    			Rectangle2D textsize = fm.getStringBounds(s, g); // Détermination du rectangle contenant le texte.
	    			
	    			/* Dessine le texte dans la tuile. */
	    			g.setColor(Color.black); 
	    			int xPos = i * largeurCase + 1 + (int) (largeurCase - textsize.getWidth()) / 2;
	    			int yPos = j * hauteurCase + 1 + (int) (hauteurCase - textsize.getHeight()) / 2 + fm.getAscent();
	    			g.drawString(s,xPos,yPos);
	    		}
	        }
	    }
		
		//g2D.drawImage(image, 0, 0, largeurEcran, hauteurEcran, this);
		
		/* Modifie l'épaisseur de la brosse */
		//Stroke stroke = new BasicStroke(EPAISSEUR);
		//g2D.setStroke(stroke);
		
		/* Trace le contour de la grille */
		//g2D.drawRect(0, 0, width-MARGE, height-MARGE);
		
		/* Trace le quadrillage */
		//for(int i = 1; i < 4; i++)
		/*{
			g2D.drawLine(0, height / 4 * i, width, height / 4 * i - MARGE); // Lignes.
			g2D.drawLine(width / 4 * i, 0, width / 4 * i, height - MARGE); // Colonnes.	
		}*/
	
		/* Trace les tuiles */
		//g.drawRect(width / 4/8, height / 4/8, width / 4 * 3/4 , height / 4 * 3/4);
		//g.setColor(Color.white);
		//g.fillRect(width / 4/8, height / 4/8, width / 4 * 3/4 , height / 4 * 3/4);
	}
}
