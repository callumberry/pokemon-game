/**
 * Pokemon.java
 * Purpose: This class contains all code concerning the Pokemon in the game,
 *          including their stats, level, and how the player and combat systems
 *          interact with them, including stat calculations, and all the getters
 *          required by the battle system.
 * Author: Callum Berry
 * Version: 1.0
 * Target: Mr. DiTomasso
 */

package PokemonPackage;

//Imports
import PokemonGame.Assets;
import States.MenuState;

import java.util.Random;

/*Information about pokemon stats*/
//IV for all pokemon is 31
//EV for all pokemon is 0
//Power of all moves is 50

public class Pokemon {
    private Object[] championPokemon, wildPokemon, playerPokemon; //Create objects for the champion pokemon, wild pokemon, and player pokemon
    private Object[] charmander, bulbasaur, squirtle, pidgey, rattata, pikachu, clefairy, eevee, growlithe, geodude; //Create object that contain the stats for each Pokemon
    private int baseAttack, baseDefence, baseHp, playerLevel, foeLevel, championLevel, fl; //Create ints to be used for calculating stats
    private int playerAttackStat, playerDefenceStat, playerHPStat, //Create ints to be used for calculating attack, Defense, and HP stats for the player pokemon, wild pokemon, and champion pokemon
                foeAttackStat, foeDefenceStat, foeHPStat,
                championAttackStat, championDefenceStat, championHPStat;

    public Pokemon(){
        //Defines the initial levels for the player and the enemy
        playerLevel = 5;
        foeLevel = 5;

        //Each object contains the stats for all pokemon, which can be called upon separately by the battle systems
        //Name, sprite, attack, defence, hp, level, experience value
        charmander = new Object[]{"Charmander", Assets.charmander, 52, 43, 39, playerLevel, 65};
        bulbasaur = new Object[]{"Bulbasaur", Assets.bulbasaur, 49, 49, 45, playerLevel, 64};
        squirtle = new Object[]{"Squirtle", Assets.squirtle, 48, 65, 44, playerLevel, 66};
        pidgey = new Object[]{"Pidgey", Assets.pidgey, 45, 40, 40, foeLevel, 55};
        rattata = new Object[]{"Rattata", Assets.rattata, 56, 35, 30, foeLevel, 57};
        pikachu = new Object[]{"Pikachu", Assets.pikachu, 55, 30, 35, foeLevel, 82};
        clefairy = new Object[]{"Clefairy", Assets.clefairy, 45, 48, 70, foeLevel, 68};
        eevee = new Object[]{"Eevee", Assets.eevee, 55, 50, 55, foeLevel, 92};
        growlithe = new Object[]{"Growlithe", Assets.growlithe, 70, 45, 55, foeLevel, 91};
        geodude = new Object[]{"Geodude", Assets.geodude, 80, 100, 40, foeLevel, 86};

        //Create a specific Champion Pokemon with high stats to be beaten by the end of the game
        championPokemon = new Object[]{"Champion's Geodude", Assets.geodude, 80, 100, 40, championLevel, 86};

        wildPokemonChooser(); //Generates a wild Pokemon

        if(MenuState.tutorialComplete == false){
            //Sets the default Pokemon to Charmander if the player has yet to complete the tutorial (this pokemon is never used, but a placeholder is needed until the player selects one after the tutorial)
            playerPokemon = charmander;
        }

        playerStatCalc(); //Calculates the player's stats
        wildPokemonStatCalc(); //Calculates the stats of the wild Pokemon
    }

    public void wildPokemonChooser(){ //The code that generates a wild pokemon
        Random rand = new Random(); //Create a new Random Number generator
        int pokemonChooser = rand.nextInt(10); //Generates a number between 1 and 10

        //Based on the number generated, this chunk of code gets one of the 10 available Pokemon
        if(pokemonChooser == 1){
            wildPokemon = charmander;
        }
        else if(pokemonChooser == 2){
            wildPokemon = bulbasaur;
        }
        else if(pokemonChooser == 3){
            wildPokemon = squirtle;
        }
        else if(pokemonChooser == 4){
            wildPokemon = pidgey    ;
        }
        else if(pokemonChooser == 5){
            wildPokemon = rattata;
        }
        else if(pokemonChooser == 6){
            wildPokemon = pikachu;
        }
        else if(pokemonChooser == 7){
            wildPokemon = clefairy;
        }
        else if(pokemonChooser == 8){
            wildPokemon = eevee;
        }
        else if(pokemonChooser == 9){
            wildPokemon = growlithe;
        }
        else{
            wildPokemon = geodude;
        }
    }

    public void playerStatCalc(){ //This code calculates the player's stats
        baseAttack = (int)playerPokemon[2]; //Gets each individual stat based on the associated part of the selected Pokemon's object
        baseDefence = (int)playerPokemon[3];
        baseHp = (int)playerPokemon[4];
        playerLevel = getPlayerLevel(); //Gets the player's Pokemon level

        playerAttackStat = ((((2 * baseAttack + 31) * playerLevel) / 100) + 5); //Determines what the player's attack stats will be based on their base attack power and their level
        playerDefenceStat = ((((2 * baseDefence + 31) * playerLevel) / 100) + 5); //Determines what the player's defense stats will be based on their base defense and their level
        playerHPStat = ((((2 * baseHp + 31) * playerLevel) / 100) + playerLevel + 10); //Determines what the player's HP stats will be based on their base HP and their level
    }

    public void wildPokemonStatCalc(){ //This code calculates the stats for the wild pokemon
        baseAttack = (int)wildPokemon[2]; //Gets each individual stat based on the associated part of the wild Pokemon's object
        baseDefence = (int)wildPokemon[3];
        baseHp = (int)wildPokemon[4];

        Random rand = new Random(); //Creates a Random Number Generator
        fl = rand.nextInt((playerLevel - 3) + 1) + 3; //Creates a temp object that is a random number that is any number from 3 to the player's level

        foeLevel = fl; //Assigns a permanent foe Level variable

        foeAttackStat = ((((2 * baseAttack + 31) * foeLevel) / 100) + 5); //Determines what the foe's attack stats will be based on their base attack power and their level
        foeDefenceStat = ((((2 * baseDefence + 31) * foeLevel) / 100) + 5); //Determines what the foe's defense stats will be based on their base defense and their level
        foeHPStat = ((((2 * baseHp + 31) * foeLevel) / 100) + foeLevel + 10); //Determines what the foe's HP stats will be based on their base HP and their level
    }

    public void championPokemonStatCalc(){  //This code calculates the stats for the Champion's pokemon
        baseAttack = (int)championPokemon[2]; //Gets each individual stat based on the associated part of the Champion Pokemon's object
        baseDefence = (int)championPokemon[3];
        baseHp = (int)championPokemon[4];
        championLevel = 20; //Sets the Champion's level to 20

        championAttackStat = ((((2 * baseAttack + 31) * championLevel) / 100) + 5); //Determines what the champion's attack stats will be based on their base attack power and their level
        championDefenceStat = ((((2 * baseDefence + 31) * championLevel) / 100) + 5); //Determines what the Champion's defense stats will be based on their base defense and their level
        championHPStat = ((((2 * baseHp + 31) * championLevel) / 100) + championLevel + 10); //Determines what the Champion's HP stats will be based on their base HP and their level
    }

    public void setPlayerPokemon(Object[] playerPokemon) { //Setter for the Player's Pokemon
        this.playerPokemon = playerPokemon;
    }

    public Object[] getPlayerPokemon() { //Getter for the Player Pokemon
        return playerPokemon;
    }
    public Object[] getWildPokemon() { //Getter for the Wild Pokemon
        return wildPokemon;
    }
    public Object[] getChampionPokemon() { //Getter for the Champion Pokemon
        return championPokemon;
    }

    public int getFoeLevel() { //Getter for the Foe Level
        return foeLevel;
    }
    public int getPlayerLevel() { //Getter for the Player Level
        return playerLevel;
    }

    public void setPlayerLevel(int playerLevel) { //Getter for the Player Level
        this.playerLevel = playerLevel;
    }

    public Object[] getCharmander() { //Getter for Charmander
        return charmander;
    }
    public Object[] getBulbasaur() { //Getter for Bulbasaur
        return bulbasaur;
    }
    public Object[] getSquirtle() { //Getter for Squirtle
        return squirtle;
    }
    public Object[] getPikachu() { //Getter for Pikachu
        return pikachu;
    }

    public int getPlayerAttackStat() { //Getter for the Player Attack Stat
        return playerAttackStat;
    }
    public int getPlayerDefenceStat() { //Getter for the Player Defence Stat
        return playerDefenceStat;
    }
    public int getPlayerHPStat() { //Getter for the Player HP Stat
        return playerHPStat;
    }

    public int getFoeAttackStat() { //Getter for the Foe Attack Stat
        return foeAttackStat;
    }
    public int getFoeDefenceStat() { //Getter for the Foe Defence Stat
        return foeDefenceStat;
    }
    public int getFoeHPStat() { //Getter for the Foe HP Stat
        return foeHPStat;
    }

    public int getChampionAttackStat() { //Getter for the Champion Attack Stat
        return championAttackStat;
    }
    public int getChampionDefenceStat() { //Getter for the Champion Defence Stat
        return championDefenceStat;
    }
    public int getChampionHPStat() { //Getter for the Champion HP Stat
        return championHPStat;
    }
    public int getChampionLevel() { //Getter for the Champion Level
        return championLevel;
    }
}
