import java.util.ArrayList;

/**
 * XOLogic handles all the game logic
 *
 * @author Zheng Xi Benjamin Zeng (ZXBZ2) (Various codes taken from Michael Kölling and David J. Barnes)
 * @version 1
 */

public class XOLogic {
    XOGui xogui = new XOGui(this);                              //create an XOGui
    ArrayList<String> noughtsArray = new ArrayList<String>();   //Arraylist for noughts pressed
    ArrayList<String> crossesArray = new ArrayList<String>();   //Arraylist for crosses pressed
    private boolean noughts;                                    //boolean to tell whether  it is currently noughts
    private boolean crosses;                                    //crosses and gameover
    private boolean gameOver;

    public static void main(String[] args) {
        new XOLogic();
    }

    public XOLogic() {

    }

    public boolean gameOver() {
        return gameOver;    //returns current gameover boolean
    }

    public boolean getNoughts() {
        return noughts;     //returns current noughts boolean
    }

    public void clearArrays()   //clear noughts and crosses arrays
    {
        noughtsArray.clear();
        crossesArray.clear();
    }

    public void setNoughtFalse() {
        noughts = false;    //set noughts boolean as false
    }

    public void setNoughtTrue() {
        noughts = true;     //set noughts voolean as true
    }

    public void setGameOverTrue() {
        gameOver = true;    //set game over boolean as true
    }

    public void iniGameBoolean()    //initial booleans when restarted game
    {
        noughts = true;   //noughts is true and game over is false
        gameOver = false;
    }

    public void addNought(String string) {
        noughtsArray.add(string);   //add string to noughts array
    }

    public void addCross(String string) {
        crossesArray.add(string);   //add string to crosses array
    }

    /**
     * method to check whether crosses have won
     * by comparing all possible iterations of winning with what is in crosses array
     */
    public boolean checkCrosses() {
        if (crossesArray.contains("1") && crossesArray.contains("2") && crossesArray.contains("3")) {
            return true;
        } else if (crossesArray.contains("4") && crossesArray.contains("5") && crossesArray.contains("6")) {
            return true;
        } else if (crossesArray.contains("7") && crossesArray.contains("8") && crossesArray.contains("9")) {
            return true;
        } else if (crossesArray.contains("1") && crossesArray.contains("4") && crossesArray.contains("7")) {
            return true;
        } else if (crossesArray.contains("2") && crossesArray.contains("5") && crossesArray.contains("8")) {
            return true;
        } else if (crossesArray.contains("3") && crossesArray.contains("6") && crossesArray.contains("9")) {
            return true;
        } else if (crossesArray.contains("1") && crossesArray.contains("5") && crossesArray.contains("9")) {
            return true;
        } else if (crossesArray.contains("7") && crossesArray.contains("5") && crossesArray.contains("3")) {
            return true;
        }
        return false;
    }

    /**
     * method to check whether noughts have won
     * by comparing all possible iterations of winning with what is in noughts array
     */
    public boolean checkNoughts() {
        if (noughtsArray.contains("1") && noughtsArray.contains("2") && noughtsArray.contains("3")) {
            return true;
        } else if (noughtsArray.contains("4") && noughtsArray.contains("5") && noughtsArray.contains("6")) {
            return true;
        } else if (noughtsArray.contains("7") && noughtsArray.contains("8") && noughtsArray.contains("9")) {
            return true;
        } else if (noughtsArray.contains("1") && noughtsArray.contains("4") && noughtsArray.contains("7")) {
            return true;
        } else if (noughtsArray.contains("2") && noughtsArray.contains("5") && noughtsArray.contains("8")) {
            return true;
        } else if (noughtsArray.contains("3") && noughtsArray.contains("6") && noughtsArray.contains("9")) {
            return true;
        } else if (noughtsArray.contains("1") && noughtsArray.contains("5") && noughtsArray.contains("9")) {
            return true;
        } else if (noughtsArray.contains("7") && noughtsArray.contains("5") && noughtsArray.contains("3")) {
            return true;
        }
        return false;
    }

    /**
     * method return strings relavant strings and clear arrays when noughts or
     * crosses have won, and sets gameover boolean as true.
     */
    public void won() {
        if (checkNoughts() == true && checkCrosses() == false) {
            xogui.setStatus("Noughts win");
            clearArrays();
            setGameOverTrue();
        } else if (checkCrosses() == true && checkNoughts() == false) {
            xogui.setStatus("Crosses win");
            clearArrays();
            setGameOverTrue();
        } else if (checkNoughts() == false && checkCrosses() == false && (noughtsArray.size() == 5 || crossesArray.size() == 5)) {
            xogui.setStatus("DRAW");
            clearArrays();
            setGameOverTrue();
        } else {
            return;
        }

    }

}
