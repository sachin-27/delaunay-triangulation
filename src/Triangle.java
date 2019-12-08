public class Triangle {

    private Point a;
    private Point b;
    private Point c;

    public Triangle(Point a, Point b, Point c){
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public boolean doesContainPoint(Point p){
        double signPAB = p.subtract(a).crossProduct(b.subtract(a));
        double signPBC = p.subtract(b).crossProduct(c.subtract(b));
        double signPCA = p.subtract(c).crossProduct(a.subtract(c));

        if(hasSameSign(signPAB, signPBC) && hasSameSign(signPBC, signPCA)){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isPointInCircumcircle(Point p){
        // Check if point lies in circumcircle by calculating determinant
        double a11 = a.getX() - p.getX();
        double a21 = b.getX() - p.getX();
        double a31 = c.getX() - p.getX();

        double a12 = a.getY() - p.getY();
        double a22 = b.getY() - p.getY();
        double a32 = c.getY() - p.getY();

        double a13 = (a.getX() - p.getX()) * (a.getX() - p.getX()) + (a.getY() - p.getY()) * (a.getY() - p.getY());
        double a23 = (b.getX() - p.getX()) * (b.getX() - p.getX()) + (b.getY() - p.getY()) * (b.getY() - p.getY());
        double a33 = (c.getX() - p.getX()) * (c.getX() - p.getX()) + (c.getY() - p.getY()) * (c.getY() - p.getY());

        double det = a11 * a22 * a33 + a12 * a23 * a31 + a13 * a21 * a32 - a13 * a22 * a31 - a12 * a21 * a33 - a11 * a23 * a32;

        // Check orientation of the traingle to determine sign of determinant
        if(isClockWise() && det > 0){
            return true;
        }
        if(!isClockWise() && det < 0){
            return true;
        }

        return false;
    }

    public Point getA() {
        return a;
    }

    public void setA(Point a) {
        this.a = a;
    }

    public Point getB() {
        return b;
    }

    public void setB(Point b) {
        this.b = b;
    }

    public Point getC() {
        return c;
    }

    public void setC(Point c) {
        this.c = c;
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                '}';
    }

    // private helper methods for class
    private boolean hasSameSign(double d1, double d2){
        if(d1 > 0 && d2 > 0){
            return true;
        }
        if(d1 < 0 && d2 < 0){
            return true;
        }

        return false;
    }

    // Checks if orientation of triangle is clockwise or anticlockwise
    private boolean isClockWise(){
        double a11 = a.getX() - c.getX();
        double a21 = b.getX() - c.getX();

        double a12 = a.getY() - c.getY();
        double a22 = b.getY() - c.getY();

        double det = a11 * a22 - a12 * a21;

        if(det > 0){
            return true;
        }
        else{
            return false;
        }
    }

}
