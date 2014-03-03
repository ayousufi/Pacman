import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.*;
import java.awt.image.*;


public class HighScorePane extends JComponent
{
    private HighScores scores;
    public HighScorePane()
    {
        //load highscores here
        scores = new HighScores();
        scores.loadScores();
        //scores.saveScores();
    }
    //draws the highscores window
   public void paintComponent(Graphics g){
       
        g.setColor(Color.BLACK);//sets background color of the window to black
        g.fillRect(0,0,300,300);//sets the size of the window
        
        Font bigfont = new Font(null, Font.BOLD, 20);//sets the font for the Highscores "Top Ten"
        g.setFont(bigfont);
        
        g.setColor(Color.YELLOW);//sets the color of the words displayed
        g.drawString("Top Ten", 100,20);//displayes "top ten" onto the window
        
        Font font = new Font(null, Font.PLAIN, 16);//sets the font of the scores and names
        g.setFont(font);
        int i = scores.getSize()-1;
        //draws the scores and names
        for(int x = 1; i >=0 && x <11; x++){
            
            g.drawString(x + ". " , 50, x*15 + 30);
            g.drawString(scores.getScore(i) + "" , 100, x*15 + 30);
            g.drawString(scores.getName(i), 150, x*15 + 30);
            i--;
        }
}
    //reloads the scores everytime you press it
    public void reload(){
        scores.loadScores();
    }
}
