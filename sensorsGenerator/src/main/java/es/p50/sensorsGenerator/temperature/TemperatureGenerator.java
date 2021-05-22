package es.p50.sensorsGenerator.temperature;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TemperatureGenerator {

    private final Temperature temp1 = new Temperature(0, "Double", "Temperature", "Celsius", 'C', 250, 0);
    private final Temperature temp2 = new Temperature(1, "Double", "Temperature", "Celsius", 'C', 250, 0);
    
    private boolean action1 = false;
    private boolean action2 = false;
    private int random1;
    private int random2;

    @Autowired
    TemperatureProducer producer;

    @Scheduled(fixedRate = 10000)   // every 10 seconds
    public void changeTemperature1() {   // simulate "normal" temperature oscillations
        if (!this.action1) {
            Random randint = new Random();
            this.random1 = randint.nextInt(2)+1; // value will be 1 or 2
        }
        if (this.random1==1) increaseTemperature1();
        else decreaseTemperature1();
        
    }

    public void increaseTemperature1() {
        this.temp1.setValue((int)(this.temp1.getValue()*10) + 1);
        this.temp1.setTimestamp(this.temp1.getTimestamp() + 1);
        producer.sendTemperature(this.temp1);
        //System.out.println("New temperature publish on kafka.");
    }

    public void decreaseTemperature1() {
        this.temp1.setValue((int)(this.temp1.getValue()*10) - 1);
        this.temp1.setTimestamp(this.temp1.getTimestamp() + 1);
        producer.sendTemperature(this.temp1);
        //System.out.println("New temperature publish on kafka.");
    }

/////

    @Scheduled(fixedRate = 15000)   // every 15 seconds
    public void changeTemperature2() {   // simulate "normal" temperature oscillations
        if (!this.action2) {
            Random randint = new Random();
            this.random2 = randint.nextInt(2)+1; // value will be 1 or 2
        }
        if (this.random2==1) increaseTemperature2();
        else decreaseTemperature2();
    }

    public void increaseTemperature2() {
        this.temp2.setValue((int)(this.temp2.getValue()*10) + 1);
        this.temp2.setTimestamp(this.temp2.getTimestamp() + 1);
        producer.sendTemperature(this.temp2);
        //System.out.println("New temperature publish on kafka.");
    }

    public void decreaseTemperature2() {
        this.temp2.setValue((int)(this.temp2.getValue()*10) -1);
        this.temp2.setTimestamp(this.temp2.getTimestamp() + 1);
        producer.sendTemperature(this.temp2);
        //System.out.println("New temperature publish on kafka.");
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

    public void setRandom1(int random1) {
        this.random1 = random1;
    }

    public void setRandom2(int random2) {
        this.random2 = random2;
    }

}
