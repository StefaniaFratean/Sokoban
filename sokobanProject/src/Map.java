import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class Map extends JPanel {
    private Player player;
    private ArrayList<Coin> coins;
    private ArrayList<Wall> walls;
    private ArrayList<Destination> destinations;
    private JButton resetBtn;
    private JButton menuBtn;

  //  JPanel buttonsPanel;

    private final int SPACE = 50;

    private int mapWidth;
    private int mapLength;

    private boolean isComplete = false;

    private int levelNr;

    private String level;

    private String level2 =
            " xxxxx\n" +
                    " x  xxx\n" +
                    " xpc  x\n" +
                    "xxx x xx\n" +
                    "xdx x  x\n" +
                    "xdc  x x\n" +
                    "xd   c x\n" +
                    "xxxxxxxx\n";

    private String level1 = "xxxxxxxx\n"+
            "x  x   x\n"+
            "x cddc x\n"+
            "xpcd  xx\n"+
            "xpcd  xx\n"+
            "x cddc x\n"+
            "x  x   x\n"+
            "xxxxxxxx\n";

    private String level3 = "xxxxxxxxxx\n"+
            "xxx  xxxxx\n"+
            "xxx cxxx x\n"+
            "xxx    cpx\n"+
            "xxxx x c x\n"+
            "x c d  d x\n"+
            "x d  d   x\n"+
            "xxxxxxxxxx\n";

    void setLevel(){
        switch (levelNr){
            case 1:
                level = level1;
                break;
            case 2:
                level = level2;
                break;
            case 3:
                level = level3;
                break;
            default:
                break;

        }

    }

    public Map(int levelNr, Frame f) {
        this.levelNr = levelNr;
        setLevel();

        addKeyListener(new TAdapter());
        setFocusable(true);
        createMap();

        if(resetBtn == null) {
            resetBtn = new JButton("Reset");
            resetBtn.setBounds(600, 50, 100, 30);
            this.setLayout(null);
            resetBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    resetMap();
                    Map.super.repaint();
                    Map.super.setFocusable(true);
                    Map.super.requestFocus();
                }
            });
            this.add(resetBtn);
            resetBtn.setVisible(true);
        }

        if(menuBtn == null){
            menuBtn = new JButton("Home Page");
            menuBtn.setBounds(600, 110, 100, 30);
            this.setLayout(null);
            menuBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setVisible(false);
                    StartPage startPage = new StartPage(f);
                    f.add(startPage);
                    startPage.setVisible(true);
                    startPage.setFocusable(true);
                    startPage.requestFocus();
                }
            });

            this.add(menuBtn);
            menuBtn.setVisible(true);
            f.setVisible(true);

        }

    }

    public int getLevelNr() {
        return levelNr;
    }

    public void setLvlNr(int levelNr) {
        this.levelNr = levelNr;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public void setMapWidth(int mapWidth) {
        this.mapWidth = mapWidth;
    }

    public int getMapLength() {
        return mapLength;
    }

    public void setMapLength(int mapLength) {
        this.mapLength = mapLength;
    }


    private void createMap() {
        walls = new ArrayList<Wall>();
        coins = new ArrayList<Coin>();
        destinations = new ArrayList<Destination>();

        Wall wall;
        Player plr;
        Coin cn;
        Destination dest;

        int x, y;
        x = SPACE;
        y = SPACE;

        for (int i = 0; i < level.length(); i++) {
            char obj = level.charAt(i);
            switch (obj) {
                case ' ':
                    x = x + SPACE;
                    break;
                case 'x':
                    wall = new Wall(x, y);
                    walls.add(wall);
                    x = x + SPACE;
                    break;
                case 'p':
                    plr = new Player(x, y);
                    player = plr;
                    x = x + SPACE;
                    break;
                case 'c':
                    cn = new Coin(x, y);
                    coins.add(cn);
                    x = x + SPACE;
                    break;
                case 'd':
                    dest = new Destination(x, y, false);
                    destinations.add(dest);
                    x = x + SPACE;
                    break;
                case '\n':
                    if (x > getMapWidth()) {
                        setMapWidth(x);
                    }
                    y = y + SPACE;
                    x = SPACE;
                    break;
                default:
                    break;
            }
        }
        setMapLength(700);
        setMapWidth(800);
    }

    public void drawMap(Graphics g) {

        g.setColor(new Color(250, 240, 170));
        g.fillRect(0, 0, this.getMapWidth(), this.getMapLength());


        ArrayList<GameObject> map = new ArrayList<GameObject>();
        map.addAll(walls);
        map.addAll(destinations);
        map.addAll(coins);
        map.add(player);

        for (int i = 0; i < map.size(); i++) {
            GameObject obj = (GameObject) map.get(i);
            g.drawImage(obj.getImage(), obj.getX(), obj.getY(), this);
        }

        if (isComplete) {
            g.setColor(new Color(0, 0, 0));
            g.drawString("Completed", 25, 20);
            g.fillRect(0, 0, this.getMapWidth(), this.getMapLength());
            Image img = new ImageIcon("src/images/fireworks.gif").getImage();
            g.drawImage(img, 40, 40, this);
        }
    }



    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawMap(g);
    }

    public void isComplete(){
        int nrOfCoins = coins.size();
        int takenChests = 0;
        for(int i=0; i<destinations.size(); i++) {
            Destination chest = destinations.get(i);
            for(int j=0; j<nrOfCoins; j++) {
                Coin coin = coins.get(j);
                if(coin.getX() == chest.getX() && coin.getY() == chest.getY()){
                    takenChests++;
                }
            }
        }
        if(takenChests == nrOfCoins){
            isComplete = true;
        } else {
            isComplete = false;
        }
    }

    public void resetMap(){
        walls.clear();
        coins.clear();
        destinations.clear();

        createMap();

        if(isComplete){
            isComplete = false;
        }
        setFocusable(true);
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            if (isComplete) {
                return;
            }

            int key = e.getKeyCode();

            switch (key){
                case KeyEvent.VK_UP:
                    if(player.isTopCollision(walls)){
                        return;
                    } else {
                        player.pushCoinUp(coins, walls);
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (player.isBotCollision(walls)) {
                        return;
                    } else {
                        player.pushCoinDown(coins, walls);
                    }
                    break;
                case KeyEvent.VK_LEFT:
                    if (player.isLeftCollision(walls)) {
                        return;
                    } else {
                        player.pushCoinLeft(coins, walls);
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (player.isRightCollision(walls)) {
                        return;
                    } else {
                        player.pushCoinRight(coins, walls);
                    }
                    break;
                case KeyEvent.VK_R:
                    resetMap();
                    break;
                default:
                    break;
            }
            repaint();
            isComplete();
        }
    }
}


