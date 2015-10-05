import java.awt.Point;
import java.util.List;
import java.util.LinkedList;

/**
 * Class representing a Building (a Triplet of int)
 */
public class Building
{
    //Fields
    private int g;
    private int h;
    private int d;

    //Methods

    /**
     * @param g an integer correspondig to the left side of a Building
     * @param h an integer correspondig to the height of a Building
     * @param h an integer correspondig to the right side of a Building
     */
    public Building(int g, int h, int d)
    {
	this.g = g;
	this.h = h;
	this.d = d;
    }

    /**
     * Returns the left side of the Building
     * @return the left side of the Building
     */
    public int getG() {
	return g;
    }

    /**
     * Returns the of height the Building
     * @return the of height the Building
     */    
    public int getH() {
	return h;
    }
    
    /**
     * Returns the right side of the Building
     * @return the right side of the Building
     */
    public int getD() {
	return d;
    }

    /**
     * Converts the Building Triplet to a SkyLine composed of two Points. (g,h) (d,0)
     * @return a SkyLine composed of two Points corresponding to the Triplet.
     */
    public SkyLine toSkyLine() {
	SkyLine skyLine = new SkyLine();
	skyLine.add(new Point(g, h));
	skyLine.add(new Point(d, 0));
	return skyLine;
    }

    /**
     * Returns a String corresponding to the Building representation.
     * The String looks like : "(g,h,d)"
     * @return a String corresponding to the Building representation
     */
    public String toString() {
	return "(" + g + "," + h + "," + d + ")"; 
    }

    /**
     * Returns a String corresponding to the representation of a List of Buildings.
     * The String looks like : "(g1,h1,d1), (g2,h2,d2), (gi,hi,di), [...]"
     * @return a String corresponding to the representation of a List of Buildings
     */
    public static String toString(List<Building> buildings) {
	String res = "";
	for(Building bd : buildings)
	    res += bd + " ";
	
	return res;
    }    
    
    /**
     * Creates a list of Building composed of the Buildings given in parameter.
     * @param buildings an infinity of Buildings
     * @return a list of Building composed of the Buildings given in parameter
     */
    public static List<Building> createBuildingList(Building...buildings) {
	List<Building> newList = new LinkedList<Building>();
	
	for (Building bd : buildings) {
	    newList.add(bd);
	}

	return newList;
    }    
}
