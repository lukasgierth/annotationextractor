import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Lukas Gierth
 * Hochschule Bochum
 * LICENSE: GPLv3
 **/

public class ExtractionLauncher {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		/*
		 * Instances of:
		 * AnnotationReader: Gets text and extracts the raw annotations and locations as text
		 * AttributeReader: Gets attributes from the annotation body
		 * PathCreator: Builds paths and timelines from existing annotation objects
		 */
		AttributeReader attReader = new AttributeReader();
		AnnotationReader annoReader = new AnnotationReader();
		PathCreator pCreator = new PathCreator();
		
		String[] annotationStrings = annoReader.readAnnotations("/home/lukas/eclipse-workspace/text.txt");
		String[] locationStrings = annoReader.readLocations();
	    Annotation[] annotationArray = null;
	    Path[] pathArray = null;
	    TimeLine [] timelineArray = null;
	    
	    ArrayList<Annotation> annotationList = new ArrayList<Annotation>();
	    ArrayList<Path> pathList = new ArrayList<Path>(); 
	    ArrayList<TimeLine> timelineList = new ArrayList<TimeLine>(); 
	        
	        /*
	         * go through every single annotation
	         */
	        for (int j=0;j<annotationStrings.length;j++) {

	        	/*
	        	 * Read basis information for every annotation
	        	 */
	        	
	        	try {
					attReader.readBase(j, annotationStrings[j], locationStrings[j], annotationList);
					annotationArray = annotationList.toArray(new Annotation[0]);

					attReader.readID(annotationStrings[j], annotationArray[j]);
					attReader.readRelation(annotationStrings[j], annotationArray[j]);
					attReader.readDate(annotationStrings[j], annotationArray[j]);
					attReader.readLabel(annotationStrings[j], annotationArray[j]);
					attReader.readDetailLevel(annotationStrings[j], annotationArray[j]);
					attReader.readOrder(annotationStrings[j], annotationArray[j]);
				} catch (Exception e1) {
					e1.printStackTrace();
					System.out.println("Error in reading the annotation attributes");
				}
	        	
	        	if (annotationArray[j].getType().equals("@from")) {
		        	attReader.readGlobalLabel(annotationStrings[j], annotationArray[j]);
	        	}
	        	else {
	        		annotationArray[j].setGLabel(null);
	        	}
	        	
	        	/*
		         * Create Paths and timelines
		         */
				try {
					pCreator.buildPathOrTimeLine(annotationStrings[j], annotationArray[j], pathList, timelineList);
				} catch (Exception e) {
					e.printStackTrace();
				}

	        }
	        
	        // close path and timeline arrays
	        pathArray = pathList.toArray(new Path[0]);
        	timelineArray = timelineList.toArray(new TimeLine[0]);
        	
        	/*
        	 * sort all paths and timelines
        	 */
        	for (int j=0; j<pathArray.length; j++) {
            	pathArray[j].sortPath();
        	}
        	for (int j=0; j<timelineArray.length; j++) {
            	timelineArray[j].sortTimeLine();
        	}
	        
        	/*
        	 * Test, get all base annotations
        	 */
	        for (int j=0; j<annotationStrings.length;j++) {
	        	if (annotationArray[j].getType().equals("@") && annotationArray[j].getID()==null) {
	            	System.out.println("======== Base Annotation ==========");
			        annotationArray[j].getObject();
	        	}
	        }
	   
	        /*
	         * Test, get all paths and timelines
	         */
	        for (int j=0; j<pathArray.length; j++) {
	        	pathArray[j].printPath();
	        }
	        for (int j=0; j<timelineArray.length; j++) {
		        timelineArray[j].printTimeLine();
	        }
		}
	}
