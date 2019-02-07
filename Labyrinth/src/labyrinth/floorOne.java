package labyrinth;

import javafx.geometry.Rectangle2D;
import javafx.scene.layout.StackPane;
//import static labyrinth.Labyrinth.setMon;
import static labyrinth.Labyrinth.entered;

public class floorOne extends StackPane {
    static String filename;
    static Rectangle2D doorLeft,doorRight,doorTop,doorBot,top,bottom,right,left;
    
    public floorOne(String name) {
        this.filename = name;//Left Door
        doorLeft = new Rectangle2D(50,350,20,100);
        //Right door
        doorRight = new Rectangle2D(1120,350,20,100);
        //Top door
        doorTop = new Rectangle2D(550,50,100,20);
        //Bottom door
        doorBot = new Rectangle2D(550,720,100,20);
        //Collision walls
        top = new Rectangle2D(550,100,100,60);
        right = new Rectangle2D(1040,350,60,100);
        bottom = new Rectangle2D(550,640,100,60);
        left = new Rectangle2D(100,350,60,100);
    }
    
    public void setMapName(String name) {
        filename = name;
    }
    
    public void doorCheck(character hero) {
        for(int i = 0; i<floorOneCollision.interiors.size();i++) {
            if(!floorOneCollision.interiors.isEmpty()) floorOneCollision.interiors.remove();
        }
        floorOneCollision.interior();
        
        if (filename.equals("mapPack/floor1roomA.png")) {
            //setMon = true;
            if(hero.getBounds().intersects(doorRight)) {
                Labyrinth.setMapName("mapPack/floor1roomB.png");
                Labyrinth.heroRelocate(165, 380);
                entered = true;
            }
        } else if (filename.equals("mapPack/floor1roomB.png")) {
            
            if(hero.getBounds().intersects(doorLeft)) {
                Labyrinth.setMapName("mapPack/floor1roomA.png");
                Labyrinth.heroRelocate(1030,380);
                entered = true;
            } else if (hero.getBounds().intersects(doorBot)) {
                Labyrinth.setMapName("mapPack/floor1roomC.png");
                Labyrinth.heroRelocate(580, 165);
                entered = true;
            } else if (hero.getBounds().intersects(doorRight)) {
                Labyrinth.setMapName("mapPack/floor1roomD.png");
                Labyrinth.heroRelocate(165,380);
                entered = true;
            }
        } else if (filename.equals("mapPack/floor1roomC.png")) {
            
            if(hero.getBounds().intersects(doorTop)) {
                Labyrinth.setMapName("mapPack/floor1roomB.png");
                Labyrinth.heroRelocate(580,630);
                entered = true;
            }
        } else if (filename.equals("mapPack/floor1roomD.png")) {
            
            if(hero.getBounds().intersects(doorLeft)) {
                Labyrinth.setMapName("mapPack/floor1roomB.png");
                Labyrinth.heroRelocate(1030, 380);
                entered = true;
            } else if (hero.getBounds().intersects(doorBot)) {
                Labyrinth.setMapName("mapPack/floor1roomE.png");
                Labyrinth.heroRelocate(580, 165);
                entered = true;
            }
        } else if (filename.equals("mapPack/floor1roomE.png")) {
            
            if(hero.getBounds().intersects(doorTop)) {
                Labyrinth.setMapName("mapPack/floor1roomD.png");
                Labyrinth.heroRelocate(580, 630);
                entered = true;
            } else if (hero.getBounds().intersects(doorBot)) {
                Labyrinth.setMapName("mapPack/floor1roomF.png");
                Labyrinth.heroRelocate(580, 165);
                entered = true;
            }
        } else if (filename.equals("mapPack/floor1roomF.png")) {
            
            if(hero.getBounds().intersects(doorTop)) {
                Labyrinth.setMapName("mapPack/floor1roomE.png");
                Labyrinth.heroRelocate(580, 630);
                entered = true;
            } else if (hero.getBounds().intersects(doorLeft)) {
                Labyrinth.setMapName("mapPack/floor1roomN.png");
                Labyrinth.heroRelocate(1030, 380);
                entered = true;
            } else if (hero.getBounds().intersects(doorBot)) {
                Labyrinth.setMapName("mapPack/floor1roomG.png");
                Labyrinth.heroRelocate(580,165);
                entered = true;
            }
        } else if (filename.equals("mapPack/floor1roomG.png")) {
            if(hero.getBounds().intersects(doorTop)) {
                Labyrinth.setMapName("mapPack/floor1roomF.png");
                Labyrinth.heroRelocate(580, 630);
                entered = true;
            } else if (hero.getBounds().intersects(doorBot)) {
                Labyrinth.setMapName("mapPack/floor1roomI.png");
                Labyrinth.heroRelocate(580, 165);
                entered = true;
            }
        } else if (filename.equals("mapPack/floor1roomH.png")) {
            
            if(hero.getBounds().intersects(doorRight)) {
                Labyrinth.setMapName("mapPack/floor1roomI.png");
                Labyrinth.heroRelocate(165,380);
                entered = true;
            }
        } else if (filename.equals("mapPack/floor1roomI.png")) {
            if(hero.getBounds().intersects(doorTop)) {
                Labyrinth.setMapName("mapPack/floor1roomG.png");
                Labyrinth.heroRelocate(580,630);
                entered = true;
            } else if (hero.getBounds().intersects(doorLeft)) {
                Labyrinth.setMapName("mapPack/floor1roomH.png");
                Labyrinth.heroRelocate(1030, 380);
                entered = true;
            }
        } else if (filename.equals("mapPack/floor1roomN.png")) {
            if(hero.getBounds().intersects(doorRight)) {
                Labyrinth.setMapName("mapPack/floor1roomF.png");
                Labyrinth.heroRelocate(165,380);
                entered = true;
            } else if(hero.getBounds().intersects(doorLeft)) {
                Labyrinth.setMapName("mapPack/floor2roomA.png");
                Labyrinth.heroRelocate(1030,380);
                entered = true;
            }
        }else if(filename.equals("mapPack/floor2roomA.png")) {
            //setMon = true;
            if(hero.getBounds().intersects(doorLeft)) {
                Labyrinth.setMapName("mapPack/floor2roomB.png");
                Labyrinth.heroRelocate(1030,380);
                entered = true;
            } else if (hero.getBounds().intersects(doorRight)) {
                Labyrinth.setMapName("mapPack/floor1roomN.png");
                Labyrinth.heroRelocate(165,380);
                entered = true;
            }
        } else if(filename.equals("mapPack/floor2roomB.png")) {
            if(hero.getBounds().intersects(doorRight)) {
                Labyrinth.setMapName("mapPack/floor2roomA.png");
                Labyrinth.heroRelocate(165,380);
                entered = true;
            } else if(hero.getBounds().intersects(doorBot)) {
                Labyrinth.setMapName("mapPack/floor2roomC.png");
                Labyrinth.heroRelocate(580,165);
                entered = true;
            } else if(hero.getBounds().intersects(doorLeft)) {
                Labyrinth.setMapName("mapPack/floor2roomH.png");
                Labyrinth.heroRelocate(1030,380);
                entered = true;
            } else if(hero.getBounds().intersects(doorTop)) {
                Labyrinth.setMapName("mapPack/floor2roomE.png");
                Labyrinth.heroRelocate(580,630);
                entered = true;
            }
        } else if(filename.equals("mapPack/floor2roomC.png")) {
            if(hero.getBounds().intersects(doorTop)) {
                Labyrinth.setMapName("mapPack/floor2roomB.png");
                Labyrinth.heroRelocate(580,630);
                entered = true;
            } else if (hero.getBounds().intersects(doorRight)) {
                Labyrinth.setMapName("mapPack/floor2roomD.png");
                Labyrinth.heroRelocate(165,380);
                entered = true;
            }
        } else if(filename.equals("mapPack/floor2roomD.png")) {
            if(hero.getBounds().intersects(doorLeft)) {
                Labyrinth.setMapName("mapPack/floor2roomC.png");
                Labyrinth.heroRelocate(1030,380);
                entered = true;
            }
        } else if(filename.equals("mapPack/floor2roomE.png")) {
            if(hero.getBounds().intersects(doorBot)) {
                Labyrinth.setMapName("mapPack/floor2roomB.png");
                Labyrinth.heroRelocate(580,165);
                entered = true;
            } else if(hero.getBounds().intersects(doorLeft)) {
                Labyrinth.setMapName("mapPack/floor2roomG.png");
                Labyrinth.heroRelocate(1030,380);
                entered = true;
            } else if(hero.getBounds().intersects(doorTop)) {
                Labyrinth.setMapName("mapPack/floor2roomF.png");
                Labyrinth.heroRelocate(580,630);
                entered = true;
            }
        } else if(filename.equals("mapPack/floor2roomF.png")) {
            if(hero.getBounds().intersects(doorBot)) {
                Labyrinth.setMapName("mapPack/floor2roomE.png");
                Labyrinth.heroRelocate(580, 165);
                entered = true;
            }
        } else if(filename.equals("mapPack/floor2roomG.png")) {
            if(hero.getBounds().intersects(doorRight)) {
                Labyrinth.setMapName("mapPack/floor2roomE.png");
                Labyrinth.heroRelocate(165,380);
                entered = true;
            } else if(hero.getBounds().intersects(doorBot)) {
                Labyrinth.setMapName("mapPack/floor2roomH.png");
                Labyrinth.heroRelocate(580,165);
                entered = true;
            }
        } else if(filename.equals("mapPack/floor2roomH.png")) {
            if(hero.getBounds().intersects(doorRight)) {
                Labyrinth.setMapName("mapPack/floor2roomB.png");
                Labyrinth.heroRelocate(165, 380);
                entered = true;
            } else if(hero.getBounds().intersects(doorBot)) {
                Labyrinth.setMapName("mapPack/floor2roomI.png");
                Labyrinth.heroRelocate(580, 165);
                entered = true;
            } else if(hero.getBounds().intersects(doorLeft)) {
                Labyrinth.setMapName("mapPack/floor2roomJ.png");
                Labyrinth.heroRelocate(1030, 380);
                entered = true;
            } else if(hero.getBounds().intersects(doorTop)) {
                Labyrinth.setMapName("mapPack/floor2roomG.png");
                Labyrinth.heroRelocate(580, 630);
                entered = true;
            }
        } else if(filename.equals("mapPack/floor2roomI.png")) {
            if(hero.getBounds().intersects(doorTop)) {
                Labyrinth.setMapName("mapPack/floor2roomH.png");
                Labyrinth.heroRelocate(580, 630);
                entered = true;
            } 
        } else if(filename.equals("mapPack/floor2roomJ.png")) {
            if(hero.getBounds().intersects(doorRight)) {
                Labyrinth.setMapName("mapPack/floor2roomH.png");
                Labyrinth.heroRelocate(165,380);
                entered = true;
            } else if(hero.getBounds().intersects(doorBot)) {
                Labyrinth.setMapName("mapPack/floor2roomM.png");
                Labyrinth.heroRelocate(580, 165);
                entered = true;
            } else if(hero.getBounds().intersects(doorLeft)) {
                Labyrinth.setMapName("mapPack/floor2roomN.png");
                Labyrinth.heroRelocate(1030,380);
                entered = true;
            } else if(hero.getBounds().intersects(doorTop)) {
                Labyrinth.setMapName("mapPack/floor2roomK.png");
                Labyrinth.heroRelocate(580, 630);
                entered = true;
            }
        } else if(filename.equals("mapPack/floor2roomK.png")) {
            if(hero.getBounds().intersects(doorBot)) {
                Labyrinth.setMapName("mapPack/floor2roomJ.png");
                Labyrinth.heroRelocate(580, 165);
                entered = true;
            } else if(hero.getBounds().intersects(doorLeft)) {
                Labyrinth.setMapName("mapPack/floor2roomL.png");
                Labyrinth.heroRelocate(1030, 380);
                entered = true;
            }
        } else if(filename.equals("mapPack/floor2roomL.png")) {
            if(hero.getBounds().intersects(doorRight)) {
                Labyrinth.setMapName("mapPack/floor2roomK.png");
                Labyrinth.heroRelocate(165,380);
                entered = true;
            }
        } else if(filename.equals("mapPack/floor2roomM.png")) {
            if(hero.getBounds().intersects(doorTop)) {
                Labyrinth.setMapName("mapPack/floor2roomJ.png");
                Labyrinth.heroRelocate(580, 630);
                entered = true;
            }
        } else if(filename.equals("mapPack/floor2roomN.png")) {
            if(hero.getBounds().intersects(doorRight)) {
                Labyrinth.setMapName("mapPack/floor2roomJ.png");
                Labyrinth.heroRelocate(165, 380);
                entered = true;
            } else if(hero.getBounds().intersects(doorLeft)) {
                Labyrinth.setMapName("mapPack/floor3roomA.png");
                Labyrinth.heroRelocate(1030,380);
                entered = true;
            }
        } else if (filename.equals("mapPack/floor3roomA.png")) {
            //setMon = true;
            if(hero.getBounds().intersects(doorLeft)) {
                Labyrinth.setMapName("mapPack/floor3roomB.png");
                Labyrinth.heroRelocate(1030, 380);
                entered = true;
            } else if(hero.getBounds().intersects(doorTop)) {
                Labyrinth.setMapName("mapPack/floor3roomH.png");
                Labyrinth.heroRelocate(580, 630);
                entered = true;
            }
        } else if(filename.equals("mapPack/floor3roomB.png")) {
            if(hero.getBounds().intersects(doorBot)) {
                Labyrinth.setMapName("mapPack/floor3roomC.png");
                Labyrinth.heroRelocate(580, 165);
                entered = true;
            } else if(hero.getBounds().intersects(doorRight)) {
                Labyrinth.setMapName("mapPack/floor3roomA.png");
                Labyrinth.heroRelocate(165, 380);
                entered = true;
            }
        } else if (filename.equals("mapPack/floor3roomC.png")) {
            if(hero.getBounds().intersects(doorTop)) {
                Labyrinth.setMapName("mapPack/floor3roomB.png");
                Labyrinth.heroRelocate(580, 630);
                entered = true;
            } else if(hero.getBounds().intersects(doorRight)) {
                Labyrinth.setMapName("mapPack/floor3roomD.png");
                Labyrinth.heroRelocate(165,380);
                entered = true;
            }
        } else if(filename.equals("mapPack/floor3roomD.png")) {
            if(hero.getBounds().intersects(doorLeft)) {
                Labyrinth.setMapName("mapPack/floor3roomC.png");
                Labyrinth.heroRelocate(1030, 380);
                entered = true;
            } else if(hero.getBounds().intersects(doorBot)) {
                Labyrinth.setMapName("mapPack/floor3roomO.png");
                Labyrinth.heroRelocate(580,165);
                entered = true;
            } else if(hero.getBounds().intersects(doorRight)) {
                Labyrinth.setMapName("mapPack/floor3roomE.png");
                Labyrinth.heroRelocate(165, 380);
                entered = true;
            }
        } else if(filename.equals("mapPack/floor3roomE.png")) {
            if(hero.getBounds().intersects(doorLeft)) {
                Labyrinth.setMapName("mapPack/floor3roomD.png");
                Labyrinth.heroRelocate(1030, 380);
                entered = true;
            } else if(hero.getBounds().intersects(doorTop)) {
                Labyrinth.setMapName("mapPack/floor3roomF.png");
                Labyrinth.heroRelocate(580, 630);
                entered = true;
            }
        } else if(filename.equals("mapPack/floor3roomF.png")) {
            if(hero.getBounds().intersects(doorBot)) {
                Labyrinth.setMapName("mapPack/floor3roomE.png");
                Labyrinth.heroRelocate(580,165);
                entered = true;
            } else if(hero.getBounds().intersects(doorTop)) {
                Labyrinth.setMapName("mapPack/floor3roomG.png");
                Labyrinth.heroRelocate(580,630);
                entered = true;
            }
        } else if(filename.equals("mapPack/floor3roomG.png")) {
            if(hero.getBounds().intersects(doorBot)) {
                Labyrinth.setMapName("mapPack/floor3roomF.png");
                Labyrinth.heroRelocate(580, 165);
                entered = true;
            } else if(hero.getBounds().intersects(doorLeft)) {
                Labyrinth.setMapName("mapPack/floor3roomH.png");
                Labyrinth.heroRelocate(1030,380);
                entered = true;
            }
        } else if(filename.equals("mapPack/floor3roomH.png")) {
            if(hero.getBounds().intersects(doorRight)) {
                Labyrinth.setMapName("mapPack/floor3roomG.png");
                Labyrinth.heroRelocate(165,380);
                entered = true;
            } else if(hero.getBounds().intersects(doorBot)) {
                Labyrinth.setMapName("mapPack/floor3roomA.png");
                Labyrinth.heroRelocate(580,165);
                entered = true;
            } else if(hero.getBounds().intersects(doorLeft)) {
                Labyrinth.setMapName("mapPack/floor3roomI.png");
                Labyrinth.heroRelocate(1030,380);
                entered = true;
            }
        } else if(filename.equals("mapPack/floor3roomI.png")) {
            if(hero.getBounds().intersects(doorRight)) {
                Labyrinth.setMapName("mapPack/floor3roomH.png");
                Labyrinth.heroRelocate(165,380);
                entered = true;
            } else if(hero.getBounds().intersects(doorLeft)) {
                Labyrinth.setMapName("mapPack/floor3roomJ.png");
                Labyrinth.heroRelocate(1030,380);
                entered = true;
            }
        } else if(filename.equals("mapPack/floor3roomJ.png")) {
            if(hero.getBounds().intersects(doorRight)) {
                Labyrinth.setMapName("mapPack/floor3roomI.png");
                Labyrinth.heroRelocate(165,380);
                entered = true;
            } else if(hero.getBounds().intersects(doorBot)) {
                Labyrinth.setMapName("mapPack/floor3roomK.png");
                Labyrinth.heroRelocate(580,165);
                entered = true;
            }
        } else if(filename.equals("mapPack/floor3roomK.png")) {
            if(hero.getBounds().intersects(doorTop)) {
                Labyrinth.setMapName("mapPack/floor3roomJ.png");
                Labyrinth.heroRelocate(580,630);
                entered = true;
            } else if(hero.getBounds().intersects(doorBot)) {
                Labyrinth.setMapName("mapPack/floor3roomL.png");
                Labyrinth.heroRelocate(580, 165);
                entered = true;
            }
        } else if(filename.equals("mapPack/floor3roomL.png")) {
            if(hero.getBounds().intersects(doorTop)) {
                Labyrinth.setMapName("mapPack/floor3roomK.png");
                Labyrinth.heroRelocate(580, 630);
                entered = true;
            } else if(hero.getBounds().intersects(doorBot)) {
                Labyrinth.setMapName("mapPack/floor3roomM.png");
                Labyrinth.heroRelocate(580,165);
                entered = true;
            }
        } else if(filename.equals("mapPack/floor3roomM.png")) {
            if(hero.getBounds().intersects(doorTop)) {
                Labyrinth.setMapName("mapPack/floor3roomL.png");
                Labyrinth.heroRelocate(580,630);
                entered = true;
            } else if(hero.getBounds().intersects(doorRight)) {
                Labyrinth.setMapName("mapPack/floor3roomN.png");
                Labyrinth.heroRelocate(165,380);
                entered = true;
            }
        } else if(filename.equals("mapPack/floor3roomN.png")) {
            if(hero.getBounds().intersects(doorLeft)) {
                Labyrinth.setMapName("mapPack/floor3roomM.png");
                Labyrinth.heroRelocate(1030,380);
                entered = true;
            } else if (hero.getBounds().intersects(doorRight)) {
                Labyrinth.setMapName("mapPack/floor3roomO.png");
                Labyrinth.heroRelocate(165,380);
                entered = true;
            }
        } else if(filename.equals("mapPack/floor3roomO.png")) {
            if(hero.getBounds().intersects(doorLeft)) {
                Labyrinth.setMapName("mapPack/floor3roomN.png");
                Labyrinth.heroRelocate(1030,380);
                entered = true;
            } else if(hero.getBounds().intersects(doorTop)) {
                Labyrinth.setMapName("mapPack/floor3roomD.png");
                Labyrinth.heroRelocate(580,630);
                entered = true;
            } else if(hero.getBounds().intersects(doorRight)) {
                Labyrinth.setMapName("mapPack/floor3roomP.png");
                Labyrinth.heroRelocate(165,380);
                entered = true;
            }
        } else if(filename.equals("mapPack/floor3roomP.png")) {
            if(hero.getBounds().intersects(doorLeft)) {
                Labyrinth.setMapName("mapPack/floor3roomO.png");
                Labyrinth.heroRelocate(1030,380);
                entered = true;
            } else if(hero.getBounds().intersects(doorRight)) {
                Labyrinth.setMapName("mapPack/floor3roomQ.png");
                Labyrinth.heroRelocate(165,380);
                entered = true;
            }
        } else if(filename.equals("mapPack/floor3roomQ.png")) {
            if(hero.getBounds().intersects(doorLeft)) {
                Labyrinth.setMapName("mapPack/floor3roomP.png");
                Labyrinth.heroRelocate(1030,380);
                entered = true;
            }
        }
    }
}
