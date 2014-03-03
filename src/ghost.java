

import java.awt.*;
import java.awt.event.*;
import javax.imageio.*;
import java.awt.image.*;


public class ghost{

    //instance variables for the ghost
    
    //variables for the coordinates, speed, life, starting speed and starting coordinates, name of the ghost, and string for the location of the file
    //boolean alive is used to record a kill of pacman
    private int x = 0;
    private int y = 0;
    private int speed = 3;
    private boolean alive = true;
    private int startx, starty, startspeed;
    private String name, picture;
    private boolean left, right, up, down;
    
    
    
    
    public ghost(int startx, int starty, int j, String name)
    {
        //sets x, y, name and speed for the ghost based on the parameters
        //the speeds will vary
        x = startx;
        y = starty;
        speed = j + 5;
        startspeed = speed;
        this.startx = startx;
        this.starty = starty;
       
        this.name = name;
        
    }
    //sets the picture to frozen picture
    public void drawFreeze(){
        picture = "freeze.gif";
    }
    //resets the ghosts to their respective starting places
    public void reset(){
        x = startx;
        y = starty;
       
    }
    //gets the boolean alive. It will be false if pacman has been killed
    public boolean getAlive(){
        return alive;
    }
    //resets the speed of the ghost to its level  1 status
    public void resetSpeed(){
        speed = startspeed;
    }
    //method for setting the boolean alive
    public void setAlive(boolean alive){
        this.alive = alive;
    }
        
    //method to chase the pacman and change the picture depending on what direction the ghost is going in
    public void chase(Ball ball)
    {   
        //gets the pacmans position
        
        Rectangle pacBox = new Rectangle(ball.getX() + ball.getVelX(), ball.getY() + ball.getVelY(), 40, 40);
        Rectangle ghostBox = new Rectangle(x,y,50,50);
        
        if(pacBox.intersects(ghostBox)){
            alive = ball.DIE();
            reset();
        }
        int PacX = ball.getX();
        int PacY = ball.getY();
        
        //moves position to be closer to pacman and chooses what picture file to use based on what direction the ghost goes in checks if they are allowed to also
        if(!right){
         if ( x < PacX){
            x = x + speed;
            picture = name + "_right.gif";
        }
    }
        if(!left){
        if (x > PacX) {
            x = x - speed;
            picture = name + "left.gif";
        }
    }
        if(!up){
        if (y < PacY){
            y = y + speed;
            picture = name+ "_down.gif";
        }
    }
        if(!down){
        if (y > PacY){
            y = y - speed;
            picture = name+ "_up.gif";
        }
    }
        
        
        
        
        
        
    }
    //method to get the picture to use for the ghost
     public String getpicture(){
        return picture;
    }
    //sets the available movements of the ghosts
    public void setMovements(boolean left, boolean right, boolean up, boolean down){
        this.left = left;
        this.right = right;
        this.up = up;
        this.down = down;
    }
    //increases the speed of the ghost by one
    public void increaseSpeed(){
        speed++;
    }
    
    //methods to get the coordinates of the ghost
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public int getSpeed(){
        return speed;
    }
    
    
    
}