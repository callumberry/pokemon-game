/**
 * KeyManager.java
 * Purpose: This class contains all code that gets keyboard input from
 *          the user, and sending that input to other classes that
 *          require that information.
 * Author: Owen Gallagher
 * Version: 1.0
 * Target: Mr. DiTomasso
 */

package PokemonGame;

//Imports
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyManager extends KeyAdapter {
    private boolean[] keys; //Creates a boolean array for the keys
    public boolean up, down, left, right, running, optionsStateSwap, escapeKey; //Creates boolean objects for each of the keys we use in the game (if they're pressed down, they'll be true)

    public KeyManager(){
        keys = new boolean[256]; //Creates the key object
    }

    public void update(){
        up = keys[KeyEvent.VK_W]; //If the key is "true" (pressed down) the corresponding variable will be true
        down = keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];
        running = keys[KeyEvent.VK_SHIFT];
        optionsStateSwap = keys[KeyEvent.VK_R];
        escapeKey = keys[KeyEvent.VK_ESCAPE];
    }

    public void keyPressed(KeyEvent e) { //When a key is pressed, run this code
        if(e.getKeyCode() < 256){
            keys[e.getKeyCode()] = true; //Make whatever key is pressed be "true"
        }
    }
    public void keyReleased(KeyEvent e) { //When a key is released, run this code
        if(e.getKeyCode() < 256) {
            keys[e.getKeyCode()] = false; //Make whatever key is released be "false"
        }
    }
}