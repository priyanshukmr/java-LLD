package Patterns.Creational;

/*
Components:
- interface for product
- concrete products
- factory
- client
 */
public class Factory {

    interface Shape {
        void draw();
    }

    static class Circle implements Shape {
        public void draw() {
            System.out.println("Drawing circle");
        }
    }

    static class Square implements Shape {
        public void draw() {
            System.out.println("Drawing Square");
        }
    }

    static class ShapeFactory{

        public Shape createShape(String type) {
            return switch (type) {
                case "Circle" -> new Circle();
                case "Square" -> new Square();
                default -> {
                    System.out.println("Shape not supported by factory, creating circle instead");
                    yield new Circle();
                }
            };
        }
    }

    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();
        Shape shape= shapeFactory.createShape("Circle");
        shape.draw();
        shape = shapeFactory.createShape("Square");
        shape.draw();
        shape = shapeFactory.createShape("Hexagon");
    }

}
