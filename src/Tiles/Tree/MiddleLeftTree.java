/**
 * MiddleLeftTree.java
 * Purpose: This class contains the asset and tile ID of the tile of one of the tree tiles.
 * Author: Callum Berry, Jack Santoro
 * Version: 1.0
 * Target: Mr. DiTomasso
 */

package Tiles.Tree;

import PokemonGame.Assets;
import Tiles.Tile;

public class MiddleLeftTree extends Tile {

    public MiddleLeftTree(){
        super(Assets.middleLeftTree, 6);
    } //Contains the texture and tile ID of the tile

    @Override
    public boolean isSolid(){
        return true;
    } //Return isSolid as true, making the tile unable to be walked through
}