/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ponggame.Objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

/**
 *
 * @author David
 */
public class GameBall2 implements GameObject{
    int ballX = 500, ballY = 500, ballWidth = 30,ballHeight=30;
    int increX=1,increY=1;  //decrement value
       
    
    static int winner;
    
    GameWindow window;
    
    int gameMode;       // 1 player, 2 player or 4 player
    
    public GameBall2(){
        
    }
    
    @Override
    public void setInitData(GameWindow window, int mode, int junk1, int junk2, int junk3, int junk4) {
        this.window = window;
        gameMode = mode;
    }
    
    
    @Override
    public int getData(String name) {
        switch (name){
            case "x":
                return ballX;
            case "y":
                return ballX;     
            case "width":
                return ballWidth;
            case "height":
                return ballHeight;
            case "increX":
                return increX;
            case "increY":
                return increY;
        }
        return 100;
    }
       
    
    @Override
    public void move(){
        if(!edgeHit().equals("")){
            if (edgeHit().equals("R")){
                if (window.check1) {
                    window.check1=false;
                    reSpawn();
                }
                    
                }
                else if (edgeHit().equals("L")){
                if (window.check2) {
                    window.check2=false;
                    reSpawn();
                }
                    
                }
                else if (edgeHit().equals("B")){
                    
                    if (window.check3) {
                    window.check3=false;
                    reSpawn();
                    }
                }
                else if (edgeHit().equals("T")){
                    if (window.check4) {
                    window.check4=false;
                    reSpawn();
                    }
                }
            if (gameMode == 1){
               if (window.scoreTwo == 3){
                    window.playSound("Win");
                    window.gameStatus = 3;
                }
               else if (window.scoreOne == 3){
                   window.playSound("Lose");
                   window.gameStatus = 4;
               }
                    
            }
                
            
            else if (gameMode == 2){
                
                if (window.scoreOne == 3|| window.scoreTwo == 3){
                    window.playSound("Win");
                    winner = ((window.scoreOne == 3) ? 1 : 2);
                    window.gameStatus = 5;
                }    

            }
        }
        
        
        if (ballHit()){
            if ((getBorder().intersects(window.paddle.getData("x"),window.paddle.getData("y"),window.paddle.getData("width"),window.paddle.getData("height"))
           || (getBorder().intersects(window.paddle1.getData("x"),window.paddle1.getData("y"),window.paddle1.getData("width"),window.paddle1.getData("height")))
                || (getBorder().intersects(window.obstacle.getData("x"),window.obstacle.getData("y"),window.obstacle.getData("width"),window.obstacle.getData("height")))
               
                
                )){
            if (increX == 1)
                increX = -1;
            else if (increX == -1)
                increX = 1;
            
        }
             if ((getBorder().intersects(window.paddle2.getData("x"),window.paddle2.getData("y"),window.paddle2.getData("width"),window.paddle2.getData("height")))
           || (getBorder().intersects(window.paddle3.getData("x"),window.paddle3.getData("y"),window.paddle3.getData("width"),window.paddle3.getData("height")))
                
               
                
                ){
            if (increY == 1)
                increY = -1;
            else if (increY == -1)
                increY = 1;
            
        }
            

        }

                   
        if(ballY==0)
                   increY=1;
        if(ballY>=window.getHeight()-ballHeight)
                   increY=-1;
        if (ballX==window.getWidth()-ballWidth)
                increX=-1;
        if (ballX==0)
                increX=1;
               
                   ballX += increX;   //ball move
                   ballY += increY; 
                   
        }  
    
    
    @Override
    public void paint(Graphics2D g) {

                g.fillRect(ballX, ballY, ballWidth, ballHeight);
		
    }
    
    public void reSpawn(){
                ballX = window.getWidth() / 2;
                ballY = window.getHeight() / 2;
    }
    
    public boolean ballHit(){       // switch game mode here
        if ((getBorder().intersects(window.paddle.getData("x"),window.paddle.getData("y"),window.paddle.getData("width"),window.paddle.getData("height")))
           || (getBorder().intersects(window.paddle1.getData("x"),window.paddle1.getData("y"),window.paddle1.getData("width"),window.paddle1.getData("height")))
                || (getBorder().intersects(window.obstacle.getData("x"),window.obstacle.getData("y"),window.obstacle.getData("width"),window.obstacle.getData("height")))
                || (getBorder().intersects(window.paddle2.getData("x"),window.paddle2.getData("y"),window.paddle2.getData("width"),window.paddle2.getData("height")))
                || (getBorder().intersects(window.paddle3.getData("x"),window.paddle3.getData("y"),window.paddle3.getData("width"),window.paddle3.getData("height")))
                
                ){
            window.playSound("Pong");
            return true;
            
        }
        return false;
            
    }
    
    public String edgeHit(){
        
                    if ((getBorder().intersects(0, 0, 0.1, window.getHeight())))
                    
                    {
                        return "L";
                    }
                    
                    else if ((getBorder().intersects(window.getWidth() - 0.1, 0, 0.1, window.getHeight()))){
                        return "R";
                    }
                    else if ((getBorder().intersects(0, 0, window.getWidth(), 0.1))){
                        
                    
                        return "T";
                    }
                    else if ((getBorder().intersects(0.1, window.getHeight()-0.1, window.getWidth()-0.1, window.getHeight()-0.1))){
                       
                        return "B";
                    }
                    
                    
       
        return "";
    }
    

    
    public Rectangle getBorder(){
        return new Rectangle(ballX, ballY, ballWidth, ballHeight);
    }

    
}
