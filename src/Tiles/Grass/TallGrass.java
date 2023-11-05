/**
 * TallGrass.java
 * Purpose: This class contains the asset and tile ID of the tall grass tile.
 * Author: Callum Berry, Jack Santoro
 * Version: 1.0
 * Target: Mr. DiTomasso
 */

package Tiles.Grass;

import PokemonGame.Assets;
import Tiles.Tile;

public class TallGrass extends Tile {
    public TallGrass(){
        super(Assets.tallGrass, 3);
    } //Contains the texture and tile ID of the tile

    public boolean isGrass() {
        return true;
    } //Return isGrass as true, so once you enter it runs the grassInteract(); method
}
