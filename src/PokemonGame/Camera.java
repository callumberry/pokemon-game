/**
 * Camera.java
 * Purpose: This class contains all code concerning the camera,
 *          including it's size, position, movement,
 *          and bounds.
 * Author: Callum Berry
 * Version: 1.0
 * Target: Mr. DiTomasso
 */

package PokemonGame;

//Imports
import Entities.Player;
import Tiles.Tile;

public class Camera {

    private Game game; //Creates objects of the game and the map to be used in the Camera sizing
    private Map map;
    private float xOffset, yOffset; //Create floats of the x and y offset

    public Camera(Game game, float xOffset, float yOffset){
        //Define the game, and x and y offset objects
        this.game = game;
        this.xOffset = xOffset;
        this.yOffset = yOffset;

        map = new Map(game); //Define the map object
    }

    public void blankSpaceCheck(){
        if(xOffset <  0){
            xOffset = 0; //Reset the xOffset variable
        }
        else if(xOffset > map.getWidth() * Tile.tileWidth - game.getWidth()){
            xOffset = map.getWidth() * Tile.tileWidth - game.getWidth(); //Create the proper xOffsets for the Camera
        }

        if(yOffset < 0){
            yOffset = 0; //Reset the yOffset variable
        }
        else if(yOffset > map.getHeight() * Tile.tileHeight - game.getHeight()){
            yOffset = map.getHeight() * Tile.tileHeight - game.getHeight(); //Create the proper yOffsets for the Camera
        }
    }

    public void centerOnPlayer(Player e){
        xOffset = e.getX() - game.getWidth() / 2 + 30; //Center the camera on the player
        yOffset = e.getY() - game.getHeight() / 2 - 7;

        blankSpaceCheck(); //Run the BlankSpaceCheck Code
    }

    public float getxOffset() {
        return xOffset;
    } //Getter for the xOffset Variable

    public float getyOffset() {
        return yOffset;
    } //Getter for the yOffset Variable
}
