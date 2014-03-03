import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.*;
import java.awt.image.*;
import java.io.*;



public class GhostRender extends JComponent
{
    //ghost to render
    public ghost blinky;
    
    //sets ghost to render
    public void setGhost(ghost bob){
        blinky = bob;
    }
    //renders (paints)  the ghost
    public void paintComponent(Graphics g )
    {
        //gets the ghost's vaiables to use
        int x = blinky.getX();
        int y = blinky.getY();
        
        
        // loads image and paints ghost

        //makes a buffered image and tries to load the right file. If there is an exception no file will be loaded
        BufferedImage ghostimg = null;
        try {
            ghostimg = ImageIO.read(new File(blinky.getpicture()));
        } catch (IOException e) {
        } 
        //paints the image of the ghost
        g.drawImage(ghostimg, x, y, null);
                          
}
}