import java.awt.Point;
import java.util.List;
import java.util.LinkedList;

/**
 * Class representing a Building 
 */
public class Building
{
    private int a;
    private int b;
    private int c;

    public Building(int a, int b, int c)
    {
	this.a = a;
	this.b = b;
	this.c = c;
    }

    public int getA() {
	return a;
    }
    
    public int getB() {
	return b;
    }
    
    public int getC() {
	return c;
    }

    public SkyLine toSkyLine() {
	SkyLine skyLine = new SkyLine();
	skyLine.add(new Point(a, b));
	skyLine.add(new Point(c, 0));
	return skyLine;
    }

    public static List<Building> createBuildingList(Building...buildings) {
	List<Building> newList = new LinkedList<Building>();
	
	for (Building bd : buildings) {
	    newList.add(bd);
	}

	return newList;
    }    
}
