package view;

import model.GameInfoProvider;
import model.GameObserver;
import util.PropertiesDiskStorage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class newInvadePanel extends GraphicsPanel implements GameObserver {

    /**
     * object for game information
     */
    private GameInfoProvider gameInfoProvider;

    /**
     * the counter for invader
     */
    private int InvaderCounter;

    /**
     * the list of image
     */
    private List<BufferedImage> ImageList;

    /**
     * iterator for image
     */
    private Iterator<BufferedImage> ImageIterator;

    private List<String> imageNames;

    /**
     * save name in list
     */
    private List<String> InvaderImageNames;

    /**
     *
     * @param gameInfoProvider information of the game
     */

    public newInvadePanel(GameInfoProvider gameInfoProvider){
        this.gameInfoProvider = gameInfoProvider;
        InvaderImageNames = PropertiesDiskStorage.getInstance().getProperties("invader");
        InvaderCounter = gameInfoProvider.getInvaderSize();
        gameInfoProvider.addObserver(this);
        ImageList = new LinkedList<BufferedImage>();
        gameInfoProvider.addObserver(this::gameChanged);
        //for (String name : spaceInvaderImageNames)
        //ImageList.add(ImageCache.getInstance().getImage(name));
        ImageIterator = ImageList.iterator();
        //Count = gameInfo.getInvaderSize();
    }
    /**
     * When the game changes, repaint the view.
     */
    @Override
    public void gameChanged() {
        int currentCount = gameInfoProvider.getInvaderSize();
        if (currentCount != InvaderCounter) {
            InvaderCounter = currentCount;
            repaint();
        }
    }


    /**
     * Paint the space invader image on the panel including the background.
     * @param g the graphics to use to paint the view
     */
    @Override
    public synchronized void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D Graphics = (Graphics2D) g;
        Graphics.setPaint(Color.BLACK);
        Graphics.fillRect(0, 0, getWidth(), getHeight());
        if(! ImageIterator.hasNext()){
            ImageIterator= ImageList.iterator();
        }
        /** Update space invaders remaining count */
        Paint Color = Graphics.getPaint();
        Graphics.setPaint(java.awt.Color.GREEN);
        Graphics.drawString("The number of invaders are " + InvaderCounter, 20, 110);
        Graphics.setPaint(Color);
        if(gameInfoProvider.getInvaderSize()%2 == 0){
            drawImage(90 ,50, 50, 50, InvaderImageNames.get(0) ,Graphics);}
        else {
            drawImage(90 ,50, 50, 50,InvaderImageNames.get(1) ,Graphics);

        }
    }

    private static final long serialVersionUID = 1;
}
