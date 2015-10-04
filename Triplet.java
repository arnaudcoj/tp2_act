import java.awt.Point;
import java.util.List;
import java.util.LinkedList;

public class Triplet
{
    private int a;
    private int b;
    private int c;

    public Triplet(int a, int b, int c)
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

    public List<Point> toSkyLine() {
	List<Point> skyLine = new LinkedList<Point>();
	skyLine.add(new Point(a, b));
	skyLine.add(new Point(c, 0));
	return skyLine;
    }
}
