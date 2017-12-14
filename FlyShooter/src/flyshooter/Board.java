/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flyshooter;

import java.awt.Color;
import java.awt.Dimension;
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
    private Cannon cannon;
    private ArrayList<Fly> flies;
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
        
        Cannon = new Cannon();
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
