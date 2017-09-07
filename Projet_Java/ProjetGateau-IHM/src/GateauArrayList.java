import java.util.ArrayList;

public class GateauArrayList extends ArrayList<Gateau> implements java.io.Serializable {
	
	   public GateauArrayList(int nb, Map m){
	        super();
	        // 2 chances sur 3 d'avoir un gateau de vie, 1 sur 3 un gateau de mana

	        for(int i = 0; i < nb; i++){
	        	Gateau g = new Gateau(m);
	        	if((int)(Math.random()*100)>66)
	        		g.setIcon(2);
	            add(g);
	        }


	    }


}
