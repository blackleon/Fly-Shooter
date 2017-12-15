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
    
    
    private ArrayList<CBall> cball;
    public Cannon(double x, double y)
    {
        super(x, y);
        fire = new Sound("sounds\\fire.wav");
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
    
    public void fire()
    {
        if(vis)
        {
            cball.add(new CBall(x+15, y+10, angle));
            fire.play();
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

