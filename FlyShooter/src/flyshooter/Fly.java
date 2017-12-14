package flyshooter;

/**
 *
 * @author Blackleon
 */
public class Fly extends Sprite
{
    public Fly(double x, double y)
    {
        super(x,y);
        
        initFly();
    }
    
    private void initFly()
    {
        loadImage("images\\fly.png");
        getImageDimensions();
    }
    
    public void move()
    {
        //Fly algorithm goes here
    }
}
