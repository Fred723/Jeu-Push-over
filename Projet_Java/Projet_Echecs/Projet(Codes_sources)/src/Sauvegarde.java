import java.util.*;
import java.io.*;
public class Sauvegarde extends java.util.ArrayList<Piece>{

	private static final long serialVersionUID = 1L;

	private String s;
	
	

	public void charger(String nomFichier, Plateau p)
	{
		try{
			
			
			while(this.size()!=0){
				this.remove(0);
			}
			FileReader fr = new FileReader(nomFichier);
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			while(line!=null)
			{
				for(int l = 0; l < p.getPlat().length; l++)
					for(int c = 0; c< p.getPlat().length; c++)
					{
						if(line.equals("TN"))
							p.setPiece(l, c, new Tour(Piece.NOIR));
						else if (line.equals("CN"))
							p.setPiece(l, c, new Cavalier(Piece.NOIR));
						else if (line.equals("FN"))
							p.setPiece(l, c, new Fou(Piece.NOIR));
						else if (line.equals("DN"))	
							p.setPiece(l, c, new Dame(Piece.NOIR));
						else if (line.equals("RN"))
							p.setPiece(l, c, new Roi(Piece.NOIR));
						else if (line.equals("PN"))
							p.setPiece(l, c, new Pion(Piece.NOIR));
						
						else if (line.equals("PB"))
							p.setPiece(l, c, new Pion(Piece.BLANC));
						else if(line.equals("TB"))
							p.setPiece(l, c, new Tour(Piece.BLANC));
						else if (line.equals("CB"))
							p.setPiece(l, c, new Cavalier(Piece.BLANC));
						else if (line.equals("FB"))
							p.setPiece(l, c, new Fou(Piece.BLANC));
						else if (line.equals("DB"))	
							p.setPiece(l, c, new Dame(Piece.BLANC));
						else if (line.equals("RB"))
							p.setPiece(l, c, new Roi(Piece.BLANC));
					
						else
							p.setPieceVide(l, c);
						
						line = br.readLine();
					}

				
				
				
				if (line.equals("BLANC"))//tour du joueur
					p.setTour(Piece.BLANC);
				else if (line.equals("NOIR"))
					p.setTour(Piece.NOIR);
				line = br.readLine();
			}
			
			br.close();
			fr.close();
		
			
		}catch(IOException e){
			System.out.println(e);
		}
	
		
	}
	
	public void sauvegarder(String monFichier, Plateau p)
	{
		
		try{
			FileWriter fw = new FileWriter(monFichier);
			BufferedWriter bw = new BufferedWriter(fw);
			
			for(int l = 0; l < p.getPlat().length; l++)
				for(int c = 0; c< p.getPlat().length; c++)
						bw.write(p.getPlat()[l][c]+"\n");
			
			if(p.getTour()==Piece.BLANC)
				bw.write("BLANC");
			else
				bw.write("NOIR");
			bw.close();
			fw.close();
		}catch(IOException e){
			System.out.println(e);
		}
		
	}
	
	
}
