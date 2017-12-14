package flyshooter;

/**
 *
 * @author Blackleon
 */
public class CBall extends Sprite
{
    private int angle;
    private int speed = 10;
    public CBall(double x, double y, int angle)
    {
        super(x, y);
        this.angle = angle;
        initCBall();
    }
    
    
    private void initCBall()
    {
        loadImage("images\\cball.png");
        getImageDimensions();
    }
    
    public void move()
    {
        y+=Math.sin(Math.toRadians(angle))*speed;
        x+=Math.cos(Math.toRadians(angle))*speed;
    }
}
