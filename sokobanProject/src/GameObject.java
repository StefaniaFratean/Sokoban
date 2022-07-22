import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameObject {

    private int x;
    private int y;
    private Image image;
    private String filename;

    int SPACE = 50;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }


    GameObject(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public boolean isLeftCollision(ArrayList<Wall> walls) {
        for(int i=0; i<walls.size(); i++) {
            Wall wall = walls.get(i);
            if (this.getX() - SPACE == wall.getX() && this.getY() == wall.getY()){
                return true;
            }
        }
        return false;
    }

    public boolean isRightCollision(ArrayList<Wall> walls) {
        for(int i=0; i<walls.size(); i++) {
            Wall wall = walls.get(i);
            if (this.getX() + SPACE == wall.getX() && this.getY() == wall.getY()){
                return true;
            }
        }
        return false;
    }

    public boolean isBotCollision(ArrayList<Wall> walls) {
        for(int i=0; i<walls.size(); i++) {
            Wall wall = walls.get(i);
            if(this.getX() == wall.getX() && this.getY() + SPACE == wall.getY()){
                return true;
            }
        }
        return false;
    }

    public boolean isTopCollision(ArrayList<Wall> walls) {
        for(int i=0; i<walls.size(); i++) {
            Wall wall = walls.get(i);
            if(this.getX() == wall.getX() && this.getY() - SPACE == wall.getY()){
                return true;
            }
        }
        return false;
    }
    public void printGameObject(JFrame frame) {
        MyCanvas obj = new MyCanvas(this.getFilename(), this.getX(), this.getY());
        frame.add(obj);
      //  frame.setVisible(true);
    }

    public int returnCoinLeftIndex(ArrayList<Coin> coins){
        for(int i=0; i<coins.size(); i++){
            Coin coin = coins.get(i);
            if(this.getX() - SPACE == coin.getX() && this.getY() == coin.getY()) {
                return i;
            }
        }
        return -1;
    }


    public int returnCoinRightIndex(ArrayList<Coin> coins){
        for(int i=0; i<coins.size(); i++){
            Coin coin = coins.get(i);
            if(this.getX() + SPACE == coin.getX() && this.getY() == coin.getY()) {
                return i;
            }
        }
        return -1;
    }

    public int returnCoinAboveIndex(ArrayList<Coin> coins){
        for(int i=0; i<coins.size(); i++){
            Coin coin = coins.get(i);
            if(this.getX() == coin.getX() && this.getY() - SPACE == coin.getY()) {
                return i;
            }
        }
        return -1;
    }

    public int returnCoinBelowIndex(ArrayList<Coin> coins){
        for(int i=0; i<coins.size(); i++){
            Coin coin = coins.get(i);
            if(this.getX() == coin.getX() && this.getY() + SPACE == coin.getY()) {
                return i;
            }
        }
        return -1;
    }

}
