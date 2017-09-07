import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Personnage extends JLabel implements java.io.Serializable {


    private int posX = 0;
    private int posY = 0;
    private int energy = 10;
    private int mana = 100;
    private int niveau = 0;

    private boolean regime=false;

    ImageIcon HAUT, BAS, GAUCHE, DROITE;
    public boolean stopwork = false;
    public final int ICON_WIDTH = 48, ICON_HEIGHT = 48;
    
    // TODO:
    public int getMana(){
    	return this.mana;
    }
    // TODO: 
    public void setMana(int n){
    	this.mana = n;
    }
    
    // TODO: 
    public int getEnergy(){
    	return this.energy;
    }
    
    // TODO:
    public void setEnergy(int n){
    	this.energy = n;
    }
    // TODO: 
    public int getPosX() {
        return this.posX;
    }
    // TODO: 
    public int getPosY() {
        return this.posY;
    }

    // TODO: constructeur pour positionner le perso dans la frame avec le bon icone
    public Personnage() {
        super();

            DROITE = new ImageIcon("./resources/joueur_droite.png");
            GAUCHE = new ImageIcon("./resources/joueur_gauche.png");
            BAS = new ImageIcon("./resources/joueur_bas.png");
            HAUT = new ImageIcon("./resources/joueur_haut.png");
            setIcon(BAS);
            setLocation(posX, posY);
            setSize(getPreferredSize());//Adapte en fonction de la taille de l'image.
            setVisible(true);

    }
    // fin TODO
    public int getNiveau() {
        return niveau;
    }
    public void setNiveau(int n) {
        niveau = n;
    }

    public void setRegime (boolean r){
    	regime=r;
    }
    public boolean getRegime (){
    	return regime;
    }    
    
    public void bouger(int i, int nposx, int nposy, Map m) {
        //Donner
    	if(stopwork)
            return;
        // TODO: fonction qui met le bon icone en fonction de l'orientation du perso
        // et bien sÃ»r, on ne peut pas sortir de la fenÃªtre...
        // 1 : HAUT
        // 2 : BAS
        // 3 : GAUCHE
        // 4 : Droite
    	// i étant le deplacement voulue en fonction de ça on setIcon
    	 switch (i) {
    	    case 1: 
    	      setIcon(HAUT);
    	      break;
    	    case 2: 
    	      setIcon(BAS);
    	      break;
    	    case 3: 
    	      setIcon(GAUCHE);
    	      break;
    	    case 4: 
    	      setIcon(DROITE);
    	    }
    	 
    	 //Pour que le perso ne sorte pas de l'écran !
    	  if ((nposx < m.getWidth() - this.ICON_WIDTH) && (nposx > 1)) {
    	      posX = nposx;
    	    }
    	    if ((nposy < m.getHeight() - this.ICON_HEIGHT) && (nposy > 1)) {
    	      posY = nposy;
    	    }
        setLocation(posX,posY);
    }
    
}