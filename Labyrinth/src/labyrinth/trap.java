package labyrinth;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import static labyrinth.Labyrinth.filename;

public class trap extends StackPane{
    private int x,y,velX,velY,width,height,direction;
    static ID id;
    ImageView ivTrap;
    Image imTrap;
    private boolean placed = false;
    String filename;
   
    public trap(int x, int y,int width, int height,int direction, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.width = width;
        this.height = height;
        this.direction = direction;
        //setImage();
        velX = 3;
        velY = 3;
       
    }
    
    public Rectangle2D getBounds() {
        if(id == ID.roundSpike) return new Rectangle2D(x,y,width,height);
        else return null;
    }
    
    public void setImage(String filename) {
        if (id == ID.roundSpike) {
            ivTrap = new ImageView();
            imTrap = new Image("spikeobject.png");
            ivTrap.setFitHeight(40);
            ivTrap.setFitWidth(40);
            ivTrap.setImage(imTrap);
            getChildren().add(ivTrap);
            setTrap();
        } 
    }
    
    public void setTrap() {
        setX(x);
        setY(y);
        relocate(getX(),getY());
    }
    
    public void move() {
        if(direction == 1) {
            x += velX;
            if(x<=160 || x+29 >= 1040) velX *= -1;
            relocate(x,y);
        }  else if (direction == 0){
            y += velY;
            if(Labyrinth.filename.equals("mapPack/floor3roomH.png")) {
                if(y<=500 || y+29 >= 640) velY *= -1;
            } else {
                if(y<=160 || y+29 >=640) velY *= -1;
            }
            relocate(x,y);
        }
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public void setX(int newX) {
        x = newX;
    }
    
    public void setY(int newY) {
        y = newY;
    }
    
    public boolean getPlaced() {
        return placed;
    }
    
    public void setPlaced(boolean newPlaced) {
        placed = newPlaced;
    }
}
