/**
 * MenuState.java
 * Purpose: This is te state you see when you first run the game
 *          has access to an exit, options, and play button.
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

public class MenuState extends State{
    //Initialization of the variables
    private int width, height, mouseX, mouseY,
            playBtnX, playBtnY, optionsBtnX, optionsBtnY, exitBtnX, exitBtnY;
    public static boolean tutorialComplete = false;     //Sets tutorialComplete to false

    //Constructor that takes in a game and display object
    public MenuState(Game game, Display display){
        super(game, display);

        //Sets the width and height of the buttons
        width = 300;
        height = 150;

        //Sets the x and y coordinates for the buttons
        playBtnX = game.getWidth() / 2 - width / 2;
        playBtnY = 450;
        optionsBtnX = game.getWidth() / 2 - width / 2;
        optionsBtnY = 650;
        exitBtnX = game.getWidth() / 2 - width / 2;
        exitBtnY = 850;
    }

    //Update Method
    public void update() {
        //Gets the x and y position of the mouse
        mouseX = game.getMouseInput().getMouseX();
        mouseY = game.getMouseInput().getMouseY();

        //Checks to see if leftPressed is true
        if(game.getMouseInput().isLeftPressed()){
            //Checks to see if the area of the play button is clicked
          if(mouseX >= playBtnX && mouseX <= playBtnX + width){
              if(mouseY >= playBtnY && mouseY <= playBtnY + height){
                  //Checks to see if the tutorialComplete boolean equals false
                  if(tutorialComplete == false){
                      Assets.sfxClick();    //Plays click sfx
                      State.setState(game.tutorialState);   //Sets the state to the tutorialState
                  }
                  else{
                      Assets.sfxClick(); //Plays click sfx
                      State.setState(game.gameState); //Sets the state to the gameState
                  }
              }
          }
          //Checks to see if the area of the options button is clicked
          if(mouseX >= optionsBtnX && mouseX <= optionsBtnX + width){
                if(mouseY >= optionsBtnY && mouseY <= optionsBtnY + height){
                    Assets.sfxClick(); //Plays click sfx
                    State.setState(game.optionsState); //Sets the state to the optionsState
                }
          }
          //Checks to see if the area of the exit button is clicked
          if(mouseX >= exitBtnX && mouseX <= exitBtnX + width){
              if(mouseY >= exitBtnY && mouseY <= exitBtnY + height){
                  Assets.sfxClick(); //Plays click sfx
                  System.exit(0);   //Closes the program
              }
          }
          //Sets leftPressed to false
          game.getMouseInput().setLeftPressed(false);
        }
    }

    //Render method
     public void render(Graphics g){
        //Sets background to light gray
        g.setColor(Color.lightGray);
        g.fillRect(0,0,game.getWidth(),game.getHeight());

        //Draws the image of the pokemon logo to the canvas
        g.drawImage(Assets.pokemonLogo, game.getWidth() / 2 - 1000 / 2, 50, 1000, 368, null);

        //Draws the image of the buttons to the canvas
        g.drawImage(Assets.playBtn, playBtnX, playBtnY, width, height, null);
        g.drawImage(Assets.optionsBtn, optionsBtnX, optionsBtnY, width, height, null);
        g.drawImage(Assets.exitBtn, exitBtnX, exitBtnY, width, height, null);
    }
}
