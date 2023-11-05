/**
 * TutorialState.java
 * Purpose: This state is run only once when you press the play button
 *          it gives you a small explanation of the game including the
 *          controls. Once you press the okay button it brings  the user
 *          to the starterSelectionState.
 * Author:  Owen Gallagher
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

public class TutorialState extends State {
    //Initializes the variables
    private int width, height, mouseX, mouseY, btnX, btnY;

    //Constructor for the TutorialState class that takes in a game and a display
    public TutorialState(Game game, Display display) {
        super(game, display);
        //Sets the value of the width and height of the button
        width = 300;
        height = 150;

        //Sets the x and y position of the button
        btnX = 820;
        btnY = game.getHeight()-170;
    }

    //Update method that overrides what's in the State class
    @Override
    public void update() {
        //Gets the x and y position of the mouse and sets it to a variable
        mouseX = game.getMouseInput().getMouseX();
        mouseY = game.getMouseInput().getMouseY();

        //Checks to see if the the boolean leftPressed is true
        if (game.getMouseInput().isLeftPressed()){
            //Checks to see if the user has clicked in an area and acts like a button
            if(mouseX >= btnX && mouseX <= btnX + width){
                if(mouseY >= btnY && mouseY <= btnY + height){
                    //Plays the click sfx and changes the state to the starterSelectionState
                    Assets.sfxClick();
                    State.setState(game.starterSelectionState);
                }
            }
            //Sets the leftPressed boolean to false
            game.getMouseInput().setLeftPressed(false);
        }
    }

    //Overrides the render method from the State class
    @Override
    public void render(Graphics g) {
        //Sets the colour to light gray and fills a rectangle as the background
        g.setColor(Color.lightGray);
        g.fillRect(0,0,game.getWidth(),game.getHeight());

        //Changes the colour to dark gray and changes the font
        g.setColor(Color.darkGray);
        g.setFont(new Font("Monospaced", Font.BOLD, 30));

        //Draws strings to the canvas at a chosen x and y position
        g.drawString("Welcome to POKEMON! Your goal in this game is to become the Pokemon Master. To do this, you must", 50, 100);
        g.drawString("level up your Pokemon until it is strong enough to face the Champion in CHAMPION CITY. You can level", 50, 150);
        g.drawString("up your Pokemon by beating Wild Pokemon, found in tall grass. You will be scored on you XP and your ", 50, 200);
        g.drawString("time, so play as quickly as you can! Good Luck!", 50, 250);

        //Draws the image of the character sprite onto the canvas
        g.drawImage(Assets.characterSprite, 4 * game.getWidth() / 5, game.getHeight() / 4, Assets.characterSprite.getWidth() * 16,Assets.characterSprite.getHeight() * 16, null);

        //Changes the font and colour
        g.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 80));
        g.setColor(Color.black);

        //Draws a string to the canvas at a chosen x and y position
        g.drawString("HOW TO PLAY", 400, 450);

        //Changes the colour of the colour and the font
        g.setColor(Color.darkGray);
        g.setFont(new Font("Monospaced", Font.BOLD, 50));

        //Draws strings to the canvas at a chosen x and y position
        g.drawString("Use the WASD Keys to move.",300 , 550);
        g.drawString("Hold SHIFT to sprint.",300 , 620);
        g.drawString("Find Wild Pokemon in Tall Grass.",300 , 690);
        g.drawString("To heal a damaged Pokemon, visit the Pokemon Center.",300 , 760);
        g.drawString("To pull up the menu, press the R key.", 300, 830);

        //Draws the ok button at the same position that the program is checking for a left press
        g.drawImage(Assets.okButton, btnX, btnY, width, height, null);
    }
}
