/**
 * @author Lukas Gierth
 * Hochschule Bochum
 * LICENSE: GPLv3
 **/

public class Annotation {

	private Integer objectID;
	private String location;
	private String type;
	private String date;
	private String ID;
	private Integer order;
	private String relation;
	private Integer detaillevel;
	private String label;
	private String glabel;
	
	public Annotation(Integer objectID, String location, String type) {
		this.objectID = objectID;
		this.location = location;
		this.type = type;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public Integer getDetaillevel() {
		return detaillevel;
	}

	public void setDetaillevel(Integer detaillevel) {
		this.detaillevel = detaillevel;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getGLabel() {
		return glabel;
	}

	public void setGLabel(String glabel) {
		this.glabel = glabel;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public void getObject() {
		System.out.println("Object: "+objectID+"\nType: "+type+"\nID: "+ID+"\nOrder-ID: "+order+"\nLocation: "+location+
							"\nDate: "+date+"\nRelation: "+relation+"\nDetaillevel: "+detaillevel+
							"\nLabel: "+label+"\nGlobal Label: "+glabel+"\n\n");
	}
}