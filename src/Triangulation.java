import java.util.ArrayList;
import java.util.List;

public class Triangulation {

    private List<Point> inputPoints;
    private TriangleSet outputTriangles;

    public Triangulation(List<Point> inputPoints){
        this.inputPoints = inputPoints;
        this.outputTriangles = new TriangleSet();
    }

    public void triangulate(){

        /*
         *Initialize the output triangles array
         */
        outputTriangles = new TriangleSet();

        /*
         * find co-ordinates for the super triangle
         */
        double maxOfAllCoordinates = 0;

        for(Point p : inputPoints){
            maxOfAllCoordinates = Math.max(p.getX(), Math.max(p.getY(), maxOfAllCoordinates));
        }

        maxOfAllCoordinates *= 16;

        Point p1 = new Point(0, 3 * maxOfAllCoordinates);
        Point p2 = new Point(3 * maxOfAllCoordinates, 0);
        Point p3 = new Point(-3 * maxOfAllCoordinates, -3 * maxOfAllCoordinates);

        Triangle superTriangle = new Triangle(p1, p2, p3);

        outputTriangles.add(superTriangle);

        // TODO: add shuffle step here

        /*
         * Run the algorithm for all points
         */
        for(int i=0; i<inputPoints.size(); i++){
            Triangle triangle = outputTriangles.findTriangleContainingPoint(inputPoints.get(i));

            // No triangle contains the point => point lies on an edge
            if(triangle == null){

            }
            // Point lies inside a triangle
            else{
                Point a = triangle.getA();
                Point b = triangle.getB();
                Point c = triangle.getC();

                outputTriangles.remove(triangle);

                Triangle first = new Triangle(a, b, inputPoints.get(i));
                Triangle second = new Triangle(b, c, inputPoints.get(i));
                Triangle third = new Triangle(c, a, inputPoints.get(i));

                outputTriangles.add(first);
                outputTriangles.add(second);
                outputTriangles.add(third);

                performFlip(first, new Edge(a, b), inputPoints.get(i));
                performFlip(second, new Edge(b, c), inputPoints.get(i));
                performFlip(third, new Edge(c, a), inputPoints.get(i));
            }
        }

        outputTriangles.removeAllTrianglesWithPoint(p1);
        outputTriangles.removeAllTrianglesWithPoint(p2);
        outputTriangles.removeAllTrianglesWithPoint(p3);

    }

    public void performFlip(Triangle triangle, Edge edge, Point point){

        Triangle triangleSharingEdge = outputTriangles.findTriangleSharingEdge(triangle, edge);

        if(triangleSharingEdge == null) return;

        if(triangleSharingEdge.isPointInCircumcircle(point)){
            // if another point lies inside the circumcircle, we have to flip the edges
            outputTriangles.remove(triangle);
            outputTriangles.remove(triangleSharingEdge);

            Point commonVertexOfNewTriangles = triangleSharingEdge.getVertexNotOnEdge(edge);

            Triangle firstTriangle = new Triangle(commonVertexOfNewTriangles, edge.getA(), point);
            Triangle secondTriangle = new Triangle(commonVertexOfNewTriangles, edge.getB(), point);

            outputTriangles.add(firstTriangle);
            outputTriangles.add(secondTriangle);

            performFlip(firstTriangle, new Edge(commonVertexOfNewTriangles, edge.getA()), point);
            performFlip(secondTriangle, new Edge(commonVertexOfNewTriangles, edge.getB()), point);
        }

    }
}
