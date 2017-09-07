import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.util.ArrayList;

/**
 déplacements et actions du personnage
**/
public class Move {

    ShowOnMap som;
    boolean isPaused = false;
    public boolean intersects(JLabel testa, Rectangle rectB){

        Area areaA = new Area(testa.getBounds());
        Area areaB = new Area(rectB.getBounds());

        return areaA.intersects(areaB.getBounds2D());

    }
    public boolean intersects(Rectangle rectA, Rectangle rectB){

        Area areaA = new Area(rectA.getBounds());
        Area areaB = new Area(rectB.getBounds());

        return areaA.intersects(areaB.getBounds2D());

    }
    public void setShowOnMap(ShowOnMap somobject ){
        som = somobject;
    }

    public void MovePerso(int i, Personnage p, NutritionisteArrayList nuts, Map map, GateauArrayList cakes, MoveNutritionisteArrayList mnal){


       int posX = p.getPosX();
       int posY = p.getPosY();
       Oonde oonde = new Oonde();
       MoveOnde movo = new MoveOnde(oonde, map);
       Rectangle ro=new Rectangle(0,0,0,0);
       boolean katon=false;
       WaitRegime wr = new WaitRegime(p,map,mnal,som);

        switch(i){
            case 1:
                posY-=5;
                break;
            case 2:
                posY+=5;
                break;
            case 3:
                posX-=5;
                break;
            case 4:
                posX+=5;
                break;
            case 5:
            	JPanel BM = (JPanel)map.getParent().getComponent(0);
        		JLabel en = (JLabel)BM.getComponent(0);
        		JLabel mana = (JLabel)BM.getComponent(1);
	
        		if (p.getMana() < 12) {
                    mana.setText("mana : " + p.getMana() + " pas assez de mana");
                    Audio au2 = new Audio();
                    au2.setFic("mana");
                    au2.start();
                }
        		else {
            		System.out.println("KATON : HOUSENKA NO JUTSU !!!");
                    Audio au = new Audio();
                    au.setFic("katon");
                    au.start();

	        		en.setText("energie : "+p.getEnergy());
	        		p.setMana(p.getMana() - 12);
	        		mana.setText("mana : "+p.getMana());
	        		
	        	    oonde.setPosX(p.getX()-28);
	        	    oonde.setPosY(p.getY()-23);
	        	    System.out.println("onde : "+oonde.getPosX()+", "+oonde.getPosY());
	                ro = new Rectangle(oonde.getPosX(), oonde.getPosY(),oonde.ICON_WIDTH, oonde.ICON_HEIGHT);
	                katon=true;
	        	    movo.start();
        		}
        	    break;
            case 6:
            	System.out.println("touche P enfoncée");
            	BM = (JPanel)map.getParent().getComponent(0);
        		en = (JLabel)BM.getComponent(0);
        		mana = (JLabel)BM.getComponent(1);
        		en.setText("PAUSE");
        		mana.setText("");
                isPaused = !(isPaused);
            	//System.out.println("isPaused Move: "+isPaused);
                mnal.stopwork =  isPaused;
                p.stopwork = isPaused;
                som.stopwork =  isPaused;
            	break;
        }

        Rectangle r = new Rectangle(posX, posY,p.ICON_WIDTH, p.ICON_HEIGHT);
        if (nuts!=null){
	        for(Nutritioniste n : nuts){
	            if(intersects(n,r)){
	        		System.out.println("touché");
	        		
	        		//mode régime
		        	if(!p.getRegime()){
		        		wr.start();
		        	}
	        		
	        		JPanel BM = (JPanel)map.getParent().getComponent(0);
	        		JLabel en = (JLabel)BM.getComponent(0);
	        		en.setText("energie : "+p.getEnergy());
	
	        		if (p.getEnergy()==1)
	            		en.setText("energie : "+p.getEnergy()+"   /!\\ DEAD MAN WALKIN /!\\");
	            	return;
	            }
	            if (katon && intersects(n,ro)){
	            	nutritionisteTouche ntouch = new nutritionisteTouche(n, map, p);
	            	ntouch.start();
	            }
	    	}
        }
        
        if (cakes!=null){
	        for(Gateau g : cakes){
	        	if(g.isShowing() && intersects(g,r) && !p.getRegime()){
	        		map.remove(g);
	        		map.repaint();
	        		System.out.println("gateau");
	
	                JPanel BM = (JPanel)map.getParent().getComponent(0);
	
	                JLabel enz = (JLabel)BM.getComponent(3);
	                p.setNiveau(p.getNiveau() + 1);
	                enz.setText("niveau : "+p.getNiveau());
	                map.indexTile++;
	                map.repaint();
	                if(nuts!=null){
		                // pour augmenter le niveau de jeu

		                if(mnal.sleeptime > 200){
		                    mnal.sleeptime-=100;
		                }
		                else {
		                    mnal.NewNutritioniste();
		                }
	                }
	
	        		// si on a mangé un gateau de vie
	        		if (g.getGateau()==1){
	            		JLabel en = (JLabel)BM.getComponent(0);
		        		p.setEnergy(p.getEnergy() + 2);
		        		System.out.println("energie : "+p.getEnergy());
		        		en.setText("energie : "+p.getEnergy());
	        		}
	        		// si on a mangé un gateau de mana
	        		else if (g.getGateau()==2){
	            		JLabel mana = (JLabel)BM.getComponent(1);
		        		p.setMana(p.getMana() + 5);
		        		System.out.println("mana : "+p.getMana());
		        		mana.setText("mana : "+p.getMana());
	        		}
	        	}
	        }
        }
        		

        p.bouger(i,posX,posY,map);

    }

}
