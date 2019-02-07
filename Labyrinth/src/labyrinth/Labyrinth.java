package labyrinth;

import java.awt.Point;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Timer;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.CacheHint;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class Labyrinth extends Application {
    //Initialize variables
    static final int HEIGHT = 840;
    static final int WIDTH = 1400;
    static int HEALTH = 100;
    static int SPEED = 3;
    static int direction = 1; //1 = right, 2 = down, 3 = left, 4 = up
    static boolean up,down,right,left,interact,attack;
    static boolean[] doors = new boolean[8];
    static boolean startup = true;
    static boolean running = false;
    static boolean entered = true;
    static boolean win = false;
    static String timeString;
    static String filename = "mapPack/floor1roomA.png";
    
    static TitleScreen ts = new TitleScreen();
    
    //Initialize objects
    static Image image,imHero;
    static floorOne f1 = new floorOne(filename);
    static floorOneCollision f1c = new floorOneCollision();
    static Timer gTimer = new Timer();
    static gameTimer task = new gameTimer();
    static scoreCounter scoreCounter = new scoreCounter(450,494);
    static character hero = new character(165,380,"charSprites/charright2.png");
    static item boots,helm,sword;// = new item(2000,2000,30,19,1,ID.boot,"mapPack/floor1roomC.png");


    static trap[] traps = new trap[3];
    
    static item heart,score;
    static enemy m1,m2,m3,m4,m5,m6,m7,m8,m9,m10,m11,m12,m13,m14,m15,m16,m17,m18,m19;
    static item key,bossKey;
    static door basicDoor,bossDoor;
    static enemy[] monsters = new enemy[19];

    

    static item[] inventory = new item[1];
    //Create labels
    static Label interactLabel = new Label();
    static Label timeLabel = new Label();
    static Label healthLabel = new Label();
    static Label scoreLabel = new Label();
    static Label pause = new Label();
    
    Point pointerLocation;
    
    static Group root;
    static FadeTransition ft;
    static Timeline timeline;
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Labyrinth");
        primaryStage.setWidth(WIDTH);
        primaryStage.setHeight(HEIGHT);
        
        root = new Group();
        
        Scene scene = new Scene(root,WIDTH,HEIGHT,Color.BLACK);
        
        Canvas canvas = new Canvas(WIDTH,HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        //Set imageview/images
        
        //Set hero
        ImageView ivHero = new ImageView();
        //Set room scene
        ImageView room = new ImageView();
        room.setLayoutX(100);
        room.setLayoutY(100);
        //Place hud
        ImageView ivHud = new ImageView();
        Image hud = new Image("sidebar.png");
        ivHud.setLayoutX(1100);
        ivHud.setLayoutY(100);
        ivHud.setFitWidth(200);
        ivHud.setFitHeight(600);
        ivHud.setImage(hud);
        //Place border
        ImageView ivBord = new ImageView();
        Image border = new Image("border.png");
        ivBord.setImage(border);
        
        //Set up interaction label
        interactLabel.setLayoutX(550);
        interactLabel.setLayoutY(350);
        interactLabel.setTextFill(Color.WHITE);
        interactLabel.setFont(new Font("8-Bit Limit R (BRK)", 24));
        
        //Set up timer
        gTimer.scheduleAtFixedRate(task.getTimerTask(),1000,1000);
        timeString = task.getElapsedTime();
        timeLabel.setText(timeString);
        timeLabel.setLayoutX(1150);
        timeLabel.setLayoutY(205);
        timeLabel.setTextFill(Color.CYAN);
        timeLabel.setFont(new Font("8-Bit Limit R (BRK)", 20));
        
        //Set up score label
        scoreLabel.setLayoutX(1165);
        scoreLabel.setLayoutY(300);
        scoreLabel.setTextFill(Color.YELLOW);
        scoreLabel.setFont(new Font("8-Bit Limit R (BRK)", 20));
        
        //Set up health label
        healthLabel.setLayoutX(1145);
        healthLabel.setLayoutY(397);
        healthLabel.setTextFill(Color.RED);
        healthLabel.setFont(new Font("8-Bit Limit R (BRK)", 16));
        
        pause.setLayoutX(550);
        pause.setLayoutY(350);
        pause.setTextFill(Color.RED);
        pause.setFont(new Font("8-Bit Limit R (BRK)", 48));
        
        timeline = new Timeline(new KeyFrame(Duration.millis(1000), ae -> collisions()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        
        
        inventory[0] = new item(2000,2000,30,19,0,ID.boot,"mapPack/floor1roomC.png");
        

        for(int i = 0; i < doors.length; i++) {
            doors[i] = true;
        }
        
        for(int i = 0; i < traps.length; i++) {
            traps[i] = null;
        }
        
        //Create directional commands for character movement and interactions
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch(event.getCode()) {
                    case UP:
                    case W:
                        up = true;
                        direction = 4;
                        break;
                    case DOWN:
                    case S:
                        down = true;
                        direction = 2;
                        break;
                    case LEFT:
                    case A:
                        left = true;
                        direction = 3;
                        break;
                    case RIGHT:
                    case D:
                        right = true;
                        direction = 1;
                        break;
                    case F:
                        interact = true;
                        break;
                    case SPACE:
                        attack = true;
                        break;
                    case P:
                        if(running) running = false;
                        else running = true;
                        break;
                    case ENTER:
                        if(startup) {
                            startup = false;
                            running = true;
                        }
                        break;
                }
            }
            
        });
        
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                    case W:
                        up = false;
                         lastDirection();
                        break;
                    case DOWN:
                    case S:
                        down = false;
                        lastDirection();
                        break;
                    case LEFT:
                    case A:
                        left = false;
                        lastDirection();
                        break;
                    case RIGHT:
                    case D:
                        right = false;
                        lastDirection();
                        break;
                    case F:
                        interact = false;
                        break;
                    case SPACE:
                        attack = false;
                        break;
                }
            }
            
        });
        

        //Completely exit game on close
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
                System.exit(0);
            }
            
        });
        
        
        //Add nodes to root
        root.getChildren().addAll(canvas,room);

        root.getChildren().addAll(hero,ivHero,ivHud);

        root.getChildren().addAll(ivBord,pause,interactLabel,timeLabel,
                healthLabel,scoreLabel,ts);
      
        scene.setRoot(root);
        primaryStage.getIcons().add(new Image("LabyrinthIcon.png"));
        primaryStage.setScene(scene);
        primaryStage.show();
        
        //Create animationtimer to update gameboard
        AnimationTimer timer = new AnimationTimer() {
            
            private long lastUpdate = 0;
            private long heroUpdate = 0;
            private long monsterUpdate = 0;
            private long collisionUpdate = 0;
            @Override
            public void handle(long now) {   
                
                //object updates
                if(!startup) {
                    root.getChildren().remove(ts);
                } else {
                    ts.readFile();
                    ts.setLabels();
                    ts.setLocation();
                }
                if(now-lastUpdate >= 10000000) {
                    f1.setMapName(filename); //update current map file path
                    f1.doorCheck(hero); //Place correct room
                    lastUpdate = now;
                }
                
                pause.setText("Paused");
                
                //Set pause selection
                if(running) {
                    pause.setText("");
                    if(entered) {
                        image = null;
                        image = new Image(filename);
                        room.setImage(image);
                        
                        
                        
                        for(int i = 0; i < traps.length; i++) {
                            removeTrap(traps[i]);
                        }
                        for(int i = 0; i < monsters.length;i++) {
                            removeEnemy(monsters[i]);
                        }
                        removeItem(key);
                        removeItem(bossKey);
                        removeItem(sword);
                        removeItem(helm);
                        removeItem(boots);
                        removeItem(heart);
                        removeItem(score);
                        removeDoor(basicDoor);
                        removeDoor(bossDoor);
                        
                        setDoor();
                        bossDoor();
                        setKey();
                        setBossKey();
                        monsterArray();
                        setTrap();
                        setHeart();
                        setScore();
                        setBoots();
                        setArmour();
                        setSword();
                        
                        if(getKey() != null) {
                            root.getChildren().add(getKey());
                        }
                        if(getBossKey() != null) {
                            root.getChildren().add(getBossKey());
                        }
                        if(getHeart() != null) {
                            root.getChildren().add(getHeart());
                        }
                        if(getScore() != null) {
                            root.getChildren().add(getScore());
                        }
                        if(getBoots() != null) {
                            root.getChildren().add(getBoots());
                        }
                        if(getArmour() != null) {
                            root.getChildren().add(getArmour());
                        }
                        if(getSword() != null) {
                            root.getChildren().add(getSword());
                        }
                        if(getEnemy() != null) {
                            root.getChildren().add(getEnemy());
                        }
                        if(getDoor() != null) {
                            root.getChildren().add(getDoor());
                        }
                        if(getBossDoor() != null) {
                            root.getChildren().add(getBossDoor());
                        }
                        for(int i = 0; i < traps.length; i++) {
                            if(getTrap() != null){
                                if(traps[i] != null) root.getChildren().add(traps[i]);
                            }
                        }
                        entered = false;
                    }
                    
                    
                    
                    timeString = task.getElapsedTime();
                    timeLabel.setText(timeString); 
                    scoreLabel.setText(scoreCounter.toString());
                    
                    //Move character
                    if(now-heroUpdate >= 10000000) {
                        imHero = new Image(hero.setHeroView());
                        ivHero.setImage(imHero);
                        ivHero.setLayoutX(hero.getX());
                        ivHero.setLayoutY(hero.getY());
                        heroUpdate = now;
                    }
                    
                    int dx = 0;
                    int dy = 0;
                    if(up) {
                        dy -= SPEED;
                    }
                    if(down) {
                        dy += SPEED;
                    }
                    if(left) {
                        dx -= SPEED;
                    }
                    if(right) {
                        dx += SPEED;
                    }
                    hero.moveBy(dx,dy);
                    hero.hitBox();

                    if(key != null) {
                        key.setImage();
                        key.relocate();
                    }
                    if(bossKey != null) {
                        bossKey.setImage();
                        bossKey.relocate();
                    }
                    if(heart != null) {
                        heart.setImage();
                        heart.relocate();
                    }
                    if(score != null) {
                        score.setImage();
                        score.relocate();
                    }
                    if(boots != null) {
                        boots.setImage();
                        boots.relocate();
                    }
                    if(sword != null) {
                        sword.setImage();
                        sword.relocate();
                    }
                    if(helm != null) {
                        helm.setImage();
                        helm.relocate();
                    }
                    for(int i = 0; i < inventory.length; i++) {
                        inventory[i].setImage();
                        inventory[i].relocate();
                    }   

                    if(basicDoor != null) {
                        basicDoor.setImage();
                        basicDoor.relocate();
                    }
                    if(bossDoor != null) {
                        bossDoor.setImage();
                        bossDoor.relocate();
                    }
                    if(now-monsterUpdate >= 500000000) {
                        for(int i = 0;i<monsters.length;i++) {
                            if(monsters[i] != null) {
                                monsters[i].setImage(filename);
                                monsters[i].move();
                            }
                        }
                        monsterUpdate = now;
                    }
                    for(int i = 0; i < traps.length; i++) {
                        if(traps[i] != null) {
                            traps[i].setImage(filename);
                            traps[i].move();
                        }
                    }
                }
                
                healthLabel.setText("Health: " + HEALTH + "/100"); 
                if(HEALTH <= 0) {
                    HEALTH = 0;
                    running = false;
                    if(ts.intArray[0] == 0 && ts.intArray[1] == 0) {
                        save();
                    }
                    else if(task.minutesPassed <= ts.intArray[0]) {
                        if(task.secondsPassed < ts.intArray[1] && scoreCounter.getValue() >= ts.intArray[2]) {
                            save();
                        }
                    }
                }
            }
        };
        timer.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    public static void setMapName(String name) {
        filename = name;
    }
    
    public static void heroRelocate(int x, int y) {
        hero.setX(x);
        hero.setY(y);
        hero.relocate(hero.getX(),hero.getY());
    }
    
    public void lastDirection() {
        //1 = right, 2 = down, 3 = left, 4 = up
        if(up) {
            direction = 4;
        } else if(right) {
            direction = 1;
        } else if(down) {
            direction = 2;
        } else if(left) {
            direction = 3;
        }
    }  
    
    public static void removeObject(Group root,int index) {
        root.getChildren().remove(monsters[index]);
    }
    
    public static void removeItem(item iM) {
        root.getChildren().remove(iM);
    }
    
    public static void addItem(item iM) {
        root.getChildren().add(iM);
    }
    
    public static void removeDoor(door dM) {
        root.getChildren().remove(dM);
    }
    
    public static void removeTrap(trap tM) {
        root.getChildren().remove(tM);
    }
    
    public static void collisions() {
        for (int i = 0; i < monsters.length; i++) {
            monsters[i].collisions();
        }
    }
    
    public static void save() {
        try (BufferedWriter bw = new BufferedWriter(new PrintWriter("save.txt"))) {
            
                    bw.write(task.minutesPassed + "");
                    bw.newLine();
                    bw.write(task.secondsPassed + "");
                    bw.newLine();
                    bw.write(scoreCounter.toStringSave());
                    bw.newLine();

            bw.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void transitionIn(FadeTransition ft) {
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.play();
    }
    
    public static void transitionOut(FadeTransition ft) {
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
    }
    
    public static enemy getEnemy() {
        if(filename.equals("mapPack/floor1roomC.png")) {
            return m1;
        } else if (filename.equals("mapPack/floor1roomD.png")) {
            return m2;
        } else if (filename.equals("mapPack/floor1roomF.png")) {
            return m3;
        } else if(filename.equals("mapPack/floor1roomH.png")) {
            return m4;
        } else if(filename.equals("mapPack/floor1roomN.png")) {
            return m5;
        } else if(filename.equals("mapPack/floor2roomB.png")) {
            return m6;
        } else if(filename.equals("mapPack/floor2roomH.png")) {
            return m7;
        } else if(filename.equals("mapPack/floor2roomC.png")) {
            return m8;
        } else if(filename.equals("mapPack/floor2roomF.png")) {
            return m9;
        } else if(filename.equals("mapPack/floor2roomD.png")) {
            return m10;
        } else if(filename.equals("mapPack/floor2roomN.png")) {
            return m11;
        } else if(filename.equals("mapPack/floor3roomD.png")) {
            return m12;
        } else if(filename.equals("mapPack/floor3roomG.png")) {
            return m13;
        } else if(filename.equals("mapPack/floor3roomC.png")) {
            return m14;
        } else if(filename.equals("mapPack/floor3roomK.png")) {
            return m15;
        } else if(filename.equals("mapPack/floor3roomN.png")) {
            return m16;
        } else if(filename.equals("mapPack/floor3roomI.png")) {
            return m17;
        } else if(filename.equals("mapPack/floor3roomE.png")) {
            return m18;
        } else if(filename.equals("mapPack/floor3roomQ.png")) {
            return m19;
        }
        else return null;
    }
    
    public static void removeEnemy(enemy tM) {
        root.getChildren().remove(tM);
    }
    
    public void monsterArray() {
        
        if(filename.equals("mapPack/floor1roomC.png")) {
            m1 = new enemy(750,380,0,ID.basicEnemy,"mapPack/floor1roomC.png");
        } 
        else {
            m1 = null;
        }
        if(filename.equals("mapPack/floor1roomD.png")) {
            m2 = new enemy(750,380,1,ID.basicEnemy,"mapPack/floor1roomD.png");
        } 
        else {
            m2 = null;
        }
        if(filename.equals("mapPack/floor1roomF.png")) {
            m3 = new enemy(750,380,2,ID.basicEnemy,"mapPack/floor1roomF.png");
        } 
        else {
            m3 = null;
        }
        if(filename.equals("mapPack/floor1roomH.png")) {
            m4 = new enemy(750,380,3,ID.basicEnemy,"mapPack/floor1roomH.png");
        } 
        else {
            m4 = null;
        }
        if(filename.equals("mapPack/floor1roomN.png")) {
            m5 = new enemy(170,380,4,ID.bossEnemy,"mapPack/floor1roomN.png");
        } else {
            m5 = null;
        }
        
        //Floor 2
        if(filename.equals("mapPack/floor2roomB.png")) {
            m6 = new enemy(550,450,5,ID.basicEnemy,"mapPack/floor2roomB.png");
        } else {
            m6 = null;
        }
        if(filename.equals("mapPack/floor2roomH.png")) {
            m7 = new enemy(250,350,6,ID.basicEnemy,"mapPack/floor2roomH.png");
        } else {
            m7 = null;
        }
        if(filename.equals("mapPack/floor2roomC.png")) {
            m8 = new enemy(200,500,7,ID.basicEnemy,"mapPack/floor2roomC.png");
        } else {
            m8 = null;
        }
        if(filename.equals("mapPack/floor2roomF.png")) {
            m9 = new enemy(300,300,8,ID.basicEnemy,"mapPack/floor2roomF.png");
            
        } else {
            m9 = null;
        }
        if(filename.equals("mapPack/floor2roomD.png")) {
            m10 = new enemy(600,500,9,ID.basicEnemy,"mapPack/floor2roomD.png");
        } else {
            m10 = null;
        }
        if(filename.equals("mapPack/floor2roomN.png")) {
            m11 = new enemy(170,350,10,ID.bossEnemy,"mapPack/floor2roomN.png");
        } else {
            m11 = null;
        }
        if(filename.equals("mapPack/floor3roomD.png")) {
            m12 = new enemy(600,200,11,ID.basicEnemy,"mapPack/floor3roomD.png");
        } else {
            m12 = null;
        }
        if(filename.equals("mapPack/floor3roomG.png")) {
            m13 = new enemy(200,350,12,ID.basicEnemy,"mapPack/floor3roomG.png");
        } else {
            m13 = null;
        }
        if(filename.equals("mapPack/floor3roomC.png")) {
            m14 = new enemy(500,300,13,ID.basicEnemy,"mapPack/floor3roomC.png");
        } else {
            m14 = null;
        }
        if(filename.equals("mapPack/floor3roomK.png")) {
            m15 = new enemy(550,350,14,ID.basicEnemy,"mapPack/floor3roomK.png");
        } else {
            m15 = null;
        }
        if(filename.equals("mapPack/floor3roomN.png")) {
            m16 = new enemy(800,250,15,ID.basicEnemy,"mapPack/floor3roomN.png");
        } else {
            m16 = null;
        }
        if(filename.equals("mapPack/floor3roomI.png")) {
            m17 = new enemy(400,200,16,ID.basicEnemy,"mapPack/floor3roomI.png");
        } else {
            m17 = null;
        }
        if(filename.equals("mapPack/floor3roomE.png")) {
            m18 = new enemy(900,500,17,ID.basicEnemy,"mapPack/floor3roomE.png");
        } else {
            m18 = null;
        }
        if(filename.equals("mapPack/floor3roomQ.png")) {
            m19 = new enemy(940,350,18,ID.bossEnemy,"mapPack/floor3roomQ.png");
        } else {
            m19 = null;
        }
        
        
        monsters[0] = m1;
        monsters[1] = m2;
        monsters[2] = m3;
        monsters[3] = m4;
        monsters[4] = m5;
        monsters[5] = m6;
        monsters[6] = m7;
        monsters[7] = m8;
        monsters[8] = m9;
        monsters[9] = m10;
        monsters[10] = m11;
        monsters[11] = m12;
        monsters[12] = m13;
        monsters[13] = m14;
        monsters[14] = m15;
        monsters[15] = m16;
        monsters[16] = m17;
        monsters[17] = m18;
        monsters[18] = m19;
    }
    
    public static void setKey() {        
        if(filename.equals("mapPack/floor1roomC.png") && hero.getKey() == false && doors[0] == true) {
            key = new item(750,400,10,17,0,ID.key,"mapPack/floor1roomC.png");
        } else if(filename.equals("mapPack/floor1roomE.png") && hero.getKey() == false && doors[1] == true) {
            key = new item(800,200,10,17,1,ID.key,"mapPack/floor1roomE.png");
        } else if(filename.equals("mapPack/floor2roomD.png") && hero.getKey() == false && doors[2] == true) {
            key = new item(950,165,10,17,1,ID.key,"mapPack/floor2roomD.png");
        } else if(filename.equals("mapPack/floor2roomI.png") && hero.getKey() == false && doors[3] == true) {
            key = new item(200,550,10,17,1,ID.key,"mapPack/floor2roomI.png");
        } else if(filename.equals("mapPack/floor3roomH.png") && hero.getKey() == false && doors[4] == true) {
            key = new item(950,550,10,17,1,ID.key,"mapPack/floor3roomH.png");
        } else if(filename.equals("mapPack/floor3roomO.png") && hero.getKey() == false && doors[5] == true) {
            key = new item(165,165,10,17,1,ID.key,"mapPack/floor3roomO.png");
        }
        else if(hero.getKey()) {
            key = new item(1160,500,10,17,1,ID.key,"mapPack/floor1roomC.png");
        } 
        else {
            key = null;
        }
    }
    
    public static item getKey() {
        if(filename.equals("mapPack/floor1roomC.png")) {
            return key;
        } else if(filename.equals("mapPack/floor1roomE.png")) {
            return key;
        } else if(filename.equals("mapPack/floor2roomD.png")) {
            return key;
        } else if (filename.equals("mapPack/floor2roomI.png")) {
            return key;
        } else if(filename.equals("mapPack/floor3roomH.png")) {
            return key;
        } else if(filename.equals("mapPack/floor3roomO.png")) {
            return key;
        }
        else if(hero.getKey()) {
            return key;
        }
        else return null;
    }
    
    public static void setBossKey() {
        if(filename.equals("mapPack/floor1roomH.png") && hero.getBossKey() == false) {
            bossKey = new item(200,350,30,19,0,ID.bossKey,"mapPack/floor1roomH.png");
        } else if(filename.equals("mapPack/floor2roomL.png") && hero.getBossKey() == false) {
            bossKey = new item(200,350,30,19,0,ID.bossKey,"mapPack/floor2roomL.png");
        } else if(hero.getBossKey()) {
            bossKey = new item(1155,495,20,25,0,ID.bossKey,"mapPack/floor1roomH.png");
        }
        else {
            bossKey = null;
        }
    }
    
    public static item getBossKey() {
        if(filename.equals("mapPack/floor1roomH.png")) {
            return bossKey;
        } else if(filename.equals("mapPack/floor2roomL.png")) {
            return bossKey;
        } else if(hero.getBossKey()) {
            return bossKey;
        } else {
            return null;
        }
    }
    
    public static void setBoots() {
        if(filename.equals("mapPack/floor1roomG.png") && hero.getBoot() == false) {
            boots = new item(200,500,30,19,1,ID.boot,"mapPack/floor1roomG.png");
        } else if (hero.getBoot() == true) {
            boots = new item(1230,500,30,19,1,ID.boot,"mapPack/floor1roomG.png");
        }
        else {
            boots = null;
        }
    }
    
    public static item getBoots() {
        if(filename.equals("mapPack/floor1roomG.png")) {
            return boots;
        } else if(hero.getBoot() == true) {
            return boots;
        } else return null;
    }
    
    public static void setArmour() {
        if(filename.equals("mapPack/floor3roomA.png") && hero.getArmour() == false) {
            helm = new item(200,600,30,30,1,ID.armour,"mapPack/floor3roomA.png");
        } else if(hero.getArmour() == true) {
            helm = new item(1150,585,30,30,1,ID.armour,"mapPack/floor3roomA.png");
        }
        else helm = null;
    }
    
    public static item getArmour() {
        if(filename.equals("mapPack/floor3roomA.png")) {
            return helm;
        } else if(hero.getArmour() == true) {
            return helm;
        } else return null;
    }
    
    public static void setSword() {
        if(filename.equals("mapPack/floor2roomA.png") && hero.getSword() == false) {
            sword = new item(200,200,40,40,1,ID.weapon,"mapPack/floor2roomA.png");
        } else if(hero.getSword() == true) {
            sword = new item(1225,585,40,40,1,ID.weapon,"mapPack/floor2roomA.png");
        } else sword = null;
    }
    
    public static item getSword() {
        if(filename.equals("mapPack/floor2roomA.png")) {
            return sword;
        } else if(hero.getSword()) {
            return sword;
        } else return null;
    }
    
    public static void setDoor() {
        if(filename.equals("mapPack/floor1roomB.png") && doors[0] == true) {
            basicDoor = new door(1080,350,1,ID.door,true,"mapPack/floor1roomB.png");
        } else if (filename.equals("mapPack/floor1roomB.png") && doors[0] == false) {
            basicDoor = new door(1080,350,1,ID.door,false,"mapPack/floor1roomB.png");
        } else if(filename.equals("mapPack/floor1roomI.png") && doors[1] == true) {
            basicDoor = new door(100,350,3,ID.door,true,"mapPack/floor1roomI.png");
        } else if(filename.equals("mapPack/floor1roomI.png") && doors[1] == false) {
            basicDoor = new door(100,350,3,ID.door,false,"mapPack/floor1roomI.png");
        } else if(filename.equals("mapPack/floor2roomB.png") && doors[2] == true) {
            basicDoor = new door(100,350,3,ID.door,true,"mapPack/floor2roomB.png");
        } else if(filename.equals("mapPack/floor2roomB.png") && doors[2] == false) {
            basicDoor = new door(100,350,3,ID.door,false,"mapPack/floor2roomB.png");
        } else if(filename.equals("mapPack/floor2roomH.png") && doors[3] == true) {
            basicDoor = new door(100,350,3,ID.door,true,"mapPack/floor2roomH.png");
        } else if(filename.equals("mapPack/floor2roomH.png") && doors[3] == false) {
            basicDoor = new door(100,350,3,ID.door,false,"mapPack/floor2roomH.png");
        } else if(filename.equals("mapPack/floor3roomG.png") && doors[4] == true) {
            basicDoor = new door(100,350,3,ID.door,true,"mapPack/floor3roomG.png");
        } else if(filename.equals("mapPack/floor3roomG.png") && doors[4] == false) {
            basicDoor = new door(100,350,3,ID.door,false,"mapPack/floor3roomG.png");
        } else if(filename.equals("mapPack/floor3roomO.png") && doors[5] == true) {
            basicDoor = new door(1080,350,1,ID.door,true,"mapPack/floor3roomO.png");
        } else if(filename.equals("mapPack/floor3roomO.png") && doors[5] == false) {
            basicDoor = new door(1080,350,1,ID.door,false,"mapPack/floor3roomO.png");
        } 
        else {    
            basicDoor = null;
        }
    }
    
    public static door getDoor() {
        if(filename.equals("mapPack/floor1roomB.png")) {
            return basicDoor;
        } else if(filename.equals("mapPack/floor1roomI.png")) {
            return basicDoor;
        } else if(filename.equals("mapPack/floor2roomB.png")) {
            return basicDoor;
        } else if(filename.equals("mapPack/floor2roomH.png")) {
            return basicDoor;
        } else if(filename.equals("mapPack/floor3roomG.png")) {
            return basicDoor;
        } else if(filename.equals("mapPack/floor3roomO.png")) {
            return basicDoor;
        } 
        else return null;
    }
    
    public static void bossDoor() {
        if(filename.equals("mapPack/floor1roomF.png") && doors[6] == true) {
            bossDoor = new door(100,350,3,ID.bossDoor,true,"mapPack/floor1roomF.png");
        } else if(filename.equals("mapPack/floor1roomF.png") && doors[6] == false) {
            bossDoor = new door(100,350,3,ID.bossDoor,false,"mapPack/floor1roomF.png");
        } else if(filename.equals("mapPack/floor2roomJ.png") && doors[7] == true) {
            bossDoor = new door(100,350,3,ID.bossDoor,true,"mapPack/floor2roomJ.png");
        } else if(filename.equals("mapPack/floor2roomJ.png") && doors[7] == false) {
            bossDoor = new door(100,350,3,ID.bossDoor,false,"mapPack/floor2roomJ.png");
        } else {
            bossDoor = null;
        }
    } 
    
    public static door getBossDoor() {
        if(filename.equals("mapPack/floor1roomF.png")) {
            return bossDoor;
        } else if(filename.equals("mapPack/floor2roomJ.png")) {
            return bossDoor;
        } else return null;
    }
    
    public static void setTrap() {
        if(filename.equals("mapPack/floor1roomI.png")) {
            traps[0] = new trap(165,165,29,29,0,ID.roundSpike);
            traps[1] = null;
            traps[2] = null;
        } else if(filename.equals("mapPack/floor1roomE.png")) {
            traps[0] = new trap(200,350,29,29,1,ID.roundSpike);
            traps[1] = new trap(1000,450,29,29,1,ID.roundSpike);
            traps[2] = null;
        } else if(filename.equals("mapPack/floor2roomD.png")) {
            traps[0] = new trap(165,165,29,29,0,ID.roundSpike);
            traps[1] = null;
            traps[2] = null;
        } else if(filename.equals("mapPack/floor2roomE.png")) {
            traps[0] = new trap(400,165,29,29,0,ID.roundSpike);
            traps[1] = new trap(700,165,29,29,0,ID.roundSpike);
            traps[2] = new trap(1000,165,29,29,0,ID.roundSpike);
        } else if(filename.equals("mapPack/floor2roomM.png")) {
            traps[0] = new trap(200,200,29,29,0,ID.roundSpike);
            traps[1] = new trap(500,375,29,29,0,ID.roundSpike);
            traps[2] = new trap(800,600,29,29,0,ID.roundSpike);
        } else if(filename.equals("mapPack/floor2roomL.png")) {
            traps[0] = new trap(575,200,29,29,0,ID.roundSpike);
            traps[1] = null;
            traps[2] = null;
        } else if(filename.equals("mapPack/floor3roomH.png")) {
            traps[0] = new trap(525,600,29,29,0,ID.roundSpike);
            traps[1] = new trap(650,600,29,29,0,ID.roundSpike);
            traps[2] = null;
        } else if(filename.equals("mapPack/floor3roomD.png")) {
            traps[0] = new trap(900,200,29,29,0,ID.roundSpike);
            traps[1] = null;
            traps[2] = null;
        } else if(filename.equals("mapPack/floor3roomF.png")) {
            traps[0] = new trap(375,200,29,29,0,ID.roundSpike);
            traps[1] = null;
            traps[2] = null;
        } else if(filename.equals("mapPack/floor3roomL.png")) {
            traps[0] = new trap(525,200,29,29,0,ID.roundSpike);
            traps[1] = new trap(650,200,29,29,0,ID.roundSpike);
            traps[2] = null;
        } else if(filename.equals("mapPack/floor3roomN.png")) {
            traps[0] = new trap(900,200,29,29,0,ID.roundSpike);
            traps[1] = null;
            traps[2] = null;
        }
        else {
            traps[0] = null;
            traps[1] = null;
            traps[2] = null;
            
        }
    }
    
    public static trap[] getTrap() {
        if(filename.equals("mapPack/floor1roomI.png")) {
           return traps;
        } else if(filename.equals("mapPack/floor1roomE.png")) {
            return traps;
        } else if(filename.equals("mapPack/floor2roomD.png")) {
            return traps;
        } else if(filename.equals("mapPack/floor2roomE.png")) {
            return traps;
        } else if(filename.equals("mapPack/floor2roomM.png")) {
            return traps;
        } else if(filename.equals("mapPack/floor2roomL.png")) {
            return traps;
        } else if(filename.equals("mapPack/floor3roomH.png")) {
            return traps;
        } else if(filename.equals("mapPack/floor3roomD.png")) {
            return traps;
        } else if(filename.equals("mapPack/floor3roomF.png")) {
            return traps;
        } else if(filename.equals("mapPack/floor3roomL.png")) {
            return traps;
        } else if(filename.equals("mapPack/floor3roomN.png")) {
            return traps;
        }
        else return null;
    }
    
    public static void setHeart() {
        if(filename.equals("mapPack/floor1roomG.png")) {
            heart = new item(800,500,30,19,0,ID.heart,"mapPack/floor1roomG.png");
        } else if(filename.equals("mapPack/floor2roomB.png")) {
            heart = new item(200,200,30,19,0,ID.heart,"mapPack/floor2roomB.png");
        } else if(filename.equals("mapPack/floor2roomF.png")) {
            heart = new item(800,200,30,19,0,ID.heart,"mapPack/floor2roomF.png");
        } else if(filename.equals("mapPack/floor2roomM.png")) {
            heart = new item(900,500,30,19,0,ID.heart,"mapPack/floor2roomM.png");
        } else if(filename.equals("mapPack/floor3roomB.png")) {
            heart = new item(200,300,30,19,0,ID.heart,"mapPack/floor3roomB.png");
        } else if(filename.equals("mapPack/floor3roomJ.png")) {
            heart = new item(200,200,30,19,0,ID.heart,"mapPack/floor3roomJ.png");
        } else if(filename.equals("mapPack/floor3roomP.png")) {
            heart = new item(900,200,30,19,0,ID.heart,"mapPack/floor3roomP.png");
        }
        else {
            heart = null;
        }
    }
    
    public static item getHeart() {
        if(filename.equals("mapPack/floor1roomG.png")) {
            return heart;
        } else if(filename.equals("mapPack/floor2roomB.png")) {
            return heart;
        } else if(filename.equals("mapPack/floor2roomF.png")) {
            return heart;
        } else if(filename.equals("mapPack/floor2roomM.png")) {
            return heart;
        } else if(filename.equals("mapPack/floor3roomB.png")) {
            return heart;
        } else if(filename.equals("mapPack/floor3roomJ.png")) {
            return heart;
        } else if(filename.equals("mapPack/floor3roomP.png")) {
            return heart;
        }
        else return null;
    }
    
    public static void setScore() {
        if(filename.equals("mapPack/floor1roomI.png")) {
            score = new item(700,500,30,19,0,ID.score,"mapPack/floor1roomI.png");
        } else if(filename.equals("mapPack/floor2roomM.png")) {
            score = new item(300,200,30,19,0,ID.score,"mapPack/floor2roomM.png");
        } else if(filename.equals("mapPack/floor2roomH.png")) {
            score = new item(200,200,30,19,0,ID.score,"mapPack/floor2roomH.png");
        } else if(filename.equals("mapPack/floor2roomF.png")) {
            score = new item(700,200,30,19,0,ID.score,"mapPack/floor2roomF.png");
        } else if(filename.equals("mapPack/floor3roomE.png")) {
            score = new item(900,500,30,19,0,ID.score,"mapPack/floor3roomE.png");
        } else if(filename.equals("mapPack/floor3roomK.png")) {
            score = new item(200,200,30,19,0,ID.score,"mapPack/floor3roomK.png");
        } else if(filename.equals("mapPack/floor3roomO.png")) {
            score = new item(900,200,30,19,0,ID.score,"mapPack/floor3roomO.png");
        } else {
            score = null;
        }
    }
    
    public static item getScore() {
        if(filename.equals("mapPack/floor1roomI.png")) {
            return score;
        } else if(filename.equals("mapPack/floor2roomM.png")) {
            return score;
        } else if(filename.equals("mapPack/floor2roomH.png")) {
            return score;
        } else if(filename.equals("mapPack/floor2roomF.png")) {
            return score;
        } else if(filename.equals("mapPack/floor3roomE.png")) {
            return score;
        } else if(filename.equals("mapPack/floor3roomK.png")) {
            return score;
        } else if(filename.equals("mapPack/floor3roomO.png")) {
            return score;
        } else return null;
    }
}
