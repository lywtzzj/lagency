package view;

import control.Controller;
import model.GameInfoProvider;

import javax.swing.*;
import java.awt.*;

public class newInvadeFrame extends JFrame {
    /**
     * Initialize the frame for the supplemental window that shows up alongside the game while
     * it is being played.
     */
    public newInvadeFrame(GameInfoProvider gameInfoProvider) {
        setTitle("Invader :(");
        setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        /*
         * Width and height passed in are those for the game panel, so add enough height for the
         * title bar and the information panel, and enough width for the border.
         */
        setSize(250, 175);
        add(new newInvadePanel(gameInfoProvider));
        //setLocation(Controller.WIDTH + X_WINDOW_GAP, Controller.HEIGHT / 4);
        setResizable(true);
    }

}
