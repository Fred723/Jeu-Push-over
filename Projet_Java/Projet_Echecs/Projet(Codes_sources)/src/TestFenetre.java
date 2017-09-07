import java.applet.AudioClip;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URL;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Mixer;

public class TestFenetre {

	public static void main(String[] args){
		Toolkit tk = Toolkit.getDefaultToolkit();
	    Dimension dim = tk.getScreenSize();
	    Audio au = new Audio();
	    
	    
	    Fenetre fen = new Fenetre(dim.width,dim.height, au);
	   
	}
	
}
