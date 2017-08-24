import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Lukas Gierth
 * Hochschule Bochum
 * LICENSE: GPLv3
 **/

public class AnnotationReader {
	
	private static String fulltext = null;
	private static int annotationNumber;
	private static String[] annotationStringArray;

	public AnnotationReader() {}

	public String[] readAnnotations(String file){
			
		/*
		 * Read the complete file into a global String
		 * only done once
		 */
		try(BufferedReader bReader = new BufferedReader(new FileReader(file))) {
		    StringBuilder sBuilder = new StringBuilder();
		    String line = bReader.readLine();

		    while (line != null) {
		        sBuilder.append(line);
		        sBuilder.append(System.lineSeparator());
		        line = bReader.readLine();
		    }
		    String text = sBuilder.toString();
		    fulltext = text.replaceAll("[\\t\\n\\r]"," ");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Regex pattern for the annotation body
	  	Pattern bodyPattern = Pattern.compile("\\{(.*?)\\}");
        Matcher m = bodyPattern.matcher(fulltext);
    	ArrayList<String> annotationStringList = new ArrayList<String>();
     
        /*
         * extract all annotations into a Stringarray
         */
        while (m.find() ) {
        	annotationStringList.add(new String(m.group(1)));
        }
        
        //close array
        annotationStringArray = annotationStringList.toArray(new String[0]);
        annotationNumber = annotationStringArray.length;
        
		return annotationStringArray;
	}


	public String[] readLocations() {
        String[] locationStringArray = new String[annotationNumber];
	  	String[] partialtextArray = fulltext.split("}");
        //wordcount array
        int[] wordcount = new int[annotationNumber];

        for (int j=0;j<annotationNumber;j++) {
        	
        	Pattern wordcountPattern = Pattern.compile("wc:\"(.*?)[\"]");
            Matcher m = wordcountPattern.matcher(annotationStringArray[j]);
        	
        	if (m.find() == true) {
        		try {
        			//wourdcount should be a number and >0
					if (Integer.parseInt(m.group(1)) > 0) {
						wordcount[j] = Integer.parseInt(m.group(1));
					}
					else {
						System.out.println("WC Attribute with an illegal value, set to 1");
		    	  		wordcount[j] = 1;
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
    	    }
    	  	else {
    	  		wordcount[j] = 1;
    	  	}
        	
        	String[] arr = partialtextArray[j+1].split("\\s+"); 
            String location="";
            String word="";

            // concatenating number of words that you required
            for(int i=0; i<wordcount[j] ; i++){
            	if (word.equals("")) {
            		word = arr[i];
            	}
            	else {
            		word = word + " " + arr[i];
            	}
            }
            
            location = word.replaceAll("[,|.|;|'|`|?|!]", "");
            locationStringArray[j] = (location);
        }

		return locationStringArray;
	}

}
