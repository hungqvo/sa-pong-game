/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ponggame.Objects;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;

/**
 *
 * @author David
 */
public class GamePopupMenu extends JFrame{      //IMPLEMENTS SINGLETON PATTERN
    
    GameWindow source;
    int count = 3;
    int stage = 0;
    boolean firstTime = true;
    private static GamePopupMenu instance = new GamePopupMenu();
    private GamePopupMenu(){
        super("GAME IS PAUSED");
    }
    
    public static GamePopupMenu getPopUp(){
        return instance;
    }
    
    public void setSource(GameWindow src){
        source = src;
        instantiate();
    }
    
    
            
    private void instantiate(){
        getContentPane().setBackground(Color.RED);
        if (!firstTime){
            count = 3;
            stage = 0;
            
            instance.setVisible(true);
            repaint();
        }
        else{
            firstTime = false;
            setLayout(new BorderLayout());
            setSize(500,300);
            setLocationRelativeTo(null);
            setUndecorated(true);
            setVisible(true);        
            setResizable(false);
        
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
            addKeyListener(new KeyListener(){

                @Override
                public void keyTyped(KeyEvent e) {
                        
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if ((e.getKeyCode() == KeyEvent.VK_S)){ 
                        source.soundObject.muteSound();
                        repaint();
                    }   // Toggle sound
                        
                        else if ((e.getKeyCode() == KeyEvent.VK_M)){ 
                        source.soundObject.muteMusic();
                        repaint();
                        }   // Toggle music
                        
                        else if ((e.getKeyCode() == KeyEvent.VK_ENTER)){                            
                            stage = 1;
                            source.unPause();
                            getContentPane().setBackground(Color.BLACK);
                            Timer timer = new java.util.Timer();
                            timer.scheduleAtFixedRate(new TimerTask()    // run the function at a fixed rate
                            {
                                public void run()
                                {
                                     if (count == 0){
                                         timer.cancel();
                                         //dispose();
                                         instance.setVisible(false);
                                         
                                     }
                                    else{                                         
                                         repaint();                                         
                                     }
                                }
                            },0,1000);     // 5 milliseconds
                            
                            
                        }   // Continue
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    
                }
 });             
    }
    }

        @Override
        public void paint(Graphics g) {
            //try {
            super.paint(g);
            if (stage == 0){            
                g.setColor(Color.WHITE);
                g.setFont(new Font(source.myFont.getFontName(),1,50));
                g.drawString("PAUSED", this.getWidth()/2 - 150, this.getHeight()/2 - 50);
                g.setFont(new Font(source.myFont.getFontName(),1,15));
                g.drawString("SOUND:", this.getWidth()/2 - 120, this.getHeight()/2 - 20);
                
                if(source.soundObject.soundPlay){
                    g.drawString("ON", this.getWidth()/2, this.getHeight()/2 - 20);
                }
                
                else
                    g.drawString("OFF", this.getWidth()/2, this.getHeight()/2 - 20);

                g.drawString("MUSIC:", this.getWidth()/2 - 120, this.getHeight()/2);
                
                if(source.soundObject.musicPlay){
                    g.drawString("ON", this.getWidth()/2, this.getHeight()/2);
                }
                else
                    g.drawString("OFF", this.getWidth()/2, this.getHeight()/2);

                g.drawString("Press ENTER to continue", this.getWidth()/2 - 170, this.getHeight()/2 + 30);
            }
            
            else {
                g.setColor(Color.WHITE);            
                g.setFont(new Font(source.myFont.getFontName(),1,80));
                g.drawString(Integer.toString(count), this.getWidth()/2 -30 , this.getHeight()/2 - 30);
                count--;
            }
            }
        }


