package flyshooter;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 *
 * @author Blackleon
 */
public class Cannon extends Sprite
{
    private int angle = 0;
    
    private ArrayList<CBall> cball;
    public Cannon(double x, double y)
    {
        super(x, y);
        
        initCannon();
    }
    
    private void initCannon()
    {
        cball = new ArrayList<>();
        loadImage("images\\cannon.png");
        getImageDimensions();
    }
    
    public void move()
    {
        //W I P
    }

    public ArrayList getCBall()
    {
        return cball;
    }
    
    public int getAngle()
    {
        return angle;
    }
    
    public void fire()
    {
        cball.add(new CBall(x+20, y+10, angle));
    }
    
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
        
        if(key == KeyEvent.VK_SPACE)
        {
            fire();
            //PlaySound
        }
        
        if(key == KeyEvent.VK_LEFT)
        {
            angle=(angle-5)%360;
            
        }
        
        if(key == KeyEvent.VK_RIGHT)
        {
            angle=(angle+5)%360;
        }
    }
    
    /*public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();
        
        if(key == KeyEvent.VK_LEFT)
        {
            //release
        }
        
        if(key == KeyEvent.VK_RIGHT)
        {
            //release
        }
    }*/
}

