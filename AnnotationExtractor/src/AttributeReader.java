import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Lukas Gierth
 * Hochschule Bochum
 * LICENSE: GPLv3
 **/

public class AttributeReader {

	public AttributeReader() {}

	/*
	 * Read annotation base values
	 * type(indicator) and annotated location
	 */
	public void readBase(Integer j, String annotationString,  String locationString, ArrayList<Annotation> annotationList) {
		        String annotationParts[] = annotationString.split(" ", 2);
		        String type = annotationParts[0];
		        annotationList.add(new Annotation(j, locationString,type));
	}

	/*
	 * Read ID
	 * used to group annotations of a path or timeline
	 */
	public void readID(String annotationString, Annotation annotation) {
	  	Pattern idPattern = Pattern.compile("id:\"(.*?)[\"]");
	  	Matcher m = idPattern.matcher(annotationString);
	  	
	  	if (m.find() == true) {
	  		annotation.setID(m.group(1));
	    }
	  	else {
	  		annotation.setID(null);
	  	}
	}

	/*
	 * Read labels
	 */
	public void readLabel(String annotationString, Annotation annotation) {
	  	Pattern labelPattern = Pattern.compile("label:\"(.*?)[\"]");
	  	Matcher m = labelPattern.matcher(annotationString);
	  	
	  	if (m.find() == true) {
	  		annotation.setLabel(m.group(1));
	    }
	  	else {
	  		annotation.setLabel(null);
	  	}
	}
	
	/*
	 * Read global labels
	 */
	public void readGlobalLabel(String annotationString, Annotation annotation) {
		Pattern glabelPattern = Pattern.compile("glabel:\"(.*?)[\"]");
	  	Matcher m = glabelPattern.matcher(annotationString);
	  	
	  	if (m.find() == true) {
	  		annotation.setGLabel(m.group(1));
	    }
	  	else {
	  		annotation.setGLabel(null);
	  	}
	}

	/*
	 * Read detaillevel
	 * throws exception when value is not an integer
	 */
	public void readDetailLevel(String annotationString, Annotation annotation) {
	  	Pattern dlPattern = Pattern.compile("dl:\"(.*?)[\"]");
	  	Matcher m = dlPattern.matcher(annotationString);
	  	
	  	if (m.find() == true) {

				try {
					if(Integer.parseInt(m.group(1)) < 5 && Integer.parseInt(m.group(1)) > -1) {
						annotation.setDetaillevel(Integer.parseInt(m.group(1)));
					}
					else {
						System.out.println("dl not between 0-4, attribute set null");
						annotation.setDetaillevel(null);
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
					System.out.println("Error: Detaillevel has to be an integer");
				}
	    }
	  	else {
	  		annotation.setDetaillevel(null);
	  	}
	}

	/*
	 * Read relation to spatial information
	 */
	public void readRelation(String annotationString, Annotation annotation) {
	  	Pattern relPattern = Pattern.compile("rel:\"(.*?)[\"]");
	  	Matcher m = relPattern.matcher(annotationString);
	  	
	  	if (m.find() == true) {
	  		annotation.setRelation(m.group(1));
	    }
	  	else {
	  		annotation.setRelation(null);
	  	}
	}
	
	/*
	 * Read date field
	 */
	public void readDate(String annotationString, Annotation annotation) {
	  	Pattern datePattern = Pattern.compile("date:\"(.*?)[\"]");
	  	Matcher m = datePattern.matcher(annotationString);
	  	
	  	if (m.find() == true) {
	  		annotation.setDate(m.group(1));
	    }
	  	else {
	  		annotation.setDate(null);
	  	}
	}
	
	/*
	 * Read order-id (attribute p)
	 * throws exception when value is not an integer
	 */
	public void readOrder(String annotationString, Annotation annotation) {
	  	Pattern datePattern = Pattern.compile("p:\"(.*?)[\"]");
	  	Matcher m = datePattern.matcher(annotationString);
	  	
	  	if (m.find() == true) {
	  		try {
				annotation.setOrder(Integer.parseInt(m.group(1)));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				System.out.println("Error: Attribute p has to be an integer");
			}
	    }
	  	else {
	  		annotation.setOrder(null);
	  	}
	}
}