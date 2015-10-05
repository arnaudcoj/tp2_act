import java.awt.Point;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
	q7result();
	return;
    }

    static void q7result() {
	List<Building> buildings = Building.createBuildingList(new Building(3,13,9), new Building(1,11,5), new Building(19,18,22), new Building(3,6,7), new Building(16,3,25), new Building(12,7,16));
	SkyLine mergedLine = mergeBuildingList(buildings);
	System.out.println(mergedLine);
    }
    
    static void q6result() {
	SkyLine points1 = new SkyLine(new Point(1,10), new Point(5,6), new Point(8,0), new Point(10,8), new Point(12,0));
	System.out.println(points1);
	
	SkyLine points2 = new SkyLine(new Point(2,12), new Point(7,0), new Point(9,4), new Point(11,2), new Point(14,0));
	System.out.println(points2);
	
	SkyLine mergedLine = SkyLine.mergeSkyLines(points1, points2);
	System.out.println(mergedLine);	
    }

    static SkyLine mergeBuildingList(List<Building> buildings) {
	List<SkyLine> skyLines = new LinkedList<SkyLine>();
	for(Building building : buildings)
	    skyLines.add(building.toSkyLine());

	return SkyLine.foldSkyLines(skyLines);
    }

}
