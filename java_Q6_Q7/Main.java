import java.awt.Point;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import java.io.FileWriter;
import java.io.File;

/**
 * Class used to answer to the question 6 and 7.
 * It uses the Building and SkyLine classes.
 */
public class Main {

    /**
     * Main function. It shows the results to the questions 6 and 7.
     */
    public static void main(String[] args) {
	q6result();
	System.out.println("");
	q7result();
	return;
    }

    /**
     * Question 7
     * Function printing the SkyLine created from a list of Building.
     * Creates a svg file representing this SkyLine.
     */
    static void q7result() {
	System.out.println("====QUESTION 7====");
	
	List<Building> buildings = Building.createBuildingList(new Building(3,13,9), new Building(1,11,5), new Building(19,18,22), new Building(3,6,7), new Building(16,3,25), new Building(12,7,16));
	System.out.println("Buildings List : " + Building.toString(buildings));
	
	SkyLine skyLine = mergeBuildingList(buildings);
	System.out.println("->Corresponding SkyLine : " + skyLine);
	
	printSkyLineSVG(skyLine,"q7SkyLine.svg");
    }
    
    /**
     * Question 6
     * Function printing the SkyLine created from the fusion of two SkyLines.
     * Creates a svg file representing this SkyLine.
     */
    static void q6result() {
	System.out.println("====QUESTION 6====");

	SkyLine points1 = new SkyLine(new Point(1,10), new Point(5,6), new Point(8,0), new Point(10,8), new Point(12,0));
	System.out.println("First line : " + points1);
	
	SkyLine points2 = new SkyLine(new Point(2,12), new Point(7,0), new Point(9,4), new Point(11,2), new Point(14,0));
	System.out.println("Second line : " + points2);
	
	SkyLine mergedLine = SkyLine.mergeSkyLines(points1, points2);
	System.out.println("-> Merged line : " + mergedLine);	
	printSkyLineSVG(mergedLine, "q6SkyLine.svg");
    }

    /**
     * Used by the Question 7.
     * Creates a SkyLine from a list of Buildings.
     * @param buildings a list of Buildings
     * @return a SkyLine containing the Buildings of the list given as a parameter
     */
    static SkyLine mergeBuildingList(List<Building> buildings) {
	List<SkyLine> skyLines = new LinkedList<SkyLine>();
	for(Building building : buildings)
	    skyLines.add(building.toSkyLine());

	return SkyLine.foldSkyLines(skyLines);
    }

    /**
     * Opens a .svg file and creates a drawing corresponding to the SkyLine given in parameter.
     * @param skyline the SkyLine we want to draw
     * @param fileName the name of the file we want to write in
     */
    static void printSkyLineSVG(SkyLine skyline, String fileName) {
	File f = new File(fileName);
	try {
	    FileWriter fw = new FileWriter(f);
		    
	    fw.write("<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" width=\"300\" height=\"300\" viewBox=\"-10 -150 200 150\">\n");
	    String skyLine = new String();
	    skyLine = (int)skyline.get(0).getX() + "," + 0 + " ";
	    skyLine += (int)skyline.get(0).getX() + "," + (int)skyline.get(0).getY() + " ";
	    for (int i=1; i<skyline.size(); i++) {
		skyLine += (int)skyline.get(i).getX() + "," + (int)skyline.get(i-1).getY() + " ";
		skyLine += (int)skyline.get(i).getX() + "," + (int)skyline.get(i).getY() + " ";
	    }
	    fw.write(" <polyline points= \"" + skyLine + "\" stroke=\"black\" stroke-width=\"0.2\" fill=\"lightblue\" transform=\" scale(5,-5) \" /></svg>\n");
	    
	    fw.close();
	}
	catch (Exception exception) {
	    System.out.println ("Error while trying to write to the file : " + exception.getMessage());
	}
    }
}
