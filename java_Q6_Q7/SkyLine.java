import java.awt.Point;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import java.lang.Math;

/**
 * Class representing a SkyLine, i.e. a List of Points classed as a polyline.
 */
public class SkyLine extends LinkedList<Point> {

    /**
     * Constructor of the SkyLine class.
     * Adds each points given to the List.
     * @param points infinity of Points
     */
    public SkyLine(Point...points) {
        super();
	
	for (Point pt : points)
	    this.add(pt);
    }	

    /**
     * Deletes all the consecutive points (except the first one) with the same y in the SkyLine.
     */
    public void deleteDuplicate() {

	Iterator<Point> itNew = this.iterator();

	Point oldPt = null;
	Point pt = null;
	
	while(itNew.hasNext()) {
	    oldPt = pt;
	    pt = itNew.next();
	    if(oldPt != null && oldPt.y == pt.y)
		itNew.remove();
	}
    }

    /**
     * Returns a String corresponding to the SkyLine representation.
     * The String looks like : "(x1,y1) (x2,y2) (xi,yi) [...]"
     * @return a String corresponding to the SkyLine representation
     */
    public String toString() {
	String res = "";
	for(Point pt : this)
	    res += "(" + pt.x + "," + pt.y + ") ";
	return res;
    }

    /**
     * Returns a SkyLine created from the merge of 2 SkyLines.
     * @param line1 the first line to merge
     * @param line2 the first line to merge
     * @return the fusion of the 2 SkyLines
     */
    public static SkyLine mergeSkyLines(SkyLine line1, SkyLine line2) {

	//Adding those two Points allow us to iterate one more time before hasNext() returns false
	line1.add(new Point(-1,-1));
	line2.add(new Point(-1,-1));

	//Initialization
	SkyLine newLine = new SkyLine();
	
	Iterator<Point> it1 = line1.iterator();
	Iterator<Point> it2 = line2.iterator();
	
	Point pt1 = it1.next();
	Point pt2 = it2.next();

	//we create 2 temporary values which will contain the previous height of the added points.
	int h1 = 0;
	int h2 = 0;

	//while there is a Point to iterate for the 2 SkyLines
	while(it1.hasNext() &&  it2.hasNext()) {
	    //for the Point with the smallest x (if the xs are equals, takes the highest y)
	    //book-keeps the height of this point in the corresponding int (h1 if a Point from line1 is added, respectively h2 for line2)
	    //Inserts a point corresponding to the selected x and the maximum of the heights from line1 and line2
	    if(pt1.x < pt2.x || (pt1.x == pt2.x && pt1.y > pt2.y)) {
		h1 = pt1.y;
		newLine.add(new Point(pt1.x, Math.max(h1, h2)));
		pt1 = it1.next();
	    } else {
		h2 = pt2.y;
		newLine.add(new Point(pt2.x, Math.max(h1, h2)));
		pt2 = it2.next();
	    }
	}

	//At this point, the algorithm has finished iterating in at least one of the SkyLines so there is no need to compare the Points now.
	//Fills the newLine with the remaining points of one of the SkyLines.
		
	//Adds the last points of line1 in newLine
	while(pt1.x != -1 && it1.hasNext()) {
	    newLine.add(pt1);
	    pt1 = it1.next();
	}

	//Adds the last points of line2 in newLine
	while(pt2.x != -1 && it2.hasNext()) {
	    newLine.add(pt2);
	    pt2 = it2.next();
	}

	//Deletes the consecutive Points with same y.
	newLine.deleteDuplicate();
	
	return newLine;
    }

    /**
     * Creates a SkyLine from the merge of the SkyLines of the list given in parameter.
     * Uses a particular fold : it merges the first two Lines using mergeSkyLines, then stores the result at the end of the List. (looks like a mix of the foldl and foldr).
     * The reason this fold is used is because it is less expensive to take elements from the begining of a List and because we can't add an element at its end.
     * Used for the Question 7.
     * @param skyLineList a list of Skylines (Must not be empty)
     * @return a SkyLine created from the merge of the SkyLines of the list given in parameter.
     * @throw IllegalArgumentException if skyLineList is empty
     */
    public static SkyLine foldSkyLines(List<SkyLine> skyLineList) {

	Iterator<SkyLine> it = skyLineList.iterator();
	SkyLine line1;
	SkyLine line2;
	SkyLine mergedLine;

	//Throws an IllegalArgumentException if skyLineList is empty
	if (!it.hasNext())
	    throw new IllegalArgumentException();

	//Takes the first element and remove it from the list
	line1 = it.next();
	it.remove();

	//If there is just one element, this is the end of the algorithm, it returns this element.
	if(!it.hasNext())
	    return line1;

	//Takes the second element and remove it from the list
	line2 = it.next();
	it.remove();

	//Merges the two elements
	mergedLine = mergeSkyLines(line1, line2);

	//Stores the result at the end of the list
	skyLineList.add(mergedLine);

	//Calls foldSkyLines again until the algorithm is finished
	return foldSkyLines(skyLineList);
    }

}
