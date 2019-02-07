package labyrinth;

import java.util.TimerTask;

public class gameTimer {
    int secondsPassed = 0;
    int minutesPassed = 0;
    String elapsedtime = "";
    
    TimerTask gameTask = new TimerTask() {
        @Override
        public void run() {
            if(Labyrinth.running) {
                if(secondsPassed < 59) secondsPassed++;
                else {
                    secondsPassed = 0;
                    minutesPassed++;
                }
            }
        }
    };
    
    public TimerTask getTimerTask() {
        return gameTask;
    }
    
    public String getElapsedTime() {
        return String.format("Time: %02d:%02d", minutesPassed, secondsPassed);
    }
    
    public String timeSave() {
        return String.format("%02d:%02d",minutesPassed, secondsPassed);
    }
    
}