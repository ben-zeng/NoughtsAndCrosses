import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * XOGui is the GUI class of the Noughts and Crosses application. It builds
 * and displays the application GUI
 * <p>
 * To start the application, create an object of this class.
 *
 * @author Ben Zeng (Various codes taken from Michael Kölling and David J. Barnes)
 * @version 1
 */
public class XOGui implements ActionListener {
    private JFrame frame;               //field for frame
    private JButton[] buttons;          //field for buttons array
    private JLabel status;              //field for status cell
    private JButton restartButton;      //field for restart button
    private JButton quitButton;         //field for quit
    private XOLogic xologic;            //field for xologic class
    private String stringN;             //field for noughts string
    private String stringC;             //field for crosses string

    /**
     * Create an XOgui show it on screen.
     */
    public XOGui(XOLogic xologic) {
        this.xologic = xologic;
        makeFrame();
    }

    /**
     * 'About' function: show the 'about' box.
     */
    private void showAbout() {
        JOptionPane.showMessageDialog(frame,
                "A wonderful game known as Noughts and Crosses or Tick Tack Toe\n" +
                        "Created using BlueJ, by BEN ZENG", "About Noughts and Crosses",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Receive notification of an action.
     *
     * @param event Details of the action.
     */
    public void actionPerformed(ActionEvent event) {

        if (event.getActionCommand().equals("About Noughts and Crosses")) {
            showAbout();
        }
        if (event.getActionCommand().equals("Restart")) {
            iniGame();
        }
        if (event.getActionCommand().equals("Quit")) {
            System.exit(0);
        }
    }

    /**
     * Create the Swing frame and its content.
     */
    public void makeFrame() {
        //Set window frame title, dimensions, and default actions
        frame = new JFrame("Noughts and Crosses");
        frame.setPreferredSize(new Dimension(400, 400));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Create menu bar on frame
        makeMenuBar(frame);

        //Set layout
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new GridLayout(4, 3));

        //Create buttons 
        button(contentPane);

        //initialise status label
        status = new JLabel("Turn: Noughts", SwingConstants.CENTER);

        //add to contentpane
        contentPane.add(restartButton);
        contentPane.add(status);
        contentPane.add(quitButton);

        //Arrange the components and initialise game 
        frame.pack();
        iniGame();
    }

    /**
     * Create the buttons and add listeners to buttons
     */
    public void button(Container contentPane) {
        JButton item;
        buttons = new JButton[9];
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton(" ");
            buttons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    JButton pressed = (JButton) (e.getSource()); //get source of j button pressed
                    String buttonText = pressed.getText();  //assign text of pressed button as button text

                    if (buttonText == "O" || buttonText == "X") {
                        //if button text is O or X, do nothing
                    } else {
                        //setGameOverTrue
                        if (xologic.gameOver() == true) {
                            //if game over boolean is true, do nothing
                        } else if (xologic.getNoughts() == true)   //if noughts
                        {
                            stringN = pressed.getText();        //set noughtsstring as text
                            xologic.addNought(stringN);         //add nought to nought array
                            pressed.setText("O");               //set button to O
                            status.setText("Turn: Crosses");    //set status to show crosses turn
                            xologic.setNoughtFalse();           //set nought boolean as false
                            xologic.checkNoughts();             //check to see if noughts has won
                            xologic.won();                      //display relavant status
                        } else if (xologic.getNoughts() == false)  //if crosses
                        {
                            stringC = pressed.getText();        //setcrossstring as text
                            xologic.addCross(stringC);          //add cross to cross array
                            pressed.setText("X");               //set button to X
                            status.setText("Turn: Noughts");    //set status to show noughts turn
                            xologic.setNoughtTrue();            //set noughts boolean as true
                            xologic.checkCrosses();             //check to see if cross has won
                            xologic.won();                      //display relavent status
                        }
                    }
                }
            });
            contentPane.add(buttons[i]);    //add buttons to content pane

        }

        //Create quit and restart buttons and attach listeners
        quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); //quit program
            }
        });
        restartButton = new JButton("Restart");
        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                iniGame(); //call initialise method to restart
            }
        });
    }

    /**
     * Initialise game
     */
    private void iniGame() {
        //Initialise booleans
        xologic.iniGameBoolean(); //resets booleans

        //Initialise text in buttons
        for (int i = 0; i < 9; i++) {
            char ch = (char) ('0' + i + 1);
            buttons[i].setText("" + ch);
        }

        //Initialise result label
        status.setText("Turn: Noughts");
        //set frame visable
        frame.setVisible(true);
    }

    /**
     * Create the main frame's menu bar.
     *
     * @param frame The frame that the menu bar should be added to.
     */
    private void makeMenuBar(JFrame frame) {
        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);

        // create the File menu
        JMenu fileMenu = new JMenu("File");
        menubar.add(fileMenu);

        JMenuItem restartItem = new JMenuItem("Restart");
        restartItem.addActionListener(this);
        fileMenu.add(restartItem);

        JMenuItem quitItem = new JMenuItem("Quit");
        quitItem.addActionListener(this);
        fileMenu.add(quitItem);

        JMenu helpMenu = new JMenu("Help");
        menubar.add(helpMenu);

        JMenuItem aboutItem = new JMenuItem("About Noughts and Crosses");
        aboutItem.addActionListener(this);
        helpMenu.add(aboutItem);
    }

    /**
     * Set status using string given in paramater
     */
    public void setStatus(String setStatus) {
        status.setText(setStatus);
    }

}

    