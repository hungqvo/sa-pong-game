/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ponggame.Objects;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


/**
 *
 * @author David
 */



public class GameWindow extends JPanel{
    GameWindow window;
    java.util.Timer move,move2,move3,movee1,movee2,movee3,movee;
    java.util.Timer timer;
    JFrame frame;
    JLabel test;
    JPanel panel;
    JButton pauseButton;
    GameMenu menu;
    int count;
    int gameDuration = 0;
    String name;
    int tmp;
    int scoreOne = 0;
    int scoreTwo = 0;
    Boolean check1=true,check2=true,check3=true,check4=true;
    Boolean ccheck1=true,ccheck2=true,ccheck3=true,ccheck4=true;
    int winner = 0;
    int fWinner = 0;    
    int quantity;
    int totalScore = 0;
    int lastStatus = 0;
    int lastLevel = 0;
    int Obstacle = 0;
    boolean AImode=false;
    boolean askName = false;
    boolean blinkScore = false;
    boolean displayedScore = false;
    boolean Pause=false;
    int speedBall;
    int speedGame,speedAI;
    Font myFont;
    int[] highscore = new int[1000];
    String[] highscoreName = new String[1000];
    int gameStatus;
    int rowPosition = 200;
    Random ran;
    
    boolean blink = true;
    boolean music = false;
    
   public static Set<String> keyLog = new HashSet<String>();
    
    GameObject paddle,paddle1, paddle3, paddle2, obstacle, ball, ball2;
    GameObjectFactory objectFactory;
    GameSound soundObject;
    GameMemento m = new GameMemento();
    
    //public GameWindow(JFrame useFrame, int status)
    public GameWindow(GameMenu menu)
    {
         //this.frame = new JFrame("Ball!");
        this.menu = menu;
        frame = menu.frame;
        window = this;
        
        objectFactory = new GameObjectFactory();
        //frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        
        readFile();
        bubbleSort();
        
    }
    
    public void createWindow(int status){
        gameStatus = status; //Game Menu
        lastStatus = status;
        
//        pauseButton = new JButton("PAUSE");
//        pauseButton.setVisible(false);
        try{
            BufferedImage buttonIcon = ImageIO.read(new File("src/ponggame/Objects/images/pause-bt.png"));
            
            pauseButton = new JButton(new ImageIcon(buttonIcon));
            pauseButton.setBorder(BorderFactory.createEmptyBorder());
            pauseButton.setContentAreaFilled(false);
            
            pauseButton.setVisible(false);
            
           }
        catch(Exception e){
            System.out.println(e.toString());
        } 
            frame.setLayout(new BorderLayout());
            this.setLayout(new FlowLayout(FlowLayout.RIGHT));
            //this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
            this.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
            this.add(Box.createHorizontalGlue());
            this.add(pauseButton);
            frame.add(this,BorderLayout.NORTH);
            
            pauseButton.addMouseListener(new MouseListener(){

                @Override
                public void mouseClicked(MouseEvent e) {
                    //System.out.println("CLICKED");
                    if (!Pause){
                        //System.out.println("Pause");
                    count = 0;
                    move.cancel();
                    move3.cancel();
                    timer.cancel();
                    if (AImode) move2.cancel();
                    if (!AImode)
                    m.addMemento("Double",ball.getData("x"),ball.getData("y"),ball.getData("increX"),ball.getData("increY"),
                            paddle.getData("y"),paddle1.getData("y"),speedGame,speedAI,gameDuration,scoreOne,scoreTwo);
                    else{ 
                        //System.out.println(speedGame);
                        if (speedGame==5)
                        m.addMemento("SingleEasy",ball.getData("x"),ball.getData("y"),ball.getData("increX"),ball.getData("increY"),
                                paddle.getData("y"),paddle1.getData("y"),speedGame,speedAI,gameDuration,scoreOne,scoreTwo);
                        
                        
                            if (speedGame==3)
                        m.addMemento("SingleMedium",ball.getData("x"),ball.getData("y"),ball.getData("increX"),ball.getData("increY"),
                                paddle.getData("y"),paddle1.getData("y"),speedGame,speedAI,gameDuration,scoreOne,scoreTwo);
                        
                        if (speedGame==1)
                            
                                m.addMemento("SingleHard",ball.getData("x"),ball.getData("y"),ball.getData("increX"),ball.getData("increY"),
                                        paddle.getData("y"),paddle1.getData("y"),speedGame,speedAI,gameDuration,scoreOne,scoreTwo);
                    }
                    
                    
                    }
                    Pause=true;
                    //GamePopupMenu pauseMenu = new GamePopupMenu(window);
                    
                    GamePopupMenu pauseMenu = GamePopupMenu.getPopUp();
                    pauseMenu.setSource(window);

                }

                @Override
                public void mousePressed(MouseEvent e) {
                    
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    
                }
                
            });
//      }
        
        //Modified
        InputStream ips = GameWindow.class.getResourceAsStream("/ponggame/Objects/font/PressStart2P.ttf");
        
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, ips);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font); 
            myFont = font.deriveFont(12f);
        }
        catch (Exception e){
            
        }
        //Modified
        
        
        Toolkit tk = Toolkit.getDefaultToolkit();
        int width = (int) tk.getScreenSize().getWidth();
        int height = (int) tk.getScreenSize().getHeight();
        frame.setSize(width,height);
        frame.setResizable(false);
        frame.setVisible(true);
        setForeground(Color.white);
        setBackground(Color.BLACK);
        this.frame.add(this);
        if (status==10){
            player4Mode();
        }
        else{
            if (menu.LoadGame()) LoadResume();
            else
            instantiate();
        }
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        frame.addKeyListener(new KeyListener() {
			
            @Override
            public void keyTyped(KeyEvent e) {                
            }

            @Override
            public void keyPressed(KeyEvent e) {                             
                if ((e.getKeyCode() == KeyEvent.VK_SPACE)){
                    if (gameStatus == 3 || gameStatus == 4 || gameStatus == 5){
                        resetGame();
                        instantiate();
                        gameStatus = lastStatus;
                    }
                    else if (gameStatus == 7){
                        
                        player4Mode();
                    }
                }
                
                else if ((e.getKeyCode() == KeyEvent.VK_M)){
                    if (gameStatus == 3 || gameStatus == 4 || gameStatus == 5 || gameStatus == 6 || gameStatus == 7){
                        gameStatus = 1;
                        if (gameStatus == 6){
                            blinkScore = false;
                        }
                    }
                }
                
                else if ((e.getKeyCode() == KeyEvent.VK_X)){
                    if (gameStatus == 3 || gameStatus == 4 || gameStatus == 5 || gameStatus == 6 || gameStatus == 7){
                    soundObject.playSound("Exit");
                    try {
                        Thread.sleep(1500);
                        System.exit(0);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GameWindow.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    }
                }
                
                else{
                ((GamePaddle)paddle).keyPressed(e);
                    
                if (!AImode)
                ((GamePaddle)paddle1).keyPressed(e);
               if (status==10){
                   ((GamePaddle2)paddle2).keyPressed(e);
                   ((GamePaddle2)paddle3).keyPressed(e);
               }
                }
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                ((GamePaddle)paddle).keyReleased(e);
                if (!AImode) ((GamePaddle)paddle1).keyReleased(e);
                if (status==10){
                  ((GamePaddle2)paddle2).keyReleased(e);
                   ((GamePaddle2)paddle3).keyReleased(e);
               }
                
            }
		});
            frame.add(this);
            
		setFocusable(true);
    }
    
     public void LoadResume(){
        
        m.loadMemento();
        scoreOne=m.getScoreOne();
        
        scoreTwo=m.getScoreTwo();
        gameDuration+=m.getGameDuration();
        
        paddle = objectFactory.getGameObject("vpaddle");
        paddle.setInitData(this, 2, m.getPaddleY2(),0,0,0);

        paddle1 = objectFactory.getGameObject("vpaddle");
        paddle.setInitData(this, 1, m.getPaddleY2(),0,0,0);

        obstacle = objectFactory.getGameObject("obstacle");
        paddle.setInitData(this, 3, 0,0,0,0);

        //MODIFIED
        if (!m.getMode().equals("Double")){
            
            ball = objectFactory.getGameObject("ball");
            ball.setInitData(this,1,m.getBallX(),m.getBallY(),m.getBallMoveX(),m.getBallMoveY());
            AImode=true;
            lastLevel = menu.getLevel();
            if (m.getMode().equals("SingleEasy")) {
            speedGame=5;
            speedAI=300;
            }
            else if (m.getMode().equals("SingleMedium")){
              speedGame=3;
                speedAI=100;
            }
            else {
                  speedGame=1;
                speedAI=1;
            }
            
            move = new java.util.Timer();
            move2 = new java.util.Timer();
        
        
            move.scheduleAtFixedRate(new TimerTask()    // run the function at a fixed rate
            {
                public void run()
                {
                     ball.move();
                     paddle.move();
                     paddle1.move();
                     repaint();
                }
            },0,speedGame);     // 5 milliseconds 1 3 5
         move3 = new java.util.Timer();
        move3.scheduleAtFixedRate(new TimerTask()    // run the function at a fixed rate
            {
                public void run()
                {
                    ((GameObstacle)obstacle).random(ball.getData("increX"),ball.getData("y"));
                    
                     repaint();
                }
            },0,3000);
            move2.scheduleAtFixedRate(new TimerTask()    // run the function at a fixed rate
            {
                public void run()
                {
                     ((GamePaddle)paddle1).moveAI(ball.getData("y"));

                     repaint();
                }
            },0,speedAI); // 2 4 7
        }
        
        else{
            AImode = false;
            
            ball = objectFactory.getGameObject("ball");
            ball.setInitData(this,1,m.getBallX(),m.getBallY(),m.getBallMoveX(),m.getBallMoveY());
            speedGame = 2;
            move = new java.util.Timer();
            move.scheduleAtFixedRate(new TimerTask()    // run the function at a fixed rate
            {
                public void run()
                {
                     ball.move();
                     paddle.move();
                     paddle1.move();
                     repaint();
                }
            },0,speedGame);     // 5 milliseconds 1 3 5
        }
        move3 = new java.util.Timer();
        move3.scheduleAtFixedRate(new TimerTask()    // run the function at a fixed rate
            {
                public void run()
                {
                    ((GameObstacle)obstacle).random(ball.getData("increX"),ball.getData("y")); 
                    
                     repaint();
                }
            },0,3000);
        
        timer = new java.util.Timer();
        
        timer.scheduleAtFixedRate(new TimerTask()    // run the function at a fixed rate
        {
           public void run()
           {
               gameDuration += 1;
           }
        },0,1000);     // 5 milliseconds
        
        
        
    }
     
     public void player4Mode() {
         check1=true;
         check2=true;
         check3=true;
         check4=true;
         ccheck1=true;
         ccheck2=true;
         ccheck3=true;
         ccheck4=true;
         quantity=4;

         paddle = objectFactory.getGameObject("vpaddle");
         paddle.setInitData(this, 2, 0,0,0,0);
         paddle1 = objectFactory.getGameObject("vpaddle");
         paddle1.setInitData(this, 1, 0,0,0,0);
         paddle2 = objectFactory.getGameObject("hpaddle");
         paddle2.setInitData(this, 2, 0,0,0,0);
         paddle3 = objectFactory.getGameObject("hpaddle");
         paddle3.setInitData(this, 1, 0,0,0,0);
         
         obstacle = objectFactory.getGameObject("obstacle");
         obstacle.setInitData(this, 3, 0,0,0,0);
         
        AImode = false;

        ball2 = objectFactory.getGameObject("ball2");
        ball2.setInitData(this, 2, 0,0,0,0);
            
        speedBall=3;
        speedGame =3;
        
        move = new java.util.Timer();
        move.scheduleAtFixedRate(new TimerTask()    // run the function at a fixed rate
            {
                public void run()
                {
                     ball2.move();
                   
                    
                     repaint();
                }
            },0,speedBall);     // 5 milliseconds 1 3 5
        movee = new java.util.Timer();
        movee.scheduleAtFixedRate(new TimerTask()    // run the function at a fixed rate
            {
                public void run()
                {
                   
                     paddle.move();
                    
                     repaint();
                }
            },0,speedGame);    
            
        movee1 = new java.util.Timer();
        movee1.scheduleAtFixedRate(new TimerTask()    // run the function at a fixed rate
            {
                public void run()
                {
                     
                     paddle1.move();
                     
                     repaint();
                }
            },0,speedGame);
        movee2 = new java.util.Timer();
        movee2.scheduleAtFixedRate(new TimerTask()    // run the function at a fixed rate
            {
                public void run()
                {
                     
                    
                     paddle2.move();
                     
                     repaint();
                }
            },0,speedGame);
        movee3 = new java.util.Timer();
        movee3.scheduleAtFixedRate(new TimerTask()    // run the function at a fixed rate
            {
                public void run()
                {
                     
                    
                     paddle3.move();
                     
                     repaint();
                }
            },0,speedGame);
        move3 = new java.util.Timer();
        move3.scheduleAtFixedRate(new TimerTask()    // run the function at a fixed rate
            {
                public void run()
                {
                    ((GameObstacle)obstacle).random(ball2.getData("increX"),ball2.getData("y"));
                    
                     repaint();
                }
            },0,3000);
        
    }
    
    public void instantiate(){
    
        paddle = objectFactory.getGameObject("vpaddle");
        paddle.setInitData(this, 2, 0,0,0,0);
        paddle1 = objectFactory.getGameObject("vpaddle");
        paddle1.setInitData(this, 1, 0,0,0,0);
         
        obstacle = objectFactory.getGameObject("obstacle");
        obstacle.setInitData(this, 3, 0,0,0,0);
        //MODIFIED
        if (menu.getLevel() != -1){
            ball = objectFactory.getGameObject("ball");
            ball.setInitData(this, 1, 0, 0, 0, 0);
            AImode=true;
            lastLevel = menu.getLevel();
            if (menu.getLevel()==1) {
            speedGame=5;
            speedAI=300;
            }
            else if (menu.getLevel()==2){
              speedGame=3;
                speedAI=100;
            }
            else {
                  speedGame=1;
                speedAI=1;
            }
            
            move = new java.util.Timer();
            move2 = new java.util.Timer();
            move3 = new java.util.Timer();
        
            move.scheduleAtFixedRate(new TimerTask()    // run the function at a fixed rate
            {
                public void run()
                {
                     ball.move();
                     paddle.move();
                     paddle1.move();
                     repaint();
                }
            },0,speedGame);     // 5 milliseconds 1 3 5
        
            move2.scheduleAtFixedRate(new TimerTask()    // run the function at a fixed rate
            {
                public void run()
                {
                     ((GamePaddle)paddle1).moveAI(ball.getData("y"));

                     repaint();
                }
            },0,speedAI); // 2 4 7
            
            move3.scheduleAtFixedRate(new TimerTask()    // run the function at a fixed rate
            {
                public void run()
                {
                     
                    ((GameObstacle)obstacle).random(ball.getData("increX"),ball.getData("y"));
                     repaint();
                }
            },0,3000);
        }
        
        else{
            AImode = false;
            //ball = new GameBall(this,2);
            ball = objectFactory.getGameObject("ball");
            ball.setInitData(this, 2, 0,0,0,0);
            move = new java.util.Timer();
            speedGame = 2;
            move.scheduleAtFixedRate(new TimerTask()    // run the function at a fixed rate
            {
                public void run()
                {
                     ball.move();
                     paddle.move();
                     paddle1.move();
                     repaint();
                }
            },0,speedGame);     // 5 milliseconds 1 3 5
            move3 = new java.util.Timer();
             move3.scheduleAtFixedRate(new TimerTask()    // run the function at a fixed rate
            {
                public void run()
                {
                     
                    ((GameObstacle)obstacle).random(ball.getData("increX"),ball.getData("y"));
                     repaint();
                }
            },0,3000);
        }
        
        
        timer = new java.util.Timer();
        
        timer.scheduleAtFixedRate(new TimerTask()    // run the function at a fixed rate
        {
           public void run()
           {
               gameDuration += 1;
           }
        },0,1000);     // 5 milliseconds
    }

    
    public void resetGame(){
        askName = false;
        gameDuration = 0;
        scoreOne = 0;
        scoreTwo = 0;
    }
    
    public void paint(Graphics g)
    {
        super.paint(g);     //painting the panel
        
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON); //improve the quality
        
        if(gameStatus == 2)
            pauseButton.setVisible(true);
        else
            pauseButton.setVisible(false);
        
        if (gameStatus == 1){   //Done sound
            resetGame();
            frame.remove(this);
            menu.createDisplay(frame);
            
        }
        
        if (gameStatus == 2){       // Done sound
            
            g.setFont(new Font(myFont.getFontName(),1,50));
            g.drawString(Integer.toString(scoreOne) + "-", frame.getWidth()/2 - 100, 55);
            g.drawString(Integer.toString(scoreTwo), frame.getWidth()/2 + 10, 55);
            g.setFont(new Font(myFont.getFontName(),1,20));
            g.drawString("Duration:" + Integer.toString(gameDuration), frame.getWidth()/2 + 150, 40);
            try {
                ball.paint(g2d);    // paint the ball on this graphics2d
                paddle.paint(g2d);
                paddle1.paint(g2d);
                obstacle.paint(g2d);
            }
            catch (Exception e)
            {
                System.out.println(e.toString());
            }
        }
        
        if (gameStatus == 10){       
            if ((!check1) && (ccheck1) ){
                ccheck1=false;
                movee.cancel();
                movee.purge();
                quantity-=1;
            }
            if ((!check2) && (ccheck2) ){
                ccheck2=false;
                movee1.cancel();
                movee1.purge();quantity-=1;
            }
            if ((!check3) && (ccheck3) ){
                ccheck3=false;
               
                movee2.cancel();
                movee2.purge();quantity-=1;
            }
            if ((!check4) && (ccheck4) ){
                ccheck4=false;
                movee3.cancel();
                movee3.purge();quantity-=1;
            }
            if (quantity ==1){
                
                if (check1){
                    fWinner = 1;
                }
                else if (check2)
                    fWinner = 2;
                else if (check3)
                    fWinner = 3;
                else if (check4)
                    fWinner = 4;
                
                gameStatus = 7;
                
                move3.cancel();
                move.cancel();
                move3.purge();
                move.purge();
                
                movee.cancel();
                movee.purge();
                movee1.cancel();
                movee1.purge();
                movee2.cancel();
                movee2.purge();
                movee3.cancel();
                movee3.purge();
                
                
            }
            try {
                ball2.paint(g2d);    // paint the ball on this graphics2d\
                obstacle.paint(g2d);
                if (check1) paddle.paint(g2d);
                if (check2) paddle1.paint(g2d);
                if (check3) paddle2.paint(g2d);
                if (check4)  paddle3.paint(g2d);
                
                
            }
            catch (Exception e)
            {
                System.out.println(e.toString());
            }
        }
        
        if (gameStatus == 7){       // 4 player ends
            g.setFont(new Font(myFont.getFontName(),1,50));
            g.drawString("Game Over!", frame.getWidth()/2 - 200, 200);
            g.setFont(new Font(myFont.getFontName(),1,25));
            rowPosition = 250;
            blinker(g);
        }
        
        if (gameStatus == 3){       // 1 player win
            timer.cancel();
            timer.purge();
            move.cancel();
            move.purge();
            if(move2 != null){
                move2.cancel();
                move2.purge();
            }
            move3.cancel();
            move3.purge();

            calculateScore();
            if (!askName){
                String saveName =JOptionPane.showInputDialog(frame, "What's your name?") ;
                
                if (saveName!=null){
            highscore[tmp]=totalScore;
            
            highscoreName[tmp] = saveName;
            tmp++;
            bubbleSort();
            writeFile();}
            askName = true;
            }
            
            
            
             
            g.setFont(new Font(myFont.getFontName(),1,50));
            g.drawString("You Win!!", frame.getWidth()/2 - 200, 200);
            g.setFont(new Font(myFont.getFontName(),1,25));
            g.drawString("Your score: " + totalScore,frame.getWidth()/2 - 170, 250);
            rowPosition = 350;
            blinker(g);
        }
        
        if (gameStatus == 4){       //1 player lose
            timer.cancel();
            timer.purge();
            move.cancel();
            move.purge();
            if(move2 != null){
                move2.cancel();
                move2.purge();
            }
            move3.cancel();
            move3.purge();
            g.setFont(new Font(myFont.getFontName(),1,50));
            g.drawString("You Lose!", frame.getWidth()/2 - 200, 200);
            g.setFont(new Font(myFont.getFontName(),1,25));
            rowPosition = 300;
            blinker(g);
            
        }
        
        if (gameStatus == 5){       // 2 player win
            timer.cancel();
            timer.purge();
            move.cancel();
            move.purge();
            move3.cancel();
            move3.purge();
            
            winner = GameBall.winner;
            g.setFont(new Font(myFont.getFontName(),1,50));
            g.drawString("Game Over!", frame.getWidth()/2 - 200, 200);
            g.setFont(new Font(myFont.getFontName(),1,25));
            g.drawString("Player " + winner + " Wins!",frame.getWidth()/2 - 150, 250);
            rowPosition = 350;
            blinker(g);
 
        }
        
        if (gameStatus == 6){
            timer.cancel();
            timer.purge();
            if (move2 != null){
                move2.cancel();
                move2.purge();
            }
            move.cancel();
            move.purge();
            move3.cancel();
            move3.purge();
            rowPosition = 200;

            g.setFont(new Font(myFont.getFontName(),1,50));
            g.drawString("Hall Of Fame", frame.getWidth()/2 - 280, 150);
            int k;
            if (tmp>5) k=5;
            else k=tmp;
            
            for (int i=0;i<k;i++){
                //in ra top 5 ne
                //highscore[i] -> diem
                //highscoreName[i] -> ten
                g.setFont(new Font(myFont.getFontName(),1,30));
                              
                    g.drawString(highscoreName[i] + "",frame.getWidth()/2 - 150,rowPosition);
                
                
                    g.drawString(highscore[i] + "",frame.getWidth()/2 + 70,rowPosition);
                    rowPosition += 40;
                
            }
            blinkScore = true;
            blinker(g);
        }
    }
    
    public void calculateScore(){      
        if (gameDuration <= 1000)
        totalScore = 1000-gameDuration;
        else totalScore = 0;
      
        
    }
    
    public void blinker(Graphics g){
        g.setFont(new Font(myFont.getFontName(),1,15));
            //resetGame();
            if(blink){
                if (!blinkScore && gameStatus != 7)
                    g.drawString("Press [Space] to retry ",frame.getWidth()/2 - 140, rowPosition);
                g.drawString("Press [M] for menu",frame.getWidth()/2 - 140, rowPosition + 50);
                g.drawString("Press [X] to exit",frame.getWidth()/2 - 140, rowPosition + 100);
            }          
            
            Runnable doHelloWorld = new Runnable() {
            public void run() {
                try {
                Thread.sleep(1000);
                blink = !blink;
                repaint();
                
                
            } catch (InterruptedException ex) {
                Logger.getLogger(GameWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            };

            SwingUtilities.invokeLater(doHelloWorld);
    }
    
  public void bubbleSort() {
    boolean check = true;
    int j = 0;
    int tmp1;
    String tmp2;
    while (check) {
        check = false;
        j++;
        for (int i = 0; i < tmp+1 - j; i++) {
            if (highscore[i] < highscore[i + 1]) {
                tmp1 = highscore[i];
                highscore[i] = highscore[i + 1];
                highscore[i + 1] = tmp1;
                
                tmp2 = highscoreName[i];
                highscoreName[i] = highscoreName[i + 1];
                highscoreName[i + 1] = tmp2;
                check = true;
            }
        }
    }
}
    
    public void readFile(){
     
         String fileName = "highscore.txt";

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                String[] tmpp = line.split(",");
                highscore[tmp]=Integer.parseInt(tmpp[1]);
                highscoreName[tmp]=tmpp[0];
                tmp++;
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
    
    public void writeFile(){
        String fileName = "highscore.txt";

        try {
            // Assume default encoding.
            FileWriter fileWriter =
                new FileWriter(fileName);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =  new BufferedWriter(fileWriter);
            
            for (int i=0;i<tmp;i++){
                bufferedWriter.write(highscoreName[i]+","+highscore[i]);
                bufferedWriter.newLine();
            }
            
            // Note that write() does not automatically
            // append a newline character.
           
            // Always close files.
            bufferedWriter.close();
        }
        catch(IOException ex) {
           
            ex.printStackTrace();
        }
    }
    
    public void setSoundObject(GameSound obj){
        soundObject = obj;
    }
    
    public void playSound(String when){
        soundObject.playSound(when);
    }
    
    void unPause() {
        if (Pause){
                    //System.out.println("Resume");
                    
                    Timer contTimer = new java.util.Timer();
                    contTimer.scheduleAtFixedRate(new TimerTask()    // run the function at a fixed rate
                    {
                        public void run()
                        {
                            //System.out.println("aloalo");
                            // Hung oi lam 3 2 1
                            if (count < 3){
                                count++;
                            }
                            else{
                                Pause=false;
                                contTimer.cancel();
                                //System.out.println("DAY NE " + speedGame);
                                move = new java.util.Timer();
                    move.scheduleAtFixedRate(new TimerTask()    // run the function at a fixed rate
                    {
                        public void run()
                        {
                             ball.move();
                             paddle.move();
                             paddle1.move();
                             repaint();
                        }
                    },0,speedGame);     // 5 milliseconds 1 3 5
                     move3=new java.util.Timer();
                     move3.scheduleAtFixedRate(new TimerTask()    // run the function at a fixed rate
            {
                public void run()
                {
                     
                    ((GameObstacle) obstacle).random(ball.getData("increX"),ball.getData("y"));
                     repaint();
                }
            },0,3000);

                   if (AImode) {
                        move2 = new java.util.Timer();
                       move2.scheduleAtFixedRate(new TimerTask()    // run the function at a fixed rate
                    {
                        public void run()
                        {
                             ((GamePaddle)paddle1).moveAI(ball.getData("y"));

                             repaint();
                        }
                    },0,speedAI);} // 2 4 7
                    
                    timer = new java.util.Timer();
        
        timer.scheduleAtFixedRate(new TimerTask()    // run the function at a fixed rate
        {
           public void run()
           {
               gameDuration += 1;
           }
        },0,1000); 
                   
                            }
                        }
                    },0,1000);     // 5 milliseconds 1 3 5  
                }
    }
    

    /**
     * @param args the command line arguments
     */
    
    
}