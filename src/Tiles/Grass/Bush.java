/**
 * Bush.java
 * Purpose: This class contains the asset and tile ID of the bush tile.
 * Author: Callum Berry, Jack Santoro
 * Version: 1.0
 * Target: Mr. DiTomasso
 */

package Tiles.Grass;

import PokemonGame.Assets;
import Tiles.Tile;

public class Bush extends Tile {
    public Bush(){
        super(Assets.bush, 66);
    } //Contains the texture and tile ID of the tile

    @Override
    public boolean isSolid() {
        return true;
    }
}
