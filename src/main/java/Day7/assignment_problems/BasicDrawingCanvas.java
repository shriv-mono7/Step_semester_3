
package Day7.assignment_problems;

abstract class Shape {
    private static int counter = 1000;
    private final String shapeId;

    public Shape() {
        counter++;
        shapeId = "SHAPE-" + counter;
    }

    public abstract double calculateArea();

    public void scale(double factor) {
        System.out.println("Scaled uniformly by " + factor);
    }

    public void scale(double xFactor, double yFactor) {
        System.out.println(
                "Scaled horizontally by " + xFactor
                + " and vertically by " + yFactor
        );
    }

    public String getShapeId() {
        return shapeId;
    }
}

class CircleShape extends Shape {
    private double radius;

    public CircleShape(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

class SquareShape extends Shape {
    private double side;

    public SquareShape(double side) {
        this.side = side;
    }

    @Override
    public double calculateArea() {
        return side * side;
    }
}

public class BasicDrawingCanvas {

    static void printArea(Shape s) {
        System.out.println(
                "Area of " + s.getShapeId()
                + ": " + s.calculateArea()
        );
    }

    public static void main(String[] args) {

        CircleShape circle = new CircleShape(5);
        SquareShape square = new SquareShape(4);

        printArea(circle);
        printArea(square);

        circle.scale(2.0);
        square.scale(2.0, 3.0);

        Shape ref = circle;
        // Upcasting: CircleShape stored as Shape

        printArea(ref);

        // Shape s = new Shape();
        // Cannot compile because Shape is abstract.
    }
}