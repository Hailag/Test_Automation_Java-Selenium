package nw2.Task2.Task2;

public class Car {
    private int year;
    private String color;

   public Car () {
       year = 2000;
       color = "red";
   }

   public Car (int year) {
       this.year=year;
       color="blue";
   }

   public Car (int year, String color){
       this.year=year;
       this.color=color;
   }
}
