package labyrinth;

import java.util.ArrayList;
import java.util.LinkedList;
import javafx.geometry.Rectangle2D;



public class floorOneCollision {
    public static ArrayList<Rectangle2D> walls = new ArrayList<Rectangle2D>();
    public static LinkedList<Rectangle2D> interiors = new LinkedList<Rectangle2D>();
    
    public floorOneCollision() {
        //top walls
        walls.add(new Rectangle2D(100,100,450,60));
        walls.add(new Rectangle2D(650,100,450,60));
        
        //right walls
        walls.add(new Rectangle2D(1040,100,60,250));
        walls.add(new Rectangle2D(1040,450,60,250));
        
        //bottom walls
        walls.add(new Rectangle2D(100,640,450,60));
        walls.add(new Rectangle2D(650,640,450,60));
        
        //left walls
        walls.add(new Rectangle2D(100,100, 60,250));
        walls.add(new Rectangle2D(100,450,60,250));
        //interior();
    }
    
    public static ArrayList<Rectangle2D> getArray() {
        return walls;
    }
    
    public static void interior() {
        if(Labyrinth.filename.equals("mapPack/floor1roomA.png")) {
            interiors.add(new Rectangle2D(256,250,100,100));
            interiors.add(new Rectangle2D(452,250,100,100));
            interiors.add(new Rectangle2D(648,250,100,100));
            interiors.add(new Rectangle2D(844,250,100,100));
            interiors.add(new Rectangle2D(256,450,100,100));
            interiors.add(new Rectangle2D(452,450,100,100));
            interiors.add(new Rectangle2D(648,450,100,100));
            interiors.add(new Rectangle2D(844,450,100,100));
        } else if(Labyrinth.filename.equals("mapPack/floor1roomD.png")) {
            interiors.add(new Rectangle2D(500,300,200,200));
        } else if(Labyrinth.filename.equals("mapPack/floor3roomH.png")) {
            interiors.add(new Rectangle2D(100,450,1000,50));
        } else if(Labyrinth.filename.equals("mapPack/floor3roomO.png")) {
            interiors.add(new Rectangle2D(100,300,1000,50));
        }
    }
}
