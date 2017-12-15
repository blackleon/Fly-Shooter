/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flyshooter;

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author Blackleon
 */
public class Sound
{
    double timer;
    File sound;
    boolean playing = false;
    
    public Sound(String s)
    {
        sound = new File(s);
        timer = System.nanoTime()/10^9;
    }
    public void play() 
    {
        try
        {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(sound));
            clip.start();
            
            
            
        }
        catch (Exception e)
        {
            System.out.println("'-'");
        }
    }
}
