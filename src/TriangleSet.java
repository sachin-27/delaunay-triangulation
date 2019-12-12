import java.util.ArrayList;
import java.util.List;

public class TriangleSet {

    private List<Triangle> triangleList;

    public TriangleSet(){
        this.triangleList = new ArrayList<Triangle>();
    }

    public void add(Triangle triangle){
        this.triangleList.add(triangle);
    }

    public void remove(Triangle triangle){
        this.triangleList.remove(triangle);
    }

    public Triangle findTriangleContainingPoint(Point p){

        // Iterate over all triangles and check if it contains this point
        for(Triangle t : this.triangleList){
            if(t.doesContainPoint(p)){
                return t;
            }
        }
        // if no triangle found, return null
        return null;
    }

    public void removeAllTrianglesWithPoint(Point p){

        List<Triangle> trianglesToBeRemoved = new ArrayList<>();

        for(Triangle t : triangleList){
            if(t.doesContainPoint(p)){
                trianglesToBeRemoved.add(t);
            }
        }

        triangleList.removeAll(trianglesToBeRemoved);
    }

    public Triangle findTriangleSharingEdge(Triangle triangle, Edge edge){
        for(Triangle t : triangleList){
            if(t.hasEdge(edge) && t != triangle){
                return t;
            }
        }
        return null;
    }

}
