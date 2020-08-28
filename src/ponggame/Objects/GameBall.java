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
public class GameBall implements GameObject{
    int ballX = 200, ballY = 250, ballWidth = 30,ballHeight=30;
    int increX=1,increY=1;  //decrement value
       
    
    static int winner;
    
    GameWindow window;
    
    int gameMode;       // 1 player, 2 player or 4 player
    
    public GameBall(){
        
    }
    public GameBall(GameWindow window, int mode) {
                this.window = window;
                gameMode = mode;                
	}
    
    
   
    @Override
    public void setInitData(GameWindow window, int mode,int BallX,int BallY, int BallMoveX,int BallMoveY) {
                this.window = window;
                gameMode = mode;              
                if (BallX != 0){
                    ballX=BallX;
                    ballY=BallY;
                    increX=BallMoveX;
                    increY=BallMoveY;
                }
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
            if (edgeHit().equals("L")){
                    window.scoreTwo += 1;
                    reSpawn();
                }
                else {
                    window.scoreOne += 1;
                    reSpawn();
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
            if (increX == 1)
                increX = -1;
            else if (increX == -1)
                increX = 1;

        }

                   
        if(ballY==0)
                   increY=1;
        if(ballY>=window.getHeight()-ballHeight)
                   increY=-1;
               
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
               
                
                ){
            window.playSound("Pong");
            return true;
            
        }
        return false;
            
    }
    
    public String edgeHit(){
        switch (gameMode){
                case 1:
                    if ((getBorder().intersects(0, 0, 0.1, window.getHeight())))
                    
                    {
                        return "L";
                    }
                case 2: 
                    if ((getBorder().intersects(0, 0, 0.1, window.getHeight())))
                    
                    {
                        return "L";
                    }
                    
                    else if ((getBorder().intersects(window.getWidth() - 0.1, 0, 0.1, window.getHeight()))){
                        return "R";
                    }
                    break;
                    
        }
        return "";
    }

    public Rectangle getBorder(){
        return new Rectangle(ballX, ballY, ballWidth, ballHeight);
    }

    

    

   
}
