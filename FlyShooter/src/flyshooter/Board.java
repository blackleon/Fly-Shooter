package flyshooter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Blackleon
 */
public class Board extends JPanel implements ActionListener
{
    private Timer timer;
    private Fly fly;
    private Cannon cannon;
    //private ArrayList<CBall> cball;
    private int maxFly;
    private int flyNo;
    private boolean ingame;
    private final int DELAY = 15;
    private final int B_WIDTH = 500;
    private final int B_HEIGHT = 500;
    Random r;
    
    public Board()
    {
        initBoard();
    }
    
    private void initBoard()
    {
        //addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.lightGray);
        ingame = true;
        
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        
        cannon = new Cannon(B_WIDTH/2 ,B_HEIGHT/2);
        
        flyNo=0;
        initFly();

        timer = new Timer(DELAY, this);
        timer.start();
    }
    
    public void initFly()
    {
        while(flyNo<maxFly)
        {
            fly = new Fly(r.nextInt(B_WIDTH), r.nextInt(B_HEIGHT));
            flyNo++;
        }
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        if(ingame)
        {
            drawObjects(g);
        }else{
            drawGameOver(g);
        }
    }
    
    private void drawObjects(Graphics g)
    {
        if(cannon.isVisible())
        {
            g.drawImage(cannon.getImage(), cannon.getX(), cannon.getY(), this);
        }
        
        ArrayList<CBall> cball = cannon.getCBall();
        
        for(CBall cb : cball)
        {
            if(cb.isVisible())
            {
                g.drawImage(cb.getImage(), cb.getX(), cb.getY(), this);
            }
        }
        
        if(fly!=null && fly.isVisible())
        {
            g.drawImage(fly.getImage(), fly.getX(), fly.getY(), this);
        }
    }
    
    private void drawGameOver(Graphics g)
    {
        //Game Over Screen Goes here!
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        //
    }
    
}
