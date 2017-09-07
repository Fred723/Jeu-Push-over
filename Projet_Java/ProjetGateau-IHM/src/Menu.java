import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class Menu extends JMenuBar {

	//tout crÃ©er ici (menus, items..)
	public JMenu jeu;
    private JMenu aide;
    private JMenuItem quitter;
    private JMenuItem apropos;
    
	
	private JMenuItem changetouche;
	private Personnage perso;
	private MoveNutritionisteArrayList mnal;
    private ShowOnMap som;
    private UIKey u;
    JOptionPane jop = new JOptionPane();

//TODO: complÃ©ter le menu du haut
	public Menu(GateauArrayList g, NutritionisteArrayList n, Personnage p, MoveNutritionisteArrayList mnalp, final ShowOnMap somp, UIKey up, Map mp, Move movp){
		this.jeu = new JMenu("Jeu");
        this.aide = new JMenu("Aide");
        this.quitter = new JMenuItem("Quitter");
        this.apropos = new JMenuItem("A propos");
        this.changetouche = new JMenuItem("Changer Touche");
        this.jop = new JOptionPane();   
		perso=p;
		mnal=mnalp;
        som=somp;
        u=up;
        //TODO: crÃ©er menu et items et leur ajouter un action listener
           this.jeu.add(changetouche);
           this.jeu.add(quitter);
           this.aide.add(apropos);
           this.add(jeu);
           this.add(aide);

           this.quitter.addActionListener((ActionListener)new QuitActionListener());
           this.apropos.addActionListener((ActionListener)new AproposActionListener());
            // pour redÃ©finir les touches de jeu. Rien Ã  faire ici
           changetouche.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
                mnal.stopwork = true;
                perso.stopwork = true;
                som.stopwork = true;

               String inputKeyW= jop.showInputDialog(null, "Entrer la touche que vous souhaitez modifier : " +
                        "1:HAUT,2:BAS,3:GAUCHE,4:DROITE,5:Katon,6:Pause"
                        );
                int inputKey = Integer.parseInt(inputKeyW);
                String newKeyS = jop.showInputDialog(null, "Entrer la nouvelle touche pour " + inputKeyW);
                int newKey = KeyEvent.getExtendedKeyCodeForChar(newKeyS.charAt(0));

                System.out.println(inputKey);

                if(!(inputKey>0  && inputKey<7) || u.alreadyUsed(newKey)){
                    jop.showMessageDialog(null, "touche déjà  utilisé ou erreur");
                }
                u.changeSetting(inputKey,newKey);
                mnal.stopwork = false;
                perso.stopwork = false;
                som.stopwork = false;
            }
        });
	}
	class QuitActionListener implements ActionListener {
		  public void actionPerformed(ActionEvent e) {
			  // TODO
			  System.exit(0);
		  }
	}
	class AproposActionListener implements ActionListener {
		  public void actionPerformed(ActionEvent e) {	
			//TODO: pop up qui informe de qui a fait le jeu, etc...
			  JOptionPane jop1 = new JOptionPane();
		      jop1.showMessageDialog(null, "Par Frédéric Nadaradjane, jeu développé dans le cadre d'un projet", "A propos.. ", 1);
		  }
	}
}