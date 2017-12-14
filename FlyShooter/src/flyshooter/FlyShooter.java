/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flyshooter;

import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 *
 * @author Blackleon
 */

public class FlyShooter extends JFrame
{

    public FlyShooter()
    {
        initUI();
    }
    
    private void initUI()    
    {
        setResizable(false);
        pack();
        
        setTitle("Fly Shooter");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    
    
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable(){
        @Override
        public void run() {
            FlyShooter fly = new FlyShooter();
            fly.setVisible(true);
        }
        });
    }
    
}
