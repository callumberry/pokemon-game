/**
 * Assets.java
 * Purpose: This class contains all code concerning the assets the
 *          game uses. This class defines every image, sound, and
 *          music effect used in the game.
 * Author: Owen Gallagher, Callum Berry, Jack Santoro
 * Version: 1.0
 * Target: Mr. DiTomasso
 */

package PokemonGame;

//Imports
import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Assets {

    //Initializes the BufferedImages for every sprite used in the game
    public static BufferedImage playerHeadUp, playerHeadDown, playerHeadLeft, playerHeadRight, characterSprite;
    public static BufferedImage grassOne, grassTwo, grassThree, tallGrass, blankGrass, bottomLeftTree, bottomRightTree,bush,
                                middleLeftTree, middleRightTree, topLeftTree, topRightTree;
    public static BufferedImage playBtn, optionsBtn, exitBtn, menuBtn, doneBtn, pokemonLogo, track1Btn, track2Btn, selectBtn,
                                continueButton, track3Btn, track4Btn, attackBtn, runBtn, healBtn, okButton, stopBtn;
    public static BufferedImage pokeCenter1, pokeCenter2, pokeCenter3, pokeCenter4, pokeCenter5, pokeCenter6, pokeCenter7,
                                pokeCenter8, pokeCenter9, pokeCenter10, pokeCenter11, pokeCenter12, pokeCenter13, pokeCenter14,
                                pokeCenter15, pokeCenter16, pokeCenter17, pokeCenter18, pokeCenter19, pokeCenter20, pokeCenter21,
                                pokeCenter22, pokeCenter23, pokeCenter24, pokeCenter25;
    public static BufferedImage building1, building2, building3, building4, building5, building6, building7, building8, building9,
                                building10, building11, building12, building13, building14, building15, building16, building17, building18,
                                building19, building20, building21, building22, building23, building24, building25, building26, building27, building28;
    public static BufferedImage leftPath, rightPath, pathCornerTopRight, pathCornerBottomRight, pathCornerBottomLeft, pathCornerTopLeft, pathInnerCornerTopLeft,
                                pathInnerCornerBottomLeft, pathInnerCornerBottomRight, pathInnerCornerTopRight, pathSideTop, pathSideBottom;
    public static BufferedImage charmander, bulbasaur, squirtle, clefairy, eevee, geodude, growlithe, pidgey, pikachu, rattata;
    public static BufferedImage[] player_down, player_up, player_left, player_right, player_neutral; //define BufferedImage objects for each of the sets of animations
    public static String track1, track2, track3, track4, click, walk, attack, heal, levelUp, pokeCenter; //Define strings that contain the files of all music and audio effects used the game
    public static Clip clip, sfxClip; //Define separate clipping systems for the music and the SFX

    //Create the try/catch statement for the loadImage function
    public static BufferedImage loadImage(String path){
        try {
            return ImageIO.read(Assets.class.getClassLoader().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Create the try/catch statement for the music playing system
    public static AudioInputStream loadAudio(String path){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Assets.class.getClassLoader().getResource(path));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);

        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        return  null;
    }

    //Create the try/catch statement for the SFX playing system
    public static AudioInputStream loadAudiosfx(String path){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Assets.class.getClassLoader().getResource(path));
            sfxClip = AudioSystem.getClip();
            sfxClip.loop(0); //Set the SFX to only play once
            sfxClip.open(audioInputStream);

        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        return  null;
    }
    public static void track1(){ //The code that plays track 1
        loadAudio(track1); //Load the proper string
        clip.loop(Clip.LOOP_CONTINUOUSLY); //Set the clip to loop
        clip.start(); //Start the clip
    }
    public static void track2(){ //The code that plays track 2
        loadAudio(track2);//Load the proper string
        clip.loop(Clip.LOOP_CONTINUOUSLY); //Set the clip to loop
        clip.start(); //Start the clip
    }
    public static void track3(){ //The code that plays track 3
        loadAudio(track3); //Load the proper string
        clip.loop(Clip.LOOP_CONTINUOUSLY); //Set the clip to loop
        clip.start(); //Start the clip
    }
    public static void track4(){ //The code that plays track 4
        loadAudio(track4); //Load the proper string
        clip.loop(Clip.LOOP_CONTINUOUSLY); //Set the clip to loop
        clip.start(); //Start the clip
    }
    public static void stopTracks(){
        clip.stop();
    } //The code that stops the music

    public static void sfxClick(){ //The code that plays the click SFX
        loadAudiosfx(click); //Load the proper string
        sfxClip.start(); //Start the clip
    }
    public static void sfxAttack(){ //The code that plays the attack SFX
        loadAudiosfx(attack); //Load the proper string
        sfxClip.start(); //Start the clip
    }
    public static void sfxHeal(){ //The code that plays the heal SFX
        loadAudiosfx(heal); //Load the proper string
        sfxClip.start(); //Start the clip
    }
    public static void sfxLevelUp(){ //The code that plays the Level Up SFX
        loadAudiosfx(levelUp); //Load the proper string
        sfxClip.start(); //Start the clip
    }
    public static void sfxPokeCenter(){ //The code that plays the PokeCenter SFX
        loadAudiosfx(pokeCenter); //Load the proper string
        sfxClip.start(); //Start the clip
    }

    public static void initialization(){
        player_down = new BufferedImage[4]; //Define the proper size of the array that holds each of the 5 animation sets
        player_up = new BufferedImage[4];
        player_left = new BufferedImage[4];
        player_right = new BufferedImage[4];
        player_neutral = new BufferedImage[4];

        track1 = "Track1.wav"; //Define the strings that contain all the music and audio in the game
        track2 = "Track2.wav";
        track3 = "Track3.wav";
        track4 = "Track4.wav";
        click = "Click_sfx.wav";
        walk = "WalkSound_sfx.wav";
        attack = "Attack_sfx.wav";
        heal = "Heal_sfx.wav";
        levelUp = "LevelUp_sfx.wav";
        pokeCenter = "PokeCenter_sfx.wav";

        characterSprite = loadImage("character/Character_Sprite.png"); //Define the full sized character sprite (mainly used for sizing)

        playerHeadDown = loadImage("character/Character_Head.png"); //Define all elements of the down animation, including the head and all 4 frames of animation
        player_down[0] = loadImage("character/walking_down_body1.png");
        player_down[1] = loadImage("character/walking_down_body2.png");
        player_down[2] = loadImage("character/walking_down_body3.png");
        player_down[3] = loadImage("character/walking_down_body2.png");

        playerHeadUp = loadImage("character/Player_Head_Up.png"); //Define all elements of the up animation, including the head and all 4 frames of animation
        player_up[0] = loadImage("character/Walking_Up_1.png");
        player_up[1] = loadImage("character/Walking_Up_2.png");
        player_up[2] = loadImage("character/Walking_Up_3.png");
        player_up[3] = loadImage("character/Walking_Up_2.png");

        playerHeadLeft = loadImage("character/player_head_left.png"); //Define all elements of the left animation, including the head and all 4 frames of animation
        player_left[0] = loadImage("character/walking_left_1.png");
        player_left[1] = loadImage("character/walking_left_2.png");
        player_left[2] = loadImage("character/walking_left_3.png");
        player_left[3] = loadImage("character/walking_left_2.png");

        playerHeadRight = loadImage("character/player_head_right.png"); //Define all elements of the right animation, including the head and all 4 frames of animation
        player_right[0] = loadImage("character/walking_right_1.png");
        player_right[1] = loadImage("character/walking_right_2.png");
        player_right[2] = loadImage("character/walking_right_3.png");
        player_right[3] = loadImage("character/walking_right_2.png");

        player_neutral[0] = loadImage("character/Character_Body.png"); //Define all elements of the neutral animation, including all 4 frames of animation (neutral animation uses the head of the down animation)
        player_neutral[1] = loadImage("character/Character_Body.png");
        player_neutral[2] = loadImage("character/Character_Body.png");
        player_neutral[3] = loadImage("character/Character_Body.png");


        grassOne = loadImage("grass/Grass_One.png"); //Define all images associated with the grass, including all 3 generic tiles, tall grass, and bushes
        grassTwo = loadImage("grass/Grass_Two.png");
        grassThree = loadImage("grass/Grass_Three.png");
        tallGrass = loadImage("grass/Tall_Grass.png");
        blankGrass = loadImage("grass/Blank_Grass.png");
        bush = loadImage("Bush.png");

        bottomLeftTree = loadImage("tree/Bottom_Left_Tree.png"); //Define all 6 images that make up a single tree
        bottomRightTree = loadImage("tree/Bottom_Right_Tree.png");
        middleLeftTree = loadImage("tree/Middle_Left_Tree.png");
        middleRightTree = loadImage("tree/Middle_Right_Tree.png");
        topLeftTree = loadImage("tree/Top_Left_Tree.png");
        topRightTree = loadImage("tree/Top_Right_Tree.png");

        leftPath = loadImage("path/Left_Path.png"); //Define all images that make up the set of pathway tiles, including all the curves and endpoints
        rightPath = loadImage("path/Right_Path.png");
        pathCornerTopRight = loadImage("path/PathCorner1.png");
        pathCornerBottomRight = loadImage("path/PathCorner2.png");
        pathCornerBottomLeft = loadImage("path/PathCorner3.png");
        pathCornerTopLeft = loadImage("path/PathCorner4.PNG");
        pathInnerCornerTopLeft = loadImage("path/PathInnerCorner1.png");
        pathInnerCornerBottomLeft = loadImage("path/PathInnerCorner2.PNG");
        pathInnerCornerBottomRight = loadImage("path/PathInnerCorner3.png");
        pathInnerCornerTopRight = loadImage("path/PathInnerCorner4.PNG");
        pathSideTop = loadImage("path/PathSide1.PNG");
        pathSideBottom = loadImage("path/PathSide2.PNG");

        pokemonLogo = loadImage("Pokemon_Logo.png"); //Define the Pokemon Logo

        optionsBtn = loadImage("buttons/Option_Button.png"); //Define all button images to be used in the main menu
        menuBtn = loadImage("buttons/Menu_Button.png");
        doneBtn = loadImage("buttons/Done_Button.png");
        exitBtn = loadImage("buttons/Exit_Button.png");

        track1Btn = loadImage("buttons/Track1_Button.png"); //Define all button images to be used in the track selector
        track2Btn = loadImage("buttons/Track2_Button.png");
        track3Btn = loadImage("buttons/Track3_Button.png");
        track4Btn = loadImage("buttons/Track4_Button.png");
        stopBtn = loadImage("buttons/Stop_Button.png");
        playBtn = loadImage("buttons/Play_Button.png");
        continueButton = loadImage("buttons/Continue_Button.png");

        attackBtn = loadImage("buttons/Attack_Button.png"); //Define all button images to be used in the battle system
        healBtn = loadImage("buttons/Heal_Button.png");
        runBtn = loadImage("buttons/Run_Button.png");
        okButton = loadImage("buttons/Ok_Button.png");
        selectBtn = loadImage("buttons/Select_Button.png");

        charmander = loadImage("pokemonSprites/Charmander.png"); //Define the sprites of all 10 Pokemon
        bulbasaur = loadImage("pokemonSprites/Bulbasaur.png");
        clefairy = loadImage("pokemonSprites/Clefairy.png");
        eevee = loadImage("pokemonSprites/Eevee.png");
        geodude = loadImage("pokemonSprites/Geodude.png");
        growlithe = loadImage("pokemonSprites/Growlithe.png");
        pidgey = loadImage("pokemonSprites/Pidgey.png");
        pikachu = loadImage("pokemonSprites/Pikachu.png");
        rattata = loadImage("pokemonSprites/Rattata.png");
        squirtle = loadImage("pokemonSprites/Squirtle.png");

        pokeCenter1 = loadImage("pokeCenter/PC1.png"); //Define the sprites of all 25 tiles that make up the PokeCenter
        pokeCenter2 = loadImage("pokeCenter/PC2.png");
        pokeCenter3 = loadImage("pokeCenter/PC3.png");
        pokeCenter4 = loadImage("pokeCenter/PC4.png");
        pokeCenter5 = loadImage("pokeCenter/PC5.png");
        pokeCenter6 = loadImage("pokeCenter/PC6.png");
        pokeCenter7 = loadImage("pokeCenter/PC7.png");
        pokeCenter8 = loadImage("pokeCenter/PC8.png");
        pokeCenter9 = loadImage("pokeCenter/PC9.png");
        pokeCenter10 = loadImage("pokeCenter/PC10.png");
        pokeCenter11 = loadImage("pokeCenter/PC11.png");
        pokeCenter12 = loadImage("pokeCenter/PC12.png");
        pokeCenter13 = loadImage("pokeCenter/PC13.png");
        pokeCenter14 = loadImage("pokeCenter/PC14.png");
        pokeCenter15 = loadImage("pokeCenter/PC15.png");
        pokeCenter16 = loadImage("pokeCenter/PC16.png");
        pokeCenter17 = loadImage("pokeCenter/PC17.png");
        pokeCenter18 = loadImage("pokeCenter/PC18.png");
        pokeCenter19 = loadImage("pokeCenter/pc19.png");
        pokeCenter20 = loadImage("pokeCenter/pc20.png");
        pokeCenter21 = loadImage("pokeCenter/pc21.png");
        pokeCenter22 = loadImage("pokeCenter/pc22.png");
        pokeCenter23 = loadImage("pokeCenter/PC23.png");
        pokeCenter24 = loadImage("pokeCenter/PC24.png");
        pokeCenter25 = loadImage("pokeCenter/PC25.png");

        building1 = loadImage("building/Building1.png"); //Define all 28 tiles that make up the Champion Building
        building2 = loadImage("building/Building2.png");
        building3 = loadImage("building/Building3.png");
        building4 = loadImage("building/Building4.png");
        building5 = loadImage("building/Building5.png");
        building6 = loadImage("building/Building6.png");
        building7 = loadImage("building/Building7.png");
        building8 = loadImage("building/Building8.png");
        building9 = loadImage("building/Building9.png");
        building10 = loadImage("building/Building10.png");
        building11 = loadImage("building/Building11.png");
        building12 = loadImage("building/Building12.png");
        building13 = loadImage("building/Building13.png");
        building14 = loadImage("building/Building14.png");
        building15 = loadImage("building/Building15.png");
        building16 = loadImage("building/Building16.png");
        building17 = loadImage("building/Building17.png");
        building18 = loadImage("building/Building18.png");
        building19 = loadImage("building/Building19.png");
        building20 = loadImage("building/Building20.png");
        building21 = loadImage("building/Building21.png");
        building22 = loadImage("building/Building22.png");
        building23 = loadImage("building/Building23.png");
        building24 = loadImage("building/Building24.png");
        building25 = loadImage("building/Building25.png");
        building26 = loadImage("building/Building26.png");
        building27 = loadImage("building/Building27.png");
        building28 = loadImage("building/Building28.png");
    }

}