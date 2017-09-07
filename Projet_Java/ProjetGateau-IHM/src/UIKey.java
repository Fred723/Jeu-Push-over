import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by syskaw on 26/03/15.
 */
public class UIKey implements KeyListener {

    /**
     * Insister sur les trucs de merde
     */
    /* Name & index */

    private int HAUT=KeyEvent.VK_UP, // 1
                BAS=KeyEvent.VK_DOWN, // 2
                GAUCHE=KeyEvent.VK_LEFT, // 3
                DROITE=KeyEvent.VK_RIGHT, // 4
    			K=KeyEvent.VK_K, // 5
				P=KeyEvent.VK_P; // 6


    private Personnage p = null;
    private Move m = null;
    private NutritionisteArrayList n;
    private Map mp;
    private GateauArrayList g;
    private MoveNutritionisteArrayList mnal;


    public void UIKey() {


    }
    public void setPerso(Personnage np){
        p = np;
    }

    public void setMove(Move mp ){
        m = mp;
    }
    public void setNutritionisteArrayList(NutritionisteArrayList ne){
        n = ne;
    }
    public void setMap(Map me){
    	mp = me;
    }
    public void setMNAL(MoveNutritionisteArrayList mnalp){
        mnal = mnalp;
    }

    public void setGateauArrayList(GateauArrayList ge){
    	g = ge;
    }
    
    // TODO: modification des touches ?
    
    public void changeSetting(int wanted,int newkey){
        switch(wanted) {
            case 1:
                HAUT = newkey;
                break;
            case 2:
                BAS = newkey;
                break;
            case 3:
                GAUCHE = newkey;
                break;
            case 4:
                DROITE = newkey;
                break;
            case 5:
                K = newkey;
                break;
            case 6:
                P = newkey;
                break;
        }
    }

    public boolean alreadyUsed(int key){
        return (HAUT==key||BAS==key||GAUCHE==key||DROITE==key||K==key||P==key);
    }


    public void keyPressed (KeyEvent e){

        if(m==null){
            throw new  IllegalArgumentException("a Move object is not set into UIKey object ");
        }

        if(e.getKeyCode()==HAUT){
            m.MovePerso(1, p, n, mp, g,mnal);
        }else if(e.getKeyCode()==BAS){
            m.MovePerso(2, p, n, mp, g,mnal);
        }else if(e.getKeyCode()==DROITE){
            m.MovePerso(4, p, n, mp, g, mnal);
        }else if(e.getKeyCode()==GAUCHE){
            m.MovePerso(3, p, n, mp, g,mnal);
        }else if(e.getKeyCode()==K){
            m.MovePerso(5, p, n, mp, g, mnal);
        }
    	else if(e.getKeyCode()==P){
    		m.MovePerso(6, p, n, mp, g,mnal);
    	}
    }
    public void keyReleased (KeyEvent e){}
    public void keyTyped (KeyEvent e){}
}

