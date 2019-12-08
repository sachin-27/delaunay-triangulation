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

}
