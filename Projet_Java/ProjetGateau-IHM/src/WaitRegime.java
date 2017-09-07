import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JOptionPane;

public class WaitRegime extends Thread {
	Personnage perso;
	Map m;
    MoveNutritionisteArrayList mnal;
    ShowOnMap som;

    Audio au = new Audio();

	
	public WaitRegime(Personnage p, Map map, MoveNutritionisteArrayList mnalp, ShowOnMap somp){
		super();
		perso=p;
		m=map;
        mnal = mnalp;
        som = somp;
        au.setFic("mort");

	}
	
	public void run(){
    	JPanel BM = (JPanel)m.getParent().getComponent(0);
		JLabel energy = (JLabel)BM.getComponent(0);
		//on enlève de l'énergie car le perso a été touché
		perso.setEnergy(perso.getEnergy() - 1);
		energy.setText("energie : "+perso.getEnergy());

		System.out.println("energie : "+perso.getEnergy());
		if(perso.getEnergy()<=0){

            mnal.stopwork = true;
            som.stopwork = true;
            au.start();
			String[] choix = {"oui", "non"};
		    int reponse = JOptionPane.showOptionDialog(null, "Recommencer ?", "/!\\ GAME OVA /!\\ ", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, choix, choix[0]);
		    if (reponse==1)
		    	System.exit(0);
		    else if (reponse==0){
                perso.setRegime(true);
		    	perso.setEnergy(10);
                mnal.ecarterTous();
                mnal.stopwork = false;
                som.stopwork = false;
                perso.setRegime(false);
		    	energy.setText("energie : "+perso.getEnergy());
		    }

		}
		
		
		//on passe en mode régime : on ne peut plus manger
		perso.setRegime(true);
		System.out.println("Mode r�gime ! Vous en pouvez plus manger");
		JLabel texte = (JLabel)BM.getComponent(2);
		for (int i=7; i>0; i--){
	        try {
	    		texte.setText("REGIME MODE : "+i+"sec");
	            Thread.sleep(1000);
	          } catch (InterruptedException e) {
	            e.printStackTrace();
	          }
		}
        perso.setRegime(false);
		System.out.println("Il faut manger des gateaux !!!");
		texte.setText("");

	}
	
}
