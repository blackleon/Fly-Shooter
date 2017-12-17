package flyshooter;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author Blackleon
 */
public class Cannon extends Sprite
{
    private boolean mouse = false;
    private int angle = 0;
    private int timer;
    private int passed;
    private ArrayList<CBall> cball;
    private Sound fire;
    private Sound rotate;
    
    public Cannon(double x, double y)
    {
        super(x, y);
        fire = new Sound("sounds\\fire.wav");
        rotate = new Sound("sounds\\rotate.wav");
        timer = (int)(System.nanoTime()*1e-6);
        initCannon();
    }
    
    private void initCannon()
    {
        cball = new ArrayList<>();
        loadImage("images\\cannon.png");
        getImageDimensions();
    }
    
    public void setMouse(boolean mouse)
    {
        this.mouse = mouse;
    }
    
    public void setPassed(int passed)
    {
        this.passed = passed;
    }
    
    public int getPassed()
    {
        return passed;
    }
    
    public int getTimer()
    {
        return timer;
    }
    
    public boolean getMouse()
    {
        return mouse;
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
    
    public boolean fire()
    {
        if(vis&&passed>=1000)
        {
            cball.add(new CBall(x+15, y+10, angle));
            fire.play();
            timer = (int)(System.nanoTime()*1e-6);
            return true;
        }
        return false;
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
            angle=(angle-5)%360;
            rotate.play();
            
        }
        
        if(key == KeyEvent.VK_RIGHT)
        {
            angle=(angle+5)%360;
            rotate.play();
        }
    }
    
    public void rotateToMouse(MouseEvent e)
    {
       
       setAngle((int) Math.toDegrees(Math.atan2(e.getX()-x, e.getY()-y))-90);
       rotate.play();
    }

}

