package es.p50.sensorsGenerator.co2;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CO2Generator {

    private final CO2 co2_1 = new CO2(0, "Double", "C02", "Parts-per-million", "ppm", 4200, 0);
    private final CO2 co2_2 = new CO2(1, "Double", "C02", "Parts-per-million", "ppm", 4200, 0);

    @Autowired 
    CO2Producer producer;

    @Scheduled(fixedRate = 15000)   // every 15 seconds
    public void changeC02_1() {   // simulate "normal" co2 oscillations
        Random randint = new Random();
        int random = randint.nextInt(2)+1; // value will be 1 or 2
        if (random==1) increaseC02_1();
        else decreaseC02_1();
    }

    public void increaseC02_1() {
        this.co2_1.setValue((int)(this.co2_1.getValue()*10) + 1);
        this.co2_1.setTimestamp(this.co2_1.getTimestamp() + 1);
        producer.sendCO2(this.co2_1);
        //System.out.println("New co2 level publish on kafka.");
    }

    public void decreaseC02_1() {
        this.co2_1.setValue((int)(this.co2_1.getValue()*10) - 1);
        this.co2_1.setTimestamp(this.co2_1.getTimestamp() + 1);
        producer.sendCO2(this.co2_1);
        //System.out.println("New co2 level publish on kafka.");
    }

/////

    @Scheduled(fixedRate = 6000)   // every 6 seconds
    public void changeC02_2() {   // simulate "normal" co2 oscillations
        Random randint = new Random();
        int random = randint.nextInt(2)+1; // value will be 1 or 2
        if (random==1) increaseC02_2();
        else decreaseC02_2();
    }

    public void increaseC02_2() {
        this.co2_2.setValue((int)(this.co2_2.getValue()*10) + 1);
        this.co2_2.setTimestamp(this.co2_2.getTimestamp() + 1);
        producer.sendCO2(this.co2_2);
        //System.out.println("New co2 level publish on kafka.");
    }

    public void decreaseC02_2() {
        this.co2_2.setValue((int)(this.co2_2.getValue()*10) -1);
        this.co2_2.setTimestamp(this.co2_2.getTimestamp() + 1);
        producer.sendCO2(this.co2_2);
        //System.out.println("New co2 level publish on kafka.");
    }


}
