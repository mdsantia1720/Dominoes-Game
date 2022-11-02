import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

/**
 * A program that should create a GUI domino game.
 *
 * @author Micky Santiago-Zayas
 * @version August 17, 2021
 */

public class GUI extends JFrame {
    Game game;
    Thread thread;
    ArrayList<Domino> chain;
    Domino middle;
    private int width = 650;
    private int height = 650;
    private int dominoWidth;
    private Image image0;
    private Image image1;
    private Image image2;
    private Image image3;
    private Image image4;
    private Image image5;
    private Image image6;

    public GUI() {
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(new Player("M"));
        players.add(new Player("E"));
        players.add(new Player("A"));
        players.add(new Player("D"));
        game = new Game(players, 100);
        start();
    }

    private void start() {
        setTitle("Domino Game");
        setSize(new Dimension(width, height));
        setLayout(new FlowLayout());
        setBackground(new Color(200, 200, 200));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        run();
        thread = new Thread();
        thread.start();
        repaint();
    }

    public void run() {
        while (!game.endGame()) {
            while (!game.endRound()) {
                try {
                    game.gameTerminal();
                } catch (Exception e) {
                }
            }
        }
    }

    private void convertToImages() {
        String list = convertToValues();
        list = list.substring(0, list.length() - 2);
        String result = "";
        chain.indexOf(middle);
        while (list.contains(",")) {

            list = list.substring(list.indexOf(",") + 1, list.length());
        }
    }

    private String convertToValues() {
        String list = "";
        for (Domino domino: chain)
            list += String.format("%d%d", domino.getSide1() + domino.getSide2());
        return list;
    }

    @Override

    public void paint(Graphics g) {
        height = getHeight();
        width = getWidth();
        try {
            image0 = ImageIO.read(new File("Zero.png"));
            image1 = ImageIO.read(new File("One.png"));
            image2 = ImageIO.read(new File("Two.png"));
            image3 = ImageIO.read(new File("Three.png"));
            image4 = ImageIO.read(new File("Four.png"));
            image5 = ImageIO.read(new File("Five.png"));
            image6 = ImageIO.read(new File("Six.png"));

            g.drawImage(image6, width / 2, height / 2, null);

            super.paint(g);
            dominoWidth = image1.getWidth(this);

            while (true){

                chain = game.getChain().readChain();

                if (chain.size() == 1) {
                    middle = chain.get(0);
                    System.out.println(middle);
                } else {
                    convertToImages();
                    System.out.println("tryx");
                    //TODO
                    System.out.println("why");
                    g.drawImage(image6, width / 2, height / 2, this);
                    super.paint(g);
                }
                repaint();
            }
        } catch (Exception e) {
        }
    }
}