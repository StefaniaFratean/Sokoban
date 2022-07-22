import javax.swing.*;
import java.util.ArrayList;

public class Player extends GameObject {
    public Player (int x, int y) {
        super(x, y);
        playerSetImg();
        this.setFilename("src/images/player.png");
    }

    private void playerSetImg(){
        ImageIcon playerIcon = new ImageIcon("src/images/player.png");
        this.setImage(playerIcon.getImage());
    }

    public void drawPlayer(JFrame frame) {
        GameObject obj = this;
        obj.printGameObject(frame);
    }

    public void move(char direction){
        switch (direction) {
            case 'a':
                this.setX(getX() - SPACE);
                break;
            case 's':
                this.setY(getY() + SPACE);
                break;
            case 'd':
                this.setX(getX() + SPACE);
                break;
            case 'w':
                this.setY(getY() - SPACE);
                break;
            default:
                break;
        }
    }


    public void pushCoinUp(ArrayList<Coin> coins, ArrayList<Wall> walls) {
        int pos = this.returnCoinAboveIndex(coins);
        if(pos >= 0) {
            Coin coin = coins.get(pos);
            if(coin.okToMove('w', pos, walls, coins)) {
                coin.move('w', pos, walls, coins);
                coins.set(pos, coin);
                this.move('w');
            }
        } else {
            this.move('w');
        }
    }


    public void pushCoinDown(ArrayList<Coin> coins, ArrayList<Wall> walls) {
        int pos = this.returnCoinBelowIndex(coins);
        if(pos >= 0) {
            Coin coin = coins.get(pos);
            if(coin.okToMove('s', pos, walls, coins)) {
                coin.move('s', pos, walls, coins);
                coins.set(pos, coin);
                this.move('s');
            }
        } else {
            this.move('s');
        }
    }

    public void pushCoinLeft(ArrayList<Coin> coins, ArrayList<Wall> walls) {
        int pos = this.returnCoinLeftIndex(coins);
        if(pos >= 0) {
            Coin coin = coins.get(pos);
            if(coin.okToMove('a', pos, walls, coins)) {
                coin.move('a', pos, walls, coins);
                coins.set(pos, coin);
                this.move('a');
            }
        } else {
            this.move('a');
        }
    }

    public void pushCoinRight(ArrayList<Coin> coins, ArrayList<Wall> walls) {
        int pos = this.returnCoinRightIndex(coins);
        if(pos >= 0) {
            Coin coin = coins.get(pos);
            if(coin.okToMove('d', pos, walls, coins)) {
                coin.move('d', pos, walls, coins);
                coins.set(pos, coin);
                this.move('d');
            }
        } else {
            this.move('d');
        }
    }
}
