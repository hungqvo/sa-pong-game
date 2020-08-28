/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ponggame.Objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

/**
 *
 * @author David
 */
public class GameObstacle implements GameObject{
    int y = 200, width = 20,height=120;
    int x;
    int incre = 0;
    int player;
    GameWindow window;
    Random rand;
    
    public GameObstacle(){
        
    }
    
    @Override
    public void setInitData(GameWindow window, int data1, int data2, int data3, int data4, int data5) {
        this.window = window;
        random();
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
    public void move() {
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
