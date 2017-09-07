public class ShowOnMap extends Thread {
	GateauArrayList gA;
	Map mp;
    public boolean stopwork = false;
	
    // affichage des gateaux régulièrement grâce à un thread
    
	public ShowOnMap(GateauArrayList ge, Map me){
		super();
		gA = ge;
		mp = me;
	}

	public void run(){
		int i = 100;
        while (true) {
            try {
                for(i = 0; i < 100; i++){
                	//System.out.println("stopwork ShowOnMap: "+!stopwork);
	                if(!stopwork){
		                mp.add(gA.get(i));
		                mp.validate();
		                mp.repaint();
		            }
	                Thread.sleep(10000);
                }
            } catch (InterruptedException v) {
                System.out.println(v);
            }
        }
	}
}
