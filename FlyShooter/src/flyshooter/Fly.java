package flyshooter;

import java.awt.Point;
import java.util.Random;

/**
 *
 * @author Blackleon
 */
public class Fly extends Sprite
{
    private final int B_WIDTH = 500;
    private final int B_HEIGHT = 500;
    private double timer;
    private double distance;
    
    private Random r;
    private Point hedef;
    
    public Fly(double x, double y)
    {
        super(x,y);
        initFly();
        r = new Random();
        timer = System.nanoTime()*1e-6;
        hedef = new Point(0, 0);
    }

    private void initFly()
    {
        loadImage("images\\fly.png");
        getImageDimensions();
    }
    
    public void move()
    {
        if((System.nanoTime()*1e-6)-timer>1500)
        {
            while(distance<150)
            {
                hedef = new Point((int) (r.nextInt(B_WIDTH -50)+25), 
                                  (int) (r.nextInt(B_HEIGHT-200)+50));
            
                distance = Math.sqrt(Math.pow((int) ((hedef.x)-x), 2)+
                                     Math.pow((int) ((hedef.y)-y), 2)); 
            }
            

            timer = System.nanoTime()*1e-6;
            
            //System.out.println(hedef.x + " " + hedef.y + " " + distance);
        }
        distance =0;
        x += (hedef.x - x)/100;
        y += (hedef.y - y)/100;
    }
}
