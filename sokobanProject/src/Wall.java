import javax.swing.*;
import java.util.ArrayList;

public class Wall extends GameObject{

    public Wall(int x, int y){
        super(x, y);
        wallSetImg();
        this.setFilename("src/images/dirt.png");
    }

    void wallSetImg(){
        ImageIcon wallIcon= new ImageIcon("src/images/dirt.png");
        this.setImage(wallIcon.getImage());

    }

}
