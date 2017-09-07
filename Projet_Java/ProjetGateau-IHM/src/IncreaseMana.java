import javax.swing.JLabel;
import javax.swing.JPanel;


public class IncreaseMana extends Thread {

	Personnage p;
	Map m;
	
	public IncreaseMana(Personnage perso, Map map) {
		super();
		p=perso;
		m=map;
	}

	public void run(){
		while(true)
	        try {
	            Thread.sleep(1000);
	            p.setMana(p.getMana()+1);
            	JPanel BM = (JPanel)m.getParent().getComponent(0);
        		JLabel mana = (JLabel)BM.getComponent(1);
        		mana.setText("mana : "+p.getMana());
	            
	            
	          } catch (InterruptedException e) {
	            e.printStackTrace();
	          } 
		}
}
