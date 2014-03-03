 

import java.awt.*;
import javax.swing.*;
public class Maze
{
    int x, y, width, height; //Defines the variables of the coodinates and the size of the maze.
    String filename;
    public Maze(int X,int  Y,int  Width,int  Height, String filename){
        x = X; //set the variable,X , as the x-coordinate of the top left corner of the maze.
        y = Y; //set the variable,Y , as the y-coordinate of the top left corner of the maze.
        width = Width; //sets the variable,Width ,as the width of the maze.
        height = Height; //sets the variable, Height ,as the height of the maze.
        this.filename = filename;
    }
    //gets the x-coordinate and returns it.
    public int getX(){ 
        return x;
    }
    public String getFilename(){
        return filename;
    }
    //gets the y coordinate and returns it.
    public int getY(){
        return y;
    }
    //gets the width of the maze and returns it.
    public int getWidth(){
        return width;
    }
    //gets the height of the maze andd returns it.
    public int getHeight(){
        return height;
    }
    
     
}
