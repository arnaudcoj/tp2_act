import java.awt.Point;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

public class SkyLine extends LinkedList<Point> {

    public SkyLine(Point...points) {
        super();
	
	for (Point pt : points)
	    this.add(pt);
    }	

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
    
    public String toString() {
	String res = "";
	for(Point pt : this)
	    res += "(" + pt.x + "," + pt.y + ") ";
	return res;
    }
    
    public static SkyLine mergeSkyLines(SkyLine line1, SkyLine line2) {

	line1.add(new Point(-1,-1));
	line2.add(new Point(-1,-1));
	
	SkyLine newLine = new SkyLine();
	
	Iterator<Point> it1 = line1.iterator();
	Iterator<Point> it2 = line2.iterator();
	
	Point pt1 = it1.next();
	Point pt2 = it2.next();
	
	int h1 = 0;
	int h2 = 0;

	while(pt1.x != -1 && pt2.x != -1) {
	    if(pt1.x < pt2.x || (pt1.x == pt2.x && pt1.y > pt2.y)) {
		h1 = pt1.y;
		newLine.add(new Point(pt1.x, Integer.max(h1, h2)));
		if(it1.hasNext())
		    pt1 = it1.next();
	    } else {
		h2 = pt2.y;
		newLine.add(new Point(pt2.x, Integer.max(h1, h2)));
		if(it2.hasNext())
		    pt2 = it2.next();
	    }
	}


	while(pt1.x != -1 && it1.hasNext()) {
	    newLine.add(pt1);
	    pt1 = it1.next();
	}

	while(pt2.x != -1 && it2.hasNext()) {
	    newLine.add(pt2);
	    pt2 = it2.next();
	}
	
	newLine.deleteDuplicate();
	
	return newLine;
    }
        
    public static SkyLine foldSkyLines(List<SkyLine> skyLineList) {

	Iterator<SkyLine> it = skyLineList.iterator();
	SkyLine line1;
	SkyLine line2;
	SkyLine mergedLine;
	
	if (!it.hasNext())
	    throw new IllegalArgumentException();

	line1 = it.next();
	it.remove();
	
	if(!it.hasNext())
	    return line1;

	line2 = it.next();
	it.remove();
	
	mergedLine = mergeSkyLines(line1, line2);

	skyLineList.add(mergedLine);
	
	return foldSkyLines(skyLineList);
    }

}
