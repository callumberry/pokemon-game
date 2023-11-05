/**
 * Building15.java
 * Purpose: This class contains the asset and tile
 *          ID of one of the building tiles.
 * Author: Owen Gallagher, Jack Santoro
 * Version: 1.0
 * Target: Mr. DiTomasso
 */

package Tiles.Building;

import PokemonGame.Assets;
import Tiles.Tile;

public class Building15 extends Tile {
    public Building15() {
        super(Assets.building15, 52);
    } //Contains the texture and tile ID of the tile
    @Override
    public boolean isSolid() {
        return true;
    } //Return isSolid as true, making the tile unable to be walked through
}
