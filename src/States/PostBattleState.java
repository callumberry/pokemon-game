/**
 * PostBattleState.java
 * Purpose: This State is used after the user wins or loses
 *          against a wild pokemon. Different information will
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

public class PostBattleState extends State{
    //Variable initialization
    private String winLoseRunString, levelUpString;
    private int width, height, okButtonX, okButtonY, mouseX, mouseY, levelUp;

    public PostBattleState(Game game, Display display){
        super(game, display);
        //Sets all the strings so they are blank, if not given a value an error occurs
        winLoseRunString = "";
        State.setExperienceString("");
        State.setExperienceString2("");
        levelUpString = "";

        //Sets the width and height for the buttons
        width = 300;
        height = 150;

        //Sets the x and y positions for the ok button
        okButtonX = game.getWidth() - 2 * width - 100;
        okButtonY = game.getHeight() - height- 50;

        //Sets the amount needed to level up (the player's level cubed)
        levelUp = game.getPokemon().getPlayerLevel() * game.getPokemon().getPlayerLevel() * game.getPokemon().getPlayerLevel();
    }

    //Update method
    public void update() {
        //Checks to see if the player ran away
        if(State.isRunAway() == true) {
            winLoseRunString = "You Got Away Safely";       //Changes winLoseString accordingly to if statement
        }
        else {
            //Checks to see if the player won
            if (State.isWin() == true) {
                winLoseRunString = "The Wild Pokemon Has Fainted";     //Changes winLoseString accordingly to if statement
            } else {  //Checks to see if the player lost
                winLoseRunString = "You Ran Out Of Usable Pokemon";     //Changes winLoseString accordingly to else statement
            }
        }

        //Checks to see if the total experience you have is enough to level up your pokemon
        if(State.getTotalExperience() >= levelUp){
            game.getPokemon().setPlayerLevel(game.getPokemon().getPlayerLevel() + 1);    //Adds one to the player pokemon level
            levelUp = game.getPokemon().getPlayerLevel() * game.getPokemon().getPlayerLevel() * game.getPokemon().getPlayerLevel(); //Changes the amount needed to level up in respect to the player's new level
            levelUpString = "Your " + game.getPokemon().getPlayerPokemon()[0] + " has leveled up. It is now at level " + game.getPokemon().getPlayerLevel();    //Changes the levelUpString from a blank string to tell the user they leveled up
            Assets.sfxLevelUp();     //Plays levelUp sfx
        }

        //Gets the x and y position of the mouse
        mouseX = game.getMouseInput().getMouseX();
        mouseY = game.getMouseInput().getMouseY();

        //Checks to see if the boolean leftPressed is true
        if(game.getMouseInput().isLeftPressed()){
            //Sets an area on the canvas that when clicked it acts as an ok Button
            if (mouseX >= okButtonX && mouseX <= okButtonX + width) {
                if (mouseY >= okButtonY && mouseY <= okButtonY + height) {
                    //Plays the click sfx then changes the state to the gameState
                    Assets.sfxClick();
                    State.setState(game.gameState);

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

        //Draws the ok btn at the same location where it checks for a mouse click
        g.drawImage(Assets.okButton, okButtonX, okButtonY, width, height, null);

        //Changes the font and colour to dark gray
        g.setFont(new Font("Monospaced", Font.BOLD, 58));
        g.setColor(Color.darkGray);

        //Draws the winLoseString, your experience gained, total experience, and the levelUpString to the canvas
        g.drawString(winLoseRunString, 50, 100);
        g.drawString(State.getExperienceString(), 50, 300);
        g.drawString(State.getExperienceString2(), 50, 400);
        g.drawString(levelUpString, 50, 600);
    }
}
