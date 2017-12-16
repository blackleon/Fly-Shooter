package flyshooter;

import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


/**
 *
 * @author Blackleon
 */
public class Cannon extends Sprite
{
    private int angle = 0;
    private Sound fire;
    private int timer;
    
    private ArrayList<CBall> cball;
    public Cannon(double x, double y)
    {
        super(x, y);
        fire = new Sound("sounds\\fire.wav");
        timer = (int)(System.nanoTime()*1e-6);
        initCannon();
    }
    
    private void initCannon()
    {
        cball = new ArrayList<>();
        loadImage("images\\cannon.png");
        getImageDimensions();
    }
    
    public ArrayList getCBall()
    {
        return cball;
    }
    
    public int getAngle()
    {
        return angle;
    }
    
    public void setAngle(int angle)
    {
        this.angle = -angle;
    }
    
    public void fire()
    {
        if(vis&&(System.nanoTime()*1e-6)-timer>500)
        {
            cball.add(new CBall(x+15, y+10, angle));
            fire.play();
            timer = (int)(System.nanoTime()*1e-6);
        }
    }
    
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
        
        if(key == KeyEvent.VK_SPACE)
        {
            fire();
        }
        
        if(key == KeyEvent.VK_LEFT)
        {
            angle=(angle-3)%360;
            
        }
        
        if(key == KeyEvent.VK_RIGHT)
        {
            angle=(angle+3)%360;
        }
    }
}

