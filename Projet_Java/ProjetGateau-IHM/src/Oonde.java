import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class Oonde extends JLabel implements java.io.Serializable{
    ImageIcon img, img2, img3;
    private int posX = 0;
    private int posY = 0;
    public final int ICON_WIDTH = 100, ICON_HEIGHT = 100;
    
      public int getPosX() {
        return posX;
      }

      public void setPosX(int posX) {
        this.posX = posX;
        setLocation(posX,posY);

      }

      public int getPosY() {
        return posY;
      }

      public void setPosY(int posY) {
        this.posY = posY;
        setLocation(posX,posY);

      }

	  public void run(){

	  }
	  public Oonde(){
	    	super();
	    	img  = new ImageIcon("./resources/onde.png");
	    	img2  = new ImageIcon("./resources/onde0.png");
	    	img3  = new ImageIcon("./resources/onde1.png");

	        setIcon(img);
	        setLocation(posX,posY);
	        this.setSize(this.getPreferredSize());
	        setVisible(true);
	  }
	  
	  public void setIcon(int a){
		  if (a==1)
			  setIcon(img);
		  else if (a==2)
			  setIcon(img2);
		  else if (a==3)
			  setIcon(img3);
	  }
}
