/**
 * Map.java
 * Purpose: This class contains the tilemap of the game,
 *          and all the code necessary to render and
 *          display the map properly.
 * Author: Owen Gallagher, Callum Berry, Jack Santoro
 * Version: 1.0
 * Target: Mr. DiTomasso
 */

package PokemonGame;

//Imports
import Tiles.Tile;

import java.awt.*;

public class Map {
    private int width, height; //Create width and height variables
    public static int[][] mapArray; //Create a variable to contain the tilemap
    private Game game; //Create a game object

    public Map(Game game){
        this.game = game; //Define the game object

        width = 64; //Define the width and height variables
        height = 36;
       
        mapArray = new int[][]{ //Define the Map Array. Each number in the array has a corresponding Tile ID to be constructed into the map
                {66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66 ,66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66},
                {66, 3, 3, 2, 0, 1, 2, 0, 1, 2, 0, 3, 3, 3, 3, 3, 3, 3, 2, 8, 9, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 3, 3, 3, 3, 3, 3, 3, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 3, 3, 3, 3, 66},
                {66, 3, 13, 14, 15, 16, 17, 2, 0, 1, 2, 0, 3, 3, 0, 1, 2, 0, 1, 6, 7, 3, 3, 3, 1, 2, 0, 1, 2, 0, 3, 2, 0, 1, 2, 0, 1, 2, 0, 1, 1, 2, 0, 3, 3, 3, 2, 0, 1, 2, 0, 8, 9, 38, 39, 39, 39, 39, 39, 39, 39, 64, 3, 66},
                {66, 1, 18, 19, 20, 21, 22, 1, 8, 9, 1, 2, 0, 1, 8, 9, 1, 2, 0, 4, 5, 3, 3, 3, 3, 1, 8, 9, 1, 3, 3, 3, 2, 0, 8, 9, 0, 1, 2, 0, 1, 2, 0, 1, 66, 0, 1, 2, 0, 1, 2, 6, 7, 40, 41, 41, 41, 41, 41, 41, 41, 65, 2, 66},
                {66, 0, 23, 24, 25, 26, 27, 0, 6, 7, 0, 8, 9, 0, 6, 7, 0, 1, 2, 0, 3, 3, 8, 9, 3, 3, 6, 7, 0, 3, 3, 3, 1, 2, 6, 7, 2, 0, 1, 8, 9, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 4, 5, 42, 43, 43, 44, 45, 46, 43, 43, 47, 1, 66},
                {66, 2, 28, 29, 30, 31, 32, 2, 4, 5, 2, 6, 7, 2, 4, 5, 2, 0, 1, 2, 0, 3, 6, 7, 3, 3, 4, 5, 2, 0, 3, 2, 0, 1, 4, 5, 1, 2, 0, 6, 7, 0, 8, 9, 0, 1, 3, 3, 1, 2, 0, 1, 2, 48, 49, 49, 50, 51, 52, 49, 49, 53, 0, 66},
                {66, 1, 33, 34, 35, 36, 37, 1, 2, 0, 1, 4, 5, 1, 2, 0, 1, 2, 0, 1, 2, 0, 4, 5, 3, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 0, 4, 5, 2, 6, 7, 2, 3, 3, 3, 3, 8, 9, 0, 1, 54, 55, 55, 56, 57, 58, 55, 55, 59, 2, 66},
                {66, 0, 1, 0, 10, 11, 0, 1, 2, 0, 1 ,2, 0, 1, 0, 2, 8, 9, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 8, 9, 0, 1, 2, 0, 1, 8, 9, 1, 8, 9, 1, 4, 5, 0, 2, 3, 3, 3, 6, 7, 2, 0, 1, 63, 63, 60, 61, 62, 63, 63, 0, 1, 66},
                {66, 2, 0, 2, 10, 11, 2, 0, 1, 2, 0, 1, 2, 0, 1, 0, 6, 7, 1, 2, 0, 1, 3, 3, 3, 2, 0, 1, 2, 6, 7, 2, 0, 1, 2, 0, 6, 7, 0, 6, 7, 0, 1, 2, 0, 1, 2, 0, 1, 4, 5, 1, 2, 0, 1, 2, 66, 10, 11, 66, 1, 2, 0, 66},
                {66, 3, 2, 0, 10, 11, 3, 2, 0, 2, 1, 2, 0, 1, 2, 0, 4, 5, 0, 1, 2, 3, 3, 3, 3, 3, 2, 0, 1, 4, 5, 1, 2, 0, 1, 2, 4, 5, 2, 4, 5, 2, 0, 1 ,2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 10, 11, 2, 0, 1, 2, 66},
                {66, 3, 3, 2, 10, 11, 3, 3, 2, 0, 1, 2, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 3, 3, 3, 0, 66, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 66, 1, 2, 0, 1, 2, 0, 1, 10, 11, 1, 2, 0, 3, 66},
                {66, 3, 66, 2, 10, 73, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 72, 11, 1, 0, 3, 3, 66},
                {66, 1, 2, 0, 68, 76, 76, 76, 76, 76, 76, 76, 76, 76, 74, 75, 76, 76, 76, 76, 76, 76, 76, 76, 76, 76, 76, 76, 76, 76, 76, 76, 76, 76, 76, 76, 76, 76, 76, 76, 76, 76, 76, 74, 75, 76, 76, 76, 76, 76, 76, 76, 76, 76, 76, 76, 76, 76, 69, 3, 3, 3, 3, 66},
                {66, 0, 1, 2, 0, 1, 2, 3, 1, 2, 0, 1, 0, 2, 10, 11, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 3, 3, 3, 3, 3, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 3, 3, 10, 11, 3, 3, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 3, 3, 3, 3, 3, 66},
                {66, 2, 0, 1, 2, 0, 3, 3, 3, 1, 2, 0, 1, 2, 10, 11, 2, 0, 1, 8, 9, 1, 2, 0, 1, 2, 0, 3, 3, 3, 3, 2, 0, 1, 2, 0, 8, 9, 0, 1, 2, 0, 3, 10, 11, 3, 2, 0, 1, 2, 0, 1, 2, 0, 1, 8, 9, 1, 2, 3, 3, 3, 3, 66},
                {66, 8, 9, 0, 1, 2, 0, 3, 2, 0, 1, 2, 0, 1, 10, 11, 1, 2, 0, 6, 7, 0, 1, 2, 0, 1, 2, 0, 3, 3, 0, 1, 2, 0, 1, 2, 6, 7, 2, 0, 1, 2, 3, 10, 11, 0, 1, 2, 8, 9, 2, 66, 1, 2, 0, 6, 7, 0, 1, 2, 3, 3, 3, 66},
                {66, 6, 7, 2, 0, 1, 2, 0, 1, 2, 8, 9, 2, 0, 10, 11, 0, 1, 2, 4, 5, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 4, 5, 1, 2, 0, 1, 2, 10, 11, 2, 0, 1, 6, 7, 1, 2, 0, 1, 2, 4, 5, 2, 0, 1, 2, 3, 3, 66},
                {66, 4, 5, 1, 2, 0, 1, 3, 3, 1, 6, 7, 1, 2, 10, 11, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 10, 11, 1, 2, 0, 4, 5, 0, 3, 3, 3, 3, 2, 0, 1, 2, 0, 1, 2, 3, 66},
                {66, 1, 2, 0, 1, 2, 0, 3, 3, 3, 4, 5, 0, 1, 10, 11, 1, 2, 0, 1, 2, 66, 1, 2, 8, 9, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 10, 11, 0, 1, 2, 0, 1, 3, 3, 3, 3, 3, 3, 3, 3, 1, 2, 0, 1, 2, 66},
                {66, 0, 1, 8, 9, 1, 3, 3, 3, 3, 0, 1 ,2, 0, 10, 11, 0, 1, 2, 0, 1, 2, 0, 1, 6, 7, 1, 70, 77, 77, 77, 77, 71, 1, 0, 1, 2, 0, 1, 2, 0, 1, 2, 10, 11, 2, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 0, 1, 2, 0, 1, 66},
                {66, 2, 0, 6, 7, 0, 3, 3, 3, 3, 2, 0, 1, 2, 10, 11, 2, 0, 8, 9, 0, 1, 2, 0, 4, 5, 0, 10, 75, 76, 76, 74, 11, 0, 1, 0, 1, 8, 9, 1, 2, 0, 1, 10, 11, 1, 2, 0, 3, 3, 3, 3, 3, 3, 3, 3, 0, 1, 2, 0, 3, 3, 0, 66},
                {66, 1, 2, 4, 5, 2, 0, 3, 1, 0, 1, 2, 0, 1, 10, 11, 1, 2, 6, 7, 2, 0, 1, 2, 0, 1, 2, 10, 11, 3, 3, 10, 11, 2, 0, 1, 2, 6, 7, 0, 1, 2, 0, 10, 11, 0, 1, 2, 0, 1, 3, 3, 3, 3, 3, 0, 1, 0, 1, 2, 3, 3, 3, 66},
                {66, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 10, 11, 0, 1, 4, 5, 1, 2, 0, 1, 2, 0, 1, 10, 11, 3, 3, 10, 11, 1, 2, 0, 1, 4, 5, 2, 0, 1, 2, 10, 11, 2, 0, 1, 2, 0, 3, 3, 3, 3, 2, 66, 1, 2, 0, 1, 2, 3, 1, 66},
                {66, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 10, 11, 2, 0, 3, 3, 3, 1, 2, 0, 1, 2, 0, 10, 73, 77, 77, 72, 11, 0, 1, 2, 0, 2, 0, 1, 2, 0, 1, 10, 11, 1, 2, 0, 1, 2, 3, 3, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 66},
                {66, 1, 2, 0, 1, 2, 0, 1, 2, 0, 66, 2, 0, 1, 10, 11, 1, 3, 3, 3, 3, 3, 1, 2, 0, 1, 2, 68, 76, 76, 76, 76, 69, 2, 0, 1, 2, 1, 2, 0, 1, 2, 0, 10, 11, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 66},
                {66, 0, 1, 2, 0, 1, 8, 9, 1, 2, 0, 8, 9, 0, 10, 11, 3, 3, 3, 3, 3, 3, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 8, 9, 1, 2, 10, 11, 2, 0, 1, 2, 0, 1, 2, 0, 8, 9, 0, 1, 2, 0, 1, 8, 9, 1, 66},
                {66, 2, 0, 1, 2, 0, 6, 7, 0, 1, 2, 6, 7, 2, 10, 11, 3, 3, 3, 3, 3, 1, 2, 0, 1, 2, 0, 1, 2, 0, 3, 3, 0, 1, 2, 0, 1, 2, 0, 6, 7, 0, 66, 10, 11, 1, 2, 0, 1, 2, 0, 1, 2, 6, 7, 2, 0, 1, 2, 0, 6, 7, 0, 66},
                {66, 3, 2, 0, 1, 2, 4, 5, 2, 0, 1, 4, 5, 1, 10, 11, 3, 3, 3, 3, 2, 0, 1, 2, 0, 1, 2, 0, 1, 3, 3, 3, 3, 0, 1, 2, 0, 1, 2, 4, 5, 3, 3, 10, 11, 0, 1, 2, 0, 1, 2, 0, 1, 4, 5, 1, 2, 0, 1, 2, 4, 5, 3, 66},
                {66, 3, 3, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 10, 73, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 72, 11, 2, 0, 1, 3, 3, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 3, 3, 66},
                {66, 3, 3, 3, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 68, 76, 76, 76, 76, 76, 76, 76, 76, 76, 76, 76, 76, 76, 76, 76, 76, 76, 76, 76, 76, 76, 76, 76, 76, 76, 76, 76, 76, 76, 69, 1, 2, 3, 3, 3, 3, 1, 2, 0, 1, 2, 0, 1, 2, 0, 3, 3, 3, 66},
                {66, 3, 3, 3, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 8, 9, 0, 1, 2, 3, 3, 3, 3, 1, 2, 0, 1, 2, 0, 1, 3, 3, 3, 3, 66},
                {66, 3, 3, 3, 3, 3, 2, 0, 1, 2, 0, 1 ,2, 0, 1, 2, 0, 1, 2, 8, 9, 2, 66, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 8, 9, 2, 0, 1, 2, 8, 9, 2, 6, 7, 2, 0, 1, 2, 3, 3, 2, 0, 1, 2, 0, 1, 2, 3, 3, 3, 3, 3, 66},
                {66, 3, 3, 3, 3, 3, 3, 3, 0, 1, 2, 0, 1, 8, 9, 1, 2, 0, 1, 6, 7, 1, 2, 0, 3, 3, 3, 1, 2, 0, 1, 2, 0, 1, 6, 7, 1, 2, 0, 1, 6, 7, 1, 4, 5, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 3, 3, 3, 3, 3, 3, 3, 66},
                {66, 3, 3, 3, 3, 3, 3, 3, 3, 0, 1, 2, 0, 6, 7, 0, 1, 2, 0, 4, 5, 0, 1, 3, 3, 3, 3, 3, 3, 2, 0, 1, 2, 0, 4, 5, 0, 1, 2, 0, 4, 5, 0, 1, 2, 0, 1, 66, 0, 1, 2, 0, 1, 2, 0, 3, 3, 3, 3, 3, 3, 3, 3, 66},
                {66, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 1, 2, 4, 5, 2, 0, 1, 2, 0, 1, 2, 3, 3, 3, 3, 3, 3, 3, 3, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 66},
                {66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66 ,66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66},
        };
    }

    public void update(){ //The update method is empty, but is still required

    }

    public void render(Graphics g){ //Renders all background tiles that the player walks over
        int xStart = (int) Math.max(0, game.getCamera().getxOffset() / Tile.tileWidth); //Define which parts of the map should render each tick (the game only renders what's in view of the camera in order to save processing power)
        int xEnd = (int) Math.min(width, (game.getCamera().getxOffset() + game.getWidth()) / Tile.tileWidth + 1);
        int yStart = (int) Math.max(0, game.getCamera().getyOffset() / Tile.tileHeight);
        int yEnd = (int) Math.min(height, (game.getCamera().getyOffset() + game.getHeight()) / Tile.tileHeight + 1);

        for(int y = yStart; y < yEnd; y++){ //Only renders the tiles in the camera's view
            for(int x = xStart; x < xEnd; x++){
                if(mapArray[y][x] != 8 && mapArray[y][x] != 9 && mapArray[y][x] != 3 && mapArray[y][x] != 13 && mapArray[y][x] != 14 && mapArray[y][x] != 15 //Only renders the tiles that are not rendered in another rendering class (background tiles, behind the player)
                        && mapArray[y][x] != 16 && mapArray[y][x] != 17 && mapArray[y][x] != 33 && mapArray[y][x] != 37 && mapArray[y][x] != 36 && mapArray[y][x] != 38
                        && mapArray[y][x] != 39 && mapArray[y][x] != 40 && mapArray[y][x] != 64 && mapArray[y][x] != 63 && mapArray[y][x] != 60 && mapArray[y][x] != 61
                        && mapArray[y][x] != 62 && mapArray[y][x] != 65) {
                    getTile(x, y).render(g, (int)(y * Tile.tileWidth - game.getCamera().getyOffset()),
                            (int)(x * Tile.tileHeight - game.getCamera().getxOffset())); //Renders all tiles defined in the parameters
                }
                else{
                    Tile.tiles[12].render(g, (int)(y * Tile.tileWidth - game.getCamera().getyOffset()), //If the tile ID is 12 render a blankGrass tile
                            (int)(x * Tile.tileHeight - game.getCamera().getxOffset()));
                }
            }
        }
    }

    public void renderTreeTops(Graphics g){
        int xStart = (int) Math.max(0, game.getCamera().getxOffset() / Tile.tileWidth);  //Define which parts of the map should render each tick (the game only renders what's in view of the camera in order to save processing power)
        int xEnd = (int) Math.min(width, (game.getCamera().getxOffset() + game.getWidth()) / Tile.tileWidth + 1);
        int yStart = (int) Math.max(0, game.getCamera().getyOffset() / Tile.tileHeight);
        int yEnd = (int) Math.min(height, (game.getCamera().getyOffset() + game.getHeight()) / Tile.tileHeight + 1);

        for(int y = yStart; y < yEnd; y++){ //Only renders the tiles in the camera's view
            for(int x = xStart; x < xEnd; x++){
                if(mapArray[y][x] == 8 || mapArray[y][x] == 9){ //Only renders the treetop files (so they can be in front of the whole player)
                    getTile(x, y).render(g, (int)(y * Tile.tileWidth - game.getCamera().getyOffset()),
                            (int)(x * Tile.tileHeight - game.getCamera().getxOffset())); //Renders all tiles defined in the parameters
                }
            }
        }
    }

    public void renderTallGrass(Graphics g){
        int xStart = (int) Math.max(0, game.getCamera().getxOffset() / Tile.tileWidth);  //Define which parts of the map should render each tick (the game only renders what's in view of the camera in order to save processing power)
        int xEnd = (int) Math.min(width, (game.getCamera().getxOffset() + game.getWidth()) / Tile.tileWidth + 1);
        int yStart = (int) Math.max(0, game.getCamera().getyOffset() / Tile.tileHeight);
        int yEnd = (int) Math.min(height, (game.getCamera().getyOffset() + game.getHeight()) / Tile.tileHeight + 1);

        for(int y = yStart; y < yEnd; y++){ //Only renders the tiles in the camera's view
            for(int x = xStart; x < xEnd; x++){
                if(mapArray[y][x] == 3){ //Only renders tall grass tiles (so they can be in front of the player's body but behind his head)
                    getTile(x, y).render(g, (int)(y * Tile.tileWidth - game.getCamera().getyOffset()),
                            (int)(x * Tile.tileHeight - game.getCamera().getxOffset()));
                }
            }
        }
    }
    public void renderBuildingTops(Graphics g){
        int xStart = (int) Math.max(0, game.getCamera().getxOffset() / Tile.tileWidth);  //Define which parts of the map should render each tick (the game only renders what's in view of the camera in order to save processing power)
        int xEnd = (int) Math.min(width, (game.getCamera().getxOffset() + game.getWidth()) / Tile.tileWidth + 1);
        int yStart = (int) Math.max(0, game.getCamera().getyOffset() / Tile.tileHeight);
        int yEnd = (int) Math.min(height, (game.getCamera().getyOffset() + game.getHeight()) / Tile.tileHeight + 1);

        for(int y = yStart; y < yEnd; y++){ //Only renders the tiles in the camera's view
            for(int x = xStart; x < xEnd; x++){
                if(mapArray[y][x] == 13 || mapArray[y][x] == 14 || mapArray[y][x] == 15 || mapArray[y][x] == 16 || mapArray[y][x] == 17
                        || mapArray[y][x] == 38 || mapArray[y][x] == 38 || mapArray[y][x] == 39 ||  mapArray[y][x] == 40
                        || mapArray[y][x] == 64 || mapArray[y][x] == 65){ //Only renders the tops of buildings (so they can be in front of the player)
                    getTile(x, y).render(g, (int)(y * Tile.tileWidth - game.getCamera().getyOffset()),
                            (int)(x * Tile.tileHeight - game.getCamera().getxOffset())); //Renders all tiles defined in the parameters
                }
            }
        }
    }
    public void renderBuildingBases(Graphics g){
        int xStart = (int) Math.max(0, game.getCamera().getxOffset() / Tile.tileWidth);  //Define which parts of the map should render each tick (the game only renders what's in view of the camera in order to save processing power)
        int xEnd = (int) Math.min(width, (game.getCamera().getxOffset() + game.getWidth()) / Tile.tileWidth + 1);
        int yStart = (int) Math.max(0, game.getCamera().getyOffset() / Tile.tileHeight);
        int yEnd = (int) Math.min(height, (game.getCamera().getyOffset() + game.getHeight()) / Tile.tileHeight + 1);

        for(int y = yStart; y < yEnd; y++){ //Only renders the tiles in the camera's view
            for(int x = xStart; x < xEnd; x++){
                if(mapArray[y][x] == 33 || mapArray[y][x] == 37  || mapArray[y][x] == 63 || mapArray[y][x] == 60 || mapArray[y][x] == 61 || mapArray[y][x] == 62 || mapArray[y][x] == 36){ //Only renders the building bases (so they can be in front of the player)
                    getTile(x, y).render(g, (int)(y * Tile.tileWidth - game.getCamera().getyOffset()),
                            (int)(x * Tile.tileHeight - game.getCamera().getxOffset())); //Renders all tiles defined in the parameters
                }
            }
        }
    }

    public Tile getTile(int x, int y){ //Getter for the tiles
        if(x < 0 || y < 0 || x >= width || y >= height){
            return  Tile.grassTile_1; //If a tile is out of the bounds of the map, render it as a plain grass tile
        }
        return Tile.tiles[mapArray[y][x]];
    }

    public int getWidth() {
        return width;
    } //Getters for the width and height
    public int getHeight() {
        return height;
    }
}

