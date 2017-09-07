import java.awt.Component;
import javax.swing.JLabel;

public class MoveOnde extends Thread
{
    Oonde onde;
    Map map;
    JLabel KATON;
    
    public MoveOnde(final Oonde o, final Map m) {
        this.KATON = new JLabel("KATON : HOUSENKA NO JUTSU !!!");
        this.onde = o;
        this.map = m;
        this.KATON.setSize(300, 50);
    }
    
    @Override
    public void run() {
        this.KATON.setLocation(this.onde.getPosX() - 60, this.onde.getPosY() + 90);
        this.map.add((Component)this.KATON);
        this.map.add((Component)this.onde);
        this.map.validate();
        this.map.repaint();
        try {
            Thread.sleep(200);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.onde.setIcon(2);
        this.map.repaint();
        try {
            Thread.sleep(200L);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.onde.setIcon(3);
        this.map.repaint();
        try {
            Thread.sleep(200L);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.onde.setIcon(1);
        this.map.repaint();
        try {
            Thread.sleep(200L);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.map.remove((Component)this.onde);
        this.map.remove((Component)this.KATON);
        this.map.validate();
        this.map.repaint();
    }
}