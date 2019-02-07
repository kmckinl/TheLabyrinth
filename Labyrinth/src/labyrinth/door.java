package labyrinth;


import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import static labyrinth.Labyrinth.filename;

public class door extends StackPane {
    private int x,y,spot;
    private ID id;
    private String room;
    private boolean locked,open;
    private boolean sameBounds = true;
    ImageView ivDoor;
    Image image;
    
    Timeline timer;
    
    public door(int x, int y, int spot, ID id, boolean locked,String room) {
        this.x = x;
        this.y = y;
        this.spot = spot; //1 = right, 2 = bottom, 3 = left, 4 = top
        this.id = id;
        this.locked = locked;
        this.room = room;
        open = false;
        setImage();
        
    }
    
    public void setImage() {
        ivDoor = new ImageView();
        if(id == ID.door) {
            if(spot == 1) {
                if(locked) image = new Image("rightsidedoor1.png");
                else image = new Image("rightsidedoor4.png");
            } else if (spot == 2) {
                if(locked) image = new Image("bottomsidedoor1.png");
                else image = new Image("bottomsidedoor4.png");
            } else if (spot == 3) {
                if(locked) image = new Image("leftsidedoor1.png");
                else image = new Image("leftsidedoor4.png");
            } else if (spot == 4) {
                if(locked) image = new Image("topsidedoor1.png");
                else image = new Image("topsidedoor4.png");
            }
        } else if (id == ID.bossDoor) {
            if(spot == 3) {
                if(locked) image = new Image("leftbossDoor1.png");
                else image = new Image("leftbossDoor4.png");
            } else if(spot == 1) {
                if(locked) image = new Image("rightbossDoor1.png");
                else if (!locked && open) image = new Image("rightbossDoor4.png");
            }
            ivDoor.setFitWidth(40);
            ivDoor.setFitHeight(100);
        }
        //ivDoor.setFitHeight(100);
        ivDoor.setImage(image);
        getChildren().add(ivDoor);
    }
    
    public Rectangle2D getBounds() {
        if (sameBounds) return new Rectangle2D(x,y,20,100);
        else if (!sameBounds) return new Rectangle2D(2000,2000,20,100);
        else return null;
    }
    
    public void relocate() {
        setX(x);
        setY(y);
        relocate(getX(),getY());
//        if(filename.equals(room)) {
//            if(spot == 1) {
//                setX(1080);
//                setY(350);
//                relocate(getX(),getY());
//            } else if(spot == 2) {
//                setX(550);
//                setY(540);
//                relocate(getX(),getY());
//            } else if(spot == 3) {
//                setX(100);
//                setY(350);
//                relocate(getX(),getY());
//            } else if(spot == 4) {
//                setX(550);
//                setY(100);
//                relocate(getX(),getY());
//            }
//        } else {
//            setX(2000);
//            setY(2000);
//            relocate(getX(),getY());
//        }
        
    }
    
    public void setX(int newX) {
        x = newX;
    }
    
    public void setY(int newY) {
        y = newY;
    }
    
    public void setLocked(boolean newLocked) {
        locked = newLocked;
    }
    
    public void setOpen(boolean newOpen) {
        open = newOpen;
    }
    
    public void setSameBounds(boolean newBounds) {
        sameBounds = newBounds;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public boolean getLocked() {
        return locked;
    }
}
