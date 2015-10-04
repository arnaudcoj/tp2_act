import java.awt.Point;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
public class Line {

    public static void main(String[] args) {
	List<Point> points1 = createPointsList(new Point(1,10), new Point(5,6), new Point(8,0), new Point(10,8), new Point(12,0));
	printPointsList(points1);
	
	List<Point> points2 = createPointsList(new Point(2,12), new Point(7,0), new Point(9,4), new Point(11,2), new Point(14,0));
	printPointsList(points2);
	
	List<Point> mergedLine = mergeSkyLines(points1, points2);
	printPointsList(mergedLine);
	
	return;
    }

    static List<Point> mergeSkyLines(List<Point> line1, List<Point> line2) {

	line1.add(new Point(-1,-1));
	line2.add(new Point(-1,-1));
	
	List<Point> newLine = new LinkedList<Point>();
	
	Iterator<Point> it1 = line1.iterator();
	Iterator<Point> it2 = line2.iterator();
	
	Point pt1 = it1.next();
	Point pt2 = it2.next();
	
	int h1 = 0;
	int h2 = 0;

	while(pt1.x != -1 && pt2.x != -1) {
	    if(pt1.x < pt2.x || /* pas sur */(pt1.x == pt2.x && pt1.y > pt2.y)) {
		h1 = pt1.y;
		newLine.add(new Point(pt1.x, Integer.max(h1, h2)));
		if(it1.hasNext())
		    pt1 = it1.next();
	    } else /*if(pt1.x > pt2.x || (pt1.x == pt2.x && pt1.y > pt2.y)) */{
		h2 = pt2.y;
		newLine.add(new Point(pt2.x, Integer.max(h1, h2)));
		if(it2.hasNext())
		    pt2 = it2.next();
	    }
	}


	while(pt1.x != -1 && it1.hasNext()) {
	System.out.println("1 = " + pt1.x);
	    newLine.add(pt1);
	    pt1 = it1.next();
	}

	while(pt2.x != -1 && it2.hasNext()) {
	System.out.println("2 = " + pt2.x);
	    newLine.add(pt2);
	    pt2 = it2.next();
	}
	
	
	return deleteDuplicate(newLine);
    }

    static List<Point> deleteDuplicate(List<Point> points) {

	Iterator<Point> itNew = points.iterator();

	Point oldPt = null;
	Point pt = null;
	
	while(itNew.hasNext()) {
	    oldPt = pt;
	    pt = itNew.next();
	    if(oldPt != null && oldPt.y == pt.y)
		itNew.remove();
	}
	return points;
    }
    
    static void printPointsList(List<Point> points) {
	for(Point pt : points)
	    System.out.print("(" + pt.x + "," + pt.y + ") ");
	System.out.println("");
    }
    
    static List<Point> createPointsList(Point...points) {
	List<Point> newList = new LinkedList<Point>();
	
	for (Point pt : points) {
	    newList.add(pt);
	}

	return newList;
    }
}
