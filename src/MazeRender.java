 

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class MazeRender extends JComponent
{
    //instance variables
    private Maze maze;
    private BufferedImage img;
    //Sets the Maze to be painted.
    public void setMaze( Maze b )
    {
            maze = b;
        
    }
   //Creates a graphic in paintComponent with variable g, and uploads a file with the picture of the maze
    public void paintComponent(Graphics g )
    {
        try {
            img = ImageIO.read(new File(maze.getFilename()));
        } catch (IOException e) {
        }
        //draws the image with the coordinates given from the maze
        g.drawImage(img,maze.getX(), maze.getY(), null);
    }
    
    
}
