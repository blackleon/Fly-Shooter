package flyshooter;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Blackleon
 */

public class Board extends JPanel implements ActionListener
{
    private boolean ingame;
    
    private final int DELAY = 15;
    private final int B_WIDTH = 500;
    private final int B_HEIGHT = 500;
    private final int maxFly=10;
    
   
    private int flyNo;
    private int score;
    private int ballCount;
    
    private double scoreTimer;
    private double scoreTimeLast;
    private double scoreTime;
    
    
    private Timer timer;
    
    private ArrayList<Fly> fly;
    private ArrayList<CBall> cball;
    
    private Cannon cannon;
    
    private Random r;
    
    private Sound buzz;
    
    private JButton reset;
    
    private Area floor;
    private Area temp;
    
    private Rectangle rc;
    
    AffineTransform identity = new AffineTransform();
    
    public Board()
    {
        initBoard();
    }
    
    private void initBoard()
    {
        this.setLayout(null);
        
        fly = new ArrayList<>();
        cball = new ArrayList<>();
        r = new Random();
        reset = new JButton("Reset");
        this.add(reset);
        reset.setBounds(200, 400, 100, 30);
        
        
        reset.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                reset();
            }
        });
        
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                boolean succeed;
                cannon.rotateToMouse(e);
                succeed = cannon.fire();
                if(succeed)
                {
                    ballCount++;
                }
            }
        });
                
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.lightGray);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        
        buzz = new Sound("sounds\\buzz.wav");
        cannon = new Cannon(B_WIDTH/2-16 ,B_HEIGHT-80);
        
        timer = new Timer(DELAY, this);
        
        initFly();
        reset();
    }
    
    private void reset()
    {
        setBackground(new Color(135,200,250));
        temp = new Area(new Rectangle(0, B_HEIGHT*18/20, B_WIDTH, B_HEIGHT));
        floor = new Area(temp);
        temp = new Area(new Rectangle(0, 0, B_WIDTH*2/20, B_HEIGHT));
        floor.add(temp);
        temp = new Area(new Rectangle(B_WIDTH*18/20, 0, B_WIDTH, B_HEIGHT));
        floor.add(temp);
        
        ballCount=1;
        flyNo = 0;
        score = 0;
        ingame = true;
        scoreTimer=System.nanoTime()*1e-6;
        scoreTime=0;
        scoreTimeLast=0;
        timer.start();
        
        reset.setVisible(false);
        cannon.setVisible(true);
        cannon.setAngle(90);
        initFly();
    }
    
    public void initFly()
    {
        fly.add(new Fly(r.nextInt(B_WIDTH-50)+25, r.nextInt(B_HEIGHT-200)+50));
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        if(ingame)
        {
            drawObjects(g);
            drawCannon(g);
            drawTimeScore(g);
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
            
            g2d.setColor(new Color(200,255,250));
            g2d.fill(floor);
            
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

        reset.setVisible(true);
        
        setBackground(Color.black);
        g.setColor(Color.white);
        g.setFont(new Font("", Font.PLAIN, 30));
        
        String sTime;
        scoreTime = (System.nanoTime()*1e-6)-scoreTimer;
        sTime = String.format("Time: %10.2f     Score: %10d", (scoreTime)*1e-3, score);
        g.drawString("GAME OVER", 150, B_HEIGHT/2-30);
        g.drawString(sTime, 20, B_HEIGHT/2+30);
    }
    
    private void drawTimeScore(Graphics g)
    {
        String sTime;
        int start = 20;
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setColor(Color.DARK_GRAY);
        scoreTime = (System.nanoTime()*1e-6)-scoreTimer;
        sTime = String.format("Time: %10.2f      Score: %10d      Flies Shot: %2d/%2d", (scoreTime)*1e-3, score, flyNo, maxFly);
        g2d.setStroke(new BasicStroke(10));
        g2d.drawLine(start, 480, start + cannon.getPassed()/5, 480);
        g2d.drawLine(B_WIDTH-start, 480, start + B_WIDTH-2*start-(cannon.getPassed()/5), 480);
        g.setFont(new Font("", Font.PLAIN, 15));
        g2d.drawString(sTime, 75, 20); 
        
    }
    
    private void DrawFloor()
    {
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        inGame();

        updateCBall();
        updateFly();
        updateCannon();
        
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
        cannon.setPassed((int) (System.nanoTime()*1e-6)-cannon.getTimer()) ;
        if(cannon.getPassed()>1000)
        {
            cannon.setPassed(1000);
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
                
                score +=(int)(1e+7/(scoreTime-scoreTimeLast)/ballCount);
                scoreTimeLast=(System.nanoTime()*1e-6)-scoreTimer;
                
                ballCount=1;
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
            
            if(floor.intersects(r2))
            {
                Ellipse2D.Double e = new Ellipse2D.Double(cb.getX()-8, cb.getY()-8, 16, 16 );
                Area sub = new Area(e);
                floor.subtract(sub);
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