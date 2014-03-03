import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
/*
 * @Authors: Abdullah Yousufi, Connor Pearson, and Derek Zheng
    @Instructor: Brian Sea
 *
 * DISCLAIMER: None of these classes are the work of one sole person. All of these authors contributed varying ammounts of code to vaious classes.
 * All authors are credited here and not in individual classes that they wrote.
 * END DISCLAIMER
 *
 * This class represents the main window
 */
public class MainWindow
{
    //defines a Jframe for scores and the game, a ball, a ghostpane, and a highscorespane
    private JFrame frame;
    private Ball ball;
    public GhostPane gp;
    private JFrame scores;
    private HighScorePane HSP;
    public MainWindow()
    {
        // Make the frame for the ghostpane
        scores = new JFrame("Highscores");
        scores.setSize(300,300);
        scores.setLocationRelativeTo(null);
        scores.setResizable(false);





        frame = new JFrame("Pacman");
        gp = new GhostPane();
        frame.setResizable(false);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        JMenuItem restart = new JMenuItem("Restart");
        JMenuItem score = new JMenuItem("Highscores");

        Restarter george = new Restarter();
        restart.addActionListener(george);

        fileMenu.add(restart);
        fileMenu.add(score);

        HighScoreShower shower = new HighScoreShower();
        score.addActionListener(shower);

        // Set the default actions and look for the frame and add the ghostpane to the frame
        frame.setSize(650,700);//set size of frame
        frame.setLocationRelativeTo(null); // center on screen
        frame.add(gp);



        //add multiple ghosts here at the corners of the screen
        for(int j = 0; j<4; j++){
        int x = 0;
        int y = 0;
        String name = "Blinky";
        if(j==1){
            x = 600;
            name = "clyde";
        }
        if(j==2){
            x = 600;
            y = 600;
            name = "inkey";
        }
        if(j==3){
            y = 600;
            name = "pinkey";
        }
        //makes the ghost and gives it to ghostpane
        ghost bob = new ghost(x, y, j, name);
        gp.addGhost(bob);

       }
       //defines the MazeRandomizer
       MazeRandomizer mr = new MazeRandomizer();
       ArrayList<Maze> mazes = mr.starterMaze();
       gp.addMazeList(mazes);
       gp.cloneMaze();

        //closes frame when you click the X
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);//makes frame visible

        frame.addKeyListener(new Listen() );//creates an action listen for the keyboard

        //adds the highscore pane and makes it visible
        gp.go();
        HSP = new HighScorePane();
        scores.add(HSP);
        scores.setVisible(false);

    }


    // The Key Listener
    public class Listen implements KeyListener
    {
        public void keyTyped ( KeyEvent e ){
        }

        public void keyPressed ( KeyEvent e){
            // Look at what the user pressed and respond accordingly
            //moves Pacman with arrowkeys, stops Pacman then moves it in a new direction to prevent diagnol movement and acceleration
            switch( e.getKeyCode() )
            {
                case KeyEvent.VK_DOWN:
                gp.stopBall();
                    gp.addVelYBall(25);
                    break;
                case KeyEvent.VK_UP:
                    gp.stopBall();
                    gp.addVelYBall(-25);
                    break;
                case KeyEvent.VK_RIGHT:
                    gp.stopBall();
                    gp.addVelXBall(25);
                    break;
                case KeyEvent.VK_LEFT:
                    gp.stopBall();
                    gp.addVelXBall(-25);
                    break;


                    default: // any other key then we need to get out of the method
                    return;
            }
        }

        public void keyReleased ( KeyEvent e ){
        }
    }
    public static void main( String[] args )
    {
        MainWindow app = new MainWindow(); //makes a new main window
    }


    public class Restarter implements ActionListener
    {
        public void actionPerformed( ActionEvent e )
        {
            //stops the frame to allow when you click restart
            gp.stop();
            //sets the dialogue for the window that pops up when you press restart
           int response = JOptionPane.showConfirmDialog(frame, "Would you like to restart?","Start", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE );
                    //restarts the game and starts the timer back up again
                    if( response == JOptionPane.YES_OPTION )

                    {
                        gp.Restart();
                        gp.start();

                    }
                    else{
                        gp.start();
                    }

                  //brings back focus to the screen
                    frame.requestFocus();

        }
    }
    //shows the frame where highscores are listed.
    public class HighScoreShower implements ActionListener
    {
        public void actionPerformed(ActionEvent e){
            scores.setVisible(true);

        }
    }

}
