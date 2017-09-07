import javax.swing.*;
import java.awt.*;

public class Gateau extends JLabel implements java.io.Serializable {
    ImageIcon img, img2;
    int type=1;
    public final int ICON_WIDTH = 32, ICON_HEIGHT = 32;

// TODO: placement aléatoire des gateaux sur la map
    public Gateau(Map m) {
    	
        img  = new ImageIcon("./resources/gateau.png");
        img2  = new ImageIcon("./resources/gateau2.png");
        // TODO: placement aléatoire des gateaux sur la map	
        // définir un type de gateau par défaut, 1 pour la vie, 2 pour le mana, et son icone associé
        
        //on set l'icone img
        this.setIcon(img);
        //on met la taille en getPreferredSize pour avoir la taille
        this.setSize(this.getPreferredSize());
        // ensuite placer aléatoirement le gateau sur la map.
        
        //Fonction random de Nutrioniste
        Dimension ecran = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(20 + (int)((ecran.width - 200) / 2 * Math.random()), 20 + (int)((ecran.height - 200) / 2 * Math.random()));
        this.setVisible(true);
    }
    
    public void setIcon(final int i) {
        if (i == 1) {
            this.setIcon(this.img);
            this.type = 1;
        }
        else if (i == 2) {
            this.setIcon(this.img2);
            this.type = 2;
        }
    }
    public int getGateau(){
    	return type;
    }


}
