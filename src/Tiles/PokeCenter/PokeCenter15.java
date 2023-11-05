/**
 * PokeCenter15.java
 * Purpose: This class contains the asset and tile ID of the tile of one of the PokeCenter tiles.
 * Author: Owen Gallagher, Jack Santoro
 * Version: 1.0
 * Target: Mr. DiTomasso
 */

package Tiles.PokeCenter;

import PokemonGame.Assets;
import Tiles.Tile;

public class PokeCenter15  extends Tile {
    public PokeCenter15() {
        super(Assets.pokeCenter15, 27);
    }
    @Override
    public boolean isSolid() {
        return true;
    } //Return isSolid as true, making the tile unable to be walked through
}
