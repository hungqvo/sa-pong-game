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
public class GamePaddle implements GameObject{
    int y = 200, width = 20,height=120;
    int x;
    int incre = 0;
    int player;
    GameWindow window;
     Random rand;
    
    public GamePaddle(){};
     
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
    
    public void setInitData(GameWindow win, int player,int PaddleY, int junk, int junk2, int junk3){
            this.player = player;
        window = win;
        if (player == 1)
        {
            x = 6;
        }
        
        else 
        {
            x = (window.frame.getWidth()- 30);
        }
        
        y=PaddleY;
     }
    
     public GamePaddle(GameWindow win)
    {
        this.player = player;
        window = win;
        if (player == 1)
        {
            x = 6;
        }
        
        else 
        {
            x = (window.frame.getWidth()- 30);
        }
        
    }
    
    
    
    public void moveAI(int ballY){
        if (y>ballY) incre=-1;
        else if (y+height<ballY) incre=1;
    }
    
    
    
    @Override
    public void move(){
        if ((y == 0)||(y == (window.getHeight()-height))){
            if(y==0){
                if (incre == 1)
                      y += incre;  
            }
            else{
                if (incre == -1)
                      y += incre;  
            //}
        }}
    
        else{
            y += incre;
        }
  
    }
    
    public void keyReleased(KeyEvent e) {
        if (player == 1){
            if (e.getKeyCode() == KeyEvent.VK_W ){
                keyLog.remove("UP11");
                if (keyLog.contains("DOWN11")==false)
                    incre = 0;
                else incre = 1;
                    
            }
            
            else if (e.getKeyCode() == KeyEvent.VK_S){   
                keyLog.remove("DOWN11");
                    if (keyLog.contains("UP11")==false)
                    incre = 0;
                else incre = -1;
                    
            }   
	}
        
        else if (player == 2){
            if (e.getKeyCode() == KeyEvent.VK_UP){      
                keyLog.remove("UP22");      
                    if (keyLog.contains("DOWN22")==false)
                    incre = 0;
                else incre = 1;
                    
            }
            else if (e.getKeyCode() == KeyEvent.VK_DOWN){            
                keyLog.remove("DOWN22");
                if (keyLog.contains("UP22")==false)
                    incre = 0;
                else incre = -1;
                    
            }
        }
	
    }

    public void keyPressed(KeyEvent e) {
        if (player == 1){
            if (e.getKeyCode() == KeyEvent.VK_W){            
                    incre = -1;
                    keyLog.add("UP11");
            }
            else if (e.getKeyCode() == KeyEvent.VK_S){            
                    incre = 1;
                    keyLog.add("DOWN11");
            }   
	}
        
        else if (player == 2){
            if (e.getKeyCode() == KeyEvent.VK_UP){            
                    incre = -1;
                    keyLog.add("UP22");
            }
            else if (e.getKeyCode() == KeyEvent.VK_DOWN){            
                    incre = 1;
                    keyLog.add("DOWN22");
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

    void random() {
        rand = new Random();
       x=rand.nextInt((window.frame.getWidth()*2/3 - window.frame.getWidth()/3) + 1) + window.frame.getWidth()/3;
       rand = new Random();
       
               y=rand.nextInt(300);
    }
     void random(int BallmoveX,int BallY) {
        rand = new Random();
       x=rand.nextInt((window.frame.getWidth()*2/3 - window.frame.getWidth()/3) + 1) + window.frame.getWidth()/3;
        
       rand = new Random();
       if (BallmoveX==1)
               y=rand.nextInt(300)+BallY;
       else    y=BallY-rand.nextInt(300);
    }

    

    
}
