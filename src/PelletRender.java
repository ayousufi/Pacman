/*
 * @author Abdullah, Brian, Connor, Derek
 *         
 *
 * This class represents the renderer for the pellets
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PelletRender
{
   
    private int[] pelletX = new int[36];//array for x coordinates of pellets
    private int[] pelletY = new int[36];//array for y coordinates of pellets
    private int pelletIndex;
    private int points;
    private int extralivespoints;
    public PelletRender()
    {

            
      int pos = 0;//starts at first element in array
      for( int x = 0; x < 6; x ++)//creates seven columns
      {
          for( int y = 0; y < 6; y++ )//will create six rows
          {
              pelletX[pos] = 75 + (100*x);//sets the X coordinate starting at 80 and incrementing by 80
              pelletY[pos] = 75 + (100*y);//sets the Y coordinate starting at 75 and incrementing by 100
              pos ++;//increases pellet element by one
          }
      }
      //this for loop sets the coordinates for each pellet in the array
    
    pelletIndex = 0;//counter for determining which pellet to draw

    points = 0;//refers to number of points won by eating pellets; points will not be displayed
    }
    
    public void resetPellet(){
        int pos = 0;//starts at first element in array
     
      for( int x = 0; x < 6; x ++)//creates seven columns
      {
          for( int y = 0; y < 6; y++ )//will create six rows
          {
              pelletX[pos] = 75 + (100*x);//sets the X coordinate starting at 80 and incrementing by 80
              pelletY[pos] = 75 + (100*y);//sets the Y coordinate starting at 75 and incrementing by 100
              pos ++;//increases pellet element by one
          }
      }
    } 
        
    //method for setting the pellet to draw
    public void setPelletIndex(int index){
        pelletIndex = index;
    }
    
    public void setPoints(int x){
        points = x;
    }
    
   //accesses x coordinate of pellet; this is for ghostpane
    public int getPelletX(int index){
        return pelletX[index];
            }
    
    //accesses y coordinate of pellet; this is for ghostpane
    public int getPelletY(int index){
        return pelletY[index];
    }
    
    //removes the pellet and adds one point
    public void scorePellet(int index){
        pelletX[index] = -20;
        pelletY[index] = -20;
        points++;
        extralivespoints++;
    }
   public int getExtraLivesPoints(){return extralivespoints;}
    //returns the number of points
    public int getPoints(){
        return points;
    }
    public void setExtraLivesPoints(int x){
        extralivespoints = x;
    }
    //paint component for the pellet draws and defines size and color
    public void paintComponent(Graphics g )
    {
            
        g.setColor( Color.YELLOW);
              
        int radius = 10;
        g.fillOval(pelletX[pelletIndex], pelletY[pelletIndex], radius, radius );
    }
    
   
}