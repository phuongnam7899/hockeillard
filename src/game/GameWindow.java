package game;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class GameWindow extends JFrame {
    // static
    public static boolean isUpPress;
    public static boolean isDownPress;
    public static boolean isLeftPress;
    public static boolean isRightPress;
    public static boolean isSpacePress;
    public static boolean isShiftPress;

    public static boolean isWPress;
    public static boolean isSPress;
    public static boolean isAPress;
    public static boolean isDPress;
    public static boolean isEnterPress;
    public static boolean isbkSpacePress;


    // attributes
    public GamePanel gamePanel;


    // constructor
    public GameWindow() {
        this.setUpWindow();
        this.createGamePanel();
        this.setVisible(true);
        this.setupListener();
    }


    //methods
    private void createGamePanel() {
        this.gamePanel = new GamePanel();
        this.gamePanel.setPreferredSize(new Dimension(Settings.SCREEN_WIDTH,Settings.SCREEN_HEIGHT));
        this.add(this.gamePanel);
        this.pack();
    }

    private void setUpWindow() {
        this.setTitle("Hockeillard");
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setupListener();
    }

    private void setupListener() {
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_W){
                    isWPress = true;
                }
                if(e.getKeyCode() == KeyEvent.VK_D){
                    isDPress = true;
                }
                if(e.getKeyCode() == KeyEvent.VK_S){
                    isSPress = true;
                }
                if(e.getKeyCode() == KeyEvent.VK_A){
                    isAPress = true;
                }
                if(e.getKeyCode() == KeyEvent.VK_UP){
                    isUpPress = true;
                }
                if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                    isRightPress = true;
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN){
                    isDownPress = true;
                }
                if(e.getKeyCode() == KeyEvent.VK_LEFT){
                    isLeftPress = true;
                }
                if(e.getKeyCode() == KeyEvent.VK_SHIFT){
                    isShiftPress = true;
                }
                if(e.getKeyCode() == 8){
                    isbkSpacePress = true;
                }
                if(e.getKeyCode() == KeyEvent.VK_SPACE){
                    isSpacePress = true;
                }
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    isEnterPress = true;
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_W){
                    isWPress = false;
                }
                if(e.getKeyCode() == KeyEvent.VK_D){
                    isDPress = false;
                }
                if(e.getKeyCode() == KeyEvent.VK_S){
                    isSPress = false;
                }
                if(e.getKeyCode() == KeyEvent.VK_A){
                    isAPress = false;
                }
                if(e.getKeyCode() == KeyEvent.VK_UP){
                    isUpPress = false;
                }
                if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                    isRightPress = false;
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN){
                    isDownPress = false;
                }
                if(e.getKeyCode() == KeyEvent.VK_LEFT){
                    isLeftPress = false;
                }
                if(e.getKeyCode() == KeyEvent.VK_SHIFT){
                    isShiftPress = false;
                }
                if(e.getKeyCode() == 8){
                    isbkSpacePress = false;
                }
                if(e.getKeyCode() == KeyEvent.VK_SPACE){
                    isSpacePress = false;
                }
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    isEnterPress = false;
                }
            }
        });
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println(e.getX() +"-"+ e.getY());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
            }
        });
    }
}
