/**
 * Player.java
 * Purpose: This class contains all code concerning the player,
 *          including movement, collision, and how it interacts
 *          with objects like doors and tall grass.
 * Author: Owen Gallagher
 * Version: 1.0
 * Target: Mr. DiTomasso
 */

package Entities;

//Imports
import PokemonGame.Animation;
import PokemonGame.Assets;
import PokemonGame.Game;
import PokemonGame.Map;
import States.State;
import Tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Player {

    private Game game; //Create the game object
    private Map map; //Create the map object
    private float x, y, speed; //Create ints necessary for the movement of the player
    private float xMove, yMove;
    private Rectangle bounds; //Create the rectangle that acts as the collision boundary for the player

    //Create animations for the 5 states in which the player can be moving
    private Animation animDown;
    private Animation animUp;
    private Animation animLeft;
    private Animation animRight;
    private Animation animNeutral;

    public Player(Game game, int x, int y) {
        //Get the game, and x and y positions of the player
        this.game = game;
        this.x = x;
        this.y = y;

        map = new Map(game); //Define the map the player will be put into

        bounds = new Rectangle(); //Define the bounds of the collision box around the player
        bounds.x = 8;
        bounds.y = 0;
        bounds.width = 42;
        bounds.height = 34;

        //Define which set of animations that each animation state will use
        animDown = new Animation(500, Assets.player_down);
        animUp = new Animation(500, Assets.player_up);
        animLeft = new Animation(500, Assets.player_left);
        animRight = new Animation(500, Assets.player_right);
        animNeutral = new Animation(500, Assets.player_neutral);
    }

    public void update() {
        //Update each of the animation classes to get the current animation
        animDown.tick();
        animUp.tick();
        animLeft.tick();
        animRight.tick();
        animNeutral.tick();

        move(); //Run the move function
        game.getCamera().centerOnPlayer(this); //Center the camera on the player
    }

    private void move(){
        //Reset the xMove and yMove variables (will be modified based on button inputs)
        xMove = 0;
        yMove = 0;

        //Checks if the player is holding shift, their speed will be higher
        if(game.getKeyManager().running){
            speed = 7;
        }
        //If not the player moves at normal speed
        else{
            speed = 5;
        }

        //Finds which direction the player wants to move, and then modifies the corresponding xMove / yMove variable accordingly
        if(game.getKeyManager().up){
            yMove = -speed;
        }
        if(game.getKeyManager().down){
            yMove = speed;
        }
        if(game.getKeyManager().left){
            xMove = -speed;
        }
        if(game.getKeyManager().right){
            xMove = speed;
        }

        moveX(); //Runs the movement class for X
        moveY(); //Runs the movement class for Y
    }

    public void moveX(){
        if(xMove > 0){      //Moving Right Code
            int tx = (int)(x + xMove + bounds.x + bounds.width) / Tile.tileWidth; //Creates a temp variable that contains the player's position

            //Checks to see if player is in the tall grass
            if (collisionWithGrass(tx, (int) (y + bounds.y) / Tile.tileHeight) ||
                    (collisionWithGrass(tx, (int) (y + bounds.y + bounds.height) / Tile.tileHeight))) {
                grassInteract(); //If the player collides with the grass, run the code that contains the random Pokemon encounter
            }

            //Checks to see if the player is approaching the PokeCenter doors
            if (collisionWithPokeCenter(tx, (int) (y + bounds.y) / Tile.tileHeight) ||
                    (collisionWithPokeCenter(tx, (int) (y + bounds.y + bounds.height) / Tile.tileHeight))) {
                game.getMouseInput().setLeftPressed(false); //Sets leftPressed to false
                State.setState(game.healState); //If the player has collided with the Pokecenter doors, then set the state to healState
            }

            //Checks to see if the player is approaching the Champion Building doors
            if (collisionWithChampion(tx, (int) (y + bounds.y) / Tile.tileHeight) ||
                    (collisionWithChampion(tx, (int) (y + bounds.y + bounds.height) / Tile.tileHeight))) {
                if(!(State.getCurrentPlayerHP() <= 0)) { //Checks if the player has HP left. If so, it loads the finalBattleState
                    game.getMouseInput().setLeftPressed(false); //Sets leftPressed to false
                   State.setState(game.finalBattleState);       //Sets the state to the finalBattleState
                }
            }

            //Checks if the player is about to collide with a solid tile
            if(!collisionWithTile(tx, (int)(y + bounds.y) / Tile.tileHeight) &&
                    (!collisionWithTile(tx, (int)(y + bounds.y + bounds.height) / Tile.tileHeight))){ //If the player is not coliding with a solid tile, let them move freely
                x += xMove;
            }
            //If the player is colliding with a solid tile, make their position 1 pixel outside the solid tile
            else{
                x = tx * Tile.tileWidth - bounds.x - bounds.width - 1;
            }


        }
        else if (xMove < 0) {     //Moving Left
            int tx = (int) (x + xMove + bounds.x) / Tile.tileWidth; //Creates a temp variable that contains the player's position

            //Checks to see if player is in the tall grass
            if (collisionWithGrass(tx, (int) (y + bounds.y) / Tile.tileHeight) ||
                    (collisionWithGrass(tx, (int) (y + bounds.y + bounds.height) / Tile.tileHeight))) {
                grassInteract(); //If the player collides with the grass, run the code that contains the random Pokemon encounter
            }

            //Checks to see if the player is approaching the PokeCenter doors
            if (collisionWithPokeCenter(tx, (int) (y + bounds.y) / Tile.tileHeight) ||
                    (collisionWithPokeCenter(tx, (int) (y + bounds.y + bounds.height) / Tile.tileHeight))) {
                game.getMouseInput().setLeftPressed(false); //Sets leftPressed to false
                State.setState(game.healState); //If the player has collided with the Pokecenter doors, then set the state to healState
            }

            //Checks to see if the player is approaching the Champion Building doors
            if (collisionWithChampion(tx, (int) (y + bounds.y) / Tile.tileHeight) ||
                    (collisionWithChampion(tx, (int) (y + bounds.y + bounds.height) / Tile.tileHeight))) {
                if(!(State.getCurrentPlayerHP() <= 0)) { //Checks if the player has HP left. If so, it loads the finalBattleState
                    game.getMouseInput().setLeftPressed(false); //Sets leftPressed to false
                    State.setState(game.finalBattleState);  //Sets the state to the finalBattleState
                }
            }

            //Checks if the player is about to collide with a solid tile
            if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.tileHeight) &&
                    (!collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.tileHeight))) { //If the player is not colliding with a solid tile, let them move freely
                x += xMove;
            }
            //If the player is colliding with a solid tile, make their position 1 pixel outside the solid tile
            else{
                x = tx * Tile.tileWidth + Tile.tileWidth - bounds.x;
            }

        }
    }
    public  void moveY(){
        if(yMove < 0){//Up
            int ty = (int) (y + yMove + bounds.y) / Tile.tileHeight; //Creates a temp variable that contains the player's position

            //Checks to see if player is in the tall grass
            if(collisionWithGrass((int) (x + bounds.x) / Tile.tileWidth, ty) ||
                    collisionWithGrass((int) (x + bounds.x + bounds.width) / Tile.tileWidth, ty)){
                grassInteract(); //If the player collides with the grass, run the code that contains the random Pokemon encounter
            }

            //Checks to see if the player is approaching the PokeCenter doors
            if(collisionWithPokeCenter((int) (x + bounds.x) / Tile.tileWidth, ty) ||
                    collisionWithPokeCenter((int) (x + bounds.x + bounds.width) / Tile.tileWidth, ty)){
                game.getMouseInput().setLeftPressed(false); //Sets leftPressed to false
                State.setState(game.healState); //If the player has collided with the Pokecenter doors, then set the state to healState
            }

            //Checks to see if the player is approaching the Champion Building doors
            if(collisionWithChampion((int) (x + bounds.x) / Tile.tileWidth, ty) ||
                    collisionWithChampion((int) (x + bounds.x + bounds.width) / Tile.tileWidth, ty)){
                if(!(State.getCurrentPlayerHP() <= 0)) { //Checks if the player has HP left. If so, it loads the finalBattleState
                    game.getMouseInput().setLeftPressed(false); //Sets leftPressed to false
                    State.setState(game.finalBattleState);   //Sets the state to the finalBattleState
                }
            }

            //Checks if the player is about to collide with a solid tile
            if(!collisionWithTile((int) (x + bounds.x) / Tile.tileWidth, ty) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.tileWidth, ty)){ //If the player is not colliding with a solid tile, let them move freely
                y += yMove;
            }
            //If the player is colliding with a solid tile, make their position 1 pixel outside the solid tile
            else{
                y = ty * Tile.tileHeight + Tile.tileHeight - bounds.y;
            }


        }else if(yMove > 0){//Down
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.tileHeight; //Creates a temp variable that contains the player's position

            //Checks to see if player is in the tall grass
            if(collisionWithGrass((int) (x + bounds.x) / Tile.tileHeight, ty) ||
                    collisionWithGrass((int) (x + bounds.x + bounds.width) / Tile.tileWidth, ty)){
                grassInteract(); //If the player collides with the grass, run the code that contains the random Pokemon encounter
            }

            //Checks to see if the player is approaching the PokeCenter doors
            if(collisionWithPokeCenter((int) (x + bounds.x) / Tile.tileHeight, ty) ||
                    collisionWithPokeCenter((int) (x + bounds.x + bounds.width) / Tile.tileWidth, ty)){
                game.getMouseInput().setLeftPressed(false); //Sets leftPressed to false
                State.setState(game.healState); //If the player has collided with the Pokecenter doors, then set the state to healState
            }

            //Checks to see if the player is approaching the Champion Building doors
            if(collisionWithChampion((int) (x + bounds.x) / Tile.tileHeight, ty) ||
                    collisionWithChampion((int) (x + bounds.x + bounds.width) / Tile.tileWidth, ty)){
                if(!(State.getCurrentPlayerHP() <= 0)) { //Checks if the player has HP left. If so, it loads the finalBattleState
                    game.getMouseInput().setLeftPressed(false); //Sets leftPressed to false
                    State.setState(game.finalBattleState);   //Sets the state to the finalBattleState
                }
            }

            //Checks if the player is about to collide with a solid tile
            if(!collisionWithTile((int) (x + bounds.x) / Tile.tileHeight, ty) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.tileWidth, ty)){ //If the player is not colliding with a solid tile, let them move freely
                y += yMove;
            }
            //If the player is colliding with a solid tile, make their position 1 pixel outside the solid tile
            else{
                y = ty * Tile.tileHeight - bounds.y - bounds.height - 1;
            }
        }
    }

    //The Code that runs if the player enters an area with tall grass
    public void grassInteract(){
        Random rand = new Random(); //Define a new Random Number Generator

        //If the player has no health left, do not run this code
        if(!(State.getCurrentPlayerHP() <= 0)){
            int randomNum = rand.nextInt(350); //Create a number from one to 350 (The odds of finding a Pokemon per collision with the grass, per frame)
            if (randomNum == 30){ //If the number generated is 30, load the battleState
                game.getMouseInput().setLeftPressed(false); //Sets leftPressed to false
                State.setState(game.battleState);
            }
        }
    }

    private boolean collisionWithTile(int x, int y){ //The code that runs to check if the player is about to enter a solid tile
        return map.getTile(x, y).isSolid(); //Returns a boolean true/false if the tile is solid
    }
    private boolean collisionWithGrass(int x, int y){ //The code that runs to check if the player is about to enter a tall grass tile
        return map.getTile(x, y).isGrass(); //Returns a boolean true/false if the tile is tall grass
    }

    private boolean collisionWithPokeCenter(int x, int y){ //The code that runs to check if the player is about to enter the PokeCenter
        return map.getTile(x, y).isPokeCenter(); //Returns a boolean true/false if the tile is the PokeCenter doors
    }

    private boolean collisionWithChampion(int x, int y){ //The code that runs to check if the player is about to enter the champion building
        return map.getTile(x, y).isChampion(); //Returns a boolean true/false if the tile is the Champion Building doors
    }

    public void renderBody(Graphics g) { //Draw the player's body at the correct size, animation frame, and position on the screen.
        g.drawImage(getCurrentAnimationFrame(), (int) (x - game.getCamera().getxOffset()), (int) (y - game.getCamera().getyOffset()), 60, 31, null);
    }

    public void renderHead(Graphics g) { //Draw the player's body at the correct size, animation frame, and position on the screen.
        g.drawImage(getCurrentHead(), (int) (x - game.getCamera().getxOffset()), (int) (y - game.getCamera().getyOffset()) - 47, 60, 58, null);
    }

    private BufferedImage getCurrentAnimationFrame(){ //Get the current animation frame
        if(xMove < 0){ //If the player is moving left, get the left animations
            return animLeft.getCurrentFrame();
        } else if (xMove > 0){ //If the player is moving right, get the right animations
            return animRight.getCurrentFrame();
        } else if (yMove < 0){//If the player is moving up, get the left animations
            return animUp.getCurrentFrame();
        } else if (yMove > 0) { //If the player is moving down, get the down animations
            return animDown.getCurrentFrame();
        } else { //If the player is not moving, get the neutral animations.
            return animNeutral.getCurrentFrame();
        }
    }

    private BufferedImage getCurrentHead(){
        if(xMove < 0){ //If the player is moving left, get the left head asset
            return Assets.playerHeadLeft;
        } else if (xMove > 0){ //If the player is moving right, get the right head asset
            return Assets.playerHeadRight;
        } else if (yMove < 0){ //If the player is moving up, get the up head asset
            return Assets.playerHeadUp;
        } else { //If the player is moving down or is in a neutral position, get the downward facing head asset.
            return Assets.playerHeadDown;
        }
    }

    //Getters for x and y
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
}