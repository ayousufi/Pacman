

public class Ball
{
    //creates variables for the coordinates of the ball, velocity of the ball, radius of the ball, and keeps track of lives
    private int velX, velY, radius, x, y, lives; 
    
    
    public Ball(int radius)
    {
        this.radius = radius;
        velX = -25;
        velY = 0;
        x = 250;
        y = 250;
        lives = 3;
        
    }
    //adds a life
    public void addLife(){
        lives++;
    }
    //Determines whether the pacman is alive, then either subtracts a live or ends the game.
    public boolean DIE(){
        boolean alive = true;
        if(lives==0){
            
            alive = false; 
        
           }
        else{
            lives = lives - 1;
            this.x = 250;
            this.y = 225;
           }
           return alive;
       }
       
       public void setLives(int x){
           lives = x;
        }
//A method that sets the X and Y coordinate as setX and setY.
    public void setX( int x ) { this.x = x; }
    public void setY( int y ) { this.y = y; }
    //A method that sets the radius as a positive integer.
    public void setR( int r )
    {
        if( r > 0 )
        {
            radius = r;
        }
    }
   //Moves the ball and sets the velocity of the ball.
    public void move() { setX(getX()+getVelX()); setY(getY()+getVelY()); }
    public void addVelX( int Vel ) { this.velX = Vel; }
    public void addVelY( int Vel ) { this.velY = Vel; }
    public void stop() { this.velX = this.velY = 0;
}
    public int getLives(){return lives;}
   //Gets the x-coordinate and returns it.
    public int getX() { return x; }
    //Gets the y-coordinate and returns it.
    public int getY() { return y; }
    //Gets the Velocity of the x axis movement and returns it.
    public int getVelX() { return velX; }
    //Gets the Velocity of the y axis movement and returns it.
    public int getVelY() { return velY; }
    //Gets the radius of the and returns it.
    public int getRadius() { return radius; }
   //creates a clone of the ball
    public Ball clone(){
       Ball k = new Ball( getRadius());
       k.setX(this.getX());
       k.setY(this.getY());
       k.addVelX(this.getVelX());
       k.addVelY(this.getVelY());
       return k;
    }
}
