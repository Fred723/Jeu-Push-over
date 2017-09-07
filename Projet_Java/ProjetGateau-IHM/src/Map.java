import javax.swing.*;
import java.awt.*;
import java.util.Random;
public class Map extends JPanel {

    public static final Color CITY = new Color(214, 217, 223);
    public static final Color DESERT = new Color(255, 204, 102);
    public static final Color DIRT_ROAD = new Color(153, 102, 0);
    public static final Color FOREST = new Color(0, 102, 0);
    public static final Color HILLS = new Color(51, 153, 0);
    public static final Color LAKE = new Color(0, 153, 153);
    public static final Color MOUNTAINS = new Color(102, 102, 255);
    public static final Color OCEAN = new Color(0, 0, 153);
    public static final Color PAVED_ROAD = new Color(51, 51, 0);
    public static final Color PLAINS = new Color(102, 153, 0);

    public static final Color[] TERRAIN = {
            CITY,
            DESERT,
            DIRT_ROAD,
            FOREST,
            HILLS,
            LAKE,
            MOUNTAINS,
            OCEAN,
            PAVED_ROAD,
            PLAINS
    };
    int indexTile = 0;
    private Dimension jframedim;

    public Map(Dimension d) {
        super(null);
      //inithialisations des variables d'instances graces au setteurs !
        this.setJframedim(d);
    }
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	g.clearRect(0, 0, this.getWidth(), this.getHeight());//Le rectangle prends tout la place 
    	
    	
    	if(indexTile+1 > TERRAIN.length)//si on est au bout pour eviter un ArrayBoundException o remet l'index a 0
    		indexTile=0;

    	//Setters
		g.setColor(Map.TERRAIN[this.indexTile]);//Change en fonction d'indexTitle donc en fonction de nombre indexTitle la map va changer de couleur
		g.fillRect(0,0,getWidth(),getHeight()); //rectangle de la taille de la fenetre
			}
	/**
	 * @return the jframedim
	 */
	public Dimension getJframedim() {
		return jframedim;
	}
	/**
	 * @param jframedim the jframedim to set
	 */
	public void setJframedim(Dimension jframedim) {
		this.jframedim = jframedim;
	}
}