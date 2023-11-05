/**
 * MouseInput.java
 * Purpose: This class contains all code getting mouse input,
 *          and then sends that information to all classes that
 *          need it.
 * Author: Callum Berry
 * Version: 1.0
 * Target: Mr. DiTomasso
 */

package PokemonGame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {
    private int mouseX, mouseY; //Create ints for mouseX and Y, as well as a boolean for if the mouse is pressed down or not
    private boolean leftPressed;

    public MouseInput(){ //Constructor is empty, but it is still required for the class

    }

    //Getters for leftPressed, mouseX and mouseY
    public boolean isLeftPressed(){
        return leftPressed;
    }
    public void setLeftPressed(boolean leftPressed) {
        this.leftPressed = leftPressed;
    }

    public int getMouseX(){
        return mouseX;
    }
    public int getMouseY(){
        return mouseY;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){ //Every time the mouse is pressed, make leftPressed = true (this avoids accidentally clicking the same button on 2 different screns)
            leftPressed = true;
        }
    }
    @Override
    public void mouseMoved(MouseEvent e) { //Every time the mouse is moved, update the X and Y positions
        mouseX = e.getX();
        mouseY = e.getY();
    }
}
