/**
 * Game.java
 * Purpose: This is the main class of the game. It collects all other
 *          elements of the game and runs them. This class performs
 *          functions such as keeping a consistent framerate, initializing
 *          all states, updating and rendering all frames each tick,and
 *          activating and closing the program.
 * Author: Owen Gallagher, Callum Berry
 * Version: 1.0
 * Target: Mr. DiTomasso
 */

package PokemonGame;

//Imports
import PokemonPackage.Pokemon;
import States.*;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable{
    //Initializes the variables of the display, thread, BufferStrategy, and Graphice
    private Display display;
    public  Thread thread;
    private BufferStrategy bs;
    private Graphics g;

    //Create Objects for width, height, title, and a running boolean
    private int width, height;
    private String title;
    private boolean running;

    //Create a Pokemon Object
    private Pokemon pokemon;

    //Create objects for each of the Game States
    public State gameState;
    public State menuState;
    public State optionsState;
    public State gameOptionsState;
    public State battleState;
    public State postBattleState;
    public State tutorialState;
    public State starterSelectionState;
    public State finalBattleState;
    public State healState;
    public State postChampionState;

	//Create objects for all input method managers
    private KeyManager keyManager;
    private MouseInput mouseInput;

    //Create an object for the camera
    private Camera camera;

    //Variables for the timer that keeps track of how long you played
    private long timeAtStartOfGame, timePlayed;

    //Game Constructor
    public Game(String title, int width, int height){
        //Sets the variable of this class (purple variables) from the values of the constructor (white variables)
        this.width = width;
        this.height = height;
        this.title = title;

        //Gets the current time in milliseconds to be used later when calculating time played
        timeAtStartOfGame = System.currentTimeMillis();

        //Creates new Key Manager object and Mouse Input
        keyManager = new KeyManager();
        mouseInput = new MouseInput();

        //Sets running to false
        running = false;

        //Runs the start method
        start();
    }

    //Method for initializing stuff
    public void initialization(){
        Assets.initialization();
        Assets.track1();
        //Creates the new display with the needed variables
        display = new Display(title, width, height);

        //Adds all needed listeners to the Frame
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseInput);
        display.getFrame().addMouseMotionListener(mouseInput);
        display.getCanvas().addMouseListener(mouseInput);
        display.getCanvas().addMouseMotionListener(mouseInput);

        //Defines objects for the camera and Pokemon classes
        camera = new Camera(this,0, 0);
        pokemon = new Pokemon();

        //Creates objects for each of the game States
        gameState = new GameState(this, display);
        menuState = new MenuState(this, display);
        optionsState = new OptionsState(this, display);
        gameOptionsState = new GameOptionsState(this, display);
        battleState = new BattleState(this, display);
        postBattleState = new PostBattleState(this, display);
        tutorialState = new TutorialState(this, display);
        starterSelectionState = new StarterSelectionState(this, display);
        finalBattleState = new FinalBattleState(this, display);
        healState = new HealState(this, display);
        postChampionState = new PostChampionBattle(this, display);

        //Sets the initial state to the menuState
        State.setState(menuState);
    }

    //Method for updates
    public void update(){
        keyManager.update(); //Runs the update class of KeyManager

        if(State.getState() != null){ //If there is a state currently in use, update the game state
            State.getState().update();
        }
    }

    //Method for rendering
    public void render(){
        bs = display.getCanvas().getBufferStrategy();
        //If the bs is null (aka no buffer strategy) it creates a new buffer strategy with double buffering
        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;     //After creating buffer strategy it returns out of the if statement
        }
        g = bs.getDrawGraphics();       //Creates the graphics with the buffer

        //Clear Screen
        //Gets rid of flickering by adding a clear rectangle that is the same size as the canvas
        g.clearRect(0, 0, width, height);

        //If there is a state currently in use, render everything in that state
        if(State.getState() != null){
            State.getState().render(g);
        }

        //After Drawing Stuff
        g.dispose();     //Disposes the graphic g
        bs.show();      //Shows the buffer strategy
    }

    //Runnable that runs the update and render methods at 60fps
    public void run(){
        //Runs the initialization method
        initialization();

        //Initializes the variables
        int fps = 60;       //Sets fps to 60; this will be the frames per second
        int oneSecond = 1000000000;     //Sets what one nanosecond is equal to
        int updatesAndFrames = 0;       //Sets the number of updates and frames to 0
        double timePerUpdate = oneSecond / fps;     //Time it has to update to update and render at 60fps
        double delta = 0;
        long currentTime;       //The current time
        long timePassed;        //Time between current and last time
        long lastTime = System.nanoTime();      //Sets last time to the nanoTime (aka nanoseconds) of the system
        long fpsTimer = 0;      //Sets the fpsTimer to 0; timer that goes up to one for fps counter

        //While running is equal to true it does this
        while(running){
            currentTime = System.nanoTime();        //Gets the current time of the system in nanoseconds
            timePassed = currentTime - lastTime;    //Gets the time passed by subtracting the lastTime from the currentTime
            delta += timePassed / timePerUpdate;     //Gets delta by divided the timePassed by the timePerUpdate; used to see if the program must update and render or not
            fpsTimer += timePassed;         //Adds the timePassed to the fpsTimer
            lastTime = currentTime;     //Sets lastTime to the current time

            //If delta is greater than or equal to 1
            if(delta >= 1){
                //Runs the update and render methods
                update();
                render();

                updatesAndFrames++;     //Adds one to updatesAndFrames
                delta--;        //Subtracts one from delta
            }

            //If fpsTimer is greater than or equal to oneNanoSecond
            if(fpsTimer >= oneSecond){
                display.getFrame().setTitle(title + " Frames: " + updatesAndFrames );

                updatesAndFrames = 0;       //Resets the updates and frames variable
                fpsTimer = 0;      //Resets the timer
            }
        }
        stop();
    }

    public KeyManager getKeyManager(){
        return keyManager; //gets the KeyManager
    }
    public MouseInput getMouseInput(){
        return mouseInput; //gets the MouseInput
    }

    public Pokemon getPokemon() {
        return pokemon;
    } //Getter for the pokemon

    public Camera getCamera(){
        return camera;
    } //getter for the camera

    public long getTimePlayed() {
        return timePlayed = System.currentTimeMillis() - timeAtStartOfGame;
    }//Getter for the time played

    //Method that starts the thread
    public void start(){
        //Checks if the program is not running; if running equals true
        if(!running) {
            //If the program is not running set running to true, create the new Thread, and start the thread with thread.start();
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }
    //Method that stops the thread
    public void stop(){
        //Checks if the program is running; if running equals true
        if(running) {
            //If running equals true it stops the thread with thread.join; must have try catch for some reason
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getWidth() {
        return width;
    } //getter for the width
    public int getHeight() {
        return height;
    } //Getter for the height

    public static void main(String[] args) {
        //Runs a new game; has the value of String title and int width and height; these variable are used in the Game and Display constructor
        new Game("PokemonPackage", 1920, 1080);
    }
}