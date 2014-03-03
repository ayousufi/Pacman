

public class Score implements Comparable<Score>
{
    // instance variables 
    private int score;
    private String name;
   
   
    public Score(int score, String name)
    {
       this.score = score;
      
       this.name = name;
    
    }
    //displays the score as a string
    public Score( String score )
    {
        String[] result = score.split("/");
        this.score = Integer.parseInt(result[0]);
        name = result[1];
    }
    //compares the scores to determine order of scores and where to place them
    public int compareTo(Score right){
        if(right.getScore() > this.getScore()){
            return -1;
        }
        if(right.getScore() < this.getScore()){
            return 1;
        }
        if(right.getScore()==this.getScore()){
            return 0;
        }
        return 0;
    }
    //gets and returns the name of the player
    public String getName(){
        return name;
    }
    //gets and returns the score of the player
    public int getScore(){
        return score;
    }
    //sets the score
    public void setScore(int score){
        this.score = score;
    }
    //returns a String that displays the score and the name of the person who got it
    public String toString()
    {
        return score + "/" + name;
    }
}
