/**
 * HealState.java
 * Purpose: This state occurs when you walk up to
 *          the pokeCenters door; it heals your
 *          pokemon to max health
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
import java.awt.image.BufferedImage;

public class HealState extends State {
    //Initializing the variables
    private int width, height, mouseX, mouseY, buttonX, buttonY;
    private boolean healNoise;
    private BufferedImage playerPokemonSprite;

    //Constructor that takes in a game and display object
    public HealState(Game game, Display display) {
        super(game, display);

        //Sets the width and height
        width = 300;
        height = 150;

        //Sets the button's x and y position
        buttonX = game.getWidth() / 2 - width / 2;
        buttonY = game.getHeight() - height - 50;

        //Sets healNoise to false
        healNoise = false;

        //Sets the playerSprite to a value from the Pokemon class that stores the pokemon image
        playerPokemonSprite = (BufferedImage) game.getPokemon().getPlayerPokemon()[1];
    }

    //Update Method
    @Override
    public void update() {
        //Sets the playerSprite to a value from the Pokemon class that stores the pokemon image
        playerPokemonSprite = (BufferedImage) game.getPokemon().getPlayerPokemon()[1];

        //Gets the x and y position of the mouse
        mouseX = game.getMouseInput().getMouseX();
        mouseY = game.getMouseInput().getMouseY();

        //Checks if healNoise is equal to false
        if (healNoise == false) {
            Assets.sfxPokeCenter(); //Plays pokeCenter sfx
            healNoise = true;       //Sets healNoise to true
        }

        //Sets the player HP to the max HP of the specific pokemon being healed
        State.setCurrentPlayerHP(game.getPokemon().getPlayerHPStat());

        //Checks if leftPressed is true
        if (game.getMouseInput().isLeftPressed()){
            //Checks area for leftClick for ok button
            if(mouseX >= buttonX && mouseX <= buttonX + width){
                if(mouseY >= buttonY && mouseY <= buttonY + height){
                    Assets.sfxClick();      //Plays click sfx
                    healNoise = false;      //Sets healNoise to false
                    State.setState(game.gameState);     //Switches the state to the gameState
                }
            }
            //Sets leftPressed to false
            game.getMouseInput().setLeftPressed(false);
        }
    }

    //Render Method
    @Override
    public void render(Graphics g) {
        //Sets the background colour to light gray by drawing a rectangle
        g.setColor(Color.lightGray);
        g.fillRect(0,0,game.getWidth(),game.getHeight());

        //Sets the colour to dar gray and changes the font
        g.setColor(Color.darkGray);
        g.setFont(new Font("Monospaced", Font.BOLD, 80));

        //Draws a string informing the user that their pokemon is fully healed
        g.drawString("Your " + game.getPokemon().getPlayerPokemon()[0] + " is fully healed!", 225, 350);

        //Draws the current player pokemon on the canvas
        g.drawImage(playerPokemonSprite, game.getWidth() / 2 - playerPokemonSprite.getWidth() * 8 / 2, 450, playerPokemonSprite.getWidth() * 8, playerPokemonSprite.getHeight() * 8, null);

        //Draws the ok button so you can leave the healState
        g.drawImage(Assets.okButton, buttonX, buttonY, width, height, null);
    }
}
