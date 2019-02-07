package labyrinth;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class character extends StackPane{
    static int x,y,velX,velY;
    static int ATTACK = 15;
    ImageView iv1;
    Image hero;
    private boolean hasKey = false;
    private boolean hasBossKey = false;
    private boolean hasBoots = false;
    private boolean hasArmour = false;
    private boolean hasSword = false;
    static String charFile;
    private int tick  = 1;
    
    public character(int x, int y, String charFile) {
        this.x = x;
        this.y = y;
        this.charFile = charFile;
    }
    
    public String setHeroView() {
        if(Labyrinth.direction == 4 && !Labyrinth.attack && !hasSword) return "charSprites/backwoodsword.png";
        else if(Labyrinth.direction == 2 && !Labyrinth.attack && !hasSword) return "charSprites/frontwoodsword.png";
        else if(Labyrinth.direction == 3 && !Labyrinth.attack && !hasSword) return "charSprites/leftwoodsword.png";
        else if(Labyrinth.direction == 1 && !Labyrinth.attack && !hasSword) return "charSprites/rightwoodsword.png";
        else if(Labyrinth.direction == 1 && Labyrinth.attack && !hasSword) return "charSprites/rightwoodswordattack.png";
        else if(Labyrinth.direction == 3 && Labyrinth.attack && !hasSword) return "charSprites/leftwoodswordattack.png";
        else if(Labyrinth.direction == 2 && Labyrinth.attack && !hasSword) return "charSprites/frontwoodswordattack.png";
        else if(Labyrinth.direction == 4 && Labyrinth.attack && !hasSword)return "charSprites/backwoodswordattack.png";
        else if(Labyrinth.direction == 4 && !Labyrinth.attack && hasSword) return "charSprites/backmastersword.png";
        else if(Labyrinth.direction == 4 && Labyrinth.attack && hasSword) return "charSprites/backmasterswordattack.png";
        else if(Labyrinth.direction == 3 && !Labyrinth.attack && hasSword) return "charSprites/leftmastersword.png";
        else if(Labyrinth.direction == 3 && Labyrinth.attack && hasSword) return "charSprites/leftmasterswordattack.png";
        else if(Labyrinth.direction == 2 && !Labyrinth.attack && hasSword) return "charSprites/frontmastersword.png";
        else if(Labyrinth.direction == 2 && Labyrinth.attack && hasSword) return "charSprites/frontmasterswordattack.png";
        else if(Labyrinth.direction == 1 && !Labyrinth.attack && hasSword) return "charSprites/rightmastersword.png";
        else if(Labyrinth.direction == 1 && Labyrinth.attack && hasSword) return "charSprites/rightmasterswordattack.png";
        else return null;
    }
    
    public Rectangle2D getBounds() {
        return new Rectangle2D(x+8,y+22,21,22);
    }
    
    public Rectangle2D hitBox() {
        if (Labyrinth.direction == 1) {
            return new Rectangle2D(x +22, y+10, 40,40);
        } else if (Labyrinth.direction == 2) {
            return new Rectangle2D(x+8,y+ 45,40,40);
        } else if (Labyrinth.direction == 3) {
            return new Rectangle2D(x-21,y,40,40);
        } else if (Labyrinth.direction == 4) {
            return new Rectangle2D(x+8,y - 1, 40,40);
        } else return null;
    }
    
    public void moveBy(int dx, int dy) {
        velX = dx;
        velY = dy;
        setPosition();
    }
    
    public void setPosition() {
        x += velX;
        y += velY;
        collision();
    }
    
    public void collision() {
        Labyrinth.interactLabel.setText("");
        
        //Check for key
        if(Labyrinth.getKey() != null) {
            if(getBounds().intersects(Labyrinth.key.getBounds())) {
                x-=velX;
                y-=velY;
            }
        }
        if(Labyrinth.getBossKey() != null) {
            if(getBounds().intersects(Labyrinth.bossKey.getBounds())) {
                x -= velX;
                y -= velY;
            }
        }

        if(Labyrinth.basicDoor != null) {
            if(getBounds().intersects(Labyrinth.basicDoor.getBounds())) {
                x-=velX;
                y-=velY;
            }
            if(hitBox().intersects(Labyrinth.basicDoor.getBounds())) {
                
                if(!hasKey) Labyrinth.interactLabel.setText("This door is locked");
                else if (hasKey) Labyrinth.interactLabel.setText("(F) to unlock");
                if(Labyrinth.interact && hasKey) {
                    Labyrinth.removeDoor(Labyrinth.basicDoor);
                    Labyrinth.basicDoor = null;
                    if(Labyrinth.filename.equals("mapPack/floor1roomB.png")) Labyrinth.doors[0] = false;   
                    else if(Labyrinth.filename.equals("mapPack/floor1roomI.png")) Labyrinth.doors[1] = false;
                    else if(Labyrinth.filename.equals("mapPack/floor2roomB.png")) Labyrinth.doors[2] = false;
                    else if(Labyrinth.filename.equals("mapPack/floor2roomH.png")) Labyrinth.doors[3] = false;
                    else if(Labyrinth.filename.equals("mapPack/floor3roomG.png")) Labyrinth.doors[4] = false;
                    else if(Labyrinth.filename.equals("mapPack/floor3roomO.png")) Labyrinth.doors[5] = false;
                    Labyrinth.setDoor();
                    if(Labyrinth.getDoor() != null) Labyrinth.root.getChildren().add(Labyrinth.basicDoor);
                    Labyrinth.basicDoor.setSameBounds(false);
                    hasKey = false;
                    Labyrinth.interact = false;
                    Labyrinth.root.getChildren().remove(Labyrinth.key);
                    Labyrinth.key = null;
                   
                }
                if(Labyrinth.filename.equals("mapPack/floor1roomB.png") && Labyrinth.doors[0] == false) {
                    Labyrinth.basicDoor.setSameBounds(false);
                } else if(Labyrinth.filename.equals("mapPack/floor1roomI.png") && Labyrinth.doors[1] == false) {
                    Labyrinth.basicDoor.setSameBounds(false);
                } else if(Labyrinth.filename.equals("mapPack/floor2roomB.png") && Labyrinth.doors[2] == false) {
                    Labyrinth.basicDoor.setSameBounds(false);
                } else if(Labyrinth.filename.equals("mapPack/floor2roomH.png") && Labyrinth.doors[3] == false) {
                    Labyrinth.basicDoor.setSameBounds(false);
                } else if(Labyrinth.filename.equals("mapPack/floor3roomG.png") && Labyrinth.doors[4] == false) {
                    Labyrinth.basicDoor.setSameBounds(false);
                } else if(Labyrinth.filename.equals("mapPack/floor3roomO.png") && Labyrinth.doors[5] == false) {
                    Labyrinth.basicDoor.setSameBounds(false);
                }
            }
        }
        if(Labyrinth.bossDoor != null) {
            if(getBounds().intersects(Labyrinth.bossDoor.getBounds())) {
                x-= velX;
                y-=velY;
            }
            if(hitBox().intersects(Labyrinth.bossDoor.getBounds())) {
                
                if(!hasBossKey) Labyrinth.interactLabel.setText("This door is locked");
                else if(hasKey) Labyrinth.interactLabel.setText("This door requires a special key");
                else if(hasBossKey) Labyrinth.interactLabel.setText("(F) to unlock");
                
                if(Labyrinth.interact && hasBossKey) {
                    hasBossKey = false;
                    Labyrinth.removeDoor(Labyrinth.bossDoor);
                    Labyrinth.bossDoor = null;
                    if(Labyrinth.filename.equals("mapPack/floor1roomF.png")) Labyrinth.doors[6] = false;
                    else if(Labyrinth.filename.equals("mapPack/floor2roomJ.png")) Labyrinth.doors[7] = false;
                    Labyrinth.setDoor();
                    if(Labyrinth.getDoor() != null) Labyrinth.root.getChildren().add(Labyrinth.bossDoor);
                    Labyrinth.bossDoor.setSameBounds(false);
                    //hasBossKey = false;
                    Labyrinth.interact = false;
                    Labyrinth.root.getChildren().remove(Labyrinth.bossKey);
                    Labyrinth.bossKey = null;
                    
                }
                if(Labyrinth.filename.equals("mapPack/floor1roomF.png") && Labyrinth.doors[6] == false) {
                    Labyrinth.bossDoor.setSameBounds(false);
                } else if (Labyrinth.filename.equals("mapPack/floor2roomJ.png") && Labyrinth.doors[7] == false) {
                    Labyrinth.bossDoor.setSameBounds(false);
                }
            }
        }

        //Check for heart
        if(Labyrinth.heart != null) {
            if(getBounds().intersects(Labyrinth.heart.getBounds())) {
                Labyrinth.HEALTH += 20;
                if(Labyrinth.HEALTH > 100) Labyrinth.HEALTH = 100;
                Labyrinth.heart.setVis(false);
                Labyrinth.removeItem(Labyrinth.heart);
                Labyrinth.heart = null;
            }
        }
        
        if(Labyrinth.score != null) {
            if(getBounds().intersects(Labyrinth.score.getBounds())) {
                scoreCounter.increment = true;
                Labyrinth.scoreCounter.increment(50);
                Labyrinth.score.setVis(false);
                Labyrinth.removeItem(Labyrinth.score);
                Labyrinth.score = null;
            }
        }
        
        if(Labyrinth.boots != null) {
            if(getBounds().intersects(Labyrinth.boots.getBounds())) {
                x -= velX;
                y -= velY;
            }
            if(hitBox().intersects(Labyrinth.boots.getBounds())) {
                Labyrinth.interactLabel.setText("(F) pick up item"); 
                if(Labyrinth.interact) {
                    Labyrinth.removeItem(Labyrinth.boots);
                    Labyrinth.boots = null;
                    hasBoots = true;
                    Labyrinth.setBoots();
                    if(Labyrinth.getBoots() != null) Labyrinth.root.getChildren().add(Labyrinth.getBoots());
                    Labyrinth.SPEED += 2;
                }
            }
        }
        if(Labyrinth.sword != null) {
            if(getBounds().intersects(Labyrinth.sword.getBounds())) {
                x-=velX;
                y-=velY;
            }
            if(hitBox().intersects(Labyrinth.sword.getBounds())) {
                Labyrinth.interactLabel.setText("(F) pick up item");
                if(Labyrinth.interact) {
                    Labyrinth.removeItem(Labyrinth.sword);
                    Labyrinth.sword = null;
                    hasSword = true;
                    Labyrinth.setSword();
                    if(Labyrinth.getSword() != null) Labyrinth.root.getChildren().add(Labyrinth.getSword());
                    ATTACK = 25;
                }
            }
        }
        if(Labyrinth.helm != null) {
            if(getBounds().intersects(Labyrinth.helm.getBounds())) {
                x-=velX;
                y-=velY;
            }
            if(hitBox().intersects(Labyrinth.helm.getBounds())) {
                Labyrinth.interactLabel.setText("(F) pick up item");
                if(Labyrinth.interact) {
                    Labyrinth.removeItem(Labyrinth.helm);
                    Labyrinth.helm = null;
                    hasArmour = true;
                    Labyrinth.setArmour();
                    if(Labyrinth.getArmour() != null) Labyrinth.root.getChildren().add(Labyrinth.getArmour());
                }
            }
        }

        for(int i = 0; i < Labyrinth.traps.length; i++) {
            if(Labyrinth.traps[i] != null) {
                if(getBounds().intersects(Labyrinth.traps[i].getBounds())) {
                    if(!hasArmour) Labyrinth.HEALTH -= 5;
                    else if(hasArmour) Labyrinth.HEALTH -= 3;
                    if(Labyrinth.direction == 1) x -= 10;
                    else if(Labyrinth.direction == 2) y -= 10;
                    else if(Labyrinth.direction == 3) x += 10;
                    else if(Labyrinth.direction == 4) y += 10;
                }
            }
        }
        
        if(Labyrinth.getKey() != null) {
            if(hitBox().intersects(Labyrinth.key.getBounds())) {
                Labyrinth.interactLabel.setText("(F) pick up item");
                if(Labyrinth.interact) {
                    Labyrinth.removeItem(Labyrinth.key);
                    Labyrinth.key = null;
                    hasKey = true;
                    Labyrinth.setKey();
                    if(Labyrinth.getKey() != null) Labyrinth.addItem(Labyrinth.getKey());
                    Labyrinth.interact = false;
                }
            }
        }
        
        if(Labyrinth.getBossKey() != null) {
            if(hitBox().intersects(Labyrinth.bossKey.getBounds())) {
                Labyrinth.interactLabel.setText("(F) pick up item");
                if(Labyrinth.interact) {
                    Labyrinth.removeItem(Labyrinth.bossKey);
                    Labyrinth.bossKey = null;
                    hasBossKey = true;
                    Labyrinth.setBossKey();
                    if(Labyrinth.getBossKey() != null) Labyrinth.addItem(Labyrinth.getBossKey());
                    Labyrinth.interact = false;
                }
            }
        }

        //Check for enemies
        for(int i = 0; i < Labyrinth.monsters.length; i++) {
            if(Labyrinth.monsters[i] != null) {
                if(getBounds().intersects(Labyrinth.monsters[i].getBounds())) {
                    if(!hasArmour) Labyrinth.HEALTH -= 2;
                    else if(hasArmour) Labyrinth.HEALTH -= 1;
                    x -= velX*5;
                    y -= velY*5;
                }
                if(getBounds().intersects(Labyrinth.monsters[i].hitBox())) {
                    if(Labyrinth.attack) {
                        Labyrinth.monsters[i].decHealth(ATTACK);
                        Labyrinth.attack = false;
                    }
                }
            }
        }
        //Wall collisions
        for(int i = 0;i<floorOneCollision.walls.size();i++) {
            if(getBounds().intersects(floorOneCollision.walls.get(i))) {
                x-=velX;
                y-=velY;
            }
        }
        for(int i = 0; i<floorOneCollision.interiors.size();i++) {
            if(getBounds().intersects(floorOneCollision.interiors.get(i))) {
                x-=velX;
                y-=velY;
            }
        }
    }
    
    public boolean getKey() {
        return hasKey;
    }
    
    public void setKey(boolean newKey) {
        hasKey = newKey;
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
    
    public boolean getBoot() {
        return hasBoots;
    }
    
    public boolean getArmour() {
        return hasArmour;
    }
    
    public boolean getSword() {
        return hasSword;
    }
    
    public boolean getBossKey() {
        return hasBossKey;
    }
}
