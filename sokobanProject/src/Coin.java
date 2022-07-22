import javax.swing.*;
import java.util.ArrayList;

public class Coin extends GameObject{
    public Coin(int x, int y){
        super(x, y);
        coinSetImg();
        this.setFilename("src/images/coin.png");
    }

    private void coinSetImg() {
        ImageIcon coinIcon= new ImageIcon("src/images/coin.png");
        this.setImage(coinIcon.getImage());
    }


    public boolean okToMove(char direction, int index, ArrayList<Wall> walls, ArrayList<Coin> coins) {
        if (direction == 'w') {
            if (!this.isTopCollision(walls)) {
                for(int i=0; i<coins.size(); i++) {
                    Coin coin = coins.get(i);
                    if (this.getX() == coin.getX() && this.getY() - SPACE == coin.getY()) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        } else if (direction == 's') {
            if (!this.isBotCollision(walls)) {
                for(int i=0; i<coins.size(); i++){
                    Coin coin = coins.get(i);
                    if(this.getX() == coin.getX() && this.getY() + SPACE == coin.getY()) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        } else if (direction == 'a') {
            if (!this.isLeftCollision(walls)) {
                for(int i=0; i<coins.size(); i++){
                    Coin coin = coins.get(i);
                    if(this.getX() - SPACE == coin.getX() && this.getY() == coin.getY()) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        } else if (direction == 'd') {
            if (!this.isRightCollision(walls)) {
                for(int i=0; i<coins.size(); i++){
                    Coin coin = coins.get(i);
                    if(this.getX() + SPACE == coin.getX() && this.getY() == coin.getY()) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    /*
    public boolean okToMove(char direction, int index, ArrayList<Wall> walls, ArrayList<Coin> coins) {
        if (direction == 'w') {
            if (!this.isTopCollision(walls)) {
                for (int j = 0; j < coins.size(); j++) {
                    if (j != index) {
                        Coin coin = coins.get(j);
                        if (this.getX() == coin.getX() && this.getY() - SPACE == coin.getY()) {
                            if(coin.okToMove('w', j, walls, coins)){
                                return true;
                            } else {
                                return false;
                            }
                        }
                    }
                }
                return true;
            } else if(this.returnCoinAboveIndex(coins) != -1){
                return false;
            }
            return false;
        } else if (direction == 's') {
            if (!this.isBotCollision(walls)) {
                for (int j = 0; j < coins.size(); j++) {
                    if (j != index) {
                        Coin coin = coins.get(j);
                        if (this.getX() == coin.getX() && this.getY() + SPACE == coin.getY()) {
                            if(coin.okToMove('s', j, walls, coins)){
                                return true;
                            } else {
                                return false;
                            }
                        }
                    }
                }
                return true;
            }
            return false;
        } else if (direction == 'a') {
            if (!this.isLeftCollision(walls)) {
                for (int j = 0; j < coins.size(); j++) {
                    if (j != index) {
                        Coin coin = coins.get(j);
                        if (this.getX() - SPACE == coin.getX() && this.getY() == coin.getY()) {
                            if(coin.okToMove('a', j, walls, coins)){
                                return true;
                            } else {
                                return false;
                            }
                        }
                    }
                }
                return true;
            }
        } else if (direction == 'd') {
            if (!this.isRightCollision(walls)) {
                for (int j = 0; j < coins.size(); j++) {
                    if (j != index) {
                        Coin coin = coins.get(j);
                            if (this.getX() + SPACE == coin.getX() && this.getY() == coin.getY()) {
                                if(coin.okToMove('d', j, walls, coins)){
                                    coin.move('d', j, walls, coins);
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        }
                    }
                return true;
                }
            }
            return false;
    }
*/

    public void move(char direction, int index, ArrayList<Wall> walls, ArrayList<Coin> coins) {
        switch (direction) {
            case 'w':
                if(this.okToMove(direction, index, walls, coins)) {
                    this.setY(getY() - SPACE);
                }
                break;
            case 'd':
                if(this.okToMove(direction, index, walls, coins)) {
                    this.setX(getX() + SPACE);
                }
                break;
            case 's':
                if(this.okToMove(direction, index, walls, coins)) {
                    this.setY(getY() + SPACE);
                }
                break;
            case 'a':
                if(this.okToMove(direction, index, walls, coins)) {
                    this.setX(getX() - SPACE);
                }
                break;
            default:
                break;
        }
    }

    public void printCoin(){
        MyCanvas coin= new MyCanvas( this.getFilename(), this.getX(), this.getY());
    }
    static public void drawAllCoins(JFrame frame, ArrayList<Coin> coins) {
        for(int i=0; i<coins.size(); i++)
        {
            coins.get(i).printGameObject(frame);
        }
    }
}
