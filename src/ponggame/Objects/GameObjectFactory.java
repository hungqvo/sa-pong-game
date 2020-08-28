/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ponggame.Objects;

/**
 *
 * @author David
 */
public class GameObjectFactory {
    public GameObject getGameObject(String type){
        if(type == null)
            return null;
        
        if(type.equalsIgnoreCase("vpaddle"))
            return new GamePaddle();
        
        else if(type.equalsIgnoreCase("hpaddle"))
            return new GamePaddle2();
        
        else if(type.equalsIgnoreCase("obstacle"))
                return new GameObstacle();
        
        else if(type.equalsIgnoreCase("ball"))
            return new GameBall();
        
        else if(type.equalsIgnoreCase("ball2"))
            return new GameBall2();
     
        return null;
    }
}
