

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class PowerUp
{
   
    
    private boolean used;
    private int radius;
    private int x, y;
    //gets the x value of the powerup and returns it
    public int getX(){
        return x;
    }
    //gets the y value of the powerup and returns it
    public int getY(){
        return y;
    }
    //contructor and makes the radius 15
    public PowerUp(int x, int y)
    {
       this.x = x;
       this.y = y;
       radius = 15;
    }
    //gets the radius of the powerup and returns it
    public int getRadius(){ return radius;}
    //draws the powerup
    public void paintComponent(Graphics g){
        g.setColor(Color.BLUE);
        g.fillOval(x, y, radius, radius);
    }
    //checks for collisions between pacman and the powerup
    public boolean collide(Ball ball){
        int d = (int) Math.sqrt(Math.pow(Math.abs(x-ball.getX()), 2)+ Math.pow(Math.abs(y-ball.getY()), 2));
            
            if(d < ball.getRadius()+15) { return true;}
            else{ return false;}
    }
    //sets the x coordinate 
    public void setX(int x){
        this.x = x;
    }
    //sets the y coordinate
    public void setY(int y){
        this.y = y;
    }
    
    
    
}
