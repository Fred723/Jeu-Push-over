import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Main {


    public static void main(String[] args){



        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                JFrame frame = new JFrame("Jeu");
                Dimension ecran = Toolkit.getDefaultToolkit().getScreenSize();

                frame.setPreferredSize(new Dimension(ecran.width/2,ecran.height/2));
                frame.setLocation(ecran.width/4,ecran.height/4);
                
                // ! \\ à décommenter dans l'ordre !
                
                // instancie le clavier
                UIKey u = new UIKey(); 

                // TODO: définition du personnage
                Personnage p = new Personnage();
                u.setPerso(p);

                // TODO: définition de la map
                Map map = new Map(frame.getSize());
                u.setMap(map);
                // ajout du personnage à la map
                map.add(p);
                
                // ajout de la barre d'infos
                infosJeu bm = new infosJeu();
                
                // démarre le thread de mana (juste à décommenter)
                IncreaseMana im = new IncreaseMana(p, map);
                im.start();

                // instancie les mouvements du personnage
                Move move = new Move(); 
                u.setMove(move);
                
                //place les gateaux
                GateauArrayList cakes = new GateauArrayList(5000,map);
                u.setGateauArrayList(cakes);

                //démarre le thread gateaux
                ShowOnMap sgal = new ShowOnMap(cakes,map);
                move.setShowOnMap(sgal);
                sgal.start();
                
                //place les nutritionnistes
                NutritionisteArrayList nuts = new NutritionisteArrayList(10,map);
                nuts.addToMap(map);
                nuts.run();
                u.setNutritionisteArrayList(nuts);                
                                
                
                // démarre le thread des nutritionnistes
                MoveNutritionisteArrayList mnal = new MoveNutritionisteArrayList(nuts,p,map,sgal);
                mnal.start();
                u.setMNAL(mnal);
             

                // TODO: la barre de menu en haut
                Menu menubar = new Menu(cakes, nuts, p, mnal, sgal, u, map, move);
                frame.setJMenuBar(menubar);
                
                //modification de l'ordre car sinon le regime mode beug
                frame.setLayout(new BorderLayout());
                frame.add(bm, BorderLayout.SOUTH);
                frame.add(map, BorderLayout.CENTER);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
                frame.addKeyListener(u);





            }
        });

    }
}
