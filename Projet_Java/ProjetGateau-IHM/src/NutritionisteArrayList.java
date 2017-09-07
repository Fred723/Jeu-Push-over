import java.util.ArrayList;


public class NutritionisteArrayList extends ArrayList<Nutritioniste> implements java.io.Serializable {

    private Personnage p;



    public void setPerso(Personnage np)
    {
        p = np;
    }

    public NutritionisteArrayList(int nb, Map m){
        super();

        for(int i = 0; i < nb; i++){
            add(new Nutritioniste(m));
        }


    }
    public NutritionisteArrayList(){
        super();
    }

    public void addToMap(Map m){

        for(Nutritioniste n : this)
            m.add(n);

    }

    public void run(){

    }


}
