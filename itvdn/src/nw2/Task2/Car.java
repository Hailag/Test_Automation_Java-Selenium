package nw2.Task2;


public class Car {
    int year;
    String color;

    public Car () {
        year = 2000;
        color = "yellow";
    }

    public Car (int year){
        this.year=year;
        color="red";
    }

    public Car (int year, String color) {
        this.year = year;
        this.color = color;
    }

}
