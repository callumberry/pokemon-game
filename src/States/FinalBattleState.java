/**
 * FinalBattleState.java
 * Purpose: This is the battle state, it takes the pokemon stats,
 *          your pokemon, a random wild pokemon, and more formulas.
 *          When you win battle you gain experience and in battles
 *          you can attack, heal. Once you beat the champion you have
 *          won the game but can still keep playing if you'd like
 * Author:  Callum Berry
 * Version: 1.0
 * Date:    January 23, 2020
 * Target:  Mr. DiTomasso
 */

package States;

//Imports
import PokemonGame.Assets;
import PokemonGame.Display;
import PokemonGame.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class FinalBattleState extends State{

    //Initializes all the variables
    private int width, height, mouseX, mouseY;
    private int attackBtnX, attackBtnY, healBtnX, healBtnY, okButtonX, okButtonY;
    private int maxPlayerHp, maxChampionHp, championHp, championChoice, potion, numberOfTimesHealed, foeNumHealed;
    private int playerDamage, championDamage, power, championExperience;
    private double playerAttack, playerDefence, championAttack, championDefence, aOverD;
    private boolean playerTurn, over, firstTurn;
    private BufferedImage playerPokemonSprite, championPokemonSprite;
    private String eventString, eventString2, playerPokemonName, championPokemonName;

    //Constructor that takes in a game and display object
    public FinalBattleState(Game game, Display display){
        super(game, display);

        //Sets firstTurn and playerTurn to true
        firstTurn = true;
        playerTurn = true;

        //Sets the various string values to an empty string
        eventString = "";
        eventString2 = "";
        State.setExperienceString("");
        State.setExperienceString2("");

        //Sets numberOfTimesHealed and foeNumHealed to 0
        numberOfTimesHealed = 0;
        foeNumHealed = 0;

        power = 50;     //Sets power to be 50
        potion = 10;        //Sets potion to be 10

        //Sets the name, sprite, attack, defence, and hp stats of the player pokemon from the Pokemon class
        playerPokemonName = (String) game.getPokemon().getPlayerPokemon()[0];
        playerPokemonSprite = (BufferedImage) game.getPokemon().getPlayerPokemon()[1];
        playerAttack = game.getPokemon().getPlayerAttackStat();
        playerDefence = game.getPokemon().getPlayerDefenceStat();
        maxPlayerHp = game.getPokemon().getPlayerHPStat();

        //Sets the name, sprite, attack, defence, experience, and hp stats of the foe pokemon from the Pokemon class
        championPokemonName = (String) game.getPokemon().getChampionPokemon()[0];
        championPokemonSprite = (BufferedImage) game.getPokemon().getChampionPokemon()[1];
        championAttack = game.getPokemon().getFoeAttackStat();
        championDefence = game.getPokemon().getFoeDefenceStat();
        maxChampionHp = game.getPokemon().getFoeHPStat();
        championExperience = (int)game.getPokemon().getWildPokemon()[6];
        championHp = maxChampionHp; //Sets the foeHp to it's maxHP

        //Sets the width and height for the buttons
        width = 300;
        height = 150;

        //Sets the x and y positions for the buttons
        attackBtnX = game.getWidth() - 2 * width - 100;
        attackBtnY = game.getHeight() - 2 * height- 100;
        healBtnX = game.getWidth() - width - 50;
        healBtnY = game.getHeight() - 2 * height - 100;
        okButtonX = game.getWidth() - width - 50;
        okButtonY = game.getHeight() - height- 50;


    }

    //Update method
    public void update() {
        //Checks to see if the boolean over is true
        if(over){
            resetBattle();//Runs the resetBattle method
        }

        //Checks to see if the boolean firstTurn is true
        if(firstTurn) {
            //Changes the stats for the player and wild pokemon
            changeStarterPokemon();
            changeChampionPokemon();
        }

        //Gets the x and y position of the mouse
        mouseX = game.getMouseInput().getMouseX();
        mouseY = game.getMouseInput().getMouseY();

        //Checks to see if the boolean leftPressed is true
        if(game.getMouseInput().isLeftPressed()){
            //Checks to see if boolean playerTurn is true
            if(playerTurn){
                eventString2 = ""; //Sets eventString2 to blank
                //Checks to see if the area of the attack button is clicked
                if (mouseX >= attackBtnX && mouseX <= attackBtnX + width) {
                    if (mouseY >= attackBtnY && mouseY <= attackBtnY + height) {
                        //Plays the attack and click sfx
                        Assets.sfxClick();
                        Assets.sfxAttack();
                        playerAttack(); //Runs the playerAttack method
                        playerTurn = false;//Sets playerTurn to false
                    }
                }
                //Checks to see if the area of the heal button is clicked
                if (mouseX >= healBtnX && mouseX <= healBtnX + width) {
                    if (mouseY >= healBtnY && mouseY <= healBtnY + height) {
                        //Plays the click sfx
                        Assets.sfxClick();

                        //Checks to see if the numberOfTimesHealed is <= 2; limits the amount of healing
                        if(numberOfTimesHealed <= 2) {
                            Assets.sfxHeal(); //Plays the heal sfx
                            playerHeal();  //Runs the playerHeal method
                            numberOfTimesHealed++;//Adds one to the value of numberOfTimesHealed++;
                            playerTurn = false; //Sets playerTurn to false
                        }
                        else{
                            eventString = "You Have No Potions Left";//Changes the value of eventString
                        }
                    }
                }
            }
            else{
                //Checks to see if the area of the ok button is clicked
                if (mouseX >= okButtonX && mouseX <= okButtonX + width) {
                    if (mouseY >= okButtonY && mouseY <= okButtonY + height) {
                        Assets.sfxClick();//Plays the click sfx
                        foeMove();//Runs the foeMove method
                        playerTurn = true;//Sets playerTurn to true;
                    }
                }
            }
            //Sets leftPressed to false
            game.getMouseInput().setLeftPressed(false);
        }
        //Sets firstTurn to false
        firstTurn = false;
    }

    //Render method
    public void render(Graphics g){
        //Sets the background by setting the colour to light gray and filling a rectangle
        g.setColor(Color.lightGray);
        g.fillRect(0,0,game.getWidth(),game.getHeight());

        //Draws the player pokemon sprite to the canvas
        g.drawImage(playerPokemonSprite, game.getWidth() / 4, game.getHeight() / 2 - 3 * playerPokemonSprite.getHeight() * 8 / 4,
                playerPokemonSprite.getWidth() * 8, playerPokemonSprite.getHeight() * 8, null);

        //Draws the champion pokemon sprite to the canvas
        g.drawImage(championPokemonSprite, 3 * game.getWidth() / 4 - championPokemonSprite.getWidth() * 8, game.getHeight() / 2 - 3 * playerPokemonSprite.getHeight() * 8 / 4,
                championPokemonSprite.getWidth() * 8, championPokemonSprite.getHeight() * 8, null);

        //Checks to see if it's the player's turn
        if(playerTurn) {
            //Draws the attack and heal buttons at the same spot as where the corresponding area that checks if its been clicked
            g.drawImage(Assets.attackBtn, attackBtnX, attackBtnY, width, height, null);
            g.drawImage(Assets.healBtn, healBtnX, healBtnY, width, height, null);
        }
        else{
            //If not the player's turn it draws the ok button instead
            g.drawImage(Assets.okButton, okButtonX, okButtonY, width, height, null);
        }

        //Sets the colour to dark gray and changes the font
        g.setColor(Color.darkGray);
        g.setFont(new Font("Monospaced", Font.BOLD, 48));

        //Draws the string with the player's pokemon name, level, and HP to the canvas
        g.drawString(playerPokemonName + "   " + "Lv" + game.getPokemon().getPlayerLevel(), game.getWidth() / 4, 100);
        g.drawString("Player Hp: " + State.getCurrentPlayerHP() + "/" + maxPlayerHp, game.getWidth() / 4, 200);

        //Changes the font
        g.setFont(new Font("Monospaced", Font.BOLD, 24));

        //Draws the string "Champion's" to the canva
        g.drawString("Champion's",3 * game.getWidth() / 4 - championPokemonSprite.getWidth() * 8, 50);

        //Changes the font
        g.setFont(new Font("Monospaced", Font.BOLD, 48));

        //Draws the string with the champion pokemon's name, level, and HP to the canvas
        g.drawString( "Geodude" + "   " + "Lv" + game.getPokemon().getChampionLevel(), 3 * game.getWidth() / 4 - championPokemonSprite.getWidth() * 8, 100);     /**Change to make the level close to the player level*/
        g.drawString("Foe Hp: " + championHp + "/" + maxChampionHp, 3 * game.getWidth() / 4 - championPokemonSprite.getWidth() * 8, 200);

        //Draws both eventStrings; this are used for telling the player what is going on in the battle
        g.drawString(eventString, game.getWidth() / 6, game.getHeight() - 300);
        g.drawString(eventString2, game.getWidth() / 6, game.getHeight() - 200);
    }

    public void playerAttack(){ //playerAttack method
        aOverD = playerAttack / championDefence; //Takes the player's attack divided by the champion's defence
        playerDamage = (int)(((2 * game.getPokemon().getPlayerLevel()) / 5 + 2) * power * aOverD) / 50 + 2;//Formula for calculating the damage done by the player's pokemon

        championHp -= playerDamage;  //Subtracts the player's damage from the foe's hp
        eventString = playerPokemonName + " Attacks";//Sets the eventString to say the player attacks

        winCheck();  //Runs the winCheck method
    }
    public void playerHeal(){     //playerHeal method
        State.setCurrentPlayerHP(State.getCurrentPlayerHP() + potion);//Gets the current player HP and adds the value of a potion to it

        //Check to see is the player's HP has surpassed the max HP it can have
        if(maxPlayerHp <= State.getCurrentPlayerHP()){
            State.setCurrentPlayerHP(maxPlayerHp);    //Sets the player's HP to the max HP
        }
        eventString = playerPokemonName + " uses a potion";//Sets the eventString to say the player heals
    }

    public void foeMove(){//foeMove method
        //Creates a random number
        Random rand = new Random();
        championChoice = rand.nextInt(10);

        //Checks to see if the foe's HP is above 0
        if(championHp > 0) {
            //Check to see if the random number is equal to 1
            if(championChoice == 1) {
                //Checks to see if the value of foeNumHealed is <=2; this will limit the amount of times it can heal
                if(foeNumHealed <= 2) {
                foeHeal();//Runs the foeHeal method
                foeNumHealed++; //Adds one to the value of foeNumHealed
                }
                else{
                    foeFight();//Runs the foeFight method if out of heals
                }
            }
            else{
                foeFight();  //Runs the foeFight method
            }
        }
    }
    public void foeFight(){ //foeFight method
        aOverD = championAttack / playerDefence;//Takes the foe's attack divided by the players's defence
        championDamage = (int)(((2 * game.getPokemon().getPlayerLevel()) / 5 + 2) * power * aOverD) / 50 + 2;//Formula for calculating the damage done by the foe's pokemon

        State.setCurrentPlayerHP(State.getCurrentPlayerHP() - championDamage); //Subtracts the foe's damage from the player's hp
        Assets.sfxAttack(); //Plays the attack sfx
        winCheck(); //Rns the winCheck method
        eventString = championPokemonName + " Attacks";//Sets the eventString to say the foe attacks
    }
    public void foeHeal(){//foeHeal method
        championHp += potion; //Adds the value of a potion to the champion's HP
        Assets.sfxHeal();//Plays heal sfx

        //Check to see is the foe's HP has surpassed the max HP it can have
        if(maxChampionHp <= championHp){
            championHp = maxChampionHp;//Sets the foe's HP to the max HP
        }
        eventString = championPokemonName + " uses";//Sets the eventString to say the foe heals
        eventString2 = "a Sitrus Berry";//Sets the eventString2 to say the foe heals
    }

    public void experienceGained(){ //experienceGained method
        State.setExperienceGained((int)((double)championExperience * (double)game.getPokemon().getChampionLevel()) / 7); //Formula for the experience gained; take in the wild pokemon's level and their base experience points
        State.setTotalExperience(State.getTotalExperience() + State.getExperienceGained()); //Sets the total experience to the total experience plus the experience gained

        //Sets the string for experienceGained and totalExperience to the values just calculated
        State.setExperienceString("Experience Gained: " + State.getExperienceGained());
        State.setExperienceString2("Total Experience: " + State.getTotalExperience());
        System.out.println(State.getTotalExperience());
    }

    public void winCheck(){   //winCheck method
        //Checks to see if the foe's HP is <= 0
        if(championHp <= 0){  //Win
            State.setWin(true);  //Set boolean win to true
            over = true;//Set boolean over to true
            experienceGained(); //Run the experienceGained method
            game.getMouseInput().setLeftPressed(false);//Sets leftPressed to false
            State.setState(game.postChampionState);   //Sets the game state to the postChampionState
        }
        //Checks to see if the player's HP is <= 0
        if(State.getCurrentPlayerHP() <= 0){   //Lose
            State.setWin(false); //Set boolean win to false
            over = true; //Set boolean over to true

            //Sets the string for experienceGained and totalExperience to 0 and the total experience
            State.setExperienceString("Experience Gained: " + 0);
            State.setExperienceString2("Total Experience: " + State.getTotalExperience());

            game.getMouseInput().setLeftPressed(false);//Sets leftPressed to false
            State.setState(game.postChampionState); //Sets the game state to the postChampionState

        }
    }
    public void resetBattle(){//resetBattle method
        //Sets the eventStrings to be blank
        eventString = "";
        eventString2 = "";

        championHp =  maxChampionHp; //Sets the foe's HP to the max

        //Sets boolean over, win, and runAway to false
        over = false;
        State.setWin(false);
        State.setRunAway(false);

        //Sets playerTurn and firstTurn to true
        playerTurn = true;
        firstTurn = true;

        //Sets numberOfTimesHealed, foeNumHealed, and experienceGained to 0
        numberOfTimesHealed = 0;
        foeNumHealed = 0;
        State.setExperienceGained(0);
    }

    public void changeStarterPokemon(){//ChangeStarterPokemon method
        game.getPokemon().playerStatCalc();//Runs the playerStatCalc method

        //Sets the name, sprite, attack, defence, and hp stats of the player pokemon from the Pokemon class
        playerPokemonName = (String) game.getPokemon().getPlayerPokemon()[0];
        playerPokemonSprite = (BufferedImage) game.getPokemon().getPlayerPokemon()[1];
        playerAttack = game.getPokemon().getPlayerAttackStat();
        playerDefence = game.getPokemon().getPlayerDefenceStat();
        maxPlayerHp = game.getPokemon().getPlayerHPStat();
    }

    public void changeChampionPokemon(){//ChangeWildPokemon method
        game.getPokemon().getChampionPokemon();//Runs the wildPokemonChooser method
        game.getPokemon().championPokemonStatCalc();//Runs the wildPokemonStatCalc method

        //Sets the name, sprite, attack, defence, hp, and experience stats of the wild pokemon from the Pokemon class
        championPokemonName = (String) game.getPokemon().getChampionPokemon()[0];
        championPokemonSprite = (BufferedImage) game.getPokemon().getChampionPokemon()[1];
        championAttack = game.getPokemon().getChampionAttackStat();
        championDefence = game.getPokemon().getChampionDefenceStat();
        maxChampionHp = game.getPokemon().getChampionHPStat();
        championExperience = (int)game.getPokemon().getChampionPokemon()[6];

        championHp = maxChampionHp;  //Sets the value of foeHp to maxFoeHp
    }
}
