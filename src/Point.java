// Represents a point on the Plane
public class Point {

    private double x;
    private double y;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    // Add point methods here

    public Point subtract(Point p){
        return new Point(this.x - p.getX(), this.y - p.getY());
    }

    public double crossProduct(Point p){
        return this.y-p.getX() - p.getY()-this.x;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
