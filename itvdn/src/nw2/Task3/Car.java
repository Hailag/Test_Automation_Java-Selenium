package nw2.Task3;

/**
 * Created by o.iakovenko on 09.03.2017.
 */
public class Car {
    int year;
    double speed;
    int weight;
    String color;

    // Конструктор по умолчанию
    public Car() {
        this.year = -1;
        this.speed = -1;
        this.weight = -1;
        this.color = "white";
    }

    // Конструктор с 1 параметром
    public Car(int year) {
        this.year = year;
    }

    public Car(int year, double speed) {
        this.year = year;
        this.speed = speed;
    }

    // Конструктор с 3 параметром
    public Car(int year, int speed, int weight) {
        this.year = year;
        this.speed = speed;
        this.weight = weight;
    }

    // Конструктор с 4 параметром
    public Car(int year, int speed, int weight, String color) {
        this.year = year;
        this.speed = speed;
        this.weight = weight;
        this.color = color;
    }

    // Перегрузка метода toString();
    @Override
    public String toString() {
        return "year = " + year + " speed = " + speed + " weight = " + weight + " color = " + color;
    }
}
