/**
 * State.java
 * Purpose: This is an abstract class that
 *          stores information that all the
 *          states can access easily and has
 *          two abstract methods that all states
 *          needs.
 * Author:  Callum Berry
 * Version: 1.0
 * Date:    January 23, 2020
 * Target:  Mr. DiTomasso
 */

package States;

//Imports
import PokemonGame.Display;
import PokemonGame.Game;

import java.awt.*;

public abstract class State {

    //Initialization of variables
    protected Game game;
    protected Display display;
    private static State currentState;
    private static boolean isPlaying, win, runAway;
    private static String experienceString, experienceString2;
    private static int totalExperience;
    private static int experienceGained;
    private static int currentPlayerHP;

    //Getter and Setter for the currentState
    public static void setState(State state){
        currentState = state;
    }
    public static State getState(){
        return currentState;
    }

    //State class constructor that takes in a game and a display
    public State(Game game, Display display){
        this.game = game;
        this.display = display;

        isPlaying = true;       //Sets is playing to true
    }

    //The update and render abstract methods
    public abstract void update();
    public abstract void render(Graphics g);

    //Getter and setter for isPlaying boolean; used for music playback
    public static boolean isIsPlaying() {
        return isPlaying;
    }
    public static void setIsPlaying(boolean isPlaying) {
        State.isPlaying = isPlaying;
    }

    //Getter and setter for isWin boolean; used so the game knows if you won a battle
    public static boolean isWin() {
        return win;
    }
    public static void setWin(boolean win) {
        State.win = win;
    }

    //Getter and setter for isRunAway boolean; used in a battle to check if the player ran away
    public static boolean isRunAway() {
        return runAway;
    }
    public static void setRunAway(boolean runAway) {
        State.runAway = runAway;
    }

    //Getters and Setters for both experienceString and experienceString2; first one is experience gained, second is total experience
    public static String getExperienceString() {
        return experienceString;
    }
    public static void setExperienceString(String experienceString) {
        State.experienceString = experienceString;
    }
    public static String getExperienceString2() {
        return experienceString2;
    }
    public static void setExperienceString2(String experienceString2) {
        State.experienceString2 = experienceString2;
    }

    //Getter and Setter for the totalExperience variable
    public static int getTotalExperience() {
        return totalExperience;
    }
    public static void setTotalExperience(int totalExperience) {
        State.totalExperience = totalExperience;
    }

    //Getter and Setter for the experienceGained variable
    public static int getExperienceGained() {
        return experienceGained;
    }
    public static void setExperienceGained(int experienceGained) {
        State.experienceGained = experienceGained;
    }

    //Getter and Setter for the current player HP
    public static int getCurrentPlayerHP() {
        return currentPlayerHP;
    }
    public static void setCurrentPlayerHP(int currentPlayerHP) {
        State.currentPlayerHP = currentPlayerHP;
    }
}