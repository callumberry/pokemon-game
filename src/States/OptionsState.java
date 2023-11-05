/**
 * OptionsState.java
 * Purpose: This is one of the options states, it is
 *          access by a button and allow you to change
 *          and or stop the music
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

public class OptionsState extends State{
    //Initializes the variables
    private int mouseX, mouseY, width, height, doneBtnX, doneBtnY, stopBtnX, stopBtnY,track1And3X, track2And4X, track1And2Y, track3And4Y;

    public OptionsState(Game game, Display display){
        super(game, display);

        //Sets the width and height of the buttons
        width = 300;
        height = 150;

        //Sets the x and y position for the done, stop, and track buttons
        doneBtnX = (game.getWidth() - width) - 50;
        doneBtnY = 50;
        track1And3X = game.getWidth() / 2 - width - 25;
        track2And4X = game.getWidth() / 2 + 25;
        track1And2Y = 250;
        track3And4Y = 450;
        stopBtnX = game.getWidth() / 2 - width / 2;
        stopBtnY = 700;
    }

    //Update method
    public void update() {
        //Checks if the escape key is pressed
        if(game.getKeyManager().escapeKey) {
            game.getMouseInput().setLeftPressed(false);     //Sets leftPressed to false
            State.setState(game.menuState);     //Switches to the menuState
        }

        //Gets the x and y position of the mouse
        mouseX = game.getMouseInput().getMouseX();
        mouseY = game.getMouseInput().getMouseY();

        //Checks to see if the left mouse button was clicked
        if(game.getMouseInput().isLeftPressed()){
            //Checks to see if the area of the done button is clicked
            if(mouseX >= doneBtnX && mouseX <= doneBtnX + width){
                if(mouseY >= doneBtnY && mouseY <= doneBtnY + height){
                    Assets.sfxClick();      //Plays click sound
                    game.getMouseInput().setLeftPressed(false);     //Sets leftPressed to false
                    State.setState(game.menuState);     //Switches to the menuState
                }
            }

            //Checks to see if the area of the stop button is clicked
            if(mouseX >= stopBtnX && mouseX <= stopBtnX + width){
                if(mouseY >= stopBtnY && mouseY <= stopBtnY + height){
                    //Checks if a track is playing
                    if(State.isIsPlaying()){
                        //Plays click sfx, stops the track that's playing, sets isPlaying to false
                        Assets.sfxClick();
                        Assets.stopTracks();
                        State.setIsPlaying(false);
                    }
                }
            }

            //Checks to see if the area of the track 1 button is clicked
            if(mouseX >= track1And3X && mouseX <= track1And3X + width){
                if(mouseY >= track1And2Y && mouseY <= track1And2Y + height){
                    Assets.sfxClick();      //Plays click sfx
                    //Check to see if something is playing, if so it stops it
                    if(State.isIsPlaying()){
                        Assets.stopTracks();
                    }
                    //Sets isPlaying to true and starts the desired track
                    State.setIsPlaying(true);
                    Assets.track1();
                }
            }
            //Checks to see if the area of the track 2 button is clicked
            if(mouseX >= track2And4X && mouseX <= track2And4X + width){
                if(mouseY >= track1And2Y && mouseY <= track1And2Y + height){
                    Assets.sfxClick();//Plays click sfx
                    //Check to see if something is playing, if so it stops it
                    if(State.isIsPlaying()){
                        Assets.stopTracks();
                    }
                    //Sets isPlaying to true and starts the desired track
                    State.setIsPlaying(true);
                    Assets.track2();
                }
            }
            //Checks to see if the area of the track 3 button is clicked
            if(mouseX >= track1And3X && mouseX <= track1And3X + width){
                if(mouseY >= track3And4Y && mouseY <= track3And4Y + height){
                    Assets.sfxClick();//Plays click sfx
                    //Check to see if something is playing, if so it stops it
                    if(State.isIsPlaying()){
                        Assets.stopTracks();
                    }
                    //Sets isPlaying to true and starts the desired track
                    State.setIsPlaying(true);
                    Assets.track3();
                }
            }
            //Checks to see if the area of the track 4 button is clicked
            if(mouseX >= track2And4X && mouseX <= track2And4X + width){
                if(mouseY >= track3And4Y && mouseY <= track3And4Y + height){
                    Assets.sfxClick();//Plays click sfx
                    //Check to see if something is playing, if so it stops it
                    if(State.isIsPlaying()){
                        Assets.stopTracks();
                    }
                    //Sets isPlaying to true and starts the desired track
                    State.setIsPlaying(true);
                    Assets.track4();
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

        //Draws the images of the buttons where each corresponding area is laid out for that button
        g.drawImage(Assets.doneBtn, doneBtnX, doneBtnY, width, height, null);
        g.drawImage(Assets.track1Btn, track1And3X, track1And2Y, width, height, null);
        g.drawImage(Assets.track2Btn, track2And4X, track1And2Y, width, height, null);
        g.drawImage(Assets.track3Btn, track1And3X, track3And4Y, width, height, null);
        g.drawImage(Assets.track4Btn, track2And4X, track3And4Y, width, height, null);
        g.drawImage(Assets.stopBtn, stopBtnX, stopBtnY, width, height, null);
    }
}

