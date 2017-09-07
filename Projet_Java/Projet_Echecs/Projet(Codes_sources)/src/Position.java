public class Position {
    private int ligne;
    private int col;
    public Position(int l,int  c){
		ligne = l;
		col= c;
    }
    public boolean equals (Position p1){
    		if(this.ligne==p1.ligne && this.col==p1.col)
    			return true;
    		
    		return false;
    }
    public int getL(){
    	return ligne;
    }    
    public int getC(){
    	return col;
    }
    public void setL(int ligne)
    {
    	this.ligne = ligne;
    }
    public void setC(int col)
    {
    	this.col = col;
    }
}