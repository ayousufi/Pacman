import java.util.ArrayList;

import java.util.Collection;

public class MazeRandomizer
{
    //instance variable of an arraylist of mazes
    private ArrayList<Maze> mazes;
    
    
    //sets a variable for the arraylist
    public MazeRandomizer()
    {
        mazes = new ArrayList<Maze>();
    }

   
   
    public void addMaze(Maze m){
        mazes.add(m);
    }
    
    public ArrayList<Maze> starterMaze(){
        mazes.clear();
         //creates all the mazes and adds them to ghostpane
        for(int j = 1; j<6; j++){
        for(int i = 1; i<6 ; i++){
        
            Maze one = new Maze(i*50 + i*50 , j*50 + j*50, 50, 50, "box.png");
            addMaze(one);
        }
        }
        //sets pictures as the maze 
        for(int q = 1; q < 12; q++){
           addMaze(new Maze(q*50, 0, 50, 50,"bottom_line.png"));
        }
        
        for(int q = 1; q < 12; q++){
            addMaze(new Maze(0,q*50,50,50,"right_line.png"));
        }
        
        for(int q = 1; q < 12; q++){
            addMaze(new Maze(600,q*50,50,50,"left_line.png"));
        }
        
        for(int q = 1; q < 12; q++){
            addMaze(new Maze(q*50,600,50,50,"top_line.png"));
        }
        //adds the pictures for the corners of the maze
        addMaze(new Maze(0,0,50,50,"top_left"));
        addMaze(new Maze(0,600,50,50,"bottom_left"));
        addMaze(new Maze(600,0,50,50,"top_right"));
        addMaze(new Maze(600,600,50,50,"bottom_right"));
        
        
        //returns list of mazes
        return mazes;
    }
}
