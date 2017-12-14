package flyshooter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Iterator;
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
    private ArrayList<Fly> fly = new ArrayList<>();
    private Cannon cannon;
    private int maxFly=10;
    private int flyNo;
    private boolean ingame;
    private final int DELAY = 15;
    private final int B_WIDTH = 500;
    private final int B_HEIGHT = 500;
    Random r = new Random();
    AffineTransform identity = new AffineTransform();
    
    public Board()
    {
        initBoard();
    }
    
    private void initBoard()
    {
        addKeyListener(new TAdapter());
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
            /*if(fly.isEmpty()==false)
            {
                fly.get(0).setVisible(false);
                fly.remove(0);
                System.gc();
            }*/
            fly.add( new Fly(r.nextInt(B_WIDTH), r.nextInt(B_HEIGHT)));
            fly.get(0).setVisible(true);
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
            drawCannon(g);
        }else{
            drawGameOver(g);
        }
    }
    private void drawCannon(Graphics g)
    {
        if(cannon.isVisible())
        {
            Graphics2D g2d = (Graphics2D)g;
            AffineTransform at = new AffineTransform();
            at.setTransform(identity);
            at.rotate(Math.toRadians(cannon.getAngle()), cannon.getX()+30, cannon.getY()+21);
            at.translate(cannon.getX(), cannon.getY());
            g2d.drawImage(cannon.getImage(), at, this);
        }
    }
    private void drawObjects(Graphics g)
    {
        
        ArrayList<CBall> cball = cannon.getCBall();
        
        for(CBall cb : cball)
        {
            if(cb.isVisible())
            {
                g.drawImage(cb.getImage(),(int) cb.getX(), (int)cb.getY(), this);
            }
        }
        
        if(fly.get(0)!=null && fly.get(0).isVisible())
        {
            g.drawImage(fly.get(0).getImage(), (int)fly.get(0).getX(), (int)fly.get(0).getY(), this);
        }
    }
    
    private void drawGameOver(Graphics g)
    {
        //Game Over Screen Goes here!
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        inGame();
        
        updateCannon();
        updateCBall();
        updateFly();
        
        checkCollisions();
        
        repaint();
    }
    
    private void inGame()
    {
        if(!ingame)
        {
            timer.stop();
        }
    }
    
    private void updateCannon()
    {
        if(cannon.isVisible())
        {
            cannon.move();
        }
    }
    
    private void updateCBall()
    {
        ArrayList<CBall> cball = cannon.getCBall();
        if(cball.isEmpty()==false)
        {
            for (Iterator<CBall> it = cball.iterator(); it.hasNext();)
            {
                CBall cb = it.next();
                if(cb.isVisible())
                {
                    cb.move();
                }else
                {
                    it.remove();
                    initFly();
                }
            }
        }
        
    }
    
    private void updateFly()
    {
        if(fly!= null && fly.get(0).isVisible())
        {
            fly.get(0).move();
        }
    }
    
    public void checkCollisions()
    {
        Rectangle r1 = new Rectangle();
        if(fly!=null)
        {
            r1 = fly.get(0).getBounds();
        }
        
        
        ArrayList<CBall> cball = cannon.getCBall();
        
        for(CBall cb : cball)
        {
            Rectangle r2 = cb.getBounds();
            if(r1.intersects(r2))
            {
                //Play Fly buzz sound for death
                fly.get(0).setVisible(false);
                //initFly();
                cb.setVisible(false);
            }
        }
    }
    
    private class TAdapter extends KeyAdapter{
        /*@Override
        public void keyReleased(KeyEvent e)
        {
            cannon.keyReleased(e);
        }*/
        
        @Override
        public void keyPressed(KeyEvent e)
        {
            cannon.keyPressed(e);
        }
    }
    
}
