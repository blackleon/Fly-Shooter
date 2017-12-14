package flyshooter;

import java.util.ArrayList;

/**
 *
 * @author Blackleon
 */
public class Cannon extends Sprite
{
    private int angle;
    private ArrayList<CBall> cball;
    public Cannon(int x, int y)
    {
        super(x, y);
        
        initCannon();
    }
    
    private void initCannon()
    {
        cball = new ArrayList<>();
        loadImage("\\images\\cannon.png");
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
    
    public void fire()
    {
        cball.add(new CBall(width/2, height/2));
    }
}

