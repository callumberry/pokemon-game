/**
 * Tile.java
 * Purpose: This class contains the code that will be extended to
 *          every tile in the game, including objects to help
 *          define texture,tile ID, and all interaction booleans.
 * Author: Owen Gallagher, Callum Berry, Jack Santoro
 * Version: 1.0
 * Target: Mr. DiTomasso
 */

package Tiles;

//Imports
import Tiles.Building.*;
import Tiles.Grass.*;
import Tiles.Path.*;
import Tiles.PokeCenter.*;
import Tiles.Tree.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {
    public static Tile[] tiles = new Tile[256]; //Create an array to contain all the tile IDs needed
    public static Tile grassTile_1 = new GrassTile_1(), //Create objects of each of the tiles used in the game
                       grassTile_2 = new GrassTile_2(),
                       grassTile_3 = new GrassTile_3(),
                       tallGrass = new TallGrass(),
                       bottomLeftTree = new BottomLeftTree(),
                       bottomRightTree = new BottomRightTree(),
                       middleLeftTree = new MiddleLeftTree(),
                       middleRightTree = new MiddleRightTree(),
                       topLeftTree = new TopLeftTree(),
                       topRightTree = new TopRightTree(),
                       leftPath = new LeftPath(),
                       rightPath = new RightPath(),
                       blankGrass = new BlankGrass(),
                       pokecenter1 = new PokeCenter1(), pokecenter2 = new PokeCenter2(), pokecenter3 = new PokeCenter3(), pokecenter4 = new PokeCenter4(), pokecenter5 = new PokeCenter5(),
                       pokecenter6 = new PokeCenter6(), pokecenter7 = new PokeCenter7(), pokecenter8 = new PokeCenter8(), pokecenter9 = new PokeCenter9(), pokecenter10 = new PokeCenter10(),
                       pokecenter11 = new PokeCenter11(), pokecenter12 = new PokeCenter12(), pokecenter13 = new PokeCenter13(), pokecenter14 = new PokeCenter14(), pokecenter15 = new PokeCenter15(),
                       pokecenter16 = new PokeCenter16(), pokecenter17 = new PokeCenter17(), pokecenter18 = new PokeCenter18(), pokecenter19 = new PokeCenter19(), pokecenter20 = new PokeCenter20(),
                       pokecenter21 = new PokeCenter21(), pokecenter22 = new PokeCenter22(), pokecenter23 = new PokeCenter23(), pokecenter24 = new PokeCenter24(), pokecenter25 = new PokeCenter25(),
                       building1 = new Building1(), building2 = new Building2(), building3 = new Building3(), building4 = new Building4(), building5 = new Building5(),
                       building6 = new Building6(), building7 = new Building7(), building8 = new Building8(), building9 = new Building9(), building10 = new Building10(),
                       building11 = new Building11(), building12 = new Building12(), building13 = new Building13(), building14 = new Building14(), building15 = new Building15(),
                       building16 = new Building16(), building17 = new Building17(), building18 = new Building18(), building19 = new Building19(), building20 = new Building20(),
                       building21 = new Building21(), building22 = new Building22(), building23 = new Building23(), building24 = new Building24(), building25 = new Building25(),
                       building26 = new Building26(), building27 = new Building27(), building28 = new Building28(), bush = new Bush(),   pathCornerBottomLeft = new pathCornerBottomLeft(),
                       pathCornerBottomRight = new pathCornerBottomRight(), pathCornerTopLeft = new pathCornerTopLeft(), pathCornerTopRight = new pathCornerTopRight(),
                       PathInnerCornerBottomLeft = new PathInnerCornerBottomLeft(), PathInnerCornerBottomRight = new PathInnerCornerBottomRight(), pathInnerCornerTopLeft = new pathInnerCornerTopLeft(),
                       PathInnerCornerTopRight = new PathInnerCornerTopRight(), PathSideBottom = new PathSideBottom(), PathSideTop = new PathSideTop();

    //Set tile width and height
    public static final int tileWidth = 64,
                            tileHeight = 64;

    //Create objects to define texture and tile ID
    protected BufferedImage texture;
    protected final int tileId;

    public Tile(BufferedImage texture, int tileId){
        this.texture = texture;
        this.tileId = tileId;
        tiles[tileId] = this;
    }

    //Update method is required, but has nothing in it
    public void update(){

    }

    public void render(Graphics g, int y, int x){
        g.drawImage(texture, x, y, tileWidth, tileHeight, null); //The code required to render the tile (texture, coordinates, and size)
    }

    public boolean isSolid(){
        return false;  //Boolean for if the tile is solid. Set to false by default
    }
    public boolean isGrass(){
        return false; //Boolean for if the tile is tall grass. Set to false by default
    }
    public boolean isPokeCenter(){
        return false; //Boolean for if the tile is the doors to the PokeCenter. Set to false by default
    }
    public boolean isChampion(){
        return false; //Boolean for if the tile is the doors to the Champion Center. Set to false by default
    }

    //not used
    public int getId(){
        return tileId; //A getter for the Tile ID of the tile
    }

}