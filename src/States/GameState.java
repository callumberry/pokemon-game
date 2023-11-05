/**
 * GameState.java
 * Purpose: The gameState is where the actually game
 *          is scene. The player and map are rendered
 *          in this state.
 * Author:  Callum Berry
 * Version: 1.0
 * Date:    January 23, 2020
 * Target:  Mr. DiTomasso
 */

package States;

//Imports
import Entities.Player;
import PokemonGame.Display;
import PokemonGame.Game;
import PokemonGame.Map;
import Tiles.Tile;

import java.awt.*;

public class GameState extends State{
    //Initialization of the variables
    private Player player; //Define a player object
    private Map map;

    public GameState(Game game, Display display){
        super(game, display);


        player = new Player(game, 1890, 1087); //Create a player object, and give it x and y coordinates
        map = new Map(game);        //Creates a map object
    }

    //Update Method
    public void update() {
        //Runs the map's and player's update method
        map.update();
        player.update();

        //Checks ff you clicked the "R" key
        if(game.getKeyManager().optionsStateSwap) {
            game.getMouseInput().setLeftPressed(false);     //Sets left pressed to false
            State.setState(game.gameOptionsState);      //Switches you to the gameOptionsState
        }
    }

    //Render Method
    public void render(Graphics g) {
        //Renders all the layers of the map and player
        map.render(g);
        map.renderBuildingBases(g);
        player.renderBody(g);
        map.renderTallGrass(g);
        player.renderHead(g);
        map.renderBuildingTops(g);
        map.renderTreeTops(g);

        //Sets the font
        g.setFont(new Font("Monospaced", Font.BOLD, 36));

        //Checks to see ifthe player's current hp is <= to 0
        if(State.getCurrentPlayerHP() <= 0){
            g.setColor(Color.lightGray);        //Switches the colour
            g.fillRect(0,0, Tile.tileWidth * 9,45);     //Fills a rectangle
            g.setColor(Color.darkGray);     //Switches the colours
            g.drawString("Please Visit a PokeCenter", 10, 35);      //Adds text inside the rectangle created above telling the player to visit the pokecenter
        }
    }
}