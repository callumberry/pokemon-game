/**
 * StarterSelectionState.java
 * Purpose: This state is run only once and it's run after the
 *          tutorialState. This state allows the user to pick
 *          a starter pokemon then bring the user to the
 *          gameState
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

public class StarterSelectionState extends State {
    //Initializes the variables
    private int width, height, mouseX, mouseY, btn1X, btn2X, btn3X, btnY;

    //Constructor for the StarterSelectionState class that takes in a game and a display
    public StarterSelectionState(Game game, Display display) {
        super(game, display);
        //Sets the value of the width and height of the button
        width = 300;
        height = 150;

        //Sets the x position of the button
        btn1X = game.getWidth()/5 - width / 2;
        btn2X = game.getWidth()/2 - width / 2;
        btn3X = 4 * game.getWidth()/5 - width / 2;

        //Sets they position of the buttons
        btnY = 740;
    }

    //Overrides the update method from the State class
    @Override
    public void update() {
        //Gets the x and y position of the mouse and sets it to a variable
        mouseX = game.getMouseInput().getMouseX();
        mouseY = game.getMouseInput().getMouseY();

        //Checks to see if the the boolean leftPressed is true
        if (game.getMouseInput().isLeftPressed()){
            //Checks to see if the user has clicked in an area, this one is specifically for Charmander
            if(mouseX >= btn1X && mouseX <= btn1X + width){
                if(mouseY >= btnY && mouseY <= btnY + height){
                    Assets.sfxClick();      //Plays the click sfx
                    game.getPokemon().setPlayerPokemon(game.getPokemon().getCharmander());      //Sets the playerPokemon to Charmander
                    game.getPokemon().playerStatCalc();         //Runs the method that gets Charmander's stats
                    State.setCurrentPlayerHP(game.getPokemon().getPlayerHPStat());      //Sets the currentHP variable to the HP stat of the player for Charmander
                    State.setState(game.gameState);     //Switches the state to the gameState
                }
            }

            //Checks to see if the user has clicked in an area, this one is specifically for Bulbasaur
            if(mouseX >= btn2X && mouseX <= btn2X + width){
                if(mouseY >= btnY && mouseY <= btnY + height){
                    Assets.sfxClick();      //Plays the click sfx
                    game.getPokemon().setPlayerPokemon(game.getPokemon().getBulbasaur());        //Sets the playerPokemon to Bulbasaur
                    game.getPokemon().playerStatCalc();        //Runs the method that gets Bulbasaur's stats
                    State.setCurrentPlayerHP(game.getPokemon().getPlayerHPStat());         //Sets the currentHP variable to the HP stat of the player for Bulbasaur
                    State.setState(game.gameState);     //Switches the state to the gameState
                }
            }

            //Checks to see if the user has clicked in an area, this one is specifically for Squirtle
            if(mouseX >= btn3X && mouseX <= btn3X + width){
                if(mouseY >= btnY && mouseY <= btnY + height){
                    Assets.sfxClick();       //Plays the click sfx
                    game.getPokemon().setPlayerPokemon(game.getPokemon().getSquirtle());           //Sets the playerPokemon to Squirtle
                    game.getPokemon().playerStatCalc();      //Runs the method that gets Squirtle's stats
                    State.setCurrentPlayerHP(game.getPokemon().getPlayerHPStat());      //Sets the currentHP variable to the HP stat of the player for Squirtle
                    State.setState(game.gameState);         //Switches the state to the gameState
                }
            }
            MenuState.tutorialComplete = true;          //Sets the tutorialComplete boolean to true; this makes it so this state and the tutorialState is only run once
            game.getMouseInput().setLeftPressed(false);     //Sets the boolean leftPressed to false
        }

    }

    //Overrides the render method from the State class
    @Override
    public void render(Graphics g) {
        //Sets the colour to light gray and fills a rectangle as the background
        g.setColor(Color.lightGray);
        g.fillRect(0,0,game.getWidth(),game.getHeight());

        //Changes the colour to black and changes the font
        g.setColor(Color.black);
        g.setFont(new Font("Monospaced", Font.ITALIC | Font.BOLD, 55));

        //Draws strings to the canvas at a chosen x and y position
        g.drawString("CHOOSE A STARTER POKEMON", game.getWidth() /2 - 400, 100);

        //Draws the images of the 3 starter pokemon on the canvas
        g.drawImage(Assets.bulbasaur, game.getWidth()/2 - width / 2, 200, 300, 300, null);
        g.drawImage(Assets.charmander, game.getWidth()/5 - width / 2, 200, 300, 300, null);
        g.drawImage(Assets.squirtle, 4 * game.getWidth()/5 - width / 2, 200, 300, 300, null);

        //Changes the colour and font
        g.setColor(Color.darkGray);
        g.setFont(new Font("Monospaced", Font.BOLD, 45));

        //Draws the strings with the pokemon names
        g.drawString("BULBASAUR", game.getWidth()/2 - width / 2, 550);
        g.drawString("CHARMANDER", game.getWidth()/5 - width / 2 + 50, 550);
        g.drawString("SQUIRTLE", 4 * game.getWidth()/5 - width / 2 + 50, 550);

        //Changes the font and colour
        g.setFont(new Font("Monospaced", Font.BOLD, 30));
        g.setColor(Color.gray);

        //Draws strings to the canvas with a quick description of each pokemon
        g.drawString("Charmander is stronger in ", game.getWidth()/5 - width / 2 - 100, 630);
        g.drawString("Attack, but weaker in Defense.", game.getWidth()/5 - width / 2 - 100, 675);
        g.drawString("Bulbasaur is a well rounded ", game.getWidth()/2 - width / 2 - 100, 630);
        g.drawString("Pokemon, with a balance in", game.getWidth()/2 - width / 2 - 100, 675);
        g.drawString("it's strengths and weaknesses. ", game.getWidth()/2 - width / 2 - 100, 720);
        g.drawString("Squirtle is stronger in ", 4 * game.getWidth()/5 - width / 2 - 100, 630);
        g.drawString("Defense, but weaker in Attack.", 4 * game.getWidth()/5 - width / 2 - 100, 675);

        //Draws the select buttons for each pokemon
        g.drawImage(Assets.selectBtn, btn1X, btnY, width, height, null);
        g.drawImage(Assets.selectBtn, btn2X, btnY, width, height, null);
        g.drawImage(Assets.selectBtn, btn3X, btnY, width, height, null);
    }
}
