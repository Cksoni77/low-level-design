package designpattern.factorydesignpattern;

public class ShapeFactory {

    public Shape getShape(String input) {
       switch (input){
           case "Circle":
               return new Circle();
           case "Rectangle":
               return new Rectangle();
           default:
               return null;
       }
    }
}
