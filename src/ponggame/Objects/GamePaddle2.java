/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ponggame.Objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Random;

import static ponggame.Objects.GameWindow.keyLog;

/**
 *
 * @author David
 */
public class GamePaddle2 implements GameObject{
    int y , width = 120,height=20;
    int x=200;
    int incre = 0;
    int player;
    GameWindow window;
     Random rand;
    
    public GamePaddle2(){};
     
     @Override
    public void setInitData(GameWindow window, int player, int junk, int junk2, int junk3, int junk4) {
        this.player = player;
        this.window = window;
        if (player == 1)
        {
            y = 6;
        }
        
        else 
        {
           y = (window.frame.getHeight()- 60);
           
        }
    }
   
    @Override
    public int getData(String name) {
        switch(name){
            case "x":
                return x;
            case "y":
                return y;
            case "width":
                return width;
            case "height":
                return height;
            case "incre":
                return incre;
        }
        return 100;
    }
    
    @Override
    public void move(){
        
        if ((x == 0)||(x == (window.getWidth()-width))){
            if(x==0){
                if (incre == 1)
                      x += incre;  
            }
            else{
                if (incre == -1)
                      x += incre;  
            //}
        }}
    
        else{
            x += incre;
            //if (player==2) System.out.println("CON CAC"+x + " " + incre);
        }
  
    }
    
    public void keyReleased(KeyEvent e) {
        if (player == 1){
            if (e.getKeyCode() == KeyEvent.VK_J ){
                keyLog.remove("UPP1");
                if (keyLog.contains("DOWNN1")==false)
                    incre = 0;
                else incre = 1;
                    
            }
            
            else if (e.getKeyCode() == KeyEvent.VK_L){   
                keyLog.remove("DOWNN1");
                    if (keyLog.contains("UPP1")==false)
                    incre = 0;
                else incre = -1;
                    
            }   
	}
        
        else if (player == 2){
            if (e.getKeyCode() == KeyEvent.VK_NUMPAD4){      
                keyLog.remove("UP2");      
                    if (keyLog.contains("DOWN2")==false)
                    incre = 0;
                else incre = 1;
                    
            }
            else if (e.getKeyCode() == KeyEvent.VK_NUMPAD6){   
                keyLog.remove("DOWN2");
                if (keyLog.contains("UP2")==false){
                    incre = 0;
                    
                }
                else incre = -1;
                    
            }
        }
	
    }

    public void keyPressed(KeyEvent e) {
        if (player == 1){
            if (e.getKeyCode() == KeyEvent.VK_J){            
                    incre = -1;
                    keyLog.add("UP1");
            }
            else if (e.getKeyCode() == KeyEvent.VK_L){            
                    incre = 1;
                    keyLog.add("DOWN1");
            }   
	}
        
        else if (player == 2){
            if (e.getKeyCode() == KeyEvent.VK_NUMPAD4){            
                    incre = -1;
                    keyLog.add("UP2");
            }
            else if (e.getKeyCode() == KeyEvent.VK_NUMPAD6){            
                    incre = 1;
                    keyLog.add("DOWN2");
            }
        }
    }
        
    
    public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
    
    @Override
    public void paint(Graphics2D g){
        g.fillRect(x,y,width,height);
    }

    

   

    
}
