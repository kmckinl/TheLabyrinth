package labyrinth;

public class scoreCounter {
    private static int x,y,count;
    private final String label = toString();    //counter name
    public static boolean increment = false;

    public scoreCounter(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    //Increment the counter
    public void increment(int scoreChange) {
        if(increment) count += scoreChange;
        increment = false;
    }
    
    //return the current count
    public static int getValue() {
        return count;
    }
    
    public String toString() {
        return "Score: " + getValue();
    }
    
    public String toStringSave() {
        return "" + getValue();
    }
}