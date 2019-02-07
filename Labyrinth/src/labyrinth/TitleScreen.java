package labyrinth;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TitleScreen extends Pane {
    static int[] intArray = new int[3];
    static Label start,highScore,fastTime;
    Image ts;
    ImageView ivTs;
    
    public TitleScreen() {
        start = new Label();
        highScore = new Label();
        fastTime = new Label();
        
        ivTs = new ImageView();
        ts = new Image("TitleScreen.png");
        ivTs.setLayoutX(0);
        ivTs.setLayoutY(0);
        
        ivTs.setImage(ts);
        
        getChildren().addAll(ivTs,start,highScore,fastTime);
    }
    
    public void setLabels() {
        start.setFont(new Font("8-Bit Limit R (BRK)",44));
        start.setTextFill(Color.BLACK);
        start.setText("Enter to start");
        
        fastTime.setFont(new Font("8-Bit Limit R (BRK)",32));
        fastTime.setTextFill(Color.BLACK);
        fastTime.setText("Fastest Time: " + intArray[0] + ":" + intArray[1]);
        
        highScore.setFont(new Font("8-Bit Limit R (BRK)", 32));
        highScore.setTextFill(Color.BLACK);
        highScore.setText("Highscore: " + intArray[2]);
    }
    
    public void setLocation() {
        start.setLayoutX(590);
        start.setLayoutY(585);
        
        fastTime.setLayoutX(600);
        fastTime.setLayoutY(350);
        
        highScore.setLayoutX(600);
        highScore.setLayoutY(400);
    }
    
    public void readFile() {
        try {
            File file = new File(/*"C:\\Users\\Kyle McKinley\\Desktop\\Labyrinth 0.3\\Labyrinth\\*/"save.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            
            
            if(!file.exists()) {
                System.exit(0);
            } else {
                for(int i = 0; i < intArray.length; i++) {
                    intArray[i] = Integer.parseInt(br.readLine()); 
                }
                br.close();
            }
        } catch (IOException e) {
            System.exit(0);
        }
    }
    
    public Rectangle2D getBounds() {
        return new Rectangle2D(620,585,100,44);
    }
}
