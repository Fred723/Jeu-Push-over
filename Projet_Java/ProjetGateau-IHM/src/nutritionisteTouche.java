import java.awt.Point;


public class nutritionisteTouche extends Thread {
	Nutritioniste nut;
	int s1, s2, x, y;
	Map map; 
	Personnage p;
	Point A, B; 
	
	public nutritionisteTouche(Nutritioniste n, Map m, Personnage perso) {
		super();
		nut = n;
		map=m;
		p=perso;
	}
	
	public void run(){
		for(int j=1; j< 32; j++){
			j*=3;
			A = nut.getLocation();
			B = p.getLocation(); // la cible
	        
	        s1 = (B.x > A.x ? 1 : -1);
	        s2 = (B.y > A.y ? 1 : -1);
	        if(B.y-A.y==0)
	        	return;
	        
	        x =  A.x;
	        y = A.y;
	    	System.out.println("position "+ j + " !!!");

	        try {
		       	//x += (int) s1*(Math.abs((B.x-A.x)/(B.y-A.y)));
		       	//y += (int) s2*(Math.abs((B.x-A.x)/(B.y-A.y)));
		       	B.x+=11; // décalage pour que visuellement le perso soit en face du nut lors de l'attaque
		       	B.y+=12;
	        	System.out.println("x:"+(B.x-A.x)+" || y:"+(B.y-A.y));
		       	// p à droite de nut et p en dessous de nut
		       	if(B.x-A.x > 0 && B.y-A.y > 0){
		       		x=x-j;
		       		y=y-j;
		       	}
		       	// p à droite de nut et p au dessus de nut
		       	else if(B.x-A.x > 0 && B.y-A.y <= 0){
	        		x=x-j;
	        		if (B.y-A.y != 0)
	        		y=y+j;
		        }
		       	// p à gauche de nut et p en dessous de nut
		       	else if(B.x-A.x <= 0 && B.y-A.y <= 0){
	        		if (B.x-A.x != 0)
		       		x=x+j;
	        		if (B.y-A.y != 0)
		       		y=y+j;
		       	}
		       	// p à gauche de nut et p au dessus de nut
		       	else if(B.x-A.x <= 0 && B.y-A.y > 0){
	        		if (B.x-A.x != 0)
		       		x=x+j;
		       		y=y-j;
		       	}	        	
	        	map.remove(nut);
	        	map.validate();
		       	nut.setLocation(x,y);
	        	map.add(nut);
	        	map.validate();
	        	map.repaint();
		       	
		       	Thread.sleep(100);
	       	} catch(InterruptedException v) {
	            System.out.println(v);
	        }
		}
		System.out.println("ennemi mis KO !!");
		nut.setIcon(2);
		for(int i=0; i<40; i++){
        	map.remove(nut);
        	map.validate();
	       	nut.setLocation(x,y);
        	map.add(nut);
        	map.validate();
        	map.repaint();
			try {
	            Thread.sleep(100);
	          } catch (InterruptedException e) {
	            e.printStackTrace();
	          }
		}
		nut.setIcon(1);
		map.validate();
    	map.repaint();
		System.out.println("ennemi se r�veille !!");
	}
	
}
