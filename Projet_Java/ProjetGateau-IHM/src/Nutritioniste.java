import javax.swing.*;
import java.awt.*;

public class Nutritioniste extends JLabel implements java.io.Serializable {

    private int ICON_WIDTH = 32, ICON_HEIGHT = 32;
    ImageIcon img, img2;


    public Nutritioniste(Map m){
        super();
        img = new ImageIcon("./resources/nutritioniste.png");
        img2 = new ImageIcon("./resources/nutritionistezzz.png");
        setIcon(img);



        Dimension ecran = Toolkit.getDefaultToolkit().getScreenSize();


        setLocation(20+ (int)  ((ecran.width-100)/2*Math.random()) , 20 + (int) ((ecran.height-100)/ 2 * Math.random() ) );



        this.setSize(this.getPreferredSize());
        setVisible(true);
    }
    public void setIcon(int a){
    	if (a==1)
    		setIcon(img);
    	else if(a==2)
    		setIcon(img2);
    }

}
