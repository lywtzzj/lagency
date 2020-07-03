///**
// * @author: Yuwen Liu
// * @vision 1.0
// * yul905
// * 11219371
// * CMPT270
// */
//
//package view;
//
//import model.GameInfoProvider;
//import model.GameObserver;
//import util.PropertiesDiskStorage;
//
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.util.Iterator;
//import java.util.List;
//
//public class InvadePanel extends GraphicsPanel implements GameObserver {
//    /**
//     * object for game information
//     */
//    private GameInfoProvider gameInfoProvider;
//
//    /**
//     * the counter for invader
//     */
//    private int InvaderCounter;
//
//    /**
//     * the list of image
//     */
//    private List<BufferedImage> ImageList;
//
//    /**
//     * iterator for image
//     */
//    private Iterator<BufferedImage> ImageIterator;
//
//    private List<String> imageNames;
//
//    /**
//     * save name in list
//     */
//    private List<String> InvaderImageNames;
//
//    /**
//     * Initialize the panel for game information
//     * @param gameInfoProvider
//     */
//    public InvadePanel(GameInfoProvider gameInfoProvider){
//        this.gameInfoProvider = gameInfoProvider;
//        imageNames = PropertiesDiskStorage.getInstance().getProperties("invader");
//        gameInfoProvider.addObserver(this::gameChanged);
//        InvaderCounter = gameInfoProvider.getInvaderSize();
//    }
//
//    /**
//     * The action to perform when the game changes.
//     */
//    public void gameChanged() {
//        int newCount = gameInfoProvider.getInvaderSize();
//        if (newCount != InvaderCounter) {
//            InvaderCounter = newCount;
//            repaint();
//        }
//    }
//
//
//    /**
//     * A class used to draw the graph of the invader panel
//     * @param g Graphics for using draw graph
//     */
//
//    public synchronized void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        Graphics2D g2 = (Graphics2D) g;
//        g2.setPaint(Color.BLACK);
//        g2.fillRect(0,0,getWidth(),getHeight() );
//
//
//
//
//        /** Update space invaders remaining count */
//        g2.setPaint(Color.GREEN);
//        g2.drawString("Invaders remain: "+InvaderCounter,30,122);
//
//        if(gameInfoProvider.getInvaderSize()%2 == 0){
//            drawImage(0 ,0, 100, 100,imageNames.get(0) ,g2);}
//        else {
//            drawImage(0 ,0, 100, 100,imageNames.get(1) ,g2);
//        }
//    }
//    public static final long serialVersionUID = 1;
//}
