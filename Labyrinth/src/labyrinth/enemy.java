package labyrinth;

import java.util.Random;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import static labyrinth.Labyrinth.filename;
import static labyrinth.Labyrinth.ts;

public class enemy extends StackPane {
    private int x,y,index;
    private int velX = 10;
    private int velY = 10;
    private ID id;
    private String img,room;
    private int status = 1; //1 is alive, 0 is dead
    ImageView ivMon;
    Image monster;
    String sprites[] = {"alien.png", "arrowdog.png", "axedragon.png",
        "biggs.png", "bosstroll.png", "caveape.png", "deathfarena.png", "goon.png",
        "rottingcorpse.png", "brainpan.png","anderoug.png", "bakor.png", "babysalamand.png",
        "arrop.png", "armorscorpion.png", "archbison.png", "anglehead.png", "blizag.png",
        "beastan.png", "bengal.png"};
    Random rand = new Random();
    Random randMov = new Random();
    int x1,mov;
    private double HEALTH = 100;
    static double attack = 10;
    private boolean placed = false;
    
    static Timeline timeline;
    public enemy(int x, int y,int index, ID id,String room) {
        this.x = x;
        this.y = y;
        this.index = index;
        this.id = id;
        this.room = room;
        
        if(id == ID.bossEnemy) {
            HEALTH += HEALTH*2;
            attack += attack*0.3;
            if(room.equals("mapPack/floor1roomN.png")) img = "Luffy.png";
            else if (room.equals("mapPack/floor2roomN.png")) img = "hitmonlee.png";
            else if (room.equals("mapPack/floor3roomQ.png")) img = "mickey.png";
        } else {
            x1 = rand.nextInt(20) + 1;
            img = sprites[x1];
        }
    }
    
    public Rectangle2D getBounds() {
        if (id == ID.bossEnemy) return new Rectangle2D(x,y,60,60);
        else return new Rectangle2D(x,y,30,30);
    }
    
    public void setImage(String filename) {
        ivMon = new ImageView();
        monster = new Image(img);
        ivMon.setImage(monster);

        getChildren().addAll(ivMon);
        Relocate(filename);
        if(HEALTH <= 0) {
            scoreCounter.increment = true;
            if(id == ID.basicEnemy) Labyrinth.scoreCounter.increment(30);
            else if(id == ID.bossEnemy) Labyrinth.scoreCounter.increment(100);
            status = 0;
            if(id == ID.bossEnemy && filename.equals("mapPack/floor3roomQ.png")) {
                Labyrinth.running = false;
                if(ts.intArray[0] == 0 && ts.intArray[1] == 0) {
                        Labyrinth.save();
                    }
                    else if(Labyrinth.task.minutesPassed <= ts.intArray[0]) {
                        if(Labyrinth.task.secondsPassed < ts.intArray[1] && scoreCounter.getValue() >= ts.intArray[2]) {
                            Labyrinth.save();
                        }
                    }
            }
        }
        if (status == 0) {
            if(Labyrinth.getEnemy() != null) Labyrinth.removeEnemy(Labyrinth.getEnemy());
            for(int i = 0; i < Labyrinth.monsters.length; i++) {
                Labyrinth.monsters[i] = null;
            }
        }
    }
    
    public void Relocate(String filename) {
        setX(x);
        setY(y);
        relocate(getX(),getY());
    }
    
    public void move() {
        mov = randMov.nextInt(4);
        if(mov == 1) {
            x+= velX;
            if(x<= 160 || x+29 > 1040) velX *= -1;
            relocate(x,y);
        } else if(mov == 0) {
            y += velY;
            if(y <= 160 || y + 30 > 640) velY *= -1;
            relocate(x,y);
        } else if(mov == 2) {
            velX *= -1;
        } else if(mov == 3) {
            velY *= -1;
        }
        for(int i = 0; i<floorOneCollision.interiors.size();i++) {
            if(getBounds().intersects(floorOneCollision.interiors.get(i))) {
                x-=velX;
                y-=velY;
            }
        }
    }
    
    public Rectangle2D hitBox() {
        return new Rectangle2D(x-50,y-50,200,200);
    }
    
    public void collisions() {
        if(hitBox().intersects(Labyrinth.hero.getBounds())) {
            if(HEALTH > 0) Labyrinth.HEALTH-=5;
        }
    }
    
    public void setX(int newX) {
        x = newX;
    }
    
    public void setY(int newY) {
        y = newY;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public void decHealth(int change) {
        HEALTH -= change;
    }
    
    public void setHealth(int newHealth) {
        HEALTH = newHealth;
    }
    
    public double getHealth() {
        return HEALTH;
    }
    
    public void setPlaced(boolean newPlaced) {
        this.placed = newPlaced;
    }
    
    public boolean getPlaced() {
        return placed;
    }
}


