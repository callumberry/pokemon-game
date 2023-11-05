/**
 * Display.java
 * Purpose: This class contains all code concerning the display,
 *          including specifics on the canvas and frame used in
 *          the game
 * Author: Callum Berry
 * Version: 1.0
 * Target: Mr. DiTomasso
 */

package PokemonGame;

//Imports
import javax.swing.*;
import java.awt.*;

public class Display{

    //Initializes the variables for the frame and canvas
    private JFrame frame;
    private Canvas canvas;

    //Creates objects for the PokeballIcon, title, width, and height
    private ImageIcon pokeballIcon;
    private String title;
    private int width, height;

    //Constructor for the Display, it takes the String title and int width and height from the Game constructor in the Game class
    public Display(String title, int width, int height){
        //Sets the variable of this class (purple variables) from the values of the constructor (white variables)
        this.title = title;
        this.width = width;
        this.height = height;

        //Runs the frame and canvas methods
        createFrame();
        createCanvas();
    }

    //Method that creates the frame
    public void createFrame(){
        //Creates the frame using the variables from the constructor
        frame = new JFrame(title);
        frame.setSize(width, height);

        //Changes the icon
        pokeballIcon = new ImageIcon("images/pokeball_Icon.png");
        frame.setIconImage(pokeballIcon.getImage());

        //Sets the frame to exit on close, not resizable, sets it to the middle of screen, removes the part of the JFrame with the name and logo, and to sets it visible
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);
        frame.setVisible(true);
    }

    //Method that creates the canvas
    public void createCanvas(){
        //Creates the canvas
        canvas = new Canvas();

        //Sets the size of the canvas; preferred, maiximum, and minimum are set all the same so the canvas' size doesn't change
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false); //Make the canvas the default focus of the computer

        //Adds the canvas to the frame and packs the frame; Packing helps with keeping the frame and canvas aligned when rendering stuff
        frame.add(canvas);
        frame.pack();
    }

    //A getter to later share the canvas with the Game class
    public Canvas getCanvas() {
        //Returns the information from the canvas
        return canvas;
    }
    //A getter to later share the frame with the Game class
    public JFrame getFrame(){
        return frame; //Returns the information from the frame
    }
}