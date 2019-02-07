package labyrinth;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import static labyrinth.Labyrinth.filename;

public class item extends StackPane {
    private int x,y,width,height;
    private ID id;
    private boolean isVis = true;
    private boolean used = false;
    private String room;
    private int rep; //1 is representation, 0 is actual item
    ImageView ivItem;
    Image item;
    
    public item(int x,int y,int width,int height,int rep,ID id,String room) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rep = rep;
        this.id = id;
        this.room = room;
        
        setImage();
    }
    
    public void setImage() {
        ivItem = new ImageView();
        if(id == ID.key) {
            item = new Image("key.png");
        } else if (id == ID.bossKey) {
            item = new Image("bossKey.png");
            ivItem.setFitHeight(25);
            ivItem.setFitWidth(20);
        } else if (id == ID.boot) {
            item = new Image("quick.png");
        } else if(id==ID.heart) {
            item = new Image("heart.png");
        } else if(id==ID.score) {
            item = new Image("score1.png");
        } else if(id==ID.armour) {
            item = new Image("helmfront.png");
            ivItem.setFitHeight(30);
            ivItem.setFitWidth(30);
        } else if(id==ID.weapon) {
            item = new Image("sword.png");
        }
        ivItem.setImage(item);
        ivItem.setLayoutX(x);
        ivItem.setLayoutY(y);
        getChildren().add(ivItem);
    }
    
    public void relocate() {
        setX(x);
        setY(y);
        relocate(getX(),getY());
    }
    
    public Rectangle2D getBounds() {
        return new Rectangle2D(x,y,width,height);
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
    
    public ID getID() {
        return id;
    }
    
    public void setVis(boolean newVis) {
        isVis = newVis;
    }
    
    public boolean getVis() {
        return isVis;
    }
    
    public void setUsed(boolean newUsed) {
        used = newUsed;
    }
}
