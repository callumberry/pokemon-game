/**
 * MiddleRightTree.java
 * Purpose: This class contains the asset and tile ID of the tile of one of the tree tiles.
 * Author: Callum Berry, Jack Santoro
 * Version: 1.0
 * Target: Mr. DiTomasso
 */

package Tiles.Tree;

import PokemonGame.Assets;
import Tiles.Tile;

public class MiddleRightTree extends Tile {

    public MiddleRightTree(){
        super(Assets.middleRightTree, 7);
    } //Contains the texture and tile ID of the tile

    @Override
    public boolean isSolid(){
        return true;
    } //Return isSolid as true, making the tile unable to be walked through
}