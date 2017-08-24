import java.util.ArrayList;

/**
 * @author Lukas Gierth
 * Hochschule Bochum
 * LICENSE: GPLv3
 **/

public class PathCreator {

	public PathCreator() {
	}
	
	public void buildPathOrTimeLine(String annotationString, Annotation annotation, ArrayList<Path> pathList, ArrayList<TimeLine> timelineList ) {
        	
			/*
			 * No ID exists: only the base path and no timelines
			 */
        	if (annotation.getID() == null) {
        		
	        	if (annotation.getType().equals("@from")) {
	        		pathList.add(new Path("default"));
	        		pathList.get(0).addAnnotation(annotation);       		
	        	}
	        	else if (annotation.getType().equals("@via") || annotation.getType().equals("@to")) {		        		
	        		for (Path path : pathList) {
	        			  if (path.getID().equals("default")) {
	        			    path.addAnnotation(annotation);
	        			  }
	        		}
	        	}
	        	
	        	else {
	        		// nothing
	        	}
        	}
        	
        	/*
        	 * ID exists: path with id or timeline object
        	 */
        	else {
        		if (annotation.getType().equals("@from")) {
	        		pathList.add(new Path(annotation.getID()));
	        		
	        		for (Path path : pathList) {
	        			  if (path.getID().equals(annotation.getID())) {
	        			    path.addAnnotation(annotation);
	        			  }
	        		}
	        	}
	        	else if (annotation.getType().equals("@via") || annotation.getType().equals("@to")) {
	        		for (Path path : pathList) {
	        			  if (path.getID().equals(annotation.getID())) {
	        			    path.addAnnotation(annotation);
	        			  }
	        		}
	        	}
	        
        		/*
        		 * create timelines
        		 */
	        	else {
	        		int counter=0;
	        		for (TimeLine line : timelineList) {
	        			  if (line.getID().equals(annotation.getID())) {
	        			    line.addAnnotation(annotation);
		        			  counter++;
	        			  }
	        		}
	        		
	        		//if a==0, no timeline with this ID --> Create new TimeLine and add the annotation
	        		if (counter==0) {
	        			timelineList.add(new TimeLine(annotation.getID()));
	        			for (TimeLine line : timelineList) {
		        			  if (line.getID().equals(annotation.getID())) {
		        			    line.addAnnotation(annotation);
		        			  }
		        		}
	        		}
	        	}
        	}
	}
}
