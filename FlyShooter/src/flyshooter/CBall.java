package flyshooter;

/**
 *
 * @author Blackleon
 */
public class CBall extends Sprite
{
    
    public CBall(int x, int y)
    {
        super(x, y);
        
        initCBall();
    }
    
    
    private void initCBall()
    {
        loadImage("\\images\\cball.png");
        getImageDimensions();
    }
    
    public void move()
    {
        //W I P 
    }
}
