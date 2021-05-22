package es.p50.sensorsGenerator.humidity;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class HumidityGenerator {

    private final Humidity humidity1 = new Humidity(2, "Double", "Humidity", "Percentage", '%', 180, 0);
    private final Humidity humidity2 = new Humidity(3, "Double", "Humidity", "Percentage", '%', 670, 0);
    private final Humidity humidity3 = new Humidity(4, "Double", "Humidity", "Percentage", '%', 500, 0);

    private boolean action1 = false;
    private boolean action2 = false;
    private boolean action3 = false;
    private int random1;
    private int random2;
    private int random3;

    @Autowired
    HumidityProducer producer;

    @Scheduled(fixedRate = 23000)   // every 23 seconds
    public void changeHumidity1() {   // simulate "normal" co2 oscillations
        if (!this.action1) {
            Random randint = new Random();
            this.random1 = randint.nextInt(2)+1; // value will be 1 or 2
        }
        if (random1==1) increaseHumidity1();
        else decreaseHumidity1();
    }

    public void increaseHumidity1() {
        if(this.humidity1.getValue()!=100) {
            this.humidity1.setValue((int)(this.humidity1.getValue()*10) + 10);
            this.humidity1.setTimestamp(this.humidity1.getTimestamp() + 1);
            producer.sendHumidity(this.humidity1);
            //System.out.println("New humidity level publish on kafka.");
        }
        else System.out.println("Cant increase anymore.");
    }

    public void decreaseHumidity1() {
        if(this.humidity1.getValue()!=0) {
            this.humidity1.setValue((int)(this.humidity1.getValue()*10) - 10);
            this.humidity1.setTimestamp(this.humidity1.getTimestamp() + 1);
            producer.sendHumidity(this.humidity1);
            //System.out.println("New humidity level publish on kafka.");
        }
        else System.out.println("Cant decrease anymore.");
    }

/////

    @Scheduled(fixedRate = 17000)   // every 17 seconds
    public void changeHumidity2() {   // simulate "normal" co2 oscillations
        if (!this.action2) {
            Random randint = new Random();
            this.random2 = randint.nextInt(2)+1; // value will be 1 or 2
        }
        if (this.random2==1) increaseHumidity2();
        else decreaseHumidity2();
    }

    public void increaseHumidity2() {
        if(this.humidity2.getValue()!=100) {
            this.humidity2.setValue((int)(this.humidity2.getValue()*10) + 10);
            this.humidity2.setTimestamp(this.humidity2.getTimestamp() + 1);
            producer.sendHumidity(this.humidity2);
            //System.out.println("New humidity level publish on kafka.");
        }
        else System.out.println("Cant increase anymore.");
    }

    public void decreaseHumidity2() {
        if(this.humidity2.getValue()!=0) {
            this.humidity2.setValue((int)(this.humidity2.getValue()*10) - 10);
            this.humidity2.setTimestamp(this.humidity2.getTimestamp() + 1);
            producer.sendHumidity(this.humidity2);
            //System.out.println("New humidity level publish on kafka.");
        }
        else System.out.println("Cant decrease anymore.");
    }

    /////

    @Scheduled(fixedRate = 19000)   // every 19 seconds
    public void changeHumidity3() {   // simulate "normal" co2 oscillations
        if (!this.action3) {
            Random randint = new Random();
            this.random3 = randint.nextInt(2)+1; // value will be 1 or 2
        }
        if (this.random3==1) increaseHumidity3();
        else decreaseHumidity3();
    }

    public void increaseHumidity3() {
        if(this.humidity3.getValue()!=100) {
            this.humidity3.setValue((int)(this.humidity3.getValue()*10) + 10);
            this.humidity3.setTimestamp(this.humidity3.getTimestamp() + 1);
            producer.sendHumidity(this.humidity3);
            //System.out.println("New humidity level publish on kafka.");
        }
        else System.out.println("Cant increase anymore.");
    }

    public void decreaseHumidity3() {
        if(this.humidity3.getValue()!=0) {
            this.humidity3.setValue((int)(this.humidity3.getValue()*10) - 10);
            this.humidity3.setTimestamp(this.humidity3.getTimestamp() + 1);
            producer.sendHumidity(this.humidity3);
            //System.out.println("New humidity level publish on kafka.");
        }
        else System.out.println("Cant decrease anymore.");
    }


// AUXILIAR

    public boolean getAction1() {
        return this.action1;
    }

    public boolean getAction2() {
        return this.action2;
    }

    public void setAction1(boolean action1) {
        this.action1 = action1;
    }

    public void setAction2(boolean action2) {
        this.action2 = action2;
    }

    public void setAction3(boolean action3) {
        this.action3 = action3;
    }

    public void setRandom1(int random1) {
        this.random1 = random1;
    }

    public void setRandom2(int random2) {
        this.random2 = random2;
    }

    public void setRandom3(int random3) {
        this.random3 = random3;
    }

}
