package flyshooter;

/**
 *
 * @author Blackleon
 */

public class CBall extends Sprite
{
    
    
    
    private int angle;
    private int speed = 12;
    private double xspeed;
    private double yspeed;
    
    public CBall(double x, double y, int angle)
    {
        super(x, y);
        this.angle = angle;
        initCBall();
        
        yspeed=Math.sin(Math.toRadians(angle))*speed;
        xspeed=Math.cos(Math.toRadians(angle))*speed;
    }
    
    private void initCBall()
    {
        loadImage("images\\cball.png");
        getImageDimensions();
    }
    
    public void move()
    {
       x+=xspeed;
       y+=yspeed;
       yspeed = yspeed + 9.82 * 0.015;
    }
}
