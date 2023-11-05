/**
 * BlankGrass.java
 * Purpose: This class contains the asset and tile ID of the blank grass tile.
 * Author: Callum Berry, Jack Santoro
 * Version: 1.0
 * Target: Mr. DiTomasso
 */

package Tiles.Grass;

import PokemonGame.Assets;
import Tiles.Tile;

public class BlankGrass extends Tile {
    public BlankGrass(){
        super(Assets.blankGrass, 12);
    } //Contains the texture and tile ID of the tile
}
