/**
 * PokeCenter23.java
 * Purpose: This class contains the asset and tile ID of the tile of one of the PokeCenter tiles.
 * Author: Owen Gallagher, Jack Santoro
 * Version: 1.0
 * Target: Mr. DiTomasso
 */

package Tiles.PokeCenter;

import PokemonGame.Assets;
import Tiles.Tile;

public class PokeCenter23  extends Tile {
    public PokeCenter23() {
        super(Assets.pokeCenter23, 35);
    } //Contains the texture and tile ID of the tile

    @Override
    public boolean isSolid() {
        return true;
    } //Return isSolid as true, making the tile unable to be walked through
    @Override
    public boolean isPokeCenter() {
        return true;
    } //Return isPokeCenter as true, making it so that you can interact with the door and enter the building

}
