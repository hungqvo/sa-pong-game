/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ponggame.Objects;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author s3446353
 */
public class GameMemento {
    String mode;
    private int BallX, BallY, BallMoveX, BallMoveY, PaddleY1,  PaddleY2, SpeedGame,SpeedAI,gameDuration,scoreOne,scoreTwo;
    public void addMemento(String mode,int BallX,int BallY, int BallMoveX,int BallMoveY,int PaddleY1, int PaddleY2,int SpeedGame,int SpeedAI,int gameDuration,int scoreOne,int scoreTwo){
        String fileName = "saveGame.txt";

        try {
            // Assume default encoding.
            FileWriter fileWriter =
                new FileWriter(fileName);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =  new BufferedWriter(fileWriter);
            bufferedWriter.write(mode+ ","+BallX+","+BallY+","+BallMoveX+","+BallMoveY+","+PaddleY1+","+PaddleY2+","+SpeedGame+","+SpeedAI+","+gameDuration+","+scoreOne+","+scoreTwo);
            
    

            // Note that write() does not automatically
            // append a newline character.
           
            // Always close files.
            bufferedWriter.close();
        }
        catch(IOException ex) {
           
            ex.printStackTrace();
        }
    }
    
    public void loadMemento(){
          String fileName = "saveGame.txt";

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            line = bufferedReader.readLine();
            if (line==null) mode="Nothing";
                    else{
                String[] tmpp = line.split(",");
                mode=tmpp[0];
                
                BallX=Integer.parseInt(tmpp[1]);
                BallY=Integer.parseInt(tmpp[2]);
                BallMoveX=Integer.parseInt(tmpp[3]);
                BallMoveY=Integer.parseInt(tmpp[4]);
                PaddleY1=Integer.parseInt(tmpp[5]);
                PaddleY2=Integer.parseInt(tmpp[6]);
                SpeedGame=Integer.parseInt(tmpp[7]);
                SpeedAI=Integer.parseInt(tmpp[8]);
                gameDuration=Integer.parseInt(tmpp[9]);
                scoreOne=Integer.parseInt(tmpp[10]);
                scoreTwo=Integer.parseInt(tmpp[11]);
                        //record that save has been used
                   
    

           
            // Always close files.
        
               
            }
            // Always close files.
            bufferedReader.close();         
            
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
    }
    
    public String getMode(){
        return mode;
    }
    
    public int getBallX(){
        return BallX;
    }
    public int getBallY(){
        return BallY;
    }
    public int getBallMoveX(){
        return BallMoveX;
    }
    public int getBallMoveY(){
        return BallMoveY;
    }
    public int getPaddleY1(){
        return PaddleY1;
    }
    public int getPaddleY2(){
        return PaddleY2;
    }
     public int getSpeedGame(){
        return SpeedGame;
    }
    public int getSpeedAI(){
        return SpeedAI;
    }
    public int getGameDuration(){
        return gameDuration;
    }
    public int getScoreOne(){
        return scoreOne;
    }
    public int getScoreTwo(){
        return scoreTwo;
    }
}
