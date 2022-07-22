import javax.swing.*;
import java.util.ArrayList;

public class Destination extends GameObject{

    boolean isOccupied;

    public Destination(int x, int y, boolean isOccupied){
        super(x, y);
        destinationSetImg(isOccupied);
        this.setFilename("src/images/openChest.png");
    }
    private void destinationSetImg(boolean isOccupied){
        ImageIcon openChestIcon= new ImageIcon("src/images/openChest.png");
        ImageIcon closedChestIcon= new ImageIcon("src/images/closedChest.png");
        if(isOccupied){
            this.setImage(closedChestIcon.getImage());
        } else {
            this.setImage(openChestIcon.getImage());
        }
    }
}
