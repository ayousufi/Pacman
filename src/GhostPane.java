

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.*;
import java.awt.image.*;
import java.io.*;
import java.util.Random;

public class GhostPane extends JPanel
{
    //instance variables for the arraylists of ghosts,mazes and powerups
    private ArrayList<ghost> ghosts;
    private ArrayList<Maze> mazes;
    private ArrayList<Maze> originalmaze;
    private ArrayList<PowerUp> powerups;
    
    //instances variables for the score and counter
    private int counter;
    private int totalscore;
    
    private GhostRender gr;
    
    private Random r;
    
    private String username;
    
    //render instance variables
    private PelletRender pr;
    private MazeRender mr;
    //pacman
    private Ball ball;
    private boolean open;
    // Animation timer
    private Timer timer;
    private Timer pretimer;
    
    
    
    private Timer powerTimer;
    private HighScores scores;
    
    
    
    private MazeRandomizer mazer;
    
    private boolean frozen;
    
    private String prestring;
    
    private int level;
    
    //methods for controlling the ball
    public void stopBall(){
        ball.stop();
    }
    
    public void moveBall(){
        ball.move();
    }
    
    public int getXBall(){
        return ball.getX();
    }
    
    public int getYBall(){
        return ball.getY();
    }
    
        
    public void addVelYBall(int x){
        ball.addVelY(x);
    }
    public void addVelXBall(int x){
        ball.addVelX(x);
    }
    //makes a ghostpane
    public GhostPane()
    {
        //adds an arraylsit of ghosts and mazes, new ballrender, pelletrender, mazerender and ghostrender, new ball and a new timer
        ghosts = new ArrayList<ghost>();
        gr = new GhostRender();
        ball = new Ball(25);//creates a new ball
        
        level = 1;
        
        mr = new MazeRender();
        pr = new PelletRender();
        mazes = new ArrayList<Maze>();
        originalmaze = new ArrayList<Maze>();
        timer = new Timer(110, new PaneAnimator() );
        pretimer = new Timer(3000, new Starter());
        setLayout(null);
        
        frozen = false;
        
        powerTimer = new Timer(5000, new GhostBuster());
        
        scores = new HighScores();
        
        powerups = new ArrayList<PowerUp>();
        counter = 0;
        open = false;
        //coordinates and functions for the powerups
        setPower();            
            prestring = "Get Ready!";
            
            
            
        
                    
    
}
   

    //the painter
    public void paintComponent( Graphics g )
    {
         String picture;       
        super.paintComponent(g);
        g.setColor(Color.BLACK);//background color of the mainwindow
        g.fillRect(0,0,650,650);//size of the game's window
        
        Font bigfont = new Font("sansserif", Font.PLAIN, 32);//fonts for the main window
        g.setFont(bigfont);
            
        
        totalscore = 36* (level -1) + pr.getPoints();
        //adds an extra life everytime you get 100 pellets
        if((pr.getExtraLivesPoints()) % 100 == 0 && pr.getExtraLivesPoints() > 0 && ball.getLives() <5){
            ball.addLife();
            pr.setExtraLivesPoints(0);
        }
     
           
        
        
        //moveMe is true when the ball has not collided with a wall or square
        boolean moveMe = true;
        //makes a clone of the ball
        Ball clone = ball.clone();
        clone.move();//moves clone
        //gets clone x, y
        int ballX = clone.getX();
        int ballY = clone.getY();
        //makes a rectange around the clone
        Rectangle boundBox = new Rectangle( ballX-(clone.getRadius()/5)+10, ballY-(clone.getRadius()/5)+10 , (clone.getRadius()/2) +10,(clone.getRadius()/2)+15 );
        //goes through the loop for every maze
          
                for( Maze one : mazes )
            {       //gets maze's variable
                    int MazeX = one.getX();
                    int MazeY = one.getY();
                    int MazeW = one.getWidth();
                    int MazeH = one.getHeight();
                    //sets the maze to test for collision and paint
                    mr.setMaze(one);
                    
                  
                   //makes a rectangle around the maze
                   Rectangle mazeRect = new Rectangle(MazeX, MazeY, MazeW, MazeH);
                  //if the rectangle around the ball and maze intersect kkk is true
                   boolean kollision = boundBox.intersects(mazeRect);
         
                   //if they collide the ball is not allowed to move
                    if(kollision){
                        moveMe = false;
                    }
                //paints the maze
                mr.paintComponent(g);
            }
            
            
            
        //draw ghosts
        //goes through loop for every ghost
        for( ghost bob : ghosts) 
        {
                
                if(!frozen){
                    //chases pacman
                    bob.chase(ball);
                    //if pacman has run out lives end the game
                    if(!bob.getAlive()){
                        timer.stop();
                        moveMe = false;
                        //prints game over
                        g.setColor(Color.WHITE);
                    
                        String over = "GAME OVER";
                        char[] gameover = over.toCharArray();
                        g.drawChars(gameover, 0, 9, 250, 300);
                        timer.stop();
                        
                       
                      
                    
                        //exits loop. no need for more ghosts to chase
                    //break; 
                }
                
            
        }
            else{
                //draws the frozen pacman when pacman's frozen
                bob.drawFreeze();
            }
                //sets the ghost to paint and paints it
                gr.setGhost(bob);
                gr.paintComponent(g);
                
            // Save the new high score
            if( !bob.getAlive()) 
            {
                scores.loadScores();
                // Get the high score here....
                //and add the new score
                scores.addScore(totalscore, username);
                
                scores.saveScores();
                
            }
         
    }
        
        //Moves and draws Pacman
        int x, y, radius;
        
        //moves ball if no collisons
        
        if (moveMe ) {
           ball.move();
        }    
        
        //draws ball
        x = ball.getX();
        y = ball.getY();
        radius = ball.getRadius();
        //chooses what picture to draw pacman depending on which direction pacman is facing          
        picture = "pacman.gif";
        if(open){
            if(ball.getVelX() > 0){picture = "right.gif";}
            if(ball.getVelX() < 0){picture = "left.gif";}
            if(ball.getVelY() > 0){picture = "down.gif";}
            if(ball.getVelY() < 0){picture = "up.gif";}
        }
        //finds the image in the folder to display
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(picture));
        } catch (IOException e) {
        }
        //draws the image
        g.drawImage(img , x, y, this);
                
        
       
        
        //draws tiny pacman to represent the lives
        int drawspot=300;
        BufferedImage livespic = null;
            try {
                livespic = ImageIO.read(new File("mini_pacman.gif"));
            } catch (IOException e) {
            }
            //draws more tiny pacman when you get more lives
        for(int i =0; i < ball.getLives(); i++){
            
            
            g.drawImage(livespic , drawspot, 615, this);
            drawspot += 45;
        }
        //points are displayed in white and as a String at certain coordinates
        int points = pr.getPoints();
        g.setColor(Color.WHITE);
        String a = "Score: " + totalscore+ "";
           
        g.drawString(a,20,640);
       
        String b = "Lives";
           
        g.drawString(b, 200, 640);
        //displays points with specific color and coordinates
        if (pr.getPoints() >= 36){
            g.setColor(Color.WHITE);
                    
                    timer.stop();
                    nextLevel();
                }
        g.drawString(prestring, 250,170);
                   
        int pelletX, pelletY;
        
        //draw pellets. goes through loop 36 times to make all the pellets
         for(int index =0; index <36; index++){
             //sets pellet to draw and draws it
            pr.setPelletIndex(index);
            pr.paintComponent(g);
            //checks for collision and if they do collide score the pellet
            pelletX = pr.getPelletX(index);
            pelletY = pr.getPelletY(index);
            int d = (int) Math.sqrt(Math.pow(Math.abs(pelletX-ball.getX()), 2)+ Math.pow(Math.abs(pelletY-ball.getY()), 2));
            
            if(d < ball.getRadius()+13) { pr.scorePellet(index);}
            
        }
         //checks to see if powerups have activated     
         for(PowerUp bob : powerups){
             if(!bob.collide(ball)){
                 bob.paintComponent(g);
                 
             }
             else{
                 
                 powerTimer.start();
                 frozen = true;
                 bob.setX(-50);
                 bob.setY(-50);
             }
         }
}
   //sets and adds powerups
    public void setPower() {
         for(int i = 0; i<4; i++){
           PowerUp jim;
			switch (i) {
			case 0:
				jim = new PowerUp(71, 70);
				break;
			case 1:
				jim = new PowerUp(571, 70);
				break;
			case 2:
				jim = new PowerUp(571, 570);
				break;
			case 3:
				jim = new PowerUp(70, 571);
				break;
			default:
				jim = new PowerUp(0, 0);
                break;
            }
            powerups.add(jim);
            
                
                    
    }
                    
    }

    //resets lives, points, pellets, and ghosts when you click restart
    public void Restart(){
        
    ball.setLives(3);
    pr.setPoints(0);
    pr.resetPellet();
    setPower();
    ball.setX(250);
    ball.setY(250);
    ball.addVelX(-25);
    ball.addVelY(0);
    repaint();
    frozen = false;
    for(ghost pinky : ghosts){
        pinky.setAlive(true);
        pinky.resetSpeed();
        pinky.reset();
        
    }
    
    pretimer.start();
    prestring = "Get Ready";
    for(int x = mazes.size() - 1; x >  72 ; x--){
            mazes.remove(x);
    }
    level = 1;
   
    }
    //resets pellets and increases ghost speed when you get to the next level
   public void nextLevel(){
       level++;
       for(ghost sue : ghosts){
           sue.increaseSpeed();
           sue.reset();
        }
        pr.setPoints(0);
        pr.resetPellet();
        setPower();
        ball.setX(250);
        ball.setY(250);
        ball.addVelX(-25);
        ball.addVelY(0);
        repaint();
        
        for(int x = mazes.size() - 1; x >  72 ; x--){
            mazes.remove(x);
        }
        
        newLevel();
        
        pretimer.start();
        prestring = "Level "+ level;
        frozen = false;
    }
   //creates random levels when you level up
    public void newLevel(){
        for(int x = mazes.size() - 1; x >  72 ; x--){
            mazes.remove(x);
        }
        int blocks;
        r = new Random();
        boolean a,b,c,d;
        int fillblock;
        //group A
        blocks = r.nextInt(4);
        a = b = c = d = false;
        
        for(int x = 0; x < blocks; x++){
            fillblock = r.nextInt(4)+1;
            //adds mazes as pictures 
            if(fillblock == 1 && !a){
                addMaze(new Maze(150,100,50,50,"box.png"));
                a = true;
            }
            else{
                if(a){
                    fillblock++;
                }
            }
            
            if(fillblock == 2 && !b){
                addMaze(new Maze(200,150,50,50,"box.png"));
                b = true;
            }
            else{
                if(b){
                    fillblock++;
                }
            }
            
            if(fillblock == 3 && !c){
                addMaze(new Maze(150,200,50,50,"box.png"));
                c = true;
            }
            else{
                if(c){
                    fillblock++;
                }
            }
            
            if(fillblock == 4 && !d){
                addMaze(new Maze(100,150,50,50,"box.png"));
                d = true;
            }
            
        }
        //group B
        blocks = r.nextInt(4);
        a = b = c = d = false;
        
        for(int x = 0; x < blocks; x++){
            fillblock = r.nextInt(4)+1;
            
            if(fillblock == 1 && !a){
                addMaze(new Maze(450,100,50,50,"box.png"));
                a = true;
            }
            else{
                if(a){
                    fillblock++;
                }
            }
            
            if(fillblock == 2 && !b){
                addMaze(new Maze(500,150,50,50,"box.png"));
                b = true;
            }
            else{
                if(b){
                    fillblock++;
                }
            }
            
            if(fillblock == 3 && !c){
                addMaze(new Maze(450,200,50,50,"box.png"));
                c = true;
            }
            else{
                if(c){
                    fillblock++;
                }
            }
            
            if(fillblock == 4 && !d){
                addMaze(new Maze(400,150,50,50,"box.png"));
                d = true;
            }
            
        }
        //Group C
        blocks = r.nextInt(4);
        a = b = c = d = false;
        
        for(int x = 0; x < blocks; x++){
            fillblock = r.nextInt(4)+1;
            
            if(fillblock == 1 && !a){
                addMaze(new Maze(150,400,50,50,"box.png"));
                a = true;
            }
            else{
                if(a){
                    fillblock++;
                }
            }
            
            if(fillblock == 2 && !b){
                addMaze(new Maze(200,450,50,50,"box.png"));
                b = true;
            }
            else{
                if(b){
                    fillblock++;
                }
            }
            
            if(fillblock == 3 && !c){
                addMaze(new Maze(150,500,50,50,"box.png"));
                c = true;
            }
            else{
                if(c){
                    fillblock++;
                }
            }
            
            if(fillblock == 4 && !d){
                addMaze(new Maze(100,450,50,50,"box.png"));
                d = true;
            }
            
        }
        //group D
        blocks = r.nextInt(4);
        a = b = c = d = false;
        
        for(int x = 0; x < blocks; x++){
            fillblock = r.nextInt(4)+1;
            
            if(fillblock == 1 && !a){
                addMaze(new Maze(450,400,50,50,"box.png"));
                a = true;
            }
            else{
                if(a){
                    fillblock++;
                }
            }
            
            if(fillblock == 2 && !b){
                addMaze(new Maze(500,450,50,50,"box.png"));
                b = true;
            }
            else{
                if(b){
                    fillblock++;
                }
            }
            
            if(fillblock == 3 && !c){
                addMaze(new Maze(450,500,50,50,"box.png"));
                c = true;
            }
            else{
                if(c){
                    fillblock++;
                }
            }
            
            if(fillblock == 4 && !d){
                addMaze(new Maze(400,450,50,50,"box.png"));
                d = true;
            }
            
        }
        //group x
        for(int i = 150; i < 550; i+=100){
            
            if(r.nextInt(2)>0){
                addMaze(new Maze(300,i, 50, 50,"box.png"));
            }
        }
            
        //group y
        
        for (int i = 150; i < 550; i+=100){
            if(r.nextInt(2)>0){
                addMaze(new Maze(i,300, 50, 50,"box.png"));
            }
        }
        
    }
   //creates a clone of maze
   public void cloneMaze(){
   originalmaze = mazes; 
   }
   //starts the timer
   public void start(){
       timer.start();
    }


    //stops the timer
     public void stop(){
        timer.stop();
    }
   
    public void addGhost( ghost b )
    {
        //adds ghosts to the arraylist
        ghosts.add(b);
        
    }
    
    public void addMaze(Maze m){
        //adds mazes to the arraylist
        mazes.add(m);
    }
    
    
   
    
    public void go()
    {
            username = JOptionPane.showInputDialog("Please enter you name");
           //starts timer
           //there is good reason for these two separate if statements. If the name is null then the .equals method will have a null pointer exception
           if(username==null){username="Anonymous";}
           if(username.equals("")){username="Anonymous";}
          
           pretimer.start();
    }
    
    public void addMazeList(ArrayList<Maze> mazesToAdd){
        
        for(Maze m : mazesToAdd){
            mazes.add(m);
        }
        
    }

      
    public class PaneAnimator implements ActionListener
    {   //class just for when the timer goes off
        public void actionPerformed( ActionEvent e )
        {
           
            open = !open;
            
            repaint();
            
        }
    }
    
    
    public class GhostBuster implements ActionListener
    {   //class just for when the timer goes off
        public void actionPerformed( ActionEvent e )
        {
            frozen = false;//boolean for freezing the timers of the ghost
            powerTimer.stop();
            
        }
    }
    
    public class Starter implements ActionListener
    {   //class just for when the timer goes off
        public void actionPerformed( ActionEvent e )
        {
           timer.start();
           pretimer.stop();
           prestring = "";
        }
    }
    
    
}
