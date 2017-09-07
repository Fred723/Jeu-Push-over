import java.awt.*;

import javax.swing.JLabel;
import javax.swing.JPanel;


// TODO: barre des infos du personnage en bas de l'Ã©cran
public class infosJeu extends JPanel {
	private JLabel energie = new JLabel("L'énergie");
	private JLabel mana = new JLabel("Le mana");
	private JLabel reg = new JLabel("");
	private JLabel niveau = new JLabel("niveau");
     
	public infosJeu() {
		setLayout(new GridLayout(1, 4));//4 collones
	  
	  
		mana.setForeground(Color.blue);
		mana.setText("mana : 100");
		energie.setText("energie : 10");
		energie.setForeground(Color.blue);
	    niveau.setText("niveau : 0");
		niveau.setForeground(Color.blue);
		  
		reg.setText("");//Information pour le mode regime (Regime mode : nb secondes)
		reg.setForeground(Color.red);
		    
		add(energie);
		add(mana);
		add(reg);
		add(niveau);
	}
	
	public void setNiveau(int n)
	{
		niveau.setText("Niveau: "+n);
	}
	public void setMana(int m)
	{
		mana.setText("Mana : "+m);
	}
	public void setEnergie(int e)
	{
		energie.setText("Energie : "+e);
	}
	
	
		
}
