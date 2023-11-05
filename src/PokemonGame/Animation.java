/**
 * Animation.java
 * Purpose: This class contains all backend code concerning animations,
 *          including the speed, and how often to switch between
 *          different animations.
 * Author: Owen Gallagher
 * Version: 1.0
 * Target: Mr. DiTomasso
 */

package PokemonGame;

//Imports
import java.awt.image.BufferedImage;

public class Animation {
    private int speed, index; //Define the speed, and the index (amount of frames in the animation)
    private long lastTime, timer; //Create objects for the animation timer, as well as a "lastTime" temp variable
    private BufferedImage[] frames; //Create an image object to contain the frames

    public Animation(int speed, BufferedImage[] frames){
        this.speed = speed; //Sets the speed and frames to be defined in the target object
        this.frames = frames;
        index = 0; //Initializes the index as 0 to begin with
        lastTime = System.currentTimeMillis(); //Get the time, as the current system time
    }

    public void tick(){
        timer += System.currentTimeMillis() - lastTime; //Get the current time of the timer
        lastTime = System.currentTimeMillis(); //Reset the LastTime variable to be the current system time

        if (timer > speed){ //If the timer is more than the speed (defined as 500 in the player class) run this code
            index++; //Add one to the index, making the animation switch to the next frame
            timer = 0; //Reset the timer to 0
            if(index >= frames.length) //If the index is longer than the length of the animation, reset the index back to 0.
                index = 0;
        }
    }

    public BufferedImage getCurrentFrame(){ //A getter for the current frame and index
        return frames[index];
    }
}
