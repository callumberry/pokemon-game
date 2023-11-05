/**
 * PostChampionBattle.java
 * Purpose: This State is used after the user wins or loses
 *          against the champion. Different information will
 *          be displayed depending if you win or lose.
 * Author:  Callum Berry
 * Version: 1.0
 * Date:    January 23, 2020
 * Target:  Mr. DiTomasso
 */

package States;

//Imports
import PokemonGame.Assets;
import PokemonGame.Display;
import PokemonGame.Game;

import java.awt.*;

public class PostChampionBattle extends State {
    //variable initialization
    private String winLoseString, winLoseString2, levelUpString, timePlayedString;
    private long timePlayed;
    private int width, height, exitButtonX, exitButtonY, continueBtnX, continueBtnY, mouseX, mouseY, levelUp;
    private int timeChecked;

    public PostChampionBattle(Game game, Display display){
        super(game, display);
        //Sets all the strings so they are blank, if not given a value an error occurs
        winLoseString = "";
        winLoseString2 = "";
        State.setExperienceString("");
        State.setExperienceString2("");
        levelUpString = "";
        timePlayedString = "";
        timeChecked = 0;

        //Sets the width and height for the buttons
        width = 300;
        height = 150;

        //Sets the x and y positions for the exit and continue button
        continueBtnX = game.getWidth()/ 2 - width / 2;
        continueBtnY = game.getHeight() - height- 50;
        exitButtonX = game.getWidth() - 2 * width - 100;
        exitButtonY = game.getHeight() - height- 50;

        //Sets the amount needed to level up (the player's level cubed)
        levelUp = game.getPokemon().getPlayerLevel() * game.getPokemon().getPlayerLevel() * game.getPokemon().getPlayerLevel();
    }

    //Update method
    public void update() {
        //Checks to see if timeChecked is < 10; must have this if statement or time doesn't show up right
        if(timeChecked < 10){
            timePlayed = game.getTimePlayed();      //Gets the value of the time played
            timeChecked++;      //Add one to timeChecked
        }

        //Check to see if the user one the battle
        if (State.isWin() == true) {
            //Sets the Strings to if you have beat the champion pokemon
            winLoseString = "You Beat the Champion, you are now a Pokemon Master";
            winLoseString2 = "Pressed Continue to Keep Playing or Exit to End the Game";
            timePlayedString = "You Defeated the champion and Beat the Game in " + timePlayed / 1000 + " Seconds";      //Displays how long it took you to beat the champion the first time
        }
        else {
            //Sets the winLoseString to a loss against the champion
            winLoseString = "You Lost to the Champion";
        }

        //Checks to see if the total experience you have is enough to level up your pokemon
        if(State.getTotalExperience() >= levelUp){
            game.getPokemon().setPlayerLevel(game.getPokemon().getPlayerLevel() + 1);       //Adds one to the player pokemon level
            levelUp = game.getPokemon().getPlayerLevel() * game.getPokemon().getPlayerLevel() * game.getPokemon().getPlayerLevel();     //Changes the amount needed to level up in respect to the player's new level
            levelUpString = "Your " + game.getPokemon().getPlayerPokemon()[0] + " has leveled up. It is now at level " + game.getPokemon().getPlayerLevel();        //Changes the levelUpString from a blank string to tell the user they leveled up
            Assets.sfxLevelUp();    //Plays levelUp sfx
        }

        //Gets the x and y position of the mouse
        mouseX = game.getMouseInput().getMouseX();
        mouseY = game.getMouseInput().getMouseY();

        //Checks to see if the boolean leftPressed is true
        if(game.getMouseInput().isLeftPressed()){
            if (State.isWin() == true) {
                //Sets an area on the canvas that when clicked it acts as an exit button
                if (mouseX >= exitButtonX && mouseX <= exitButtonX + width) {
                    if (mouseY >= exitButtonY && mouseY <= exitButtonY + height) {
                        //Plays the click sfx then exits the game
                        Assets.sfxClick();
                        System.exit(0);
                    }
                }
            }
            //Sets an area on the canvas that when clicked it acts as an continue Button
            if (mouseX >= continueBtnX && mouseX <= continueBtnX + width) {
                if (mouseY >= continueBtnY && mouseY <= continueBtnY + height) {
                    //Plays the click sfx then changes the state to the gameState
                    Assets.sfxClick();
                    State.setState(game.gameState);
                    levelUpString = "";     //Sets the levelUpString to be blank
                }
            }
            //Sets the leftPressed boolean to false
            game.getMouseInput().setLeftPressed(false);
        }
    }

    //The render method
    public void render(Graphics g){
        //Sets the colour to light gray and makes a rectangle for the background
        g.setColor(Color.lightGray);
        g.fillRect(0,0,game.getWidth(),game.getHeight());

        //Draw the continue button at the same location as the area that checks for a click
        g.drawImage(Assets.continueButton, continueBtnX, continueBtnY, width, height, null);

        //Changes the colour to dark gray and changes the font
        g.setFont(new Font("Monospaced", Font.BOLD, 60));
        g.setColor(Color.darkGray);

        //Checks to see if the player won the battle
        if (State.isWin() == true) {
            //Draws the exit button to close the game and the second string displaying information about winnig
            g.drawImage(Assets.exitBtn, exitButtonX, exitButtonY, width, height, null);
            g.drawString(winLoseString2, 50, 200);

            //Changes the font then draws the timePlayedString to the canvas
            g.setFont(new Font("Monospaced", Font.BOLD, 48));
            g.drawString(timePlayedString, 50, 300);
        }

        //Changes the font and draws the first winLoseString to the canvas
        g.setFont(new Font("Monospaced", Font.BOLD, 58));
        g.drawString(winLoseString, 50, 100);

        //Draws your experience gained, total experience and the levelUpString to the canvas
        g.drawString(State.getExperienceString(), 50, 500);
        g.drawString(State.getExperienceString2(), 50, 600);
        g.drawString(levelUpString, 50, 700);
    }
}
