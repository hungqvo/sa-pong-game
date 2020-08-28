/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ponggame.Objects;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author David
 */

public class GameMenu {
    JFrame frame;
    private JLabel gLbl ;
    private JLabel onePLbl;
    private JLabel twoPLbl;
    private JLabel fourLbl;
    private JLabel resumeLbl;
    private JLabel hiScoreLbl;
    private JLabel exitLbl;
    private JLabel easyLevel;
    private JLabel mediumLevel;
    private JLabel hardLevel;
    private Font toBeUsedFont;
    private JLabel test;
    private JPanel listPane;
    private JPanel wrapper;
    private GameMenu menu;
    private GameWindow window;
    private GameSound soundObject;
    
    private String title;
    private int width, height;
    private int level=-1;
    private boolean LoadGame=false;
    
    public GameMenu(String title){
        soundObject = new GameSound();
        
        this.title = title;
        menu = this;
        frame = new JFrame(title);
        window = new GameWindow(this); //initiate the frame without any GameWindow
        window.setSoundObject(soundObject);
        
        createDisplay(frame);
    }
    
    public void createDisplay(JFrame input){
        soundObject.playMusic("Menu");
        frame = input;
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        
        Toolkit tk = Toolkit.getDefaultToolkit();
        width = (int) tk.getScreenSize().getWidth();
        height = (int) tk.getScreenSize().getHeight();
        frame.setSize(width,height);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.BLACK);
        
        
        // Start Menu
        gLbl = new JLabel("Pong Revolution", JLabel.CENTER);
        gLbl.setForeground(Color.white);
        
        onePLbl = new JLabel("1 Player", JLabel.CENTER);
        onePLbl.setForeground(Color.white);
        
        twoPLbl = new JLabel("2 Player", JLabel.CENTER);
        twoPLbl.setForeground(Color.white);
        
        fourLbl = new JLabel("4 Player", JLabel.CENTER);
        fourLbl.setForeground(Color.white);
        
        resumeLbl = new JLabel("Resume game", JLabel.CENTER);
        resumeLbl.setForeground(Color.white);
        
        hiScoreLbl = new JLabel("High Score", JLabel.CENTER);
        hiScoreLbl.setForeground(Color.white);
        
        exitLbl = new JLabel("Exit", JLabel.CENTER);
        exitLbl.setForeground(Color.white);
        
        test = new JLabel("", JLabel.CENTER);
        test.setForeground(Color.white);
        
        easyLevel = new JLabel("Easy", JLabel.CENTER);
        easyLevel.setForeground(Color.white);
        
        mediumLevel = new JLabel("Medium", JLabel.CENTER);
        mediumLevel.setForeground(Color.white);
        
        hardLevel = new JLabel("Hard", JLabel.CENTER);
        hardLevel.setForeground(Color.white);
        
        // Included Truetype font (ttf) in Java Project
        InputStream ips = GameMenu.class.getResourceAsStream("font/PressStart2P.ttf");
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, ips);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font); 
            toBeUsedFont = font.deriveFont(12f);
            gLbl.setFont(new Font(toBeUsedFont.getFontName(), Font.PLAIN, 50));
            onePLbl.setFont(new Font(toBeUsedFont.getFontName(), Font.PLAIN, 28));
            twoPLbl.setFont(new Font(toBeUsedFont.getFontName(), Font.PLAIN, 28));
            hiScoreLbl.setFont(new Font(toBeUsedFont.getFontName(), Font.PLAIN, 28));
            fourLbl.setFont(new Font(toBeUsedFont.getFontName(), Font.PLAIN, 28));
            resumeLbl.setFont(new Font(toBeUsedFont.getFontName(), Font.PLAIN, 28));
            exitLbl.setFont(new Font(toBeUsedFont.getFontName(), Font.PLAIN, 28));
            easyLevel.setFont(new Font(toBeUsedFont.getFontName(), Font.PLAIN, 28));
            mediumLevel.setFont(new Font(toBeUsedFont.getFontName(), Font.PLAIN, 28));
            hardLevel.setFont(new Font(toBeUsedFont.getFontName(), Font.PLAIN, 28));
        } catch (Exception e){
            
        }
        
        setMouseListener(onePLbl);
        setMouseListener(twoPLbl);
        setMouseListener(hiScoreLbl);
        setMouseListener(fourLbl);
        setMouseListener(resumeLbl);
        setMouseListener(exitLbl);
        
        setMouseListener(easyLevel);
        setMouseListener(mediumLevel);
        setMouseListener(hardLevel);
        
        displayMenu(frame);
        
    }
    
    public void displayMenu(JFrame frameInput){
        frame = frameInput;
        listPane = new JPanel();
        listPane.setBackground(Color.BLACK);
        listPane.setLayout(new GridLayout(6,1,0,5));
       
        listPane.add(onePLbl);
        listPane.add(twoPLbl);
        listPane.add(fourLbl);
        if (true)
            listPane.add(resumeLbl);
        listPane.add(hiScoreLbl);        
        listPane.add(exitLbl);
        listPane.add(test);
        
        
        
        // The Main Layout
        wrapper = new JPanel();
        wrapper.setBackground(Color.BLACK);
        wrapper.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 0;
        wrapper.add(gLbl,c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 1;
        c.insets = new Insets(25,0,0,0);  //top padding
        
        wrapper.add(listPane,c);
        
        frame.add(wrapper);
        frame.setVisible(true);
    }
    
    private void setMouseListener(JLabel toChange){
        // Action Listener for Label
        toChange.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                soundObject.playSound("Click");
                String which = toChange.getText();
                switch(which){
                    case "1 Player":
                        
                        test.setText("1 Player");

                        gLbl.setText("Choose game difficulty:");
                        listPane.removeAll();
                        listPane.setLayout(new GridLayout(4,1,0,5));
                        listPane.add(easyLevel);
                        listPane.add(mediumLevel);
                        listPane.add(hardLevel);
                        listPane.add(test);
                        break;
                    case "2 Player":
                        soundObject.playMusic("Game");
                        test.setText("2 Player");
                        frame.remove(wrapper);
                        level = -1;
                        window.createWindow(2);
                        break;
                        
                    case "4 Player":
                        soundObject.playMusic("Game");
                        test.setText("2 Player");
                        frame.remove(wrapper);                        
                        window.createWindow(10);
                        break;
                        
                    case "High Score":
                        test.setText("High Score");
                        frame.remove(wrapper);
                        window.createWindow(6);
                        break;
                        
                    case "Resume game":
                        test.setText("2 Player");
                        frame.remove(wrapper);
                        LoadGame=true;
                        window.createWindow(2);
                        break;
                        
                    case "Exit":
                        soundObject.playSound("Exit");
                        try {
                            Thread.sleep(1500);
                            System.exit(0);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(GameWindow.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        
                        break;
                    case "Easy":
                        soundObject.playMusic("Game");
                        test.setText("Easy");
                        level=1;
                        frame.remove(wrapper);
                        window.createWindow(2);
                        break;
                    case "Medium":
                        soundObject.playMusic("Game");
                        test.setText("Medium");
                        level=2;
                        frame.remove(wrapper);
                        window.createWindow(2);
                        break;
                    case "Hard":
                        soundObject.playMusic("Game");
                        test.setText("Hard");
                        level=3;
                        frame.remove(wrapper);
                        window.createWindow(2);
                        break;
                }
                
            }
            @Override
            public void mousePressed(MouseEvent e) {
                toChange.setForeground(Color.YELLOW);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                toChange.setForeground(Color.white);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                soundObject.playSound("Hover");
                toChange.setForeground(Color.red);
            }
            @Override
            public void mouseExited(MouseEvent e) {                
                toChange.setForeground(Color.white);
            }
        });
        
        
    }
    
    public boolean LoadGame(){
        if (LoadGame) {
            LoadGame=false;
            return true;
        }
        else return false;
    }
    
    public int getLevel(){
        return level;
    }
    
    
    
    
}


