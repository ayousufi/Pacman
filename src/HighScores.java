import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class HighScores
{
    //instance variables defining scores
    private ArrayList<Score> scores;
    public HighScores()
    {
        scores = new ArrayList<Score>();   
        
    }
    //opens up a text file that contains the saved highscores
    public void loadScores(){
        scores.clear();
        File file = new File("highscores.txt");
        
        try{
            //scans the text file to see if it should add a new highscore
            Scanner scanner = new Scanner(file);
            String data;
            while (scanner.hasNextLine()) {
               
                data = scanner.next();
                addScore(data);
                
            }
            scanner.close();
        }catch(Exception ex){
        }
        
       
    }
            
    //adds a new score to the saved scores
    public void addScore(String data){
        Score s = new Score(data);
        scores.add(s);
        Collections.sort(scores);
        
        
    }
    //adds a score and the name 
    public void addScore(int score, String name){
        Score s = new Score(score, name);
        scores.add(s);
    }
    //saves the score to a textfile called highscores
    public void saveScores()
    {
        PrintWriter writer = null;
        try
        {
           writer = new PrintWriter( new FileOutputStream( new File("highscores.txt")));
            for( Score s : scores ) {
                writer.println( s.toString() );
                
            }
            writer.flush();
            writer.close();
        }
        catch( IOException e )
        {
            System.err.println("ERROR: File \'highscores.txt\' could not be printed...");
        }
    }
    //gets the name inputed by the player
    public String getName(int index){
        return scores.get(index).getName();
    }
    //gets and returns the score the plater got
    public int getScore(int index){
        return scores.get(index).getScore();
    }
    //returns the size of the array of Scores
    public int getSize(){
        return scores.size();
    }
    
}
