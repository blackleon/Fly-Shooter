package flyshooter;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author Blackleon
 */

public class Sprite
{
    protected double x;
    protected double y;
    protected int width;
    protected int height;
    protected boolean vis;
    protected Image image;

    public Sprite(double x, double y)
    {
        this.x = x;
        this.y = y;
        vis = true;
    }
    
    protected void getImageDimensions()
    {
        width = image.getWidth(null);
        height = image.getHeight(null);
        
    }
    
    protected void loadImage(String imageName)
    {
        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();
    }
    
    public Image getImage()
    {
        return image;
    }
    
    public double getX()
    {
        return x;
    }
    
    public double getY()
    {
        return y;
    }
    
    public boolean isVisible()
    {
        return vis;
    }
    
    public void setVisible(Boolean visible)
    {
        vis = visible;
    }
    
    public Rectangle getBounds() 
    {
        return new Rectangle((int)x, (int)y, width, height);
    }
    
    
}
