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
    ArrayList<CBall> cball = new ArrayList<>();
    private Cannon cannon;
    private int maxFly=10;
    private int flyNo=0;
    private boolean ingame;
    private final int DELAY = 15;
    private final int B_WIDTH = 500;
    private final int B_HEIGHT = 500;
    Random r = new Random();
    private Sound buzz;
    
    
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
        
        buzz = new Sound("sounds\\buzz.wav");
        
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        
        cannon = new Cannon(B_WIDTH/2-21 ,B_HEIGHT-50);
        
        initFly();

        timer = new Timer(DELAY, this);
        timer.start();
    }
    
    public void initFly()
    {
        fly.add(new Fly(r.nextInt(B_WIDTH-100)+50, r.nextInt(B_HEIGHT-100)+50));
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
            at.rotate(Math.toRadians(cannon.getAngle()), cannon.getX()+15, cannon.getY()+12);
            at.translate(cannon.getX(), cannon.getY());
            g2d.drawImage(cannon.getImage(), at, this);
        }
    }
    private void drawObjects(Graphics g)
    {
        
        cball = cannon.getCBall();
        
        for(CBall cb : cball)
        {
            if(cb.isVisible())
            {
                g.drawImage(cb.getImage(),
                            (int) cb.getX()-5, 
                            (int)cb.getY()-5, 
                            this);
            }
        }
        
        if(fly.get(flyNo).isVisible())
        {
            g.drawImage(fly.get(flyNo).getImage(),
                        (int) fly.get(flyNo).getX(), 
                        (int)fly.get(flyNo).getY(), 
                        this);
        }
    }
    
    private void drawGameOver(Graphics g)
    {
        cannon.setVisible(false);
        fly.removeAll(fly);
        cball.removeAll(cball);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        inGame();
        
        updateCBall();
        updateFly();
        
        checkCollisions();
        
        repaint();
        //System.out.println(System.nanoTime()/10^9);
    }
    
    private void inGame()
    {
        if(!ingame)
        {
            timer.stop();
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
                }
            }
        }
    }
    
    private void updateFly()
    {
        if(fly!= null && fly.get(flyNo).isVisible())
        {
            fly.get(flyNo).move();
        }
    }
    
    public void checkCollisions()
    {
        Rectangle r1 = new Rectangle();
        if(fly!=null)
        {
            r1 = fly.get(flyNo).getBounds();
        }
        
        
        ArrayList<CBall> cball = cannon.getCBall();
        
        for(CBall cb : cball)
        {
            Rectangle r2 = cb.getBounds();
            if(r1.intersects(r2))
            {
                buzz.play();
                fly.get(flyNo).setVisible(false);
                flyNo++;
                if(flyNo < maxFly)
                {
                   initFly(); 
                }else
                {
                    ingame = false;
                    inGame();
                }
                
                cb.setVisible(false);
            }
        }
    }
    
    private class TAdapter extends KeyAdapter{
        
        @Override
        public void keyPressed(KeyEvent e)
        {
            cannon.keyPressed(e);
        }
    }
    
}
