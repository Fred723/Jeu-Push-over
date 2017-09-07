package jeu_2048_de_farida;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

import com.farida.android_2048.modele.MoteurJeu;

public class FenJeu2048DeFarida extends JFrame 
{
	private static final int HAUT = MoteurJeu.HAUT; //Permet d'appeler Haut au lieu de MoteurJeu.Haut
    private static final int BAS = MoteurJeu.BAS; //Permet d'appeler Bas au lieu 
    private static final int GAUCHE = MoteurJeu.GAUCHE;
    private static final int DROITE = MoteurJeu.DROITE;
    
    private static final int LARGEUR_FENETRE_DEFAUT = 800;//Taille de la fenetre
    private static final int HAUTEUR_FENETRE_DEFAUT = 600;
	
    /*Declararion des variables pour le 2048*/
	//private JButton bRaz;
	//private JButton bRecommencer;
	private JButton[][] bGrille;
	private JTextArea jeuEnCours;
	private JTextField tScore ;
	private JTextField tNbDeplacements;
	private JTextField tMeilleurScore ;
	private MoteurJeu jeu;
	private boolean iHMconfigure;
	
	private GrilleTuilesGraphique img;
	
    
    public FenJeu2048DeFarida (String titre, int X, int Y, int w, int h)
	{
		super(titre);
		this.initialiseMenu();
		jeu = MoteurJeu.getInstance();//On met dans le jeu qui de type MoteurJeu(L.53) instance.
	    jeu.setup();
		this.centreEcran(X, Y, w, h);
		this.initialise();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		
		this.setFocusable(true);
	}
    
    private void centreEcran(int X, int Y, int w, int h)
	{
		X = X/2 - w/2;
		Y = Y/2 - h/2;
		this.setBounds(X, Y, w, h);
	}
 
    private void initialise()
	{		
    	this.addKeyListener(new AdapteurTouches()); 
    	
		/* Récupere la référence du contentPane */
		Container c = this.getContentPane();
		
		JPanel pMeilleurScore =  this.creePanneauSud(); // Panneau Sud.
		c.add(pMeilleurScore, BorderLayout.SOUTH);	
		
		JPanel pScoreDeplacements =  this.creePanneauNord(); // Panneau Nord.
		c.add(pScoreDeplacements, BorderLayout.NORTH);	
		
		JPanel pDeplacementsEtCommandes =  this.creePanneauOuest(); // Panneau Ouest.
		c.add(pDeplacementsEtCommandes, BorderLayout.WEST);	
		
		JTabbedPane pGrille = this.creePanneauCentre(); // Panneau Centre.
		c.add(pGrille, BorderLayout.CENTER);
		
		
	}
	
    private void initialiseMenu()
    {
    	JMenuBar menu = new JMenuBar();//Nouveau menu(en haut)
    	this.setJMenuBar(menu);
    	
    	/* Menu Partie */
    	JMenu partieMenu = new JMenu("Partie"); //titre du menu déroulant
    	menu.add(partieMenu);
    	
    	JMenuItem nouvellePartie = new JMenuItem("Nouvelle partie"); //premiere action 
    	partieMenu.add(nouvellePartie);
    	nouvellePartie.addActionListener(new EcouteurRAZRecommencer());//On commence recommence le jeu donc on appelle EcouteurRAZRecommencer()
    	
    	nouvellePartie.setMnemonic('N');
    	nouvellePartie.setAccelerator(KeyStroke.getKeyStroke("N")); // Raccourci "N".
    	
    	JMenuItem quitter = new JMenuItem("Quitter");//On fait pareille pour quitter
    	partieMenu.add(quitter);
    	quitter.addActionListener(new EcouteurMenu());
    	
    	quitter.setMnemonic('Q'); 
    	quitter.setAccelerator(KeyStroke.getKeyStroke("Q")); // Raccourci "Q".
    	
    	/* Menu Options */
    	JMenu optionsMenu = new JMenu("Options");//Nouvelle option
    	menu.add(optionsMenu);
    	
    	JRadioButtonMenuItem tuilesRondes = new JRadioButtonMenuItem("Tuiles rondes");//Permet de mettre le jeu en tuille ronde
    	optionsMenu.add(tuilesRondes);
    	tuilesRondes.addActionListener(new EcouteurMenu());
    	
    	
    	tuilesRondes.setMnemonic('R');  
    	tuilesRondes.setAccelerator(KeyStroke.getKeyStroke("R")); // Raccourci "R".
    	
    	JRadioButtonMenuItem tuilesCarrees = new JRadioButtonMenuItem("Tuiles carrees");//Permet de mettre le jeu en tuille rondes
    	optionsMenu.add(tuilesCarrees);
    	tuilesCarrees.setSelected(true);
    	tuilesCarrees.addActionListener(new EcouteurMenu());
    	
    	tuilesCarrees.setMnemonic('C'); // Raccourci "C".
    	tuilesCarrees.setAccelerator(KeyStroke.getKeyStroke("C"));
    	
    	ButtonGroup forme = new ButtonGroup();//Regroupement des deux boutons
    	forme.add(tuilesCarrees);//Ajout des bouttons au regrouppement
    	forme.add(tuilesRondes);
    }
    
	private JPanel creePanneauSud()
	{
		/* Création panneau avec un FlowLayout */
		JPanel pMeilleurScore = new JPanel();
		pMeilleurScore.setBorder(BorderFactory.createTitledBorder("Meilleur Score"));
		
		/* Label meilleur score. */
		JLabel lMeilleurScore = new JLabel("Meilleur score"); 
		pMeilleurScore.add(lMeilleurScore); // Ou JLabel lMeilleurScore = new JLabel("Meilleur score");
		
		/* Zone de texte pour le meilleur score. */
		this.tMeilleurScore = new JTextField(9); 
		this.tMeilleurScore .setEditable(false);
		pMeilleurScore.add(tMeilleurScore);
		
		return pMeilleurScore;
	}
	
	private JPanel creePanneauNord()
	{
		/* Création panneau avec un FlowLayout. */
		JPanel pScoreDeplacements = new JPanel();
		pScoreDeplacements.setBorder(BorderFactory.createTitledBorder("Score et déplacements"));
		
		/* Sous-panneau contenu dans pScoreDeplacement avec un GridLayout. */
		JPanel sousPScoreDeplacement = new JPanel(new GridLayout(2, 2, 3, 3));
		pScoreDeplacements.add(sousPScoreDeplacement);	
		
		/* Label score. */
		JLabel lScore = new JLabel("Score courant"); 
		sousPScoreDeplacement.add(lScore);
		
		/* Label nombre de déplacements. */
		JLabel lNbDeplacement = new JLabel("Nombre de déplacements"); 
		sousPScoreDeplacement.add(lNbDeplacement);
		
		/* Zone de texte pour le score. */
		this.tScore = new JTextField(10); 
		this.tScore.setVisible(false);
		this.tScore.setEditable(false);
		sousPScoreDeplacement.add(tScore);
		
		/* Zone de texte pour le nombre de déplacements. */
		this.tNbDeplacements = new JTextField(10); 
		this.tNbDeplacements.setVisible(false);
		this.tNbDeplacements.setEditable(false);
		sousPScoreDeplacement.add(this.tNbDeplacements);
		
		return pScoreDeplacements;
	}
	
	private JPanel creePanneauOuest()
	{
		/* Création panneau avec un BorderLayout. */
		JPanel pDeplacementsEtCommandes = new JPanel(new BorderLayout());
		
		/* Panneau déplacements. */
		JPanel pDeplacements = new JPanel();
		pDeplacementsEtCommandes.add(pDeplacements, BorderLayout.CENTER);
		
		pDeplacements.setLayout(new GridLayout(3,3, 0, 0)); // JPanel pDeplacements = new JPanel(new GridLayout()); possible
		pDeplacements.setBorder(BorderFactory.createTitledBorder("Déplacements"));
		
		for (int i = 0; i < 9; i++) // Instanciation des différents boutons.
		{
			switch(i)
			{
				case 1:
					JButton bHaut = new JButton("Haut"); // Bouton Haut.
					pDeplacements.add(bHaut);
					bHaut.addActionListener(new EcouteurMouvement(FenJeu2048DeFarida.HAUT));
					break;
				case 3:
					JButton bGauche = new JButton("Gauche"); // Bouton Gauche.
					pDeplacements.add(bGauche);
					bGauche.addActionListener(new EcouteurMouvement(FenJeu2048DeFarida.GAUCHE));
					break;
				case 5:
					JButton bDroite = new JButton("Droite"); // Bouton Droite.
					pDeplacements.add(bDroite);
					bDroite.addActionListener(new EcouteurMouvement(FenJeu2048DeFarida.DROITE));
					break;
				case 7:
					JButton bBas = new JButton("Bas"); // Bouton Bas.
					pDeplacements.add(bBas);
					bBas.addActionListener(new EcouteurMouvement(FenJeu2048DeFarida.BAS));
					break;
				default: // Création de boutons par défaut qui ne seront pas activés car inutiles.
					JButton bDefault = new JButton();
					pDeplacements.add(bDefault);
					bDefault.setEnabled(false);
					break;
			}		
		}
		
		/* Panneau commandes. */
		/*JPanel pCommandes = new JPanel();
		pCommandes.setBorder(BorderFactory.createTitledBorder("Commandes partie"));
		
		this.bRecommencer = new JButton("Recommencer");
		pCommandes.add(bRecommencer);
		this.bRecommencer.setEnabled(false);
		this.bRecommencer.addActionListener(new EcouteurRAZRecommencer());
		
		this.bRaz = new JButton("RÃ Z");
		pCommandes.add(bRaz);
		this.bRaz.setEnabled(false);
		this.bRaz.addActionListener(new EcouteurRAZRecommencer());
		
		pDeplacementsEtCommandes.add(pCommandes, BorderLayout.SOUTH);*/
	
		return pDeplacementsEtCommandes;	
	}
	
	public JTabbedPane creePanneauCentre()
	{
        JTabbedPane pGrille = new JTabbedPane();//Creation des onglets
        JPanel panTxt = new JPanel(new BorderLayout());
        
        this.jeuEnCours  = new JTextArea();
        this.jeuEnCours.setText(jeu.getGrilleTuiles().toString());
        this.jeuEnCours.setFont(new Font("Serif",Font.BOLD, 64));
        this.jeuEnCours.setEditable(false);
        
        panTxt.add(jeuEnCours);
        pGrille.add("Affichage textuel",panTxt);

        this.img = new GrilleTuilesGraphique(jeu.getGrilleTuiles());
        
        this.bGrille = new JButton[this.jeu.getTaille()][this.jeu.getTaille()];
        
        for (int i = 0; i < this.jeu.getTaille(); i++)
        {
            for (int j = 0; j < this.jeu.getTaille(); j++)
            {
                if (jeu.getGrilleTuiles().getUneTuile(i, j) == null)
                {
                    this.bGrille[i][j]= new JButton();
                    this.bGrille[i][j].setBackground(Color.white);
                }
                else 
                {
                    this.bGrille[i][j]= new JButton("" + jeu.getGrilleTuiles().getUneTuile(i, j).getValue());
                }    
                
                this.bGrille[i][j].setEnabled(false);
                this.bGrille[i][j].setFont((new Font("Serif", Font.BOLD, 32)));
            }
        }
        
        pGrille.add("Affichage graphique", img);
        
   

        String nomsCols[] = {"Nombre de deplacements", "Score courant", "Score maximal"};//Nom donnée au collones du tableau.
        String rows[][] = {{"0", "0", "0"}};//On initialise a 0
        
        DefaultTableModel model = new DefaultTableModel(rows, nomsCols);
        
        JTable historique = new JTable(model);
        
        pGrille.add("Historique", historique);


        return pGrille;
    }
	
	private void configurationDepart() 
	{
		this.setFocusable(true);
        
		this.tScore.setVisible(false);
        this.tNbDeplacements.setVisible(false);
        //this.bRaz.setEnabled(false);
        //this.bRecommencer.setEnabled(false);
        this.iHMconfigure = false;
    }

    private void configurationJeuApresPremierCou() 
    {
        this.tScore.setVisible(true);
        this.tNbDeplacements.setVisible(true);
        //this.bRaz.setEnabled(true);
        //this.bRecommencer.setEnabled(true);
        this.iHMconfigure = true;
    }

    private void miseAJourVues() 
    {
        this.jeuEnCours.setText(this.jeu.getGrilleTuiles().toString());
        this.tNbDeplacements.setText("" + this.jeu.getNombreDeplacements());
        this.tScore.setText("" + this.jeu.getScore());
        this.tMeilleurScore.setText("" + this.jeu.getMeilleurScore());
        
        if (this.jeu.partieTerminee()) 
        {
            if (this.jeu.partieGagnee())
            	JOptionPane.showMessageDialog(this, "Gagné !", "Partie terminée", JOptionPane.INFORMATION_MESSAGE);
            else 
            	JOptionPane.showMessageDialog(this, "Perdu !", "Partie terminée", JOptionPane.INFORMATION_MESSAGE );
            	
            this.jeu.saveScore();
            this.jeu.recommencerPartie();
            this.configurationDepart();
        }
    }

    class EcouteurMouvement implements ActionListener 
    {
        private int direction;

        public EcouteurMouvement(int i) 
        {
            this.direction = i;
        }

        public void actionPerformed(ActionEvent ev) 
        {
            FenJeu2048DeFarida.this.jeu.deplacer(this.direction);
            FenJeu2048DeFarida.this.miseAJourVues();
            
            if (!FenJeu2048DeFarida.this.iHMconfigure) 
                FenJeu2048DeFarida.this.configurationJeuApresPremierCou();
            
            repaint();
        }
    }
    
    class EcouteurMenu implements ActionListener
    {
    	public void actionPerformed(ActionEvent e) 
        {
    		String s = e.getActionCommand();
    		
    		if (s.equals("Quitter"))
    			dispose();
    		
    		if (s.equals("Tuiles rondes"))
    		{
    			img.setFormeTuile(GrilleTuilesGraphique.FORME_RONDE);
    			repaint();
    		}
    				
    		if (s.equals("Tuiles carrees"))
    		{
    			img.setFormeTuile(GrilleTuilesGraphique.FORME_CARREE);
    			repaint();
    		}	
        }
    }

    class EcouteurRAZRecommencer implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            String s = e.getActionCommand();
            
            if (s.equals("Recommencer")) //Ancienne version (Quand il y avait le boutton RAZ)
                FenJeu2048DeFarida.this.jeu.recommencerPartie();
            else 
                FenJeu2048DeFarida.this.jeu.resetJeu();
            
            FenJeu2048DeFarida.this.configurationDepart();
            FenJeu2048DeFarida.this.miseAJourVues();
        }
    }
    
    class AdapteurTouches extends KeyAdapter //Pour jouer au 2048 grâce au clavier
    {
    	public void keyPressed(KeyEvent e)
    	{
    		int keyCode = e.getKeyCode();
    	    
    		switch(keyCode) 
    		{ 
            	case KeyEvent.VK_UP://Si on appuie sur haut
            		FenJeu2048DeFarida.this.jeu.deplacer(FenJeu2048DeFarida.HAUT);
            		break;
            	
            	case KeyEvent.VK_DOWN://Si on appuye sur bas
            		FenJeu2048DeFarida.this.jeu.deplacer(FenJeu2048DeFarida.BAS); 
            		break;
            	
            	case KeyEvent.VK_LEFT://SI on appuit sur Gauche
            		FenJeu2048DeFarida.this.jeu.deplacer(FenJeu2048DeFarida.GAUCHE);
            		break;
            
            	case KeyEvent.VK_RIGHT : //Si on appuie sur Droite
            		FenJeu2048DeFarida.this.jeu.deplacer(FenJeu2048DeFarida.DROITE);
            		break;
    		}
    		
    		FenJeu2048DeFarida.this.miseAJourVues();
    		
    		if (!FenJeu2048DeFarida.this.iHMconfigure) 
                FenJeu2048DeFarida.this.configurationJeuApresPremierCou();
    		
    		repaint();
    	}
    }
    public static void main(String[] args)
    {	
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int X = dim.width;
		int Y = dim.height;
		
		/* Affichage console */
		String classPath = System.getProperty("java.class.path");
		String javaVersion = System.getProperty("java.version");
		System.out.println("Le CLASSPATH est " + classPath);
		System.out.println("La version de Java est " + javaVersion + "\n");
		
		/* Création de la fenÃªtre */
        new FenJeu2048DeFarida("2048 de Farida", X, Y, LARGEUR_FENETRE_DEFAUT, HAUTEUR_FENETRE_DEFAUT);    
    }   
}
