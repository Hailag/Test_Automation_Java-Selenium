package nw2.Task4;

/**
 * Created by o.iakovenko on 09.03.2017.
 */
public class Car {
    int year;
    double speed;
    int weight;
    String color;

    public Car(){
        year = 1;
        speed = 1;
        weight = 1;
        color = "white";
    }

    public Car (int year){
        this.year = year;
    }

    public Car (int year, double speed){
        this(year);
        this.speed = speed;
    }

    public Car (int year, double speed, int weight) {
        this(year, speed);
        this.weight = weight;
    }

    public Car (int year, double speed, int weight, String color) {
        this(year, speed, weight);
        this.color = color;
    }

    @Override
    public String toString() {
        return "year = " + year + " speed = " + speed + " weight = " + weight + " color = " + color;
    }

}
