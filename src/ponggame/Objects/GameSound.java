/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ponggame.Objects;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
//import ponggame.View.GameSound;

/**
 *
 * @author David
 */
public class GameSound {
    Clip bgMusic;
    Clip gameSound;
    boolean musicPlay = true;
    boolean soundPlay = true;
    
    String currentStage;
    
    public GameSound(){        
    }
    
    public void playMusic(String stage){
        String musicPath = "0";
        currentStage = stage;
        switch(stage){
            case "Menu":
                musicPath = "src/ponggame/Objects/sound/intro-2.wav";
                break;
            case "Game":
                musicPath = "src/ponggame/Objects/sound/introbg.wav";
                break;
            case "End":
                musicPath = "src/ponggame/Objects/sound/ending.wav";   
                break;
        }
        
        
        if (musicPlay) {        
                try {                  
                        AudioInputStream soundStream = AudioSystem.getAudioInputStream(new File(musicPath).getAbsoluteFile());
                        if(bgMusic != null){        // Music already playing
                            bgMusic.stop();
                        }
                        bgMusic = AudioSystem.getClip();                
                        bgMusic.open(soundStream);
                        bgMusic.start();
                        bgMusic.loop(Clip.LOOP_CONTINUOUSLY);       // Loop music
             
                } catch (UnsupportedAudioFileException ex) {
                    Logger.getLogger(GameSound.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(GameSound.class.getName()).log(Level.SEVERE, null, ex);
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(GameSound.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }
    
    public void muteMusic(){
        musicPlay = !musicPlay;     // Mute and Unmute Music
        if (bgMusic != null){       // Music is playing
            if (musicPlay == false){
                bgMusic.stop();
            }
        }
        
        if (musicPlay == true){    // From Mute to replay
            playMusic(currentStage);
        }
        
    }
    
   public void playSound(String sound){
        String path = "";
        switch(sound){
            case "Hover":
                path = "src/ponggame/Objects/sound/hover.wav";
                break;
            case "Click":
                path = "src/ponggame/Objects/sound/click.wav";
                break;
            case "Exit":
                path = "src/ponggame/Objects/sound/gameOver.wav";
                break;
            case "Win":
                path = "src/ponggame/Objects/sound/tatada.wav";
                break;
            case "Lose":
                path = "src/ponggame/Objects/sound/lose.wav";
                break;
            case "Pong":
                path = "src/ponggame/Objects/sound/pong.aiff";
                break;        
        }
        
        if (soundPlay) {        
                try {                  
                        AudioInputStream soundStream = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
                        gameSound = AudioSystem.getClip();                
                        gameSound.open(soundStream);
                        gameSound.start();
             
                } catch (UnsupportedAudioFileException ex) {
                    Logger.getLogger(GameSound.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(GameSound.class.getName()).log(Level.SEVERE, null, ex);
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(GameSound.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }
    
    public void muteSound(){
        soundPlay = !soundPlay;     // Mute and Unmute sound        
    }
}
