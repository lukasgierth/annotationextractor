import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Lukas Gierth
 * Hochschule Bochum
 * LICENSE: GPLv3
 **/

public class Path {
	
	private String ID;
    private ArrayList<Annotation> annotationList = new ArrayList<Annotation>();
    private Annotation[] annotationArray = null;

	public Path(String ID) {
		this.ID = ID;
	}
	
	public void addAnnotation(Annotation annotation) {
		annotationList.add(annotation);
	}

	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}
	
	/*
	 * Sorts path annotations based on the p attribute.
	 * Only when all annotations of a path have the p attribute
	 */
	public void sortPath() {
    	annotationArray = annotationList.toArray(new Annotation[0]);
    	int counter=0;
    	
    	for (int j=0; j<annotationArray.length;j++) {
    		if (annotationArray[j].getOrder() != null) {
    			counter++;
    		}
    		else {
    		}
    	}
    	
		if(annotationArray.length == counter) {
	    	Collections.sort(annotationList, (p1, p2) -> p1.getOrder() - p2.getOrder());
		}
	}
	
	/*
	 * Prints all annotations of a path
	 */
	public void printPath() {
    	annotationArray = annotationList.toArray(new Annotation[0]);
    	System.out.println("============ Pfad "+ID+" ===========");
		for (int j=0; j<annotationArray.length; j++) {
			annotationArray[j].getObject();
		}
	}
}
