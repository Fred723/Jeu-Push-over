import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class MoveNutritionisteArrayList extends Thread {

    NutritionisteArrayList nal;
    Personnage p;
    Move m = new Move();
    public boolean stopwork = false;
    Map mymap;
    ShowOnMap som;

    public int sleeptime = 1000;



    public MoveNutritionisteArrayList(NutritionisteArrayList nnal, Personnage np, Map mp, ShowOnMap somp){
        super();
        nal = nnal;
        p = np;
        mymap = mp;
        som = somp;
    }
    public void NewNutritioniste(){
        nal.add(new Nutritioniste(mymap));
    }

    public NutritionisteArrayList getNal(){
        return nal;
    }





    public Point DirectionToPerso(Nutritioniste nA, NutritionisteArrayList nal, int coef, int droit){
        Point A = nA.getLocation();
        Point B = p.getLocation(); // la cible

        int s1 = (B.x > A.x ? 1 : -1);
        int s2 = (B.y > A.y ? 1 : -1);

        int x =  A.x, y = A.y;

        if(B.y-A.y==0)
            return A; // éviter les divisions par 0 ..
        if(droit==1){
            x += (int) s1*coef*Math.sin(Math.atan(Math.abs((B.x-A.x)/(B.y-A.y))));
            y += (int) s2*coef*Math.cos(Math.atan(Math.abs((B.x-A.x)/(B.y-A.y))));
        }
        else{
            x -= (int) s1*coef*Math.sin(Math.atan(Math.abs((B.x-A.x)/(B.y-A.y))));
            y -= (int) s2*coef*Math.cos(Math.atan(Math.abs((B.x-A.x)/(B.y-A.y))));
        }


        Rectangle newPos = new Rectangle(x,y,nA.getWidth(),nA.getHeight());

        if(m.intersects(p,newPos)&&!p.getRegime()){

            nutritionisteTouche nt = new nutritionisteTouche(nA,mymap,p);
            nt.start();
            p.setEnergy(p.getEnergy()-1);
            WaitRegime wr = new WaitRegime(p,mymap,this,this.som);
            wr.start();

            return A; // collision perso


        }
        if(m.intersects(p,newPos)&&p.getRegime()){

            return A;
        }



        return new Point(x,y); // c'est bon

    }
    public void touchedNutritionist(Nutritioniste n, int i, Map m){
        Point A = n.getLocation();
        Point B = p.getLocation(); // la cible
        
        int s1 = (B.x > A.x ? 1 : -1);
        int s2 = (B.y > A.y ? 1 : -1);
        if(B.y-A.y==0)
        	return;
        
        int x =  A.x, y = A.y;
    	System.out.println("its "+ i + "         !!!       ");

        try {
	       	//x += (int) s1*(Math.abs((B.x-A.x)/(B.y-A.y)));
	       	//y += (int) s2*(Math.abs((B.x-A.x)/(B.y-A.y)));
	       	B.x+=11; // décalage pour que visuellement le perso soit en face du nut lors de l'attaque
	       	B.y+=12;
        	System.out.println("x:"+(B.x-A.x)+" || y:"+(B.y-A.y));
	       	// p à droite de nut et p en dessous de nut
	       	if(B.x-A.x > 0 && B.y-A.y > 0){
	       		x=x-i;
	       		y=y-i;
	       	}
	       	// p à droite de nut et p au dessus de nut
	       	else if(B.x-A.x > 0 && B.y-A.y <= 0){
        		x=x-i;
        		if (B.y-A.y != 0)
        		y=y+i;
	        }
	       	// p à gauche de nut et p en dessous de nut
	       	else if(B.x-A.x <= 0 && B.y-A.y <= 0){
        		if (B.x-A.x != 0)
	       		x=x+i;
        		if (B.y-A.y != 0)
	       		y=y+i;
	       	}
	       	// p à gauche de nut et p au dessus de nut
	       	else if(B.x-A.x <= 0 && B.y-A.y > 0){
        		if (B.x-A.x != 0)
	       		x=x+i;
	       		y=y-i;
	       	}	        	
        	m.remove(n);
        	m.validate();
	       	n.setLocation(x,y);
        	m.add(n);
        	m.validate();
        	m.repaint();
	       	
	       	Thread.sleep(100);
       	} catch(InterruptedException v) {
            System.out.println(v);
        }
    }
    public void ecarterTous(){
        int i=0;

        for (i = 0; i < nal.size(); i++) {
            //nal.get(i).setLocation(nal.get(i).getX()+10,nal.get(i).getY());
            nal.get(i).setLocation(DirectionToPerso(nal.get(i), nal, 200, 0));
        }




    }


    public void run() {
        int i;


            while (true) {
                try {
                	//System.out.println("stopwork MNAL: "+!stopwork);
                    if(!stopwork) {
                        for (i = 0; i < nal.size(); i++) {
                            //nal.get(i).setLocation(nal.get(i).getX()+10,nal.get(i).getY());
                            nal.get(i).setLocation(DirectionToPerso(nal.get(i), nal, 15,1));
                        }
                    }

                    Thread.sleep(sleeptime);

                } catch (InterruptedException v) {
                    System.out.println(v);
                }
            }
        }
}


